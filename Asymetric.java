import javax.crypto.Cipher;
import java.security.*;

public class Asymetric {
    private static Cipher cipher;
    private static KeyPair keyPair;

    public static void main(String[] args) throws Exception {
        // Generate key pair for asymmetric encryption
        generateKeyPair();

        // Encrypt and decrypt a message
        String message = "Hello, this is a secret message!";
        byte[] encryptedMessage = encryptMessage(message);
        String decryptedMessage = decryptMessage(encryptedMessage);

        System.out.println("Original Message: " + message);
        System.out.println("Encrypted Message: " + bytesToHex(encryptedMessage));
        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    // Generate key pair
    public static void generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // RSA key size
        keyPair = keyPairGenerator.generateKeyPair();
    }

    // Encrypt a message using public key
    public static byte[] encryptMessage(String message) throws Exception {
        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        return cipher.doFinal(message.getBytes());
    }

    // Decrypt a message using private key
    public static String decryptMessage(byte[] encryptedMessage) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        byte[] decryptedBytes = cipher.doFinal(encryptedMessage);
        return new String(decryptedBytes);
    }

    // Helper method to convert byte array to hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
