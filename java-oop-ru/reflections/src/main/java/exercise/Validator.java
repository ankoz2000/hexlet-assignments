package exercise;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        return Arrays.stream(address.getClass().getDeclaredFields())
                .toList()
                .stream()
                .filter(f -> f.getDeclaredAnnotation(NotNull.class) != null)
                .filter(f -> {
                    try {
                        f.setAccessible(true);
                        return f.get(address) == null;
                    } catch (IllegalAccessException e) {
                        System.out.println("no such field");
                    }
                    return false;
                })
                .map(Field::getName)
                .collect(Collectors.toList());
    }
}
// END
