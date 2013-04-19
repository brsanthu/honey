package com.brsanthu.honey.handler.replace;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;

public class ConvertToLowerCaseHandler extends AbstractTextReplaceHandler {

    @Override
    public TextChangeResponse executeReplaceCommand(String selectedText) {
        if (isEmpty(selectedText)) {
            return null;
        }
        
        return new TextChangeResponse(selectedText.toLowerCase());
    }
}
