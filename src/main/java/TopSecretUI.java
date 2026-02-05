public class TopSecretUI {

    private ProgramController controller;
// this is from the member C
    public TopSecretUI(ProgramController controller) {
        this.controller = controller;
    }

    //decides what program to run based on the amoutn of arguemtns
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
        // request file list from ProgramControl -- member c
        // controller.listFiles();
    }

    private void handleOneArg(String index) {
        //  validate index -- make sure index is a valid file associated or throw error
        //  request file using default cipher -- decifer
        // controller.displayFile(parsedIndex, null); -- use method from person D to get the file and decode
    }

    private void handleTwoArgs(String index, String keyFile) {
        //  validate index - make sure index is a valid file associated or throw error
        //  request file using alternate cipher 
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

    public static void main(String[] args) { //this just runs the program
        ProgramController controller = new ProgramController();
        new TopSecretUI(controller).run(args);
    }
}

