package github.jadetang.other.google.kickstart.f;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Stack;
import org.junit.jupiter.api.Test;

public class MetalHarvest {

    @Test
    void test() {
        assertEquals(2, solution(3, 5, new int[][]{{1, 5}, {10, 11}, {8, 9}}));
        assertEquals(3, solution(3, 2, new int[][]{{1, 2}, {3, 5}, {13, 14}}));
        assertEquals(2, solution(3, 12, new int[][]{{1, 2}, {3, 5}, {13, 14}}));
        assertEquals(1, solution(3, 13, new int[][]{{1, 2}, {3, 5}, {13, 14}}));
        assertEquals(3, solution(1, 2, new int[][]{{1, 6}}));
        assertEquals(2, solution(1, 2, new int[][]{{1, 5}}));
        assertEquals(3, solution(1, 2, new int[][]{{1, 6}, {6, 7}}));
        assertEquals(5, solution(1, 2, new int[][]{{1, 6}, {6, 7}, {7, 10}}));
        assertEquals(5, solution(1, 2, new int[][]{{1, 6}, {7, 10}}));
    }

    @Test
    void test2() {
        assertEquals(3 , solution(1, 4, new int[][]{{1, 10}}));
    }


    public static int solution(int n, int k, int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> i1[0] == i2[0] ? i2[1] - i1[1] : i1[0] - i2[0]);
        int [] robot = null;
        Stack<int[]> stack = new Stack<>();
        for (int i = intervals.length - 1; i >= 0; i--) {
            stack.push(intervals[i]);
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            if (robot == null) {
                ans++;
                robot = new int[] {current[0], current[0] + k};
            }
            if (robot[1] <= current[0]) {
                robot = null;
                stack.push(current);
                continue;
            }
            if (robot[1] < current[1]) {
                int length = current[1] - current[0];
                int robotLength = robot[1] - robot[0];
                int leftLength = length - robotLength;
                if (leftLength == 0) {
                    continue;
                }
                ans += leftLength / k;
                if (leftLength % k != 0) {
                    stack.push(new int[]{current[1] - leftLength % k, current[1]});
                }
                robot = null;
            } else if (robot[1] == current[1]) {
                robot = null;
            } else {
                robot = new int[] {current[1], robot[1]};
            }
        }
        return ans;
    }
}
