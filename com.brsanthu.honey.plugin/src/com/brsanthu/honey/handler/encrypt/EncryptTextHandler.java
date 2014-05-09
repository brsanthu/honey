package com.brsanthu.honey.handler.encrypt;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static com.brsanthu.eclipseutils.EclipseUtils.*;

import com.brsanthu.eclipseutils.widgets.UsernamePasswordDialog;
import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;
import com.brsanthu.honey.handler.TextChangeResponse.Mode;
import com.brsanthu.utils.Utils;

public class EncryptTextHandler extends AbstractTextReplaceHandler {
    private static final String INSTRUCTION = "Enter the password to encrypt the text. Please be aware that if you " +
    		"forget this password, you will not be able to decrypt the text";

    //Whoever maintains this code, don't change the salt. If you do, you will break
    //all the encrypted text that users would have encrypted.
    public static final String SALT = "342B15BFD381722C";
    
    @Override
    public TextChangeResponse executeReplaceCommand(String selectedText) {
        TextChangeResponse textChangeResponse = null;
        if (isEmpty(selectedText)) {
            selectedText = getEditor().getContents();
            textChangeResponse = new TextChangeResponse(Mode.REPLACE_ALL);
        } else {
            textChangeResponse = new TextChangeResponse(Mode.REPLACE_SELECTED);
        }
        
        String password = promptUserForPassword();
        if (isEmpty(password)) {
            return null;
        }

        try {
            textChangeResponse.setNewText(Utils.encrypt(password, SALT, selectedText));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return textChangeResponse;
    }
    
    public String promptUserForPassword() {
        UsernamePasswordDialog dialog = new UsernamePasswordDialog(getShell(), "Enter Password", INSTRUCTION, false, true);
        return isOk(dialog.open())?dialog.getPassword():null;
    }
}
