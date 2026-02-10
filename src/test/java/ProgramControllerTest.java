import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.logging.FileHandler;

import static org.junit.jupiter.api.Assertions.*;

class ProgramControllerTest {

    class MockFileHandler extends FileHandler {

        @Override
        public List<String> listFiles() {
            return Arrays.asList("file1.txt", "file2.txt");
        }

        @Override
        public String getFileContents(String filename) {
            return "abc";  // pretend ciphered text
        }
    }

    @Test
    void testListFiles() {
        ProgramController controller =
                new ProgramController(new MockFileHandler());

        assertDoesNotThrow(() -> controller.listFiles());
    }

    @Test
    void testValidIndex() {
        ProgramController controller =
                new ProgramController(new MockFileHandler());

        assertDoesNotThrow(() ->
                controller.displayFile(1, null));
    }

    @Test
    void testInvalidIndexHandler() {
        ProgramController controller =
                new ProgramController(new MockFileHandler());

        assertDoesNotThrow(() ->
                controller.displayFile(99, null));
    }

    @Test
    void testInvalidKeyHandler() {
        ProgramController controller =
                new ProgramController(new MockFileHandler());

        assertDoesNotThrow(() ->
                controller.displayFile(1, "badkey.txt"));
    }
}