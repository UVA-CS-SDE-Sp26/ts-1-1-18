import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileHandlerTest {
    @Mock
    private Cipher cipher;

    private FileHandler fileHandler;

    // Create a file handler object before each test. Maintain independence.
    @BeforeEach
    void setUp() {
        fileHandler = new FileHandler(cipher, "src/test/resources/mockdata");
    }

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

    @Test
    void handleFile() {
        String filename = "filea.txt";
        String raw = "njttjpo 2: nffu bu epdl 8 bu 3211.";
        String deciphered = "mission 2: meet at dock 8 at 3211.";

        when(cipher.decipher(raw)).thenReturn(deciphered);

        String result = fileHandler.
                handleFile(filename);
        assertEquals(deciphered, result);
        verify(cipher).decipher(raw);
    }

    @Test
    void listFiles() {
        String result = fileHandler.listFiles();
        assertEquals("filea.txt\nfileb.txt\nfilec.txt\n", result);
    }

}
