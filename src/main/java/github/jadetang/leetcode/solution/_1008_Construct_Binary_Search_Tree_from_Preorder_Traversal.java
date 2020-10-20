package github.jadetang.leetcode.solution;

import github.jadetang.leetcode.datastructure.TreeNode;

public class _1008_Construct_Binary_Search_Tree_from_Preorder_Traversal {

    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder, 0, preorder.length - 1);
    }

    private TreeNode build(int[] p, int l, int r) {
        if (l > r) {
            return null;
        }
        if (l == r) {
            return new TreeNode(p[l]);
        }
        TreeNode node = new TreeNode(p[l]);
        int left = l + 1;
        int right = r + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (p[mid] > p[l]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        node.left = build(p, l + 1, left - 1);
        node.right = build(p, left, r);
        return node;
    }
}
