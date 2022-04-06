package simple_problems.encryption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnbreakableEncryptionTest {
    private static String path;
    FileInputStream fis;
    Properties property = new Properties();

    @BeforeEach
    void setProperties() {
        try {
            fis = new FileInputStream("src/test/resources/test.properties");
            property.load(fis);
            path = property.getProperty("file.path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void encryptString() {
        String original = "Test string.";
        UnbreakableEncryption<String> unbreakableEncryption = new UnbreakableEncryption<>();
        UnbreakableEncryption.KeyPair encryptResult = null;
        try {
            encryptResult = unbreakableEncryption.encrypt(original);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(original, unbreakableEncryption.decrypt(Objects.requireNonNull(encryptResult)));
    }

    @Test
    void encryptImage() {
        UnbreakableEncryption<File> unbreakableEncryption = new UnbreakableEncryption<>();
        UnbreakableEncryption.KeyPair encryptResult = null;
        try {
            encryptResult = unbreakableEncryption.encrypt(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        unbreakableEncryption.decrypt(encryptResult);
    }
}