package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;
import org.junit.jupiter.api.Test;

public class _227_Basic_Calculator_II {

    @Test
    void test() {
        assertEquals(1, calculate("3/2"));
        assertEquals(5, calculate("3 + 5/2"));
    }

    public int calculate(String s) {
        s = s.replaceAll("\\s+", "");
        Deque<String> exp = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*' || c == '/') {
                Integer left = Integer.parseInt(exp.pollLast());
                int j = i + 1;
                StringBuilder sb = new StringBuilder();
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    sb.append(s.charAt(j));
                    j++;
                }
                i = j - 1;
                Integer right = Integer.parseInt(sb.toString());
                exp.offerLast(eval(c, left, right));
            } else if (Character.isDigit(c)) {
                int j = i;
                StringBuilder sb = new StringBuilder();
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    sb.append(s.charAt(j));
                    j++;
                }
                i = j - 1;
                exp.offerLast(sb.toString());
            } else if (c == '+' || c == '-') {
                exp.offerLast(String.valueOf(c));
            }
        }
        return Integer.parseInt(eval(exp));
    }

    private String eval(char c, Integer left, Integer right) {
        switch (c) {
            case '*': return String.valueOf(left * right);
            case '/': return String.valueOf(left / right);
        }
        return "0";
    }

    private String eval(Deque<String> exp) {
        if (exp.size() == 0) {
            return "0";
        }
        String op = null;
        int sum = 0;
        while (!exp.isEmpty()) {
            String e = exp.pollFirst();
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
