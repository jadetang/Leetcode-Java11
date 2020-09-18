package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class _952_Largest_Component_Size_by_Common_Factor {


    @Test
    void test() {
        assertEquals(8, largestComponentSize(new int[]{2, 3, 6, 7, 4, 12, 21, 39}));
    }

    public int largestComponentSize(int[] a) {
        int largest = IntStream.of(a).max().getAsInt();
        UnionFind unionFind = new UnionFind(largest + 1);
        for (int i : a) {
            for (int factor = 2; factor <= Math.sqrt(i); factor++) {
                if (i % factor == 0) {
                    unionFind.union(factor, i);
                    unionFind.union(i / factor, i);
                }
            }
        }
        int max = 0;
        Map<Integer, Integer> counter = new HashMap<>();
        for (int i : a) {
            int root = unionFind.find(i);
            counter.put(root, counter.getOrDefault(root, 0) + 1);
            max = Math.max(counter.get(root), max);
        }
        return max;
    }

    public static class UnionFind {

        int[] array;

        int[] size;

        public UnionFind(int n) {
            array = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = i;
            }
            Arrays.fill(size, 1);
        }

        public int find(int p) {
            while (p != array[p]) {
                array[p] = array[array[p]];
                p = array[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootp = find(p);
            int rootq = find(q);
            if (rootp != rootq) {
                if (size[rootp] > size[rootq]) {
                    size[rootp] += size[rootq];
                    array[rootq] = rootp;
                }else {
                    size[rootq] += size[rootp];
                    array[rootp] = rootq;
                }
            }
        }

        public int size(int p) {
            return size[find(p)];
        }
    }
}
