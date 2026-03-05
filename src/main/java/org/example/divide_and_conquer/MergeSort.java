package org.example.divide_and_conquer;

public class MergeSort {

    public static void sort(int[] array, int begin, int end) {
        if (begin >= end) {
            return;
        }

        int mid = (begin + end) / 2;
        sort(array, begin, mid);
        sort(array, mid + 1, end);
        merge(array, begin, mid, end);
    }

    private static void merge(int[] array, int begin, int mid, int end) {
        int i = begin;
        int j = mid + 1;
        int k = 0;
        int[] tempArray = new int[end - begin + 1];

        while ((i <= mid) && (j <= end)) {
            if (array[i] <= array[j]) {
                tempArray[k++] = array[i++];
            } else {
                tempArray[k++] = array[j++];
            }
        }

        while (i <= mid) {
            tempArray[k++] = array[i++];
        }

        while (j <= end) {
            tempArray[k++] = array[j++];
        }

        for (int l = begin; l <= end; l++) {
            array[l] = tempArray[l - begin];
        }
    }

}
