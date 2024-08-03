package org.example.divide_and_conquer;

import org.example.DataGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class QuickSortTest {

    @Test
    void sort() {
        int[] array = DataGenerator.generate(10, 100);
        System.out.println("the original array");
        System.out.println(Arrays.toString(array));

        QuickSort.sort(array, 0, array.length - 1);
        System.out.println("the sorted array");
        System.out.println(Arrays.toString(array));
    }
}