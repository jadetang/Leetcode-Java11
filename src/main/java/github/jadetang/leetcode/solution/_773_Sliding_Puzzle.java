package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class _773_Sliding_Puzzle {

    @Test
    void test() {
        assertEquals(-1, slidingPuzzle(new int[][]{{1, 2, 3,}, {5, 4, 0}}));
    }

    int[] dir = new int[]{1, 0, -1, 0, 1};

    public int slidingPuzzle(int[][] board) {
        Board initial = new Board(board);
        Queue<Board> queue = new LinkedList<>();
        queue.offer(initial);
        Set<Integer> visited = new HashSet<>();
        visited.add(initial.code());
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Board current = queue.poll();
                if (solved(current.board)) {
                    return ans;
                }
                int[] zeroPosition = current.getZero();
                for (int k = 0; k < 4; k++) {
                    int nextI = zeroPosition[0] + dir[k];
                    int nextJ = zeroPosition[1] + dir[k + 1];
                    if (valid(nextI, nextJ)) {
                        Board nextBoard = current.move(nextI, nextJ);
                        if (visited.add(nextBoard.code())) {
                            queue.offer(nextBoard);
                        }
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    private boolean solved(int[][] board) {
        int d  = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != d++) {
                    return false;
                }
                if (d == 6) {
                    d = 0;
                }
            }
        }
        return true;
    }

    private boolean valid(int i, int j) {
        return i >= 0 && i < 2 && j >= 0 && j < 3;
    }

    public static class Board {

        public int[][] board;

        public int[] position;

        public Board(int[][] board) {
            this.board = new int[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                System.arraycopy(board[i], 0, this.board[i], 0, board[0].length);
            }
            for (int i = 0; i < this.board.length; i++) {
                for (int j = 0; j < this.board[0].length; j++) {
                    if (board[i][j] == 0) {
                        position =  new int[] {i, j};
                    }
                }
            }
        }

        public int[] getZero() {
            return position;
        }

        public int code() {
            int hash = 1;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    hash = hash * 31 + board[i][j];
                }
            }
            return hash;
        }


        public Board move(int nextI, int nextJ) {
            Board newBoard = new Board(this.board);
            newBoard.swap(nextI, nextJ);
            return newBoard;
        }

        private void swap(int nextI, int nextJ) {
            int[] p = this.getZero();
            swap(p[0], p[1], nextI, nextJ);
            position = new int[]{nextI, nextJ};
        }

        private void swap(int i, int j, int i1, int j1) {
            int temp = board[i][j];
            board[i][j] = board[i1][j1];
            board[i1][j1] = temp;
        }
    }
}
