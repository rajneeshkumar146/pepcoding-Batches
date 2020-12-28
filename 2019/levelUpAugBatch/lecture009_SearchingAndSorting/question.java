public class question{
    //leetcode 34
    public int binarySearchFirstIndex(int[] arr,int data){
        int si = 0,ei = arr.length - 1;
        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] == data){
                if(mid - 1 >= 0 && arr[mid - 1] == data) ei = mid - 1;
                else return mid;
            }else if(arr[mid] < data) si = mid + 1;
            else ei = mid - 1;
        }

        return -1;
    }

    public int binarySearchLastIndex(int[] arr,int data){
        int si = 0,ei = arr.length - 1;
        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] == data){
                if(mid + 1 < arr.length && arr[mid + 1] == data) si = mid + 1;
                else return mid;
            }else if(arr[mid] < data) si = mid + 1;
            else ei = mid - 1;
        }

        return -1;
    }

    public int[] searchRange(int[] arr, int data) {
//    int[] ans = new int[2];

//    ans[0] = binarySearchFirstIndex(arr,data);
//    ans[1] = binarySearchLastIndex(arr,data);

//    return ans;
        
      return new int[]{binarySearchFirstIndex(arr,data),binarySearchLastIndex(arr,data)};
}

// 74
public boolean searchMatrix(int[][] matrix, int target) {
    if(matrix.length == 0 || matrix[0].length == 0) return false;
    int n = matrix.length, m = matrix[0].length;
    int si = 0, ei = n * m - 1;
    while(si <= ei){
        int mid = (si + ei) / 2;
        int val = matrix[mid / m][mid % m];

        if(val == target) return true;
        else if(val < target) si = mid + 1;
        else ei = mid - 1;
    }
    return false;
}


static long totalInversionCount(long[] arr, long[] sortedArray,  int si,  int mid,  int ei)
{
     long count = 0;
     int i = si, j = mid, k = si;

    while (i < mid && j <= ei)
    {
        if (arr[i] <= arr[j]){
            sortedArray[k++] = arr[i++];
            // arr[i] > arr[j] and i > j; count += ei - j + 1;
        }else{
            sortedArray[k++] = arr[j++];
            count += mid - i;  
        }
    }

    while (i < mid)
        sortedArray[k++] = arr[i++];
    while (j <= ei)
        sortedArray[k++] = arr[j++];

    while (si <= ei)
        arr[si] = sortedArray[si++];
    return count;
}

static long inversionCount(long[] arr, long[] sortedArray, int si,int ei)
{
    if (si >= ei)
        return 0;

    int mid = (si + ei) / 2;
    long count = 0;

    count += inversionCount(arr, sortedArray, si, mid);
    count += inversionCount(arr, sortedArray, mid + 1, ei);

    count += totalInversionCount(arr, sortedArray, si, mid+1, ei);
    return count;
}

static long inversionCount(long[] arr, long N)
{
    if (N == 0)
        return 0;
    int n = (int)N;
    long[] sortedArray = new long[n];
    return inversionCount(arr, sortedArray, 0, n - 1);
}

public int binarySearch(int[] arr,int data){
    int si = 0, ei = arr.length;
    while(si < ei){
        int mid = (si + ei) / 2; 
        if(data > arr[mid]) si = mid + 1;
        else ei = mid;
    }

    return si;
}

public List<Integer> findClosestElements(int[] A, int k, int x) {
    List<Integer> arr = new ArrayList<>();
    for(int ele : A) arr.add(ele);
    
    int n = A.length;
    
    if(x <= A[0]) return arr.subList(0,k);
    else if(x >= A[n - 1]) return arr.subList(n - k, n);
    else{
        
        int idx = binarySearch(A,x);   // where we suppose to find the x element.
        int si = Math.max(0,idx - k);
        int ei = Math.min(n - 1, idx + k);
        
        while((ei - si + 1) > k){
            if((x - A[si]) > (A[ei] - x)) si++;
            else ei--;
        }
        return arr.subList(si, ei + 1);
    }
}

//300
public int binarySearch(ArrayList<Integer> arr,int data){
    int si = 0, ei = arr.size();
    while(si < ei){
        int mid = (si + ei) / 2; 
        if(data > arr.get(mid)) si = mid + 1;
        else ei = mid;
    }

    return si;
}

