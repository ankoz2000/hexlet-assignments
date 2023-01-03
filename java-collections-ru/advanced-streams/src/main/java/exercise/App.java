package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {
    public static String getForwardedVariables(String config) {
        return Arrays.stream(config.split("\n"))
                .filter(s -> s.startsWith("environment=\""))
                .map(s -> s.replaceAll("environment=\"", ""))
                .map(s -> s.replaceAll("\"", ""))
                .map(String::trim)
                .flatMap(s -> Arrays.stream(s.split(",")))
                .filter(s -> s.startsWith("X_FORWARDED_"))
                .map(v -> v.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}
//END
