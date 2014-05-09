package com.brsanthu.honey.handler.encrypt;
import static com.brsanthu.eclipseutils.EclipseUtils.isOk;
import static com.brsanthu.eclipseutils.EclipseUtils.openQuestion;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import com.brsanthu.eclipseutils.widgets.UsernamePasswordDialog;
import com.brsanthu.honey.handler.TextChangeResponse;
import com.brsanthu.honey.handler.TextChangeResponse.Mode;
import com.brsanthu.utils.AesEncrypter;
import com.brsanthu.utils.Utils;

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
            selectedText = getEditor().getContents();
            textChangeResponse = new TextChangeResponse(Mode.REPLACE_ALL);
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
                textChangeResponse.setNewText(Utils.decrypt(password, EncryptTextHandler.SALT, selectedText));
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
