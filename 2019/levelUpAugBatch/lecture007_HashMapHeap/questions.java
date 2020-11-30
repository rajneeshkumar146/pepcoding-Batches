import java.util.PriorityQueue;
import java.util.HashSet;
public class questions{

    //Leetcode 215
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int ele : nums){
            pq.add(ele);
            if(pq.size() > k) pq.remove();
        }

        return pq.peek();
    }
}


// 703
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
        pq.add(val);
        if(pq.size() > this.K) pq.remove();
        return pq.peek();
    }
}

//349
public int[] intersection(int[] nums1, int[] nums2) {
    HashMap<Integer,Integer> map = new HashMap<>();
    for(int ele : nums1){
        map.put(ele,map.getOrDefault(ele,0) + 1);
    }
    
    ArrayList<Integer> ans = new ArrayList<>();

    for(int ele : nums2){
        if(map.containsKey(ele)){
            ans.add(ele);
            map.remove(ele);
        }
    }

    int[] arr = new int[ans.size()];
    int i = 0;
    
    for(int ele:ans) arr[i++] = ele;
    
    return arr;
}
//350
public int[] intersect(int[] nums1, int[] nums2) {
    HashMap<Integer,Integer> map = new HashMap<>();
    for(int ele : nums1){
        map.put(ele,map.getOrDefault(ele,0) + 1);
    }
    
    ArrayList<Integer> ans = new ArrayList<>();

    for(int ele : nums2){
        if(map.containsKey(ele)){
            ans.add(ele);
            map.put(ele,map.get(ele) - 1);
            if(map.get(ele) == 0) map.remove(ele);
        }
    }

    int[] arr = new int[ans.size()];
    int i = 0;
    
    for(int ele:ans) arr[i++] = ele;
    
    return arr;
}

//128
public int longestConsecutive(int[] nums) {
    HashMap<Integer,Integer> map = new HashMap<>();
    for(int ele : nums) map.put(ele,1);
    
    int len = 0;
    for(int ele : nums){
        if(!map.containsKey(ele)) continue;

        map.remove(ele);
        int prev = ele - 1;
        int next = ele + 1;

        while(map.containsKey(prev)){
            map.remove(prev); prev--;
        }

        while(map.containsKey(next)){
            map.remove(next); next++;
        }

        len = Math.max(len, next - prev - 1);
    }

    return len;
}

//347
public int[] topKFrequent(int[] nums, int k) {
        
    HashMap<Integer,Integer>map = new HashMap<>();
    for(int ele : nums) map.put(ele,map.getOrDefault(ele,0) + 1);
    
    // data,freq
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
        return a[1] - b[1];//other - this
    });
    
    for(int ele : map.keySet()){
        pq.add(new int[]{ele,map.get(ele)});
        if(pq.size() > k) pq.remove(); 
    }
    
    int[] ans = new int[k];
    int idx = 0;
    while(pq.size() != 0){
        int[] d = pq.remove();
        ans[idx++] = d[0];
    }
    
    return ans;   
}
// 347
public int[] topKFrequent(int[] nums, int k) {
        
    HashMap<Integer,Integer>map = new HashMap<>();
    for(int ele : nums) map.put(ele,map.getOrDefault(ele,0) + 1);
    
    // data,freq
    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
        return map.get(a) - map.get(b);//other - this
    });
    
    for(int ele : map.keySet()){
        pq.add(ele);
        if(pq.size() > k) pq.remove(); 
    }
    
    int[] ans = new int[k];
    int idx = 0;
    while(pq.size() != 0){
        ans[idx++] = pq.remove();
    }
    
    return ans;   
}


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
         
         int cidx = map.get(val);
         int lidx = list.size() - 1;
         
         map.put(list.get(lidx),cidx);
         map.remove(val);
         
         list.set(cidx,list.get(lidx));
         list.remove(lidx);
         
         return true;
         
     }
     
     /** Get a random element from the set. */
     public int getRandom() {
         int idx = rand.nextInt(list.size());
         return list.get(idx);
     }
 }

 //295
 class MedianFinder {
    
    PriorityQueue<Integer> leftMaxHeap;
    PriorityQueue<Integer> rightMinHeap;
    
    public MedianFinder() {
       leftMaxHeap = new PriorityQueue<>((a,b)->{
        return b - a;
    }); 
        rightMinHeap = new PriorityQueue<>();
    
    }
    
    public void addNum(int num) {
      if(leftMaxHeap.size() == 0 || num <= leftMaxHeap.peek())leftMaxHeap.add(num);
      else rightMinHeap.add(num);
        
      if(leftMaxHeap.size() - rightMinHeap.size() == 2){
          rightMinHeap.add(leftMaxHeap.peek());
          leftMaxHeap.remove();
      }else if(leftMaxHeap.size() < rightMinHeap.size()){
          leftMaxHeap.add(rightMinHeap.peek());
          rightMinHeap.remove();
      }
    }
    
    public double findMedian() {
       if(leftMaxHeap.size() == rightMinHeap.size()) return (leftMaxHeap.peek() + rightMinHeap.peek())/2.0;
        else return leftMaxHeap.peek();
    }
}


