package github.jadetang.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class _1606_Find_Servers_That_Handled_Most_Number_of_Requests {

    @Test
    void test() {
        System.out.println(busiestServers(3, new int[]{1, 2, 3, 4, 8, 9, 10},
                new int[]{5, 2, 10, 3, 1, 2, 2}));
    }

    public List<Integer> busiestServers(int k, int[] a, int[] l) {
        TreeSet<Integer> servers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            servers.add(i);
        }
        int[] jobs = new int[k];
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            events.add(new Event(i, 1, a[i]));
            events.add(new Event(i, 0, a[i] + l[i]));
        }
        events.sort((e1, e2) -> e1.time == e2.time ? e1.id - e2.id : e1.time - e2.time);
        Map<Integer, Integer> idToServer = new HashMap<>();
        for (Event event : events) {
            if (event.start == 1) {
                if (servers.isEmpty()) {
                    continue;
                } else {
                    int server = nextServer(servers, event.id % k);
                    idToServer.put(event.id, server);
                    jobs[server]++;
                    servers.remove(server);
                }
            } else {
                Integer server = idToServer.get(event.id);
                if (server != null) {
                    servers.add(server);
                }
            }
        }
        System.out.println(Arrays.toString(jobs));
        int max = IntStream.of(jobs).max().getAsInt();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < jobs.length; i++) {
            if (jobs[i] == max) {
                ans.add(i);
            }
        }
        return ans;
    }

    int nextServer(TreeSet<Integer> servers, int id) {
        //  System.out.println(servers + ":" + id);
        Integer nextServer = servers.ceiling(id);
        return nextServer != null ? nextServer : servers.first();
    }


    public static class Event {

        public int start; // 1 start 0 end

        public int time;

        public int id;

        public Event(int id, int start, int time) {
            this.start = start;
            this.time = time;
            this.id = id;
        }
    }
}