public int lengthOfLIS(int[] arr)
{
    if (arr.length <= 1)
        return arr.length;
    int n = arr.length;

    ArrayList<Integer> list = new ArrayList<>();

    for (int ele : arr)
    {
        // int idx = Collections.binarySearch(list,ele);
        // if(idx < 0) idx = -idx - 1;
        
        int idx = binarySearch(list,ele);
        if (idx == list.size())
            list.add(ele);

        list.set(idx, ele);
    }

    return list.size();
}

//875
bool isPossibleToEat(vector<int>&piles,int eatingSpeed,int H){
    int hours = 0;
    for(int i = piles.size()-1;i>=0;i--){
        hours += ceil(piles[i] / (eatingSpeed * 1.0));
        if(hours > H) return false;
    }
    
    return true;
}

int minEatingSpeed(vector<int>& piles, int H) {    
    // sort(piles.begin(),piles.end());
    int n = piles.size();
    int minSpeed = 1, maxSpeed = (int)1e9;
    
    while(minSpeed < maxSpeed){
        int eatingSpeed = minSpeed + (maxSpeed - minSpeed) / 2;
        
        if(isPossibleToEat(piles,eatingSpeed,H)) maxSpeed = eatingSpeed;
        else minSpeed = eatingSpeed + 1;
    }
    
    return maxSpeed;
}


public static void isPossibleToServe(double[] area,double area,int k){
    int count = 0;
    for(double ele : area){
        count += ele / area;
        if(count >= k) return true;
    }

    return false;
}


public static void servingAreaCake(int[] radii,int k){
    int n = radii.length;
    double[] area = new double[n];

    double lo = 0.0, hi = 0.0,mid = 0.0;
    for(int i = 0;i < n; i++){
        area[i] = radii[i] * radii[i] * Math.PI;
        hi = Math.max(hi,area[i]);
    }

    while(hi - lo > 1e-5){
        mid = lo + (hi - lo) / 2.0;
        if(isPossibleToServe(area,mid,k)) lo = mid;
        else hi = mid;
    }

    return mid;
}

public boolean checkIsValid(int[] arr,double mid,int K){
    int count = 0;
    for(int i = 1;i<arr.length;i++){
        count += (int)((arr[i] - arr[i-1])/mid);
        if(count > K) return true;
    }
    
    return false;
}

public double minmaxGasDist(int[] arr, int K) {
    double lo = 0.0, hi = 1e9, mid = 0.0;
    while((hi - lo) >= 1e-5 ){
        mid = lo + (hi - lo) / 2.0;
        
        if(checkIsValid(arr,mid,K)) lo = mid + 1e-6;
        else hi = mid ;
    }
    
    return hi;
}


//33
public int search(int[] arr, int data) {
    int lo = 0, hi = arr.length - 1;
    while(lo <= hi){
        int mid = (hi + lo) / 2;
        
        if(arr[mid] == data) return mid;
        else if(arr[lo] <= arr[mid]){
            if(arr[lo] <= data && data < arr[mid]) hi = mid - 1;
            else lo = mid + 1; 
        }else{
            if(arr[mid] < data && data <= arr[hi]) lo = mid + 1;
            else hi = mid - 1;
        }
    }
    
    return -1;
}

//81
public boolean search(int[] arr, int data) {
    int lo = 0, hi = arr.length - 1;
    while(lo <= hi){
    int mid = (hi + lo) / 2;
    
    if(arr[mid] == data || arr[lo] == data) return true;
    else if(arr[lo] < arr[mid]){
        if(arr[lo] <= data && data < arr[mid]) hi = mid - 1;
        else lo = mid + 1; 
    }else if(arr[mid] < arr[hi]){
        if(arr[mid] < data && data <= arr[hi]) lo = mid + 1;
        else hi = mid - 1;
    }else lo++;
}

return false;
}

//153
public int findMin(int[] arr) {
    int lo = 0, hi = arr.length - 1;
    if(arr[lo] <= arr[hi]) return arr[lo];
    
    while(lo < hi){
        int mid = (lo + hi) / 2;
        
        if(arr[mid] < arr[hi]) hi = mid;
        else if(arr[lo] <= arr[mid]) lo = mid + 1;
        // else lo++; // if ele is duplicate.
    }
    
    return arr[lo];
}

