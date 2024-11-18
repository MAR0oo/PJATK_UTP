package UTP4.zad3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {
    private final Map<String, Set<String>> langsMap = new HashMap<>();
    private final Map<String, Set<String>> progsMap = new HashMap<>();

    public ProgLang(String fileName) {
        try {
            Files.lines(Paths.get(fileName))
                    .forEach(line -> {
                        String[] data = line.split("\t");
                        String lang = data[0];
                        Set<String> progs = langsMap.computeIfAbsent(lang, k -> new HashSet<>());
                        for (int i = 1; i < data.length; i++) {
                            String prog = data[i];
                            progs.add(prog);
                            progsMap.computeIfAbsent(prog, k -> new HashSet<>()).add(lang);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }

    public Map<String, Set<String>> getLangsMap() {
        return langsMap;
    }

    public Map<String, Set<String>> getProgsMap() {
        return progsMap;
    }

    public Map<String, Set<String>> getLangsMapSortedByNumOfProgs() {
        return sorted(langsMap, Comparator.comparing((Map.Entry<String, Set<String>> e) -> e.getValue().size()).reversed()
                .thenComparing(Map.Entry::getKey));
    }

    public Map<String, Set<String>> getProgsMapSortedByNumOfLangs() {
        return sorted(progsMap, Comparator.comparing((Map.Entry<String, Set<String>> e) -> e.getValue().size()).reversed()
                .thenComparing(Map.Entry::getKey));
    }

    public Map<String, Set<String>> getProgsMapForNumOfLangsGreaterThan(int n) {
        return filtered(progsMap, e -> e.getValue().size() > n);
    }

    public static <K, V> Map<K, V> sorted(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        return map.entrySet().stream()
                .sorted(comparator)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static <K, V> Map<K, V> filtered(Map<K, V> map, Predicate<Map.Entry<K, V>> predicate) {
        return map.entrySet().stream()
                .filter(predicate)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}

