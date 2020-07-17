package github.jadetang.other.flink;

import static org.junit.jupiter.api.Assertions.assertEquals;

import github.jadetang.leetcode.datastructure.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * A binary tree is called perfect if all of its nodes have exactly 0 or 2 children
 * and all of its leaves are at the same level.
 * You task is to calculate the size of the biggest perfect subtree that can be obtained by
 * removing any number of nodes.
 */
public class MaxSizeOfPerfectTree {

    @Test
    void test() {
        var root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(4);
        assertEquals(7, this.solution(root));
    }

    int ans = Integer.MIN_VALUE;

    public int solution(TreeNode t) {
        helper(t);
        return ans;
    }

    int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftSub = helper(node.left);
        int rightSub = helper(node.right);
        if (leftSub == rightSub) {
            ans = Math.max(ans, 1 + leftSub + rightSub);
            return 1 + leftSub + rightSub;
        }else {
            ans = Math.max(ans, 1 + 2 * Math.min(leftSub, rightSub));
            return 1 + 2 * Math.min(leftSub, rightSub);
        }
    }
}
