import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgramControllerTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void listFiles() {
        FileHandler mockHandler = mock(FileHandler.class);
        when(mockHandler.listFiles()).thenReturn("file1.txt\nfile2.txt");

        ProgramController controller = new ProgramController(mockHandler);
        controller.listFiles();

        String output = outputStream.toString();

        assertTrue(output.contains("01 file1.txt"));
        assertTrue(output.contains("02 file2.txt"));
    }

    @Test
    void displayFiles_validIndex_noKey() {
        FileHandler mockHandler = mock(FileHandler.class);

        when(mockHandler.listFiles()).thenReturn("file1.txt\nfile2.txt");
        when(mockHandler.handleFile("file1.txt")).thenReturn("decoded content");

        ProgramController controller = new ProgramController(mockHandler);
        controller.displayFiles("1", null);

        String output = outputStream.toString();

        assertTrue(output.contains("decoded content"));
    }

    @Test
    void displayFiles_invalidInput() {
        FileHandler mockHandler = mock(FileHandler.class);
        when(mockHandler.listFiles()).thenReturn("file1.txt\nfile2.txt");

        ProgramController controller = new ProgramController(mockHandler);
        controller.displayFiles("abc", null);

        String output = outputStream.toString();

        assertTrue(output.contains("Please enter a number"));
    }

    @Test
    void displayFiles_invalidIndex() {
        FileHandler mockHandler = mock(FileHandler.class);
        when(mockHandler.listFiles()).thenReturn("file1.txt\nfile2.txt");

        ProgramController controller = new ProgramController(mockHandler);
        controller.displayFiles("5", null);

        String output = outputStream.toString();

        assertTrue(output.contains("Invalid index"));
    }

    @Test
    void handleTooManyArgs() {
        FileHandler mockHandler = mock(FileHandler.class);
        ProgramController controller = new ProgramController(mockHandler);

        controller.handleTooManyArgs();

        String output = outputStream.toString();
        assertTrue(output.contains("Too many positional arguments"));
    }
}
