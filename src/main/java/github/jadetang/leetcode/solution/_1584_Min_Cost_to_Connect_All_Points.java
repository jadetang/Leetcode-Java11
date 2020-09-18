package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class _1584_Min_Cost_to_Connect_All_Points {

    @Test
    void test() {
        assertEquals(20, minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
    }

    public int minCostConnectPoints(int[][] points) {
        Graph graph = new Graph();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                Point p1 = new Point(points[i][0], points[i][1]);
                Point p2 = new Point(points[j][0], points[j][1]);
                graph.addEdge(new Edge(p1, p2));
            }
        }
        Prime p = new Prime(graph, new Point(points[0][0], points[0][1]));
        return p.mst();
    }

    public static class Prime {

        Graph graph;

        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(Edge::weight));

        Set<Point> visited = new HashSet<>();

        int cost;

        public Prime(Graph graph, Point start) {
            this.graph = graph;
            build(start);
        }

        private void build(Point start) {
            visit(start);
            while (!edges.isEmpty()) {
                Edge e = edges.poll();
                if (visited.contains(e.left) && visited.contains(e.right)) {
                    continue;
                }
                cost += e.weight();
                if (!visited.contains(e.left)) {
                    visited.add(e.left);
                    visit(e.left);
                }
                if (!visited.contains(e.either(e.left))) {
                    visit(e.either(e.left));
                }
            }
        }

        private void visit(Point point) {
            visited.add(point);
            for (Edge edge : graph.getEdges(point)) {
                if (!visited.contains(edge.either(point))) {
                    edges.add(edge);
                }
            }
        }

        public int mst() {
            return cost;
        }
    }


    public static class Graph {

        Map<Point, List<Edge>> map = new HashMap<>();

        public List<Edge> getEdges(Point p) {
            return map.get(p);
        }

        public void addEdge(Edge edge) {
            map.computeIfAbsent(edge.left, k -> new ArrayList<>()).add(edge);
            map.computeIfAbsent(edge.right, k -> new ArrayList<>()).add(edge);
        }
    }

    public static class Edge {
        Point left;
        Point right;

        public Edge(Point left, Point right) {
            this.left = left;
            this.right = right;
        }

        public Point either(Point p) {
            if (left.equals(p)) {
                return right;
            }
            return left;
        }

        public int weight() {
            return Math.abs(left.x - right.x) + Math.abs(left.y - right.y);
        }
    }

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
