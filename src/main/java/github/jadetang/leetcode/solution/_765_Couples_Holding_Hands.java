package github.jadetang.leetcode.solution;

import java.util.HashMap;
import java.util.Map;

public class _765_Couples_Holding_Hands {

    public int minSwapsCouples(int[] row) {
        var uf = new UnionFind(row.length);
        for (int i = 0; i < row.length; i += 2) {
            uf.union(i, i + 1);
        }
        for (int i = 0; i < row.length; i += 2) {
            uf.union(row[i], row[i + 1]);
        }
        int ans = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < row.length; i++) {
            var root = uf.find(i);
            count.put(root, count.getOrDefault(root, 0) + 1);
        }
        return count.values().stream().map(n -> n / 2 - 1).reduce(Integer::sum).get();
    }

    public static class UnionFind {

        int[] array;

        public UnionFind(int n) {
            array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = i;
            }
        }

        int find(int n) {
            while (n != array[n]) {
                array[n] = array[array[n]];
                n = array[n];
            }
            return n;
        }

        void union(int i, int j) {
            int rooti = find(i);
            int rootj = find(j);
            if (rooti != rootj) {
                array[rootj] = rooti;
            }
        }
    }
}
