package org.example.greedy_approach;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ScheduleTest {

    int n = 5;
    int[] t = new int[]{0, 5, 8, 4, 10, 3};
    int[] d = new int[]{0, 10, 12, 15, 11, 20};
    @Test
    void schedule() {
        Schedule.Customer[] customers = Schedule.schedule(n, t, d);
        System.out.printf("调度: %s", Arrays.toString(customers));
    }

}