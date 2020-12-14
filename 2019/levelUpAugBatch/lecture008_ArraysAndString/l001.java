class l001 {
    //Leetcode 75
    public void sortColors(int[] arr) {
             if (arr.length == 0)
        return;

    int p1 = -1, idx = 0, n = arr.length, p2 = n - 1;
    while (idx <= p2)
    {
        if (arr[idx] == 0)
            swap(arr,++p1, idx++);
        else if (arr[idx] == 2)
            swap(arr,idx, p2--);
        else idx++;
    } 
    }
    
    public void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        
    }

    public void reverse(int[] arr,int i,int j){
        while(i<j){
            swap(arr,i++,j--);
        }
    }

    public void rotate(int[] arr, int k) {
        if(arr.length == 0) return;
        
        k = (k % n + n) % n;

        int n = arr.length;
        reverse(arr,0 , n - 1);
        reverse(arr,0, k - 1);
        reverse(arr, k , n - 1);
    }

    public int maSumInRotation(int[] arr){

        int sum = 0,rotatedSum = 0, n = arr.length;
        
        for(int ele : arr) sum += ele;
        for(int i = 0;i<arr.length;i++)  rotatedSum += arr[i] * i;

        int maxSum = rotatedSum;
        // for(int i = 0;i<arr.length - 1;i++)
        //     maxSum = Math.max(maxSum, rotatedSum =  rotatedSum - sum + arr[i] * n);

        for(int i = 0;i<arr.length - 1;i++){
            rotatedSum  = rotatedSum - sum + arr[i] * n;
            maxSum = Math.max(maxSum, rotatedSum - sum + arr[i] * n);
        }

        return maxSum;
    }

    
public int lengthOfLongestSubstring(string s)
{
    if (s.length() <= 1)
        return s.length();

    int n = s.length(), si = 0, ei = 0, count = 0,len = 0;
    int Maxsi = 0, Maxei = 0;
    int[] map = new int[128];

    while(ei < n){
        if(map[s.charAt(ei++)]++ > 0) count++;

        while(count > 0){
            if(map[s.charAt(si++)]-- > 1) count--;
        }

        // len = Math.max(len,ei - si);
        if(ei - si > len){
            len  = ei - si;
            Maxei = ei;
            Maxsi = si;
        }
    }

    return len;
}

public String minWindow(String s, String t) {
    int ns = s.length();
    int nt = t.length();
    
    int[] freq = new int[128];
    for(int i=0;i<nt;i++) freq[t.charAt(i)]++;
    
    int requirement = nt, si = 0,ei = 0, len = (int)1e8;
    int head = 0;
    
    while(ei < ns){
        if(freq[s.charAt(ei++)]-- > 0) requirement--;
        
        while(requirement == 0){
            if(ei - si < len) len = ei - (head = si);
            
            if(freq[s.charAt(si++)]++ == 0) requirement++;
        }
    }
    
    return len == (int)1e8 ? "" : s.substring(head, head + len); 
}

//https://www.geeksforgeeks.org/smallest-window-contains-characters-string/

public int minWindow(String s) {
    int n = s.length();
    
    int[] freq = new int[128];
    for(int i=0;i<nt;i++) freq[s.charAt(i)] = 1;

    int requirement = 0, si = 0, ei = 0,head = 0, len = (int)1e8;
    for(int ele : freq) requirement += ele;

    while(ei < n){
        if(freq[s.charAt(ei++)]-- > 0) requirement--;

        while(requirement == 0){
            len = (ei - si <= len) ? ei - (head = si) : len;

            if(freq[s.charAt(si++)]++ == 0) requirement++;
        }
    }

    return len;
}

