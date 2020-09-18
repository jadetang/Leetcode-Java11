package github.jadetang.other.google;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RotatedSlidingWindow {

    @Test
    void test() {
        assertEquals(4, maxPick(new int[]{-2, -1, 9, 0, -3, -4, 4}, 1));
        assertEquals(10, maxPick(new int[]{-2, -1, 9, 0, -3, -4, 4}, 4));
        assertEquals(5, maxPick(new int[]{-12, 1, 15, -6, 10, 10, 0, -3, 4, 4}, 3));
    }

    public int maxPick(int[] arr, int k) {
        return dp(arr, 0, arr.length - 1, k);
    }

    private int dp(int[] arr, int left, int right, int k) {
        if (left > right || k == 0) {
            return 0;
        }
        if (left == right) {
            return 1;
        }
        return Math.max(arr[left] + dp(arr, left + 1, right, k - 1),
                            arr[right] + dp(arr, left, right - 1, k - 1));
    }
}
