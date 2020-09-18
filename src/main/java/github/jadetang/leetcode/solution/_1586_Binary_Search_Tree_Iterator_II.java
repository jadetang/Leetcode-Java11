package github.jadetang.leetcode.solution;

import github.jadetang.leetcode.datastructure.TreeNode;
import java.util.ArrayList;
import java.util.Stack;
import org.junit.jupiter.api.Test;

public class _1586_Binary_Search_Tree_Iterator_II {

    @Test
    void test() {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        BSTIterator it = new BSTIterator(root);
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.prev());
        System.out.println(it.next());
        System.out.println(it.hasNext());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.hasNext());
        System.out.println(it.hasPrev());
        System.out.println(it.prev());
        System.out.println(it.prev());
    }

    public static class BSTIterator {

        ArrayList<Integer> pre = new ArrayList<>();

        int preIndex = -1;

        private boolean inRange(int i) {
            return i >= 0 && i < pre.size();
        }

        Stack<TreeNode> stack = new Stack<>();

        public BSTIterator(TreeNode root) {
            pushLeft(root);
        }

        void pushLeft(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        public boolean hasNext() {
            return inRange(preIndex + 1) || !stack.isEmpty();
        }

        public int next() {
            if (inRange(preIndex + 1)) {
                return pre.get(preIndex+++1);
            } else {
                TreeNode c = stack.pop();
                pre.add(c.val);
                pushLeft(c.right);
                preIndex++;
                return c.val;
            }
        }

        public boolean hasPrev() {
            return inRange(preIndex - 1);
        }

        public int prev() {
            return pre.get(--preIndex);
        }
    }
}
