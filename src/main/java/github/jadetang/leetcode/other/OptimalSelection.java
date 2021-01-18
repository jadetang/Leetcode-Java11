package github.jadetang.leetcode.other;


import static org.junit.jupiter.api.Assertions.assertEquals;

import github.jadetang.Debug;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * We are given the prices of k products over n days, and we want to buy each product exactly once.
 * However, we are allowed to buy at most one product in a day. What is the minimum total price
 */
public class OptimalSelection {

    @Test
    void test() {
        assertEquals(5, optimalPrice(
                new int[][]{{6, 9, 5, 2, 8, 9, 1, 6}, {8, 2, 6, 2, 7, 5, 7, 2}, {5, 3, 9, 7, 3, 5, 1, 4}}));
    }


    @Test
    void test2() {
        assertEquals(2, optimalPrice(
                new int[][]{{1, 2}, {2, 1}}));
    }

    @Test
    void test24() {
        assertEquals(3, optimalPrice(
                new int[][]{{1, 2, 1}, {1, 2, 1}, {2, 1, 3}}));
    }


    @Test
    void test3() {
        assertEquals(3, optimalPrice(
                new int[][]{{1, 2}, {2, 2}}));
    }

    public int optimalPrice(int[][] prices) {
        int product = prices.length;
        int days = prices[0].length;
        int[][] dp = new int[1 << product][days];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE / 2);
        }
        for (int i = 0; i < product; i++) {
            dp[1 << i][0] = prices[i][0];
        }
        for (int d = 1; d < days; d++) {
            for (int s = 0; s < (1 << product); s++) {
                dp[s][d] = dp[s][d - 1];
                for (int i = 0; i < product; i++) {
                    if ((s & (1 << i)) > 0) {
                        dp[s][d] = Math.min(dp[s][d], dp[s ^ (1 << i)][d -1] + prices[i][d]);
                    }
                }
            }
        }
        Debug.print(dp);
        return dp[(1 << product) - 1][days - 1];
    }
}
