package exercise;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class AppTest{
    @Test
    void simpleTest() {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };
        String[][] imageEnlarged = {
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        };
        assertThat(Arrays.deepToString(App.enlargeArrayImage(image))).isEqualTo(Arrays.deepToString(imageEnlarged));
    }

    @Test
    void simpleTest2() {
        String[][] image = {
                {"*", "-", "-", "*"},
                {"*", "-", "-", "*"},
                {"*", "-", "-", "*"},
                {"*", "-", "-", "*"},
        };
        String[][] imageEnlarged = {
                {"*", "*", "-", "-", "-", "-", "*", "*"},
                {"*", "*", "-", "-", "-", "-", "*", "*"},
                {"*", "*", "-", "-", "-", "-", "*", "*"},
                {"*", "*", "-", "-", "-", "-", "*", "*"},
                {"*", "*", "-", "-", "-", "-", "*", "*"},
                {"*", "*", "-", "-", "-", "-", "*", "*"},
                {"*", "*", "-", "-", "-", "-", "*", "*"},
                {"*", "*", "-", "-", "-", "-", "*", "*"},
        };
        assertThat(Arrays.deepToString(App.enlargeArrayImage(image))).isEqualTo(Arrays.deepToString(imageEnlarged));
    }

    @Test
    void simpleTest3() {
        String[][] image = {};
        String[][] imageEnlarged = {};
        assertThat(Arrays.deepToString(App.enlargeArrayImage(image))).isEqualTo(Arrays.deepToString(imageEnlarged));
    }
}
// END
