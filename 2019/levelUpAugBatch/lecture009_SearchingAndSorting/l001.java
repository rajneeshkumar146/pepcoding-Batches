public class l001{

    public static int binarySearch(int[] arr,int data){
        int si = 0, ei = arr.length - 1;
        while(si <= ei){
            int mid = (si + ei) / 2; 
            if(arr[mid] == data) return mid;
            else if(arr[mid] < data) si = mid + 1;
            else ei = mid - 1;
        }

        return -1;
    }

    public static int binarySearchFirstIndex(int[] arr,int data){
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

    public static int binarySearchLastIndex(int[] arr,int data){
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

    // ceil and floor 
    public static int binarySearchNearestIndex(int[] arr,int data){
        if(arr.length == 0) return -1;
        
        int n = arr.length;
        if(data <= arr[0] || data >= arr[n - 1]) return data <= arr[0] ? 0 : n - 1;

        int si = 0, ei = n - 1;
        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] == data) return mid;
            else if(arr[mid] < data) si = mid + 1;
            else ei = mid - 1;
        }

        // ei = floor, si = ceil.
        return ((data - arr[ei] < arr[si] - data) ? ei : si);
    }

    public static void main(String[] args){
    }

}