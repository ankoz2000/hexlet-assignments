package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] arr) {
        MinThread min = new MinThread(arr);
        MaxThread max = new MaxThread(arr);

        min.start();
        max.start();

        try {
            min.join();
            max.join();
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
        int minVal = min.min;
        int maxVal = max.max;

        Map<String, Integer> result = new HashMap<>();
        result.put("min", minVal);
        result.put("max", maxVal);

        return result;
    }
    // END
}
