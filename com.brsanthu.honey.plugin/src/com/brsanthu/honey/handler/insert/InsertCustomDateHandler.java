package com.brsanthu.honey.handler.insert;
import static com.brsanthu.eclipse.common.ui.util.EclipseUiUtils.isOk;

import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;

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
