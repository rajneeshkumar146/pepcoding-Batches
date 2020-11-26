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


public void numIslands(int r,int c,char[][] grid,int[][] dir) {
        
    grid[r][c] = '0';
    for(int d = 0; d < 4; d++){
        int x = r + dir[d][0];
        int y = c + dir[d][1];
        
        if(x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == '1')
           numIslands(x,y,grid,dir); 
    }
    
}

public int numIslands(char[][] grid) {
    int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    int count = 0;
    for(int i =0; i<grid.length;i++){
        for(int j=0;j<grid[0].length;j++){
            if(grid[i][j]=='1'){
                numIslands(i,j,grid,dir);
                count++;
            }
        }
    }
    
    return count;
}


public int maxAreaOfIsland(int r,int c,int[][] grid,int[][] dir) {
        
    grid[r][c] = 0;
        int area = 0;
    for(int d = 0; d < 4; d++){
        int x = r + dir[d][0];
        int y = c + dir[d][1];
        
        if(x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1)
           area +=maxAreaOfIsland(x,y,grid,dir); 
    }
        return area + 1;
}

    
    public int maxAreaOfIsland(int[][] grid) {
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    int area = 0;
    for(int i =0; i<grid.length;i++){
        for(int j=0;j<grid[0].length;j++){
            if(grid[i][j]==1){
                area = Math.max(area,maxAreaOfIsland(i,j,grid,dir));
            }
        }
    }
    
    return area;
    }

    //Leetcode 994
    public int orangesRotting(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        LinkedList<Integer> que = new LinkedList<Integer>();
        int freshOranges = 0;
        for(int i=0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(grid[i][j] == 2){
                    que.addLast(i*m+j);
                    grid[i][j] = 2;
                }else if(grid[i][j] == 1){
                    freshOranges++;
                }
            }
        }

        if(freshOranges == 0) return 0;
        int time = 0;
        while(que.size() != 0){
            int size = que.size();
            while(size-->0){
                int idx = que.removeFirst();
                int r = idx / m;
                int c = idx % m;
                
                for(int d = 0; d < 4; d++){
                    int x = r = dir[d][0];
                    int y = c = dir[d][1];

                    if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1){
                        grid[x][y] = 2;
                        que.addLast(x * m + y);
                        freshOranges--;       
                        if(freshOranges == 0) return time + 1;
                    }
                }
            }
            time++;
        }
        return -1;
    }

    public boolean isBipartite(int src,int[][] graph,int[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        que.add(src);
        int color = 0;
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                
            int rvtx = que.removeFirst();
            if(vis[rvtx] != -1){
                if(vis[rvtx] != color) return false;
                continue;
            }
            
            vis[rvtx] = color;
            for(int e : graph[rvtx]){
                if(vis[e] == -1){
                    que.addLast(e);
                }
            }
            
            }
            
            color = (color + 1) % 2;
        }
        
        return true;
    }
    
    public boolean isBipartite(int[][] graph){
        if(graph.length == 0) return true;
        int[] vis = new int[graph.length];
        Arrays.fill(vis,-1);
        
        for(int i=0;i<graph.length;i++){
            if(vis[i]==-1){
                 if(!isBipartite(i,graph,vis)) return false;
            }  
        }
        
        return true;
    }


    //210`
    public int[] findOrder(int N, int[][] arr) {
        ArrayList<Integer>[] graph = new ArrayList[N];
         for(int i=0;i<N;i++) graph[i] = new ArrayList<>();
         
         int[] indegree = new int[N];
         for(int[] a: arr){
             indegree[a[1]]++;
             graph[a[0]].add(a[1]);
         }
         
         LinkedList<Integer> que = new LinkedList<>();
         for(int i=0;i<N;i++) if(indegree[i]==0) que.addLast(i);
 
         int[] ans = new int[N];
         int idx = N - 1;
         while(que.size()!=0){
             int vtx = que.removeFirst();
             ans[idx--] = vtx;
             
             for(int e : graph[vtx]){
                 if(--indegree[e] == 0) que.addLast(e);
             }
         }
         
         
         if(idx == -1) return ans;
         return new int[0];
     }

     public static int findUniqueInUnSortedArray(int[] arr,int k){
        int ans = 0;
        for(int i=0;i<32;i++){
            int mask =  (1 << i); 
            int count = 0;
            for(int ele : arr){
                if((ele & mask) != 0) count++;
            }

            if(count % k != 0) ans |= mask;
        }

        return ans;
     }

     public static int[] extractNumber(int[] arr){
        int A = 0;
        int B = 0;

        int mask = 0;
        for(int ele : arr) mask ^= ele;

        mask = (mask & (-mask));

        for(int ele: arr){
            if((mask & ele) == 0){
                B ^= ele; 
            }else A ^= ele;
        }

        return int[]{A,B};
     }

     public int subarraysWithKDistinct_(int[] A, int K) {   
        int si = 0, ei = 0, count = 0,ans = 0;
        int[] freq = new int[20000 + 5];
        while(ei < A.length){
            if(freq[A[ei++]]++ == 0){
                count++;
            }
            
            while(count > K){
                if(freq[A[si++]]-- == 1) count--;
            }
            
            ans += (ei - si);
        }
        
        return ans;
    }
    
    public int subarraysWithKDistinct(int[] A, int K) {
       return subarraysWithKDistinct_(A,K) - subarraysWithKDistinct_(A,K-1);
    }

    public int lengthOfLongestSubstring(String s) {
        if(s.length() <= 1) return s.length();
        int n = s.length();
        
        int[] freq = new int[256];
        int si = 0,ei = 0,len = 0;
        
        int count = 0;
        
        while(ei < n){
            if(freq[s.charAt(ei++)]++ > 0) count++;
            
            while(count > 0){
                if(freq[s.charAt(si++)]-- > 1) count--;
            }
            
            
            len = Math.max(len, ei - si);            
        }
        
        return len; 
    }






