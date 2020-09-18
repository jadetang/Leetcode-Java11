package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

public class _1514_Path_with_Maximum_Probability {

    @Test
    void test() {
        assertEquals(0.25000D,
                maxProbability(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.2}, 0, 2));
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Graph graph = new Graph(n);
        for (int i = 0; i < edges.length; i++) {
            graph.connect(edges[i][0], edges[i][1], succProb[i]);
        }
        double[] dis = new double[n];
        Arrays.fill(dis, Double.MIN_VALUE);
        dis[start] = 1.0D;
        PriorityQueue<Integer> queue = new PriorityQueue<>((i1, i2) -> Double.compare(dis[i2], dis[i1]));
        queue.offer(start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.println("visit:" + current);
            for (Edge e : graph.edges(current)) {
                int next = e.to(current);
                if (dis[next] < dis[current] * e.weight ) {
                    dis[next] = dis[current] * e.weight;
                 //   queue.remove(next);
                    queue.offer(next);
                }
            }
        }
        return dis[end];
    }

    public static class Graph {

        Map<Integer, List<Edge>> graph = new HashMap<>();

        public Graph(int n) {
            for (int i = 0; i < n; i++) {
                graph.put(i, new ArrayList<>());
            }
        }

        public void connect(int left, int right, double weight) {
            Edge e = new Edge(left, right, weight);
            graph.get(left).add(e);
            graph.get(right).add(e);
        }

        public List<Edge> edges(int i) {
            return graph.get(i);
        }
    }

    public static class Edge {

        double weight;
        int left;
        int right;

        public Edge(int left, int right, double weight) {
            this.weight = weight;
            this.left = left;
            this.right = right;
        }

        public int to(int node) {
            if (this.left == node) {
                return right;
            }
            return this.left;
        }
    }


    public double maxProbability2(int n, int[][] edges, double[] succProb, int start, int end) {
        var g = new ArrayList<List<Map.Entry<Integer, Double>>>();
        for (int i = 0; i < n; ++i) g.add(new ArrayList<Map.Entry<Integer, Double>>());
        for (int i = 0; i < edges.length; ++i) {
            g.get(edges[i][0]).add(new AbstractMap.SimpleEntry<>(edges[i][1], -Math.log(succProb[i])));
            g.get(edges[i][1]).add(new AbstractMap.SimpleEntry<>(edges[i][0], -Math.log(succProb[i])));
        }
        var seen = new boolean[n];
        var dist = new double[n];
        Arrays.fill(dist, Double.MAX_VALUE);
        seen[start] = true;
        dist[start] = 0.0;
        // {u, dist[u]}
        var q = new PriorityQueue<Map.Entry<Integer, Double>>(Map.Entry.comparingByValue());
        q.offer(new AbstractMap.SimpleEntry<>(start, 0.0));
        while (!q.isEmpty()) {
            var node = q.poll();
            final int u = node.getKey();
            if (u == end) return Math.exp(-dist[end]);
            seen[u] = true;
            for (var e : g.get(u)) {
                final int v = e.getKey();
                final double w = e.getValue();
                if (seen[v] || dist[u] + w > dist[v]) continue;
                dist[v] = dist[u] + w;
                q.offer(new AbstractMap.SimpleEntry<>(v, dist[v]));
            }
        }
        return 0;
    }
}
