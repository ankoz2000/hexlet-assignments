package exercise;

import jdk.jshell.execution.Util;

import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private final String filePath;
    private Map<String, String> dict;

    public FileKV(String filePath, Map<String, String> dict) {
        this.filePath = filePath;
        this.dict = dict;
        Utils.writeFile(filePath, Utils.serialize(dict));
    }

    @Override
    public void set(String key, String value) {
        dict = Utils.unserialize(Utils.readFile(filePath));
        if (dict.containsKey(key)) {
            dict.replace(key, value);
        } else {
            dict.put(key, value);
        }
        Utils.writeFile(filePath, Utils.serialize(dict));
    }

    @Override
    public void unset(String key) {
        dict = Utils.unserialize(Utils.readFile(filePath));
        dict.remove(key);
        Utils.writeFile(filePath, Utils.serialize(dict));
    }

    @Override
    public String get(String key, String defaultValue) {
        dict = Utils.unserialize(Utils.readFile(filePath));
        if (dict.containsKey(key)) {
            return dict.get(key);
        }
        return defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
        return Utils.unserialize(Utils.readFile(filePath));
    }
}
// END
