package com.brsanthu.honey.handler.delete;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;

public class TrimTrailingSpacesHandler extends AbstractTextReplaceHandler {

    @Override
    public TextChangeResponse executeReplaceCommand(String selectedText) {
        if (isEmpty(selectedText)) {
            return null;
        }
        
        StringBuilder sb = null;
        String[] lines = selectedText.split("\n");
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].replaceAll("\\s+$", "");
            
            if (sb == null) {
                sb = new StringBuilder();
            } else {
                sb.append("\n");
            }
            sb.append(lines[i]);
        }
        
        return new TextChangeResponse(sb==null?"":sb.toString());
    }
}
