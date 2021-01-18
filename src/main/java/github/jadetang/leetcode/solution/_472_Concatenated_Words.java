package github.jadetang.leetcode.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class _472_Concatenated_Words {

    @Test
    void test() {
        List<String> ans = findAllConcatenatedWordsInADict(new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"});
        System.out.println(ans);
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            if (!word.equals("")) {
                root.add(word);
            }
        }
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (dfs(word.toCharArray(), 0, root)) {
                res.add(word);
            }
        }
        return res;
    }
    private boolean dfs(char[] str, int start, TrieNode root) {
        int n = str.length;
        TrieNode node = root;
        for (int i = start; i < n; i++) {
            if (!node.next.containsKey(str[i])) {
                return false;
            }
            node = node.next.get(str[i]);
            if (node.isWord && dfs(str, i + 1, root)) {
                return true;
            }
        }
        return node.isWord && start != 0;  // start != 0 含义即是不能匹配到自己
    }

    public static class TrieNode {
        public boolean isWord;
        public Map<Character, TrieNode> next;
        public TrieNode() {
            isWord = false;
            next = new HashMap<>();
        }
        public void add(String str) {
            TrieNode node = this;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (!node.next.containsKey(c)) {
                    node.next.put(c, new TrieNode());
                }
                node = node.next.get(c);
            }
            node.isWord = true;
        }
    }
}


