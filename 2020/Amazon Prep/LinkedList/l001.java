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
        if(head == null || head.next == null) return t;

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

    // Leetcode 141
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) return true;
        }

        return false;
    }

    // 142
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next== null)
            return null;
        

        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) break;
        }

        if(slow != fast) return null;

        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    //160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;

        ListNode curr = headA;
        while(curr.next != null){
            curr = curr.next;
        }

        curr.next = headB;
        ListNode ans = detectCycle(headA);
        curr.next = null;

        return ans;
    }

    // Leetcode 25
    ListNode tt  = null;
    ListNode th  = null;

    public void addFirst(ListNode node){
        if(th == null){
            th = node;
            tt = node;
        }else{
            node.next = th;
            th = node;
        }
    }

    public void length(ListNode node){
        int len = 0 ;
        while(node!=null){
            node = node.next;
            len++;
        }
        return len;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
       if(head == null || head.next == null || k <= 1) return head;
       
       int len = length(head);

       ListNode oh  = null;
       ListNode ot  = null;
       ListNode curr = head;

       while(curr != null && len >= k){
           int tempK = k;
           while(tempK-- > 0){
               ListNode forw = curr.next;

               curr.next = null;
               addFirst(curr);

               curr = forw;
           }

           len -= k;
           if(oh == null){
               oh = th;
               ot = tt;
           }else{
               ot.next = th;
               ot = tt;
           }

           th = null;
           tt = null;
       }

       ot.next = curr;
       return oh;
    }

    // 92
    public ListNode reverseBetween(ListNode head, int n, int m) {
        if(head == null || head.next == null || m == n) return head;
        
        ListNode curr = head;
        ListNode prev = null;

        int idx = 1;
        while(idx < m){
            while(idx >= n && idx <= m){
                ListNode forw = curr.next;
                curr.next = null;

                addFirst(curr);

                curr = forw;
                idx++;
            }

            if(idx > n){
                tt.next = curr;
                if(prev != null){
                  prev.next = th;
                  return head;
                }

                return th;
            }
            idx++;
            prev = curr;
            curr = curr.next;
        }

        return head;
    }


    class LRUCache {
        private class Node{
            Integer key = 0;
            Integer value = 0;

            Node prev = null;
            Node next = null;

            Node(Integer key,Integer value){
                this.key = key;
                this.value = value;
            }
        }

        private HashMap<Integer,Node> map = new HashMap<>();
        private Node head;
        private Node tail;
        private int maxCapacity = 0;
        private int size = 0;
 
        public LRUCache(int capacity) {
            this.maxCapacity = capacity;
        }

        private void addFirst(Node node){
            if(this.size == 0){
                this.head = node;
                this.tail = node;
            }else{
                this.head.next = node;
                node.prev = this.head;
                this.head = node;
            }

            this.size++;
        }

        private void removeNode(Node node){
            if(this.size == 1){
                this.head = null;
                this.tail = null;
            }else if(this.tail == node){
                Node nextNode = this.tail.next;
                this.tail.next = null;
                nextNode.prev = null;
                this.tail = nextNode;
            }else{
                Node prevNode = node.prev;
                Node nextNode = node.next;

                prevNode.next = nextNode;
                nextNode.prev = prevNode;

                node.prev = null;
                node.next = null;
            }

            this.size--;
        }


        private void shiftToFirst(Node node){
            if(this.head == node) return;

            removeNode(node);
            addFirst(node);
        }
        
        public int get(int key) {
            if(!map.containsKey(key)) return -1;
            
            Node node = map.get(key);
            shiftToFirst(node);
            
            return node.value;
        }
        
        public void put(int key, int value) {
            if(map.containsKey(key)){
                Node node = map.get(key);
                node.value = value;
                shiftToFirst(node);
            }else{
                Node node = new Node(key,value);
                addFirst(node);
                map.put(key,node);

                if(this.size > this.maxCapacity){
                    Node lastNode = this.tail;
                    removeNode(lastNode);
                    map.remove(lastNode.key);
                }
            }
            
        }
    }
}