public class questions{

    //Leetcode 74
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length ==0 || matrix[0].length == 0) return false;
        int n = matrix.length;
        int m = matrix[0].length;
        
        int si = 0, ei = (n * m) - 1;
        
        while(si <= ei){
            int mid = (si + ei) >> 1;
            if(matrix[mid/m][mid%m]==target) return true;
            else if(matrix[mid/m][mid%m] < target) si = mid + 1;
            else ei = mid - 1;
        }
        
        return false;
    }

    //for you : Leetcode 34 

    // 658
    public List<Integer> findClosestElements(int[] A, int k, int x) {
        List<Integer> arr = new ArrayList<>();
        for(int ele : A) arr.add(ele); 
        
        int n = A.length;
        
        if(x <= A[0]) return arr.subList(0,k);
        else if(x >= A[n-1]) return arr.subList(n - k ,n);
        else{
            int idx  = Collections.binarySearch(arr,x);
            if(idx < 0){
                idx = -idx - 1; 
            }
            
            int si = Math.max(0,idx - k);
            int ei = Math.min(idx + k, n - 1);
            
            while(ei - si > k - 1){
                if((x - A[si])  > (A[ei] - x)) si++;
                else ei--;
            }
            return arr.subList(si,ei+1);
        }   
    }

    //Leetcode 300
    public int lengthOfLIS(int[] nums) {
        if(nums.length <= 1) return nums.length;

        ArrayList<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        
        for(int i = 1; i < nums.length; i++){
            int idx = Collections.binarySearch(list,nums[i]);
            if(idx >= 0) continue;

            idx = -idx - 1;
            if(idx == list.size()) list.add(nums[i]);
            else list.set(idx,nums[i]);
        }

        return list.size();
    }

    public int lengthOfLIS(int[] nums) {
        if(nums.length <= 1) return nums.length;

        ArrayList<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        
        for(int i = 1; i < nums.length; i++){
            int si = 0, ei = list.size();
            while(si < ei){
                int mid = (si+ei)>>1;
                if(list.get(mid) < nums[i]) si = mid + 1;
                else ei = mid;
            }

            if(ei == list.size()) list.add(nums[i]);
            else list.set(ei,nums[i]);
        }

        return list.size();
    }


    // https://practice.geeksforgeeks.org/problems/inversion-of-array/0#
    public static int inversionCount_(int[] arr,int[] sortedArray,int si,int ei){
        if(si >= ei) return 0;
        
        int mid = (si + ei) / 2;
        int count = 0;
        
        count += inversionCount_(arr,sortedArray,si,mid);
        count += inversionCount_(arr,sortedArray,mid + 1,ei);

        count += totalInversion(arr,sortedArray,si,mid + 1, ei);
        return count;
    }

    public static int totalInversion(int[] arr,int[] sortedArray,int si,int mid,int ei){
        int count = 0;
        int i = si, j = mid, k = si;
        while(i <= mid - 1 && j <= ei){
            if(arr[i] <= arr[j])
                sortedArray[k++] = arr[i++];
            else{
                sortedArray[k++] = arr[j++];
                count += mid - i;
            }
        }

        while(i <= mid - 1) sortedArray[k++] = arr[i++];
        while(j <= ei) sortedArray[k++] = arr[j++];

        while(si <= ei) arr[si] = sortedArray[si++]; 
        return count;
    }

    public static int inversionCount(int[] arr){
        if(arr.length <= 1) return 0;

        int n = arr.length;
        int[] sortedArray = new int[n];

        return inversionCount_(arr,sortedArray,0,n-1);
    }

    //240
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0 || matrix[0].length==0) return false;
        
        int n = matrix.length;
        int m = matrix[0].length;
        
        int r = n -1 ;
        int c = 0;
        while(r >=0 && c < m){
            int ele = matrix[r][c];
            if(ele == target) return true;
            if(ele < target) c++;
            else r--;
        }
        
        return false;
    }

    //875
    public int minEatingSpeed(int[] piles, int H) {
        int si = 1, ei = 1000000;
        // Arrays.sort(piles);
        while(si < ei){
            int eatingSpeed = (si + ei) >> 1;
            if(isPossibleToEat(piles,eatingSpeed, H))
               ei = eatingSpeed; 
            else si = eatingSpeed + 1;
        }
        
        return ei;
    }
    
    public boolean isPossibleToEat(int[] arr,int eatingSpeed,int H){
        int n = arr.length;
        int hours = 0;
        for(int i=n-1;i>=0;i--){
             // hour[i] += (int)Math.ceil((arr[i])/(eatingSpeed * 1.0));
            hours += (arr[i]-1)/eatingSpeed + 1;
            if(hours > H) return false;
        }

        return true;
    }

    
    //774
    public double minmaxGasDist(int[] arr, int K) {     
        double si = 0.0, ei = 1e8, mid = 0.0;
        while(ei - si > 1e-5){
            mid =(ei + si) / 2.0;
            if(isValidToPlaceGasStation(arr,mid,K)) ei = mid;
            else si = mid;
          
        }
        
        return mid;
    }
    
    public boolean isValidToPlaceGasStation(int[] arr,double dis,int k){
        int gasStationCount = 0;
        
        int n = arr.length;
        for(int i = 1; i < n; i++){
            double x = (arr[i] - arr[i-1]) / dis - (1e-6 + 1e-6)/dis;
            gasStationCount += (int)x;
            if(gasStationCount > k) return false;
        }
        
        return true;
    }

    //Leetcode 33
    public int search(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length-1;
        
        while(lo <= hi){
            int mid = (lo + hi ) >> 1;
            if(arr[mid] == target) return mid;
            else if(arr[lo] <= arr[mid]){
                if(arr[lo] <= target && target < arr[mid]) hi = mid - 1;
                else lo = mid + 1;
            }else{
                if(arr[mid] < target && target <= arr[hi]) lo = mid + 1;
                else hi = mid - 1;
            }
        }
        
        return -1;
    }
 
    // Leetcode 81
    public boolean search(int[] arr, int data) {
        int lo = 0;
        int hi = arr.length-1;
        
        while(lo <= hi){
            int mid = (lo + hi) >> 1;
            
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

    //Leetcode 786
    
    public int[] kthSmallestPrimeFraction(int[] A, int k) {
        int n = A.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            return A[a[0]]*A[b[1]] - A[b[0]]*A[a[1]];
        });

        for(int i=0;i<n;i++){
            pq.add(new int[]{0,i});
        }

        while(--k>0){
            int[] a = pq.remove();
            a[0]++;
            if(a[0] < a[1]) pq.add(a);
        }
        
        int[] a = pq.remove();
        a[0] = A[a[0]];
        a[1] = A[a[1]];
        
        return a;
    }
// Leetcode 4
double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int m = nums2.length;
    if(n > m)
        return findMedianSortedArrays(nums2,nums1);
    
    int omid = (n + m + 1) >> 1;
    int si = 0;
    int ei = n;
    while(si <= ei){
        int sMid = (si + ei) >> 1;
        int lMid = omid - sMid;

        int sl = (sMid == 0) ? -(int)1e8 : nums1[sMid - 1];
        int sr = (sMid == n) ? (int)1e8 : nums1[sMid];

        int ll = (lMid == 0) ? -(int)1e8 : nums2[lMid - 1];
        int lr = (lMid == m) ? (int)1e8 : nums2[lMid];

        if(sl > lr)
            ei = sMid - 1;
        else if(ll > sr)
            si = sMid + 1;
        else{
            int boundaryOfLeft = Math.max(sl,ll);
            int boundaryOfright = Math.min(sr,lr);

            if((n + m) % 2 != 0) return boundaryOfLeft;
            else return (boundaryOfLeft + boundaryOfright) / 2.0;
        }
    }

    return 0.0;
}






}