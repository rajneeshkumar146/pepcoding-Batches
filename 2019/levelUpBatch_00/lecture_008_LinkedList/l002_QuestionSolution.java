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
        if(head==null || head.next==null) return null;

        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast) break;  // means cycle.
        }

        if(fast!=slow) return null;  //no cycle.

        slow=head;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }

        return slow;  // intersection point.
    }



    //leetcode 160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        Node tail=null;
        Node curr=headA;
        while(curr!=null){
            tail=curr;
            curr=curr.next;
        }

        tail.next=headB;
        return detectCycle(headA);
    }

    //leetcode 148
    public ListNode sortList(ListNode head) {
        if(head==null || head.next==null) return head;

        ListNode mid=mid_(head);
        ListNode nhead=mid.next;
        mid.next=null;

        return mergeTwoList(sortList(head),sortList(head2));
        
    }

    //leetcode 21
    public ListNode mergeTwoList(ListNode A,ListNode B){
        if(A==null) return B;
        else if(B==null) return A;

        ListNode head=new ListNode(-1); // dummyNode.
        ListNode curr=head;

        while(A!=null && B!=null){
            if(A.val<=B.val){
                curr.next=A; //connection.
                
                curr=A;    // move forward.
                A=A.next;
            }else{
                curr.next=B;//connection

                curr=B;  // move forward.
                B=B.next;
            }
        }
        if(A!=null)
            curr.next=A;
        else if(B!=null)
            curr.next=B;

       return head.next;
    }

    //leetcode 138
    public Node copyRandomList(Node head) {
        //First: we try to make new nodes and link them next to each other.
        Node curr=head,forw=null;
        while(curr!=null){
            forw=curr.next;

            Node copyNode=new Node(curr.val);
            curr.next=copyNode;
            copyNode.next=forw;

            curr=forw;
        }

        //second: set random pointers.
        curr=head;
        while(curr!=null){
            if(curr.random!=null)
                curr.next.random=curr.random.next;  
            curr=curr.next.next;
        }

        //Third: extract copy List.
        curr=head;
        Node newList=new Node(-1);  // dummy Node.
        Node newCurr=newList;
        Node copyNode=null;

        while(curr!=null){
            forw=curr.next.next;

            copyNode=curr.next;
            newCurr.next=copyNode;
            curr.next=forw;

            curr=forw;
            newCurr=copyNode;
        }

        return newList.next;
    }

    //leetcode 19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(n==0 || head==null) return null;
        if(n==1 && head.next==null) return null;

        ListNode slow=head,fast=head;
        while(n-->0){
            fast=fast.next;
        }

        if(fast==null) return head.next;

        while(fast.next!=null){
            slow=slow.next;
            fast=fast.next;
        }

        ListNode temp=slow.next;
        slow.next=slow.next.next;
        temp.next=null;

        return head;
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