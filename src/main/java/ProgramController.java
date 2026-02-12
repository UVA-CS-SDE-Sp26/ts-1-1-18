import java.io.*;
import java.util.*;
public class ProgramController{
    public FileHandler fileHandler;
    private String dataDirectory = "data";

    public ProgramController(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }
    //just print out the results of the listFiles method from the file handler
    public void listFiles() {
        String files = fileHandler.listFiles();
        System.out.println(files);
    }

    public void displayFiles(String input, String keyFile) {
        int index =0;
        //make sure the user is passing an int as their first argument
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            //if not prompt that they enter a valid index
            System.out.println("Please enter a number");
            return;
        }
        //The string of files will be split up and put into a splitFiles array of
        //strings

        String files = fileHandler.listFiles();
        String[] splitFiles = files.split("\n");

        //check for a valid file index
        if(index < 0 || index> splitFiles.length) {
            //prompt the user for a valid index and stop the program
            System.out.println("Invalid index");
            return;
        }
        //extract the file's number and the actual file name so you can
        //use the file name with the file handler
        String fileWithNumber = splitFiles[index - 1];
        String[] parts = fileWithNumber.split(" ", 2);
        String file = parts[1];

        //make sure the file isnt null
        if (file != null) {
            //make sure the key isnt null
            if (keyFile != null) {
                //make a new cipher with the provided cipher
                Cipher customCipher = new Cipher(keyFile);
                //create a new file handler to handle the file and pass in the cipher
                FileHandler customHandler = new FileHandler(customCipher, dataDirectory);
                //print out the results
                System.out.printf(customHandler.handleFile(file));
            } else {
                //if you have no custom cipher the file handler will default to the key
                System.out.println(fileHandler.handleFile(file));
            }
        }
    }
    public void handleTooManyArgs() {
        System.out.println("Error: Too many positional arguments.");
    }


}