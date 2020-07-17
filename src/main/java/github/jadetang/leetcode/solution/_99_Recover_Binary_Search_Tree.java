package github.jadetang.leetcode.solution;

import github.jadetang.leetcode.datastructure.TreeNode;

public class _99_Recover_Binary_Search_Tree {

    public void recoverTree(TreeNode root) {
        TreeNode pre = null;
        TreeNode q = null;
        TreeNode p = null;
        var cur = root;
        while (cur != null) {
            if (cur.left == null) {
                if (pre != null && pre.val >= cur.val) {
                    if (q == null) {
                        q = pre;
                    }
                    p = cur;
                }
                pre = cur;
                cur = cur.right;
            }else {
                TreeNode visitPre = cur.left;
                while (visitPre.right != null && visitPre.right != cur) {
                    visitPre = visitPre.right;
                }
                if (visitPre.right == null) {
                    visitPre.right = cur;
                    cur = cur.left;
                }
                if (visitPre.right == cur) {
                    visitPre.right = null;
                    if (pre != null && pre.val >= cur.val) {
                        if (q == null) {
                            q = pre;
                        }
                        p = cur;
                    }
                    pre = cur;
                    cur = cur.right;
                }
            }
        }
        if (p != null && q != null) {
            int t = p.val;
            p.val = q.val;
            q.val = t;
        }
    }
}
