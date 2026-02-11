import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TopSecretTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void noArguments_doesNotThrowException() {
        TopSecret ui = new TopSecret(new ProgramController());

        assertDoesNotThrow(() -> ui.run(new String[]{}));
    }

    @Test
    void oneNumericArgument_doesNotThrowException() {
        TopSecret ui = new TopSecret(new ProgramController());

        assertDoesNotThrow(() -> ui.run(new String[]{"1"}));
    }

    @Test
    void twoArguments_doesNotThrowException() {
        TopSecret ui = new TopSecret(new ProgramController());

        assertDoesNotThrow(() -> ui.run(new String[]{"1", "key.txt"}));
    }

    @Test
    void nonNumericIndex_printsErrorMessage() {
        TopSecret ui = new TopSecret(new ProgramController());

        ui.run(new String[]{"abc"});

        String output = outContent.toString();
        assertTrue(output.contains("Error: 'abc' is not a valid file number"));
    }

    @Test
    void tooManyArguments_printsUsage() {
        TopSecret ui = new TopSecret(new ProgramController());

        ui.run(new String[]{"1", "key.txt", "extra"});

        String output = outContent.toString();
        assertTrue(output.contains("Usage:"));
        assertTrue(output.contains("java topsecret"));
    }
}
