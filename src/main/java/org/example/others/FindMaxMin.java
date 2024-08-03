package org.example.others;

import java.util.ArrayList;
import java.util.List;

public class FindMaxMin {

    public static int[] findValue(int[] array) {

        List<Integer> greater = new ArrayList<>();
        List<Integer> lesser = new ArrayList<>();

        for (int i = 1; i < array.length; i += 2) {
            if (array[i - 1] > array[i]) {
                greater.add(array[i - 1]);
                lesser.add(array[i]);
            } else {
                greater.add(array[i]);
                lesser.add(array[i - 1]);
            }
        }

        if (array.length % 2 != 0) {
            greater.add(array[array.length - 1]);
            lesser.add(array[array.length - 1]);
        }

        int maxValue = FindMax.findValue(greater.stream().mapToInt(value -> value).toArray());
        int minValue = FindMin.findValue(lesser.stream().mapToInt(value -> value).toArray());

        return new int[]{maxValue, minValue};
    }

}
