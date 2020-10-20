package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class _1583_Count_Unhappy_Friends {

    @Test
    void test() {
        assertEquals(4,
                unhappyFriends(4, new int[][]{{1, 3, 2}, {2, 3, 0}, {1, 3, 0}, {0, 2, 1}}, new int[][]{{1, 3}, {0, 2}}));
    }

    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[] friends = new int[n];
        for (var p : pairs) {
            friends[p[0]] = p[1];
            friends[p[1]] = p[0];
        }
        int ans = 0;
        for (int x = 0; x < n; x++) {
            var y = friends[x];
            var xPre = preferences[x];
            var index = search(xPre, y);
            for (int i = 0; i < index; i++) {
                var u = xPre[i];
                var v = friends[u];
                var pX = search(preferences[u], x);
                var pV = search(preferences[u], v);
                if (pX < pV) {
                    ans += 2;
                    break;
                }
            }
        }
        return ans;
    }

    private int search(int[] xPre, int y) {
        for (int i = 0; i < xPre.length; i++) {
            if (xPre[i] == y) {
                return i;
            }
        }
        return -1;
    }
}
