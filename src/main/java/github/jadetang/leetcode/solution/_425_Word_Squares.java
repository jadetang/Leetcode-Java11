package github.jadetang.leetcode.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class _425_Word_Squares {

    @Test
    void test() {
        System.out.println(wordSquares(new String[]{"ball","area","lead","lady"}));
    }

    @Test
    void test2() {
        System.out.println(wordSquares(new String[]{"abat","baba","atan","atal"}));
    }

    int length;

    List<List<String>> ans = new ArrayList<>();

    String[] words;

    Map<String, Set<String>> preFixCache = new HashMap<>();


    public List<List<String>> wordSquares(String[] words) {
        length = words[0].length();
        this.words = words;
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                var pre = word.substring(0, i + 1);
                preFixCache.computeIfAbsent(pre, k -> new HashSet<String>()).add(word);
            }
        }
        for (String word : words) {
            ArrayList<String> list = new ArrayList<String>();
            list.add(word);
            backTrack(list);
        }
        return ans;
    }

    private void backTrack(ArrayList<String> path) {
        if (path.size() == length) {
            ans.add(new ArrayList<>(path));
        } else {
            var prefix = preFix(path);
            for (var w : preFixCache.getOrDefault(prefix, new HashSet<>())) {
                path.add(w);
                backTrack(path);
                path.remove(path.size() - 1);
            }
        }
    }

    private String preFix(ArrayList<String> path) {
        var sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i).charAt(path.size()));
        }
        return sb.toString();
    }
}

