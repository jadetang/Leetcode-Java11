package github.jadetang.leetcode.other;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class Solution {

    @Test
    void test() {
        int[][] ans = restoreMatrix(new int[]{3, 8}, new int[]{4, 7});
        for(int[] r : ans) {
            System.out.println(Arrays.toString(r));
        }
    }

    int[][] ans;

    int r;

    int c;

    int[] row;

    int[] col;

    int[] rowSum;

    int[] colSum;

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        ans = new int[rowSum.length][colSum.length];

        r = rowSum.length;
        c = colSum.length;
        for (int i = 0; i < r; i++) {
            Arrays.fill(ans[i], -1);
        }
        row = new int[rowSum.length];
        col = new int[colSum.length];
        this.rowSum = rowSum;
        this.colSum = colSum;
        fillboard();
        return ans;
    }

    private boolean fillboard() {
/*        for (int i = 0; i < r; i++) {
            System.out.println(Arrays.toString(ans[i]));
        }*/
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (ans[i][j] != -1) {
                    continue;
                }
                int left = rowSum[i] - row[i];
                for (int k = 0; k <= left; k++) {
                    if (valid(i, j, k)) {
                        ans[i][j] = k;
                        row[i] += k;
                        col[j] += k;
                        if (fillboard()) {
                            return true;
                        } else {
                            row[i] -= k;
                            col[j] -= k;
                            ans[i][j] = -1;
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean valid(int i, int j, int k) {
        boolean rowValid = j == c - 1 ? row[i]  + k == rowSum[i] : row[i]  + k <= rowSum[i];
        boolean colValid = i == r - 1 ? col[j]  + k == colSum[j] : col[j]  + k <= colSum[j];
        return rowValid & colValid;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(System.identityHashCode(s));
        System.out.println(s.hashCode());
        test(s);
    }

    static void test(Solution s) {
        System.out.println(System.identityHashCode(s));
        System.out.println(s.hashCode());
    }

}
