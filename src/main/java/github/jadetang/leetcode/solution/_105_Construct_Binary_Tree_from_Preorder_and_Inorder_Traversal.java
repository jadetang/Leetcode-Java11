package github.jadetang.leetcode.solution;

import github.jadetang.leetcode.datastructure.TreeNode;

public class _105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        return build(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int preOrderIndex, int inOrderStar, int inOrderEnd) {
        if (inOrderStar > inOrderEnd) {
            return null;
        }
        var root = new TreeNode(preorder[preOrderIndex]);
        int rootValue = preorder[preOrderIndex];
        int leftSubTreeLength = 0;
        for (int i = inOrderStar; i <= inOrderEnd; i++) {
            if (rootValue == inorder[i]) {
                break;
            }
            leftSubTreeLength++;
        }
        var left = build(preorder, inorder, preOrderIndex + 1, inOrderStar, inOrderStar + leftSubTreeLength - 1);
        var right = build(preorder, inorder, preOrderIndex + leftSubTreeLength + 1, inOrderStar + leftSubTreeLength + 1,
                inOrderEnd);
        root.left = left;
        root.right = right;
        return root;
    }
}