public int[] maxSlidingWindow(int[] nums, int k) {
    if(nums.length == 1 || k == 1) return nums;

    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
        // return nums[a] - nums[b];  // this - other for default behaviour,min PQ.
        return nums[b] - nums[a];  // other - this for reverse of default behaviour,max PQ
    });


    int n = nums.length;
    int[] ans = new int[n - k + 1];
    int idx = 0;

    for(int i = 0;i<n;i++){
        while(pq.size() != 0 && pq.peek() <= i - k) pq.remove();
        pq.add(i);

        if(i >= k - 1) ans[idx++] = nums[pq.peek()];
    }

    return ans;
}

public static void longestSubArrayDivisibleByK(int[] arr,int k){
    int n = arr.length;
    int[] psum = new int[n + 1];

    for(int i = 0; i < n; i++){
        psum[i + 1] = psum[i] + arr[i]; 
    }

    int len = 0;
    for(int i =0 ;i < n ;i++){
        for(int j = i;j < n; j++){
            if((psum[j + 1] - psum[i]) % k == 0) len = Math.max(j - i + 1, len);
        }
    }
}

int maxLen(int[] arr, int N){
    if(N <= 1) return 0;

    HashMap<Integer,Integer> map = new HashMap<>();
    map.put(0,-1);

    int sum = 0,len = 0;
    for(int i = 0 ; i < N ; i++){
        int val = arr[i];
        if(val == 0) val = -1;
        sum += val;

        if(map.containsKey(sum)) len = Math.max(len, i - map.get(sum));
        else map.put(sum,i);
    }

    return len;
}


int countLen(int[] arr, int N){
    if(N <= 1) return 0;

    HashMap<Integer,Integer> map = new HashMap<>();
    map.put(0,1);

    int sum = 0,count = 0;
    for(int i = 0 ; i < N ; i++){
        int val = arr[i];
        if(val == 0) val = -1;
        sum += val;

        count += map.getOrDefault(sum,0);
        map.put(sum,map.getOrDefault(sum,0) + 1);
    }

    // for(Integer key : map.keySet()){
    //     count += (map.get(key) * (map.get(key) - 1)) / 2
    // }

    return len;
}

//https://practice.geeksforgeeks.org/problems/longest-subarray-with-sum-divisible-by-k1259/1
int longSubarrWthSumDivByK(int arr[], int n, int k){
    if (n == 0)
        return 0;
        
    int len = 0;
    unordered_map<int, int> map;   // sum , index
    map[0] = -1;

    int sum = 0;
       for (int i = 0; i < n; i++)
      {
        sum += arr[i];

        int SUM = (sum % k + k) % k;
        if(map.find(SUM) != map.end()) len = max(len, i - map[SUM]);
        else map[SUM] = i;
       }

       return len;
}

// 974
public int subarraysDivByK(int[] arr, int k) {
    if(arr.length == 0) return 0;

   int[] map = new int[k + 1];
   map[0] = 1;
  
   int sum = 0,count = 0;
   for(int i = 0 ; i < arr.length ; i++){
       sum += arr[i];
       int SUM = (sum % k + k) % k;
       count += map[SUM];
       map[SUM]++;
   }

   return count;
}

public int numRabbits(int[] arr) {
    if(arr.length == 0) return 0;
    
    int n = arr.length;
    HashMap<Integer,Integer> map = new HashMap<>();
    
    int ans = 0;
    for(int ele : arr){
        if(!map.containsKey(ele)){
            ans += (ele + 1);
            map.put(ele, 1);
        }else{
            map.put(ele,map.get(ele) + 1);
        }
        
        if(map.get(ele) == ele + 1) map.remove(ele);
    }
    
    return ans;
}

// 930
public int numSubarraysWithSum(int[] arr, int S) {
    int n = arr.length;
    if(n == 0) return 0;
    
    int ei = 0, count = 0, psum = 0;
    
    // int[] freq = new int[30000 + 1];
    // freq[0] = 1;
    
    HashMap<Integer,Integer> map = new HashMap<>();
    map.put(0,1);
    while(ei < n){
        psum +=  arr[ei++];
        // if(psum - S >= 0)count += freq[psum - S];
        count += map.getOrDefault(psum - S,0);
        map.put(psum,map.getOrDefault(psum,0) + 1);            
    }
    
    return count;
}

