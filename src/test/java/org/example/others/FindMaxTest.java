package org.example.others;

import org.example.DataGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


class FindMaxTest {

    @Test
    void find() {
        int[] array = DataGenerator.generate(10, 100);
        System.out.println("the array");
        System.out.println(Arrays.toString(array));

        System.out.println("the max index");
        int maxIndex = FindMax.find(array);
        System.out.println(maxIndex);
        System.out.println("the max value");
        System.out.println(array[maxIndex]);
    }
}