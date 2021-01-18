package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import org.junit.jupiter.api.Test;

public class _224_Basic_Calculator {

    @Test
    void test() {
        assertEquals(1, calculate("1 - 0"));
        assertEquals(2, calculate("3 - 1"));
        assertEquals(23, calculate("(1+(4+5+2)-3)+(6+8)"));
        assertEquals(59, calculate("(1+(40+5+2)-3)+(6+8)"));
    }

    public int calculate(String s) {
        s = "(" + s + ")";
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                int j = i;
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    sb.append(s.charAt(j));
                    j++;
                }
                i = j - 1;
                stack.push(sb.toString());
                continue;
            }
            if (c == ')') {
                List<String> sb = new ArrayList<>();
                while (!stack.peek().equals("(")) {
                    sb.add(stack.pop());
                }
                stack.pop();
                Collections.reverse(sb);
                stack.push(eval(sb));
                continue;
            }
            stack.push(String.valueOf(c));
        }
        return Integer.parseInt(stack.peek());
    }

    private String eval(List<String> exp) {
        if (exp.size() == 0) {
            return "0";
        }
        String op = null;
        int sum = 0;
        for (String e : exp) {
            if (e.equals("+") || e.equals("-")) {
                op = e;
            } else {
                if (op == null || op.equals("+")) {
                    sum += Integer.parseInt(e);
                } else {
                    sum -= Integer.parseInt(e);
                }
            }
        }
        return String.valueOf(sum);
    }
}
