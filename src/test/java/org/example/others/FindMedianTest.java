package org.example.others;

import org.example.DataGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


class FindMedianTest {

    @Test
    void findMedianValue() {
        int[] array = DataGenerator.generate(5, 100);
        System.out.println("the array");
        System.out.println(Arrays.toString(array));

        System.out.println("the median value");
        System.out.println(FindMedian.findValue(array));
    }
}