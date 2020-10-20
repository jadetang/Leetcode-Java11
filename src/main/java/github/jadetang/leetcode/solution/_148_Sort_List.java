package github.jadetang.leetcode.solution;

import github.jadetang.leetcode.other.ListNode;
import org.junit.jupiter.api.Test;

public class _148_Sort_List {

    @Test
    void test() {
        ListNode listNode = ListNode.of(4, 2, 1, 3);
        ListNode sorted = sortList(listNode);
        System.out.println(sorted);
    }

    @Test
    void test2() {
        ListNode listNode = ListNode.of(1, 2, 3, 4);
        ListNode sorted = sortList(listNode);
        System.out.println(sorted);
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMid(head);
        ListNode first = sortList(head);
        ListNode second = sortList(mid);
        return merge(first, second);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dumy = new ListNode(-1);
        ListNode head = dumy;
        while (left != null && right != null) {
            int a = left.val;
            int b = right.val;
            if (a <= b) {
                left = left.next;
                head.next = new ListNode(a);
                head = head.next;
            } else if (b < a) {
                right = right.next;
                head.next = new ListNode(b);
                head = head.next;
            }
        }
        while (left != null) {
            head.next = new ListNode(left.val);
            left = left.next;
            head = head.next;
        }
        while (right != null) {
            head.next = new ListNode(right.val);
            right = right.next;
            head = head.next;
        }
        return dumy.next;
    }

    private ListNode findMid(ListNode head) {
        ListNode temp = head;
        int length = 0;
        while (temp != null) {
            temp = temp.next;
            length++;
        }
        int mid = length / 2;
        temp = head;
        ListNode pre = null;
        while (mid > 0) {
            pre = temp;
            temp = temp.next;
            mid--;
        }
        //ListNode toReturn = temp.next;
        pre.next = null;
        return temp;
    }
}
