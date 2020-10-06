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

    public static void solve(){
        mcm();
        // minMaxValue();
    }

    public static void main(String[] args){
        solve();
    }
}