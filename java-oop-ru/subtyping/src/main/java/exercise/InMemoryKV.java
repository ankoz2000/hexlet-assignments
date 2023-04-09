package exercise;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    private Map<String, String> dict;

    public InMemoryKV(Map<String, String> dict) {
        this.dict = dict;
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> map = toMap();
        if (dict.containsKey(key)) {
            map.replace(key, value);
        } else {
            map.put(key, value);
        }
        dict = Collections.unmodifiableMap(map);
    }

    @Override
    public void unset(String key) {
        Map<String, String> map = toMap();
        if (dict.containsKey(key)) {
            map.remove(key);
        }
        dict = Collections.unmodifiableMap(map);
    }

    @Override
    public String get(String key, String defaultValue) {
        if (dict.containsKey(key)) {
            return dict.get(key);
        }
        return defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(dict);
    }
}
// END
