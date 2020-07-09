package github.jadetang.leetcode.solution;

import github.jadetang.leetcode.datastructure.TreeNode;

public class _106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }
        return build(inorder, postorder, 0, inorder.length - 1, postorder.length - 1);
    }

    private TreeNode build(int[] inorder, int[] postorder, int inorderStart, int inorderEnd, int postorderIndex) {
        if (inorderStart > inorderEnd) {
            return null;
        }
        int rootValue = postorder[postorderIndex];
        var root = new TreeNode(rootValue);
        var rightSubTreeLength = 0;
        for (int i = inorderEnd; i >= inorderStart; i--) {
            if (inorder[i] == rootValue) {
                break;
            }
            rightSubTreeLength++;
        }
        var right = build(inorder, postorder,inorderEnd - rightSubTreeLength + 1, inorderEnd, postorderIndex - 1);
        var left = build(inorder, postorder, inorderStart, inorderEnd - rightSubTreeLength - 1, postorderIndex - rightSubTreeLength - 1 );
        root.left = left;
        root.right = right;
        return root;
    }
}
