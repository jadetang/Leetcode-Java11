package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class _911_Online_Election {

    @Test
    void test() {
        var t = new TopVotedCandidate(new int[]{0, 1, 1, 0, 0, 1, 0}, new int[]{0, 5, 10, 15, 20, 25, 30});
        assertEquals(0, t.q(3));
        assertEquals(1, t.q(5));
    }

    @Test
    void test2() {
        var t = new TopVotedCandidate(new int[]{0, 0, 0, 0, 1}, new int[]{0, 6, 39, 52, 75});
        assertEquals(0, t.q(78));
        assertEquals(0, t.q(45));
        assertEquals(0, t.q(49));
        assertEquals(0, t.q(59));
        assertEquals(0, t.q(68));
        assertEquals(0, t.q(42));
        assertEquals(0, t.q(37));
        assertEquals(0, t.q(99));
        assertEquals(0, t.q(26));
        assertEquals(0, t.q(43));
    }

    public static class TopVotedCandidate {

        int[] topCandidate;

        int[] times;

        public TopVotedCandidate(int[] persons, int[] times) {
            topCandidate = new int[times.length];
            this.times = times;
            Map<Integer, Integer> votes = new HashMap<>();
            Integer topSoFar = null;
            for (int i = 0; i < times.length; i++) {
                int person = persons[i];
                votes.put(person, votes.getOrDefault(person, 0) + 1);
                int v = votes.get(person);
                if (topSoFar == null || v >= votes.get(topSoFar)) {
                    topSoFar = person;
                }
                topCandidate[i] = topSoFar;
            }
        }

        public int q(int t) {
            int index = Arrays.binarySearch(times, t);
            if (index < 0) {
                index = -index - 2;
            }
            return topCandidate[index];
        }
    }
}
