package github.jadetang.leetcode.solution;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;

public class _1244_Design_A_Leaderboard {

    @Test
    void test() {
        var leaderboard = new Leaderboard();
        leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
        leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
        leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
        leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
        leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
        leaderboard.top(1);           // returns 73;
        leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
        leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
        leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
        leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
    }

    static class Leaderboard {

        Map<Integer, Integer> player = new HashMap<>();

        TreeMap<Integer, Integer> scoreMap = new TreeMap<>(Comparator.reverseOrder());

        public Leaderboard() {

        }

        public void addScore(int playerId, int score) {
            var oldScore = player.get(playerId);
            if (oldScore != null) {
                scoreMap.put(oldScore, scoreMap.get(oldScore) - 1);
                if (scoreMap.get(oldScore) == 0) {
                    scoreMap.remove(oldScore);
                }
            }
            var newScore = oldScore == null ? score : oldScore + score;
            scoreMap.put(newScore, scoreMap.getOrDefault(newScore, 0) + 1);
            player.put(playerId, newScore);
        }

        public int top(int k) {
            int ans = 0;
            for (var e : scoreMap.entrySet()) {
                ans += e.getKey() * Math.min(e.getValue(), k);
                k -= e.getValue();
                if (k <= 0) {
                    return ans;
                }
            }
            return ans;
        }

        public void reset(int playerId) {
            int score = player.get(playerId);
            player.remove(playerId);
            scoreMap.put(score, scoreMap.get(score) - 1);
            if (scoreMap.get(score) == 0) {
                scoreMap.remove(score);
            }
        }
    }
}