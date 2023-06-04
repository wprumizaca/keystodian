package com.keystodian.apikeys.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordUtils {
    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "asdfgfer23errdfw3223rffdrdf4edr6fgfdf"; // Clave secreta para encriptación/desencriptación
    private static final String SALT = "your-salt";

    public static String encryptPassword(String password) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 128);
            SecretKeySpec secretKeySpec = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String decryptPassword(String encryptedPassword) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 128);
            SecretKeySpec secretKeySpec = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String password = "myPassword123";

        // Encriptar la contraseña
        String encryptedPassword = encryptPassword(password);
        System.out.println("Contraseña encriptada: " + encryptedPassword);

        // Desencriptar la contraseña
        String decryptedPassword = decryptPassword(encryptedPassword);
        System.out.println("Contraseña desencriptada: " + decryptedPassword);
    }

}
