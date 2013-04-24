package com.brsanthu.honey.handler.replace;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;

public class SwapQuotesHandler extends AbstractTextReplaceHandler {

    @Override
    public TextChangeResponse executeReplaceCommand(String selectedText) {
        if (isEmpty(selectedText)) {
            return null;
        }

        StringBuilder buf = new StringBuilder();
        for (char c : selectedText.toCharArray()) {
            switch (c) {
                case '\'':
                    c = '"';
                    break;
                case '"':
                    c = '\'';
                    break;
            }
            buf.append(c);
        }
        return new TextChangeResponse(buf.toString());
    }
}
