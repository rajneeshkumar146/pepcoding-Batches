public class l002{
     public static void main(String[] args){
        coinChange();
     }

     public static void coinChange(){
         int[] arr={2,3,5,7};
         int tar=10;


         System.out.println(coinChangeCombination(arr,0,tar,""));

     }


    public static int coinChangePermutation_INF(int[] arr,int lidx, int tar, String ans){
        if(lidx==arr.length || tar==0){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
    
         int count=0;
         if(tar-arr[lidx]>=0)
             count+=coinChangePermutation_INF(arr,lidx+1,tar-arr[lidx],ans+ arr[lidx]+" ");
         count+=coinChangePermutation_INF(arr,lidx+1,tar,ans);
         
         return count;
    }

    
    public static int coinChangePermutation(int[] arr, int tar, String ans){
        if(lidx==arr.length || tar==0){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
    
         int count=0;
        if (arr[i] > 0 && tar - arr[i] >= 0 ){
            int temp = arr[i];
            arr[i] = -arr[i];
            count+=coinChangePermutation_INF(arr,lidx+1,tar-temp,ans + temp+" ");
            arr[i] = -arr[i];
        }
            count+=coinChangePermutation_INF(arr,lidx+1,tar,ans);
         
         return count;
    }

    
    public static int coinChangeCombination_INF(int[] arr, int lidx, int tar, String ans){
    if(lidx==arr.length || tar==0){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }
        return 0;
    }

     int count=0;
     if(tar-arr[lidx]>=0)
         count+=coinChangeCombination_INF(arr,lidx+1,tar-arr[lidx],ans+ arr[lidx]+" ");
     count+=coinChangeCombination_INF(arr,lidx+1,tar,ans);
     
     return count;
    }


    public static int coinChangeCombination(int[] arr, int lidx, int tar, String ans){
       if(lidx==arr.length || tar==0){
           if(tar==0){
               System.out.println(ans);
               return 1;
           }
           return 0;
       }

        int count=0;
        if(tar-arr[lidx]>=0)
            count+=coinChangeCombination(arr,lidx+1,tar-arr[lidx],ans+ arr[lidx]+" ");
        count+=coinChangeCombination(arr,lidx+1,tar,ans);
        
        return count;
    }





}