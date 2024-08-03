package org.example.others;

public class FindMin {

    public static int findIndex(int[] array) {
        int minIndex = 0;
        int min = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    public static int findValue(int[] array) {
        return array[findIndex(array)];
    }

}
