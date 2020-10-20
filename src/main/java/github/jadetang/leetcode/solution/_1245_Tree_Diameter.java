package github.jadetang.leetcode.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class _1245_Tree_Diameter {

    Map<Integer, List<Integer>> graph = new HashMap<>();

    int n;

    public int treeDiameter(int[][] edges) {
        for (int[] e : edges) {
            graph.computeIfAbsent(e[0], k -> new ArrayList<>()).add(e[1]);
            graph.computeIfAbsent(e[1], k -> new ArrayList<>()).add(e[0]);
        }
        n = graph.size();
        int[] d1 = distance(0);
        int deepest = findDeepestNode(d1);
        int[] d2 = distance(deepest);
        return IntStream.of(d2).max().getAsInt();
    }

    private int findDeepestNode(int[] d) {
        int ans = 0;
        int max = d[0];
        for (int i = 0; i < d.length; i++) {
            if (d[i] > max) {
                max = d[i];
                ans = i;
            }
        }
        return ans;
    }

    public int[] distance(int start) {
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];
        visited[start] = true;
        dist[start] = 0;
        dfs(visited, dist, start, 1);
        return dist;
    }

    private void dfs(boolean[] visited, int[] dist, int current, int d) {
        for (int next : graph.get(current)) {
            if (!visited[next]) {
                visited[next] = true;
                dist[next] = d;
                dfs(visited, dist, next, d + 1);
            }
        }
    }
}
