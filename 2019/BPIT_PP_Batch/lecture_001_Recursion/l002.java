public class l002{

    public static void main(String[] args){
        solve();
    }

    public static void solve(){
        int[] coins={2,3,5,7};
        int tar=10;

        // System.out.println(coinChangeInfnitePermuatation(coins,0,tar,""));
        // System.out.println(coinChangeInfniteCombination(coins,0,tar,""));
        // System.out.println(coinChangeSingleCombination(coins,0,tar,""));
        // System.out.println(coinChangeSinglePermuatation(coins,0,tar,""));
         
        // System.out.println(coinChangeInfnitePermuatation_subseq(coins,0,tar,""));
        // System.out.println(coinChangeInfniteCombination_subseq(coins,0,tar,""));
        // System.out.println(coinChangeSingleCombination_subseq(coins,0,tar,""));
        // System.out.println(coinChangeSinglePermuatation_subseq(coins,0,tar,""));
         
        // boolean[] box=new boolean[6];
        // int tnq=3;
        // System.out.println(oneDQueenCombination(box,0,tnq,0,""));
        // System.out.println(oneDQueenPermutation(box,0,tnq,0,""));

        boolean[][] box=new boolean[4][4];
        int tnq = 4;
        // System.out.println(twoDQueenCombination(box,0,tnq,""));
        // System.out.println(twoDQueenPermutation(box,0,tnq,""));
        System.out.println(twoDNqueenFloorWise(box,0,tnq,""));
    }

    public static int coinChangeInfnitePermuatation(int[] arr,int idx,int tar,String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(tar-arr[i] >= 0)
              count += coinChangeInfnitePermuatation(arr,0,tar-arr[i],ans + arr[i]);
        }
        return count;
    }

    public static int coinChangeInfniteCombination(int[] arr,int idx,int tar,String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(tar-arr[i] >= 0)
              count += coinChangeInfniteCombination(arr,i,tar-arr[i],ans + arr[i]);
        }
        return count;
    }

    
    public static int coinChangeSingleCombination(int[] arr,int idx,int tar,String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(tar-arr[i] >= 0)
              count += coinChangeSingleCombination(arr,i+1,tar-arr[i],ans + arr[i]);
        }
        return count;
    }
    
    public static int coinChangeSinglePermuatation(int[] arr,int idx,int tar,String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;

        for(int i=idx;i<arr.length;i++){
            if(arr[i] > 0 && tar-arr[i] >= 0){
                int temp = arr[i];
                arr[i]=-arr[i];
                count += coinChangeSinglePermuatation(arr,0,tar-temp,ans + temp);
                arr[i]=-arr[i];
            }
        }
        return count;
    }

    public static int coinChangeSingleCombination_subseq(int[] arr,int idx,int tar,String ans){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        
        int count=0;
        if(tar-arr[idx]>=0)
           count+=coinChangeSingleCombination_subseq(arr,idx+1,tar-arr[idx],ans+arr[idx]);

        count+=coinChangeSingleCombination_subseq(arr,idx+1,tar,ans);
        
        return count;
    }

    public static int coinChangeInfniteCombination_subseq(int[] arr,int idx,int tar,String ans){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        
        int count=0;
        if(tar-arr[idx]>=0)
           count+=coinChangeInfniteCombination_subseq(arr,idx,tar-arr[idx],ans+arr[idx]);

        count+=coinChangeInfniteCombination_subseq(arr,idx+1,tar,ans);
        
        return count;
    }

    public static int coinChangeInfnitePermuatation_subseq(int[] arr,int idx,int tar,String ans){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        
        int count=0;
        if(tar-arr[idx]>=0)
           count+=coinChangeInfnitePermuatation_subseq(arr,0,tar-arr[idx],ans+arr[idx]);

        count+=coinChangeInfnitePermuatation_subseq(arr,idx+1,tar,ans);
        
        return count;
    }

    public static int coinChangeSinglePermuatation_subseq(int[] arr,int idx,int tar,String ans){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        
        int count=0;
        if(arr[idx] > 0 && tar-arr[idx] >= 0){
            int temp = arr[idx];
            arr[idx]=-arr[idx];
            count += coinChangeSinglePermuatation_subseq(arr,0,tar-temp,ans + temp);
            arr[idx]=-arr[idx];
        }

        count+=coinChangeSinglePermuatation_subseq(arr,idx+1,tar,ans);
        
        return count;
    }

    public static int oneDQueenCombination(boolean[] box,int idx,int tnq,int qpsf,String ans){
        if(qpsf==tnq){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=idx;i<box.length;i++){
            count+=oneDQueenCombination(box,i+1,tnq,qpsf+1,ans + "b" + i + "q" + qpsf + " ");
        }

        return count;
    }

    public static int oneDQueenPermutation(boolean[] box,int idx,int tnq,int qpsf,String ans){
        if(qpsf==tnq){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=idx;i<box.length;i++){
            if(!box[i]){
                box[i] = true;
                count+=oneDQueenPermutation(box,0,tnq,qpsf+1,ans + "b" + i + "q" + qpsf + " ");
                box[i] = false;
            }
        }

        return count;
    }

    public static int twoDQueenCombination(boolean[][] box,int idx,int tnq,String ans){
        if(tnq==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        int n=box.length;
        int m=box[0].length;

        for(int i=idx;i<n*m;i++){
            int r = i / m;
            int c = i % m; 
            count+=twoDQueenCombination(box,i+1,tnq-1,ans + "(" + r + ", " + c + ") ");
        }

        return count;
    }

    public static int twoDQueenPermutation(boolean[][] box,int idx,int tnq,String ans){
        if(tnq==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        int n=box.length;
        int m=box[0].length;
        for(int i=idx;i<n*m;i++){
            int r = i / m;
            int c = i % m; 
            if(!box[r][c]){
                box[r][c] = true;
                count+=twoDQueenPermutation(box,0,tnq-1,ans + "(" + r + ", " + c + ") ");
                box[r][c] = false;
            }
        }

        return count;
    }

    public static int twoDNqueenFloorWise(boolean[][] box,int row,int tnq,String ans){
        if(tnq==0 || row==box.length){
            if(tnq==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int m = box[0].length;
        int count = 0; 
        for(int i = 0; i < m; i++){
            count+=twoDNqueenFloorWise(box,row + 1, tnq - 1, ans + "(" + row + ", " + i + ") " );
        }
        
         return count;
    }
}