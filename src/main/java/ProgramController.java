import java.io.*;
import java.util.*;
public class ProgramController{
    public FileHandler fileHandler;
    private String dataDirectory = "data";

    public ProgramController(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }
    public void listFiles() {
        String files = fileHandler.listFiles();
        String[] splitFiles = files.split("\n");

        for (int i = 0; i < splitFiles.length; i++) {
            System.out.printf("%02d %s\n", i + 1, splitFiles[i]);
        }
    }

    public void displayFiles(String input, String keyFile) {
        int index =0;
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number");
            return;
        }

        String files = fileHandler.listFiles();
        String[] splitFiles = files.split("\n");

        if(index < 0 || index> splitFiles.length) {
            System.out.println("Invalid index");
            return;
        }
        String file = splitFiles[index -1];

        if (file != null) {
            if (keyFile != null) {
                Cipher customCipher = new Cipher(keyFile);
                FileHandler customHandler = new FileHandler(customCipher, dataDirectory);
                System.out.printf(customHandler.handleFile(file));
            } else {
                System.out.println(fileHandler.handleFile(file));
            }
        }
    }
    public void handleTooManyArgs() {
        System.out.println("Error: Too many positional arguments.");
    }


}