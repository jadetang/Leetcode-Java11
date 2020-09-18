package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

public class _778_Swim_in_Rising_Water {

    @Test
    void test() {
        assertEquals(3, swimInWater(new int[][]{{0, 2}, {1, 3}}));
    }

    int[] dir = new int[]{1, 0, -1, 0, 1};

    boolean[][] visited;

    public int swimInWater(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        this.visited = new boolean[r][c];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(p -> grid[p[0]][p[1]]));
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        int ans = 0;
        while (true) {
            int[] p = queue.poll();
            int currentR = p[0];
            int currentC = p[1];
            ans = Math.max(ans, grid[currentR][currentC]);
            if (currentR == r - 1 && currentC == c - 1) {
                return ans;
            }
            for (int i = 0; i < dir.length - 1; i++) {
                int nextR = currentR + dir[i];
                int nextC = currentC + dir[i + 1];
                if (nextR >= 0 && nextR < grid.length && nextC >= 0 && nextC < grid[0].length
                        && !visited[nextR][nextC]) {
                    visited[nextR][nextC] = true;
                    queue.offer(new int[]{nextR, nextC});
                }
            }
        }
    }
}
