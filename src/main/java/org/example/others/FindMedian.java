package org.example.others;

public class FindMedian {

    public static int findValue(int[] array) {
        int m = (array.length % 2 == 0) ? (array.length / 2 - 1) : (array.length / 2);

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }

            if (i == m) {
                break;
            }
        }

        return array[m];
    }

}
