package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class _1521_Find_a_Value_of_a_Mysterious_Function_Closest_to_Target {

    @Test
    void test() {
        assertEquals(2, closestToTarget(new int[]{9,12,3,7,15}, 5));
    }

    public int closestToTarget(int[] arr, int target) {
        SegmentTree segmentTree = new SegmentTree(arr);
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        for (; right < arr.length; right++) {
            int t = segmentTree.query(left, right);
            ans = Math.min(ans, Math.abs(t - target));
            while (left <= right && t < target) {
                t = segmentTree.query(left, right);
                ans = Math.min(ans, Math.abs(t - target));
                left++;
            }
        }
        return ans;
    }


    public static class SegmentTree {

        SegmentNode root;

        public SegmentTree(int[] arr) {
            this.root = build(arr, 0, arr.length - 1);
        }

        private SegmentNode build(int[] arr, int l, int r) {
            if (l == r) {
                return new SegmentNode(arr[l], l, r, null, null);
            }
            int mid = l + (r - l) / 2;
            SegmentNode left = build(arr, l, mid);
            SegmentNode right = build(arr, mid + 1, r);
            return new SegmentNode(left.result & right.result, l, r, left, right);
        }

        public int query(int l, int r) {
            return query(root, l, r);
        }

        private int query(SegmentNode node, int start, int end) {
            if (node.l == start && node.r == end) {
                return node.result;
            }
            int mid = node.l + (node.r - node.l) / 2;
            if (end <= mid) {
                return query(node.left, start, end);
            }else if (start >= mid + 1) {
                return query(node.right, start, end);
            }else {
                return query(node.left, start, mid) & query(node.right, mid + 1, end);
            }
        }
    }

    public static class SegmentNode {

        int result;

        int l;

        int r;

        SegmentNode left;

        SegmentNode right;

        public SegmentNode(int result, int l, int r,
                SegmentNode left,
                SegmentNode right) {
            this.result = result;
            this.l = l;
            this.r = r;
            this.left = left;
            this.right = right;
        }
    }
}
