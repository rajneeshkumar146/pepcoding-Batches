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

    public ListNode middleNode_leetcode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode forw = curr.next; // backup

            curr.next = prev; // linkup

            prev = curr; // move
            curr = forw;
        }

        return prev;
    }

    // 143
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode midNode = middleNode(head);
        ListNode nHead = midNode.next;
        midNode.next = null;

        nHead = reverseList(nHead);

        ListNode c1 = head, c2 = nHead;
        while (c2 != null) {
            ListNode f1 = c1.next, f2 = c2.next; // backup

            c1.next = c2; // linkup
            c2.next = f1;

            c1 = f1;
            c2 = f2;
        }
    }

    // 21
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null)
            return list1 != null ? list1 : list2;

        ListNode c1 = list1, c2 = list2, dummy = new ListNode(-1), prev = dummy;
        while (c1 != null && c2 != null) {
            if (c1.val < c2.val) {
                prev.next = c1;
                c1 = c1.next;
            } else {
                prev.next = c2;
                c2 = c2.next;
            }

            prev = prev.next;
        }

        prev.next = (c1 != null ? c1 : c2);

        ListNode head = dummy.next;
        dummy.next = null;

        return head;
    }

    // 23
    // (N.K) where N is Total No of Nodes in lists array
    public ListNode mergeKLists_bakwasMethod(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        ListNode ans = null;
        for (ListNode head : lists) {
            ans = mergeTwoLists(ans, head);
        }

        return ans;
    }

    public ListNode mergeKLists(ListNode[] lists, int si, int ei) {
        if (si == ei)
            return lists[si];

        int mid = (si + ei) / 2;
        ListNode l1 = mergeKLists(lists, si, mid);
        ListNode l2 = mergeKLists(lists, mid + 1, ei);
        return mergeTwoLists(l1, l2);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;

        return mergeKLists(lists, 0, lists.length - 1);
    }

    public static void main(String[] args) {

    }

}