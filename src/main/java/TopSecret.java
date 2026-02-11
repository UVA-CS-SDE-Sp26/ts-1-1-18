/**
 * Commmand Line Utility
 */
public class TopSecret {

    public static void main(String[] args) {
        Cipher cipher = new Cipher();
        FileHandler newFileHandler = new FileHandler(cipher, "data");
        ProgramController controller = new ProgramController(newFileHandler);
        new TopSecretUI(controller).run(args);
    }
}
