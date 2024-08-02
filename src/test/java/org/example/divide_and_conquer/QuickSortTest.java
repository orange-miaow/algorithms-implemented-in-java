package org.example.divide_and_conquer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

class QuickSortTest {

    @Test
    void sort() {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(100);
        }
        System.out.println("the original array");
        System.out.println(Arrays.toString(array));

        QuickSort.sort(array, 0, array.length - 1);
        System.out.println("the sorted array");
        System.out.println(Arrays.toString(array));
    }
}