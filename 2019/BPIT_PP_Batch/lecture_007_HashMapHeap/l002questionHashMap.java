public class l002questionHashMap{
    //349
    public int[] intersection(int[] nums1, int[] nums2) {
        
        HashSet<Integer> map = new HashSet<>();
        for(int ele: nums1) map.add(ele);
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int ele: nums2){
            if(map.contains(ele)){
                list.add(ele);
                map.remove(ele);
            }
        }
        
        int[] ans = new int[list.size()];
        for(int i=0;i<ans.length;i++) ans[i] = list.get(i);
        
        return ans;
    }
 
    //350
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele: nums1) map.put(ele,map.getOrDefault(ele,0)+1);
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int ele: nums2){
            if(map.containsKey(ele)){
                map.put(ele,map.get(ele) - 1);
                list.add(ele);
                
                if(map.get(ele) == 0) map.remove(ele);
            }
        }
        
        int[] ans = new int[list.size()];
        for(int i=0;i<ans.length;i++) ans[i] = list.get(i);
        
        return ans;
        
    }

    //128
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> map = new HashSet<>();
        
        for(int ele : nums) map.add(ele);
        
        int len = 0;
        for(int ele : nums){
            if(!map.contains(ele)) continue;
            
            map.remove(ele);
            int prev = ele-1;
            int next = ele+1;
            
            while(map.contains(prev)) map.remove(prev--);
            while(map.contains(next)) map.remove(next++);
            
            len = Math.max(len,next - prev - 1);
        }
        
        return len;
        
    }

    // 347
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele: nums) map.put(ele,map.getOrDefault(ele,0) + 1);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return map.get(a) - map.get(b);
        });
        
        for(int ele: map.keySet()){
            pq.add(ele);
            if(pq.size() > k) pq.poll();
        }
        
        int[] ans = new int[pq.size()];
        int i = 0;
        while(pq.size()!=0){
            ans[i++] = pq.poll();
            
        }
        return ans;   
    }
   
    //https://practice.geeksforgeeks.org/problems/check-arithmetic-progression/0
    public void mainsolve ()
	 {
	     Scanner scn = new Scanner(System.in);
	     
	     int t = scn.nextInt();
	  while(t-->0){
	      int n = scn.nextInt();
	      int[] arr = new int[n];
	      
	     HashSet<Integer> map = new HashSet<>();
	     
	     PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
	           return b - a;    
	     });
	     
	     for(int i = 0; i < n;i++){
	         arr[i] = scn.nextInt();
	         map.add(arr[i]);
	         pq.add(arr[i]);
	         
	         if(pq.size() > 2) pq.remove();
	     }
	     
	     if(n <= 2) {
	          System.out.println("YES");
	          continue;
	          
	      }
	     
	     int a = pq.poll();
	     int b = pq.poll();
	     
	     int d = a - b;
	     int ele = b + d;
	     int count = 1;
	     while(map.contains(ele)){
	         map.remove(ele);
	         count++;
	         ele += d;
	     }
	     
	     System.out.println((count == n) ? "YES":"NO");
	  }
     }
     
     // 760
     public int[] anagramMappings(int[] A, int[] B) {
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        
        int n = A.length;
        for(int i = 0;i<n;i++) {
            map.putIfAbsent(B[i],new ArrayList<>());
            map.get(B[i]).add(i);
        }
        
        int[] ans = new int[n];
        
        for(int i=0;i<n;i++){
            ArrayList<Integer> li = map.get(A[i]);
            ans[i] = li.get(li.size() - 1);
            li.remove(li.size() - 1);
            if(li.size() == 0) map.remove(A[i]);
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

    // https://www.geeksforgeeks.org/maximum-consecutive-ones-or-zeros-in-a-binary-array/

    class FreqStack {
    
        HashMap<Integer,Integer> freq = new HashMap<>();
        HashMap<Integer,Stack<Integer>> map = new HashMap<>();
        int maxFreq = 0;
        
        public FreqStack() {
            
        }
        
        public void push(int x) {
            freq.put(x,freq.getOrDefault(x,0) + 1);
            maxFreq = Math.max(maxFreq,freq.get(x));
            
            map.putIfAbsent(freq.get(x),new Stack<>());
            map.get(freq.get(x)).push(x);
        }
        
        public int pop() {
            int rv = map.get(maxFreq).pop();
            freq.put(rv,maxFreq - 1);
            if(map.get(maxFreq).size() == 0) {
                map.remove(maxFreq);
                maxFreq--;
            }
            return rv;
            
        }
    }

    //https://www.geeksforgeeks.org/check-two-strings-k-anagrams-not/
    //Sol 1
    public static boolean kAnagram(String s1,String s2){
        if(s1.length() != s2.length()) return false;
        sort(s1);
        sort(s2);
        int count = 0;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i) != s2.charAt(i) && ++count > k) return false;
        }
        return true;
    }
    
    //Sol 2
    public static boolean kAnagram(String s1,String s2){
        if(s1.length() != s2.length()) return false;
        int[] f1 = new int[26];
        int[] f2 = new int[26];
        int count = 0;

        for(int i=0;i<s1.length();i++) f1[s1.charAt(i) - 'a']++;
        for(int i=0;i<s1.length();i++) f2[s2.charAt(i) - 'a']++;
        for(int i=0;i<s1.length();i++) count += Math.abs(f2[i] - f1[i]);
        
        return true;
    }

    // https://www.geeksforgeeks.org/check-anagram-string-palindrome-not/
    public static boolean palindromeAnagram(String str){
        int n = str.length();
        int[] freq = new int[26];
        for(int i=0;i<n;i++) freq[str.charAt(i) - 'a']++;

        int oddCount = 0;
        for(int i=0;i<26;i++){
            if((freq[i] % 2) != 0 && ++oddCount > 1) return false;   
        }

        return true;
    }

    // -> https://www.geeksforgeeks.org/check-if-frequency-of-all-characters-can-become-same-by-one-removal/

    // 407
    public int trapRainWater(int[][] arr) {
        if(arr.length == 0 || arr[0].length == 0) return 0;
        int n = arr.length;
        int m = arr[0].length;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return arr[a/m][a%m] - arr[b/m][b%m];
        });
        
        int maxWater = 0;
        int boun = 0;
        boolean[][] vis = new boolean[n][m];
        
        for(int i=0;i<n;i++){
            for(int j =0;j<m;j++){
                if(i==0 || j==0 || i == n - 1 || j == m - 1){
                    pq.add(i*m+j);
                    vis[i][j] = true;
                }`
            }
        }
        
        
        int[][] dir = {{1,0},{0,1},{0,-1},{-1,0}};
        while(pq.size() != 0){
            int idx = pq.poll();
            int r = idx/m;
            int c = idx%m;
            
            boun = Math.max(boun,arr[r][c]);
            maxWater += Math.max(0, boun - arr[r][c]);
            for(int d = 0;d<4;d++){
                int x = r + dir[d][0];
                int y = c + dir[d][1];

                if(x>=0 && y>=0 && x<n && y<m && !vis[x][y]){
                    
                    vis[x][y] = true;
                    pq.add(x * m + y);
                }
            }  
        }
        
        return maxWater;   
    }

    // 781
public int numRabbits(int[] arr) {
    if(arr.length == 0) return 0;
    
    int n = arr.length;
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    

    int ans = 0;
    for(int ele : arr){
        map.put(ele, map.getOrDefault( ele, 0 ) + 1);
        if(map.get(ele) == 1) ans += ele + 1;
        
        if(map.get(ele) == ele + 1) map.remove(ele);
    }
    
    return ans;     
}

//560
public int subarraySum(int[] nums, int k) {
    int n = nums.length;
    if(n == 0 ) return 0;
    
    
    HashMap<Integer,Integer> map = new HashMap<>();
    map.put(0,1);
    int count = 0;
    int sum = 0;
    for(int ele: nums){
        sum += ele;
        if(map.containsKey(sum-k)) count += map.get(sum-k);
        
        map.put(sum,map.getOrDefault(sum,0)+1);
    }
    
    return count;   
}

// Leetcode 974
public int subarraysDivByK(int[] nums, int K) {
    int n = nums.length;
    if(n == 0 ) return 0;
    
    
    HashMap<Integer,Integer> map = new HashMap<>();
    map.put(0,1);
    int count = 0;
    int sum = 0;
    for(int ele: nums){
        sum += ele;
        int val = (sum%K + K )%K;
        if(map.containsKey(val)) count += map.get(val);
        
        map.put(val,map.getOrDefault(val,0)+1);
    }
    
    return count;   
}

// https://practice.geeksforgeeks.org/problems/longest-sub-array-with-sum-k/0
public static void main (String[] args)
	{
	    
	    Scanner scn = new Scanner(System.in);
	    int t = scn.nextInt();
	    while(t-->0){
	        int n = scn.nextInt();
	        int k = scn.nextInt();
	        
	        HashMap<Integer,Integer> map = new HashMap<>();
	        map.put(0,-1);
	        int maxLen = 0;
	        int sum = 0;
	        
	        for(int i=0;i<n;i++){
	           sum  += scn.nextInt();
	           int val = ( sum - k );
	           
	           if(map.containsKey(val)) maxLen = Math.max(maxLen, i - map.get(val));
	           
	           if(!map.containsKey(sum)) map.put(sum,i);
	        }
	        
	        System.out.println(maxLen);
	    }
    }
    
    // https://practice.geeksforgeeks.org/problems/longest-subarray-with-sum-divisible-by-k1259/1
    int longSubarrWthSumDivByK(int a[], int n, int k)
    {
        HashMap<Integer,Integer> map = new HashMap<>();
	    map.put(0,-1);
	    int maxLen = 0;
	    int sum = 0;
	        
	        for(int i=0;i<n;i++){
	           sum  += a[i];
	           int val = ( sum%k + k )%k;
	           
	           if(map.containsKey(val)) maxLen = Math.max(maxLen, i - map.get(val));
	           
	           if(!map.containsKey(val)) map.put(val,i);
	        }
	        
	        return maxLen;
    }
   
    // 525 , equal no of 0 and 1 
    public int findMaxLength(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int maxLen = 0;
        int sum = 0;
             
        for(int i=0;i<nums.length;i++){
            sum  += nums[i] == 1?1:-1;
            int val = sum - 0;
            if(map.containsKey(val)) maxLen = Math.max(maxLen, i - map.get(val));
                
                if(!map.containsKey(sum)) map.put(sum,i);
        }
         
         return maxLen;
     }

     // https://www.geeksforgeeks.org/count-subarrays-equal-number-1s-0s/
     // Count of all subarray equal no of 0 and 1
     public int findMaxLength(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int count = 0;
        int sum = 0;
             
        for(int i=0;i<nums.length;i++){
            sum  += nums[i]==1 ? 1 : -1;
            int val = sum - 0;
            if(map.containsKey(val)) count += map.get(val);   
            map.put(sum,map.getOrDefault(sum,0) + 1);
        }
         
         return count;
     }

    // 930
    public int numSubarraysWithSum(int[] nums, int S) {
       HashMap<Integer,Integer> map = new HashMap<>();
	   map.put(0,1);
	   int count = 0;
	   int sum = 0;
	        
	   for(int i=0;i<nums.length;i++){
           sum  += nums[i];
           int val = sum - S;
	       if(map.containsKey(val)) count += map.get(val);   
	       map.put(sum,map.getOrDefault(sum,0) + 1);
	   }
        
        return count;
    }

    public int numOfSubarraysSumLessK(int[] arr,int k){
        int si = 0,ei=0;
        int n = arr.length;

        int count = 0;
        int sum = 0;
        while(ei < n){
            sum += arr[ei++];
            while(sum > k && si < ei){
                sum -= arr[si++]; 
            }

            count += (ei - si);
        }

        return count;
    }
    
    public int numSubarraysWithSum(int[] nums, int S) {
        return numOfSubarraysSumLessK(nums,S) - numOfSubarraysSumLessK(nums, S-1);
     }


    //https://www.geeksforgeeks.org/number-subarrays-sum-less-k/
    public static void numOfSubarraysSumLessK(int[] arr,int k){
        int si = 0,ei=0;
        int n = arr.length;

        int count = 0;
        int sum = 0;
        while(ei < n){
            sum += arr[ei++];
            while(sum > k && si < ei){
                sum -= arr[si++]; 
            }

            count += (ei - si);
        }

        return count;
    }


    // Leetcode 003
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

    // 76
    public String minWindow(String s, String t) {
        
        int ns = s.length();
        int nt = t.length();
        int[] freq = new int[128];
        for(int i=0;i<nt;i++) freq[t.charAt(i)]++;
        
        int count = nt;
        int si = 0,ei =0,len = (int)1e8;
        int head = 0;
        
        while(ei < ns){
            if(freq[s.charAt(ei++)]-- > 0) count--;
            while(count == 0){
                if(ei - si < len) len = ei - (head = si);
                if(freq[s.charAt(si++)]++ == 0) count++;
            }
        }
        
        return  len == (int)1e8 ? "" : s.substring(head, head + len);
    }

    //https://www.geeksforgeeks.org/smallest-window-contains-characters-string/

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        
        int[] freq = new int[128];
        int si = 0,ei = 0,len = 0,count = 0;
        while(ei < n){
            if(freq[s.charAt(ei++)]++ == 0) count++;
            
            while(count > k){
                if(freq[s.charAt(si++)]-- == 1) count--;
            }
            
            len = Math.max(len, ei - si);            
        }
        
        return len;         
    }

    // for you -> Leetcode 159

    // 239
    public int[] maxSlidingWindow(int[] arr, int k) {
        int n = arr.length;
        // arr[i], i
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            return b[0] - a[0];
        });
        
        int[] ans = new int[n-k+1];
        int idx = 0;
        
        for(int i=0;i<n;i++){
            while(pq.size()!=0 && pq.peek()[1] <= i - k) pq.poll();
            
            pq.add(new int[]{arr[i],i});
            
            if(i >= k-1) ans[idx++] = pq.peek()[0];
        }
        
        return ans;   
    }

    public int[] maxSlidingWindow(int[] arr, int k){
        int n = arr.length;
        ArrayDeque<Integer> que = new ArrayDeque<>();
        
        int[] ans = new int[n-k+1];
        int idx = 0;
        
        for(int i = 0; i < n; i++){

            while(que.size()!=0 && i - k >= que.getFirst()) que.removeFirst();
            while(que.size() !=0 && arr[que.getLast()] <= arr[i]) que.removeLast();

            que.addLast(i);

            if(i >= k-1) ans[idx++] = arr[que.getFirst()];
        }

        return ans;
    }

    // https://www.geeksforgeeks.org/length-largest-subarray-contiguous-elements-set-1/
    // without duplicate
    public static void largestSubarray(int[] arr){
        int len = 0;
        int n = arr.length;

        int min = 0, max = 0;
        for(int i=0;i < n;i++){
            min = max = arr[i];

            for(int j = i+1;j<n;j++){
                min = Math.min(min,arr[j]);
                max = Math.max(max,arr[j]);

                if(max - min == j - i) len = Math.max(len, j - i);
            }
        }
    }

    //https://www.geeksforgeeks.org/length-largest-subarray-contiguous-elements-set-2/
    // with duplicate
    public static void largestSubarray(int[] arr){
        int len = 0;
        int n = arr.length;

        int min = 0, max = 0;
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i < n;i++){
            
            min = max = arr[i];
            set.add(arr[i]);

            for(int j = i+1;j<n;j++){
                if(set.contains(arr[j])) break;
                set.add(arr[j]);

                min = Math.min(min,arr[j]);
                max = Math.max(max,arr[j]);

                if(max - min == j - i) len = Math.max(len, j - i);
            }

            set.clear();
        }
    }

    // 954
    public boolean canReorderDoubled(int[] arr) {
        if(arr.length == 0) return true;
          
        int n = arr.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele : arr) map.put(ele, map.getOrDefault(ele,0)+1);
        
        Integer[] ar = new Integer[n];
        for(int i =0 ;i<n;i++) ar[i] = arr[i];
        
        Arrays.sort(ar,(a,b)->{
            return Math.abs(a) - Math.abs(b);
        });
        
        for(int ele: ar){
            if(map.get(ele)==0) continue;
            if(map.getOrDefault(2 * ele,0) <= 0 ) return false;
            
            map.put(ele, map.getOrDefault(ele,0) - 1);
            map.put(2 * ele, map.getOrDefault(2 * ele,0) - 1);
            
            
        }       
        return true;
    }





}