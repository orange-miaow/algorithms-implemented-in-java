package org.example.greedy_approach;

import java.util.*;

public class Schedule {

    /**
     * 求具有最小最大延迟的调度f
     * 一个调度f的最大延迟是所有客户延迟时间的最大值
     * @param n
     * 客户的个数
     * @param t
     * [i] 客户i的服务时长
     * @param d
     * [i] 客户i的希望的完成时间
     */
    public static Customer[] schedule(int n, int[] t, int[] d) {
        Customer[] customers = new Customer[n + 1];
        for (int i = 0; i <= n; i++) {
            customers[i] = new Customer(i, t[i], d[i]);
        }

        // 排序使得d1<=d2<=...<=dn
        Arrays.sort(customers, (c1, c2) -> {
            if (c1.d < c2.d) {
                return -1;
            } else if (c1.d > c2.d) {
                return 1;
            }
            return 0;
        });

        int maxDelay = 0;
        for (int j = 1; j <= n; j++) {
            customers[j].start = customers[j - 1].start + customers[j - 1].t;
            customers[j].delay = customers[j].start + customers[j].t - customers[j].d;

            if (customers[j].delay > maxDelay) {
                maxDelay = customers[j].delay;
            }
        }

        System.out.printf("最大延迟: %s\n", maxDelay);

        return customers;
    }

    static class Customer {
        // 客户id
        public int id;
        // 某个客户的服务时长
        public int t;
        // 某个客户的希望的完成时间
        public int d;

        // 某个客户的开始时间
        public int start;
        // 某个客户的延迟时间
        public int delay;


        public Customer(int id, int t, int d) {
            this.id = id;
            this.t = t;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "id=" + id +
                    ", start=" + start +
                    '}';
        }
    }

}