public int numSubarraysWithAtMostSum(int[] arr,int S){
    int si = 0, ei = 0, count = 0, sum = 0, n = arr.length;

    while(ei < n){
        sum += arr[ei++];

        while(sum > k){
            sum -= arr[si++];
        }

        count += ei - si;
    }

    return count;
}

public int numSubarraysWithSum(int[] arr, int S) {
    int n = arr.length;
    if(n == 0) return 0;
    return numSubarraysWithAtMostSum(arr,S) - (S > 0 ? numSubarraysWithAtMostSum(arr,S - 1) : 0);
}

//1248
public int numberOfSubarraysAtMost(int[] arr, int k) {
    int n = arr.length;
    int si = 0, ei = 0, oddCount = 0, res = 0;

    while(ei < n){
        if((arr[ei++] & 1) != 0) oddCount++;

        while(oddCount > k){
            if((arr[si++] & 1) != 0) oddCount--;
        }

        res += ei - si;
    }

    return res;
}

public int numberOfSubarrays(int[] arr, int k) {
    int n = arr.length;
    if(n == 0) return 0;

    return numberOfSubarraysAtMost(arr,k) - numberOfSubarraysAtMost(arr,k - 1);
}


// 1004
public int longestOnes(int[] arr, int K) {
        
    int n = arr.length, ei = 0, si = 0, len = 0;
    int zeroCount  = 0;
    while(ei < n){
        if(arr[ei++] == 0) zeroCount++;
        
        while(zeroCount > K){
            if(arr[si++] == 0) zeroCount--;    
        }
        
        len = Math.max(len, ei - si);
    }
    
    
    return len;
}

// ======================================== 904 -> for you

public static void kadanesAlgo(int[] arr){
    int gMaxSum = -(int)1e8, runningSum = 0;
    for(int ele : arr){
        runningSum += ele;
        
        if(runningSum > gMaxSum) gMaxSum = runningSum;
        
        if(runningSum <= 0) runningSum = 0;
    }

    return gMaxSum;
}

public static void kadanesAlgoSubarray(int[] arr){
    int gMaxSum = -(int)1e8, runningSum = 0;
    int gsi = 0, gei = 0, rsi = 0; // gsi : gloabl start index, rsi : running start index
    for(int rei = 0; rei < arr.length; rei++){
        runningSum += arr[rei];
        
        if(runningSum > gMaxSum){
            gMaxSum = runningSum;
            gsi = rsi;
            gei = rei;
        }

        if(runningSum <= 0){
            runningSum = 0;
            rsi = rei + 1;
        }
    }
    return gMaxSum;
}

public static long kadanesAlgoGeneric(int[] arr){
    long gMaxSum = arr[0], runningSum = arr[0];
    for(int i = 1; i < arr.length; i++){
        runningSum = Math.max(arr[i],runningSum + arr[i]);
        gMaxSum = Math.max(gMaxSum, runningSum);
    }
    
    return gMaxSum;
}

// sum from starting
public long prefixSum(int[] arr){
    long gSum = -(int)1e9;
    long cSum = 0;
    for(int ele : arr){
        cSum = (cSum + ele) % mod;
        gSum = Math.max(gSum, cSum); 
    }

    return gSum;
}

// sum from last
public long suffixSum(int[] arr){
    long gSum = -(int)1e9;
    long cSum = 0;
    for(int i = arr.length - 1; i >= 0; i--){
        cSum = (cSum + arr[i]) % mod;
        gSum = Math.max(gSum, cSum); 
    }

    return gSum;
}

