public class questions {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode midNode2(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static ListNode midNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            ListNode forw = curr.next; // backup

            curr.next = prev; // link

            prev = curr; // move
            curr = forw;
        }

        return prev;
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverse(nhead);

        ListNode c1 = head, c2 = nhead;

        boolean res = true;
        while (c2 != null) {
            if (c1.val != c2.val) {
                res = false;
                break;
            }

            c1 = c1.next;
            c2 = c2.next;
        }

        nhead = reverse(nhead);
        mid.next = nhead;

        return res;
    }

    public static void fold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverse(nhead);

        ListNode c1 = head, c2 = nhead;
        while (c2 != null) {
            ListNode f1 = c1.next, f2 = c2.next; // backup

            c1.next = c2; // link
            c2.next = f1;

            c1 = f1; // move
            c2 = f2;
        }
    }

    public static void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(-1);
        ListNode p1 = l1, p2 = l2, c1 = head, c2 = head.next;

        while (c1 != null && c2 != null) {
            p1.next = c1;
            p2.next = c2;

            p1 = p1.next;
            p2 = p2.next;

            if (c2 != null) // for odd Len
                c1 = c2.next;
            if (c1 != null) // for even
                c2 = c1.next;
        }

        p1.next = null;
        p2 = reverse(l2.next);
        p1.next = p2;
    }

    public static void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(-1);
        ListNode p1 = l1, p2 = l2;

        p1.next = head;
        p2.next = head.next;

        p1 = p1.next;
        p2 = p2.next;

        while (p2 != null && p2.next != null) {
            ListNode f = p2.next;

            p1.next = f;
            p2.next = f.next;

            p1 = p1.next;
            p2 = p2.next;
        }

        p1.next = null;
        p2 = reverse(l2.next);
        p1.next = p2;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;

        ListNode head = null;
        for (ListNode node : lists) {
            head = mergeTwoLists(head, node);
        }

        return head;
    }

    // T : O(NlogkK), S : O(logK) -> N = k times of (avg length Of Linkedlist),
    // where k is length of lists.
    public static ListNode mergeKList(ListNode[] lists, int si, int ei) {
        if (si == ei)
            return lists[si];
        int mid = (si + ei) / 2;
        ListNode leftMergedList = mergeKList(lists, si, mid);
        ListNode rightMergedList = mergeKList(lists, mid + 1, ei);

        return mergeTwoLists(leftMergedList, rightMergedList);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        return mergeKList(lists, 0, lists.length - 1);
    }

    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode mid = midNode(head);
        ListNode nHead = mid.next;
        mid.next = null;

        return mergeTwoLists(mergeSort(head), mergeSort(nHead));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0)
            return head;

        ListNode slow = head, fast = head;
        while (n-- > 0) {
            fast = fast.next;
            if (fast == null && n > 0)
                return head;
        }

        if (fast == null) {
            ListNode rnode = slow;
            head = rnode.next;
            rnode.next = null;
            return head;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode rnode = slow.next;
        slow.next = rnode.next;
        rnode.next = null;

        return head;
    }

    public static ListNode segregateEvenOdd(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode even = new ListNode(-1);
        ListNode odd = new ListNode(-1);
        ListNode ep = even, op = odd, curr = head;

        while (curr != null) {
            if (curr.val % 2 != 0) {
                op.next = curr;
                op = op.next;
            } else {
                ep.next = curr;
                ep = ep.next;
            }
            curr = curr.next;
        }

        ep.next = odd.next;
        op.next = null;
        head = even.next;

        // even.next = odd.next = null;
        return head;
    }

    public static ListNode segregate01(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode one = new ListNode(-1);
        ListNode zero = new ListNode(-1);
        ListNode op = one, zp = zero, curr = head;

        while (curr != null) {
            if (curr.val != 0) {
                op.next = curr;
                op = op.next;
            } else {
                zp.next = curr;
                zp = zp.next;
            }
            curr = curr.next;
        }

        zp.next = one.next;
        op.next = null;

        head = zero.next;

        zero.next = one.next = null;
        return head;
    }

    public static ListNode segregate012(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode one = new ListNode(-1);
        ListNode zero = new ListNode(-1);
        ListNode two = new ListNode(-1);
        ListNode op = one, zp = zero, tp = two, curr = head;

        while (curr != null) {
            if (curr.val == 1) {
                op.next = curr;
                op = op.next;
            } else if (curr.val == 0) {
                zp.next = curr;
                zp = zp.next;
            } else {
                tp.next = curr;
                tp = tp.next;
            }
            curr = curr.next;
        }

        op.next = two.next;
        zp.next = one.next;
        tp.next = null;

        head = zero.next;
        zero.next = one.next = two.next = null;
        return head;
    }

    public static ListNode segregateOnLastIndex(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);
        ListNode sp = small, lp = large, curr = head;

        ListNode pivotNode = head;
        while (pivotNode.next != null)
            pivotNode = pivotNode.next;

        while (curr != null) {
            if (curr.val <= pivotNode.val) {
                sp.next = curr;
                sp = sp.next;
            } else {
                lp.next = curr;
                lp = lp.next;
            }
            curr = curr.next;
        }

        sp.next = large.next;
        lp.next = null;

        return sp;
    }

    public static ListNode segregate(ListNode head, int pivotIdx) {
        if (head == null || head.next == null)
            return head;

        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);
        ListNode sp = small, lp = large, curr = head;

        ListNode pivotNode = head;
        while (pivotIdx-- > 0)
            pivotNode = pivotNode.next;

        while (curr != null) {
            if (curr != pivotNode && curr.val <= pivotNode.val) {
                sp.next = curr;
                sp = sp.next;
            } else if (curr != pivotNode) {
                lp.next = curr;
                lp = lp.next;
            }
            curr = curr.next;
        }

        sp.next = pivotNode;
        pivotNode.next = large.next;
        lp.next = null;

        head = small.next;
        return head;
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode forw = curr.next;

            curr.next = prev;

            prev = curr;
            curr = forw;
        }

        return prev;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);

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

        head = reverse(head);
        l1 = reverse(l1);
        l2 = reverse(l2);

        return head;
    }

    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode dummy = new ListNode(-1);
        ListNode c1 = l1, c2 = l2, prev = dummy;
        int borrow = 0;
        while (c1 != null || c2 != null) {
            int diff = borrow + (c1 != null ? c1.val : 0) - (c2 != null ? c2.val : 0);
            if (diff < 0) {
                diff += 10;
                borrow = -1;
            } else
                borrow = 0;

            prev.next = new ListNode(diff);
            prev = prev.next;

            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        ListNode head = dummy.next;
        head = reverse(head);

        while (head != null && head.val == 0) // 1000000000 - 99999999 = 1, 999 - 999 = 0
            head = head.next;

        l1 = reverse(l1);
        l2 = reverse(l2);

        return head;
    }

    public static boolean isCyclePresentInLL(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow)
                return true;

        }

        return false;
    }

    public static ListNode CycleNode(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow)
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

    public static ListNode CycleNode2(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow)
                break;
        }

        if (fast != slow)
            return null;

        ListNode meetingNode = fast;
        int a = 0, b = 0, c = 0, bc = 0, nDash = 0, n = 0; // bc is (b + c)F

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;

            if (fast == meetingNode)
                nDash++;
            a++;
        }

        fast = meetingNode;
        fast = fast.next;

        bc = 1;
        while (fast != meetingNode) {
            fast = fast.next;
            bc++;
        }

        n = nDash + 1;
        c = a - bc * nDash;
        b = bc - c;

        System.out.println("Length Of Tail is:" + a);
        System.out.println("Length Of b is:" + b);
        System.out.println("Length Of c is:" + c);
        System.out.println("No Of rotation by fast pointer before meeting poiny:" + n);
        System.out.println("No Of rotation by fast pointer after meeting poiny:" + nDash);

        return slow;
    }

    public static ListNode IntersectionNodeInTwoLL(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode tail = headA;
        while (tail.next != null)
            tail = tail.next;

        tail.next = headB;
        ListNode ans = CycleNode(headA);
        tail.next = null;

        return ans;
    }

    public static int length(ListNode head) {
        ListNode curr = head;
        int len = 0;
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        return len;
    }

    public static ListNode IntersectionNodeInTwoLL(ListNode headA, ListNode headB) {
        int lenA = length(headA);
        int lenB = length(headB);

        ListNode biggerList = lenA > lenB ? headA : headB;
        ListNode smallerList = lenB < lenA ? headB : headA;

        int diff = Math.abs(lenA - lenB);
        while (diff-- > 0)
            biggerList = biggerList.next;

        while (biggerList != smallerList) {
            biggerList = biggerList.next;
            smallerList = smallerList.next;
        }

        return biggerList;
    }

    // K Reverse
    ListNode th = null, tt = null;

    public void addFirstNode(ListNode node) {
        if (th == null)
            th = tt = node;
        else {
            node.next = th;
            th = node;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head.next == null || k == 1)
            return head;
        int len = length(head);

        ListNode curr = head, ph = null, pt = null;

        while (curr != null && len >= k) {
            int itr = k;
            while (itr-- > 0) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirstNode(curr);
                curr = forw;
            }

            if (ph == null) {
                ph = th;
                pt = tt;
            } else {
                pt.next = th;
                pt = tt;
            }

            th = tt = null;
            len -= k;
        }

        pt.next = curr;
        return ph;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head.next == null || m == n)
            return head;
        int i = 1;
        ListNode curr = head, prev = null;
        while (curr != null) {
            while (i >= m && i <= n) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirstNode(curr);
                curr = forw;
                i++;
            }

            if (i > n) {
                if (prev == null) {
                    tt.next = curr;
                    return th;
                } else {
                    prev.next = th;
                    tt.next = curr;
                    return head;
                }
            }

            prev = curr;
            curr = curr.next;
            i++;
        }

        return null;
    }

    public static ListNode removeDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode curr = head.next, prev = head;

        while (curr != null) {
            while (curr != null && curr.val == prev.val) {
                ListNode forw = curr.next;
                curr.next = null;
                curr = forw;
            }

            prev.next = curr;
            prev = prev.next;
            if (curr != null)
                curr = curr.next;
        }

        return head;
    }

    public static ListNode removeDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(-1);
        ListNode curr = head.next, prev = dummy;
        prev.next = head;

        while (curr != null) {
            boolean isLoopRun = false;
            while (curr != null && curr.val == prev.next.val) {
                ListNode forw = curr.next;
                curr.next = null;
                curr = forw;
                isLoopRun = true;
            }

            if (isLoopRun) {
                prev.next = curr;
            } else {
                prev = prev.next;
                prev.next = curr;
            }

            if (curr != null)
                curr = curr.next;
        }

        return dummy.next;
    }

    // Copy List with Random Pointer
    public void copyNodes(Node head) {
        Node curr = head;
        while (curr != null) {
            Node forw = curr.next;
            Node newNode = new Node(curr.val);

            curr.next = newNode;
            newNode.next = forw;

            curr = forw;
        }
    }

    public void copyRandom(Node head) {
        Node curr = head;
        while (curr != null) {
            if (curr.random != null)
                curr.next.random = curr.random.next;

            curr = curr.next.next;
        }
    }

    public Node extractList(Node head) {
        Node dummy = new Node(-1);
        Node curr = head, prev = dummy;

        while (curr != null) {
            prev.next = curr.next;
            prev = prev.next;

            curr.next = curr.next.next;
            curr = curr.next;
        }

        return dummy.next;
    }

    public Node copyRandomList(Node head) {
        copyNodes(head);
        copyRandom(head);
        return extractList(head);

    }

}