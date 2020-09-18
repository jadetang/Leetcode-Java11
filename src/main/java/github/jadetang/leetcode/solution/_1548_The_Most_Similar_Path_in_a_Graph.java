package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class _1548_The_Most_Similar_Path_in_a_Graph {

    @Test
    void test() {
        assertEquals(List.of(0, 1, 0, 1, 0, 1, 0, 1),
                mostSimilar(4, new int[][]{{1, 0}, {2, 0}, {3, 0}, {2, 1}, {3, 1}, {3, 2}}, new String[]{
                        "ATL", "PEK", "LAX", "DXB"
                }, new String[]{"ABC", "DEF", "GHI", "JKL", "MNO", "PQR", "STU", "VWX"}));
    }

    Map<Integer, List<Integer>> graph = new HashMap<>();

    String[] names;

    String[] targetPath;

    Integer min = Integer.MAX_VALUE;

    List<Integer> ans;


    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        buildGraph(roads);
        this.names = names;
        this.targetPath = targetPath;
        for (int i = 0; i < n; i++) {
            dfs(i, 0, new ArrayList<>());
        }
        return ans;
    }

    private void dfs(int currentNode, int distance, ArrayList<Integer> path) {
        if (distance > min) {
            return;
        }
        if (path.size() == targetPath.length) {
            if (distance < min) {
                min = distance;
                ans = new ArrayList<>(path);
            }
            return;
        }
        int currentCost = names[currentNode].equals(targetPath[path.size()]) ? 1 : 0;
        path.add(currentNode);
        for (int next : graph.get(currentNode)) {
            dfs(next, distance + currentCost, path);
        }
        path.remove(path.size() - 1);
    }

    private void buildGraph(int[][] roads) {
        for (int[] road : roads) {
            graph.computeIfAbsent(road[0], i -> new ArrayList<>()).add(road[1]);
            graph.computeIfAbsent(road[1], i -> new ArrayList<>()).add(road[0]);
        }
    }
}
