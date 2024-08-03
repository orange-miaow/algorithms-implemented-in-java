package org.example.others;

import org.example.DataGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


class FindMaxMinTest {

    @Test
    void findValue() {
        int[] array = DataGenerator.generate(10, 100);
        System.out.println("the array");
        System.out.println(Arrays.toString(array));

        int[] maxmin = FindMaxMin.findValue(array);
        System.out.println("the max value");
        System.out.println(maxmin[0]);
        System.out.println("the min value");
        System.out.println(maxmin[1]);
    }
}