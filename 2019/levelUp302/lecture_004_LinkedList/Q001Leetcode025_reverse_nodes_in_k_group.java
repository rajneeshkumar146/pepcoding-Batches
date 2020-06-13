public class Q001Leetcode025_reverse_nodes_in_k_group {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

    }

    ListNode oh = null;
    ListNode ot = null;

    ListNode th = null;
    ListNode tt = null;

    public void addFirstNode(ListNode node) {
        if (th == null) {
            th = node;
            tt = node;
        } else {
            node.next = th;
            th = node;
        }
    }

    public int len(ListNode node) {
        int l = 0;
        while (node != null) {
            l++;
            node = node.next;
        }
        return l;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 0 || k == 1)
            return head;

        int len = len(head);
        if (len < k)
            return head;

        ListNode curr = head;
        while (curr != null) {
            int tk = k;

            while (tk-- > 0) {
                ListNode forw = curr.next;

                curr.next = null;
                addFirstNode(curr);

                curr = forw;
            }

            len -= k;

            if (ot == null) {
                oh = th;
                ot = tt;
            } else {
                ot.next = th;
                ot = tt;
            }

            th = null;
            tt = null;

            if (len < k) {
                ot.next = curr;
                curr = null;
            }
        }

        return oh;
    }

    public ListNode reverseKGroup_02(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;

        int len = len(head);

        if (len < k)
            return head;

        ListNode nhead = null;
        ListNode ntail = head;

        while (head != null) {
            int temp = k;
            ListNode ttail = null;
            ListNode rev = null;

            if (len >= k) {
                while (head != null && temp != 0) {
                    if (ttail == null) {
                        ttail = head;
                    }

                    ListNode forw = head.next;
        
                    head.next = rev;
                    
                    rev = head;
                    head = forw;

                    temp--;
                }
                len -= k;

                if (nhead == null) {
                    nhead = rev;
                } else {
                    ntail.next = rev;
                    ntail = ttail;
                }
            } else {
                ntail.next = head;
                break;
            }

        }

        return nhead;

    }

}