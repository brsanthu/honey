package com.brsanthu.honey.handler;

import static org.apache.commons.lang3.StringUtils.*;
import static com.brsanthu.eclipseutils.EclipseUtils.*;

public abstract class AbstractTextCopyHandler extends AbstractTextHandler {

    @Override
    public void executeCommand(String selectedText) {
        String newText = executeCopyCommand(selectedText);
        if (isEmpty(newText)) {
            setTimedErrorMessage("Command '" + getCommandName() + "' didn't copy anything.");
        } else {
            setClipboard(newText);
        }
    }
    
    public abstract String executeCopyCommand(String selectedText);
}
