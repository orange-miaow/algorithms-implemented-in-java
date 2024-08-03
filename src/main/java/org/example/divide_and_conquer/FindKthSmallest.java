package org.example.divide_and_conquer;

import org.example.others.FindMedian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindKthSmallest {

    public static int findValue(int[] S, int k) {
        if (S.length == 1) {
            return S[0];
        }

        // 1.将S划分成5个一组 共ceil(n/5)个组
        List<int[]> arrCollection = new ArrayList<>();
        for (int i = 4; i < S.length; i += 5) {
            int[] arr = Arrays.copyOfRange(S, i - 4, i + 1);
            arrCollection.add(arr);
        }
        if (S.length % 5 != 0) {
            int[] arr = Arrays.copyOfRange(S, S.length / 5 * 5, S.length);
            arrCollection.add(arr);
        }

        // 2.每组找一个中位数 把这些中位数放到集合M中
        int[] M = new int[arrCollection.size()];
        for (int i = 0; i < arrCollection.size(); i++) {
            int medianValue = FindMedian.findValue(arrCollection.get(i));
            M[i] = medianValue;
        }

        // 3.m <-findValue(M, ceil(|M|/2)) 选M的中位数m
        int m = findValue(M, (int) Math.ceil(M.length / 2.0));
        // 将S中的数划分成A,B,C,D 4个集合
        List<Integer>[] ABCD = partition(arrCollection, m);

        // 4.把A和D中的每个元素与m比较 小的构成S1 大的构成S2
        List<Integer>[] S1S2 = s1s2(ABCD[0], ABCD[3], m);
        List<Integer> S1 = S1S2[0];
        List<Integer> S2 = S1S2[1];

        // 5. S1 <- S1 U C; S2 <- S2 U B
        S1.addAll(ABCD[2]);
        S2.addAll(ABCD[1]);

        // 6.7.8.9
        if (k == S1.size() + 1) {
            return m;
        } else if (k <= S1.size()) {
            return findValue(S1.stream().mapToInt(value -> value).toArray(), k);
        } else {
            return findValue(S2.stream().mapToInt(value -> value).toArray(), k - S1.size() - 1);
        }
    }

    private static List<Integer>[] partition(List<int[]> arrCollection, int m) {
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        List<Integer> D = new ArrayList<>();

        int CArrCount = 0;
        int BArrCount = 0;

        List<Integer> indexs = new ArrayList<>();
        for (int j = 0; j < arrCollection.size(); j++) {
            int[] arr = arrCollection.get(j);
            int arrMedianIndex = (int) Math.ceil(arr.length / 2.0) - 1;

            if (arr[arrMedianIndex] < m) {
                CArrCount++;
                for (int i = 0; i <= arrMedianIndex; i++) {
                    C.add(arr[i]);
                }
                for (int i = arrMedianIndex + 1; i < arr.length; i++) {
                    A.add(arr[i]);
                }
            } else if (arr[arrMedianIndex] > m) {
                BArrCount++;
                for (int i = 0; i < arrMedianIndex; i++) {
                    D.add(arr[i]);
                }
                for (int i = arrMedianIndex; i < arr.length; i++) {
                    B.add(arr[i]);
                }
            } else {
                indexs.add(j);
            }
        }

        for (Integer index : indexs) {
            int[] arr = arrCollection.get(index);
            int arrMedianIndex = (int) Math.ceil(arr.length / 2.0) - 1;

            if (CArrCount < ((int) Math.ceil(arrCollection.size() / 2.0) - 1)) {
                CArrCount++;
                for (int i = 0; i <= arrMedianIndex; i++) {
                    C.add(arr[i]);
                }
                for (int i = arrMedianIndex + 1; i < arr.length; i++) {
                    A.add(arr[i]);
                }
            } else if (BArrCount < (arrCollection.size() / 2)) {
                BArrCount++;
                for (int i = 0; i < arrMedianIndex; i++) {
                    D.add(arr[i]);
                }
                for (int i = arrMedianIndex; i < arr.length; i++) {
                    B.add(arr[i]);
                }
            } else {
                for (int i = 0; i < arrMedianIndex; i++) {
                    C.add(arr[i]);
                }
                for (int i = arrMedianIndex + 1; i < arr.length; i++) {
                    B.add(arr[i]);
                }
            }
        }

        return new List[]{A, B, C, D};
    }

    private static List<Integer>[] s1s2(List<Integer> A, List<Integer> D, int m) {
        List<Integer> S1 = new ArrayList<>();
        List<Integer> S2 = new ArrayList<>();

        for (Integer number : A) {
            if (number < m) {
                S1.add(number);
            } else {
                S2.add(number);
            }
        }

        for (Integer number : D) {
            if (number < m) {
                S1.add(number);
            } else {
                S2.add(number);
            }
        }

        return new List[]{S1, S2};
    }

}
