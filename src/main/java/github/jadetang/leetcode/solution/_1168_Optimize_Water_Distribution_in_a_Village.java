package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

public class _1168_Optimize_Water_Distribution_in_a_Village {

    @Test
    void test() {
        assertEquals(131704, minCostToSupplyWater(5, new int[]{46012, 72474, 64965, 751, 33304},
                new int[][]{{2, 1, 6719}, {3, 2, 75312}, {5, 3, 44918}}));
    }

    public static class Edge implements Comparable<Edge> {

        private final int left;

        private final int right;

        int weight;

        public Edge(int left, int right, int weight) {
            this.left = left;
            this.right = right;
            this.weight = weight;
        }

        public int either() {
            return this.left;
        }

        public int other(int v) {
            if (this.left == v) {
                return this.right;
            }else if(this.right == v) {
                return this.left;
            }else {
                throw new RuntimeException();
            }
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    Map<Integer, List<Edge>> graph = new HashMap<>();

    boolean[] visited;

    List<Edge> mst = new ArrayList<>();

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        visited = new boolean[n + 2];
        for (int i = 1; i <= n + 1; i++) {
            graph.put(i, new ArrayList<>());
        }
        var queue = new PriorityQueue<Edge>();
        for (int i = 0; i < wells.length; i++) {
            var edge = new Edge(n + 1, i + 1, wells[i]);
            graph.get(i + 1).add(edge);
            graph.get(n + 1).add(edge);
        }
        for (int[] pipe : pipes) {
            var edge = new Edge(pipe[0], pipe[1], pipe[2]);
            graph.get(pipe[0]).add(edge);
            graph.get(pipe[1]).add(edge);
        }
        int ans = 0;
        visit(1, queue);
        while (!queue.isEmpty()) {
            var edge = queue.poll();
            int left = edge.either();
            int right = edge.other(left);
            if (visited[left] && visited[right]) {
                continue;
            }
            if (!visited[left]) {
                visit(left, queue);
            }
            if (!visited[right]) {
                visit(right, queue);
            }
            ans += edge.weight;
        }
        return ans;
    }

    private void visit(int v, PriorityQueue<Edge> queue) {
        visited[v] = true;
        for (var edge : graph.get(v)) {
            if (!visited[edge.other(v)]) {
                queue.offer(edge);
            }
        }
    }
}
