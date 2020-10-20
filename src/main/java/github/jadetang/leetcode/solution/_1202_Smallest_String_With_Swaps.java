package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.print.DocFlavor.STRING;
import org.junit.jupiter.api.Test;

public class _1202_Smallest_String_With_Swaps {

    @Test
    void test() {
        assertEquals("bacd", smallestStringWithSwaps("dcab", List.of(List.of(0, 3), List.of(1, 2))));
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        UnionFind u = new UnionFind(s.length());
        for (List<Integer> pair : pairs) {
            u.union(pair.get(0), pair.get(1));
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int root = u.find(i);
            map.computeIfAbsent(root, r -> new ArrayList<>()).add(i);
        }
        Map<Integer, Iterator<Character>> sortedString =
        map.entrySet().stream().filter(e -> e.getValue().size() > 0)
                .map(e -> new SimpleEntry<>(e.getKey(),
                        e.getValue().stream().map(s::charAt).sorted().iterator()
                )).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (sortedString.containsKey(u.find(i))) {
                sb.append(sortedString.get(u.find(i)).next());
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static class UnionFind {

        int[] array;

        public UnionFind(int n) {
            array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = i;
            }
        }

        public int find(int n) {
            while (n != array[n]) {
                array[n] = array[array[n]];
                n = array[n];
            }
            return n;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot != qRoot) {
                array[pRoot] = qRoot;
            }
        }
    }
}
