package com.brsanthu.honey.handler.replace;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import org.apache.commons.lang3.text.WordUtils;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;

public class ConvertToSentenseCaseHandler extends AbstractTextReplaceHandler {

    @Override
    public TextChangeResponse executeReplaceCommand(String selectedText) {
        if (isEmpty(selectedText)) {
            return null;
        }
        
        return new TextChangeResponse(WordUtils.capitalize(selectedText));
    }
}
