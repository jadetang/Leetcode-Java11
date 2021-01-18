package github.jadetang;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class Debug {

    public static void print(int[][] array) {
        for (int[] a : array) {
            System.out.println(Arrays.toString(a));
        }
    }

    public static void print(double[][] array) {
        for (double[] a : array) {
            System.out.println(Arrays.toString(a));
        }
    }


    int house = 2;
    int cell = 0;
    int wall = 1;
    int bus = 3;


    @Test
    void test() {
        solve(new int[][] {{2, 1, 3, 0}, {1, 1, 1, 1}, {0, 3, 0, 0}, {0, 0, 0, 2}});
    }




    public int solve(int[][] matrix) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == house) {
                    ans = Math.min(ans, bfs(matrix, new int[]{i, j}));
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    private int bfs(int[][] matrix, int[] start) {
        System.out.println(start[0] + "," + start[1]);
        Set<String> visisted = new HashSet<>();
        visisted.add(start[0] + "," + start[1]);
        int distance = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int[] current = queue.poll();
                int[] dir = new int[] {1, 0, -1, 0, 1};
                int r = current[0];
                int c = current[1];
                if (matrix[r][c] == bus) {
                    return distance + 1;
                }
                for (int j = 0; j < 4; j++) {
                    int nextr = r + dir[j];
                    int nextc = c + dir[j + 1];
                    if (nextr >= 0 && nextr < matrix.length && nextc >= 0 && nextc < matrix[0].length
                    ) {
                        System.out.println(nextr + "," + nextc + "," + matrix[nextr][nextc]);
                        if (matrix[nextr][nextc] == cell || matrix[nextr][nextc] == bus) {


                            if (visisted.add(nextr + "," + nextc)) {

                                queue.offer(new int[]{nextr, nextc});
                            }
                        }
                    }
                }
                size--;
            }
            distance++;

        }
        System.out.println(start[0] + "," + start[1] + "," + visisted);
        return Integer.MAX_VALUE;
    }
}
