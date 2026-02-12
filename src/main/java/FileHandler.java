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
        String[] discovered = Objects.requireNonNull(Paths.get(dataDirectory).toFile().list());
        Arrays.sort(discovered);
        this.files = Arrays.asList(discovered);
    }

    // In the case where a file name is not provided
    public String listFiles() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < files.size(); i++) {
            out.append(String.format("%02d %s%n", i + 1, files.get(i)));
        }
        return out.toString();
    }

    public List<String> getAvailableFiles() {
        return new ArrayList<>(files);
    }

    // In the case where a file name is provided
    public String handleFile(String filename) {
        // Get the file path
        Path filePath = Paths.get(dataDirectory, filename);

        // Check if file exists and is a regular file
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