//378
public int kthSmallest(int[][] matrix, int k) {
    if(matrix.length == 0 || matrix[0].length == 0) return 0;
    
    int n = matrix.length;
    int m = matrix[0].length;
    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
        return matrix[a/m][a%m] - matrix[b/m][b%m];
    });
    
    for(int i=0;i<n;i++) pq.add(i * m + 0);
    
    while(--k > 0){
        int idx = pq.remove();
        int r = idx / m;
        int c = idx % m;
        
        c++;
        if(c < m) pq.add( r * m + c);
    }
    
        int idx = pq.remove();
        int r = idx / m;
        int c = idx % m;

     return matrix[r][c];
}

// Leetcode 146.
class LRUCache {
    private class Node{
        int key = 0;
        int value = 0;

        Node next = null;
        Node prev = null;

        Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;
    private int Maxcapacity = 0;
    private HashMap<Integer,Node> map;
    
    public LRUCache(int capacity) {
        this.Maxcapacity = capacity;
        map = new HashMap<>();
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
    
    private Node removeNode(Node node){
        if(this.size == 1){
            this.head = null;
            this.tail = null;
            this.size--;
            return node;
        }else if(this.tail == node){
            return removeLast();
        }else{
            Node prevNode = node.prev;
            Node nextNode = node.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            node.prev = null;
            node.next = null;
            this.size--;

            return node;
        }
    }

    private Node removeLast(){
        if(this.size == 1){
            Node node = this.tail;
            
            this.head = null;
            this.tail = null;
            this.size--;
            
            return node;
        }
        
        Node lastNode = this.tail;
        
        Node secondLastNode = lastNode.next;
        secondLastNode.prev = null;
        lastNode.next = null;
        this.tail = secondLastNode;
        this.size--;

        return lastNode;
    }



    private void makeMostRecent(Node node){
        if(this.head == node) return;

        removeNode(node);
        addFirst(node);
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        
        Node node = map.get(key);
        makeMostRecent(node);
        return node.value;        
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            makeMostRecent(node);
        }else{
            if(this.size == this.Maxcapacity){
                Node node = removeLast();
                map.remove(node.key);
            }

            Node node = new Node(key,value);
            addFirst(node);
            map.put(key,node);
        }
    }
}
   
public Node copyRandomList(Node head) {
    HashMap<Node,Node> map = new HashMap<>();
    Node curr = head;
    while(curr != null){
        Node node = new Node(curr.val);
        map.put(curr,node);
        
        curr = curr.next;
    }

    curr = head;
    while(curr!=null){
        Node copyNode = map.get(curr);

        copyNode.next = map.containsKey(curr.next) ? map.get(curr.next) : null;
        copyNode.random = map.containsKey(curr.random) ? map.get(curr.random) : null;

        curr= curr.next;
    }

    return map.get(head);
 }

 //895
 class FreqStack {
    HashMap<Integer,Integer> freq;
    ArrayList<Stack<Integer>> map;
    int maxFreq = 0;

    public FreqStack() {
        this.freq = new HashMap<>();
        this.map = new ArrayList<>();
        this.maxFreq = 0;
        
        map.add(new Stack<>());  // dummy stack
    }
    
    public void push(int x) {
        freq.put(x,freq.getOrDefault(x,0) + 1);
        maxFreq = Math.max(maxFreq,freq.get(x));

        if(map.size() == maxFreq) map.add(new Stack<>());
        map.get(freq.get(x)).push(x);
    }
    
    public int pop() {
        int rv = map.get(maxFreq).pop();
        
        if(map.get(maxFreq).size() == 0) maxFreq--;
        freq.put(rv,freq.get(rv) - 1);
        if(freq.get(rv) == 0) freq.remove(rv);
        
        return rv;
    }
}

//49
public List<List<String>> groupAnagrams(String[] strs) {
        
    HashMap<String,ArrayList<String>> map = new HashMap<>();
    for(String str: strs){
       
       char[] arr = str.toCharArray();
       Arrays.sort(arr);
       String key = new String(arr);
       map.putIfAbsent(key,new ArrayList<>());
       map.get(key).add(str);
    }
    
    List<List<String>> ans = new ArrayList<>();
    for(String s: map.keySet()){
        ans.add(map.get(s));
    }
    
    return ans;   
}

vector<vector<string>> groupAnagrams(vector<string>& strs) {
    unordered_map<string,vector<string>> map;
    
    for(string &str: strs){
        string s = str;
        sort(str.begin(),str.end());
        map[str].push_back(s);
    }
    
    vector<vector<string>> ans;
    for(auto keys: map){
        ans.push_back(keys.second);
    }
    return ans;
}

//407
public int trapRainWater(int[][] heightMap) {
    if(heightMap.length == 0 || heightMap[0].length == 0) return 0;
    int n = heightMap.length;
    int m = heightMap[0].length;
    
    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
        return heightMap[a/m][a%m] - heightMap[b/m][b%m];
    });

    int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    boolean[][] vis = new boolean[n][m];

    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            if(i==0 || j==0 || i == n-1 || j == m-1)
            {
                pq.add(i*m+j);
                vis[i][j] = true;
            }
        }
    }
    
    int maxHeight = 0;
    int water = 0;
    
    while(pq.size()!=0){
        int idx = pq.remove();
        int r = idx / m;
        int c = idx % m;
        
        maxHeight = Math.max(maxHeight,heightMap[r][c]);
        water += maxHeight - heightMap[r][c];
        for(int d = 0 ;d< 4;d++){
            int x = r + dir[d][0];
            int y = c + dir[d][1];
            if(x>=0 && y>=0 && x < n && y < m && !vis[x][y]){
                 vis[x][y] = true;
                 pq.add(x * m + y);
            }   
        }
    }
    
    return water;
}




 