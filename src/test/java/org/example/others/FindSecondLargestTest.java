package org.example.others;

import org.example.DataGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


class FindSecondLargestTest {

    @Test
    void findValue() {
        int[] array = DataGenerator.generate(10, 100);
        System.out.println("the array");
        System.out.println(Arrays.toString(array));

        System.out.println("the second largest value");
        System.out.println(FindSecondLargest.findValue(array));
    }
}