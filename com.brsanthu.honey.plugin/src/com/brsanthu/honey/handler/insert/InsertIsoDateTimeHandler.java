package com.brsanthu.honey.handler.insert;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;

public class InsertIsoDateTimeHandler extends AbstractTextReplaceHandler {

    @Override
    public TextChangeResponse executeReplaceCommand(String selectedText) {
        return new TextChangeResponse(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()));
    }
}
