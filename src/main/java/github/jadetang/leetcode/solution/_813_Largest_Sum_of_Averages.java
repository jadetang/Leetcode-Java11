package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class _813_Largest_Sum_of_Averages {

    @Test
    void test() {
        assertEquals(20.D, largestSumOfAverages(new int[]{9, 1, 2, 3, 9}, 3));
    }


    @Test
    void test2() {
        assertEquals(18.16667, largestSumOfAverages(new int[]{4, 1, 7, 5, 6, 2, 3}, 4));
    }

    public double largestSumOfAverages(int[] a, int k) {
        double[][] dp = new double[a.length][k + 1];
        for (int i = 0; i < a.length; i++) {
            dp[i][1] = sum(a, 0, i) / (double) (i + 1);
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 2; j <= k; j++) {
                if (i + 1 >= j) {
                    for (int l = 1; l <= i; l++) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - l][j - 1] + sum(a, i - l + 1, i)/(double)l);
                    }
                }
            }
        }
        double max = 0.D;
        for (int l = 1; l <= k; l++) {
            max = Math.max(max, dp[a.length - 1][l]);
        }
        return max;
    }

    private int sum(int[] a, int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += a[i];
        }
        return sum;
    }
}
