package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class _1095_Find_in_Mountain_Array {


    @Test
    void test() {
      //  assertEquals(4, findPeak(new int[]{1, 2, 3, 4, 5, 3, 1}));
        assertEquals(3, findPeak(new int[]{1, 2, 3, 5, 3}));
    }

    int findPeak(int[] array) {
        int l = 0;
        int r = array.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mid == array.length - 1) {
                r = mid;
                continue;
            }
            if (mid == 0) {
                l = mid;
                continue;
            }
            int midValue = array[mid];
            int leftValue = array[mid - 1];
            int rightValue = array[mid + 1];
            if (midValue > leftValue && midValue > rightValue) {
                return mid;
            }
            if (midValue > leftValue && midValue < rightValue) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        throw new RuntimeException("Can not find summit");
    }
}
