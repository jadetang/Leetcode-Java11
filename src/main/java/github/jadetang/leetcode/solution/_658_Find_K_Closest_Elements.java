package github.jadetang.leetcode.solution;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class _658_Find_K_Closest_Elements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        var list = IntStream.of(arr).boxed().collect(Collectors.toList());
        if (list.size() >= k) {
            return list;
        }
        list.sort((a, b) -> {
            int dist1 = Math.abs(a - x);
            int dist2 = Math.abs(b - x);
            if (dist1 == dist2) {
                return a - b;
            }
            return dist1 - dist2;
        });
        return list.subList(0, k).stream().sorted().collect(Collectors.toList());
    }
}
