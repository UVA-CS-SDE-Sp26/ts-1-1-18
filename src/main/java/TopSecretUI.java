public class TopSecretUI {

    private ProgramController controller;

    public TopSecretUI(ProgramController controller) {
        this.controller = controller;
    }

    //from psuedocode
    public void run(String[] args) {
        if (args.length == 0) {
            controller.listFiles();
        } else if (args.length == 1) {
            controller.displayFiles(args[0], null);
        } else if (args.length == 2) {
            controller.displayFiles(args[0], args[1]);
        } else {
            controller.handleTooManyArgs();
            printUsage();
        }
    }


    //instructions on how program works
    private void printUsage() {
        System.out.println("Usage:");
        System.out.println("java topsecret");
        System.out.println("java topsecret [num]");
        System.out.println("java topsecret [num] [keyfile]");
    }


    public static void main(String[] args) {
        Cipher cipher = new Cipher();
        FileHandler newFileHandler = new FileHandler(cipher, "data");
        ProgramController controller = new ProgramController(newFileHandler);
        new TopSecretUI(controller).run(args);
    }
}
