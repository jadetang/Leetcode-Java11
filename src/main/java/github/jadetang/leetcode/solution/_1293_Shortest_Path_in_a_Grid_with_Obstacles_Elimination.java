package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class _1293_Shortest_Path_in_a_Grid_with_Obstacles_Elimination {

    @Test
    void test() {
        assertEquals(2, shortestPath(new int[][] {{0, 0}, {0, 0}}, 0));
    }

    @Test
    void test2() {
        assertEquals(2, shortestPath(new int[][] {{0, 1}, {1, 0}}, 1));
    }

    boolean[][][] visited;
    int[][][] dp;
    int r;
    int c;
    int[][] grid;

    public int shortestPath(int[][] grid, int k) {
        r = grid.length;
        c = grid[0].length;
        dp = new int[r][c][k + 1];
        visited = new boolean[r][c][k + 1];
        this.grid = grid;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int l = 0; l <= k; l++) {
                    dp[i][j][l] = Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 0; i <= k; i++) {
            dp[0][0][i] = 0;
        }
        //  visited[0][0][k] = true;
        search(0, 0, k);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            if (dp[r - 1][c - 1][k] != 0) {
                min = Math.min(min, dp[r - 1][c - 1][k]);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private void search(int r, int c, int k) {
        if (visited[r][c][k]) {
            return;
        }
        visited[r][c][k] = true;
        int[] dir = new int[]{1, 0, -1, 0, 1};
        for (int i = 0; i < 4; i++) {
            int nextR = r + dir[i];
            int nextC = c + dir[i + 1];
            if (nextR >= 0 && nextR < this.r && nextC >= 0 && nextC < this.c) {
                if (grid[nextR][nextC] == 0) {
                    search(nextR, nextC, k);
                    for (int j = 0; j <= k; j++) {
                        dp[nextR][nextC][j] = Math.min(dp[nextR][nextC][j], dp[r][c][j] + 1);
                    }
                } else if (grid[nextR][nextC] == 1) {
                    if (k > 0) {
                        //continue;
                        search(nextR, nextC, k - 1);
                        for (int j = 1; j <= k; j++) {
                            dp[nextR][nextC][j - 1] = Math.min(dp[nextR][nextC][j - 1], dp[r][c][j] + 1);
                        }
                    }
                }
            }
        }
    }
}
