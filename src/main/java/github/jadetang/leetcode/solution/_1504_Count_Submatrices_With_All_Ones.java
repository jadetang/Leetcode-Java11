package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class _1504_Count_Submatrices_With_All_Ones {

    @Test
    void test() {
        assertEquals(13, numSubmat(new int[][]{{1, 0, 1}, {1, 1, 0}, {1, 1, 0}}));
    }

    @Test
    void test2() {
        assertEquals(17, numSubmat(new int[][]{{1,1,1,1,0},{1,0,0,1,0},{0,0,1,0,1},{0,1,0,0,0}}));
    }

    public int numSubmat(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] left = new int[n][m];
        for (int i = 0; i < n; i++) {
            left[i][0] = mat[i][0];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (mat[i][j] == 1) {
                    left[i][j] += left[i][j - 1] + 1;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (left[i][j] > 0) {
                    ans += left[i][j];
                    int k = i - 1;
                    int min = left[i][j];
                    while (k >= 0 && left[k][j] > 0) {
                        min = Math.min(min, left[k][j]);
                        ans += min;
                        k--;
                    }
                }
            }
        }
        return ans;
    }
}
