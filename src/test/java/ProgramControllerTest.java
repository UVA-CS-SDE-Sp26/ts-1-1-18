import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgramControllerTest {

    private ProgramController controller;
    private FileHandler mockFileHandler;

    @BeforeEach
    void setUp() {
        mockFileHandler = mock(FileHandler.class);
        controller = new ProgramController(mockFileHandler);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void listFilesTest() {
        //make sure the files are returning properly
        //They should be in the form
        //1 file1.txt
        //2 file2.txt
        when(mockFileHandler.listFiles()).thenReturn("1 file1.txt\n2 file2.txt");
        controller.listFiles();
        verify(mockFileHandler).listFiles();
    }

    @Test
    void displayFilesValidIndex() {
        //make sure the files are being handled correctly when there are multiple files
        when(mockFileHandler.listFiles()).thenReturn("1 file1.txt\n2 file2.txt");
        when(mockFileHandler.handleFile("file1.txt")).thenReturn("File Content");
        controller.displayFiles("1", null);

        verify(mockFileHandler).handleFile("file1.txt");
    }

    @Test
    void displayFilesInvalidIndex() {
        //Make sure an error is being thrown if there are no files
        when(mockFileHandler.listFiles()).thenReturn("1 file1.txt");

        controller.displayFiles("5", null);
        verify(mockFileHandler, never()).handleFile(anyString());
    }

    @Test
    void displayFilesNonIntIndex() {
        //if you provide the display files with not a number it should send an error
        controller.displayFiles("abc", null);
        verify(mockFileHandler, never()).handleFile(anyString());
    }

    @Test
    void handleTooManyArgs() {
        controller.handleTooManyArgs();
    }
}
