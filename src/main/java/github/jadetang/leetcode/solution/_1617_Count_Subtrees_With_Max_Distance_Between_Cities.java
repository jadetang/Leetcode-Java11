package github.jadetang.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class _1617_Count_Subtrees_With_Max_Distance_Between_Cities {

    @Test
    void test() {
        int[] result = countSubgraphsForEachDiameter(4, new int[][]{{1,2},{2,3},{2,4}});
        System.out.println(Arrays.toString(result));
    }

    List<Integer>[] graph;

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }
        int[] result = new int[n - 1];
        for (int i = 1; i < (1 << n); i++) {
            result[maxDistance(n, i)]++;
        }
        return result;
    }

    private int maxDistance(int n, int subGraph) {
        boolean[] visited = new boolean[n];
        boolean[] visited2 = new boolean[n];
        Arrays.fill(visited, true);
        Arrays.fill(visited2, true);
        for (int i = 0; i < n; i++) {
            if ((subGraph & (1 << i)) > 0) {
                visited[i] = false;
                visited2[i] = false;
            }
        }
        System.out.println(Integer.toBinaryString(subGraph));
        System.out.println(Arrays.toString(visited));
        int start = 0;
        for (int i = 1; i <= n; i++) {
            if ((subGraph | (1 << i)) > 0) {
                start = i - 1;
                break;
            }
        }
        int[] distance = new int[n];
        dfs(distance, start, visited, 0);
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i - 1] > max) {
                max = distance[i - 1];
                start = i;
            }
        }
        distance = new int[n];
        dfs(distance, start, visited2, 0);
        int maxDistance = IntStream.of(distance).max().orElse(0);
        System.out.println(maxDistance);
        return maxDistance;
    }

    private void dfs(int[] distance, int current, boolean[] visited, int depth) {
        if (visited[current]) {
            return;
        }
        visited[current] = true;
        distance[current] = depth;
        for (int next : graph[current]) {
            dfs(distance, next, visited, depth + 1);
        }
    }
}
