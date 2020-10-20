package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class _1610_Maximum_Number_of_Visible_Points {

    @Test
    void test() {
        assertEquals(1, visiblePoints(List.of(List.of(1, 0), List.of(2, 1)), 13, List.of(1, 1)));
    }

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> degrees = new ArrayList<>();
        int same = 0;
        for (List<Integer> p : points) {
            int x = p.get(0) - location.get(0);
            int y = p.get(1) - location.get(1);
            if (x == 0 && y == 0) {
                same++;
            } else {
                double degree = Math.toDegrees(Math.atan2(y, x));
                degrees.add(degree);
                degrees.add(degree + 360);
            }
        }
        Collections.sort(degrees);
        System.out.println(degrees);
        int ans = 0;
        Queue<Double> queue = new LinkedList<>();
        for (Double d : degrees) {
            if (queue.size() == 0) {
                queue.offer(d);
                continue;
            } else {
                while (!queue.isEmpty() && d - queue.peek() > (double)angle) {
                    queue.poll();
                }
            }
            queue.offer(d);
            ans = Math.max(ans, queue.size());
        }
        return ans + same;
    }
}
