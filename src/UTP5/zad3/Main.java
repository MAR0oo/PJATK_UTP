/**
 *
 *  @author Osiak Marek S24671
 *
 */

package UTP5.zad3;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
      String url = "http://wiki.puzzlers.org/pub/wordlists/unixdict.txt";
      String localFilename = "unixdict.txt";
      try {
          downloadFile(url, localFilename);
      } catch (IOException e) {
          throw new RuntimeException(e);
      }

      Map<String, List<String>> anagrams = null;
      try {
          anagrams = Files.lines(Paths.get(localFilename), StandardCharsets.UTF_8)
                  .collect(Collectors.groupingBy(Main::sortLetters));
      } catch (IOException e) {
          throw new RuntimeException(e);
      }

      Optional<List<String>> maxAnagrams = anagrams.values().stream()
              .max(Comparator.comparingInt(List::size));

      maxAnagrams.ifPresent(strings -> strings.forEach(System.out::println));
  }

    private static String sortLetters(String word) {
        char[] letters = word.toCharArray();
        Arrays.sort(letters);
        return new String(letters);
    }

    private static void downloadFile(String url, String localFilename) throws IOException {
        try (InputStream in = new URL(url).openStream()) {
            Files.copy(in, Paths.get(localFilename), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}

