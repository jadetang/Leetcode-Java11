package github.jadetang.other.google;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SearchValidParentheses {

    @Test
    void test() {
        char[][] matrix = new char[][] {
                {'(', '(', ')', ')', '('},
                {')', ')', '(', ')', ')'},
                {')', '(', '(', '(', '('},
                {')', '(', '(', '(', ')'}
        };
        assertTrue(searchParentheses(matrix));
    }

    @Test
    void test2() {
        char[][] matrix = new char[][]{
                {'(', '(', ')'},
                {')', ')', ')'}
        };
        assertTrue(searchParentheses(matrix));
    }

    @Test
    void test3() {
        char[][] matrix = new char[][] {
                {'(', ')'},
        };
        assertTrue(searchParentheses(matrix));
    }

    char[][] m;
    int r;
    int c;
    boolean[][] visited;
    int desR;
    int desC;
    int[] dir = new int[] {1, 0, -1, 0, 1};

    boolean searchParentheses(char[][] m) {
        this.r = m.length;
        this.c = m[0].length;
        this.m = m;
        this.desR = r - 1;
        this.desC = c - 1;
        visited = new boolean[m.length][m[0].length];
        return search(0, 0, 0);
    }

    private boolean search(int r, int c, int unmatched) {
        if (r == desR && c == desC && unmatched == 1) {
            return true;
        }
        char current = m[r][c];
        if (current == ')' && unmatched <= 0) {
            return false;
        }
        int nextUnmatched = unmatched;
        if (current == ')') {
            nextUnmatched--;
        } else {
           nextUnmatched++;
        }
        for (int i = 0; i < dir.length - 1; i++) {
            int nextR = r + dir[i];
            int nextC = c + dir[i + 1];
            if (nextR >= 0 && nextR < this.r && nextC >= 0 && nextC < this.c && !visited[nextR][nextC]) {
                visited[nextR][nextC] = true;
                if (search(nextR, nextC, nextUnmatched)) {
                    return true;
                }
                visited[nextR][nextC] = false;
            }
        }
        return false;
    }
}
