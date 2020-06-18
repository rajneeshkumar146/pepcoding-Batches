public class Q002Leetcode092 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

    }

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

    public ListNode reverseBetween(ListNode node, int m, int n) {
        if (node.next == null || m == n)
            return node;

        int idx = 1;

        ListNode curr = node;
        ListNode prev = null;
        ListNode nhead = node;

        while (curr != null) {
            while (idx >= m && idx <= n) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirstNode(curr);
                

                curr = forw;
                idx++;
            }

            if (tt != null) {
                tt.next = curr;
                if (prev != null)
                    prev.next = th;
                else
                    nhead = th;
                break;
            }

            prev = curr;
            curr = curr.next;
            idx++;
        }

        return nhead;
    }
}