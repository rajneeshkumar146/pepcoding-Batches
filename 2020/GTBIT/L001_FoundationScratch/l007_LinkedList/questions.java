public class questions {
    public class Node {
        int data;
        Node next;
    }

    Node head = null;
    Node tail = null;

    public int mid() {
        Node slow = head;
        Node fast = head;
        // while(fast != null && fast.next != null) // 2nd mid of even length linkedlist
        while (fast.next != null && fast.next.next != null) { // 1mid of even length linkedlist
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.data;
    }

    public void reversePI(){
        Node curr = head;
          Node prev = null;
          while (curr != null) {
              Node forw = curr.next; // backup
  
              curr.next = prev; // link
  
              prev = curr; // move
              curr = forw;
          }
          
          tail = head;
          head = prev;
      }

}