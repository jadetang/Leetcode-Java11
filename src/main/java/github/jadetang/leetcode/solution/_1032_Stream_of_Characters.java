package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.jupiter.api.Test;

public class _1032_Stream_of_Characters {

    @Test
    void test() {
        var streamChecker = new StreamChecker(new String[]{"cd","f","kl"});
        assertFalse(streamChecker.query('a'));          // return false
        assertFalse(streamChecker.query('b'));          // return false
        assertFalse(streamChecker.query('c'));          // return false
        assertTrue(streamChecker.query('d'));          // return true, because 'cd' is in the wordlist
        assertFalse(streamChecker.query('e'));          // return false
        assertTrue(streamChecker.query('f'));          // return true, because 'f' is in the wordlist
        assertFalse(streamChecker.query('g'));          // return false
        assertFalse(streamChecker.query('h'));          // return false
        assertFalse(streamChecker.query('i'));          // return false
        assertFalse(streamChecker.query('j'));          // return false
        assertFalse(streamChecker.query('k'));          // return false
        assertTrue(streamChecker.query('l'));          // return true, because 'kl' is in the wordlist
    }

    public static class StreamChecker {

        TrieNode root;

        Queue<TrieNode> nodeList = new LinkedList<>();

        public StreamChecker(String[] words) {
            root = new TrieNode();
            for (String word : words) {
                insert(word);
            }
        }

        public boolean query(char letter) {
            boolean found = false;
            int size = nodeList.size();
            for (int i = 0; i < size; i++) {
                var n = nodeList.poll();
                var nextN = n.array[letter - 'a'];
                if (nextN != null) {
                    nodeList.offer(nextN);
                    found |= nextN.hasWord;;
                }
            }
            var node = root.array[letter - 'a'];
            if (node != null) {
                nodeList.offer(node);
                found |= node.hasWord;
            }
            return found;
         }

        private void insert(String word) {
            var node = root;
            for (char c : word.toCharArray()) {
                if (node.array[c - 'a'] == null) {
                    node.array[c - 'a'] = new TrieNode();
                }
                node = node.array[c - 'a'];
            }
            node.hasWord = true;
        }


        public static class TrieNode {

            TrieNode[] array;

            boolean hasWord;

            public TrieNode() {
                array = new TrieNode[26];
                hasWord = false;
            }
        }
    }
}
