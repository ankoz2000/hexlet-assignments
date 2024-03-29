package exercise;

import java.util.Map;
import java.util.Map.Entry;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> tempMap = storage.toMap();
        for (Entry<String, String> entry : tempMap.entrySet()) {
            storage.unset(entry.getKey());
            storage.set(entry.getValue(), entry.getKey());
        }
    }
}
// END
