package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class _255_Verify_Preorder_Sequence_in_Binary_Search_Tree {

    @Test
    void test() {
        assertTrue(verifyPreorder(new int[]{5, 2, 1, 3, 6}));
    }

    @Test
    void test2() {
        assertFalse(verifyPreorder(new int[]{5, 2, 6, 1, 3}));
    }

    public boolean verifyPreorder(int[] preorder) {
        return verify(preorder, 0, preorder.length - 1);
    }

    boolean verify(int[] p, int l, int r) {
        if (l >= r) {
            return true;
        } else {
            int root = p[l];
            int right = findLarger(root, p, l + 1, r);
            for (int i = right; i <= r; i++) {
                if (p[i] < root) {
                    return false;
                }
            }
            for (int i = l; i < right; i++) {
                if (p[i] > root) {
                    return false;
                }
            }
            return verify(p, l + 1, right - 1) && verify(p, right, r);
        }
    }

    private int findLarger(int pivot, int[] p, int start, int end) {
        int l = start;
        int r = end + 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (p[mid] > pivot) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
