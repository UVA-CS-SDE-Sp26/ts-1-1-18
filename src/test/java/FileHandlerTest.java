import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FileHandlerTest {
    private Cipher cipher;

    private FileHandler fileHandler;

    // Create a file handler object before each test
    // This helps to maintain independence between tests
    @BeforeEach
    void setUp() {
        // Use mockito to mock the cipher class
        cipher = Mockito.mock(Cipher.class);
        fileHandler = new FileHandler(cipher, "src/test/resources/mockdata");
    }

    // Test the ability to load files into the files field
    @Test
    void loadAvailableFiles() throws NoSuchFieldException, IllegalAccessException {
        Field filesField = FileHandler.class.getDeclaredField("files");
        filesField.setAccessible(true);
        @SuppressWarnings("unchecked")
        List<String> files = (List<String>) filesField.get(fileHandler);

        assertNotNull(files);
        assertEquals(3, files.size());
        assertTrue(files.contains("filea.txt"));
        assertTrue(files.contains("fileb.txt"));
        assertTrue(files.contains("filec.txt"));
    }

    // Test the ability to handle a file by returning the deciphered text
    @Test
    void handleFile() {
        String filename = "filea.txt";
        String deciphered = "mission 2: meet at dock 8 at 3211.";

        // Mock the cipher decipher method to return the deciphered text when called. anyString() would refer to the ciphered text.
        when(cipher.decipher(anyString())).thenReturn(deciphered);

        String result = fileHandler.handleFile(filename);
        assertEquals(deciphered, result);

        verify(cipher).decipher(anyString());
    }

    // Test the ability to list all available files after having loaded them into the files field
    @Test
    void listFiles() {
        String result = fileHandler.listFiles();
        assertEquals("01 filea.txt\n02 fileb.txt\n03 filec.txt\n", result);
    }

    @Test
    void handleFileReturnsNotFoundForMissingFile() {
        String result = fileHandler.handleFile("missing-file.txt");
        assertEquals("File not found: missing-file.txt", result);
    }

    @Test
    void handleFileReturnsReadErrorWhenDecipherFails() {
        String filename = "filea.txt";
        when(cipher.decipher(anyString())).thenThrow(new RuntimeException("decipher failed"));

        String result = fileHandler.handleFile(filename);
        assertEquals("Could not read file: " + filename, result);
    }

    @Test
    void constructorThrowsWhenDataDirectoryIsInvalid() {
        assertThrows(
                NullPointerException.class,
                () -> new FileHandler(cipher, "src/test/resources/does-not-exist")
        );
    }

    @Test
    void handleFileReturnsErrorWhenNullFileGiven() {
        assertThrows(NullPointerException.class, () -> fileHandler.handleFile(null));
    }

}
