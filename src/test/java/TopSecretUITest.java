import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TopSecretUITest {

    @Test
    public void testNoArguments() {
        TopSecretUI ui = new TopSecretUI(null);

        ui.run(new String[]{});
        assertEquals(0, ui.a, "Should hit branch 0 for no arguments");
    }

    @Test
    public void testOneArgument() {
        TopSecretUI ui = new TopSecretUI(null);

        ui.run(new String[]{"01"});
        assertEquals(1, ui.a, "Should hit branch 1 for one argument");
    }

    @Test
    public void testTwoArguments() {
        TopSecretUI ui = new TopSecretUI(null);

        ui.run(new String[]{"01", "cipher.key"});
        assertEquals(2, ui.a, "Should hit branch 2 for two arguments");
    }

    @Test
    public void testTooManyArguments() {
        TopSecretUI ui = new TopSecretUI(null);

        ui.run(new String[]{"1", "2", "3"});
        assertEquals(3, ui.a, "Should hit branch 3 for too many arguments");
    }

    @Test
    public void testMoreThanThreeArguments() {
        TopSecretUI ui = new TopSecretUI(null);

        ui.run(new String[]{"1", "2", "3", "4"});
        assertEquals(3, ui.a, "Should still hit branch 3 for excessive arguments");
    }
}
