package github.jadetang.leetcode.other;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class MergeShort {

    @Test
    void test() {
        int[] array = new int[]{1, 23, 5, 2, 10, 3};
        int[] copy = Arrays.copyOf(array, array.length);
        mergeSort(array);
        Arrays.sort(copy);
        assertArrayEquals(copy, array);
    }

    @Test
    void test2() {
        int[] array = new int[]{1, 23, 5, 6};
        int[] copy = Arrays.copyOf(array, array.length);
        mergeSort(array);
        Arrays.sort(copy);
        assertArrayEquals(copy, array);
    }

    public static void mergeSort(int[] sort) {
        sort(new int[sort.length], sort, 0, sort.length - 1);
    }

    private static void sort(int[] aux, int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(aux, array, low, mid);
        sort(aux, array, mid + 1, high);
        merge(aux, array, low, mid, high);
    }

    private static void merge(int[] aux, int[] array, int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            aux[i] = array[i];
        }
        int l = low;
        int r = mid + 1;
        for (int i = low; i <= high; i++) {
            if (l > mid) {
                array[i] = aux[r++];
            } else if (r > high) {
                array[i] = aux[l++];
            } else if (aux[l] <= aux[r]) {
                array[i] = aux[l++];
            } else {
                array[i] = aux[r++];
            }
        }
    }
}
