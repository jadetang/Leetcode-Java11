package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class main {

    @Test
    void test() {
        assertEquals(6, numWays(new String[]{"acca", "bbbb", "caca"}, "aba"));
    }


    long mod = 1_000_000_007L;
    long ans = 0;
    char[] chars;
    String[] words;
    String target;

    public int numWays(String[] words, String target) {
        this.chars = target.toCharArray();
        this.words = words;
        this.target = target;
        search(0, 0, 1L);
        return (int) ans;
    }

    public void search(int t, int w, long count) {
        if (t >= target.length()) {
            ans = (ans + count) % mod;
        } else {
            if (w >= words[0].length()) {
                return;
            }
            for (int i = t; i < target.length(); i++) {
                char targetC = target.charAt(i);
                int match = 0;
                for (String word : words) {
                    if (word.charAt(w) == targetC) {
                        match++;
                    }
                }
                if (match > 0) {
                    search(i + 1, w + 1, (count * match) % mod);
                }
                search(i, w + 1, count);
            }
        }
    }

}
