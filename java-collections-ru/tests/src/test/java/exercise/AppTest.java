package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> actual1 = App.take(new ArrayList<>(),2);
        List<Integer> actual2 = App.take(Arrays.asList(1, 2, 3, 4), 0);
        List<Integer> actual3 = App.take(Arrays.asList(1, 2, 3, 4), 2);
        
        assertThat(actual1).isEqualTo(new ArrayList<>());
        assertThat(actual2).isEqualTo(new ArrayList<>());
        assertThat(actual3).isEqualTo(Arrays.asList(1, 2));
        // END
    }
}
