package github.jadetang.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class _1520_Maximum_Number_of_Non_Overlapping_Substrings {

    @Test
    void test() {
        System.out.println(maxNumOfSubstrings("adefaddaccc"));
    }

    @Test
    void test2() {
        System.out.println(maxNumOfSubstrings("abab"));
    }

    @Test
    void test3() {
        System.out.println(maxNumOfSubstrings("abbaccd"));
    }

    @Test
    void test4() {
        System.out.println(maxNumOfSubstrings("zyz"));
    }

    @Test
    void test5() {
        System.out.println(maxNumOfSubstrings("abaabbcaaabbbccd"));
    }


    Map<Character, int[]> indexMap = new HashMap<>();

    String s;

    public List<String> maxNumOfSubstrings(String s) {
        this.s = s;
        for (int i = 0; i < s.length(); i++) {
            if (!indexMap.containsKey(s.charAt(i))) {
                indexMap.put(s.charAt(i), new int[]{i, i});
            } else {
                indexMap.get(s.charAt(i))[1] = i;
            }
        }
        int start = 0;
        int end = 0;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < s.length();) {
            start = indexMap.get(s.charAt(i))[0];
            end = indexMap.get(s.charAt(i))[1];

            if (start < i) {
                continue;
            }
            int j = start;
            while (j <= end) {
                int midStart = indexMap.get(s.charAt(j))[0];
                int midEnd = indexMap.get(s.charAt(j))[1];
                if (midEnd < end) {
                    start = midStart;
                    end = midEnd;
                } else if (midEnd > end) {
                    end = midEnd;
                }
                j++;
            }
            ans.add(s.substring(start, end + 1));
            i = end + 1;
        }
        return ans;
    }
}
