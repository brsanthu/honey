package com.brsanthu.honey.handler.insert;
import java.util.Date;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;

public class InsertUnixTimeHandler extends AbstractTextReplaceHandler {

    @Override
    public TextChangeResponse executeReplaceCommand(String selectedText) {
        return new TextChangeResponse("" + new Date().getTime());
    }
}