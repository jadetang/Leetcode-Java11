package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import github.jadetang.Debug;
import org.junit.jupiter.api.Test;

public class _1639_Number_of_Ways_to_Form_a_Target_String_Given_a_Dictionary {

    @Test
    void test() {
        assertEquals(6, numWays(new String[]{"acca","bbbb","caca"}, "aba"));
        assertEquals(4, numWays(new String[]{"abba","baab"}, "bab"));
        assertEquals(16, numWays(new String[]{"abab","baba","abba","baab"}, "abba"));
    }

    public int numWays(String[] words, String target) {
        long mod = 1_000_000_009;
        long[][] countIndex = new long[words[0].length()][26];
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                countIndex[i][word.charAt(i) - 'a']++;
            }
        }
        long[][] dp = new long[target.length()][words[0].length()];
        for (int i = 0; i < words[0].length(); i++) {
            dp[0][i] += countIndex[i][target.charAt(0) - 'a'];
            if (i > 0) {
                dp[0][i] += dp[0][i - 1] % mod;
            }
        }
       // Debug.print(dp);
        for (int i = 1; i < target.length(); i++) {
            for (int j = 0; j < words[0].length(); j++) {
                if (i > j) {
                    continue;
                }
                char currentChar = target.charAt(i);
                long temp = 0;
                for (int k = j - 1; k >= 0; k--) {
                    temp = (temp + countIndex[k + 1][currentChar - 'a'] * dp[i - 1][k] % mod) % mod;
                }
                dp[i][j] = temp;
            }
        }
       // Debug.print(dp);

        return (int)dp[target.length() - 1][words[0].length() - 1];
    }


}
