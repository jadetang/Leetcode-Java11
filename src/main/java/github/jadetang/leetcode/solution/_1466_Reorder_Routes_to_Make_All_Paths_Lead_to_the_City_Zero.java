package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class _1466_Reorder_Routes_to_Make_All_Paths_Lead_to_the_City_Zero {

    @Test
    void test() {
        assertEquals(2, minReorder(5, new int[][]{

                {1, 0},
                {1, 2},
                {3, 2},
                {3, 4}


        }));
    }

    Map<Integer, List<Integer>> graph = new HashMap<>();

    Map<Integer, Set<Integer>> roads = new HashMap<>();

    Set<Integer> visited = new HashSet<>();

    int ans = 0;

    public int minReorder(int n, int[][] connections) {
        for (int[] connection : connections) {
            graph.computeIfAbsent(connection[0], k -> new ArrayList<>()).add(connection[1]);
            graph.computeIfAbsent(connection[1], k -> new ArrayList<>()).add(connection[0]);
            roads.computeIfAbsent(connection[0], k -> new HashSet<>()).add(connection[1]);
        }
        System.out.println(graph);
        System.out.println(roads);
        visited.add(0);
        dfs(0);
        return ans;
    }


    private void dfs(int n) {
        for (int next : graph.getOrDefault(n, new ArrayList<>())) {
            if (!visited.contains(next)) {
                visited.add(next);
                if (roads.getOrDefault(n, new HashSet<>()).contains(next)) {
                    ans++;
                }
                dfs(next);
            }
        }
    }
}
