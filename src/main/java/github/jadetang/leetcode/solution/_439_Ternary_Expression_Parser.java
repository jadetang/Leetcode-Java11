package github.jadetang.leetcode.solution;

import org.junit.jupiter.api.Test;

public class _439_Ternary_Expression_Parser {

    @Test
    void test() {
        System.out.println(parseTernary("T?T:F?T?1:2:F?3:4"));
    }

    public String parseTernary(String e) {
        if (e.length() == 1) {
            return e;
        }
        if (!e.contains("?")) {
            return e;
        }
        if (e.charAt(0) == 'F') {
            return help(e.substring(2, e.length()), false);
        } else {
            return help(e.substring(2, e.length()), true);
        }
    }

    private String help(String e, boolean flag) {
        if (e.indexOf(":") == e.lastIndexOf(":")) {
            int index = e.indexOf(":");
            if (flag) {
                return e.substring(0, index);
            } else {
                return e.substring(index + 1, e.length());
            }
        } else {
            int index = firstUnMatchColon(e);
            if (flag) {
                return parseTernary(e.substring(0, index));
            } else {
                return parseTernary(e.substring(index + 1, e.length()));
            }
        }
    }

    private int firstUnMatchColon(String e) {
        boolean haveQuestionMark = false;
        for (int i = 0; i < e.length(); i++) {
            if (e.charAt(i) == '?') {
                haveQuestionMark = true;
            } else if (e.charAt(i) == ':') {
                if (haveQuestionMark) {
                    haveQuestionMark = false;
                } else {
                    return i;
                }
            }
        }
        throw new RuntimeException(e);
    }
}
