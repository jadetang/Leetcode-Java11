package github.jadetang.leetcode.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _753_Cracking_the_Safe {

    int total;
    List<Character> ans;
    Set<String> visited = new HashSet<>();
    int k;
    int n;

    public String crackSafe(int n, int k) {
        total = (int) Math.pow(k, n);
        this.k = k;
        this.n = n;
        var initial = "0".repeat(n);
        visited.add(initial);
        var path = Collections.nCopies(n, '0');
        if (dfs(initial, path)) {
            var sb = new StringBuilder();
            for (char c : ans) {
                sb.append(c);
            }
            return sb.toString();
        }
        return "";
    }

    private boolean dfs(String current, List<Character> path) {
        if (visited.size() == total) {
            ans = new ArrayList<>(path);
            return true;
        }
        var nextPart = current.substring(1);
        for (int i = 0; i < k; i++) {
            char c = (char) ('0' + i);
            String next = nextPart + c;
            if (!visited.contains(next)) {
                visited.add(next);
                path.add(c);
                if (dfs(next, path)) {
                    return true;
                }
                visited.remove(next);
                path.remove(path.size() - 1);
            }
        }
        return false;
    }
}
