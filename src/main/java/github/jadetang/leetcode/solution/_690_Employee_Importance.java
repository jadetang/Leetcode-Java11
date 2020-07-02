package github.jadetang.leetcode.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class _690_Employee_Importance {

    @Test
    void testHappyPath() {
        var employee = new Employee();
        employee.id = 1;
        employee.importance = 10;
        employee.subordinates = new ArrayList<>();
        var employees = List.of(employee);
        assertEquals(10, this.getImportance(employees, 1));
    }

    public static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    public int getImportance(List<Employee> employees, int id) {
        var idToEmployees = new HashMap<Integer, Employee>();
        employees.forEach(e -> idToEmployees.put(e.id, e));
        var queue = new LinkedList<Employee>();
        queue.offer(idToEmployees.get(id));
        int ans = 0;
        while (!queue.isEmpty()) {
            var employee = queue.poll();
            ans += employee.importance;
            employee.subordinates.forEach(s -> queue.offer(idToEmployees.get(s)));
        }
        return ans;
    }
}
