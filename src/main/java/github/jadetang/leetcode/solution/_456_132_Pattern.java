package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.TreeMap;
import org.junit.jupiter.api.Test;

public class _456_132_Pattern {


    @Test
    void test() {
        assertFalse(find132pattern(new int[]{1, 2, 3, 4}));
    }

    public boolean find132pattern(int[] nums) {

        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(nums[i], min[i - 1]);
        }
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int i = 1; i < nums.length; i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > min[i]) {
                Integer largerThanMin = count.higherKey(min[i]);
                if (largerThanMin != null && largerThanMin < nums[i]) {
                    return true;
                }
            }
            count.put(nums[i], count.get(nums[i]) - 1);
            if (count.get(nums[i]) == 0) {
                count.remove(nums[i]);
            }
        }
        return true;
    }
}
