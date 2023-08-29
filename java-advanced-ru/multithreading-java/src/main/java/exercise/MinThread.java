package exercise;

// BEGIN
public class MinThread extends Thread {

    int[] arr;
    int min;

    public MinThread(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
    }
}
// END
