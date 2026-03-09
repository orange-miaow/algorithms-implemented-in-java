package org.example.others;

import org.example.DataGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class BubbleSortTest {

    @Test
    void sort() {
        int[] array = DataGenerator.generate(10, 100);
        System.out.println("the original array");
        System.out.println(Arrays.toString(array));

        BubbleSort.sort(array);
        System.out.println("the sorted array");
        System.out.println(Arrays.toString(array));
    }

}