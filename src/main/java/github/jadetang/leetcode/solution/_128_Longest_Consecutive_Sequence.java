package github.jadetang.leetcode.solution;

import java.util.HashSet;
import java.util.Set;

public class _128_Longest_Consecutive_Sequence {

    public int longestConsecutive(int[] num) {
        Set<Integer> set = new HashSet<Integer>();
        for (int n : num) {
            set.add(n);
        }
        int length = 0;
        for (int n : num) {
            if (set.contains(n - 1)) {
                continue;
            }
            int l = 0;
            while (set.contains(n)) {
                l++;
                n++;
            }
            length = Math.max(length, l);
        }
        return length;
    }
}
