import java.util.ArrayList;
import java.util.Scanner;

public class l002_array{

    public static Scanner scn=new Scanner(System.in);

    public static void main(String[] args){
    //   ArrayList<Integer> arr=new ArrayList<>();
    int[] arr={10,16,100,1,-19,13,-12,5,100,1,-19,100,16,15,14,13};
    // System.out.println(lastIndex(arr,0,100));
    int[] ans=allIdx(arr,0,100,0);
    
    for(int i: ans)
    System.out.print(i+ " ");

    }

    public static void input(ArrayList<Integer> arr){
        int n=scn.nextInt();
        for(int i=0;i<n;i++){
            int val=scn.nextInt();
            arr.add(val);
        }
    }

    public static int minimum(ArrayList<Integer> arr,int idx){
        if(arr.size()==idx) return Integer.MAX_VALUE;

       int res=minimum(arr,idx+1);
       return Math.min(res,arr.get(idx));
    }

    public static int lastIndex(int[] arr,int idx,int data){
        if(arr.length==idx) return -1;

        int recAns=lastIndex(arr,idx+1,data);
        if(recAns!=-1){
            return recAns;
        }else{
            return arr[idx]==data?idx:-1;
        }

    }

    public static int[] allIdx(int[] arr,int idx,int data,int count){
        if(idx==arr.length){
            int[] baseArray=new int[count];
            return baseArray;
        }

          if(arr[idx]==data){
              count++;
          }
         
         int[] recAns=allIdx(arr,idx+1,data,count);
          
          if(arr[idx]==data){
            recAns[count-1]=idx;
          }
         return recAns;
    }
}