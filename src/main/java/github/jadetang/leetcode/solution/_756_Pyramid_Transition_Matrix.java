package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.junit.jupiter.api.Test;

public class _756_Pyramid_Transition_Matrix {

    @Test
    void test() {
        assertTrue(pyramidTransition("BCD", List.of("BCG", "CDE", "GEA", "FFF")));
    }

    @Test
    void test2() {
        assertFalse(pyramidTransition("BDD", List.of("ACC","AAD","ABB","DAB","DCD","ADA","BBC","CAB","BCA","DDC","BAC","BAA","DDB","CCD","CAA","DBC")));
    }

    Map<String, List<String>> graph = new HashMap<>();

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        buildGraph(allowed);
        Queue<String> queue = initialQueue(bottom);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Queue<String> tempQueue = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                String c = queue.poll();
                if (c.length() == 1) {
                    return true;
                }
                tempQueue.addAll(initialQueue(c));
            }
            queue = tempQueue;
        }
        return false;
    }

    private Queue<String> initialQueue(String bottom) {
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < bottom.length() - 1; i++) {
            String sub = bottom.substring(i, i + 2);
            Queue<String> tempQueue = new LinkedList<>();
            if (!graph.containsKey(sub)) {
                return new LinkedList<>();
            }
            for (String t : graph.get(sub)) {
                if (!queue.isEmpty()) {
                    for (String pre : queue) {
                        tempQueue.offer(pre + t);
                    }
                } else {
                    tempQueue.offer(t);
                }
            }
            queue = tempQueue;
        }
        return queue;
    }

    private void buildGraph(List<String> allowed) {
        for (String a : allowed) {
            String base = a.substring(0, 2);
            String top = a.substring(2);
            graph.computeIfAbsent(base, k -> new ArrayList<>()).add(top);
        }
    }
}
