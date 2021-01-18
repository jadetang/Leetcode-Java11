package github.jadetang.leetcode.solution;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class Minimum_Deletions_to_Make_String_Balanced {


    @Test
    void test() {
        minimumDeletions("baababbaabbaaabaabbabbbabaaaaaabaabababaaababbb");
    }

    int a = 0;
    int b = 1;
    public int minimumDeletions(String s) {
        //
        int i = 0;
        while (i < s.length() && s.charAt(i) == 'b') {
            i++;
        }
        if (i == s.length()) {
            return i;
        }
        s = s.substring(i);
        int[][] dp = new int[s.length() + 1][2];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE / 2);
        }
        dp[0][a] = 0;
        dp[0][b] = 0;
        int[] lastA = new int[s.length()];
        int[] lastB = new int[s.length()];
        int lastAIndex = -1;
        int lastBIndex = -1;
        for (int index = 0; index < s.length(); index++) {
            lastA[index] = lastAIndex;
            lastB[index] = lastBIndex;
            if (s.charAt(index) == 'a') {
                lastAIndex = index;
            } else {
                lastBIndex = index;
            }
        }

        for (int index = i; index < s.length(); index++) {
            for (int j = 0; j < 2; j++) {
                char c = s.charAt(index);
                if (c == 'b') {
                    lastBIndex = lastB[index];
                    if (lastBIndex == - 1) {
                        dp[index + 1][b] = 0;
                    } else {
                        dp[index + 1][b] = dp[lastBIndex + 1][b] + index - lastBIndex - 1;
                    }
                } else {
                    // c == 'a';
                    lastAIndex = lastA[index];
                    if (lastAIndex == -1) {
                        dp[index + 1][a] = 0;
                    } else {
                        dp[index + 1][a] = dp[lastAIndex + 1][a] + index - lastAIndex - 1;
                    }
                }

            }
        }
      //  for (int[] d : dp) {
      //      System.out.println(Arrays.toString(d));
      //  }
        return Math.min(dp[s.length()][a], dp[s.length()][b]) + i;
    }


    private int findLastA(String s, int i, char c) {
        for (int j = i - 1;  j >= 0; j--) {
            if (s.charAt(j) == c) {
                return j;
            }
        }
        return -1;
    }
}
