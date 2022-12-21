package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> data) {
        List<Map<String, String>> searchResult = new ArrayList<>();
        for (Map<String, String> book : books) {
            Integer counter = 0;
            for (Entry entry : book.entrySet()) {
                for (Entry search : data.entrySet()) {
                    if (entry.getKey().equals(search.getKey())
                            && entry.getValue().equals(search.getValue())) {
                        counter += 1;
//                        searchResult.add(Map.of((String) entry.getKey(), (String) entry.getValue()));
                    }
                }
            }
            if (counter.equals(data.size())) {
                searchResult.add(book);
            }
        }
        return searchResult;
    }
}
//END
