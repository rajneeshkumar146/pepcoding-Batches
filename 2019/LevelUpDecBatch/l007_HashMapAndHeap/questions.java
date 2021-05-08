import java.util.PriorityQueue;
import java.util.Random;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;

public class questions {
    // 215
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> que = new PriorityQueue<>(); // By Default Min.
        for (int ele : nums) {
            que.add(ele);
            if (que.size() > k)
                que.remove();
        }

        return que.peek();
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void downheapify(int[] arr, int pi, int li) {
        int maxIdx = pi, lci = 2 * pi + 1, rci = 2 * pi + 2;
        if (lci <= li && arr[lci] > arr[maxIdx])
            maxIdx = lci;
        if (rci <= li && arr[rci] > arr[maxIdx])
            maxIdx = rci;
        if (maxIdx != pi) {
            swap(arr, pi, maxIdx);
            downheapify(arr, maxIdx, li);
        }
    }

    public int findKthLargest_Btr(int[] nums, int k) {
        int li = nums.length - 1;
        for (int i = li; i >= 0; i--)
            downheapify(nums, i, li);

        while (k-- > 1) {
            swap(nums, 0, li--);
            downheapify(nums, 0, li);
        }

        return nums[0];
    }

    public int findKthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> que = new PriorityQueue<>((a, b) -> { // max PQ.
            return b - a;
        }); // By Default Min.

        for (int ele : nums) {
            que.add(ele);
            if (que.size() > k)
                que.remove();
        }

