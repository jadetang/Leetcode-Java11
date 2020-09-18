package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.jupiter.api.Test;

public class _1298_Maximum_Candies_You_Can_Get_from_Boxes {

    @Test
    void test() {
        assertEquals(16, maxCandies(new int[]{1, 0, 1, 0}, new int[]{7, 5, 4, 100}, new int[][]{{}, {}, {1}, {}},
                new int[][]{{1, 2}, {3}, {}, {}}, new int[]{0}));
    }

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        boolean[] findBox = new boolean[status.length];
        boolean[] haveKeys = new boolean[status.length];

        int ans = 0;

        Queue<Integer> queue = new LinkedList<>();

        for(int box : initialBoxes) {
            if (status[box] == 1) {
                queue.offer(box);
            } else {
                findBox[box] = true;
            }
        }

        while (!queue.isEmpty()) {
            int openedBox = queue.poll();
            System.out.println(openedBox);
            ans += candies[openedBox];
            for (int key : containedBoxes[openedBox]) {
                haveKeys[key] = true;
                if (findBox[key]) {
                    queue.add(key);
                }
            }
            for (int foundBox : containedBoxes[openedBox]) {
                if (status[foundBox] == 1) {
                    queue.offer(foundBox);
                } else if (haveKeys[foundBox]) {
                    queue.offer(foundBox);
                } else {
                    findBox[foundBox] = true;
                }
            }
        }
        return ans;
    }
}
