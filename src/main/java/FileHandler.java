// This java.nio.file.* import allows us to operate with files. We can get files, check their status, and read them
import java.nio.file.*;
import java.util.*;

public class FileHandler {
    private final Cipher cipher;
    private String result;
    private List<String> files;
    private String dataDirectory;

    // File handler constructor
    public FileHandler(Cipher cipher, String dataDirectory) {
        this.cipher = cipher;
        this.result = null;
        this.dataDirectory = dataDirectory;
        this.files = new ArrayList<>();

        // We load all the files inside the constructor so that the files field is already populated
        loadAvailableFiles();
    }

    // Used to save files in data/ to this.files
    private void loadAvailableFiles() {
        // List the files in the data directory and save them to `discovered` array
        String[] discovered = Objects.requireNonNull(Paths.get(dataDirectory).toFile().list());
        // Sort alphabetically so that 01 gets associated with filea.txt, 02 gets associated with fileb.txt, etc.
        Arrays.sort(discovered);
        // Use a list version of discovered to save to this.files
        this.files = Arrays.asList(discovered);
    }

    // In the case where a file name is not provided
    public String listFiles() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < files.size(); i++) {
            // Format file list as "01 filea.txt\n02 fileb.txt\n..."
            out.append(String.format("%02d %s%n", i + 1, files.get(i)));
        }
        // Return a String type, not a StringBuilder
        return out.toString();
    }

    public List<String> getAvailableFiles() {
        return new ArrayList<>(files);
    }

    // In the case where a file name is provided
    public String handleFile(String filename) {
        // Get the file path
        Path filePath = Paths.get(dataDirectory, filename);

        // Error case handling: check if file exists and is regular
        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            return "File not found: " + filename;
        }

        try {
            String fileContentCiphered = Files.readString(filePath);
            // Call the cipher decipher method to get back the deciphered text
            result = cipher.decipher(fileContentCiphered);
            return result;
        } catch (Exception e) { // If there are issues reading the file
            return "Could not read file: " + filename;
        }
    }
}
