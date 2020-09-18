package github.jadetang.leetcode.solution;

import java.util.StringJoiner;

public class _1334_FindCities {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        return 1;
    }

    public static class Internal {
        int start;
        int end;

        public Internal(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            if (start == end) {
                return start + "";
            }
            return start + "->" + end;
        }
    }
}
