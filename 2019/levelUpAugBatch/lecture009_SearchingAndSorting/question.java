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
}