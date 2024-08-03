package org.example.others;

import java.util.*;
import java.util.stream.Collectors;

public class FindSecondLargest {

    public static int findValue(int[] array) {
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        Map<Integer, List<Integer>> greaterMap = new HashMap<>();

        while (list.size() > 1) {
            List<Integer> greater = new ArrayList<>();
            for (int i = 1; i < list.size(); i += 2) {
                Integer former = list.get(i - 1);
                Integer latter = list.get(i);

                if (former > latter) {
                    greater.add(former);

                    if (null == greaterMap.get(former)) {
                        greaterMap.put(former, new ArrayList<Integer>(){{add(latter);}});
                    } else {
                        greaterMap.get(former).add(latter);
                    }
                } else {
                    greater.add(latter);

                    if (null == greaterMap.get(latter)) {
                        greaterMap.put(latter, new ArrayList<Integer>(){{add(former);}});
                    } else {
                        greaterMap.get(latter).add(former);
                    }
                }
            }

            if (list.size() % 2 != 0) {
                greater.add(list.get(list.size() - 1));
            }

            list = greater;
        }

        Integer maxValue = list.get(0);
        List<Integer> eliminatedByMax = greaterMap.get(maxValue);

        int secondLargest = FindMax.findValue(eliminatedByMax.stream().mapToInt(value -> value).toArray());

        return secondLargest;
    }

}
