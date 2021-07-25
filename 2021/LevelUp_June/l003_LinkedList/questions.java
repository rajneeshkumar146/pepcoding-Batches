public class questions {

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    // T: O(n), S: O(1)
    public static ListNode midNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;

        }
        return slow;
    }

    // T: O(n), S: O(1)
    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode forw = curr.next; // backup

            curr.next = prev; // link

            prev = curr; // move
            curr = forw;
        }

        return prev;
    }

    // T: O(n), S: O(1)
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;
        nhead = reverse(nhead);

        boolean flag = true;
        ListNode c1 = head, c2 = nhead;
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

    // T: O(n), S: O(1)
    public static void fold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;
        nhead = reverse(nhead);

        ListNode c1 = head, c2 = nhead;

        while (c2 != null) {
            ListNode f1 = c1.next, f2 = c2.next;

            c1.next = c2;
            c2.next = f1;

            c1 = f1;
            c2 = f2;
        }
    }

    // T: O(n), S: O(1)
    public static void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode c1 = head, c2 = head.next, nHead = c2;
        while (c2 != null && c2.next != null) {
            ListNode f1 = c2.next;

            c1.next = f1;
            c2.next = f1.next;

            c1 = c1.next; // c1 = f1;
            c2 = c2.next; // c2 = c1.next;
        }

        c1.next = null;
        nHead = reverse(nHead);
        c1.next = nHead;
    }

    // T: O(n + m), S: O(1)
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
        ListNode head = dummy.next;
        dummy.next = null; // delete dummy;
        return head;
    }

    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode mid = midNode(head);
        ListNode nHead = mid.next;
        mid.next = null;

        return mergeTwoLists(mergeSort(head), mergeSort(nHead));
    }

    public ListNode mergeKSortedList_01(ListNode[] arr) {
        ListNode res = null;
        for (ListNode list : arr) {
            res = mergeTwoLists(res, list);
        }

        return res;
    }

    public ListNode getTail(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode curr = head;
        while (curr.next != null)
            curr = curr.next;

        return curr;
    }

    public ListNode mergeKSortedList_02(ListNode[] arr) {
        if (arr.length == 0)
            return null;

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        for (int i = 0; i < arr.length; i++) {
            ListNode tail = getTail(arr[i]);
            if (tail != null) {
                prev.next = arr[i];
                prev = tail;
            }
        }

        return mergeSort(dummy.next);
    }

    public ListNode mergeKSortedList_03(ListNode[] arr, int si, int ei) {
        if (si == ei)
            return arr[si];

        int mid = (si + ei) / 2;
        return mergeTwoLists(mergeKSortedList_03(arr, si, mid), mergeKSortedList_03(arr, mid + 1, ei));
    }

    public static ListNode segregateOnLastIndex(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode pivotNode = getTail(head);

        ListNode large = new ListNode(-1), small = new ListNode(-1), sp = small, lp = large, curr = head;
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
        sp.next = large.next;
        return small.next;
    }

    public static ListNode[] segregate(ListNode head, int pivotIdx) {
        ListNode pivotNode = head;
        while (pivotIdx-- > 0)
            pivotNode = pivotNode.next;

        ListNode large = new ListNode(-1), small = new ListNode(-1), sp = small, lp = large, curr = head;
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
        // sp.next = pivotNode;
        // pivotNode.next = large.next;
        // return small.next;

        ListNode leftHead = small.next == pivotNode ? null : small.next;
        ListNode rightHead = large.next;
        return new ListNode[] { leftHead, pivotNode, rightHead };
    }

    public static ListNode[] mergeLists(ListNode[] left, ListNode pivotNode, ListNode[] right) {
        ListNode[] myRes = new ListNode[2];
        if (left[0] != null && right[0] != null) {
            myRes[0] = left[0];
            myRes[1] = right[1];

            left[1].next = pivotNode;
            pivotNode.next = right[0];
        } else if (right[0] != null) {
            myRes[0] = pivotNode;
            myRes[1] = right[1];

            pivotNode.next = right[0];
        } else {
            myRes[0] = left[0];
            myRes[1] = pivotNode;

            left[1].next = pivotNode;
        }

        return myRes;
    }

    public static int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }

        return len;
    }

    // {head, tail}
    public static ListNode[] quickSort_(ListNode head) {
        if (head == null || head.next == null)
            return new ListNode[] { head, head };

        int len = getLength(head);
        int pivotIndex = len / 2; // 0, len - 1, len/2,
        ListNode[] partition = segregate(head, pivotIndex);

        ListNode[] left = quickSort_(partition[0]);
        ListNode[] right = quickSort_(partition[2]);

        return mergeLists(left, partition[1], right);
    }

    public static ListNode quickSort(ListNode head) {
        return quickSort_(head)[0];
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy, forw = dummy;
        while (n-- > 0)
            forw = forw.next;

        while (forw.next != null) {
            forw = forw.next;
            prev = prev.next;
        }

        ListNode dNode = prev.next;
        prev.next = dNode.next;

        dNode.next = null; // delete dNode

        return dummy.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 != null ? l1 : l2;

        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode dummy = new ListNode(-1), prev = dummy, c1 = l1, c2 = l2;
        int carry = 0;

        while (c1 != null || c2 != null || carry != 0) {
            int sum = carry + (c1 != null ? c1.val : 0) + (c2 != null ? c2.val : 0);
            carry = sum / 10;

            prev.next = new ListNode(sum % 10);
            prev = prev.next;

            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        return reverse(dummy.next);
    }

    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        if (l2 == null)
            return l1;
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode dummy = new ListNode(-1), prev = dummy, c1 = l1, c2 = l2;

        int borrow = 0;
        while (c1 != null) {
            int sub = borrow + c1.val - (c2 != null ? c2.val : 0);
            if (sub < 0) {
                borrow = -1;
                sub += 10;
            } else
                borrow = 0;

            prev.next = new ListNode(sub);
            prev = prev.next;

            c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        ListNode head = reverse(dummy.next), curr = head;
        while (curr != null && curr.val == 0) {
            ListNode forw = curr.next;
            curr.next = null;
            curr = forw;
        }

        return curr != null ? curr : new ListNode(0);
    }

    public static ListNode isCyclePresentInLL(ListNode head) {
        if (head == null && head.next == null)
            return null;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast)
                break;
        }

        return fast;
    }

    public static ListNode CycleNode(ListNode head) {

        ListNode meetingPoint = isCyclePresentInLL(head);
        if (meetingPoint == null || meetingPoint.next == null)
            return null;

        ListNode slow = head, fast = meetingPoint;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public static void allVariablesOfCycle(ListNode head) {
        ListNode meetingPoint = isCyclePresentInLL(head);
        if (meetingPoint == null || meetingPoint.next == null)
            return;

        ListNode slow = head, fast = meetingPoint, intersectionPoint = null;
        ;
        int A = 0, mDash = 0;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;

            if (fast == meetingPoint)
                mDash++;
            A++;
        }

        intersectionPoint = slow;
        slow = meetingPoint;
        slow = slow.next;
        int cycleLen = 1;
        while (slow != meetingPoint) {
            slow = slow.next;
            cycleLen++;
        }

        int B = 0, C = 0, m = 0;
        if (A != 0 && mDash == 0 && meetingPoint == intersectionPoint) {
            B = cycleLen;
        } else {
            C = A - mDash * cycleLen;
            B = cycleLen - C;
            m = mDash + 1; // resolved. conclusion : A >= C
        }
    }

    public static ListNode removeDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = head, curr = head.next;
        while (curr != null) {
            while (curr != null && prev.val == curr.val) {
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

        ListNode dummy = new ListNode(-1), prev = dummy, curr = head;
        prev.next = curr;
        while (curr != null) {
            boolean isLoopRun = false;
            while (curr.next != null && prev.next.val == curr.next.val) {
                curr = curr.next;
                isLoopRun = true;
            }

            prev.next = curr.next;
            if (!isLoopRun)
                prev = prev.next;
            curr = curr.next;
        }

        return dummy.next;
    }

}