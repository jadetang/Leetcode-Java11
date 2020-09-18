package github.jadetang.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class _163_Missing_Ranges {

    @Test
    void test() {
        System.out.println(findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 99));
        System.out.println(findMissingRanges(new int[]{-1}, -2, -1));
        System.out.println(findMissingRanges(new int[]{-1}, -1, -1));
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        if (nums.length == 0) {
            return List.of(new Internal(lower, upper).toString());
        } else {
            int startIndex = Arrays.binarySearch(nums, lower);
            Integer pre = null;
            if (startIndex < 0) {
                pre = lower;
                startIndex = -startIndex - 1;
            }
            int endIndex = Arrays.binarySearch(nums, upper);
            endIndex = endIndex < 0 ? -endIndex - 1 : endIndex;
            List<Internal> intervals = new ArrayList<>();
            for (int i = startIndex; i < Math.min(endIndex, nums.length); i++) {
                if (pre != null) {
                    if (pre + 1 <= nums[i] - 1) {
                        intervals.add(new Internal(pre + 1, nums[i] - 1));
                    }
                }
                pre = nums[i];
            }
            if (endIndex == nums.length - 1 || endIndex == nums.length) {
                intervals.add(new Internal(pre + 1, upper));
            }
            return intervals.stream().map(Internal::toString).collect(Collectors.toList());
        }
    }

    public static class Internal {

        int start;
        int end;

        public Internal(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            if (start == end) {
                return start + "";
            }
            return start + "->" + end;
        }
    }
}
