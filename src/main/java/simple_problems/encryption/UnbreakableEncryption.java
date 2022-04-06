package simple_problems.encryption;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.Random;

public class UnbreakableEncryption<T> {
    private Type dataType;

    private static byte[] randomKey(int length) {
        byte[] dummy = new byte[length];
        Random random = new Random();
        random.nextBytes(dummy);
        return dummy;
    }

    private static String decryptString(KeyPair keyPair) {
        byte[] decrypted = new byte[keyPair.key1.length];
        for (int i = 0; i < decrypted.length; i++) {
            decrypted[i] = (byte) (keyPair.key1[i] ^ keyPair.key2[i]);
        }
        return new String(decrypted);
    }
    public String decrypt(KeyPair keyPair) {
        if (dataType.equals(String.class)) return decryptString(keyPair);
        else if (dataType.equals(File.class)) {
            decryptFile(keyPair);
            return "";
        }
        return null;
    }

    private void decryptFile(KeyPair keyPair) {
        byte[] decrypted = new byte[keyPair.key1.length];
        for (int i = 0; i < decrypted.length; i++) {
            decrypted[i] = (byte) (keyPair.key1[i] ^ keyPair.key2[i]);
        }
        try {
            FileOutputStream out = new FileOutputStream("decrypted-pic.jpg");
            out.write(decrypted);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KeyPair encrypt(T original) throws IOException {
        byte[] originalBytes;
        if (original instanceof String) {
            originalBytes = ((String) original).getBytes();
            dataType = String.class;
        } else if (original instanceof File) {
            originalBytes = Files.readAllBytes(((File) original).toPath());
            dataType = File.class;
        } else {
            originalBytes = original.toString().getBytes();
            dataType = String.class;
        }
        byte[] dummyKey = randomKey(originalBytes.length);
        byte[] encryptedKey = new byte[originalBytes.length];
        for (int i = 0; i < originalBytes.length; i++) {
            encryptedKey[i] = (byte) (originalBytes[i] ^ dummyKey[i]);
        }
        return new KeyPair(dummyKey, encryptedKey);
    }

    public record KeyPair(byte[] key1, byte[] key2) {
    }
}
