package com.brsanthu.honey.handler.replace;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.replace;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;

public class ConvertXmlSafeToGtLtHandler extends AbstractTextReplaceHandler {

    @Override
    public TextChangeResponse executeReplaceCommand(String selectedText) {
        if (isEmpty(selectedText)) {
            return null;
        }
        selectedText = replace(selectedText, "&lt;", "<");
        selectedText = replace(selectedText, "&gt", ">");
        
        return new TextChangeResponse(selectedText);
    }
}
