package github.jadetang.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class _851_Loud_and_Rich {

    @Test
    void test() {
        int[] ans = loudAndRich(new int[][]{{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}},
                new int[]{3, 2, 5, 4, 6, 1, 7, 0});
        System.out.println(Arrays.toString(ans));
    }

    int[] quiet;
    int n;
    int[] ans;

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        this.quiet = quiet;
        this.n = quiet.length;
        ans = new int[quiet.length];
        Arrays.fill(ans, -1);
        Graph graph = new Graph(n);
        for (int i = 0; i < richer.length; i++) {
            graph.connect(richer[i][1], richer[i][0]);
        }
        for (int i = 0; i < n; i++) {
            DFS dfs = new DFS(graph, i);
        }
        return ans;
    }

    public class Graph {

        List<Integer>[] graph;

        public Graph(int n) {
            graph = new List[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
        }

        public void connect(int i, int j) {
            graph[i].add(j);
        }
    }

    private class DFS {

        boolean[] visited;
        Graph graph;

        public DFS(Graph graph, int start) {
            this.visited = new boolean[n];
            this.graph = graph;
            dfs(start);
        }

        private void dfs(int current) {
            if (ans[current] != -1) {
                return;
            }
            ans[current] = current;
            for (int richer : graph.graph[current]) {
                dfs(richer);
                if (quiet[ans[richer]] < quiet[ans[current]]) {
                    ans[current] = ans[richer];
                }
            }
        }
    }
}
