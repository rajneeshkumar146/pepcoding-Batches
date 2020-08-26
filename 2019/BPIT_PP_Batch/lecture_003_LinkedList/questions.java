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





















}

