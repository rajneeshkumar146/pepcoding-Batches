public class l002{
     public static void main(String[] args){
        // coinChange();
        queenProblem();
     }

     public static void coinChange(){
         int[] arr={2,3,5,7};
         int tar=10;


        //  System.out.println(coinChangePermutation_INF(arr,0,tar,""));
        //  System.out.println(coinChangePermutation(arr,0,tar,""));
        //  System.out.println(coinChangeCombination_INF(arr,0,tar,""));
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
             count+=coinChangePermutation_INF(arr,0,tar-arr[lidx],ans+ arr[lidx]+" ");
         count+=coinChangePermutation_INF(arr,lidx+1,tar,ans);
         
         return count;
    }

    
    public static int coinChangePermutation(int[] arr,int lidx, int tar, String ans){
        if(lidx==arr.length || tar==0){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
    
         int count=0;
        if (arr[lidx] > 0 && tar - arr[lidx] >= 0 ){
            int temp = arr[lidx];
            arr[lidx] = -arr[lidx];
            count+=coinChangePermutation(arr,0,tar-temp,ans + temp+" ");
            arr[lidx] = -arr[lidx];
        }
            count+=coinChangePermutation(arr,lidx+1,tar,ans);
         
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
         count+=coinChangeCombination_INF(arr,lidx,tar-arr[lidx],ans+ arr[lidx]+" ");
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

   //Queens.====================================================================

public static int queensCombination2D(boolean[][] rooms, int room, int tnq, String ans) // qpsf: queen place so far.
{
    if (tnq==0)
    {
        System.out.println(ans);
        return 1;
    }

    int count = 0;
    for (int r = room; r < rooms.length*rooms[0].length; r++){
        int x = r / rooms[0].length;
        int y = r % rooms[0].length;
        count += queensCombination2D(rooms, r + 1,tnq-1, ans + "(" + x + ", " + y + ") ");
    }
    return count;
}

public static int queensPermutation2D(boolean[][] rooms,int tnq, String ans) // qpsf: queen place so far.
{
    if (tnq==0)
    {
        System.out.println(ans);
        return 1;
    }

    int count = 0;
    for (int r = 0; r < rooms.length; r++){
        int x = r / rooms[0].length;
        int y = r % rooms[0].length;

        if (!rooms[x][y])
        {
            rooms[x][y] = true;
            count += queensPermutation2D(rooms,tnq-1,  ans + "(" + x + ", " + y + ") ");
            rooms[x][y] = false;
        }
    }
    return count;
}

public static void queenProblem()
{
    boolean[][] rooms=new boolean[4][4];
    int tnq = 4;

    System.out.println(queensCombination2D(rooms,0,tnq,""));
    // System.out.println(queensPermutation2D(rooms,tnq,""));
}






}