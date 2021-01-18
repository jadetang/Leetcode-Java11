package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

public class _1648_Sell_Diminishing_Valued_Colored_Balls {

    @Test
    void testOnlyOneElement() {
        assertEquals(21, maxProfit(new int[]{1000000000}, 1000000000));
    }

    @Test
    void testElements() {
        assertEquals(110, maxProfit(new int[]{2, 8, 4, 10, 6}, 20));
    }

    @Test
    void testElements2() {
        assertEquals(14, maxProfit(new int[]{2, 5}, 4));
    }

    @Test
    void testElements3() {
        assertEquals(19, maxProfit(new int[]{3, 5}, 6));
    }

    @Test
    void testElements4() {
        assertEquals(19, maxProfit(new int[]{8, 8, 8}, 6));
    }

    public int maxProfit(int[] inventory, int orders) {
        int mod = 1_000_000_007;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i : inventory) {
            queue.offer(i);
        }
        long ans = 0;
        while (orders > 0 && !queue.isEmpty()) {
            System.out.println("queue is " + queue);
            // orders--;
            int biggest = queue.poll();
            if (queue.isEmpty()) {
                // only one element;
                long orderCanMake = Math.min(biggest, orders);
                long value = (biggest + (biggest - orderCanMake + 1)) * orderCanMake / 2;
                ans = (ans + value % mod) % mod;
                if (biggest - orderCanMake > 0) {
                    queue.offer((int)(biggest - orderCanMake));
                }
                orders -= orderCanMake;
            } else {
                // more than one element
                List<Integer> tempList = new ArrayList<>();
                while (!queue.isEmpty() && queue.peek() == biggest) {
                    tempList.add(queue.poll());
                }
                // every elements are same the queue.
                if (queue.isEmpty()) {
                    long orderCanMake = 1;
                    long value = biggest;
                    ans = (ans + value % mod) % mod;
                    orders -= orderCanMake;
                    System.out.printf("big %d, smallest %d, orders %s, value %s, left order %s%n", biggest, biggest - orderCanMake + 1, orderCanMake, value, orders);
                    if (biggest - 1 > 0) {
                        queue.offer((int)(biggest - 1));
                    }
                    queue.addAll(tempList);
                } else {
                    long secondBiggest = queue.poll();
                    long orderCanMake = Math.min(biggest - secondBiggest, orders);
                    long value = (biggest + (biggest - orderCanMake + 1)) * orderCanMake / 2;
                    ans = (ans + value % mod) % mod;
                    orders -= orderCanMake;
                    System.out.printf("big %d, smallest %d, orders %s, value %s, left order %s%n", biggest, biggest - orderCanMake + 1, orderCanMake, value, orders);
                    if (biggest - orderCanMake > 0) {
                        queue.offer((int)(biggest - orderCanMake));
                    }
                    queue.addAll(tempList);
                    queue.add((int)secondBiggest);
                }
            }
        }
        return (int)ans;
    }
}
