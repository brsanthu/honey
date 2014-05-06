package com.brsanthu.honey.handler.copy;
import static com.brsanthu.eclipseutils.EclipseUtils.*;
import com.brsanthu.honey.handler.AbstractTextCopyHandler;

public class CopyAppendHandler extends AbstractTextCopyHandler {

    @Override
    public String executeCopyCommand(String selectedText) {
        if (selectedText == null) {
            return null;
        }
        
        return getClipboardText() + selectedText;
    }
}
