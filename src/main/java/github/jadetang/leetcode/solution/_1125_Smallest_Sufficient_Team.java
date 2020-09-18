package github.jadetang.leetcode.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class _1125_Smallest_Sufficient_Team {

    @Test
    void test() {
        Assertions.assertArrayEquals(new int[]{1, 2}, smallestSufficientTeam(
                new String[] {"algorithms","math","java","reactjs","csharp","aws"},
                List.of(
                        List.of("algorithms","math","java"),
                        List.of("algorithms","math","reactjs"),
                        List.of("java","csharp","aws"),
                        List.of("reactjs","csharp"),
                        List.of("csharp","math"),
                        List.of("aws","java")
                )
        ));
    }



    Map<String, Integer> skillToInteger = new HashMap<>();

    int requiredSkills = 0;

    List<Integer> ans = new ArrayList<>();

    int[] peopleArray;

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        for (int i = 0; i < req_skills.length; i++) {
            skillToInteger.put(req_skills[i], 1 << i);
            requiredSkills = requiredSkills | 1 << i;
        }
        peopleArray = new int[people.size()];
        for (int i = 0; i < peopleArray.length; i++) {
            int skill = 0;
            for (String s : people.get(i)) {
                skill = skill | (skillToInteger.get(s));
            }
            peopleArray[i] = skill;
        }
        search(0, 0, new ArrayList<>());
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    void search(int currentIndex, int currentSkill, List<Integer> path) {
        if (currentIndex >= peopleArray.length) {
            return;
        }
        if ( (currentSkill & requiredSkills) == requiredSkills && (ans.size() == 0 || path.size() < ans.size())) {
            ans = new ArrayList<>(path);
        }
        for (int i = currentIndex; i < peopleArray.length; i++) {
            if (currentSkill != (currentSkill | peopleArray[i])) {
                path.add(i);
                search(currentIndex + 1, currentSkill | peopleArray[i], path);
                path.remove(path.size() - 1);
            }
        }
    }
}
