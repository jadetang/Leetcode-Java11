package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.Test;

public class _1377_Frog_Position_After_T_Seconds {

    @Test
    void test() {
        assertEquals(0.16667, frogPosition(7, new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, 2, 4), 0.00001D);
    }

    List<Integer>[] graph = new List[]{};

    int target;

    public double frogPosition(int n, int[][] edges, int t, int target) {
        graph = new List[n + 1];
        this.target = target;
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        boolean[] visited = new boolean[n + 1];
        double[] possibility = new double[n + 1];
        possibility[1] = 1.0D;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        while (t > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                double p = possibility[current];
                int childNumber = 0;
                for (int child : graph[current]) {
                    if (!visited[child]) {
                        childNumber++;
                    }
                }
                if (childNumber != 0) {
                    possibility[current] = 0;
                    for (int child : graph[current]) {
                        if (!visited[child]) {
                            visited[child] = true;
                            possibility[child] += p / childNumber;
                            queue.offer(child);
                        }
                    }
                }
            }
            t--;
        }
        return possibility[target];
    }
}
