package github.jadetang.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class _791_Custom_Sort_String {

    public String customSortString(String s, String t) {
        var charList = new ArrayList<Character>();
        for (var c : t.toCharArray()) {
            charList.add(c);
        }
        var index = new int[26];
        Arrays.fill(index, Integer.MAX_VALUE);
        for (var i = 0; i < s.length(); i++) {
           index[s.charAt(i) - 'a'] = i;
        }
        charList.sort(Comparator.comparingInt(c -> index[c - 'a']));
        var sb = new StringBuilder();
        charList.forEach(sb::append);
        return sb.toString();
    }
}
