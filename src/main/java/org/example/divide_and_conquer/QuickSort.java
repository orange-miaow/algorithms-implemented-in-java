package org.example.divide_and_conquer;

public class QuickSort {

    public static void sort(int[] array, int begin, int end) {
        if (begin > end) {
            return;
        }

        int position = partition(array, begin, end);
        int temp = array[position];
        array[position] = array[begin];
        array[begin] = temp;


        sort(array, begin, position - 1);
        sort(array, position + 1, end);
    }

    private static int partition(int[] array, int begin, int end) {
        int x = array[begin];
        int j = end;
        int i = begin + 1;

        while (true) {
            while ((j >= begin) && (array[j] > x)) {
                j--;
            }

            while ((i <= end) && (array[i] <= x)) {
                i++;
            }

            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            } else {
                return j;
            }
        }
    }

}
