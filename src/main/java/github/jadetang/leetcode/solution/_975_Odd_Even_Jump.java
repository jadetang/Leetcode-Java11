package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;

public class _975_Odd_Even_Jump {

    @Test
    void test() {
        assertEquals(2, oddEvenJumps(new int[]{10, 13, 12, 14, 15}));
    }

    public int oddEvenJumps(int[] a) {
        int[] larger = new int[a.length];
        Arrays.fill(larger, Integer.MAX_VALUE);
        TreeMap<Integer, Integer> maxTreeMap = new TreeMap<>();
        maxTreeMap.put(a[a.length - 1], a.length - 1);
        for (int i = a.length - 2; i >= 0; i--) {
            Integer largeKey = maxTreeMap.ceilingKey(a[i]);
            if (largeKey != null) {
                larger[i] = maxTreeMap.get(largeKey);
            }
            maxTreeMap.put(a[i], i);
        }

        int[] smaller = new int[a.length];
        Arrays.fill(smaller, Integer.MAX_VALUE);
        TreeMap<Integer, Integer> minTreeMap = new TreeMap<>();
        minTreeMap.put(a[a.length - 1], a.length - 1);
        for (int i = a.length - 2; i >= 0; i--) {
            Integer smallerKey = minTreeMap.floorKey(a[i]);
            if (smallerKey != null) {
                smaller[i] = minTreeMap.get(smallerKey);
            }
            minTreeMap.put(a[i], i);
        }


        int ans = 0;
        int[][] cache = new int[a.length][2];
        for (int[] c : cache) {
            Arrays.fill(c, -1);
        }
        for (int i = 0; i < a.length; i++) {
            if (canJump(i, larger, smaller, a, 1, cache)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean canJump(int index, int[] larger, int[] smaller, int[] a, int jump, int[][] cache) {
        if (cache[index][jump] != -1) {
            return cache[index][jump] == 1;
        }
        if (index == a.length - 1) {
            return true;
        }
        int nextIndex = -1;
        if (jump == 1) {
             nextIndex = larger[index];
        }else {
             nextIndex = smaller[index];
        }
        if (nextIndex == Integer.MAX_VALUE) {
            return false;
        }else {
            boolean canJump = canJump(nextIndex, larger, smaller, a, jump ^ 1, cache);
            cache[index][jump] = canJump ? 1 : 0;
            return canJump;
        }
    }
}
