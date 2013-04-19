package com.brsanthu.honey.handler.insert;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;

public class InsertIsoDateHandler extends AbstractTextReplaceHandler {

    @Override
    public TextChangeResponse executeReplaceCommand(String selectedText) {
        return new TextChangeResponse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }
}