        return que.peek();
    }

    // 703
    class KthLargest {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int K = 0;

        public KthLargest(int k, int[] nums) {
            this.K = k;
            for (int ele : nums) {
                this.pq.add(ele);
                if (this.pq.size() > this.K)
                    this.pq.remove();

            }
        }

        public int add(int val) {
            this.pq.add(val);
            if (this.pq.size() > this.K)
                this.pq.remove();

            return this.pq.peek();
        }
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for (int ele : nums1)
            set.add(ele);

        ArrayList<Integer> ans = new ArrayList<>();
        for (int ele : nums2) {
            if (set.contains(ele)) {
                ans.add(ele);
                set.remove(ele);
            }
        }

        int[] res = new int[ans.size()];
        int i = 0;
        for (int ele : ans)
            res[i++] = ele;

        return res;
    }

    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int ele : nums)
            set.add(ele);

        int len = 0;
        for (int ele : nums) {
            if (!set.contains(ele))
                continue;

            int ple = ele - 1, pre = ele + 1;
            set.remove(ele);

            while (set.contains(ple))
                set.remove(ple--);
            while (set.contains(pre))
                set.remove(pre++);

            len = Math.max(len, pre - ple - 1);
        }

        return len;
    }

    // 347
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        // {val,freq}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        for (Integer key : map.keySet()) {
            pq.add(new int[] { key, map.get(key) });
            if (pq.size() > k)
                pq.remove();
        }

        int[] ans = new int[pq.size()];
        int i = 0;
        while (pq.size() != 0) {
            int[] p = pq.remove();
            int val = p[0];
            int freq = p[1];

            ans[i++] = val;
        }

        return ans;
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        // {val,freq}
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return map.get(a) - map.get(b);
        });

        for (Integer key : map.keySet()) {
            pq.add(key);
            if (pq.size() > k)
                pq.remove();
        }

        int[] ans = new int[pq.size()];
        int i = 0;
        while (pq.size() != 0) {
            int val = pq.remove();
            ans[i++] = val;
        }

        return ans;
    }

    // 973
    public int[][] kClosest(int[][] points, int k) {
        // {x,y}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int d1 = a[0] * a[0] + a[1] * a[1]; // x1^2 + y1^2
            int d2 = b[0] * b[0] + b[1] * b[1]; // x2^2 + y2^2

            return d2 - d1;
        });

        for (int[] p : points) {
            pq.add(new int[] { p[0], p[1] });
            if (pq.size() > k)
                pq.remove();
        }

        int[][] ans = new int[k][];
        int i = 0;
        while (pq.size() != 0) {
            int[] p = pq.remove();

            ans[i++] = p;
        }

        return ans;
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int r1 = a / m, c1 = a % m;
            int r2 = b / m, c2 = b % m;

            return matrix[r1][c1] - matrix[r2][c2];
        });

        for (int i = 0; i < n; i++) {
            pq.add(i * m + 0);
        }

        int ans = 0;
        while (k-- > 0) {
            int idx = pq.remove();
            int r = idx / m;
            int c = idx % m;

            ans = matrix[r][c];

            c++;
            if (c < m) {
                pq.add(r * m + c);
            }
        }

        return ans;
    }

    // 380
    class RandomizedSet {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();

        /** Initialize your data structure here. */
        public RandomizedSet() {

        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain
         * the specified element.
         */
        public boolean insert(int val) {
            if (map.containsKey(val))
                return false;

            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified
         * element.
         */
        public boolean remove(int val) {
            if (!map.containsKey(val))
                return false;

            int idx = map.get(val);
            int lidx = list.size() - 1;
            int lval = list.get(lidx);

            list.set(idx, lval);
            map.put(lval, idx);

            map.remove(val);
            list.remove(lidx);

            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int idx = rand.nextInt(list.size());
            return list.get(idx);
        }
    }

    // 895
    class FreqStack {
        // val , freq
        HashMap<Integer, Integer> freq;
        ArrayList<Stack<Integer>> freqMap;
        int maxFreq = 0;

        public FreqStack() {
            freq = new HashMap<>();
            freqMap = new ArrayList<>();
            maxFreq = 0;

            freqMap.add(new Stack<>()); // dummy.
        }

        public void push(int val) { // O(1)
            freq.put(val, freq.getOrDefault(val, 0) + 1);
            maxFreq = Math.max(maxFreq, freq.get(val));

            if (freqMap.size() == maxFreq)
                freqMap.add(new Stack<>());
            freqMap.get(freq.get(val)).add(val);
        }

        public int pop() { // O(1)
            int rv = freqMap.get(maxFreq).pop();
            if (freqMap.get(maxFreq).size() == 0)
                freqMap.remove(maxFreq--);

            freq.put(rv, freq.get(rv) - 1);
            if (freq.get(rv) == 0)
                freq.remove(rv);

            return rv;
        }
    }

    // 895 PQ solution
    class FreqStack {
        private class pair implements Comparable<pair> {
            int val = 0;
            int freq = 0;
            int idx = 0;

            pair(int val, int freq, int idx) {
                this.val = val;
                this.freq = freq;
                this.idx = idx;
            }

            public int compareTo(pair o) {
                if (this.freq == o.freq)
                    return o.idx - this.idx; // other - this, for max PQ

                return o.freq - this.freq;
            }
        }

        private HashMap<Integer, Integer> freqMap;
        private PriorityQueue<pair> pq;
        private int idx = 0;

        public FreqStack() {
            this.freqMap = new HashMap<>();
            this.pq = new PriorityQueue<>();
            this.idx = 0;
        }

        public void push(int val) { // Log(n)
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
            pq.add(new pair(val, freqMap.get(val), idx++));
        }

        public int pop() { // Log(n)
            pair p = pq.remove();
            freqMap.put(p.val, freqMap.get(p.val) - 1);
            if (freqMap.get(p.val) == 0)
                freqMap.remove(p.val);

            return p.val;
        }
    }

    // Trapping Rain Water II
    public int trapRainWater(int[][] heightMap) {
        int n = heightMap.length, m = heightMap[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int i1 = a / m, j1 = a % m;
            int i2 = b / m, j2 = b % m;

            return heightMap[i1][j1] - heightMap[i2][j2];
        });

        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    vis[i][j] = true;
                    pq.add(i * m + j);
                }
            }
        }

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        int minBoun = 0, water = 0;

        while (pq.size() != 0) {
            int idx = pq.remove();
            int i = idx / m, j = idx % m;

            minBoun = Math.max(minBoun, heightMap[i][j]);
            water += minBoun - heightMap[i][j];

            for (int d = 0; d < 4; d++) {
                int r = i + dir[d][0];
                int c = j + dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                    vis[r][c] = true;
                    pq.add(r * m + c);
                }
            }
        }

        return water;
    }

}

    public int swimInWater(int[][] grid) {
        int n = grid.length, m = n;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int i1 = a / m, j1 = a % m;
            int i2 = b / m, j2 = b % m;

            return grid[i1][j1] - grid[i2][j2];
        });

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        boolean[][] vis = new boolean[n][m];

        pq.add(0);
        vis[0][0] = true;

        int minHeight = 0, time = 0;

        while (pq.size() != 0) {
            int idx = pq.remove();
            int i = idx / m, j = idx % m;
            int height = grid[i][j];

            time += Math.max(0, height - minHeight);
            if (i == n - 1 && j == m - 1)
                break;

            minHeight = Math.max(minHeight, height);

            for (int d = 0; d < dir.length; d++) {
                int r = i + dir[d][0];
                int c = j + dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                    vis[r][c] = true;
                    pq.add(r * m + c);
                }
            }
        }

        return time;
    }

    class MedianFinder {

        private PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        private PriorityQueue<Integer> minPQ = new PriorityQueue<>();

        /** initialize your data structure here. */
        public MedianFinder() {

        }

        public void addNum(int num) {
            if (maxPQ.size() == 0 || num <= maxPQ.peek())
                maxPQ.add(num);
            else
                minPQ.add(num);

            if (maxPQ.size() - minPQ.size() == 2)
                minPQ.add(maxPQ.remove());
            if (maxPQ.size() - minPQ.size() == -1)
                maxPQ.add(minPQ.remove());
        }

        public double findMedian() {
            if (maxPQ.size() == minPQ.size())
                return ((maxPQ.peek() + minPQ.peek()) / 2.0);
            else
                return maxPQ.peek() * 1.0;
        }
    }

    // 23
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });

        for (int i = 0; i < lists.length; i++)
            if (lists[i] != null)
                pq.add(lists[i]);

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;

        while (pq.size() != 0) {
            ListNode rn = pq.remove();
            prev.next = rn;
            prev = prev.next;

            if (rn.next != null)
                pq.add(rn.next);
        }
        return dummy.next;
    }

    // 632
