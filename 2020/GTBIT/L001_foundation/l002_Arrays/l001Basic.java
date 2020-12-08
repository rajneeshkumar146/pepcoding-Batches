import java.util.Scanner;
import java.util.Arrays;

public class l001Basic{
    public static Scanner scn = new Scanner(System.in);
    
    public static void test1(){
        int n = scn.nextInt();
        int[] arr = new int[n];

        for(int i=0;i<arr.length;i++){
            arr[i] = scn.nextInt();
        }
    
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }

    public static void test2(){
        int[] arr  = new int[10];
        Arrays.fill(arr,88);
        for(int i=0;i<arr.length;i++) System.out.print(arr[i] + " ");
    }

    // Basic Question.============================================================

    public static int maximumEle(int[] arr){
        int maxEle = (int)-1e8;
        for(int i=0;i<arr.length;i++){
            if(arr[i] > maxEle) maxEle = arr[i];
        }
    
        return maxEle;
    }
    
    public static int minimumEle(int[] arr){
        int minEle = (int)1e8;
        for(int i=0;i<arr.length;i++){
            if(arr[i] < minEle) minEle = arr[i];
        }
    
        return minEle;
    }
    
    public static int findEle(int[] arr,int data){
        int idx = -1;
        for(int i=0;i<arr.length;i++){
            if(arr[i] == data){
                idx = i;
                break;
            }
        }
    
        return idx;
    }
    
    public static int firstIndex(int[] arr,int data){
        int idx = -1;
        for(int i=0;i<arr.length;i++){
            if(arr[i] == data){
                idx = i;
                break;
            }
        }
    
        return idx;
    }
    
    
    public static int lastIndex(int[] arr,int data){
        int idx = -1;
        for(int i = arr.length - 1;i >= 0; i--){
            if(arr[i] == data){
                idx = i;
                break;
            }
        }
    
        return idx;
    }

    public static int spanOfArray(int[] arr){
        int minEle = (int)1e8;
        int maxEle = -(int)1e8;

        for(int i=0;i<arr.length;i++){
            if(arr[i] > maxEle) maxEle = arr[i];
            if(arr[i] < minEle) minele = arr[i];
        }

        return maxEle - minEle;
    }

    
    public static void reverse(int[] arr){
        int i = 0, j = arr.length - 1;
        while(i < j){
          int temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
        
          i++;
          j--;
       }
    }

    public static int[] inverse(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        for(int i=0;i<n;i++){
            ans[arr[i]] = i;
        }

        // for(int ele : ans){
        //     System.out.println(ele + " ");
        // }

        return ans;
    }

    
    public static void printAllSubArrays(int[] arr){
        int n = arr.length;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                for(int k=i;k<=j;k++){
                    System.out.print(arr[k]+ "\t");
                }
                System.out.println();
            }   
        }
    }

    // array should be in sorted order 
    public static int BinarySearch(int[] arr,int data){
        int si = 0, ei = arr.length - 1;
        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] == data)
                return mid;
            else if(data < arr[mid]){
                ei = mid - 1;
            }else si = mid + 1;
        }

        return -1;
    }
    
    public static void main(String[] args){
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++) arr[i] = scn.nextInt();

        int data = scn.nextInt();
        System.out.println(lastIndex(arr,data));
    }


}