package exercise;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String src1, String src2, String dest) throws NoSuchFileException {
        Path pathToSrc1 = Paths.get(src1);
        Path pathToSrc2 = Paths.get(src2);
        Path pathToDest = Paths.get(dest);
        if (Files.exists(pathToSrc1) && Files.exists(pathToSrc2)) {
            CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
                try {
                    return Files.readAllLines(pathToSrc1).stream()
                            .collect(Collectors.joining());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return "";
            }).exceptionally((ex) -> {
                        System.out.println("Ex: " + ex.getMessage());
                        return null;
            });
            CompletableFuture<String> second = CompletableFuture.supplyAsync(() -> {
                try {
                    return Files.readAllLines(pathToSrc2).stream()
                            .collect(Collectors.joining());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return "";
            }).exceptionally((ex) -> {
                System.out.println("Ex: " + ex.getMessage());
                return null;
            });

            CompletableFuture<String> result = first.thenCombine(second, (f, s) -> {
                String res = f + s;
                try {
                    Files.write(pathToDest, res.getBytes(), StandardOpenOption.CREATE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return res;
            });

            return result;
        } else {
            throw new NoSuchFileException("NoSuchFileException");
        }
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        unionFiles("src/resources/file1.txt", "src/resources/file2.txt", "src/resources/file3.txt")
                .exceptionally(throwable -> {
                    System.out.println(throwable.getMessage());
                    return null;
                });
        // END
    }
}

