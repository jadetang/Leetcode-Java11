package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.Test;

public class _1129_Shortest_Path_with_Alternating_Colors {

    @Test
    void test() {
        int[] ans = shortestAlternatingPaths(3, new int[][]{{0, 1}, {0, 2}}, new int[][]{{1, 0}});
        assertArrayEquals(new int[]{0, 1, 1}, ans);
    }

    @Test
    void test2() {
        assertArrayEquals(new int[]{0, 1, -1}, shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{2, 1}}));
    }

    @Test
    void test3() {
        assertArrayEquals(new int[]{0, 1, 2, 3, 7},
                shortestAlternatingPaths(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}},
                        new int[][]{{1, 2}, {2, 3}, {3, 1}}));
    }


    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        Graph red = new Graph(n);
        for (int[] edge : red_edges) {
            red.connect(edge[0], edge[1]);
        }
        Graph blue = new Graph(n);
        for (int[] edge : blue_edges) {
            blue.connect(edge[0], edge[1]);
        }
        boolean[] redVisited = new boolean[n];
        boolean[] blueVisited = new boolean[n];
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Queue<SimpleEntry<Integer, Boolean>> queue = new LinkedList<>();
        queue.offer(new SimpleEntry<>(0, true));
        queue.offer(new SimpleEntry<>(0, false));
        redVisited[0] = true;
        blueVisited[0] = true;
        int dis = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                SimpleEntry<Integer, Boolean> current = queue.poll();
                int currentNode = current.getKey();
                boolean isRed = current.getValue();
                if (ans[currentNode] == -1) {
                    ans[currentNode] = dis;
                } else {
                    ans[currentNode] = Math.min(dis, ans[currentNode]);
                }
                boolean[] visited = isRed ? blueVisited : redVisited;
                Graph graph = isRed ? blue : red;
                for (int adj : graph.adj(currentNode)) {
                    if (!visited[adj]) {
                        queue.offer(new SimpleEntry<>(adj, !isRed));
                    }
                }
            }
            dis++;
        }
        return ans;
    }

    public class Graph {

        List<Integer>[] array;

        public Graph(int n) {
            array = new List[n];
            for (int i = 0; i < n; i++) {
                array[i] = new ArrayList<>();
            }
        }

        public void connect(int i, int j) {
            array[i].add(j);
        }

        public List<Integer> adj(int i) {
            return array[i];
        }

    }
}