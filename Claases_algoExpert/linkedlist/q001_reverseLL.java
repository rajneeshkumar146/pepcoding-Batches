import java.util.*;

class Program {
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

  static class LinkedList {
    int value;
    LinkedList next = null;

    public LinkedList(int value) {
      this.value = value;
    }
  }
}
