package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class _684_Redundant_Connection {

    @Test
    void test() {
        assertArrayEquals(new int[]{2, 3}, findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}}));
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n);
        int[] ans = null;
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                ans = edge;
            }
        }
        return ans;
    }

    public static class UnionFind {

        int[] p;

        public UnionFind(int n) {
            p = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                p[i] = i;
            }
        }

        public int find(int n) {
            while(n != p[n]) {
                p[n] = p[p[n]];
                n = p[n];
            }
            return n;
        }

        public boolean union(int l, int r) {
            int lRoot = find(l);
            int rRoot = find(r);
            if (lRoot == rRoot) {
                return false;
            } else {
                p[lRoot] = rRoot;
                return true;
            }
        }
    }
}
