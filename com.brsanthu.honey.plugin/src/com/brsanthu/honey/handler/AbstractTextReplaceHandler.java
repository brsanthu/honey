package com.brsanthu.honey.handler;

import static com.brsanthu.eclipseutils.EclipseUtils.*;
public abstract class AbstractTextReplaceHandler extends AbstractTextHandler {

    @Override
    public void executeCommand(String selectedText) {
        TextChangeResponse response = executeReplaceCommand(selectedText);
        if (response == null) {
            return;
        }
        
        Boolean result = false;
        switch(response.getMode()) {
            case INSERT:
                result = getEditor().replaceSelection(response.getNewText());
                break;
            case REPLACE_ALL:
                result = getEditor().setContents(response.getNewText());
                break;
            case REPLACE_SELECTED:
                result = getEditor().replaceSelection(response.getNewText());
                break;
        }
        
        if (!result) {
            setTimedErrorMessage("Command '" + getCommandName() + "' didn't make any changes to the editor.");
        }
        
        getActiveEditor().setFocus();
    }
    
    public abstract TextChangeResponse executeReplaceCommand(String selectedText);
}
