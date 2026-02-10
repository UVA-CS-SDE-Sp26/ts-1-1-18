import java.io.*;
import java.util.*;

public class ProgramController {

    public void listFiles() {
        //create a new file object called docs directing to the docs folder
        File docs = new File("docs");
        int i = 1;
        for (final File fileEntry : docs.listFiles()) {
            //print out the file number and the file name
            System.out.println(i + " " + fileEntry.getName());

            i++;
        }
    }

    public void displayFile(int index, String keyFile) {
        //use the file handler to create a ist of files
        List<String> files = fileHandler.listFiles();

        if (index < 1 || index > files.size()) {
            //if the file number given is invalid say so
            System.out.println("Invalid file number.");
            return;
        }

        String fileName = files.get(index - 1);
        String contents = fileHandler.getFileContents(fileName);

        Cipher cipher;

        if (keyFile == null) {
            //if there is no key provided use the cypher
            cipher = new Cipher();
        } else {
            //otherwise you should be deciphering using the keyfile provided
            cipher = new Cipher("ciphers/" + keyFile);
        }

        String readable = cipher.decipher(contents);

        System.out.println(readable);
    }

}