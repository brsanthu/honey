package com.brsanthu.honey.handler.replace;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.replace;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.google.common.base.Splitter;

/**
 * Code in this class is from http://stackoverflow.com/questions/992019/java-256-bit-aes-password-based-encryption.
 * 
 * @author Santhosh Kumar
 */
public class AESEncrypter {

    private static final byte[] SALT = {
        (byte) 0x1d, (byte) 0x9e, (byte) 0xab, (byte) 0x55,
        (byte) 0xa3, (byte) 0x56, (byte) 0x23, (byte) 0x03
    };
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 128;
    private Cipher ecipher;
    private Cipher dcipher;
    private byte[] ivs;
    private SecretKey secret;
    
    public AESEncrypter(String passPhrase) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(passPhrase.toCharArray(), SALT, ITERATION_COUNT, KEY_LENGTH);
        SecretKey tmp = factory.generateSecret(spec);
        secret = new SecretKeySpec(tmp.getEncoded(), "AES");

        ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        ecipher.init(Cipher.ENCRYPT_MODE, secret);
        ivs = ecipher.getParameters().getParameterSpec(IvParameterSpec.class).getIV();
        
        dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    }

    public String encrypt(String encrypt) throws Exception {
        byte[] bytes = encrypt.getBytes("UTF-8");
        byte[] encrypted = encrypt(bytes);
        
        String encryptedString = DatatypeConverter.printHexBinary(encrypted);
        String ivString = DatatypeConverter.printHexBinary(ivs);
        
        String finalEncryptedString = join(Splitter.fixedLength(100).split(ivString+":"+encryptedString), "\n");
        
        return finalEncryptedString;
    }

    public byte[] encrypt(byte[] plain) throws Exception {
        return ecipher.doFinal(plain);
    }

    public String decrypt(String finalEncryptedString) throws Exception {
        //Split the IV and Encrypted string.
        finalEncryptedString = replace(finalEncryptedString, "\n", "").trim();
        String ivString = substringBefore(finalEncryptedString, ":");
        String encryptedString = substringAfter(finalEncryptedString, ":");
        
        byte[] ivBytes = DatatypeConverter.parseHexBinary(ivString);
        byte[] encryptedBytes = DatatypeConverter.parseHexBinary(encryptedString);
        dcipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes));
        byte[] decrypted = decrypt(encryptedBytes);
        return new String(decrypted, "UTF-8");
    }

    public byte[] decrypt(byte[] encrypt) throws Exception {
        return dcipher.doFinal(encrypt);
    }

    public static void main(String[] args) throws Exception {

        String message = "msg";
        String password = "test";

        AESEncrypter encrypter = new AESEncrypter(password);
        String encrypted = encrypter.encrypt(message);
        
        encrypter = new AESEncrypter(password);
        String decrypted = encrypter.decrypt(encrypted);

        System.out.println("Encrypt(\"" + message + "\", \"" + password + "\") = \"" + encrypted + "\"");
        System.out.println("Decrypt(\"" + encrypted + "\", \"" + password + "\") = \"" + decrypted + "\"");
    }
}