public int[] smallestRange(List<List<Integer>> nums) {     
    int n = nums.size();
    
    // {r,c}
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
        int r1 = a[0], c1 = a[1];
        int r2 = b[0], c2 = b[1];
        
        return nums.get(r1).get(c1) - nums.get(r2).get(c2);
    });
    
    int maxValue = -(int)1e9;
    for(int i=0;i<n;i++){
        pq.add(new int[]{i,0});
        maxValue = Math.max(maxValue,nums.get(i).get(0));
    }
    
    int range = (int)1e9;
    int sp = -1, ep = -1;
    
    while(pq.size() == n){
        int[] re = pq.remove(); // re : remove Element
        int r = re[0], c = re[1], val = nums.get(r).get(c);
        
        if(maxValue - val < range){
            range = maxValue - val;
            sp = val;
            ep = maxValue;
        }
        
        
        c++;
        if(c < nums.get(r).size()){
            pq.add(new int[]{r,c});
            maxValue = Math.max(maxValue,nums.get(r).get(c));
        }            
    }
    
    
    return new int[]{sp,ep};
}

// public static void test(String str){

// int[] freq = new int[26];
// for(int i =0;i<n;i++) freq[str.charAt(i) - 'a']++;
// PriorityQueue<Character> pq = new PriorityQueue<>((a,b)->{
// if(freq[a - 'a'] == freq[b - 'a']){
// return a - b;
// }

// return freq[a - 'a'] - freq[b - 'a'];
// });

// class comp{
// public:
// bool operator()(const auto& a,const auto& b) const{
// if(a.second == b.second)
// return a.first > b.first;

// return a.second > b.second;
// }
// };

// priority_queue<pair<char,int>,vector<pair<char,int>>,comp> pq;
// }
