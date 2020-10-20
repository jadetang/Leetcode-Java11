package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class _827_Making_A_Large_Island {

    @Test
    void test() {
        assertEquals(3, largestIsland(new int[][]{{1, 0}, {0, 1}}));
        assertEquals(4, largestIsland(new int[][]{{1, 0}, {1, 1}}));
        assertEquals(4, largestIsland(new int[][]{{1, 1}, {1, 1}}));
    }

    int[] dir = new int[] {1, 0, -1, 0, 1};

    int r;

    int c;
    public int largestIsland(int[][] grid) {
        this.r = grid.length;
        this.c = grid[0].length;
        boolean[][] visited = new boolean[r][c];
        UnionFind unionFind = new UnionFind(r * c);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j, unionFind, visited, grid);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    UnionFind clonedUf = clone(unionFind);
                    for (int k = 0; k < 4; k++) {
                        int nextr = i + dir[k];
                        int nextc = j + dir[k + 1];
                        if (nextr >= 0 && nextr < r && nextc >= 0 && nextc < c &&
                          grid[nextr][nextc] == 1) {
                            clonedUf.union(i * c + j, nextr * c + nextc);
                        }
                    }
                    ans = Math.max(ans, clonedUf.size[clonedUf.find(i * c + j)]);
                }
            }
        }
        if (ans != 0) {
            return ans;
        }
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    ans = Math.max(ans, unionFind.size[i * c + j]);
                }
            }
        }
        return ans;
    }


    UnionFind clone(UnionFind original) {
        return new UnionFind(original.array, original.size);
    }

    private void dfs(int row, int col, UnionFind unionFind, boolean[][] visited, int[][] grid) {
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dir[i];
            int nextCol = col + dir[i + 1];
            if (nextRow >= 0 && nextRow < r && nextCol >= 0 && nextCol < c && !visited[nextRow][nextCol] && grid[nextRow][nextCol] == 1) {
                visited[nextRow][nextCol] = true;
                unionFind.union(row * c + col, nextRow * c + nextCol);
                dfs(nextRow, nextCol, unionFind, visited, grid);
            }
        }
    }

    public static class UnionFind {
        public int[] array;
        public int[] size;

        public UnionFind(int[] array, int[] size) {
            this.array = Arrays.copyOf(array, array.length);
            this.size = Arrays.copyOf(size, array.length);
        }

        public UnionFind(int n) {
            array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = i;
            }
            size = new int[n];
            Arrays.fill(size, 1);
        }

        public int find(int n) {
            while (n != array[n]) {
                array[n] = array[array[n]];
                n = array[n];
            }
            return n;
        }

        public void union(int p, int q) {
            int proot = find(p);
            int qroot = find(q);
            if (proot != qroot) {
                if (size[proot] > size[qroot]) {
                    size[proot] += size[qroot];
                    array[qroot] = proot;
                } else {
                    size[qroot] += size[proot];
                    array[proot] = qroot;
                }
            }
        }

    }
}
