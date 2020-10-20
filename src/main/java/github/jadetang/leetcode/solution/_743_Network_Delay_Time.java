package github.jadetang.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _743_Network_Delay_Time {

    public int networkDelayTime(int[][] times, int n, int k) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        for (int i = 0; i < n; i++) {
            for (int[] time : times) {
                int start = time[0];
                int end = time[1];
                int weight = time[2];
                if (dist[end] > dist[start] + weight) {
                    dist[end] = dist[start] + weight;
                }
            }
        }
        Arrays.sort(dist);
        return dist[dist.length - 1];
    }
}
