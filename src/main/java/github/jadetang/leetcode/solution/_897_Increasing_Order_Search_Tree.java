package github.jadetang.leetcode.solution;

import com.sun.source.tree.Tree;
import github.jadetang.leetcode.datastructure.TreeNode;
import org.junit.jupiter.api.Test;

public class _897_Increasing_Order_Search_Tree {

    @Test
    void test() {
        TreeNode n = new TreeNode(2);
        n.left = new TreeNode(1);
        n.right = new TreeNode(3);
        TreeNode left = increasingBST(n);
    }

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = increasingBST(root.left);
        root.right = increasingBST(root.right);
        if (left == null) {
            return root;
        } else {
            TreeNode rightMost = findRightMost(left);
            rightMost.right = root;
            return left;
        }
    }

    private TreeNode findRightMost(TreeNode n) {
        if (n.right == null) {
            return n;
        }
        return findRightMost(n.right);
    }
}
