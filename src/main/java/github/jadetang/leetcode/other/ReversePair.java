package github.jadetang.leetcode.other;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReversePair {

    @Test
    void test() {
        int[] array = new int[]{1, 23, 5};
        int reversePair = mergeSortAndCount(array);
        assertEquals(1, reversePair);
    }

    @Test
    void test2() {
        int[] array = new int[]{1, 23, 5, 6};
        int reversePair = mergeSortAndCount(array);
        assertEquals(2, reversePair);
    }

    public static int mergeSortAndCount(int[] sort) {
        return sortAndCount(new int[sort.length], sort, 0, sort.length - 1);
    }

    private static int sortAndCount(int[] aux, int[] array, int low, int high) {
        if (low >= high) {
            return 0;
        }
        int mid = low + (high - low) / 2;
        int a = sortAndCount(aux, array, low, mid);
        int b = sortAndCount(aux, array, mid + 1, high);
        return mergeAndCount(aux, array, low, mid, high) + a + b;
    }

    private static int mergeAndCount(int[] aux, int[] array, int low, int mid, int high) {
        int index = 0;
        for (int i = low; i <= high; i++) {
            aux[i] = array[i];
        }
        int reversePair = 0;
        int l = low;
        int r = mid + 1;
        for (int i = low; i <= high; i++) {
            if (l > mid) {
                array[i] = aux[r++];
            } else if (r > high) {
              //  reversePair += mid - l + 1;
                array[i] = aux[l++];
            } else if (aux[l] <= aux[r]) {
                array[i] = aux[l++];
            } else {
                reversePair += mid - l + 1;
                array[i] = aux[r++];
            }
        }
        return reversePair;
    }
}
