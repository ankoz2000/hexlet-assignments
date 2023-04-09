package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void save(Path path, Car car) throws IOException {
        Files.writeString(path, car.serialize(), StandardOpenOption.WRITE);
    }

    public static Car extract(Path path) throws IOException {
        return Car.unserialize(String.join("\n", Files.readAllLines(path)));
    }
}
// END
