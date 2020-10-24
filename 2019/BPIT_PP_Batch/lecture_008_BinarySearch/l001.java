public class l001{

    public static int BS01(int[] arr,int ele){
        int si = 0,ei = arr.length-1;
        while(si <= ei){
            int mid = (si + ei) >> 1;
            
            if(arr[mid] == ele) return mid;
            else if(arr[mid] < ele) si = mid + 1;
            else ei = mid - 1;
        }

        return -1;
    }

    public static int firstOcc(int[] arr,int ele){
        int si = 0,ei = arr.length-1;
        
        while(si <= ei){
            int mid = (si + ei) >> 1;
            
            if(arr[mid] == ele){
                if(mid - 1 >= 0 && arr[mid - 1] == ele) ei = mid - 1;
                else return mid;
            }
            else if(arr[mid] < ele) si = mid + 1;
            else ei = mid - 1;
        }

        return -1;
    }

    public static int lastOcc(int[] arr,int ele){
        int si = 0,ei = arr.length-1;
        
        while(si <= ei){
            int mid = (si + ei) >> 1;
            
            if(arr[mid] == ele){
                if(mid + 1 < arr.length && arr[mid + 1] == ele) si = mid + 1;
                else return mid;
            }
            else if(arr[mid] < ele) si = mid + 1;
            else ei = mid - 1;
        }

        return -1;
    }

    public static int nearestElement(int[] arr,int ele){
        int si = 0,ei = arr.length-1;
        while(si <= ei){
            int mid = (si + ei) >> 1;
            
            if(arr[mid] == ele) return mid;
            else if(arr[mid] < ele) si = mid + 1;
            else ei = mid - 1;
        }

        if(j < 0) return i;
        else if(i >= arr.length) return j;
        else (ele - arr[j] < arr[i] - ele) ? j : i; 
    }

    public static void binarySearchExpectedPosition(int[] arr,int ele){
        int si = 0, ei = arr.length;
        while(si < ei){
            int mid = (si + ei) >> 1;

            if(arr[mid] < ele) si = mid + 1;
            else ei = mid;
        }

        return ei;
    }

    public static void main(String[] args){
        int[] arr = {10,10,10,20,20,20,30,30,30,30,30,30,30,30,32,33,33,34,39,45};
        System.out.println(BS01(arr,30));
        System.out.println(firstOcc(arr,30));
        System.out.println(lastOcc(arr,30));

    }

}