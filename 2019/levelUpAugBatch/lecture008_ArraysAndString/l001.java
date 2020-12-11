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

}