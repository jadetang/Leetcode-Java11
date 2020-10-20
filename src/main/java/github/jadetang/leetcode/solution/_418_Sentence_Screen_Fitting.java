package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class _418_Sentence_Screen_Fitting {

    @Test
    void test() {
        assertEquals(2, wordsTyping(new String[]{"a", "bcd", "e"}, 3, 6));
    }

    @Test
    void test2() {
        assertEquals(0, wordsTyping(new String[]{"hello", "leetcode"}, 1, 10));
    }

    public int wordsTyping(String[] sentence, int rows, int cols) {
        int rIndex = 0;
        int cIndex = 0;
        int ans = 0;
        out: while (rIndex < rows) {
            for (int i = 0; i < sentence.length; i++) {
                String word = sentence[i];
                if (word.length() > cols) {
                    return 0;
                }
                int[] result = writeWord(rIndex, cIndex, cols - 1, word.length());
                rIndex = result[0];
                cIndex = result[1];
                if (rIndex >= rows) {
                    if (i < sentence.length - 1) {
                        break out;
                    }
                    if (cIndex > 0) {
                        break out;
                    }
                }
            }
            ans++;
        }
        return ans;
    }

    int[] writeWord(int rindex, int cindex, int maxc, int wordLength) {
        int nextCindex = cindex + wordLength + 1;
        if (nextCindex > maxc + 2) {
            return new int[]{rindex + 1, wordLength + 1};
        } else if (nextCindex == maxc + 1 || nextCindex == maxc + 2) {
            return new int[]{rindex + 1, 0};
        } else {
            return new int[]{rindex, nextCindex};
        }
    }
}
