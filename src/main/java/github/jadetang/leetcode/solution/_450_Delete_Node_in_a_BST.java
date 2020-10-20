package github.jadetang.leetcode.solution;

import github.jadetang.leetcode.datastructure.TreeNode;
import org.junit.jupiter.api.Test;

public class _450_Delete_Node_in_a_BST {

    @Test
    void test() {
        deleteNode(new TreeNode(0), 0);
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if(root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode t = root;
            root = min(t.right);
            root.right = deleteMin(t.right);
            root.left = t.left;
            //return min;
        }
        return root;
    }

    TreeNode deleteMin(TreeNode node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    TreeNode min(TreeNode node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }
}
