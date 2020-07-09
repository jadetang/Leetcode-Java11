package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class _370_Range_Addition {

    @Test
    void test() {
        int[][] updates = new int[][]{{1, 3, 2}, {2, 4, 3}, {0, 2, -2}};
        assertArrayEquals(new int[] {-2, 0, 3, 5, 3}, getModifiedArray(5, updates));
    }
    public int[] getModifiedArray(int length, int[][] updates) {
        var array = new int[length];
        for (var update : updates) {
            array[update[0]] += update[2];
            if (update[1] < array.length - 1) {
                array[update[1] + 1] -= update[2];
            }
        }
        int current = 0;
        for (int i = 0; i < array.length; i++) {
            current += array[i];
            array[i] = current;
        }
        return array;
    }
}
