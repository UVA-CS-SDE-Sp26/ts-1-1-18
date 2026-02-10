import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class FileHandler {
    private final Cipher cipher;
    private String result;
    private List<String> files;
    private static final String DATA_DIRECTORY = "data";

    public FileHandler(Cipher cipher) {
        this.cipher = cipher;
        this.result = null;
        this.files = new ArrayList<>();
        loadAvailableFiles();
    }

    private void loadAvailableFiles() {

    }

    // In the case where a filename is provided
    public void handleFile(String filename) {

    }

    // In the case where a file name is not provided
    public String listFiles() {
        return "";
    }

    public String getResult() {
        return "";
    }

    private String readFileAsString(String filename) throws Exception {
        return "";
    }
}