public class l001 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 876
    // second Mid
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    // first Mid
    public ListNode midNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public int length(ListNode head) {
        if (head == null)
            return 0;

        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            len++;
        }

        return len;
    }

    // 206
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode forw = curr.next; // backup

            curr.next = prev; // link

            prev = curr;
            curr = forw;
        }

        return prev;
    }

    // 234
    public boolean isPalindrome(ListNode head) {
        if (head == null && head.next == null)
            return true;

        ListNode mid = midNode(head), nHead = mid.next;
        mid.next = null;
        nHead = reverseList(nHead);

        ListNode c1 = head, c2 = nHead;
        boolean res = true;
        while (c2 != null) {
            if (c1.val != c2.val) {
                res = false;
                break;
            }
            c1 = c1.next;
            c2 = c2.next;
        }

        nHead = reverseList(nHead);
        mid.next = nHead;

        return res;
    }

    // 143
    public void reorderList(ListNode head) {
        if (head == null && head.next == null)
            return;

        ListNode mid = midNode(head), nHead = mid.next;
        mid.next = null;
        nHead = reverseList(nHead);
        ListNode c1 = head, c2 = nHead;
        while (c2 != null) {
            ListNode f1 = c1.next, f2 = c2.next; // backup

            c1.next = c2; // link
            c2.next = f1;

            c1 = f1;
            c2 = f2;
        }
    }

    // 21
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null)
            return list1 == null ? list2 : list1;

        ListNode dummy = new ListNode(-1), prev = dummy, c1 = list1, c2 = list2;
        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                prev.next = c1;
                c1 = c1.next;
            } else {
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }

        prev.next = c1 != null ? c1 : c2;
        ListNode head = dummy.next;
        dummy.next = null;
        return head;
    }

    public void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode h1 = head, h2 = head.next, c1 = h1, c2 = h2;

        while (c2 != null && c2.next != null) {
            ListNode f = c2.next;

            c1.next = f;
            c2.next = f.next;

            c1 = c1.next;
            c2 = c2.next;
        }

        h2 = reverseList(h2);
        c1.next = h2;
    }

    // 19
    public ListNode removeNthFromEnd(ListNode head, int n) {

    }

    // 2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

    }

    // pepcoding
    public ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        return null;
    }

}