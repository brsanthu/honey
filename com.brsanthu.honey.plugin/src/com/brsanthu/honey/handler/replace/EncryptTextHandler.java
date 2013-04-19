package com.brsanthu.honey.handler.replace;
import static com.brsanthu.eclipse.common.ui.util.EclipseUiUtils.isOk;
import static com.brsanthu.eclipse.common.ui.util.EclipseUiUtils.openQuestion;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import com.brsanthu.eclipse.common.ui.dialog.UsernamePasswordDialog;
import com.brsanthu.honey.handler.AbstractTextReplaceHandler;
import com.brsanthu.honey.handler.TextChangeResponse;
import com.brsanthu.honey.handler.TextChangeResponse.Mode;

public class EncryptTextHandler extends AbstractTextReplaceHandler {
    private static final String INSTRUCTION = "Enter the password to encrypt the text. Please be aware that if you " +
    		"forget this password, you will not be able to decrypt the text";

    @Override
    public TextChangeResponse executeReplaceCommand(String selectedText) {
        TextChangeResponse textChangeResponse = null;
        if (isEmpty(selectedText)) {
            if (openQuestion("Encrypt whole file?", "No text is selected. Do you want to encrypt whole file?")) {
                selectedText = getEditor().getContents();
                textChangeResponse = new TextChangeResponse(Mode.REPLACE_ALL);
            } else {
                return null;
            }
        } else {
            textChangeResponse = new TextChangeResponse(Mode.REPLACE_SELECTED);
        }
        
        String password = promptUserForPassword();
        if (isEmpty(password)) {
            return null;
        }

        try {
            AESEncrypter encryptor = new AESEncrypter(password);
            textChangeResponse.setNewText(encryptor.encrypt(selectedText));
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
