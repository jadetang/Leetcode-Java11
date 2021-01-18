package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import github.jadetang.Debug;
import org.junit.jupiter.api.Test;

public class _1139_Largest_1_Bordered_Square {

    @Test
    void test() {
        assertEquals(9, largest1BorderedSquare(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}));
    }

    public int largest1BorderedSquare(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int[][] h = new int[grid.length][grid[0].length];
        int[][] v = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            int sum = 0;
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) {
                    sum = 0;
                } else {
                    sum++;
                }
                h[i][j] = sum;
            }
        }
        for (int j = 0; j < grid[0].length; j++) {
            int sum = 0;
            for (int i = 0; i < grid.length; i++) {
                if(grid[i][j] == 0) {
                    sum = 0;
                } else {
                    sum++;
                }
                v[i][j] = sum;
            }
        }
        Debug.print(h);
        Debug.print(v);
        int ans = 0;
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                int length = Math.min(h[i][j], v[i][j]);
                while (length > 0) {
                    int left = Math.max(0, j - length + 1);
                    int up = Math.max(0, i - length + 1);
                    if (v[i][left] >= length && h[up][j] >= length) {
                        ans = Math.max(ans, length);
                        break;
                    }
                    length--;
                }
            }
        }
        return ans * ans;
    }
}
