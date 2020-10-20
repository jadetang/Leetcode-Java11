package github.jadetang.leetcode.solution;

import org.junit.jupiter.api.Test;

public class _1621_Number_of_Sets_of_K_Non_Overlapping_Line_Segments {

    @Test
    void t() {
        System.out.println(numberOfSets(30, 7));
        System.out.println(numberOfSets(5, 3));
    }


    public int numberOfSets(int n, int k) {
        long mod = 1_000_000_007;

        long[][] dp = new long[n][k + 1];
        dp[1][1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i][1] = (i + 1) * i / 2;
        }
        for (int i = 1; i <= k; i++) {
            dp[i][i] = 1;
        }
        for (int i = 2; i < n; i++) {
            for (int j = 2; j <= k; j++) {
                if (j > i) {
                    continue;
                }
                long sum = 0;
                for (int l = 1; l < i; l++) {
                    sum +=  (((long)l) * dp[i - l][j - 1]) % mod;
                }
                dp[i][j] = sum % mod;
            }
        }
        return (int)dp[n -1][k];
    }
}
