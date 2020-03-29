public class l002_QuestionSolution {

	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
    }
    
    //leetcode 234
    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null) return true;

        ListNode mid=mid(head);
        ListNode rev=reverse(mid);
        while(rev!=null)
        { 
            if(head.val!=rev.val) return false;
            head=head.next;
            rev=rev.next;
        }

        return true;
    }

    //leetcode 143
    public void reorderList(ListNode head) {
        if(head==null || head.next==null) return;
        
        ListNode mid=mid_(head);
        ListNode nhead=mid.next;
        mid.next=null;

        nhead=reverse(nhead);
        while(head!=null && nhead!=null){
            ListNode forw1=head.next;
            ListNode forw2=nhead.next;

            //links
            head.next=nhead; 
            nhead.next=forw1;

            //move forward
            head=forw1;
            nhead=forw2;
        }
    }

    //leetcode 61
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next==null || k==0) return head; 
        int size=0;
        ListNode curr=head,tail=null;
        while(curr!=null){
            tail=curr;
            curr=curr.next;
            size++;
        }

        k%=size;  //k in range
        if(k<0) k+=size;  // k become positive.

        if(k==0) return head;

        curr=head;
        int count=1;
        int n=size-k;
        
        while(count<n){
            curr=curr.next;
            count++;
        }

        tail.next=head;
        head=curr.next;
        curr.next=null;
        
        return head;
    }


    //leetcode 141 
    boolean hasCycle(ListNode head) {
        if(head==null || head.next==null) return false;

        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast) return true;
        }

        return false;
    }

    //Leetcode 142
    public ListNode detectCycle(ListNode head) {
        
    }



    //leetcode 160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
    }



    public ListNode mid(ListNode node){
        ListNode slow=node;
        ListNode fast=node;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    public ListNode mid_(ListNode node){
        ListNode slow=node;
        ListNode fast=node;
        while(fast!=null && fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }


    public ListNode reverse(ListNode node){
        ListNode prev=null;
        while(node!=null){
            ListNode forw=node.next;
            node.next=prev;

            prev=node;
            node=forw;
        }
        return prev;
    }



}