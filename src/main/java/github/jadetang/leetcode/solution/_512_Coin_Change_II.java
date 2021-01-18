package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import github.jadetang.Debug;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class _512_Coin_Change_II {

    @Test
    void test() {
        assertEquals(10, change(10, new int[]{1, 2, 5}));
    }

    public int change(int amount, int[] coins) {
        if (amount == 0 ) {
            return 1;
        }
        if (coins.length == 0) {
            return 0;
        }
        int[][] dp = new int[amount + 1][coins.length + 1];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i<= amount; i++) {
            for (int j = 1; j <= coins.length; j++) {
                int mount = i;
                int coin = coins[j - 1];
                while (mount >= 0) {
                    dp[i][j] += dp[mount][j - 1];
                    mount -= coin;
                }
            }
        }
        return dp[amount][coins.length];
    }
}
