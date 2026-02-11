import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TopSecretTest {
    private MockController mock;
    private TopSecret ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    // This Mock class completely blocks the real ProgramController's logic
    class MockController extends ProgramController {
        public boolean listCalled = false;
        public int indexPassed = -1;
        public String keyPassed = null;

        @Override public void listFiles() { this.listCalled = true; }
        @Override public void displayFile(int i, String k) { this.indexPassed = i; this.keyPassed = k; }
    }

    @BeforeEach
    public void setUp() {
        mock = new MockController();
        ui = new TopSecret(mock);
        System.setOut(new PrintStream(outContent)); // Start listening to console
    }

    @Test
    public void testTwoArgsSuccess() {
        ui.run(new String[]{"2", "mykey.txt"});
        assertEquals(2, mock.indexPassed);
        assertEquals("mykey.txt", mock.keyPassed);
    }

    @Test
    public void testInvalidNumberError() {
        ui.run(new String[]{"notANumber"});
        // Verifies the error message was printed
        assertTrue(outContent.toString().contains("is not a valid file number"));
        // Verifies the controller was NOT called
        assertEquals(-1, mock.indexPassed);
    }

    @Test
    public void testTooManyArgsError() {
        ui.run(new String[]{"1", "2", "3"});
        assertTrue(outContent.toString().contains("Too many positional arguments"));
    }

    @Test
    public void testNoArgsSuccess() {
        ui.run(new String[]{});
        assertTrue(mock.listCalled);
    }
}