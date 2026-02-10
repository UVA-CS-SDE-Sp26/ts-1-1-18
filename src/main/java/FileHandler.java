import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class FileHandler {
    private final Cipher cipher;
    private String result;
    private List<String> files;
    private String dataDirectory;

    public FileHandler(Cipher cipher, String dataDirectory) {
        this.cipher = cipher;
        this.result = null;
        this.dataDirectory = dataDirectory;
        this.files = new ArrayList<>();
        loadAvailableFiles();
    }

    // Used to save files in data/ to this.files
    private void loadAvailableFiles() {
        this.files = Arrays.asList(Objects.requireNonNull(Paths.get(dataDirectory).toFile().list()));
    }

    // In the case where a file name is not provided
    public String listFiles() {
        return files.stream().map(file -> (file + "\n")).reduce("", String::concat);
    }

    // In the case where a filename is provided
    public String handleFile(String filename) {
        // Get the file path
        Path filePath = Paths.get(dataDirectory, filename);

        // Check if file exists and is a regular file
        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            return "File not found: " + filename;
        }

        try {
            String fileContentCiphered = Files.readString(filePath);
            // Call the Cipher class decipher method to get back the deciphered text
            result = cipher.decipher(fileContentCiphered);
            return result;
        } catch (Exception e) { // If there's issues reading the file
            return "Could not read file: " + filename;
        }
    }
}
