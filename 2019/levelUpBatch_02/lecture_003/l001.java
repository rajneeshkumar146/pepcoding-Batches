public class l001{

    public static void main(String[] args){
        int[] arr={2,3,5,7};
        int tar=10;
        System.out.println(coinCombiInfi(arr,0,tar,""));
        

    } 


    public static int coinCombiInfi(int[] arr,int idx,int tar,String ans){
        if(idx==arr.length || tar==0){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;

        }

        int count=0;
        if(tar-arr[idx]>=0)
            count+=coinCombiInfi(arr,0,tar-arr[idx],ans+arr[idx]);
        count+=coinCombiInfi(arr,idx+1,tar,ans);
        return count;
    }


}