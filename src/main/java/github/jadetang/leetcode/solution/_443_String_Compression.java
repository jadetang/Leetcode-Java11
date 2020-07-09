package github.jadetang.leetcode.solution;

public class _443_String_Compression {

    public int compress(char[] chars) {
        int j = 0;
        int i = 0;
        while (i < chars.length) {
            int count = 1;
            char currentchar = chars[i];
            while (i < chars.length - 1 && currentchar == chars[i + 1] ) {
                i++;
                count++;
            }
            chars[j++] = currentchar;
            //j++;
            if (count > 1) {
                String value = String.valueOf(count);
                for (char c : value.toCharArray()) {
                    chars[j++] = c;
                }
            }
            i++;
        }
        return j;
    }
}
