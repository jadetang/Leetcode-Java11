package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayDeque;
import java.util.Deque;
import org.junit.jupiter.api.Test;

public class _321_Create_Maximum_Number {

    @Test
    void test() {
        assertArrayEquals(new int[]{9, 8, 6, 5, 3}, maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5));
    }

    @Test
    void test2() {
        assertArrayEquals(new int[]{6, 7, 6, 0, 4}, maxNumber(new int[]{6, 7}, new int[]{6, 0, 4}, 5));
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] max = null;
        for (int i = 0; i <= k; i++) {
            int j = k - i;
            if (i <= nums1.length && j <= nums2.length) {
                int[] temp = merge(maxK(nums1, i), maxK(nums2, j));
                if (max == null) {
                    max = temp;
                } else {
                    max = max(max, temp);
                }
            }
        }
        return max;
    }

    private int[] max(int[] left, int[] right) {
        for (int i = 0; i < Math.min(left.length, right.length); i++) {
            if (left[i] > right[i]) {
                return left;
            }
            if (right[i] > left[i]) {
                return right;
            }
        }
        return left.length > right.length ? left : right;
    }

    private Deque<Integer> maxK(int[] n, int k) {
        int remove = n.length - k;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i : n) {
            while (!stack.isEmpty() && remove > 0 && stack.peek() < i) {
                stack.pop();
                remove--;
            }
            stack.push(i);
        }
        while (stack.size() > k) {
            stack.pop();
        }
        return stack;
    }

    private int[] merge(Deque<Integer> stack1, Deque<Integer> stack2) {
        int[] ans = new int[stack1.size() + stack2.size()];
        int index = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int a = stack1.isEmpty() ? Integer.MIN_VALUE : stack1.peekLast();
            int b = stack2.isEmpty() ? Integer.MIN_VALUE : stack2.peekLast();
            if (a > b) {
                ans[index++] = a;
                stack1.pollLast();
            } else if (a < b){
                ans[index++] = b;
                stack2.pollLast();
            } else if (stack1.size() >= stack2.size()) {
                ans[index++] = a;
                stack1.pollLast();
            } else {
                ans[index++] = b;
                stack2.pollLast();
            }
        }
        return ans;
    }
}
