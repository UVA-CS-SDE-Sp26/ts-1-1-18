/**
 * Commmand Line Utility
 */
public class TopSecret {

    public static void main(String[] args) {
        ProgramController controller = new ProgramController();
        new TopSecretUI(controller).run(args);
    }

}
