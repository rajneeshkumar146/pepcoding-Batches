import java.util.*;

class Program {
    // This is an input class. Do not edit.
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public LinkedList reverse(LinkedList head) {
        if (head == null || head.next == null)
            return head;

        LinkedList curr = head, prev = null;
        while (curr != null) {
            LinkedList forw = curr.next; // backup

            curr.next = prev; // link

            prev = curr; // move
            curr = forw;
        }

        return prev;
    }

    public LinkedList midNode(LinkedList head) {
        if (head == null || head.next == null)
            return head;

        LinkedList slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public LinkedList zipLinkedList(LinkedList head) {
        if (head == null || head.next == null)
            return head;

        LinkedList midNode = midNode(head);
        LinkedList nHead = midNode.next;
        midNode.next = null;
			  nHead = reverse(nHead);
        LinkedList c1 = head, c2 = nHead;
        while (c2 != null) {
            LinkedList f1 = c1.next, f2 = c2.next; // backup

            c1.next = c2; // link
            c2.next = f1;

            c1 = f1; // move
            c2 = f2;
        }

        return head;
    }
}
