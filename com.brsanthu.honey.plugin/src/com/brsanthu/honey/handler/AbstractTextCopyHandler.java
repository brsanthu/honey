package com.brsanthu.honey.handler;

import static com.brsanthu.eclipse.common.ui.util.EclipseUiUtils.setClipboard;
import static com.brsanthu.eclipse.common.ui.util.EclipseUiUtils.setTimedStatusErrorMessage;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public abstract class AbstractTextCopyHandler extends AbstractTextHandler {

    @Override
    public void executeCommand(String selectedText) {
        String newText = executeCopyCommand(selectedText);
        if (isEmpty(newText)) {
            setTimedStatusErrorMessage("Command '" + getCommandName() + "' didn't copy anything.");
        } else {
            setClipboard(newText);
        }
    }
    
    public abstract String executeCopyCommand(String selectedText);
}