// 001

public int[] twoSum(int[] nums, int target) {    
    HashMap<Integer,Integer> map = new HashMap<>();
    for(int i = 0;i<nums.length;i++){
        int ele = nums[i];
        if(map.containsKey(target - ele)) return new int[]{map.get(target-ele),i};
        
        map.put(ele,i);
    }
    
    return new int[]{-1,-1};
}

public int[] twoSum(int[] arr, int data) {
        
    int si = 0, ei = arr.length - 1;
    while(si < ei){
        int sum = arr[si] + arr[ei];
        if(sum == data) return new int[]{si + 1,ei + 1};
        else if(sum < data) si++;
        else ei--;
    }
    return new int[]{-1,-1};
}

public List<List<Integer>> threeSum(int[] arr) {
    Arrays.sort(arr);
    int n = arr.length;
    int data = 0;
    List<List<Integer>> res = new ArrayList<>();
    
    for(int i = 0;i < n;i++){
        while( i != 0 && i < n && arr[i] == arr[i-1]) i++;
        int j = i + 1, k = n - 1;
        
        while(j < k){
            int sum = arr[i] + arr[j] + arr[k];
            if(sum == data){
                // List<Integer> ar = new ArrayList<>();
                // ar.add(arr[i]);
                // ar.add(arr[j]);
                // ar.add(arr[k]);
                // res.add(ar);
                
                res.add(Arrays.asList(arr[i],arr[j],arr[k]));
                
                j++;
                k--;
                
                while( j < k && arr[j] == arr[j-1]) j++;
                while( j < k && arr[k] == arr[k+1]) k--;
            }else if(sum < data)
                 j++;
            else 
                 k--;
        }
    }
    
    return res;
}

public List<List<Integer>> fourSum(int[] arr, int target) {
    if(arr.length < 4) return new ArrayList<>();
    Arrays.sort(arr);
    int n = arr.length;
    List<List<Integer>> ans = new ArrayList<>();

for(int i = 0; i < n;i++){
    if(i > 0 && arr[i] == arr[i - 1]) continue;
    
    for(int j = i + 1; j < n;j++){
        if(j > i + 1 && arr[j] == arr[j - 1]) continue;

        int k = j + 1, l  = n - 1;
        while(k < l){
            int sum = arr[i] + arr[j] + arr[k] + arr[l];
            if(sum == target){
                ans.add(Arrays.asList(arr[i],arr[j],arr[k],arr[l]));
                k++;
                l--;

                while(k < l && arr[k] == arr[k - 1]) k++;
                while(k < l && arr[l] == arr[l + 1]) l--;

            }else if(sum < target) 
                k++;
            else 
                l--; 
        }
    }
}
return ans;     
}

public static void makeAns(List<List<Integer>> ans,List<List<Integer>> smallAns){
    if(smallAns.size() > 0){
        for(List<Integer> al : smallAns){
            al.add(arr[i]);
            ans.add(al);
        }
    }
}

public static List<List<Integer>> twoSum(int[] arr,int data,int si,int ei){
    List<List<Integer>> ans = new ArrayList<>();
    int i = si, j = ei;
    while(i < j){
        int sum = arr[i] + arr[j];
        if(sum == data){
            ans.add(Arrays.asList(arr[i],arr[j]));
            i++;
            j--;

            while(i < j && arr[i] == arr[i - 1]) i++;
            while(i < j && arr[j] == arr[j + 1]) j--;
        }
        else if(sum < data) i++;
        else j--;
    }

    return ans;
}

public static List<List<Integer>> threeSum(int[] arr,int data,int si,int ei){
    List<List<Integer>> ans = new ArrayList<>();
    for(int i = si; i <= ei;i++){
        if(i != si && arr[i] == arr[i - 1]) continue;
        List<List<Integer>> smallAns = twoSum(arr,data - arr[i], i + 1, ei);
        makeAns(ans,smallAns);   
    }

    return ans;
}

