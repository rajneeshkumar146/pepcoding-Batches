import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

public class questions{
    public static void nextGreaterOnRight(int[] arr){

        LinkedList<Integer> st = new LinkedList<>();
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans,n);

        for(int i=0;i<n;i++){
            while(st.size() != 0 && arr[st.getFirst()] < arr[i]){
                ans[st.removeFirst()] = i;
            }
            st.addFirst(i);
        }
    }

    public static void nextGreaterOnLeft(int[] arr){

        LinkedList<Integer> st = new LinkedList<>();
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans,-1);

        for(int i = n - 1; i >= 0 ;i--){
            while(st.size() != 0 && arr[st.getFirst()] < arr[i]){
                ans[st.removeFirst()] = i;
            }
            st.addFirst(i);
        }
    }


    
    public static void nextSmallerOnRight(int[] arr){

        LinkedList<Integer> st = new LinkedList<>();
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans,n);

        for(int i=0;i<n;i++){
            while(st.size() != 0 && arr[st.getFirst()] > arr[i]){
                ans[st.removeFirst()] = i;
            }
            st.addFirst(i);
        }
    }

    public static void nextSmallerOnLeft(int[] arr){

        LinkedList<Integer> st = new LinkedList<>();
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans,-1);

        for(int i = n - 1; i >= 0 ;i--){
            while(st.size() != 0 && arr[st.getFirst()] > arr[i]){
                ans[st.removeFirst()] = i;
            }
            st.addFirst(i);
        }
    }

    //503
    public int[] nextGreaterElements(int[] nums) {
        LinkedList<Integer> st = new LinkedList<>();
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans,n);

        for(int i=0;i< 2 * n;i++){
            while(st.size() != 0 && arr[st.getFirst()] < arr[i % n]){
                ans[st.removeFirst()] = i % n;
            }
            if(i <n ) st.addFirst(i);
        }
    }

    //735
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> st = new LinkedList<>();
        for(int ele : asteroids){
            if(ele > 0) st.addFirst(ele);
            else{

                while(st.size() != 0 && st.getFirst() < -ele){
                    st.removeFirst();
                }

                if(st.size() != 0 && st.getFirst() == -ele) st.removeFirst();
                else if(st.size() == 0 || st.getFirst() < 0) st.addFirst(ele);
                else{
                    // st.getFirst() > 0 && ele < 0 && st.getFirst() > -ele
                    // negative element will destroy
                }

            }
        }   
    }

    
    public static boolean mirror_(TreeNode node1,TreeNode node2){
        if(node1 == null && node2 == null) return true;
        if(node1 == null || node2 == null || node1.val != node.val) return false;

        if(!mirror_(node1.left,node2.right)) return false;
        if(!mirror_(node1.right,node2.left)) return false;

        return true;
    }

    public static boolean mirror(TreeNode node){
        if(node == null) return true;
        return mirror_(node.left,node.right);
    }


    //215
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int ele : nums){
            pq.add(ele);
            if(pq.size() > k) pq.remove();
        }
        
        return pq.peek();
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            // return a - b;  // default behaviour
            return b - a; // reverse of default behaviour.
        });

        for(int ele : nums){
            pq.add(ele);
            if(pq.size() > k) pq.remove();
        }
        
        return pq.peek();
    }
    
    class KthLargest {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int K = 0;
        
        public KthLargest(int k, int[] nums) {
             this.K = k;
            for(int ele : nums){
                pq.add(ele);
                if(pq.size() > k) pq.remove();
            }
        }
        
        public int add(int val) {
            pq.add(ele);
            if(pq.size() > this.K) pq.remove();
            return pq.peek();
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
     HashMap<Integer,Integer> map = new HashMap<>();
     for(int ele: nums) map.put(ele,map.getOrDefault(ele)+1);

     PriorityQueue<Integer> pq  = new PriorityQueue<>((a,b)->{
         return map.get(a) - map.get(b);
     });

     for(int ele: map.keySet()){
         pq.add(ele);
         if(pq.size() > k) pq.remove();
     }

     int idx = 0;
     int[] ans = new int[k];
     while(pq.size()!=0){
         ans[idx++] = pq.remove();
     }

     return ans;
    }

    //Leetcode 23
    public ListNode mergeKLists(ListNode[] lists,int si,int ei){
        if(si == ei) return lists[si];

        int mid = (si + ei) / 2;
        mergeTwoSortedLL(mergeKLists(lists,si,mid),mergeKLists(lists,mid+1,ei));
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.size()==0) return null;
        mergeKLists(lists,0,lists.size()-1);
    }

    public ListNode mergeTwoSortedLL(ListNode head1,ListNode head2){
        if(head1==null || head2 == null) return head1 != null?head1:head2;
        
        ListNode curr1 = head1;
        ListNode curr2 = head2;

        ListNode head = new ListNode(-1);
        ListNode prev = head;

        while(curr1!=null && curr2!=null){
            if(curr1.val < curr2.val){
                prev.next = curr1;
                curr1 = curr1.next;
            }else{
                prev.next = curr2;
                curr2 = curr2.next;
            }
            prev = prev.next;
        }

        prev.next = curr1 != null ? curr1 : curr2;
        return head.next;
    }

    public static ListNode mid(ListNode node){
        if(node == null || node.next == null) return node;

        ListNode slow = node;
        ListNode fast = node;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static ListNode mid2(ListNode node){
        if(node == null || node.next == null) return node;

        ListNode slow = node;
        ListNode fast = node;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    
public boolean hasCycle(ListNode head) {
    if(head == null) return false;

    ListNode slow = head;
    ListNode fast = head;

    while(fast!=null && fast.next != null){
        slow = slow.next;
        fast = fast.next.next;
        if(slow == fast) return  true;
    }
    
    return false;
}

//Leetcode 142
public ListNode detectCycle(ListNode head) {
    if (head == null || head.next== null)
        return null;

    
    ListNode slow = head;
    ListNode fast = head;

    while(fast!=null && fast.next != null){
        slow = slow.next;
        fast = fast.next.next;
        if(slow == fast) break;
    }

    if(slow!=fast) return null;

    slow = head;
    while(slow!=fast){
        slow = slow.next;
        fast = fast.next;;
    }

    return slow;
}

public void reorderList(ListNode head) {
    if (head == null || head.next == null)
        return;
    
    ListNode mid = midNode(head);
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

public ListNode midNode(ListNode node) {
    if (node == null || node.next == null)
        return node;

    ListNode slow = node, fast = node;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
}

 public ListNode reverseList(ListNode node) {
    if (node == null || node.next == null)
        return node;

    ListNode prev = null;
    ListNode curr = node;
    while (curr != null) {
        ListNode forw = curr.next; // backup.

        curr.next = prev; // connection

        prev = curr; // move forw.
        curr = forw;
    }

    return prev;
}

public void copyNodes(Node head){
    Node curr = head;
    while(curr != null){
        ListNode node = new ListNode(curr.val);
        node.next = curr.next;
        curr.next = node;
        curr = curr.next.next;
    }
}

public void setRandoms(Node head){
    Node curr = head;
    while(curr != null){
        
        if(curr.random != null) curr.next.random = curr.random.next;
        curr = curr.next.next;
    }
}


public Node extractList(Node head){
    Node curr = head;
    ListNode head = new ListNode(-1);
    ListNode prev = head;

    while(curr != null){
        prev.next = curr.next;
        prev = prev.next;

        curr.next = curr.next.next;
    }

    return head.next;
}

public Node copyRandomList(Node head) {
    if(head == null) return null;

    copyNodes(head);
    setRandoms(head);
    return extractList(head);
}

//380
class RandomizedSet {
    HashMap<Integer,Integer> map = new HashMap<>();
    ArrayList<Integer> list = new ArrayList<>();
    Random rand = new Random(); 

    public RandomizedSet() {
        
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;

        map.put(val,list.size());
        list.add(val);

        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;

        int targetIndex = map.get(val);
        int lastIndex = list.size()-1;

        map.put(list.get(lastIndex), targetIndex);
        map.remove(val);
        list.set(targetIndex,list.get(lastIndex));

        list.remove(lastIndex);

        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int idx = rand.nextInt(list.size()); 
        return list.get(idx);
    }
}   

public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    int m = matrix[0].length;

    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
        return matrix[a/m][a%m] - matrix[b/m][b%m];
    });

    for(int i=0;i<n;i++) pq.add(i * m + 0);

    while(--k > 0){
        int idx = pq.poll();
        int r = idx / m;
        int c = idx % m;

        c++;
        if(c < m) pq.add(r * m + c);
    }

    int val = pq.peek();
    return matrix[val/m][val%m];
}


public int trap(int[] height) {
    if(height.length == 0) return 0;
    int n = height.length;

    int water = 0, lmax = 0,rmax = 0, li = 0, ri = n-1;
    while(li < ri){
        lmax = Math.max(lmax,height[li]);
        rmax = Math.max(rmax,height[ri]);

        if(lmax <= rmax) water += lmax - height[li++];
        else water += rmax - height[ri--];
    }
    
    return water;
}





