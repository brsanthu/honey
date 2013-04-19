package com.brsanthu.honey.handler.insert;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;

public class InsertLongDateHandler extends AbstractTextReplaceHandler {

    @Override
    public TextChangeResponse executeReplaceCommand(String selectedText) {
        return new TextChangeResponse(new SimpleDateFormat("EEEEE, MMMM dd, yyyy").format(new Date()));
    }
}