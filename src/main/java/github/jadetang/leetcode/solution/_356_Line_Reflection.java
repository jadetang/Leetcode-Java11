package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class _356_Line_Reflection {

    @Test
    void test() {
        assertTrue(isReflected(new int[][]{{1, 1}, {0 ,0}, {-1, 1}}));
    }

    public boolean isReflected(int[][] points) {
        if (points.length == 1) {
            return true;
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] p : points) {
            map.computeIfAbsent(p[1], k -> new HashSet<>()).add(p[0]);
        }
        Double x = null;
        for (Set<Integer> pointsList : map.values()) {
            Optional<Double> midPoint = findMidPoint(pointsList);
            if (midPoint.isEmpty()) {
                return false;
            } else if (x == null) {
                x = midPoint.get();
            } else if (!x.equals(midPoint.get())) {
                return false;
            }
        }
        return true;
    }

    Optional<Double> findMidPoint(Set<Integer> points) {
        if (points.size() == 1) {
            Double mid = (double)points.iterator().next();
            return Optional.of(mid);
        }
        Integer largest = null;
        Integer smallest = null;
        for (int p : points) {
            if (largest == null) {
                largest = p;
                smallest = p;
            }
            largest = Math.max(largest, p);
            smallest = Math.min(smallest, p);
        }
        System.out.println(largest + ":" + smallest);
        double mid = (double)smallest + (largest - smallest) / 2.0D;
        System.out.println(mid);
        Map<Double, Integer> count = new HashMap<>();
        for (int p : points) {
            double dist = mid - (double)p;
            if (dist > 0) {
                count.put(dist, count.getOrDefault(dist, 0) + 1);
            } else if (dist < 0) {
                count.put(-dist, count.getOrDefault(-dist, 0) - 1);
            }
        }
        System.out.println(count);
        for (int p : count.values()) {
            if (p != 0) {
                return Optional.empty();
            }
        }
        return Optional.of(mid);
    }
}
