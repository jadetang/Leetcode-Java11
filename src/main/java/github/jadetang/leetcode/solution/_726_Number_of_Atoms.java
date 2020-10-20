package github.jadetang.leetcode.solution;

import static java.lang.Character.digit;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;

public class _726_Number_of_Atoms {

    @Test
    void test() {
   //     System.out.println(countOfAtoms("Mg(OH)2"));
        System.out.println(countOfAtoms("K4(ON(SO3)2)2"));
        System.out.println(countOfAtoms("Be32"));
    }

    public String countOfAtoms(String formula) {
        Stack<Counter> stack = new Stack<>();
        stack.push(new Counter());
        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);
            if (isUpperCase(c)) {
                var sb = new StringBuilder();
                sb.append(c);
                int j = i + 1;
                while (j < formula.length() && isLowerCase(formula.charAt(j))) {
                    sb.append(formula.charAt(j));
                    j++;
                }
                stack.peek().add(sb.toString(), 1);
                i = j - 1;
            } else if (isDigit(c)) {
                var times = digit(c, 10);
                int j = i + 1;
                while (j < formula.length() && isDigit(formula.charAt(j))) {
                    times = times * 10 + digit(formula.charAt(j), 10);
                    j++;
                }
                i = j - 1;
                stack.peek().add(stack.peek().recentKey, times - 1);
            } else if (c == '(') {
                stack.push(new Counter());
            } else if (c == ')') {
                int j = i + 1;
                var times = 0;
                while (j < formula.length() && isDigit(formula.charAt(j))) {
                    times += times * 10 + digit(formula.charAt(j), 10);
                    j++;
                }
                i = j - 1;
                times = times == 0 ? 1 : times;
                Counter top = stack.pop();
                top.times(times);
                stack.peek().merge(top);
            }
        }
        var sb = new StringBuilder();
        for (var e : stack.peek().counter.entrySet()) {
            sb.append(e.getKey());
            if (e.getValue() > 1) {
                sb.append(e.getValue());
            }
        }
        return sb.toString();
    }

    public static class Counter {

        public TreeMap<String, Integer> counter = new TreeMap<>();

        public String recentKey;

        public void add(String key, int count) {
            recentKey = key;
            counter.put(key, counter.getOrDefault(key, 0) + count);
        }

        public void times(int times) {
            counter.replaceAll((k, v) -> counter.get(k) * times);
        }

        public void merge(Counter other) {
            for (var key : other.counter.keySet()) {
                if (counter.containsKey(key)) {
                    counter.put(key, counter.get(key) + other.counter.get(key));
                } else {
                    counter.put(key, other.counter.get(key));
                }
            }
        }
    }

}
