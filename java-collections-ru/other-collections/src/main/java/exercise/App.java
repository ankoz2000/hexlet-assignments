package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static Map genDiff(Map<String, Object> f1, Map<String, Object> f2) {
        Map<String, String> result;

        result = f1.entrySet().stream()
                .filter(e -> !f2.containsKey(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> "deleted"));

        result.putAll(f2.entrySet().stream()
                .filter(e -> !f1.containsKey(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> "added")));

        result.putAll(f1.entrySet().stream()
                .filter(e -> f2.containsKey(e.getKey()))
                .filter(e -> f2.get(e.getKey()).equals(e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> "unchanged")));

        result.putAll(f1.entrySet().stream()
                .filter(e -> f2.containsKey(e.getKey()))
                .filter(e -> !f2.get(e.getKey()).equals(e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> "changed")));

        return result;
    }
}
//END
