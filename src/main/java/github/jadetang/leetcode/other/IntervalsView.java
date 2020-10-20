package github.jadetang.leetcode.other;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;

public class IntervalsView {

    @Test
    void test() {
        List<Interval> bottom = List.of(new Interval(0,100, "red"));
        List<Interval> second = List.of(new Interval(2, 50, "blue"), new Interval(51, 53, "green"));
        List<Interval> third = List.of(new Interval(2, 50, "yellow"), new Interval(52, 53, "purple"));
        System.out.println(intervalView(new ArrayList<>(List.of(bottom, second, third))));
    }

    public List<Interval> intervalView(List<List<Interval>> intervals) {
        TreeMap<Integer, SimpleEntry<Integer, String>> map = new TreeMap<>();
      //  Collections.reverse(intervals);
        for (List<Interval> i : intervals) {
            for (Interval interval : i) {
                map.put(interval.start, new SimpleEntry<>(1, interval.color));
                map.put(interval.end, new SimpleEntry<>(-1, interval.color));
            }
        }
        Stack<String> stack = new Stack<>();
        Integer pre = null;
        List<Interval> ans = new ArrayList<>();
        for (Entry<Integer, SimpleEntry<Integer,String>> entry : map.entrySet()) {
            if (pre == null) {
                pre = entry.getKey();
                stack.push(entry.getValue().getValue());
            } else {
                int current = entry.getKey();
                if (entry.getValue().getKey() == 1) {
                    if (pre < current - 1) {
                        ans.add(new Interval(pre, current - 1, stack.peek()));
                    }
                    stack.push(entry.getValue().getValue());
                    pre = current;
                } else {
                    ans.add(new Interval(pre, current, stack.peek()));
                    stack.pop();
                    pre = current + 1;
                }
            }
        }
        return ans;
    }


    public static class Interval {
        public int start;
        public int end;
        public String color;

        public Interval(int start, int end, String color) {
            this.start = start;
            this.end = end;
            this.color = color;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Interval.class.getSimpleName() + "[", "]")
                    .add("start=" + start)
                    .add("end=" + end)
                    .add("color='" + color + "'")
                    .toString();
        }
    }
}
