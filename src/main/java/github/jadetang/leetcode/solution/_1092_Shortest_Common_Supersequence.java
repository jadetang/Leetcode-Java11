package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class _1092_Shortest_Common_Supersequence {

    @Test
    void test() {
        assertEquals("aabbabbbbaa", shortestCommonSupersequence("aabbabaa", "aabbbbbbaa"));
    }

    public String shortestCommonSupersequence(String str1, String str2) {
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < str1.length() && j < str2.length()) {
            char ci = str1.charAt(i);
            char cj = str2.charAt(j);
            if (ci == cj) {
                sb.append(ci);
                i++;
                j++;
                continue;
            }
            int disti = findDistance(ci, j, str2);
            int distj = findDistance(cj, i, str1);
            if (disti < distj) {
                sb.append(cj);
                j++;
            } else if (disti > distj) {
                sb.append(ci);
                i++;
            }
        }
        while (i < str1.length()) {
            sb.append(str1.charAt(i++));
        }
        while (j < str2.length()) {
            sb.append(str2.charAt(j++));
        }
        return sb.toString();
    }

    private int findDistance(char c, int start, String s) {
        int i = start;
        for (; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                return i - start;
            }
        }
        return Integer.MAX_VALUE;
    }
}
