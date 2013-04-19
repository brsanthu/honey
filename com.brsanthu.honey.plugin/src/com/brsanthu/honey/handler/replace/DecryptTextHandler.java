package com.brsanthu.honey.handler.replace;
import static com.brsanthu.eclipse.common.ui.util.EclipseUiUtils.isOk;
import static com.brsanthu.eclipse.common.ui.util.EclipseUiUtils.openQuestion;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import com.brsanthu.eclipse.common.ui.dialog.UsernamePasswordDialog;
import com.brsanthu.honey.handler.TextChangeResponse;
import com.brsanthu.honey.handler.TextChangeResponse.Mode;

/**
 * Code in this class is from http://stackoverflow.com/questions/992019/java-256-bit-aes-password-based-encryption.
 * 
 * @author Santhosh Kumar
 */
public class DecryptTextHandler extends EncryptTextHandler {
    private static final String INSTRUCTION = "Enter the password to decrypt the text. " +
    		"You must enter the same password you encrypted this text with.";

    @Override
    public TextChangeResponse executeReplaceCommand(String selectedText) {
        TextChangeResponse textChangeResponse = null;
        if (isEmpty(selectedText)) {
            if (openQuestion("Decrypt whole file?", "No text is selected. Do you want to decrypt whole file?")) {
                selectedText = getEditor().getContents();
                textChangeResponse = new TextChangeResponse(Mode.REPLACE_ALL);
            } else {
                return null;
            }
        } else {
            textChangeResponse = new TextChangeResponse(Mode.REPLACE_SELECTED);
        }
        
        Boolean passwordError = false;
        while(true) {
            String password = promptUserForPassword(passwordError);
            if (isEmpty(password)) {
                return null;
            }
               
            try {
                AESEncrypter encryptor = new AESEncrypter(password);
                textChangeResponse.setNewText(encryptor.decrypt(selectedText));
                break;
            } catch (Exception e) {
                passwordError = true;
            }
        }
        return textChangeResponse;
    }
    
    public String promptUserForPassword(Boolean passwordError) {
        UsernamePasswordDialog dialog = new UsernamePasswordDialog(getShell(), "Enter Password", INSTRUCTION, false, false);
        if (passwordError) {
            dialog.setErrorMessage("Couldn't decrypt the text using specified password. " +
            		"Please make sure you have selected only but all encrypted text and try again with correct password");
        }
        return isOk(dialog.open())?dialog.getPassword():null;
    }
}
