package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class _997_Find_the_town_judge {

    @Test
    void test() {
        assertEquals(3, findJudge(4, new int[][]{{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}}));
    }
    int findJudge(int n, int[][] trusts) {
        int[] indegree = new int[n + 1];
        int[] outdegree = new int[n + 1];
        for (int[] trust : trusts) {
            indegree[trust[1]]++;
            outdegree[trust[0]]++;
        }
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == n - 1 && outdegree[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
