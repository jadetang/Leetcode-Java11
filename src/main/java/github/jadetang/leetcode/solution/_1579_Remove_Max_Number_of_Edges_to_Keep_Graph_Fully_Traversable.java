package github.jadetang.leetcode.solution;

public class _1579_Remove_Max_Number_of_Edges_to_Keep_Graph_Fully_Traversable {

    public static class UnionFind {

        public int edge;

        int[] array;

        public UnionFind(int n) {
            edge = 0;
            array = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                array[i] = i;
            }
        }

        public int find(int p) {
            while (array[p] != p) {
                array[p] = array[array[p]];
                p = array[p];
            }
            return p;
        }

        //return 1 indicate that a edge is removed.
        public int merge(int p, int q) {
            int rootp = find(p);
            int rootq = find(q);
            if (rootp == rootq) {
                return 1;
            } else {
                array[rootp] = rootq;
                edge++;
                return 0;
            }
        }
    }


    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind a = new UnionFind(n);
        UnionFind b = new UnionFind(n);
        int ans = 0;
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                ans += a.merge(edge[1], edge[2]);
                b.merge(edge[1], edge[2]);
            }
        }
        for (int[] edge : edges) {
            if (edge[0] != 3) {
                UnionFind d = edge[0] == 1 ? a : b;
                ans += d.merge(edge[1], edge[2]);
            }
        }
        return a.edge == n - 1 && b.edge == n - 1 ? n - ans : -1;
    }

}
