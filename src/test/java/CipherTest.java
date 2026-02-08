import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class CipherTest {

    @TempDir
    Path tempDir;

    @Test
    void missingKeyFile_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cipher("does/not/exist/key.txt"));
    }

    @Test
    void keyFileWithOneLine_throwsException() throws Exception {
        Path key = tempDir.resolve("key.txt");
        Files.writeString(key, "abc\n");

        assertThrows(IllegalArgumentException.class,
                () -> new Cipher(key.toString()));
    }

    @Test
    void mismatchedLengths_throwsException() throws Exception {
        Path key = tempDir.resolve("key.txt");
        Files.writeString(key, "abcd\nxyz\n");

        assertThrows(IllegalArgumentException.class,
                () -> new Cipher(key.toString()));
    }

    @Test
    void duplicateCharactersInActual_throwsException() throws Exception {
        Path key = tempDir.resolve("key.txt");
        Files.writeString(key, "aabc\nwxyz\n");

        assertThrows(IllegalArgumentException.class,
                () -> new Cipher(key.toString()));
    }

    @Test
    void duplicateCharactersInCipher_throwsException() throws Exception {
        Path key = tempDir.resolve("key.txt");
        Files.writeString(key, "abcd\nwxxz\n");

        assertThrows(IllegalArgumentException.class,
                () -> new Cipher(key.toString()));
    }

    @Test
    void basicMapping_deciphersCorrectly() throws Exception {
        Path key = tempDir.resolve("key.txt");
        Files.writeString(key, "abc\nxyz\n");

        Cipher c = new Cipher(key.toString());
        assertEquals("a b c", c.decipher("x y z"));
    }

    @Test
    void unmappedCharactersRemainUnchanged() throws Exception {
        Path key = tempDir.resolve("key.txt");
        Files.writeString(key, "abc\nxyz\n");

        Cipher c = new Cipher(key.toString());
        assertEquals("a!2", c.decipher("x!2"));
    }

    @Test
    void emptyInput_returnsEmptyString() throws Exception {
        Path key = tempDir.resolve("key.txt");
        Files.writeString(key, "abc\nxyz\n");

        Cipher c = new Cipher(key.toString());
        assertEquals("", c.decipher(""));
    }

    @Test
    void nullInput_returnsNull() throws Exception {
        Path key = tempDir.resolve("key.txt");
        Files.writeString(key, "abc\nxyz\n");

        Cipher c = new Cipher(key.toString());
        assertNull(c.decipher(null));
    }
}
