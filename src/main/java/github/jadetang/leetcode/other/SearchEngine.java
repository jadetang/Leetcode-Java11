package github.jadetang.leetcode.other;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SearchEngine {

    @Test
    void test() {
        this.add("dog");
        this.add("document");
        assertTrue(this.exists("dog"));
        assertTrue(this.exists("do."));
    }


    TrieNode root;

    public SearchEngine() {
        root = new TrieNode();
    }

    public void add(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.nodes[c - 'a'] == null) {
                node.nodes[c - 'a'] = new TrieNode();
            }
            node = node.nodes[c - 'a'];
        }
        node.str = word;
    }

    public boolean exists(String word) {
        return search(root, word, 0);
    }

    private boolean search(TrieNode preNode, String target, int index) {
        if (index >= target.length()) {
            return preNode.str != null;
        }
        char c = target.charAt(index);
        if (c == '.') {
            for (TrieNode n : preNode.nodes) {
                if (n != null) {
                    if (search(n, target, index + 1)) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            TrieNode n = preNode.nodes[c - 'a'];
            if (n == null) {
                return false;
            }
            return search(n, target, index + 1);
        }
    }



    public static class TrieNode {

        public TrieNode[] nodes;

        public String str;

        public TrieNode() {
            nodes = new TrieNode[26];
        }
    }
}
