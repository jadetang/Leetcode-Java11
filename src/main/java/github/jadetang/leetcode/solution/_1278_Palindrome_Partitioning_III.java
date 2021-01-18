package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import github.jadetang.Debug;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class _1278_Palindrome_Partitioning_III {

    @Test
    void test() {
        assertEquals(1, palindromePartition("abc", 2));
    }

    @Test
    void test2() {
        assertEquals(0, palindromePartition("aabbc", 2));
    }

    public int palindromePartition(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n + 1][k + 1];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE / 2);
        }
        dp[1][1] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(k, i); j++) {
                for (int l = i; l >= j; l--) {
                    dp[i][j] = Math.min(dp[i][j], dp[l - 1][j - 1] + count(s, l - 1, i - 1));
                }
            }
        }
        Debug.print(dp);
        return dp[n][k];
    }

    private int count(String s, int left, int right) {
        int count = 0;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                count++;
            }
            left++;
            right--;
        }
        return count;
    }
}
