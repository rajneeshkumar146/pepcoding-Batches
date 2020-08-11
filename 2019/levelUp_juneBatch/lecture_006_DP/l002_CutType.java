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

    public static int maxCoins(int[] nums,int si,int ei,int[][] dp) {
        if(dp[si][ei]!=0) return dp[si][ei];

        int lval = si == 0 ? 1 : nums[si-1];
        int rval = ei == nums.length -1 ? 1 : nums[ei+1];

        int maxAns = 0;
        for(int cut = si;cut<=ei;cut++){
            int leftRes = cut == si ? 0 : maxCoins(nums,si,cut - 1,dp);
            int rightRes = cut == ei ? 0 : maxCoins(nums,cut + 1, ei, dp); 

            int myAns = leftRes + lval * nums[cut] * rval + rightRes;
            maxAns = Math.max(maxAns, myAns);
        }

        return dp[si][ei] = maxAns;
    }

    public static int maxCoinsDP(int[] nums,int si,int ei,int[][] dp) {
        
        for(int gap = 1; gap < nums.length;gap++){
            for(si=0,ei=gap;ei<nums.length;si++,ei++){
                int lval = si == 0 ? 1 : nums[si-1];
                int rval = ei == nums.length - 1 ? 1 : nums[ei+1];

                int maxAns = 0;
                for(int cut = si;cut<=ei;cut++){
                    int leftRes = cut == si ? 0 : dp[si][cut-1];//maxCoins(nums,si,cut - 1,dp);
                    int rightRes = cut == ei ? 0 : dp[cut+1][ei];//maxCoins(nums,cut + 1, ei, dp); 

                    int myAns = leftRes + lval * nums[cut] * rval + rightRes;
                    maxAns = Math.max(maxAns, myAns);
                }

            dp[si][ei] = maxAns;
            }
        }

        return dp[0][nums.length-1];
        
    }

    public static int evaluateExpression(int a,int b,char oper){
        if(oper=='+') return a+b;
        else return a*b;

    }

    public static class pair{
        int min=(int)1e8;
        int max= (int) -1e8;
       
        String minStr="";
        String maxStr="";
        pair(int min,int max,String minStr,String maxStr){
            this.min = min;
            this.max = max;

            this.minStr = minStr;
            this.maxStr = maxStr;
        }

        pair(){
        }

        @Override
        public String toString(){
           return "(" + min + ", " + max  + ")";
        }
    }

    public static pair minMaxEval(String str,int si,int ei,pair[][] dp){
        if(si==ei){
            char ch = str.charAt(si);
            int val = ch-'0';
            return new pair(val,val, val+"",val+"");
        }

        if(dp[si][ei] != null) return dp[si][ei];
        
        pair maxAns = new pair();
        for(int cut = si + 1;cut < ei; cut+=2){
            pair leftRes = minMaxEval(str, si, cut-1, dp);
            pair rightRes = minMaxEval(str, cut + 1, ei, dp); 

            int minVal = evaluateExpression(leftRes.min,rightRes.min,str.charAt(cut));
            int maxVal = evaluateExpression(leftRes.max,rightRes.max,str.charAt(cut));
            
            if(minVal < maxAns.min){
                maxAns.min=minVal;
                maxAns.minStr = "("+leftRes.minStr + str.charAt(cut) + rightRes.minStr + ")";
            }

            if(maxVal > maxAns.max){
                maxAns.max=maxVal;
                maxAns.maxStr = "("+leftRes.maxStr + str.charAt(cut) + rightRes.maxStr + ")";
            }
        }

        return dp[si][ei] = maxAns;

    }

    public static int minPlaindromicCut(String str,int si,int ei,int[][] dp,boolean[][] palindromicSubstring){
        if(palindromicSubstring[si][ei]) return 0;
        if(dp[si][ei] != -1) return dp[si][ei];
        int minCut=(int)1e8;
        
        for(int cut = si; cut < ei; cut++){
            int leftRes = minPlaindromicCut(str,si,cut,dp,palindromicSubstring);
            int rightRes = minPlaindromicCut(str,cut+1,ei,dp,palindromicSubstring);
            
            int myAns= leftRes + 1 + rightRes;
            minCut = Math.min(minCut, myAns);
        }

        return dp[si][ei]=minCut;
    }

    public static int minPlaindromicCut_bestMethod(String str,int si,int ei,int[] dp,boolean[][] palindromicSubstring){
        if(palindromicSubstring[si][ei]) return 0;
        if(dp[si] != -1) return dp[si];
        
        int minCut=(int)1e8;
        for(int cut = si; cut < ei; cut++){
            if(palindromicSubstring[si][cut]){
              minCut = Math.min(minCut,minPlaindromicCut_bestMethod(str,cut+1,ei,dp,palindromicSubstring)+1);
            }            
        }

        return dp[si]=minCut;
    }

    public static int minCut(String str) {
		int n = str.length();
		int[][] dp=new int[n][n];
		boolean[][] isPalindrome=new boolean[n][n];

		for(int[] d: dp) Arrays.fill(d,-1);

		for (int gap = 0; gap < n; gap++) {
			for (int si = 0, ei = gap; ei < n; si++, ei++) {
				if (gap == 0) isPalindrome[si][ei] = true;
				else if (str.charAt(si) == str.charAt(ei) && gap == 1) isPalindrome[si][ei] = true;
				else isPalindrome[si][ei] = str.charAt(si) == str.charAt(ei) && isPalindrome[si + 1][ei - 1];
			}
		}

		return minPlaindromicCut(str,0,n-1,dp,isPalindrome);
    }

    public static int minTriangulation(int[] arr,int si,int ei,int[][] dp){
        if(ei - si < 2) return 0;
        if(dp[si][ei]!=0) return dp[si][ei];

        int minAns = (int)1e8;
        for(int cut = si + 1; cut < ei; cut++){
            int leftAns = minTriangulation(arr,si,cut,dp);
            int rightAns = minTriangulation(arr,cut,ei,dp);

            int myAns = leftAns + arr[si]*arr[cut]*arr[ei]  + rightAns;
            minAns=Math.min(minAns,myAns); 
        }

        return dp[si][ei] = minAns;
    }

    
    public int minScoreTriangulation(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        return minTriangulation(A,0,n-1,dp); 
    }
    

    public static void minMaxEval(){
        String str="1+2*3+4*5";
        int n=str.length();
        pair[][] dp=new pair[n][n];

        pair ans=minMaxEval(str,0,n-1,dp);
        System.out.println(ans.minStr + " -> "+ans.min);
        System.out.println(ans.maxStr + " -> "+ans.max);
        

        // for(pair[] d : dp){
        //     for(pair e : d){
        //         System.out.print(e + " ");
        //     }
        //     System.out.println();
        // }
    }

    public static int maxCoins(int[] nums) {
        int n=nums.length; if(n==0) return 0;
        int[][] dp =new int[n][n];
        
        return maxCoins(nums,0,n-1,dp);   
    }

    public static void OBST(){
        int[] keys={10,12,20};
        int[] freq={34, 8, 50};
    }

    public static void main(String[] args){
        // MCM();
        minMaxEval();
       
    }






}