package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class _1288_Remove_Covered_Intervals {

    @Test
    void test() {
        assertEquals(2, removeCoveredIntervals(new int[][]{{1, 4}, {3, 6}, {2, 8}}));
    }

    public int removeCoveredIntervals(int[][] intervals) {
        List<int[]> intervalList = new ArrayList<>();
        for (int[] i : intervals) {
            intervalList.add(i);
        }
        Collections.sort(intervalList, (i1, i2) -> {
            if (i1[0] == i2[0]) {
                return i1[1] - i2[1];
            }
            return i1[0] - i2[0];
        });
        int ans = intervalList.size();
        int[] pre = intervalList.get(0);
        for (int i = 1; i < intervalList.size(); i++) {
            int[] cur = intervalList.get(i);
            if (pre[0] == cur[0] && cur[1] >= pre[1]) {
                ans--;
                pre = cur;
                continue;
            }
            if (pre[0] <= cur[0] && pre[1] >= cur[1]) {
                ans--;
                continue;
            }
            pre = cur;
        }
        return ans;
    }
}
