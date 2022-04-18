import java.util.HashSet;

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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return head;

        ListNode slow = head, fast = head;
        while (n-- > 0)
            fast = fast.next;

        if (fast == null) {
            ListNode rn = head;
            head = head.next;
            rn.next = null;
            return head;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode rn = slow.next;
        slow.next = rn.next;
        rn.next = null;
        return head;
    }

    public void removeNthFromEnd_followUp(ListNode head, int n) {
        if (head == null)
            return;

        ListNode slow = head, fast = head;
        while (n-- > 0)
            fast = fast.next;

        if (fast == null) {
            head.val = head.next.val;
            ListNode rn = slow.next;
            slow.next = rn.next;
            rn.next = null;
            return;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode rn = slow.next;
        slow.next = rn.next;
        rn.next = null;
    }

    // 2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
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

        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode head = dummy.next;
        dummy.next = null;

        head = reverseList(head);
        return head;
    }

    // pepcoding

    public static int isBiggerList(ListNode l1, ListNode l2) {
        int len1 = len(l1), len2 = len(l2);
        if (len1 == len2) {
            ListNode c1 = l1, c2 = l2;
            while (c1 != null) {
                if (c1.val != c2.val)
                    return c1.val - c2.val;
                c1 = c1.next;
                c2 = c2.next;
            }
        }

        return len1 - len2;
    }

    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        if (isBiggerList(l1, l2) < 0) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }

        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode dummy = new ListNode(-1), prev = dummy, c1 = l1, c2 = l2;

        int borrow = 0;
        while (c1 != null || c2 != null) {
            int val = borrow + (c1 != null ? c1.val : 0) - (c2 != null ? c2.val : 0);
            if (val < 0) {
                val += 10;
                borrow = -1;
            } else {
                borrow = 0;
            }

            prev.next = new ListNode(val);

            prev = prev.next;
            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode head = dummy.next;
        dummy.next = null;

        head = reverseList(head);
        c1 = head;

        while (c1.next != null) {
            if (c1.val != 0) {
                break;
            }
            c1 = c1.next;
        }

        return c1;
    }

    public static ListNode removeDuplicates(ListNode head) {
        if (head == null && head.next == null)
            return head;
        ListNode prev = head, curr = head.next;

        while (curr != null) {
            if (prev.val != curr.val) {
                prev.next = curr;
                prev = prev.next;
            }

            curr = curr.next;
        }
        prev.next = curr;

        return head;
    }

    public static ListNode removeDuplicates_02(ListNode head) {
        if (head == null && head.next == null)
            return head;
        ListNode dummy = new ListNode(-1), prev = dummy, curr = head.next;
        prev.next = head;

        while (curr != null) {
            boolean isSequence = false;
            while (curr != null && prev.next.val == curr.val) {
                isSequence = true;
                curr = curr.next;
            }

            if (isSequence) {
                prev.next = curr;
            } else {
                prev = prev.next;
            }

            if (curr != null)
                curr = curr.next;
        }

        return dummy.next;
    }

    // pepcoding
    public static ListNode segregateEvenOdd(ListNode head) {
        return null;
    }

    // 328
    public ListNode oddEvenList(ListNode head) {
        return null;
    }

    // 148
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode mid = midNode(head), nHead = mid.next;
        mid.next = null;
        return mergeTwoLists(sortList(head), sortList(nHead));
    }

    // 23
    public ListNode mergeKLists_bruteForce(ListNode[] lists) {
        ListNode ans = null;
        for (ListNode list : lists)
            ans = mergeTwoLists(ans, list);

        return ans;
    }

    public ListNode mergeKLists(ListNode[] lists, int si, int ei) {
        if (si >= ei)
            return si > ei ? null : lists[si];
        int mid = (si + ei) / 2;

        return mergeTwoLists(mergeKLists(lists, si, mid), mergeKLists(lists, mid + 1, ei));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        return mergeKLists(lists, 0, n - 1);
    }

    // 25
    ListNode th = null, tt = null;

    private void addFirst(ListNode node) {
        if (th == null)
            th = tt = node;
        else {
            node.next = th;
            th = node;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1)
            return head;

        int len = length(head);
        ListNode ah = null, at = null, curr = head;
        while (len >= k) {
            int tempK = k;
            while (tempK-- > 0) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = forw;
            }

            if (ah == null) {
                ah = th;
                at = tt;
            } else {
                at.next = th;
                at = tt;
            }

            th = tt = null;
            len -= k;
        }
        at.next = curr;
        return ah;
    }

    // 92
    public ListNode reverseBetween(ListNode head, int n, int m) {
        if (head == null || head.next == null || n == m)
            return head;
        int idx = 1;
        ListNode prev = null, curr = head;
        while (curr != null) {
            while (curr != null && idx >= n && idx <= m) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = forw;
                idx++;
            }

            if (idx > m) {
                if (prev != null) {
                    prev.next = th;
                    tt.next = curr;
                    return head;
                } else {
                    tt.next = curr;
                    return th;
                }
            }

            idx++;
            prev = curr;
            curr = curr.next;
        }
        return head;
    }

    // 817
    public int numComponents(ListNode head, int[] nums) {
        if (head == null || nums.length == 0)
            return 0;

        HashSet<Integer> set = new HashSet<>();
        for (int ele : nums)
            set.add(ele);

        ListNode curr = head;
        int components = 0;
        while (curr != null) {
            if (set.contains(curr.val) && (curr.next == null || !set.contains(curr.next.val)))
                components++;
            curr = curr.next;
        }
        return components;
    }

    // 1171
    public ListNode removeZeroSumSublists(ListNode head) {

    }

    // 138
    public Node copyRandomList(Node head) {

    }

}