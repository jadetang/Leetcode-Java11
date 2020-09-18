package github.jadetang.other.google;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ZeroFilledSubarrays {

    @Test
    void test() {
        assertEquals(26, subarrays(new int[][]{{2, 0, 0, 0, 0}, {0, 0, 0, 0, 1}}));
         assertEquals(9, subarrays(new int[][]{{0, 0}, {0, 0}}));
    }
    public int subarrays(int[][] arrays) {
        int ans = 0;
        ans += count(arrays[0]);
        ans += count(arrays[1]);
        for (int i = 0; i < arrays.length; ) {
            if (arrays[0][i] == 0 && arrays[1][i] == 0) {
                int j = i + 1;
                while (j < arrays[1].length  && arrays[0][j] == arrays[0][j - 1] && arrays[1][j] == arrays[1][j - 1]) {
                    j++;
                }
                int length = j - i;
                ans += (1 + length) * length / 2;
                i = j;
            } else {
                i++;
            }
        }
        return ans;
    }

    private int count(int[] array) {
        int ans = 0;
        for (int i = 0; i < array.length; ) {
            if (array[i] == 0) {
                int j = i;
                while (j < array.length - 1 && array[j] == array[j + 1]) {
                    j++;
                }
                int length = j - i + 1;
                ans += (1 + length) * length / 2;
                i = j + 1;
            } else {
                i++;
            }
        }
        return ans;
    }
}
