package github.jadetang.binarysearch;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Hoppable {

    @Test
    void test() {
        assertTrue(solve(new int[]{2, 4, 0, 1, 0}));
    }

    public boolean solve(int[] nums) {
        int maxReach = nums[0];
        int i = 0;
        while (i <= maxReach) {
            int nextMaxReach = Math.max(maxReach, nums[i] + i);
            if (nextMaxReach >= nums.length - 1) {
                return true;
            }
            if (nextMaxReach == maxReach) {
                break;
            }
            maxReach = nextMaxReach;
            i++;
        }
        return false;
    }
}
