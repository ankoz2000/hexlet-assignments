package exercise;

import java.util.Arrays;

// BEGIN
public class App {
    public static void main(String[] args) {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };
        enlargeArrayImage(image);
    }

    public static String[][] enlargeArrayImage(String[][] img) {
        int img_size = img.length;
        String[] horizontalEnlargedArray = Arrays.stream(img)
                .flatMap(Arrays::stream)
                .map(e -> {
                    String[] res = new String[2];
                    res[0] = e;
                    res[1] = e;
                    return res;
                })
                .flatMap(Arrays::stream)
                .toArray(String[]::new);
        String[][] result = new String[img_size*2][];
        int j = 0;
        int step = 0;
        while(j < img_size*2) {
            result[j] = Arrays.copyOfRange(horizontalEnlargedArray, step*img_size*2, (step+1)*img_size*2);
            result[j + 1] = Arrays.copyOfRange(horizontalEnlargedArray, step*img_size*2, (step+1)*img_size*2);
            j = j + 2;
            step += 1;
        }
        return result;
    }
}
// END
