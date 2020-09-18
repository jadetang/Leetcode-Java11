package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class _1157_Online_Majority_Element_In_Subarray {

    @Test
    void test() {
        MajorityChecker m = new MajorityChecker(new int[]{1,1,2,2,1,1});
        assertEquals(1, m.query(0, 5, 4));
        assertEquals(-1, m.query(0, 3, 3));
        assertEquals(2, m.query(2, 3, 2));
    }

    class MajorityChecker {

        SegmentTree segmentTree;

        public MajorityChecker(int[] arr) {
            segmentTree = new SegmentTree(arr);
        }

        public int query(int left, int right, int threshold) {
            return segmentTree.query(left, right).entrySet()
                    .stream().filter(e -> e.getValue() >= threshold)
                    .findFirst().map(Entry::getKey).orElse(-1);
        }
    }

    public static class SegmentTree {

        SegmentNode root;

        public SegmentTree(int[] array) {
            root = build(array, 0, array.length - 1);
        }

        private SegmentNode build(int[] array, int start, int end) {
            if (start == end) {
                return new SegmentNode(Map.of(array[start], 1), start, end, null, null);
            }
            int mid = start + (end - start) / 2;
            SegmentNode left = build(array, start, mid);
            SegmentNode right = build(array, mid + 1, end);
            return new SegmentNode(merge(left.countMap, right.countMap), start, end, left, right);
        }

        private Map<Integer, Integer> merge(Map<Integer, Integer> leftMap, Map<Integer, Integer> rightMap) {
            Map<Integer, Integer> totalMap = new HashMap<>(leftMap);
            for (Entry<Integer, Integer> i : rightMap.entrySet()) {
                totalMap.put(i.getKey(), totalMap.getOrDefault(i.getKey(), 0) + i.getValue());
            }
            return totalMap;
        }

        public Map<Integer, Integer> query(int left, int right) {
            return query(root, left, right);
        }

        private Map<Integer, Integer> query(SegmentNode node, int left, int right) {
            if (node.start == left && node.end == right) {
                return node.countMap;
            }
            int mid = node.start + (node.end - node.start) / 2;
            if (right <= mid) {
                return query(node.left, left, right);
            }
            if (left >= mid + 1) {
                return query(node.right, left, right);
            }
            return merge(query(node.left, left, mid), query(node.right, mid + 1, right));
        }


    }

    public static class SegmentNode {

        Map<Integer, Integer> countMap;

        int start;

        int end;

        SegmentNode left;

        SegmentNode right;

        public SegmentNode(Map<Integer, Integer> countMap, int start, int end,
                SegmentNode left, SegmentNode right) {
            this.countMap = countMap;
            this.start = start;
            this.end = end;
            this.left = left;
            this.right = right;
        }
    }

}
