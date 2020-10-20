package github.jadetang.leetcode.solution;

public class _1594_Maximum_Non_Negative_Product_in_a_Matrix {

    public int maxProductPath(int[][] grid) {
        int[][] max = new int[grid.length][grid[0].length];
        int[][] min = new int[grid.length][grid[0].length];
        int product = 1;
        for (int i = 0; i < grid[0].length; i++) {
            product *= grid[0][i];
            max[0][i] = product;
            min[0][i] = product;
        }
        product = 1;
        for (int i = 0; i < grid.length; i++) {
            product *= grid[i][0];
            max[i][0] = product;
            min[i][0] = product;
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid.length; j++) {
                int c = grid[i][j];
                max[i][j] = max(c * max[i - 1][j], c * max[i][j - 1], c * min[i][j - 1], c * min[i - 1][j]);
                min[i][j] = min(c * max[i - 1][j], c * max[i][j - 1], c * min[i][j - 1], c * min[i - 1][j]);
            }
        }
        return max[grid.length - 1][grid[0].length - 1] > 0 ? -1 : max[grid.length - 1][grid[0].length - 1];
    }

    private int max(int a, int b, int c, int d) {
        return Math.max(Math.max(a, b), Math.max(c ,d));
    }

    private int min(int a, int b, int c, int d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }
}
