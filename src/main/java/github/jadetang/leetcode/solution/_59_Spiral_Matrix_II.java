package github.jadetang.leetcode.solution;

public class _59_Spiral_Matrix_II {

    public int[][] generateMatrix(int n) {
        int[][] board = new int[n][n];
        int[] dir = new int[] {0, 1, 0, -1, 0};
        int r = 0;
        int c = 0;
        int dirIndex = 0;
        for (int i = 1; i <= n * n; i++) {
            board[r][c] = i;
            int nextr = r + dir[dirIndex];
            int nextc = c + dir[dirIndex + 1];
            if (nextr >= 0 && nextr < n && nextc >= 0 && nextc < n && board[nextr][nextc] == 0) {
                r = nextr;
                c = nextc;
                continue;
            }else {
                dirIndex = (dirIndex + 1) % 4;
                r = r + dir[dirIndex];
                c = c + dir[dirIndex + 1];
            }
        }
        return board;
    }
}