public List<List<Integer>> fourSum(int[] arr, int data,int si,int ei) {
    List<List<Integer>> ans = new ArrayList<>();
    for(int i = si;i <= ei;i++){
        if(i != si && arr[i] == arr[i - 1]) continue;
        List<List<Integer>> smallAns = threeSum(arr,data - arr[i], i + 1, ei);
        makeAns(ans,smallAns);
    }

    return ans;
}

public List<List<Integer>> fourSum(int[] arr, int data) {
    if(arr.length < 4) return new ArrayList<>();
    Arrays.sort(arr);
    
    int n = arr.length;
    return fourSum(arr,data,0,n - 1);
}


//454
public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
    HashMap<Integer,Integer> map = new HashMap<>();
    
    for(int e1: A){
        for(int e2 : B){
            map.put(e1 + e2, map.getOrDefault(e1+e2,0) + 1);
        }
    }
    
    int count = 0;
    for(int e1: C){
        for(int e2 : D){
            count += map.getOrDefault(-(e1 + e2),0);
        }
    }
    
    return count;    
}

public int twoSumCount(int[] A, int[] B) {
    Arrays.sort(A);
    Arrays.sort(B);

    int i = 0, j = B.length - 1;
    int ans = 0;

    while(i < A.length && j >= 0){
        int sum = A[i] + B[j];
        if(sum < 0) i++;
        else if(sum > 0) j--;
        else{
            int countA = 1, countB = 1;
            while(++i < A.length && A[i] == A[i - 1]) countA++;
            while(--j >= 0 && B[j] == B[j + 1]) countB++;

            ans += countA * countB;
        }
    }
}

public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
   int n = A.length;
   int[] ABcombination = new int[n*n];
   int[] CDcombination = new int[n*n];
   
   int k = 0;
   for(int i = 0;i<n;i++){
       for(int j = 0;j<n;j++){
        ABcombination[k] = A[i] + B[j];
        CDcombination[k] = C[i] + D[j];
        k++;
       }
   }

   return twoSumCount(ABcombination,CDcombination);
}

//240
public boolean searchMatrix(int[][] matrix, int target) {
    int n = matrix.length;
    int m = matrix[0].length;
    
    int i = n - 1;
    int j = 0;
    while(i >=0 && j < m){
        if(matrix[i][j]== target) return true;
        else if(matrix[i][j] < target) j++;
        else i--;
    }
    
    return false;
}

public double findMedianSortedArrays(int[] arr1, int[] arr2) {
    int n = arr1.length;
    int m = arr2.length;
    if(n > m) return findMedianSortedArrays(arr2,arr1);

    int gMid = (n + m + 1) >> 1; // mid is right oriented, so more no of elements belong to left region.

    int si = 0, ei = n;
    while(si <= ei){
        int sMid = (si + ei) >> 1;
        int lMid = gMid - sMid;

        int sLeftRegionEle = sMid - 1 >=0 ? arr1[sMid - 1] : -(int)1e8;
        int sRightRegionEle = sMid < n ? arr1[sMid] : (int) 1e8;

        int lLeftRegionEle = lMid - 1 >= 0? arr2[lMid - 1] : -(int)1e8;
        int lRightRegionEle = lMid < m ? arr2[lMid] : (int) 1e8;

        if(sLeftRegionEle > lRightRegionEle) ei = sMid - 1;
        else if(lLeftRegionEle > sRightRegionEle) si = sMid + 1;
        else{

            int leftBoundaryEle = Math.max(sLeftRegionEle, lLeftRegionEle);
            int rightBoundaryEle = Math.min(sRightRegionEle,lRightRegionEle);

            if(((n + m) & 1) != 0) return leftBoundaryEle * 1.0;
            else return (leftBoundaryEle + rightBoundaryEle) / 2.0;
        }
    }


    return 0.0;
}

//134
public int canCompleteCircuit(int[] gas, int[] cost) {
    int extraGas = 0;
    int sp = 0;
    int dificit =0;
    
    for(int i=0;i<gas.length;i++){
        extraGas += gas[i] - cost[i];
        if(extraGas < 0){
            dificit += extraGas;
            extraGas = 0;
            sp = i + 1;
        }    
    }

    return (sp == gas.length || extraGas + dificit < 0) ? -1 : sp;
}

}