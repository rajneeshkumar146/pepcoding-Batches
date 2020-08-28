public class questions{
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    
    public ListNode middleNode_(ListNode head) {
        if(head==null) return head;

        ListNode slow = head;
        ListNode fast = head;

        while(fast.next!=null && fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }

    //Leetcode 876
    public ListNode middleNode(ListNode head) {
        if(head==null || head.next==null) return head;

        ListNode slow = head;
        ListNode fast = head;

        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }

    //Leetcode 206
    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;
        ListNode forw = null;
        
        while(curr!=null){
            forw = curr.next;   // backup

            curr.next = prev;   // connections

            prev = curr;        // Movement
            curr = forw;
        }

        return prev;
    }

    public ListNode reverseList(ListNode head) {
        if(head==null || head.next == null) return head;

        ListNode nHead = null;

        while(curr!=null){
            Node rn = curr;
            curr = curr.next;
            rn.next = null;

            if(nHead==null) head = rn;
            else {
                rn.next = head;
                head = rn;
            }
        }

        return nHead;
    }

    //Leetcode 234
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode mid = middleNode_(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverseList(nhead);

        ListNode curr1 = head;
        ListNode curr2 = nhead;

        boolean res = true;
        while(curr1!=null && curr2!=null){
            if(curr1.data != curr2.data){
                res = false;
                break;
            } 

            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        nhead = reverseList(nhead);
        mid.next = nhead;

        return res;
    }

    //Leetcode 21
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) return l1!=null? l1: l2;

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;

        ListNode c1 = l1;
        ListNode c2 = l2;

        while(c1!=null && c2!=null){
            if(c1.val < c2.val){
                prev.next = c1;
                c1 = c1.next;
            }else{
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }

        if(c1!=null) prev.next = c1;
        else if(c2!=null) prev.next = c2;

        return dummy.next;
    }

    //Leetcode 328
    public ListNode oddEvenList(ListNode head) {
        if(head==null || head.next == null) return head;

        ListNode eHead = head.next;
        ListNode c1 = head;
        ListNode c2 = eHead;

        while(c1.next != null && c2.next != null){

            c1.next = c2.next;
            c1 = c1.next;

            c2.next = c1.next;
            c2 = c2.next;
        } 

        c1.next = eHead;
        return head;
    }

    //https://www.geeksforgeeks.org/segregate-even-and-odd-elements-in-a-linked-list/

    ListNode OddEvenListByValue(ListNode head){
        if(head==null || head.next == null) return head;

        ListNode oHead = new ListNode(-1);
        ListNode op = oHead;

        ListNode eHead = new ListNode(-1);
        ListNode ep = eHead;

        ListNode curr = head;
        while(curr!=null){
            if(curr.val%2 == 0){
                ep.next = curr;
                ep = ep.next;
            }else{
                op.next = curr;
                op = op.next;
            }

            curr = curr.next;
        }

        ep.next = null;
        op.next = null;

        ep.next = ohead.next;
        return eHead.next; 
    }

    //Leetcode 61
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next == null || k == 0) return head;

        ListNode c1 = head;
        ListNode c2 = head;
        int len = 0;
        while(c1!=null){
            len++;
            c1 = c1.next;
        }

        c1 = head;
        k = k % len;
        
        while(k-->0) c2 = c2.next;

        while(c2.next != null){
            c1 = c1.next;
            c2 = c2.next;
        }

        c2.next = head;
        head = c1.next;
        c1.next = null;

        return head;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return null;

        ListNode c1 = head, c2 = head;
        while(n-->0) c2 = c2.next;

        if(c2 == null) return head.next;

        while(c2.next!=null){
            c1 = c1.next;
            c2 = c2.next;
        }

        c1.next = c1.next.next;
        return head;
    }

    //Leetcode 143
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;
        
        ListNode mid = middleNode_(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverseList(nhead);

        ListNode c1 = head;
        ListNode c2 = nhead;

        while(c1 != null && c2 != null){
            ListNode f1 = c1.next;
            ListNode f2 = c2.next;

            c1.next = c2;
            c2.next = f1;

            c1 = f1;
            c2 = f2;
        }
    }

    //Leetcode 148
    public ListNode sortList(ListNode head) {
        if(head==null || head.next == null) return head;

        ListNode mid = middleNode_(head);
        ListNode nhead = mid.next;
        mid.next = null;

        return mergeTwoLists(sortList(head),sortList(nhead));
    }

    //Leetcode 23
    public ListNode mergeKLists_(ListNode[] lists,int si,int ei) {
        if(si == ei) return lists[si];
        int mid = (si+ei)/2;
        return mergeTwoLists(mergeKLists_(lists,si,mid). mergeKLists_(list,mid+1,ei));
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;

        return mergeKLists_(lists,0,lists.length-1);
    }
}

