import java.util.*;

class Program {
    // This is an input class. Do not edit.
    public static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static LinkedList mergeLinkedLists(LinkedList headOne, LinkedList headTwo) {
        if (headOne == null || headTwo == null)
            return headOne != null ? headOne : headTwo;

        LinkedList dummy = new LinkedList(-1), prev = dummy, c1 = headOne, c2 = headTwo;
        while (c1 != null && c2 != null) {
            if (c1.value <= c2.value) {
                prev.next = c1;
                c1 = c1.next;
            } else {
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }

        prev.next = c1 != null ? c1 : c2;

        return dummy.next;
    }
}
