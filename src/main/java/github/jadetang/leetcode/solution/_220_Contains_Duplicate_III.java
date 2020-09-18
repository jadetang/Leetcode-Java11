package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.TreeSet;
import org.junit.jupiter.api.Test;

public class _220_Contains_Duplicate_III {

    @Test
    void test() {
        assertTrue(containsNearbyAlmostDuplicate(new int[]{1, 1, 1, 1, 0}, 3, 1));
    }


    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        Long tLong = Long.valueOf(t);
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            long c = Long.valueOf(nums[i]);
            Long big = set.ceiling(c);
            if (big != null && Math.abs(big -c) <= tLong) {
                return true;
            }
            Long small = set.floor(c);
            if (small != null && Math.abs(small - c) <= tLong) {
                return true;
            }
            set.add(c);
            if (set.size() > k) {
                set.remove(Long.valueOf(nums[i - k]));
            }
        }
        return false;
    }
}
