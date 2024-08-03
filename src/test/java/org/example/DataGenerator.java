package org.example;

import java.util.Random;

public class DataGenerator {

    public static int[] generate(int count, int bound) {
        int[] array = new int[count];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(bound);
        }

        return array;
    }

}
