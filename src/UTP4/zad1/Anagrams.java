/**
 *
 *  @author Osiak Marek S24671
 *
 */

package UTP4.zad1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {
    private final Map<String, List<String>> anagrams;

    public Anagrams(String allWords) throws IOException {
        anagrams = new HashMap<>();
        Files.lines(Paths.get(allWords))
                .map(line -> line.split("\\s+"))
                .flatMap(Arrays::stream)
                .forEach(word -> {
                    String sortedWord = sortWord(word);
                    anagrams.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
                });
    }

    private String sortWord(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public List<List<String>> getSortedByAnQty() {
        return anagrams.values().stream()
                .sorted(Comparator.comparing((List<String> list) -> -list.size())
                        .thenComparing(list -> list.get(0)))
                .collect(Collectors.toList());
    }

    public String getAnagramsFor(String word) {
        List<String> anagramList = anagrams.get(sortWord(word));
        if (anagramList == null) {
            return word + ": null";
        }
        List<String> filteredAnagramList = anagramList.stream()
                .filter(anagram -> !anagram.equals(word))
                .collect(Collectors.toList());
        return word + ": " + filteredAnagramList;
    }
}