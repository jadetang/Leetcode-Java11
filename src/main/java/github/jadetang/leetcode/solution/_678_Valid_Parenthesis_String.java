package github.jadetang.leetcode.solution;

public class _678_Valid_Parenthesis_String {

    public boolean checkValidString(String s) {
        int min = 0;
        int max = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                min++;
                max++;
            }else if (c == ')') {
                if (min > 0) {
                    min--;
                }
                if (max == 0) {
                    return false;
                }
                max--;
            }else {
                if (min > 0) {
                    min--;
                }
                max++;
            }
        }
        return min == 0;
    }
}
