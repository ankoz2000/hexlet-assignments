package exercise;

// BEGIN
public class MaxThread extends Thread {

    int[] arr;
    int max;

    public MaxThread(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
    }
}
// END
