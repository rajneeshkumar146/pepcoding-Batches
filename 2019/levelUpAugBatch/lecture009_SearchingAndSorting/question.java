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

public double minmaxGasDist(int[] stations, int K) {
        
}

}