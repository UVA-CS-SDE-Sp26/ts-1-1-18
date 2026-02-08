import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cipher {
    private final Map<Character, Character> map = new HashMap<>();

    public Cipher() {
        this("ciphers/key.txt");
    }

    public Cipher(String keyPath) {
        List<String> lines;

        //ensure key file exists and is readable
        try {
            lines = Files.readAllLines(Path.of(keyPath));
        } catch (Exception e) {
            throw new IllegalArgumentException("Please try again! Cipher key file not found or unreadable: " + keyPath);
        }

        //ensure only two lines
        if (lines.size() != 2) {
            throw new IllegalArgumentException("Please try again! Cipher key file must contain exactly two lines.");
        }

        //set actual line
        String actual = lines.get(0);
        //set cipher line
        String cipher = lines.get(1);

        //ensure each line exists
        if (actual.isEmpty() || cipher.isEmpty()) {
            throw new IllegalArgumentException("Please try again! Cipher key lines cannot be empty.");
        }

        //ensure easy character has a ciphered version
        if (actual.length() != cipher.length()) {
            throw new IllegalArgumentException("Please try again! Cipher key lines must be the same length.");
        }

        //ensure there is no duplicte in actual or ciphered line
        if (hasDuplicates(actual) || hasDuplicates(cipher)) {
            throw new IllegalArgumentException("Please try again! Cipher key lines cannot contain duplicate characters.");
        }

        for (int i = 0; i < cipher.length(); i++) {
            //cipher --> map cipher to actual
            map.put(cipher.charAt(i), actual.charAt(i));
        }
    }

    public String decipher(String cipherText) {

        //if text to decipher is empty, exit
        if (cipherText == null) return null;

        StringBuilder out = new StringBuilder();

        //map out each character from cipher to actual
        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.charAt(i);
            if (map.containsKey(ch)) out.append(map.get(ch));
            else out.append(ch);
        }
        return out.toString();
    }

    //check for duplicates
    private boolean hasDuplicates(String s) {
        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (seen.contains(c)) return true;
            seen.add(c);
        }
        return false;
    }
}
