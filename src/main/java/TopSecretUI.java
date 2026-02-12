public class TopSecretUI {
    private ProgramController controller;
    public int a; // Tracker for testing

    public TopSecretUI(ProgramController controller) {
        this.controller = controller;
    }

    public void run(String[] args) {
        if (args.length == 0) {
            if (controller != null) controller.listFiles();// call list files if there is no input from user so they get  alist of files
            // if there is no arguements lsit the files
            this.a = 0;
        } else if (args.length == 1) {
            if (controller != null) controller.displayFiles(args[0], null);// call display files with out a key file if they only input one
            //display the file with the default key
            this.a = 1;
        } else if (args.length == 2) {
            if (controller != null) controller.displayFiles(args[0], args[1]);//call display files with the new key and the file
            //display the file with the new key
            this.a = 2;
        } else {
            if (controller != null) controller.handleTooManyArgs();// do the handle too many if they put in over two arguemnts
            //if there is too many call and error and than print the usage instructions
            printUsage();
            this.a = 3;
        }
    }
    // added the instructions for how this works
    private void printUsage() {
        System.out.println("java TopSecret -> list available files");
        System.out.println("java TopSecret [num]  -> view file [num] and decipher using default key");
        System.out.println("java topsecret [num] [keyfile] -> view file [num] and decipher using [keyfile]");
    }
}