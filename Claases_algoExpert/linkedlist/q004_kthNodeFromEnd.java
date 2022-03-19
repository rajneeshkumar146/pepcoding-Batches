import java.util.*;

class Program {
    public static void removeKthNodeFromEnd(LinkedList head, int k) {
        if (head == null)
            return;

        LinkedList slow = head, fast = head;
        while (k-- > 0)
            fast = fast.next;

        if (fast == null) {
            head.value = head.next.value;

            LinkedList rn = head.next;
            head.next = rn.next;
            rn.next = null;

            return;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        LinkedList rn = slow.next;
        slow.next = rn.next;
        rn.next = null;

    }

    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }
    }
}
