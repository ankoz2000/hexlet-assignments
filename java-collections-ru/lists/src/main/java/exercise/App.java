package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static boolean scrabble(String symbols, String word) {
        if (symbols.length() >= word.length()) {
            String lowerCaseSymbols = symbols.toLowerCase();
            String lowerCaseWord = word.toLowerCase();

            char[] symCharArr = lowerCaseSymbols.toCharArray();
            char[] wordCharArr = lowerCaseWord.toCharArray();

            int counter = 0;

            List<Integer> usedIndexes = new ArrayList<>();
            for (int i = 0; i < word.length(); i++) {
                for (int j = 0; j < symbols.length(); j ++) {
                    if (symCharArr[j] == wordCharArr[i] && !usedIndexes.contains(j)) {
                        counter += 1;
                        usedIndexes.add(j);
                        break;
                    }
                }
            }
            return counter == word.length();
        }
        return false;
    }
}
//END
