package com.brsanthu.honey.handler.insert;
import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;

import static com.brsanthu.eclipseutils.EclipseUtils.*;

public class InsertCustomDateHandler extends AbstractTextReplaceHandler {

    @Override
    public TextChangeResponse executeReplaceCommand(String selectedText) {
        InsertCustomDateDialog dateDialog = new InsertCustomDateDialog(getShell());
        if(isOk(dateDialog.open())) {
            return new TextChangeResponse((String) dateDialog.getResult());
        }
        
        return null;
    }
}
