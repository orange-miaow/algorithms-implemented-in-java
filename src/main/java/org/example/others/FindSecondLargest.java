package org.example.others;

import java.util.*;
import java.util.stream.Collectors;

public class FindSecondLargest {

    /**
     * 锦标赛树法查找次大值
     * <p>
     * 算法思想
     * 1. 构建单淘汰锦标赛树
     * - 元素两两分组比较,胜者(较大值)晋级
     * - 记录每个胜者直接击败的对手(greaterMap)
     * - 奇数个元素时,最后元素直接晋级
     * 2. 全局最大值(冠军)在树顶
     * 3. 次大值必在冠军直接击败的对手中
     * <p>
     * 时间复杂度: O(n + log n)
     * 比较次数: ≈ n + log₂n
     * <p>
     * 边界情况:
     * - n=2: 1次比较确定最大值,次大值为被淘汰值
     * - 所有元素相等: 返回任意元素(等于最大值)
     *
     * @param array 输入数组 (长度≥2)
     * @return 次大值
     */
    public static int findValueByTournament(int[] array) {
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

    /**
     * 分组比较法查找次大值
     * <p>
     * 算法思想
     * 1. 相邻元素两两分组 (0-1, 2-3,...),奇数长度时末元素暂不分组
     * - 每组较大值→greater列表,较小值→lesser列表
     * 2. 在greater中找最大值(maxOfGreater)及其索引
     * 3. 获取该最大值在原始组中的对手(lesserOfMax)
     * 4. 从greater中移除该最大值
     * 5. 分情况处理：
     * a) 若greater为空 (原数组只有一组):
     * - 偶数长度: 次大值=lesserOfMax
     * - 奇数长度: 次大值=max(末元素, lesserOfMax) 或 maxOfGreater (当末元素>maxOfGreater时)
     * b) 若greater非空:
     * - 在剩余greater中找最大值(secondLargestOfGreater)
     * - 偶数长度: 次大值=max(lesserOfMax, secondLargestOfGreater)
     * - 奇数长度: 次大值=max(末元素, lesserOfMax, secondLargestOfGreater) 或 maxOfGreater (当末元素>maxOfGreater时)
     * <p>
     * 时间复杂度: O(n)
     * 比较次数: ≈ 3n/2
     *
     * @param array 输入数组 (长度≥2)
     * @return 次大值
     */
    public static int findValueByGrouping(int[] array) {
        List<Integer> greater = new ArrayList<>();
        List<Integer> lesser = new ArrayList<>();

        for (int i = 1; i < array.length; i += 2) {
            if (array[i] > array[i - 1]) {
                greater.add(array[i]);
                lesser.add(array[i - 1]);
            } else {
                greater.add(array[i - 1]);
                lesser.add(array[i]);
            }
        }

        int maxIndexOfGreater = FindMax.findIndex(greater.stream().mapToInt(Integer::intValue).toArray());
        int maxOfGreater = greater.get(maxIndexOfGreater);
        int lesserOfMax = lesser.get(maxIndexOfGreater);

        greater.remove(maxIndexOfGreater);
        if (greater.isEmpty()) {
            if (array.length % 2 == 0) {
                return lesserOfMax;
            } else {
                if (array[array.length - 1] > maxOfGreater) {
                    return maxOfGreater;
                }

                return Math.max(array[array.length - 1], lesserOfMax);
            }
        } else {
            int secondLargestOfGreater = FindMax.findValue(greater.stream().mapToInt(Integer::intValue).toArray());

            if (array.length % 2 == 0) {
                return Math.max(lesserOfMax, secondLargestOfGreater);
            } else {
                if (array[array.length - 1] > maxOfGreater) {
                    return maxOfGreater;
                }

                return Math.max(array[array.length - 1], Math.max(lesserOfMax, secondLargestOfGreater));
            }
        }
    }

}
