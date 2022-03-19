import java.util.*;

class Program {
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static LinkedList midNode(LinkedList head) {
        if (head == null || head.next == null)
            return head;

        LinkedList slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static LinkedList reverseLinkedList(LinkedList head) {
        if (head == null || head.next == null)
            return head;

        LinkedList prev = null, curr = head;

        while (curr != null) {
            LinkedList forw = curr.next; // backup

            curr.next = prev; // linking

            prev = curr; // move forward
            curr = forw;
        }

        return prev;
    }

    public boolean linkedListPalindrome(LinkedList head) {
        if (head == null || head.next == null)
            return true;

        LinkedList midNode = midNode(head);
        LinkedList nHead = midNode.next;
        midNode.next = null;

        nHead = reverseLinkedList(nHead);

        LinkedList c1 = head, c2 = nHead;
        while (c2 != null) {
            if (c1.value != c2.value)
                return false;

            c1 = c1.next;
            c2 = c2.next;
        }

        nHead = reverseLinkedList(nHead);
        midNode.next = nHead;
        return true;
    }
}
