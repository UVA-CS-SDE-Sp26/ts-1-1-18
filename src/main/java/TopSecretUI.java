public class TopSecretUI {
    private ProgramController controller;
    public int a; // Tracker for testing logic branches

    public TopSecretUI(ProgramController controller) {
        this.controller = controller;
    }

    public void run(String[] args) {
        if (args.length == 0) {
            // Branch 0: No Arguments
            if (controller != null) controller.listFiles();
            this.a = 0;
        } else if (args.length == 1) {
            // Branch 1: One Argument (e.g., "01")
            if (controller != null) controller.displayFiles(args[0], null);
            this.a = 1;
        } else if (args.length == 2) {
            // Branch 2: Two Arguments (e.g., "01", "cipher.key")
            if (controller != null) controller.displayFiles(args[0], args[1]);
            this.a = 2;
        } else {
            // Branch 3: Error/Too Many
            if (controller != null) controller.handleTooManyArgs();
            printUsage();
            this.a = 3;
        }
    }

    private void printUsage() {
        System.out.println("Usage instructions displayed...");
    }
}