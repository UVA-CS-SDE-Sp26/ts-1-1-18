import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TopSecretUITest {

    @Test
    public void testCommandLineLogic() {
        // Pass null so we don't use any other parts of the project
        TopSecretUI ui = new TopSecretUI(null);

        // Scenario 1: User runs 'java TopSecret'
        ui.run(new String[]{});
        assertEquals(0, ui.a, "Should hit branch 0 for no arguments");

        // Scenario 2: User runs 'java TopSecret 01'
        ui.run(new String[]{"01"});
        assertEquals(1, ui.a, "Should hit branch 1 for one argument");

        // Scenario 3: User runs 'java TopSecret 01 cipher.key'
        ui.run(new String[]{"01", "cipher.key"});
        assertEquals(2, ui.a, "Should hit branch 2 for two arguments");

        // Scenario 4: User runs with too many arguments
        ui.run(new String[]{"1", "2", "3"});
        assertEquals(3, ui.a, "Should hit branch 3 for too many arguments");
    }
}