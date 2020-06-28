import java.util.ArrayList;

public class question {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode midNode(ListNode node) {
        if (node == null || node.next == null)
            return node;

        ListNode slow = node, fast = node;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // leetcode 876:
    public ListNode middleNode(ListNode node) {
        if (node == null || node.next == null)
            return node;

        ListNode slow = node, fast = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // leetcode 206:

    public ListNode reverseList(ListNode node) {
        if (node == null || node.next == null)
            return node;

        ListNode prev = null;
        ListNode curr = node;
        while (curr != null) {
            ListNode forw = curr.next; // backup.

            curr.next = prev; // connection

            prev = curr; // move forw.
            curr = forw;
        }

        return prev;
    }

    // leetcode 234.=======================================

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverseList(nhead);

        ListNode c1 = head, c2 = nhead;
        boolean res = true;

        while (c1 != null && c2 != null) {
            if (c1.val != c2.val) {
                res = false;
                break;
            }
            c1 = c1.next;
            c2 = c2.next;
        }

        nhead = reverseList(nhead);
        mid.next = nhead;

        return res;
    }

    // Leetcode 143.
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverseList(nhead);
        ListNode c1 = head, c2 = nhead;

        while (c1 != null && c2 != null) {
            ListNode f1 = c1.next, f2 = c2.next; // backup

            c1.next = c2; // connection.
            c2.next = f1;

            c1 = f1; // move forward.
            c2 = f2;
        }
    }

    // Leetcode 19:
    public ListNode removeNthFromEnd(ListNode head, int n) { // min possible value of n : 1 and it is always valid.
        if (head == null || head.next == null)
            return null;

        ListNode c1 = head, c2 = head;
        while (n-- > 0)
            c2 = c2.next;

        if (c2 == null) // if we have to remove head.
            return head.next;

        while (c2.next != null) {
            c1 = c1.next;
            c2 = c2.next;
        }

        ListNode rnode = c1.next;
        c1.next = rnode.next;
        rnode.next = null;

        // delete rnode; // for c++.

        return head;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 == null ? l2 : l1;

        ListNode head = new ListNode(-1); // dummy Node.
        ListNode prev = head;

        ListNode c1 = l1, c2 = l2;
        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                prev.next = c1;
                prev = c1;
                c1 = c1.next;
            } else {
                prev.next = c2;
                prev = c2;
                c2 = c2.next;
            }
        }

        if (c2 != null)
            prev.next = c2;
        else
            prev.next = c1;

        return head.next;
    }

    // Leetcode 023.
    public ListNode mergeKLists(ListNode[] lists) { // O(KN).
        if (lists.length == 0)
            return null;
        ArrayList<ListNode> list = new ArrayList<>();
        for (int i = 0; i < lists.length; i++)
            list.add(lists[i]);

        while (list.size() != 1) {
            ListNode l1 = list.remove(list.size() - 1);
            ListNode l2 = list.remove(list.size() - 1);

            ListNode l3 = mergeTwoLists(l1, l2);
            list.add(l3);
        }

        return list.get(0);
    }

    public ListNode mergeKLists02(ListNode[] lists, int li, int ri) { // O(Nlogk)
        if (li == ri)
            return lists[li];

        int mid = (li + ri) / 2;

        ListNode l1 = mergeKLists02(lists, li, mid);
        ListNode l2 = mergeKLists02(lists, mid + 1, ri);
        ListNode finalList = mergeTwoLists(l1, l2);

        return finalList;
    }

    public ListNode mergeKLists02(ListNode[] lists) {
        if (lists.length == 0)
            return null;

        return mergeKLists02(lists, 0, lists.length - 1);
    }

    // Leetcode 141: linked-list-cycle.====================================

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }

        return fast == slow ? true : false;
    }

    // Leetcode 142.=====================================================
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }

        if (fast != slow)
            return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // Leetcode 160=========================================================
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode curr = headA;
        while (curr.next != null)
            curr = curr.next;

        curr.next = headB; // cycle.
        ListNode ans = detectCycle(headA);
        curr.next = null; // destroy cycle.
        return ans;
    }

    ListNode oH = null, oT = null;
    ListNode tH = null, tT = null;

    public void addFirstNode(ListNode node) {
        if (tT == null) {
            tH = node;
            tT = node;
        } else {
            node.next = tH;
            tH = node;
        }
    }

    public int length(ListNode node) {
        int len = 0;
        while (node != null) {
            node = node.next;
            len++;
        }
        return len;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 0 || k == 1)
            return head;

        int len = length(head);
        if (len < k)
            return head;

        ListNode curr = head;
        while (curr != null && len >= k) {
            int temp = k;
            while (temp-- > 0) {
                ListNode rnode = curr;
                curr = curr.next;
                rnode.next = null;

                addFirstNode(rnode);
            }

            if (oH == null) {
                oH = tH;
                oT = tT;
            } else {
                oT.next = tH;
                oT = tT;
            }

            tH = null;
            tT = null;

            len -= k;
        }

        oT.next = curr;
        return oH;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || n == m)
            return head;

        int i = 1;
        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            while (i >= m && i <= n) {
                ListNode rnode = curr;
                curr = curr.next;
                rnode.next = null;
                addFirstNode(rnode);
                i++;
            }

            if (i > n) {
                if (prev != null)
                    prev.next = tH;
                else
                    head = tH;
                tT.next = curr;
                break;
            }

            prev = curr;
            curr = curr.next;
            i++;
        }

        return head;
    }

    ListNode reverseLL(ListNode head) {
        if (head == null || head.next == null)
            return head;

        while (head != null) {
            ListNode rnode = head;
            head = head.next;
            rnode.next = null;
            addFirstNode(rnode);
        }

        return th;

    }

}
