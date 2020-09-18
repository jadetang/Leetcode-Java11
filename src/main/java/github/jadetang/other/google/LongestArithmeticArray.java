package github.jadetang.other.google;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestArithmeticArray {

    @Test
    void test() {
        assertEquals(4, solution(new int[]{10, 7, 4, 6, 8, 10, 11}));
        assertEquals(4, solution(new int[]{9, 7, 5, 3}));
        assertEquals(3, solution(new int[]{5, 5, 4, 5, 5, 5, 4, 5, 6}));
        assertEquals(6, solution(new int[]{5, 4, 3, 2, 1, 2, 3, 4, 5, 6}));
    }

    static int solution(int[] a) {
        if (a.length == 1 || a.length == 0) {
            return a.length;
        }
        int[] diff = new int[a.length - 1];
        for (int i = 0; i < diff.length; i++) {
            diff[i] = a[i + 1] - a[i];
        }
        return longest(diff) + 1;
    }

    static int longest(int[] diff) {
        int longest = 1;
        int i = 0;
        while (i < diff.length) {
            int j = i;
            while (j < diff.length - 1 && diff[j] == diff[j + 1]) {
                j++;
            }
            longest = Math.max(longest, j - i + 1);
            i = j + 1;
        }
        return longest;
    }
}
