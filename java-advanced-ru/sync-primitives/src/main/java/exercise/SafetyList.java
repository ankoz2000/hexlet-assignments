package exercise;

import java.util.NoSuchElementException;

class SafetyList {
    // BEGIN
    final int INITIAL_CAPACITY = 32;
    int size;
    int[] arr;

    public SafetyList() {
        size = 0;
        arr = new int[INITIAL_CAPACITY];
    }

    public int getSize() {
        return size;
    }

    public synchronized void add(int i) {
        if (getSize() == arr.length) {
            int[] tempArr = new int[arr.length];
            System.arraycopy(arr, 0, tempArr, 0, getSize());
            arr = new int[arr.length * 2];
            System.arraycopy(tempArr, 0, arr, 0, getSize());
        }
        arr[size++] = i;
    }

    public int get(int index) {
        if (index > getSize()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < getSize(); i++) {
            if (i == index) {
                return arr[i];
            }
        }
        throw new NoSuchElementException();
    }
    // END
}
