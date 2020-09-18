package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class _493_Reverse_Pairs {

    @Test
    void test() {
        assertEquals(2, reversePairs(new int[]{1, 3, 2, 3, 1}));
        assertEquals(3, reversePairs(new int[]{2, 4, 3, 5, 1}));
    }

    public int reversePairs(int[] nums) {
        return mergeSortAndCount(nums);
    }

    public  int mergeSortAndCount(int[] sort) {
        return sortAndCount(new int[sort.length], sort, 0, sort.length - 1);
    }

    private  int sortAndCount(int[] aux, int[] array, int low, int high) {
        if (low >= high) {
            return 0;
        }
        int mid = low + (high - low) / 2;
        int a = sortAndCount(aux, array, low, mid);
        int b = sortAndCount(aux, array, mid + 1, high);
        int j = mid + 1;
        int count = a + b;
        for (int i = low; i <= mid; i++) {
            while (j <= high && (long)array[i] > (long)array[j] * 2L) {
                j++;
            }
            count += j - (mid + 1);
        }
        mergeAndCount(aux, array, low, mid, high);
        return count;
    }

    private  int mergeAndCount(int[] aux, int[] array, int low, int mid, int high) {
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
                array[i] = aux[l++];
            } else if (aux[l] < aux[r]) {
                array[i] = aux[l++];
            } else {
                array[i] = aux[r++];
            }
        }
        return reversePair;
    }
}
