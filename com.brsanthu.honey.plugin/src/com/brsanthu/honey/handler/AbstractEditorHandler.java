package com.brsanthu.honey.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;

import com.brsanthu.eclipseutils.EclipseUtils;
import com.brsanthu.honey.Activator;

import static com.brsanthu.eclipseutils.EclipseUtils.*;

public abstract class AbstractEditorHandler extends AbstractHandler {
    private ExecutionEvent event;
    private HoneyEditor editor;
    
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        this.event = event;
        IEditorPart editorPart = getActiveEditor();
        if (editorPart == null) {
        	setTimedErrorMessage("Couldn't get the current text editor.");
            return  null;
        }
        
        editor = new HoneyEditor(editorPart);
        try {
        	executeCommand();
        } catch (Exception e) {
        	String message = "Exception while executing the command " + getCommandName();
        	Activator.getDefault().logError(message, e);
			EclipseUtils.setTimedErrorMessage(message + " [" + e.toString() + "]");
        }
        
        return null;
    }
    
    public ExecutionEvent getEvent() {
        return event;
    }
    
    public void setEditor(HoneyEditor editor) {
        this.editor = editor;
    }
    
    public HoneyEditor getEditor() {
        return editor;
    }
    
    public String getCommandName() {
        try {
            return event.getCommand().getName();
        } catch (NotDefinedException e) {
            e.printStackTrace();
            return "Commond name not found.";
        }
    }
    
    public Shell getShell() {
        return EclipseUtils.getShell(event);
    }

    public abstract void executeCommand() throws Exception;
    
}
