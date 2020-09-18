package github.jadetang.other.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DisjointElement {

    @Test
    void test() {
        int[] a = new int[]{0, 1, 1, 1, 2, 2};
        int[] b = new int[]{0, 0, 0, 1, 1};
        System.out.println(disjointElement(a, b));
        System.out.println(disjointElement(new int[]{1, 2, 3, 1}, new int[]{3, 2, 0, 1, 0}));
    }

    public static List<Integer> disjointElement(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0;
        int j = 0;
        List<Integer> ans = new ArrayList<>();
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                i++;
                j++;
            }else if (a[i] < b[j]) {
                ans.add(a[i]);
                i++;
            }else if (a[i] > b[j]) {
                ans.add(b[j]);
                j++;
            }
        }
        while (i < a.length) {
            ans.add(a[i++]);
        }
        while (j < b.length) {
            ans.add(b[j++]);
        }
        return ans;
    }
}
