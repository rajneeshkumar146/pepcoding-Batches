public class l002_CutType{

    public static void display(int[] dp) {
        for (int ele : dp)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] ar : dp) {
            display(ar);
        }
        System.out.println();
    }


    public static int MCM_Rec(int[] arr,int si,int ei,int[][] dp){
        if(si+1 == ei) return dp[si][ei] = 0;
        if(dp[si][ei] != 0) return dp[si][ei]; 

        int myAns=(int)1e8;
        for(int cut=si+1;cut<ei;cut++){
            int leftRes=MCM_Rec(arr,si,cut,dp);
            int rightRes=MCM_Rec(arr,cut,ei,dp);
            
            int recRes = leftRes +  arr[si]*arr[cut]*arr[ei]  + rightRes;
            if(recRes<myAns) myAns = recRes; 
        }

        return dp[si][ei] = myAns;
    }

    public static int MCM_DP(int[] arr,int si,int ei,int[][] dp){

        for(int gap = 1; gap < arr.length;gap++){
            for(si=0,ei=gap;ei<arr.length;si++,ei++){
                if(si+1 == ei) {
                    dp[si][ei] = 0;
                    continue;
                }
                int myAns=(int)1e8;
                for(int cut=si+1;cut<ei;cut++){
                    int leftRes = dp[si][cut];//MCM_Rec(arr,si,cut,dp);
                    int rightRes = dp[cut][ei];//MCM_Rec(arr,cut,ei,dp);
            
                    int recRes = leftRes +  arr[si] * arr[cut] * arr[ei]  + rightRes;
                    if(recRes<myAns) myAns = recRes; 
                }

            dp[si][ei] = myAns;
            }
        }
    
        return dp[0][arr.length-1];
    }

    public static int MCM_Rec_02(int[] arr,int si,int ei,int[][] dp,String[][] sdp){
        if(si+1 == ei) {
            sdp[si][ei] = ((char)(si+'A')+"");
            return dp[si][ei] = 0;
        }
            
        if(dp[si][ei] != 0) return dp[si][ei]; 

        dp[si][ei]=(int)1e8;
        for(int cut=si+1;cut<ei;cut++){
            int leftRes=MCM_Rec_02(arr,si,cut,dp,sdp);
            int rightRes=MCM_Rec_02(arr,cut,ei,dp,sdp);
            
            int recRes = leftRes +  arr[si] * arr[cut] * arr[ei]  + rightRes;
            if(recRes < dp[si][ei]) {
                sdp[si][ei] = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                dp[si][ei] = recRes; 
            }
        }
        return dp[si][ei];
    }

    public static void MCM(){
        int[] arr={10, 20, 30, 40, 30};
        int n=arr.length;

        int[][] dp=new int[n][n];
        String[][] sdp=new String[n][n];
        
        // System.out.println(MCM_Rec(arr,0,n-1,dp));
        // System.out.println(MCM_DP(arr,0,n-1,dp));
        System.out.println(MCM_Rec_02(arr,0,n-1,dp,sdp));
        
        System.out.println(sdp[0][n-1]);
        display2D(dp);
        for(String[] s: sdp){
            for(String e:s) System.out.print(e+" ");
            System.out.println();
        }
    }

    //OBST.======================================================

    public static int summation(int si,int ei,int[] freq){
        int sum=0;
        while(si<=ei) sum+=freq[si++];
        return sum;
    }

    public static int OBST_Rec(int[] freq,int si,int ei,int[][] dp,int[] prefixSum){
        if(dp[si][ei] != 0) return dp[si][ei]; 

        int myAns=(int)1e8;
        for(int cut=si;cut<=ei;cut++){
            int leftRes = cut == si ? 0 : OBST_Rec(freq,si,cut,dp,prefixSum);
            int rightRes = cut == ei ? 0 : OBST_Rec(freq,cut,ei,dp,prefixSum);
            
            int recRes = leftRes +  (prefixSum[ei]-(si==0?0:prefixSum[si])) + rightRes;
            if(recRes<myAns) myAns = recRes; 
        }

        return dp[si][ei] = myAns;
    }

    public static void OBST(){
        int[] keys={10,12,20};
        int[] freq={34, 8, 50};

        


    }

    public static void main(String[] args){
        MCM();
    }






}