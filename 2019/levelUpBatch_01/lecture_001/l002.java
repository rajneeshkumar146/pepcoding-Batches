// import java.util.*;
import java.util.Scanner;
public class l002{
    public static Scanner scn=new Scanner(System.in);
    
    public static void main(String[] args){
      solve();
    }

    public static void solve(){
        // int[] arr=new int[scn.nextInt()];
        int[] arr={2,3,5,7};
        int tar=10;
        // System.out.println(coinChgCombi_01(arr,0,tar,""));
        // System.out.println(coinChgCombi_02(arr,0,tar,""));
        // System.out.println(coinChgCombi_03(arr,0,tar,""));


        boolean[] boxes=new boolean[6];
        System.out.println(QueenBoxCombi_01(boxes,0,3,0,""));
        


    }

    public static int coinChgCombi_01(int[] arr,int idx,int tar,String ans){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(tar-arr[i]>=0){
                count+=coinChgCombi_01(arr,i,tar-arr[i],ans+ arr[i]+ " ");
            }
        }
        return count;

    }

    public static int coinChgCombi_02(int[] arr,int idx,int tar,String ans){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(tar-arr[i]>=0){
                count+=coinChgCombi_02(arr,i+1,tar-arr[i],ans+ arr[i]+ " ");
            }
        }
        return count;
    }


    public static int coinChgCombi_03(int[] arr,int i,int tar,String ans){
        if(tar==0 || i==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count=0;
        count+=coinChgCombi_03(arr,i+1,tar,ans);
        if(tar-arr[i]>=0){
            count+=coinChgCombi_03(arr,i+1,tar-arr[i],ans+ arr[i]+ " ");
        }
    
        return count;
    }
    //lqpl=last queen place location
    public static int QueenBoxCombi_01(boolean[] boxes,int lqpl,int tnq,int qpsf,String ans){
     if(qpsf==tnq){
         System.out.println(ans);
         return 1;
     }

     int count=0;
     for(int i=lqpl;i<boxes.length;i++){
         count+=QueenBoxCombi_01(boxes,i+1,tnq,qpsf+1,ans+"b"+i+ "q"+qpsf);
     }

        return count;
    }
}