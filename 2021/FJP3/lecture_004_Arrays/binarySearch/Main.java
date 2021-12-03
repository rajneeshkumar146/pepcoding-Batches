import java.util.*;

public class Main{
    public  static int binarySearch(int [] arr, int x){
        int li = 0;
        int ri = arr.length-1;

        while(li<=ri){
            int mid = (li+ri)/2;

            if(arr[mid] == x){
                return mid;
            }
            else if(arr[mid] > x){
                //left side me dhundho
                ri = mid-1;
            }
            else if(arr[mid] < x){
                //right me dhundho
                li = mid + 1;
            }
        }
        return -1;
        
    }
        

    public static void main(String [] args){
        //incre
        int [] arr = {4,6,8,10,12,14,16,18,20,22,24};
        int x = 20;
        // int x = 24;
        // int x = 11;
        int ans = binarySearch(arr,x);
        System.out.println(ans);

    }
}