public int kConcatenationMaxSum(int[] arr, int k) {
    long KadanesSum = kadanesAlgo(arr) % mod;
    if(k == 1) return (int)KadanesSum;

    long sumOfArray = 0;
    for(int ele : arr) sumOfArray += ele;

    long prefixSum = prefixSum(arr);
    long suffixSum = suffixSum(arr);

    if(sumOfArray > 0){
        long APSum = ((k - 2) * sumOfArray  % mod + suffixSum % mod + prefixSum % mod) % mod;
        return (int)Math.max(APSum, KadanesSum);
    }else{
        return (int)(Math.max(suffixSum + prefixSum, KadanesSum));
    }
}

// Best method
 
public long kadanesSum(int[] arr,int k){
    int gMaxSum = 0, runningSum = 0;
    int n = arr.length;
    
    for(int i = 0; i < n * k; i++){
        runningSum = (runningSum + arr[i % n]) % mod;
        
        if(runningSum > gMaxSum) gMaxSum = runningSum;
        
        if(runningSum <= 0) runningSum = 0;
    }

    return gMaxSum % mod;
}

public int kConcatenationMaxSum(int[] arr, int k) {
    long prevSum = 0;
    for(int i = 1 ; i <= k && i <= 3;i++){
        long KSum =  kadanesSum(arr, i);
        if(i == k) return (int)KSum;
        
        if(i == 3){
            return (int)((prevSum + (k - 2) * (KSum - prevSum)) % mod);
        }

        prevSum = KSum;
    }
    return  0;
}

// 1074
public int numSubmatrixSumTarget(int[][] matrix, int k){    
    int n = matrix.length, m = matrix[0].length;
    for(int i = 1;i<n;i++){
        for(int j = 0;j<m;j++)
            matrix[i][j] += matrix[i-1][j];
    }
    
    int count = 0;
    for(int base = 0; base < n;base++){ 
        for(int row = base;row < n;row++){
            HashMap<Integer,Integer> map = new HashMap<>();
            map.put(0,1);
            int sum = 0;
            for(int j = 0;j < m; j++){
                
                sum += matrix[row][j] - (base != 0 ? matrix[base - 1][j] : 0);
                count += map.getOrDefault(sum - k, 0);
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        } 
    }
    
    return count;
}

public int maxSumSubmatrix(int[][] matrix, int k) {
    int n = matrix.length, m = matrix[0].length;
    for(int i = 1;i<n;i++){
        for(int j = 0;j<m;j++)
            matrix[i][j] += matrix[i-1][j];
    }
    
    int maxSum = -(int)1e9;
    for(int base = 0; base < n;base++){ 

        for(int row = base;row < n;row++){
            
            
            int gSum = -(int)1e9, rsum = 0;
            for(int j = 0; j < m; j++){
                int val  = matrix[row][j] - (base != 0 ? matrix[base - 1][j] : 0);
               
                rsum = Math.max(val,rsum + val);
                gSum = Math.max(gSum, rsum);

                if(gSum == k) return k;
            }
            
            if(gSum < k){
                maxSum = Math.max(maxSum, gSum);
                continue;
            }
            
            
           TreeSet<Integer> map = new TreeSet<>();
           int sum = 0;
           map.add(0);

           for(int j = 0; j <m ; j++){
               sum += matrix[row][j] - (base != 0 ? matrix[base - 1][j] : 0);
               
               if(map.contains(sum - k)) return k;
               
               Integer val = map.ceiling(sum - k);
               if(val != null){
                   maxSum = Math.max(maxSum,sum - val);
               }
               map.add(sum);
           }
        } 
    }

    return maxSum;
}

//152
public int maxProduct(int[] A) {
    int n = A.length, res = A[0], l = 0, r = 0;
    for (int i = 0; i < n; i++) {
        l =  (l == 0 ? 1 : l) * A[i];
        r =  (r == 0 ? 1 : r) * A[n - 1 - i];
        res = Math.max(res, Math.max(l, r));
    }
    return res;
}
}