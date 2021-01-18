package github.jadetang.leetcode.other;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class LargestSumOfNonAdjacentNumbersInCircularList {

    @Test
    void test() {
        assertEquals(12, solve(new int[]{9, 2, 3, 5}));
    }


    int take = 0;
    int notake = 1;
    public int solve(int[] nums) {
        return Math.max(d(nums, 0, nums.length - 2), d(nums, 1, nums.length - 1));
    }

    private int d(int[] nums, int l, int r) {
        int length = r - l + 1;
        int[][] dp = new int[length][2];
        dp[0][take] = nums[l];
        for (int i = 1; i < length; i++) {
            dp[i][take] = dp[i - 1][notake] + nums[i + l];
            dp[i][notake] = Math.max(dp[i - 1][take], dp[i - 1][notake]);
        }
        for (int[ ] d : dp) {
            System.out.println(Arrays.toString(d));
        }
        return Math.max(dp[dp.length - 1][take], dp[dp.length - 1][notake]);
    }
}
