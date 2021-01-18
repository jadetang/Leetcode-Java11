package github.jadetang.leetcode.other;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import org.junit.jupiter.api.Test;

public class LexicographicCombinationIterator {

    public static void main(String[] args) {
        LexicographicCombinationIterator iterator = new LexicographicCombinationIterator("abcdefgh", 4);
        while (iterator.hasnext()) {
            System.out.println(iterator.next());
        }
    }

    char[] chars;
    int[] indexs;
    int n;

    public LexicographicCombinationIterator(String s, int k) {
        chars = s.toCharArray();
        n = chars.length;
        Arrays.sort(chars);
        indexs = new int[k];
        for (int i = 0; i < k; i++) {
            indexs[i] = i;
        }
    }

    public String next() {
        StringBuilder sb = new StringBuilder();
        for (int index : indexs) {
            sb.append(chars[index]);
        }
        System.out.print(Arrays.toString(indexs) + "->");
        nextIndexs();
        System.out.println(Arrays.toString(indexs));
        return sb.toString();
    }

    public boolean hasnext() {
        // System.out.println(Arrays.toString(indexs));
        return indexs != null;
    }

    private void nextIndexs() {
        int carry = 1;
        int upperBound = n;
        for (int i = indexs.length - 1; i >= 0; i--) {
            indexs[i] += carry;
            if (indexs[i] == upperBound) {
                upperBound--;
                indexs[i] = -1;
                carry = 1;
            } else {
                carry = 0;
            }
        }
        for (int i = 0; i < indexs.length; i++) {
            if (indexs[i] == -1) {
                if (i == 0) {
                    indexs = null;
                    return;
                } else {
                    indexs[i] = indexs[i - 1] + 1;
                    if (indexs[i] == n) {
                        indexs = null;
                        return;
                    }
                }
            }
        }
    }
}
