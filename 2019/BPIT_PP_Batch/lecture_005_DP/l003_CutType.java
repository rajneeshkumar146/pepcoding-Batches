import java.util.Arrays;
public class l003_CutType{

    public static void print(int[] arr){
        for(int ele: arr)
        System.out.print(ele+" ");

        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] ar: arr) print(ar);
        System.out.println();
    }

    public static int mcm_rec(int[] arr,int si,int ei,int[][] dp){
        if(si + 1 == ei){
            return dp[si][ei] = 0;
        }

        if(dp[si][ei]!=0) return dp[si][ei];

        int myAns = (int)1e8;
        for(int cut = si + 1;cut<ei;cut++){
            int leftTree = mcm_rec(arr,si,cut,dp);
            int rightTree = mcm_rec(arr,cut,ei,dp);

            int myCost = leftTree +  arr[si] * arr[cut] * arr[ei] + rightTree;
            
            myAns = Math.min(myAns, myCost);
        }

        return dp[si][ei] = myAns;
    }

    public static int mcm_DP(int[] arr,int SI,int EI,int[][] dp){
        int n = arr.length;
        for(int gap = 1; gap < n;gap++){
            for(int si = 0, ei = gap; ei < n; si++,ei++){
                if(si + 1 == ei){
                    dp[si][ei] = 0;
                    continue;
                }
        
                int myAns = (int)1e8;
                for(int cut = si + 1;cut<ei;cut++){
                    int leftTree = dp[si][cut];
                    int rightTree = dp[cut][ei];
        
                    int myCost = leftTree +  arr[si] * arr[cut] * arr[ei] + rightTree;
                    
                    myAns = Math.min(myAns, myCost);
                }
        
                dp[si][ei] = myAns;
            }
        }

        return dp[SI][EI];
    }

    public static int mcm_DPString(int[] arr,int SI,int EI,int[][] dp){
        int n = arr.length;
        String[][] sdp = new String[n][n];

        for(int gap = 1; gap < n;gap++){
            for(int si = 0, ei = gap; ei < n; si++,ei++){
                if(si + 1 == ei){
                    String s = ""+(char)(si + 'A');
                    sdp[si][ei] = s;
                    dp[si][ei] = 0;
                    continue;
                }
        
                int myAns = (int)1e8;
                String s = "";
                for(int cut = si + 1;cut<ei;cut++){
                    int leftTree = dp[si][cut];
                    int rightTree = dp[cut][ei];
        
                    int myCost = leftTree +  arr[si] * arr[cut] * arr[ei] + rightTree;
                    
                    if(myCost < myAns){
                        myAns = myCost;
                        s = "("  + sdp[si][cut] + sdp[cut][ei] + ")";
                    }
                }
        
                dp[si][ei] = myAns;
                sdp[si][ei] = s;
            }
        }

        System.out.println(sdp[SI][EI]);
        return dp[SI][EI];
    }


    public static void mcm(){
        int[] arr = {4, 2, 3, 1, 3};
        int n = arr.length;

        int[][] dp = new int[n][n];
        System.out.println(mcm_DPString(arr,0,n-1,dp));

        print2D(dp);
    }

    //https://www.geeksforgeeks.org/minimum-maximum-values-expression/

    public static class minMaxPair{
        int minVal = (int) 1e8;
        int maxVal = 0;

        minMaxPair(int minVal,int maxVal){
            this.minVal = minVal;
            this.maxVal = maxVal;
        }

        minMaxPair(){

        }

        public String toString(){
            return "(" + this.minVal + ", " + this.maxVal +  ")";
        }
    }

    
    public static int evaluate(char ch,int v1, int v2){
        if(ch == '+') return v1 + v2;
        else if(ch == '+') return v1 - v2;    
        else return v1 * v2;
    }

    public static minMaxPair minMaxValue(String str,int si,int ei,minMaxPair[][] dp){
        if(si == ei){
            int val = str.charAt(si) - '0';
            return dp[si][ei] = new minMaxPair(val,val);
        }

        if(dp[si][ei] != null) return dp[si][ei];
        
        
        minMaxPair myAns = new minMaxPair();

        for(int cut = si + 1; cut < ei; cut+=2){
            minMaxPair leftTree = minMaxValue(str,si , cut - 1,dp);
            minMaxPair rightTree = minMaxValue(str,cut + 1, ei,dp);

            
            char ch = str.charAt(cut);
            myAns.minVal = Math.min(myAns.minVal,evaluate(ch,leftTree.minVal,rightTree.minVal));
            myAns.maxVal = Math.max(myAns.maxVal,evaluate(ch,leftTree.maxVal,rightTree.maxVal));
        }

        return dp[si][ei] = myAns;
    }

    public static minMaxPair minMaxValue_DP(String str,int SI,int EI,minMaxPair[][] dp){
        int n = str.length();
        for(int gap = 0 ; gap < n; gap++){
            for(int si = 0, ei = gap; ei < n ; si++,ei++){
                if(si == ei){
                    int val = str.charAt(si) - '0';
                    dp[si][ei] = new minMaxPair(val,val);
                    continue;
                }
                
                minMaxPair myAns = new minMaxPair();
        
                for(int cut = si + 1; cut < ei; cut+=2){
                    minMaxPair leftTree = dp[si][cut-1];//minMaxValue(str,si , cut - 1,dp);
                    minMaxPair rightTree = dp[cut+1][ei];//minMaxValue(str,cut + 1, ei,dp);
        
                    
                    char ch = str.charAt(cut);
                    myAns.minVal = Math.min(myAns.minVal,evaluate(ch,leftTree.minVal,rightTree.minVal));
                    myAns.maxVal = Math.max(myAns.maxVal,evaluate(ch,leftTree.maxVal,rightTree.maxVal));
                }
        
                dp[si][ei] = myAns;
            }
        }

        return dp[SI][EI];
    }

    public static minMaxPair evalCombination(char operator,minMaxPair p1,minMaxPair p2){

        int a = evaluate(operator,p1.minVal,p2.minVal);
        int b = evaluate(operator,p1.minVal,p2.maxVal);
        int c = evaluate(operator,p1.maxVal,p2.minVal);
        int d = evaluate(operator,p1.maxVal,p2.maxVal);

        minMaxPair p = new minMaxPair();
        p.minVal = Math.min(Math.min(a,b),Math.min(c,d));
        p.maxVal = Math.max(Math.max(a,b),Math.max(c,d));

        return p;
    }

    public static minMaxPair minMaxValue_02(int[] numArr,char[] chArr,int si,int ei,minMaxPair[][] dp){
        if(si == ei){
            int val = numArr[si];
            return dp[si][ei] = new minMaxPair(val,val);
        }

        if(dp[si][ei] != null) return dp[si][ei];
        
        minMaxPair myAns = new minMaxPair();

        for(int cut = si; cut < ei; cut++){
            minMaxPair leftTree = minMaxValue_02(numArr,chArr,si , cut,dp);
            minMaxPair rightTree = minMaxValue_02(numArr,chArr,cut + 1, ei,dp);

            char operator = chArr[cut];
            minMaxPair p = evalCombination(operator,leftTree,rightTree);

            myAns.minVal = Math.min(myAns.minVal, p.minVal);
            myAns.maxVal = Math.max(myAns.maxVal, p.maxVal);
        }

        return dp[si][ei] = myAns;        
    }



    public static void minMaxValue(){
        String str = "1+2*3+4*5+2*3+3+3+3*3*8*7";

        int n = str.length();
        minMaxPair[][] dp = new minMaxPair[n][n];
        minMaxPair ans = minMaxValue(str,0,n-1,dp);

        System.out.println(ans.minVal + " , " + ans.maxVal);

        for(minMaxPair[] d : dp){
            for(minMaxPair e: d){
                System.out.print(e);
            }
            System.out.println();
        }
    }

    // Leetcode 312
    public int maxCoins(int[] nums,int si,int ei ,int[][] dp) {
        if(dp[si][ei] != -1) return dp[si][ei];
        
        int liVal = (si - 1 == -1) ? 1 : nums[si - 1];
        int riVal = (ei + 1 == nums.length) ? 1 : nums[ei + 1];
        
        int myCost = 0;
        
        for(int cut = si;cut<=ei;cut++){
            int leftTree = (cut == si)?0:maxCoins(nums,si,cut-1,dp);
            int rightTree = (cut == ei)?0:maxCoins(nums,cut+1,ei,dp);
            
            myCost = Math.max(myCost, leftTree + liVal * nums[cut] * riVal + rightTree); 
        }
        
        return dp[si][ei] = myCost;
    }
    
    public int maxCoins(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        
        int[][] dp = new int[n][n];
        for(int[] d: dp)Arrays.fill(d,-1);
        
        return maxCoins(nums,0,n-1,dp);
        
    }

    public static int OBST(int[] freq,int si,int ei,int[][] dp,int[] prefixSum){
        if(dp[si][ei]!=0) return dp[si][ei];

        int myRes = (int) 1e8;
        // int sum = 0;
        for(int cut = si; cut<=ei;cut++){
            int leftTree = cut == si ? 0 : OBST(freq,si,cut-1,dp,prefixSum);
            int rightTree = cut == ei ? 0 : OBST(freq,cut + 1,ei,dp,prefixSum);

            int myAns = leftTree +  (prefixSum[ei] - (si == 0 ? 0 : prefixSum[si-1]))  + rightTree;  
            // int myAns = leftTree + rightTree;
            
            myRes = Math.min(myRes,myAns);
            
            // sum+=freq[cut];
        }

        return dp[si][ei] = myRes;
    }

    public static void OBST(){
        int[] freq = {34, 8, 50};
        int[] val = {10, 12, 20};

        int n = val.length;

        int[][] dp = new int[n][n];

        int[] prefixSum = new int[n];
        
        int prev = 0;
        for(int i = 0;i<n;i++){
            prefixSum[i] = prev + freq[i];
            prev = prefixSum[i];
        }

        System.out.println(OBST(freq,0,n-1,dp,prefixSum));
        print2D(dp);
    }

    // 1039
    public int minTriangulation(int[] A,int si,int ei,int[][] dp){
        if(ei - si < 2) return dp[ei][si] = 0;
        if(dp[ei][si] != -1 ) return dp[si][ei];

        int minAns = (int) 1e8;
        for(int cut = si + 1; cut < ei; cut++){
            int leftTree = minTriangulation(A,si,cut,dp);
            int rightTree = minTriangulation(A,cut,ei,dp);

            int myAns = leftTree + arr[si] * arr[cut] * arr[ei] + rightTree;
            minAns = Math.min(minAns,myAns);
        }

        return dp[si][ei] = minAns;
    }

    public int minScoreTriangulation(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        for(int[] d: dp) Arrays.fill(d,-1);
        return minTriangulation(A,0,n-1,dp); 
    }

       //Leetcode 132
       public static int minCut_01(String str,int si,int ei ,int[][] dp,boolean[][] isPalindrome){
        if(isPalindrome[si][ei]) return 0;

        if(dp[si][ei] != -1) return dp[si][ei];

        int minAns = (int) 1e8;
        for(int cut = si; cut < ei;cut++){
            int leftTree = minCut_01(str,si,cut,dp,isPalindrome);
            int rightTree = minCut_01(str,cut + 1,ei,dp,isPalindrome);

            minAns = Math.min(minAns,leftTree + 1 + rightTree);
        }

        return dp[si][ei] = minAns;
    }

    public static int minCut_02(String str,int si,int ei ,int[] dp,boolean[][] isPalindrome){
        if(isPalindrome[si][ei]) return dp[si] = 0;
        if(dp[si] != -1 ) return dp[si];

        int minAns = (int) 1e8;
        for(int cut = si; cut<=ei;cut++){
            if(isPalindrome[si][cut]){
                minAns = Math.min(minAns, minCut_02(str,cut+1,ei,dp,isPalindrome) + 1);
            }
        }

        return dp[si] = minAns;
    }
    
    public static int minCut_02DP(String str,int SI,int EI ,int[] dp,boolean[][] isPalindrome){
        for(int si = EI;si>=0;si--){
            if(isPalindrome[si][EI]){
                dp[si] = 0;
                continue;
            }

            int minAns = (int) 1e8;
            for(int cut = si; cut<=EI;cut++){
                if(isPalindrome[si][cut]){
                    minAns = Math.min(minAns, dp[cut+1] + 1);
                }
            }
    
            dp[si] = minAns;                
        }

        return dp[SI];
    }
    
    
    public static int minCut(String str) {
        int n = str.length();
        // int[][] dp = new int[n][n];
        // for(int[] d: dp) Arrays.fill(d,-1);

        boolean[][] isPalindrome = new boolean[n][n];
        for(int gap = 0 ; gap < n ;gap++){
            for(int i = 0, j = gap; j<n;i++,j++){
                if(gap == 0) isPalindrome[i][j] = true;
                else if(gap == 1) isPalindrome[i][j] = str.charAt(i) == str.charAt(j);
                else isPalindrome[i][j] = str.charAt(i) == str.charAt(j) && isPalindrome[i + 1][j - 1];
            }
        }


        // int ans = minCut_01(str,0,n-1,dp, isPalindrome);

        int[] dp = new int[n];
        Arrays.fill(dp,-1);
        int ans = minCut_02DP(str,0,n-1,dp, isPalindrome);
        
        // for(int ele : dp) System.out.print(ele + " ");
        return ans;
    }
    
    //Leetcode 45
    public int jump(int[] nums,int idx,int[] dp) {
        if(idx == nums.length - 1){
            return dp[idx] = 0;    
        }
        
        if(dp[idx] != -1) return dp[idx];
        
        if(nums[idx] == 0){
            return dp[idx] = (int)1e8;
        }
        
        int minSteps = (int) 1e8;
        for(int jump = 1; idx + jump < nums.length && jump <= nums[idx]; jump++){
            int recAns = jump(nums,idx + jump,dp);
            
            if(recAns != (int)1e8){
                minSteps = Math.min(minSteps, recAns + 1);
            }
        }
        
        return dp[idx] = minSteps;
    
    }
    
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp,-1);
        
        int ans = jump(nums,0,dp);
        
        return ans;
        
    }

    public int jump(int[] nums) {
        int n = nums.length;

        int maxEnding = 0;
        int maxPossibleJump = 0;
        int jump = 0;
        for(int i=0;i<n;i++){

            maxPossibleJump = Math.max(maxPossibleJump, i + nums[i]);
            if(i == maxEnding){
                maxEnding = maxPossibleJump;
                jump++;
            }
        }

        return jump;
    }

    
    static int mod = 1003;
    public static void evaluate (int[] left,int[] right,char ch , int[] ans){ 
        int tleft = (left[0] % mod + left[1] % mod) % mod;
        int tright = (right[0]% mod + right[1]% mod)% mod;
        
        int tTF = ((tleft % mod) * (tright% mod ))% mod;
        
        if(ch == '|'){
            
            ans[0] += (tTF % mod - (left[1]% mod) * (right[1])% mod + mod) % mod;
            ans[1] += ((left[1]% mod) * (right[1])% mod) % mod;
        
            
        }else if(ch == '&'){
            
            ans[0] += ((left[0]% mod) * (right[0])% mod) % mod;
            ans[1] += (tTF % mod - (left[0]% mod) * (right[0])% mod + mod) % mod;
        }else{
            
            ans[0] += ( ((left[0]% mod) * (right[1])% mod) % mod + ((left[1]% mod) * (right[0])% mod) % mod)
                      %mod;
            ans[1] += ( ((left[0]% mod) * (right[0])% mod) % mod + ((left[1]% mod) * (right[1])% mod) % mod)
                      %mod;           
        }
        
        ans[0] %= mod;
        ans[1] %= mod;
    }
    
    
    
    // 0 th - > true, 1st -> false.
    public static int[] booleanParenthesization(String str,int si,int ei,int[][][] dp){
        if(si == ei){
            int[] base = new int[2];
            base[0] = str.charAt(si) == 'T' ? 1 : 0;
            base[1] = str.charAt(si) == 'F' ? 1 : 0;
            
            return base;
        }
        
        if(dp[si][ei][0] != 0 || dp[si][ei][1] != 0) return dp[si][ei];
        
        for(int cut = si + 1; cut < ei; cut += 2){
            
            int[] left = booleanParenthesization(str,si,cut-1,dp);
            int[] right = booleanParenthesization(str,cut + 1,ei,dp);
            
            char ch = str.charAt(cut);
            evaluate(left,right,ch,dp[si][ei]);
    }
    
        return dp[si][ei];
    }

    // Leetcode 1278
    public int palindromePartition(String s,int k,int si,int ei,int[][] dp,int[][] pdp){
        if(k >= (ei-si+1)){
            return dp[k][ei] = (k == (ei-si+1))?0:(int)1e8;
        }
        
        if(k == 1 || si  == ei){
            return dp[k][ei] = ( si == ei ) ? 0 : pdp[0][ei];
        }
        
        if(dp[k][ei] != -1) return dp[k][ei];
        
        int ans = (int)1e8;
        for(int cut = si; cut < ei;cut++){
            int recAns = palindromePartition(s,k-1,si,cut,dp,pdp);
            
            if(recAns!=(int)1e8)
            ans = Math.min(ans,recAns + pdp[cut+1][ei]);
        }
        
        return dp[k][ei] =  ans;
    }
    
    
    public int palindromePartition(String s, int k) {
        int n = s.length();
        if(k==0 || k == n ) return 0;
        
        int[][] dp = new int[k+1][n+1];
        for(int[] d:dp) Arrays.fill(d,-1);
        int[][] pdp = new int[n][n];
        
        for(int gap = 1 ;gap <n;gap++){
            for(int i =0,j=gap;j<n;i++,j++){
                pdp[i][j] += pdp[i+1][j-1];
                if(s.charAt(i) != s.charAt(j)) pdp[i][j] += 1;
            }
        }
        
        
        int ans = palindromePartition(s,k,0,n-1,dp,pdp);
        return ans; 
    }


    // Leetcode 1216
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int gap = 0 ;gap <n;gap++){
            for(int i =0,j=gap;j<n;i++,j++){
                if(gap ==0) dp[i][j] = 1;
                else if(gap == 1 && s.charAt(i) == s.charAt(j)) dp[i][j] = 2;
                else if(s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i+1][j-1] + 2;
                else dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);    
            }
        }
        return n - dp[0][n-1] <= k;   
    }

    //688
    int dx[] = { -2, -1, 1, 2, -2, -1, 1, 2 }; 
    int dy[] = { -1, -2, -2, -1, 1, 2, 2, 1 }; 
    
    public double knightProbability(int N, int K, int r, int c,double[][][] dp ) {
        if(K == 0) dp[K][r][c] = 1;
        
        if(dp[K][r][c] != 0.0) return dp[K][r][c];
        
        double count = 0.0;
        for(int d = 0;d<8;d++){
            int x = r + dx[d];
            int y = c + dy[d];
            
            if(x>=0 && y>=0 && x<N && y<N){
                count += knightProbability(N,K-1,x,y,dp);
            }
        }
        
        return dp[K][r][c] = count/8.0;
    }
    
    
    public double knightProbability(int N, int K, int r, int c) {
        double[][][] dp = new double[K+1][N+1][N+1];
        return knightProbability(N,K,r,c,dp);
    }



    public static void solve(){
        // mcm();
        // minMaxValue();
        OBST();
    }

    public static void main(String[] args){
        solve();
    }
}