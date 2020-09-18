package github.jadetang.leetcode.solution;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class _1585_Check_If_String_Is_Transformable_With_Substring_Sort_Operations {

    public boolean isTransformable(String s, String t) {
        Map<Integer, Queue<Integer>> index = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            index.put(i, new LinkedList<>());
        }
        for (int i = 0; i < s.length(); i++) {
            index.get(s.charAt(i) - '0').offer(i);
        }
        for (int i = 0; i < t.length(); i++) {
            int n = t.charAt(i) - '0';
            if (index.get(n).isEmpty()) {
                return false;
            }
            for (int j = 0; j < n; j++) {
                if (!index.get(j).isEmpty() && index.get(j).peek() < i) {
                    return false;
                }
            }
            index.get(n).poll();
        }
        return true;
    }
}
