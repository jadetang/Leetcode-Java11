package github.jadetang.leetcode.other;

import static org.junit.jupiter.api.Assertions.assertTrue;

import github.jadetang.leetcode.datastructure.TreeNode;
import org.junit.jupiter.api.Test;

public class TreeColoring {

    @Test
    void test() {
        TreeNode node = new TreeNode(3);
        node.right = new TreeNode(3);
        node.right.right = new TreeNode(4);
        assertTrue(solve(node));
    }


    Integer aValue;
    int acount = 0;
    Integer bValue;
    int bcount = 0;
    public boolean solve(TreeNode root) {
        if (root == null) {
            return true;
        }
        count(root);
        if (bValue == null) {
            return acount == 1;
        }
        return traverse(root, aValue, new int[]{acount}, new int[]{bcount}) || traverse(root, bValue, new int[]{acount}, new int[]{bcount});
    }

    private boolean traverse(TreeNode node, int parentValue, int[] acount, int[] bcount) {
        if (node == null) {
            return true;
        }
        int currentValue = parentValue == aValue ? bValue : aValue;
        if (currentValue == aValue) {
            acount[0]--;
            if (acount[0] < 0) {
                return false;
            }
        }
        if (currentValue == bValue) {
            bcount[0]--;
            if (bcount[0] < 0) {
                return false;
            }
        }
        return traverse(node.left, currentValue, acount, bcount) && traverse(node.right, currentValue, acount, bcount);
    }



    private void count(TreeNode node) {
        if (node == null) {
            return;
        }
        if (aValue == null) {
            aValue = node.val;
            acount++;
        } else if (aValue != node.val && bValue == null) {
            bValue = node.val;
            bcount++;
        } else if (aValue == node.val) {
            acount++;
        } else {
            bcount++;
        }
        count(node.left);
        count(node.right);
    }
}
