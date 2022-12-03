package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> result = new HashMap<>();
        if (sentence.length() > 0) {
            for (String word : sentence.split("\s")) {
                System.out.println(word);
                if (!result.containsKey(word)) {
                    result.put(word, 1);
                } else {
                    result.put(word, result.get(word) + 1);
                }
            }
        }
        return result;
    }

    public static String toString(Map<String, Integer> dict) {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<String, Integer> entry : dict.entrySet()) {
            sb.append("\n")
                    .append("\s\s")
                    .append(entry.getKey())
                    .append(":\s")
                    .append(entry.getValue());
        }
        if (!dict.isEmpty()) {
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
//END
