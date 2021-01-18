package github.jadetang.leetcode.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class _1135_Connecting_Cities_With_Minimum_Cost {

    public int minimumCost(int n, int[][] connections) {
        List<Edge>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] c : connections) {
            Edge e = new Edge(c[0], c[1], c[2]);
            graph[c[0]].add(e);
            graph[c[1]].add(e);
        }
        PriorityQueue<Edge> queue = new PriorityQueue<>((e1, e2) -> e1.length - e2.length);
        boolean[] visited = new boolean[n + 1];
        visit(1, visited, queue, graph);
        int length = 0;
        int edgeNumber = 0;
        while (!queue.isEmpty()) {
            Edge current = queue.poll();
            if (visited[current.left] && visited[current.right]) {
                continue;
            }
            if (!visited[current.left]) {
                visit(current.left, visited, queue, graph);
            }
            if (!visited[current.right]) {
                visit(current.right, visited, queue, graph);
            }
            length += current.length;
            edgeNumber++;
        }
        return edgeNumber == n - 1 ? length : -1;
    }

    private void visit(int node, boolean[] visited, PriorityQueue<Edge> queue,  List<Edge>[] graph) {
        visited[node] = true;
        for (Edge edge : graph[node]) {
            int thatNode = edge.otherNode(node);
            if (!visited[thatNode]) {
                queue.offer(edge);
            }
        }
    }

    public static class Edge {

        public int left;

        public int right;

        public int length;

        public Edge(int left, int right, int length) {
            this.left = left;
            this.right = right;
            this.length = length;
        }

        public int otherNode(int node) {
            if (node == this.left) {
                return this.right;
            }
            return this.left;
        }

        public String toString() {
            return this.left + ":" + this.right + ":" + this.length;
        }
    }

}