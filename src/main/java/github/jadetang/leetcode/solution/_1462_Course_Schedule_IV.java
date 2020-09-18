package github.jadetang.leetcode.solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _1462_Course_Schedule_IV {

    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        int[] indegree = new int[n];
        int[] orders = new int[n];
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            int pre = prerequisite[0];
            int next = prerequisite[1];
            indegree[next]++;
            adj[pre].add(next);
        }
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> tempQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int order = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            orders[current] = order;
            for (int ad : adj[current]) {
                indegree[ad]--;
                if (indegree[ad] == 0) {
                    tempQueue.offer(ad);
                }
            }
            if (queue.isEmpty()) {
                queue = tempQueue;
                tempQueue = new LinkedList<>();
                order++;
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (int[] query : queries) {
            ans.add(orders[query[0]] < orders[query[1]]);
        }
        return ans;
    }
}
