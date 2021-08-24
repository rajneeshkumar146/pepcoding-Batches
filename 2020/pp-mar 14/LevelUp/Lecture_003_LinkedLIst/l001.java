import java.util.List;

public class l001 {

    public static class ListNode {
        int val = 0;
        ListNode next = null;
        ListNode random = null;

        ListNode(int val) {
            this.val = val;
        }
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

        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode forw = curr.next; // backup

            curr.next = prev; // link

            prev = curr;
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

        boolean flag = true;
        while (c1 != null && c2 != null) {
            if (c1.val != c2.val) {
                flag = false;
                break;
            }

            c1 = c1.next;
            c2 = c2.next;
        }

        nhead = reverse(nhead);
        mid.next = nhead;

        return flag;
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
            // backup
            ListNode f1 = c1.next, f2 = c2.next;

            // links
            c1.next = c2;
            c2.next = f1;

            // move
            c1 = f1;
            c2 = f2;
        }
    }

    public static void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode d1 = new ListNode(-1), d2 = new ListNode(-1), c1 = d1, c2 = d2, c = head;

        while (c != null) {
            c1.next = c;
            c2.next = c.next;

            c1 = c1.next;
            c2 = c2.next;

            c = c.next;
            if (c != null)
                c = c.next;
        }

        c1.next = null;
        ListNode rhead = reverse(d2.next);
        c1.next = rhead;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return head;

        ListNode slow = head, fast = head;
        while (n-- > 0)
            fast = fast.next;

        if (fast == null) {
            ListNode rnode = slow;
            head = head.next;
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

        ListNode even = new ListNode(-1), odd = new ListNode(-1), ep = even, op = odd, curr = head;

        while (curr != null) {
            if ((curr.val & 1) == 0) {
                ep.next = curr;
                ep = ep.next;
            } else {
                op.next = curr;
                op = op.next;
            }

            curr = curr.next;
        }

        ep.next = op.next = null;
        ep.next = odd.next;

        return even.next;
    }

    public static ListNode segregate01(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode zero = new ListNode(-1), one = new ListNode(-1), zp = zero, op = one, curr = head;

        while (curr != null) {
            if (curr.val == 0) {
                zp.next = curr;
                zp = zp.next;
            } else {
                op.next = curr;
                op = op.next;
            }

            curr = curr.next;
        }

        zp.next = op.next = null;
        zp.next = one.next;

        return zero.next;
    }

    public static ListNode segregate012(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode zero = new ListNode(-1), one = new ListNode(-1), two = new ListNode(-1), zp = zero, op = one, tp = two,
                curr = head;

        while (curr != null) {
            if (curr.val == 0) {
                zp.next = curr;
                zp = zp.next;
            } else if (curr.val == 1) {
                op.next = curr;
                op = op.next;
            } else {
                tp.next = curr;
                tp = tp.next;
            }

            curr = curr.next;
        }

        zp.next = op.next = tp.next = null;

        op.next = two.next;
        zp.next = one.next;

        return zero.next;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 != null ? l1 : l2;

        ListNode dummy = new ListNode(-1), p = dummy, c1 = l1, c2 = l2;

        while (c1 != null && c2 != null) {
            if (c1.val < c2.val) {
                p.next = c1;
                c1 = c1.next;
            } else {
                p.next = c2;
                c2 = c2.next;
            }

            p = p.next;
        }

        p.next = c1 != null ? c1 : c2;
        return dummy.next;
    }

    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode mid = midNode(head);
        ListNode nHead = mid.next;
        mid.next = null;

        return mergeTwoLists(mergeSort(head), mergeSort(nHead));
    }

    public static ListNode mergeKLists(ListNode[] lists, int si, int ei) {
        if (si == ei)
            return lists[si];

        int mid = (si + ei) / 2;
        ListNode leftList = mergeKLists(lists, si, mid);
        ListNode rightList = mergeKLists(lists, mid + 1, ei);

        return mergeTwoLists(leftList, rightList);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;

        return mergeKLists(lists, 0, lists.length - 1);
    }

    private static ListNode th = null, tt = null;

    private static void addFirstNode(ListNode node) {
        if (th == null) {
            th = tt = node;
        } else {
            node.next = th;
            th = node;
        }
    }

    public static int length(ListNode head) {
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

    public static ListNode reverseInKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1)
            return head;

        int len = length(head);
        ListNode curr = head, oh = null, ot = null;
        while (len >= k) {
            int tempK = k;
            while (tempK-- > 0) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirstNode(curr);
                curr = forw;
            }

            if (oh == null) {
                oh = th;
                ot = tt;
            } else {
                ot.next = th;
                ot = tt;
            }

            th = tt = null;
            len -= k;
        }

        ot.next = curr;
        return oh;
    }

    public static ListNode reverseInRange(ListNode head, int n, int m) {
        if (head == null || head.next == null || n == m)
            return head;

        ListNode dummy = new ListNode(-1), prev = dummy, curr = head;
        prev.next = head;
        int i = 1;
        while (i <= m) {
            while (i >= n && i <= m) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirstNode(curr);
                curr = forw;
                i++;
            }

            if (i > m) {
                prev.next = th;
                tt.next = curr;
                break;
            }

            i++;
            prev = curr;
            curr = curr.next;
        }

        return dummy.next;
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

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 != null ? l1 : l2;

        ListNode dummy = new ListNode(-1), prev = dummy, c1 = reverse(l1), c2 = reverse(l2);
        int carry = 0;
        while (c1 != null || c2 != null || carry != 0) {
            int sum = carry + (c1 != null ? c1.val : 0) + (c2 != null ? c2.val : 0);
            int digit = sum % 10;
            carry = sum / 10;

            prev.next = new ListNode(digit);
            prev = prev.next;

            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        return reverse(dummy.next);
    }

    public static int getLength(ListNode head) {
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

    public static boolean isBiggerList(ListNode l1, ListNode l2) {
        int len1 = getLength(l1), len2 = getLength(l2);
        if (len1 > len2)
            return true;
        else if (len1 < len2)
            return false;

        ListNode c1 = l1, c2 = l2;
        while (c1 != null) {
            if (c1.val > c2.val)
                return true;
            else if (c1.val < c2.val)
                return false;

            c1 = c1.next;
            c2 = c2.next;
        }

        return true;
    }

    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = null, c2 = null;
        if (isBiggerList(l1, l2)) {
            c1 = reverse(l1);
            c2 = reverse(l2);
        } else {
            c1 = reverse(l2);
            c2 = reverse(l1);
        }

        ListNode dummy = new ListNode(-1), prev = dummy;
        int borrow = 0;
        while (c1 != null || c2 != null) {
            int diff = borrow + (c1 != null ? c1.val : 0) - (c2 != null ? c2.val : 0);
            if (diff < 0) {
                borrow = -1;
                diff += 10;
            } else {
                borrow = 0;
            }

            prev.next = new ListNode(diff);
            prev = prev.next;

            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        ListNode ans = reverse(dummy.next);
        prev = dummy;
        prev.next = null;
        ListNode c = ans;
        while (c != null) {
            if (c.val != 0) {
                prev.next = c;
                break;
            }

            ListNode forw = c.next;
            c.next = null;
            c = forw;
        }

        return dummy.next != null ? dummy.next : new ListNode(0);
    }

    public static void addList(ListNode prev, ListNode list) {
        int carry = 0;
        while (list != null || carry != 0) {
            int sum = carry + (list != null ? list.val : 0) + (prev.next != null ? prev.next.val : 0);
            int digit = sum % 10;
            carry = sum / 10;

            if (prev.next != null)
                prev.next.val = digit;
            else
                prev.next = new ListNode(digit);

            prev = prev.next;
            if (list != null)
                list = list.next;
        }
    }

    public static ListNode multiplyDigit(ListNode list, int d) {
        ListNode dummy = new ListNode(-1), curr = list, prev = dummy;

        int carry = 0;
        while (curr != null || carry != 0) {
            int ans = carry + (curr != null ? curr.val : 0) * d;
            int digit = ans % 10;
            carry = ans / 10;

            prev.next = new ListNode(digit);
            prev = prev.next;

            if (curr != null)
                curr = curr.next;
        }

        return dummy.next;
    }

    public static ListNode multiplyTwoLL(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode ans = new ListNode(-1), prev = ans;
        while (l2 != null) {
            ListNode multipliedList = multiplyDigit(l1, l2.val);
            addList(prev, multipliedList);
            prev = prev.next;
            l2 = l2.next;
        }

        return reverse(ans.next);
    }

    public static void copyList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            ListNode forw = curr.next;
            ListNode node = new ListNode(curr.val);

            curr.next = node;
            node.next = forw;

            curr = forw;
        }
    }

    public static void copyRandoms(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }

            curr = curr.next.next;
        }
    }

    public static ListNode extractList(ListNode head) {
        ListNode curr = head, dummy = new ListNode(-1), prev = dummy;
        while (curr != null) {
            ListNode forw = curr.next.next; // backup

            prev.next = curr.next; // links
            curr.next = forw;

            curr = forw; // move
            prev = prev.next;
        }

        return dummy.next;
    }

    public static ListNode copyRandomList(ListNode head) {
        copyList(head);
        copyRandoms(head);
        return extractList(head);
    }

    // 141 Cycle
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }

        return false;
    }

    // 142
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                break;
        }

        if (slow != fast)
            return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    // 160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode tail = headA;
        while (tail.next != null) {
            tail = tail.next;
        }

        tail.next = headB;

        ListNode ans = detectCycle(headA);

        tail.next = null;

        return ans;
    }

    // All Variable
    public int getCycleLen(ListNode mp) {
        int cycleLen = 1;
        ListNode curr = mp.next;

        while (curr != mp) {
            curr = curr.next;
            cycleLen++;
        }

        return cycleLen;
    }

    public ListNode cycleVariable(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                break;
        }

        if (slow != fast)
            return null;

        slow = head;
        ListNode mp = fast; // meetingPoint
        int cycleCount = 0;
        int A = 0;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;

            if (mp == fast)
                cycleCount++;
            A++;
        }

        int cycleLen = getCycleLen(mp);
        int m = 0, C = 0, B = 0;
        if (A != 0 && A % cycleLen == 0) {
            m = cycleCount - 1;
            B = cycleLen;
        } else {
            m = cycleCount + 1;
            C = A - cycleCount * cycleLen;
            B = cycleLen - C;
        }

        return slow;
    }

    public static ListNode segregateOnLastIndex(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode pivotNode = head;
        while (pivotNode.next != null)
            pivotNode = pivotNode.next;

        ListNode smaller = new ListNode(-1), larger = new ListNode(-1), sp = smaller, lp = larger, curr = head;
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

        sp.next = lp.next = null;
        sp.next = larger.next;

        return pivotNode;
    }

    public static ListNode segregate(ListNode head, int pivotIdx) {
        if (head == null || head.next == null)
            return head;

        ListNode pivotNode = head;
        while (pivotIdx-- > 0)
            pivotNode = pivotNode.next;

        ListNode smaller = new ListNode(-1), larger = new ListNode(-1), sp = smaller, lp = larger, curr = head;
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

        sp.next = lp.next = pivotNode.next = null;
        sp.next = pivotNode;
        pivotNode.next = larger.next;

        return smaller.next;
    }

    // Quick Sort

    public static ListNode[] getSegregateNodes(ListNode head, int pivotIdx) {
        if (head == null || head.next == null)
            return new ListNode[] { null, head, null };

        ListNode pivotNode = head;
        while (pivotIdx-- > 0)
            pivotNode = pivotNode.next;

        ListNode smaller = new ListNode(-1), larger = new ListNode(-1), sp = smaller, lp = larger, curr = head;
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

        sp.next = lp.next = pivotNode.next = null;

        return new ListNode[] { smaller.next, pivotNode, larger.next };
    }

    public static ListNode[] mergeLists(ListNode[] left, ListNode pivotNode, ListNode[] right) {
        ListNode fh = null, ft = null;
        if (left[0] != null && right[0] != null) {
            fh = left[0];
            left[1].next = pivotNode;
            pivotNode.next = right[0];
            ft = right[1];
        } else if (left[0] == null && right[0] == null) {
            fh = ft = pivotNode;
        } else if (left[0] == null) {
            fh = pivotNode;
            pivotNode.next = right[0];
            ft = right[1];
        } else {
            fh = left[0];
            left[1].next = pivotNode;
            ft = pivotNode;
        }

        return new ListNode[] { fh, ft };
    }

    // {head,tail}
    public static ListNode[] quickSort(ListNode head) {
        if (head == null || head.next == null)
            return new ListNode[] { head, head };

        int len = getLength(head);
        ListNode[] segregateNodes = getSegregateNodes(head, len / 2);

        ListNode[] left = quickSort(segregateNodes[0]);
        ListNode[] right = quickSort(segregateNodes[2]);

        return mergeLists(left, segregateNodes[1], right);
    }

    public static void main(String[] args) {

    }
}