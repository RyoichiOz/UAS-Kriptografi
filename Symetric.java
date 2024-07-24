import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Symetric {
    private static Cipher cipher;
    private static SecretKey secretKey;

    public static void main(String[] args) throws Exception {

        generateSymmetricKey();

        // Encrypt and decrypt a message
        String message = "Hello, this is a secret message!";
        byte[] encryptedMessage = encryptMessage(message);
        String decryptedMessage = decryptMessage(encryptedMessage);

        System.out.println("Original Message: " + message);
        System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(encryptedMessage));
        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    // Generate a symmetric key
    public static void generateSymmetricKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // AES key size
        secretKey = keyGenerator.generateKey();
    }

    // Encrypt a message using symmetric key
    public static byte[] encryptMessage(String message) throws Exception {
        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(message.getBytes());
    }

    // Decrypt a message using symmetric key
    public static String decryptMessage(byte[] encryptedMessage) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedMessage);
        return new String(decryptedBytes);
    }
}
