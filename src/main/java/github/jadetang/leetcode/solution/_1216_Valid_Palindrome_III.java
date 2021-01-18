package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class _1216_Valid_Palindrome_III {

    @Test
    void test() {
      //  assertTrue(isValidPalindrome("ab", 1));
        assertTrue(isValidPalindrome("abcdeca", 2));
    }

    @Test
    void test2() {
        assertTrue(isValidPalindrome("aaabaabaa", 1));
    }

    @Test
    void test3() {
        assertTrue(isValidPalindrome("abba", 0));
    }

    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        boolean[][][] dp = new boolean[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                Arrays.fill(dp[i][i], true);
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                if (s.charAt(i) == s.charAt(i - 1) || j >= 1) {
                    dp[i-1][i][j] = true;
                }
            }
        }
        for (int l = 3; l <= n; l++) {
            for (int i = 0;  i + l - 1 < n; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (k + 1 >= 0) {
                        System.arraycopy(dp[i + 1][j - 1], 0, dp[i][j], 0, k + 1);
                    }
                } else {
                    for (int tk = 1; tk <= k; tk++) {
                        dp[i][j][tk] = dp[i][j - 1][tk-1] || dp[i + 1][j][tk - 1];
                        if (dp[i][j][tk]) {
                            break;
                        }
                    }
                }
            }
        }
        boolean ans = false;
        for (int i = 0; i <= k; i++) {
            ans |= dp[0][n - 1][k];
        }
        return ans;
    }
}
