package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.TreeSet;
import org.junit.jupiter.api.Test;

public class _1395_Count_Number_of_Teams {

    @Test
    void test() {
        assertEquals(3, numTeams(new int[]{2, 5, 3, 4, 1}));
    }

    public int numTeams(int[] r) {
        return help(r) + help(reverse(r));
    }

    private int help(int[] r) {
        int[] less = new int[r.length];
        int[] bigger = new int[r.length];
        TreeSet<Integer> set = new TreeSet<>();
        set.add(r[0]);
        for (int i = 1; i < r.length; i++) {
            int c = r[i];
            less[i] = set.headSet(c).size();
            set.add(c);
        }
        set.clear();
        set.add(r[r.length - 1]);
        for (int i = r.length - 2; i >= 0; i--) {
            int c = r[i];
            bigger[i] = set.tailSet(c).size();
            set.add(c);
        }
        int ans = 0;
        for (int i = 1; i < r.length - 1; i++) {
            ans += less[i] * bigger[i];
        }
        return ans;
    }

    private int[] reverse(int[] r) {
        int[] a = new int[r.length];
        for (int i = 0; i < r.length; i++) {
            a[r.length - 1 - i] = r[i];
        }
        return a;
    }
}
