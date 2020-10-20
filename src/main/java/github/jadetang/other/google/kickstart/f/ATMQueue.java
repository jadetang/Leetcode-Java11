package github.jadetang.other.google.kickstart.f;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class ATMQueue {
    @Test
    void test() {
        System.out.println(solution(5, 6, new int[]{9, 10, 4, 7, 2}));
    }

    static String solution(int n, int max, int[] p) {
        List<int[]> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(new int[]{i, (p[i - 1] - 1) / max});
        }
        list.sort((i1, i2) -> i1[1] == i2[1] ? i1[0] - i2[0] : i1[1] - i2[1]);
        return list.stream().map(i -> i[0] + "").collect(Collectors.joining(" "));
    }
}
