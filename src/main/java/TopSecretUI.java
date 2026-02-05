public class TopSecretUI {

    private ProgramController controller;

    public TopSecretUI(ProgramController controller) {
        this.controller = controller;
    }

    /**
     * Entry point for CLI logic.
     * Decides which command to run based on argument count.
     */
    public void run(String[] args) {
        if (args.length == 0) {
            handleNoArgs();
        } else if (args.length == 1) {
            handleOneArg(args[0]);
        } else if (args.length == 2) {
            handleTwoArgs(args[0], args[1]);
        } else {
            handleTooManyArgs();
        }
    }

    private void handleNoArgs() {
        // TODO: request file list from ProgramControl
        // controller.listFiles();
    }

    private void handleOneArg(String index) {
        // TODO: validate index
        // TODO: request file using default cipher
        // controller.displayFile(parsedIndex, null);
    }

    private void handleTwoArgs(String index, String keyFile) {
        // TODO: validate index
        // TODO: request file using alternate cipher
        // controller.displayFile(parsedIndex, keyFile);
    }

    private void handleTooManyArgs() {
        System.out.println("Error: Too many positional arguments.");
        printUsage();
    }

    private void printUsage() {
        System.out.println("Usage:");
        System.out.println("java topsecret");
        System.out.println("java topsecret [num]");
        System.out.println("java topsecret [num] [keyfile]");
    }

    public static void main(String[] args) {
        ProgramController controller = new ProgramController();
        new TopSecretUI(controller).run(args);
    }
}

