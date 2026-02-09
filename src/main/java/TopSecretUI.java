public class TopSecretUI {

    private ProgramController controller;


    public TopSecretUI(ProgramController controller) {
        this.controller = controller;
    }

    //from psuedocode
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

    //if there are no arguements list the files (run the program from the filehandler one but maybe needs to change)
    private void handleNoArgs() {
        controller.listFiles();
    }

    //this is for one argument if it is right display the file and if not throw an error
    private void handleOneArg(String index) {
        try {
            int parsedIndex = Integer.parseInt(index);
            controller.displayFile(parsedIndex, null);
        } catch (NumberFormatException e) {
            System.out.println("Error: '" + index + "' is not a valid file number");
        }
    }

    //if two arguments validate the integer and find the key or throw error
    private void handleTwoArgs(String index, String keyFile) {
        try {
            int parsedIndex = Integer.parseInt(index);
            controller.displayFile(parsedIndex, keyFile);
        } catch (NumberFormatException e) {
            System.out.println("Error: '" + index + "' is not a valid file number");
        }
    }

   //if the user enters more than two arguments throw error
    private void handleTooManyArgs() {
        System.out.println("Error: Too many positional arguments.");
        printUsage();
    }

    //instructions on how program works
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
