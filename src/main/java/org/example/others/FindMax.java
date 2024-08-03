package org.example.others;

public class FindMax {

    public static int find(int[] array) {
        int maxIndex = 0;
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }
        }

        return maxIndex;
    }

}
