package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class _1405_Longest_Happy_String {

    @Test
    void testHappyPath() {
        assertEquals(5, longestDiverseString(2, 2, 1).length());
        assertEquals(5, longestDiverseString(7, 1, 0).length());
    }

    public String longestDiverseString(int a, int b, int c) {
        List<Record> records = new ArrayList<>();
        if (a != 0) records.add(new Record('a', a));
        if (b != 0) records.add(new Record('b', b));
        if (c != 0) records.add(new Record('c', c));
        var sb = new StringBuilder();
        while (!records.isEmpty()) {
            records.sort((r1, r2) -> r2.count - r1.count);
            var r = records.get(0);
            if (sb.length() >= 2 && sb.charAt(sb.length() - 1) == r.c && sb.charAt(sb.length() - 2) == r.c) {
                if (records.size() >= 2) {
                    var anotherR= records.get(1);
                    sb.append(anotherR.c);
                    anotherR.count--;
                    if (anotherR.count == 0) {
                        records.remove(anotherR);
                    }
                }else {
                    break;
                }
            }else {
                sb.append(r.c);
                r.count--;
                if (r.count == 0) {
                    records.remove(r);
                }
            }
        }
        return sb.toString();
    }

    public static class Record {
        char c;
        int count;

        public Record(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
}
