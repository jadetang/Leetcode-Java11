package github.jadetang.leetcode.other;

import java.util.StringJoiner;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ListNode.class.getSimpleName() + "[", "]")
                .add("val=" + val)
                .add("next=" + next)
                .toString();
    }

    public static ListNode of(int... nums) {
        ListNode dumy = new ListNode();
        ListNode head = dumy;
        for (int i : nums) {
            head.next = new ListNode(i);
            head = head.next;
        }
        return dumy.next;
    }
}