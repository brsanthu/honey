package com.brsanthu.honey.handler;

import static com.brsanthu.eclipse.common.ui.util.EclipseUiUtils.getActiveEditor;
import static com.brsanthu.eclipse.common.ui.util.EclipseUiUtils.setStatusErrorMessage;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;

import com.brsanthu.eclipse.common.ui.util.EclipseUiUtils;

public abstract class AbstractEditorHandler extends AbstractHandler {
    private ExecutionEvent event;
    private HoneyEditor editor;
    
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        this.event = event;
        IEditorPart editorPart = getActiveEditor();
        if (editorPart == null) {
            setStatusErrorMessage("Couldn't get the current text editor.");
            return  null;
        }
        
        editor = new HoneyEditor(editorPart);
        executeCommand();
        
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
        return EclipseUiUtils.getShell(event);
    }

    public abstract void executeCommand();
    
}
