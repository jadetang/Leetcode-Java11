package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class _1553_Minimum_Number_of_Days_to_Eat_N_Oranges {

    @Test
    void test() {
        assertEquals(4, minDays(10));
    }
    public int minDays(int n) {
        int days = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        Set<Integer> visited = new HashSet<>();
        visited.add(n);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int c = queue.poll();
                visited.add(n);
                if (c == 0) {
                    return days;
                }
                if (c % 3 == 0 && !visited.contains(c % 3)) {
                    visited.add(c / 3);
                    queue.offer(c / 3);
                }
                if (c % 2 == 0 && !visited.contains(c % 2)) {
                    visited.add(c / 2);
                    queue.offer(c / 2);
                }
                if (!visited.contains(c - 1)) {
                    visited.add(c - 1);
                    queue.offer(c - 1);
                }
            }
            days++;
        }
        return -1;
    }
}
