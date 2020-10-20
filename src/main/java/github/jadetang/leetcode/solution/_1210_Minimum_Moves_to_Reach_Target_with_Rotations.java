package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringJoiner;
import org.junit.jupiter.api.Test;

public class _1210_Minimum_Moves_to_Reach_Target_with_Rotations {

    @Test
    void test() {
        int[][] grid = new int[][]
                {{0, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 1, 0}, {0, 0, 0, 0, 1, 1}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 0},
                        {0, 1, 1, 0, 0, 0}};
        assertEquals(11, minimumMoves(grid));
    }

    public int minimumMoves(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        Position end = new Position(new int[]{r - 1, c - 1}, new int[]{r - 1, c - 2});
        Queue<Position> queue = new LinkedList<>();
        Position start = new Position(new int[]{0, 1}, new int[]{0, 0});
        queue.offer(start);
        Set<Position> visited = new HashSet<>();
        visited.add(start);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Position current = queue.poll();
                System.out.println(current);
                if (current.equals(end)) {
                    return step;
                }
                if (canMoveDown(current, grid)) {
                    Position next = moveDown(current);
                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
                if (canMoveRight(current, grid)) {
                    Position next = moveRight(current);
                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
                if (canClockWise(current, grid)) {
                    Position next = clockWise(current);
                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
                if (canAntiClockWise(current, grid)) {
                    Position next = antiClockWise(current);
                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }
            step++;
        }
        return -1;
     }

    private Position antiClockWise(Position current) {
        return new Position(new int[]{current.tail[0], current.tail[1] + 1},
                new int[]{current.tail[0], current.tail[1]});
    }

    private boolean canAntiClockWise(Position current, int[][] grid) {
        return current.head[1] == current.tail[1] && current.head[1] < grid[0].length - 1
                && grid[current.head[0]][current.head[1] + 1] == 0
                && grid[current.tail[0]][current.tail[1] + 1] == 0;
    }

    private Position clockWise(Position current) {
        return new Position(new int[]{current.tail[0] + 1, current.tail[1]},
                new int[]{current.tail[0], current.tail[1]});
    }

    private boolean canClockWise(Position current, int[][] grid) {
        return current.head[0] == current.tail[0] && current.head[0] < grid.length - 1 &&
                grid[current.head[0] + 1][current.head[1]] == 0 &&
                grid[current.tail[0] + 1][current.tail[1]] == 0;
    }

    private Position moveRight(Position current) {
        return new Position(new int[]{current.head[0], current.head[1] + 1},
                new int[]{current.tail[0], current.tail[1] + 1});
    }

    private boolean canMoveRight(Position current, int[][] grid) {
        int[] nextHead = new int[] {current.head[0], current.head[1] + 1};
        int[] nextTail = new int[] {current.tail[0], current.tail[1] + 1};
        return valid(nextHead, grid) && valid(nextTail, grid);
    }

    private Position moveDown(Position current) {
        return new Position(new int[]{current.head[0] + 1, current.head[1]},
                new int[]{current.tail[0] + 1, current.tail[1]});
    }

    private boolean canMoveDown(Position current, int[][] grid) {
        int[] nextHead = new int[] {current.head[0] + 1, current.head[1]};
        int[] nextTail = new int[] {current.tail[0] + 1, current.tail[1]};
        return valid(nextHead, grid) && valid(nextTail, grid);
    }

    private boolean valid(int[] position, int[][] grid) {
        return position[0] >= 0 && position[0] < grid.length && position[1] >= 0 && position[1] < grid[0].length
                && grid[position[0]][position[1]] == 0;
    }

    public static class Position {
        int[] head;
        int[] tail;

        public Position(int[] head, int[] tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Position.class.getSimpleName() + "[", "]")
                    .add("head=" + Arrays.toString(head))
                    .add("tail=" + Arrays.toString(tail))
                    .toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Position position = (Position) o;
            return Arrays.equals(head, position.head) &&
                    Arrays.equals(tail, position.tail);
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(head);
            result = 31 * result + Arrays.hashCode(tail);
            return result;
        }
    }
}
