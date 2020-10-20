package github.jadetang.leetcode.solution;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class _55_Jump_Game {

    @Test
    void test() {
        Assertions.assertFalse(canJump(new int[]{3, 2, 1, 0, 4}));
        Assertions.assertTrue(canJump(new int[]{2, 3, 1, 1, 4}));
        Assertions.assertTrue(canJump(new int[]{5,9,3,2,1,0,2,3,3,1,0,0}));
    }

    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int i = 0;
        while (i < nums.length && nums[i] > 0) {
            int max = i + nums[i];
            for (int j = i; j <= max && j < nums.length; j++) {
                i = Math.max(i, nums[j] + j);
            }
        }
        System.out.println(i);
        return i >= nums.length - 1;
    }

    public boolean canJump2(int[] nums) {
        var canReach = new boolean[nums.length];
        canReach[0] = true;
        for (int i = 0; i < nums.length; i++) {
            if (!canReach[i]) {
                return false;
            }
            for (int j = i; j <= i + nums[i] && j < nums.length; j++) {
                canReach[j] = true;
            }
        }
        return canReach[nums.length - 1];
    }
}
