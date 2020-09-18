package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import github.jadetang.leetcode.datastructure.TreeNode;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class _1305_All_Elements_in_Two_Binary_Search_Trees {

    @Test
    void test() {
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(0);
        root2.right = new TreeNode(3);
        assertEquals(List.of(0,1,1,2,3,4), getAllElements(root1, root2));
    }

    List<Integer> ans = new ArrayList<>();

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        collect(root1, root2);
        return ans;
    }

    private void collect(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return;
        }
        if (root1 == null && root2 != null) {
            ans.add(root2.val);
            return;
        }
        if (root1 != null && root2 == null) {
            ans.add(root1.val);
            return;
        }
        if (root1.val == root2.val) {
            collect(root1.left, root2.left);
            ans.add(root1.val);
            ans.add(root1.val);
            collect(root1.right, root2.right);
        }else if (root1.val < root2.val) {
            collect(root1, root2.left);
            collect(root1.left, root2);
        }else {
            collect(root1.left, root2);
            collect(root1, root2.right);
        }
    }
}
