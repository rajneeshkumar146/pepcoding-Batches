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





}