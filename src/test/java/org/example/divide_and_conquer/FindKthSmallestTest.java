package org.example.divide_and_conquer;

import org.example.DataGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;


class FindKthSmallestTest {

    @Test
    void findValue() {
        int count = 11;
        int[] array = DataGenerator.generate(count, 100);
        System.out.println("the array");
        System.out.println(Arrays.toString(array));

        System.out.println("the sorted array");
        QuickSort.sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

        int Kth = new Random().nextInt(count) + 1;
        System.out.println("the Kth=" + Kth + " smallest value");
        int value = FindKthSmallest.findValue(array, Kth);
        System.out.println(value);

        assert value == array[Kth - 1];
    }

    @Test
    void multipleTest() {
        for (int i = 0; i < 100; i++) {
            findValue();
        }
    }

}