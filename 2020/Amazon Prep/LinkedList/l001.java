public class l001{
    public class ListNode {
        int val;
        ListNode next;
        
        public ListNode(int val) { 
            this.val = val; 
        }
    }

    //876
    public ListNode middleNode(ListNode head) {
       if(head == null || head.next == null) return head;
       
       
       ListNode slow = head;
       ListNode fast = head;

       while(fast != null && fast.next != null){
           slow = slow.next;
           fast = fast.next.next;
       }

       return slow;
    }

    public ListNode middleNode2(ListNode head) {
        if(head == null || head.next == null) return head;
        
        
        ListNode slow = head;
        ListNode fast = head;
 
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
 
        return slow;
     }

    
     public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode prev = null;
        ListNode curr = head;

        while(curr != null){
            ListNode forw = curr.next;  // backup

            curr.next = prev;  // link
            
            prev = curr;  // move
            curr = forw;
        }
        
        return prev;
    }

    //234
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;


        ListNode middle = middleNode2(head);
        ListNode nhead = middle.next;
        middle.next = null;

        nhead = reverseList(nhead);

        ListNode c1 = head;
        ListNode c2 = nhead;

        boolean flag = true;
        while(c1 != null && c2 != null){
            if(c1.val != c2.val){
                flag = false;
                break;
            }

            c1 = c1.next;
            c2 = c2.next;
        }

        nhead = reverseList(nhead);
        middle.next = nhead;

        return flag;
    }

    //143
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;


        ListNode middle = middleNode2(head);
        ListNode nhead = middle.next;
        middle.next = null;

        nhead = reverseList(nhead);

        ListNode c1 = head;
        ListNode c2 = nhead;

        while(c1 != null && c2 != null){
            ListNode f1 = c1.next;  // backup
            ListNode f2 = c2.next;

            c1.next = c2;  // links
            c2.next = f1;

            c1 = f1;  // move
            c2 = f2;
        }
    }

    //Leetcode 21
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) return l1 != null ? l1 : l2;

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;

        ListNode c1 = l1;
        ListNode c2 = l2;
        
        while(c1 != null && c2 != null){
            if(c1.val <= c2.val){
                prev.next = c1;
                c1 = c1.next;
            }else{
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }

        prev.next = (c1 != null ? c1 : c2);
        
        return dummy.next;
    }

    // Leetcode 23
    public ListNode mergeKLists(ListNode[] lists,int si ,int ei) {
        if(si == ei) return lists[si];
        int mid = (si + ei) / 2;
        return mergeTwoLists(mergeKLists(lists,si,mid), mergeKLists(lists,mid + 1, ei));
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        return mergeKLists(lists, 0, lists.length - 1);
    }


    //148
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode middle = middleNode2(head);
        ListNode nhead = middle.next;
        middle.next = null;
       
        return mergeTwoLists(sortList(head), sortList(nhead));
    }

    //138
    public void copyLinkedList(Node head){
        Node curr = head;
        while(curr != null){
            Node forw = curr.next;
            Node node = new Node(curr.val);
            
            curr.next = node;
            node.next = forw;
            
            curr = forw;
        }
    }
    
    public void setRandoms(Node head){
        Node curr = head;
        while(curr != null){
            if(curr.random != null)
               curr.next.random = curr.random.next;
            curr = curr.next.next;
        }
    }
    
    public Node extractLinkedList(Node head){
        Node dummy = new Node(-1);
        Node prev = dummy;
        
        Node curr = head;
        while(curr != null){
            prev.next = curr.next;
            prev = prev.next;
            
            curr.next = prev.next;
            curr = curr.next;
        }
        
        return dummy.next;
    }
    
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        
        copyLinkedList(head);
        setRandoms(head);
        return extractLinkedList(head);
    }

}