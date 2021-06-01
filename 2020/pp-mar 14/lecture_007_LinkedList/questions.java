public class questions {

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
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head, fast = head;
        while (n-- > 0)
            fast = fast.next;

        if (fast == null)
            return head = head.next;

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return head;
    }

    // 83
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-101);
        ListNode dp = dummy;

        ListNode curr = head;
        int size = 0;
        while (curr != null) {
            while (curr != null && curr.val == dp.val) {
                ListNode forw = curr.next;
                curr.next = null;
                curr = forw;
            }

            dp.next = curr;
            if (curr != null) {
                curr = curr.next;
                dp = dp.next;
                size++;
            }
        }

        return dummy.next;
    }

    // 206
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode forw = curr.next; // backup

            curr.next = prev; // link

            prev = curr; // move
            curr = forw;
        }

        return prev;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        l1 = reverseList(l1);
        l2 = reverseList(l2);

        ListNode dummy = new ListNode(-1);

        ListNode c1 = l1, c2 = l2, prev = dummy;
        int carry = 0;
        while (c1 != null || c2 != null || carry != 0) {
            int sum = carry + (c1 != null ? c1.val : 0) + (c2 != null ? c2.val : 0);

            carry = sum / 10;
            sum %= 10;

            prev.next = new ListNode(sum);

            prev = prev.next;
            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        ListNode head = dummy.next;
        head = reverseList(head);

        l1 = reverseList(l1);
        l2 = reverseList(l2);

        return head;
    }

    // 21
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 != null ? l1 : l2;

        ListNode head = null, prev = null, c1 = l1, c2 = l2;
        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                if (prev != null) {
                    prev.next = c1;
                    prev = prev.next;
                } else {
                    head = c1;
                    prev = head;
                }
                c1 = c1.next;
            } else {
                if (prev != null) {
                    prev.next = c2;
                    prev = prev.next;
                } else {
                    head = c2;
                    prev = head;
                }
                c2 = c2.next;
            }
        }

        prev.next = c1 != null ? c1 : c2;

        return head;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 != null ? l1 : l2;

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, c1 = l1, c2 = l2;

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

        return dummy.next;
    }

    // 148
    public ListNode sortList(ListNode head) {
        if (head.next == null)
            return head;

        ListNode mid = middleNode(head);
        ListNode nHead = mid.next;
        mid.next = null;

        ListNode leftSortedList = sortList(head);
        ListNode rightSortedList = sortList(nHead);

        return mergeTwoLists(leftSortedList, rightSortedList);
    }

}