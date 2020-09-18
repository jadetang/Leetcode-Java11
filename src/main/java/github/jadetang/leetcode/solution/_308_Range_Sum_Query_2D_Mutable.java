package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class _308_Range_Sum_Query_2D_Mutable {

    @Test
    void test() {
        int[][] matrix = new int[][] {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(matrix);
       // assertEquals(8, numMatrix.sumRegion(2, 1, 4, 3));
        assertEquals(3, numMatrix.sumRegion(0, 0, 0, 0));
        assertEquals(14, numMatrix.sumRegion(0, 0, 1, 1));
        assertEquals(8, numMatrix.sumRegion(2, 1, 4, 3));
    }

    class NumMatrix {

        int r;

        int c;

        SegmentTree[] segmentTrees;

        public NumMatrix(int[][] matrix) {
            r = matrix.length;
            c = matrix[0].length;
            segmentTrees = new SegmentTree[r];
            for (int i = 0; i < r; i++) {
                segmentTrees[i] = new SegmentTree(matrix[i]);
            }
        }

        public void update(int row, int col, int val) {
            segmentTrees[row].update(col, val);
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                sum += segmentTrees[i].query(col1, col2);
            }
            return sum;
        }
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
            return new SegmentNode(left.result + right.result, l, r, left, right);
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
                return query(node.left, start, mid) + query(node.right, mid + 1, end);
            }
        }

        public void update(int index, int val) {
            update(root, index, val);
        }

        private void update(SegmentNode node, int index, int val) {
            if (node.l == index && node.r == index) {
                node.result = val;
                return;
            }
            int mid = node.l + (node.r - node.l) / 2;
            if (index <= mid) {
                update(node.left, index, val);
            }else {
                update(node.right, index, val);
            }
            node.result = node.left.result + node.right.result;
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
