package github.jadetang.leetcode.other;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class MinimumDifferenceoOfExtremes {

    @Test
    void test() {
        assertEquals(2, solve(new int[]{1, 2, 3, 4, 5, 6}));
    }

    public int solve(int[] nums) {
        Arrays.sort(nums);
        if (nums.length <= 3) {
            return 0;
        }
        int[][][] dp = new int[nums.length][nums.length][4];
        for (int length = 1; length <= nums.length; length++) {
            for (int i = 0;  i + length - 1 < nums.length; i++) {
                int j = i + length - 1;
                for (int k = 0; k <= 3; k++) {
                    if (k >= length) {
                        continue;
                    }
                    if (k == 0) {
                        dp[i][j][0] = nums[j] - nums[i];
                    } else if (k == 1) {
                        dp[i][j][1] = Math.min(dp[i][j - 1][0], dp[i + 1][j][0]);
                    } else if (k == 2) {
                        dp[i][j][2] = Math.min(dp[i][j - 1][1], dp[i + 1][j][1]);
                    } else if (k == 3) {
                        dp[i][j][3] = Math.min(dp[i][j - 1][2], dp[i + 1][j][2]);
                    }
                }
            }
        }
        return dp[0][nums.length - 1][3];
    }
}
