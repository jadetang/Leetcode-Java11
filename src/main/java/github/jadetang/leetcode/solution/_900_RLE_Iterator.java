package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class _900_RLE_Iterator {

    @Test
    void test() {
        var q = new RLEIterator(new int[]{3, 8, 0, 9, 2, 5});
        assertEquals(8, q.next(2));
        assertEquals(8, q.next(1));
        assertEquals(5, q.next(1));
        assertEquals(-1, q.next(2));
    }

    public static class RLEIterator {

        int[] a;
        int remain;
        int index;

        public RLEIterator(int[] a) {
            this.a = a;
            this.remain = a[0];
            this.index = 0;
        }

        public int next(int n) {
            while (index < a.length - 1) {
                if (remain == n) {
                    int toReturn = a[index + 1];
                    index += 2;
                    if (index < a.length -1) {
                        remain = a[index];
                    }
                    return toReturn;
                }else if (remain > n) {
                    remain -= n;
                    return a[index + 1];
                }else {
                    n -= remain;
                    index += 2;
                    if (index < a.length - 1) {
                        remain = a[index];
                    }
                }
            }
            return -1;
        }
    }
}
