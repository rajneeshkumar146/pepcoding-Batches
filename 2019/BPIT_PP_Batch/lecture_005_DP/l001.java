import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
public class l001{
    public static void print(int[] arr){
        for(int ele: arr)
        System.out.print(ele+" ");

        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] ar: arr) print(ar);
        System.out.println();
    }

    public static int fibo(int n,int[] dp){
        if(n<=1) return dp[n] = n;

        if(dp[n]!=0) return dp[n];
        
        int a = fibo(n-1,dp);
        int b = fibo(n-2,dp);
         
        return dp[n] = a+b;
    }

    public static int fibo_DP(int n,int[] dp){
        int N = n;
        for( n = 0;n <= N;n++){
            if(n<=1) 
            {
                dp[n] = n;
                continue;
            }

            int a = dp[n-1];//fibo(n-1,dp);
            int b = dp[n-2];//fibo(n-2,dp);
         
            dp[n] = a+b;
        }

        return dp[N];
    }

    public static int fibo_opti(int N){
        int a = 0;
        int b = 1;
        for(int n = 2;n<=N;n++){
            int sum = a+b;
            a=b;
            b=sum;
        }

        return b;
    }

    public static int mazePath_HVD(int sr,int sc,int er,int ec,int[][] dp){
        if(sr==er && sc==ec){
            return dp[sr][sc] = 1;
        }

        if(dp[sr][sc]!=0) return dp[sr][sc];
        int count=0;
        
        if(sc+1 <= ec) count+=mazePath_HVD(sr,sc+1,er,ec,dp);
        if(sr+1 <= er && sc+1 <= ec)count+=mazePath_HVD(sr+1,sc+1,er,ec,dp);
        if(sr+1 <= er)count+=mazePath_HVD(sr+1,sc,er,ec,dp);
        
        return dp[sr][sc] = count;
    }

    public static int mazePath_HVD_DP(int sr,int sc,int er,int ec,int[][] dp){
     
        for(sr=er;sr>=0;sr--){
            for(sc=ec;sc>=0;sc--){
                if(sr==er && sc==ec){
                    dp[sr][sc] = 1;
                    continue;
                }
        
                int count=0;
                
                if(sc+1 <= ec) count+=dp[sr][sc+1];//mazePath_HVD(sr,sc+1,er,ec,dp);
                if(sr+1 <= er && sc+1 <= ec)count+=dp[sr+1][sc+1];//mazePath_HVD(sr+1,sc+1,er,ec,dp);
                if(sr+1 <= er)count+=dp[sr+1][sc];//mazePath_HVD(sr+1,sc,er,ec,dp);
                
               dp[sr][sc] = count;
            }
        }
        return dp[0][0];
    }

    public static int mazePath_HVDJump_DP(int sr,int sc,int er,int ec,int[][] dp){
     
        for(sr=er;sr>=0;sr--){
            for(sc=ec;sc>=0;sc--){
                if(sr==er && sc==ec){
                    dp[sr][sc] = 1;
                    continue;
                }
        
                int count=0;
                
                for(int jump=1; sc+jump <= ec; jump++) count+=dp[sr][sc+jump];//mazePath_HVD(sr,sc+1,er,ec,dp);
                for(int jump=1; sr+jump <= er && sc+jump <= ec; jump++)count+=dp[sr+jump][sc+jump];//mazePath_HVD(sr+1,sc+1,er,ec,dp);
                for(int jump=1; sr+jump <= er; jump++)count+=dp[sr+jump][sc];//mazePath_HVD(sr+1,sc,er,ec,dp);
                
               dp[sr][sc] = count;
            }
        }
        return dp[0][0];
    }

    public static int boardPath(int si,int ei,int[] dp){
        if(si==ei){
            return dp[si] = 1;
        }

        if(dp[si]!=0) return dp[si];

        int count=0;
        for(int dice = 1; dice <= 6 && si + dice <= ei ; dice++){
            count+=boardPath(si+dice,ei,dp);
        }

        return dp[si] = count;
    }

    public static int boardPath_DP(int si,int ei,int[] dp){
        for(si=ei;si>=0;si--){
            if(si==ei){
                dp[si] = 1;
                continue;
            }
    
            int count=0;
            for(int dice = 1; dice <= 6 && si + dice <= ei ; dice++){
                count+=dp[si+dice]; //boardPath(si+dice,ei,dp);
            }

            dp[si] = count;
        }
        
        return dp[0];
    }

    public static int boardPath_Opti(int si,int ei,int[] dp){
        LinkedList<Integer> ll = new LinkedList<>();
        for(si=ei;si>=0;si--){
            if(si>=ei-1){
                ll.addFirst(1);
                continue;
            }

            if(ll.size()<=6) ll.addFirst(ll.getFirst()*2);
            else{
                int lval = ll.removeLast();
                ll.addFirst(ll.getFirst()*2 - lval);
            }
        }
        
        return ll.getFirst();
    }

    //----------------> Leetcode 70 similar to fibo.
    
    //leetcode 746

    public int minCostClimbingStairs(int[] cost, int n, int[] dp) {
        if(n<=1) return dp[n]=cost[n];
        if(dp[n]!=0) return dp[n];
        
        int ans = Math.min(minCostClimbingStairs(cost,n-1,dp),minCostClimbingStairs(cost,n-2,dp));

        return dp[n] = ans + (n != cost.length ? cost[n] : 0);
    }

    public int minCostClimbingStairs_DP(int[] cost, int n, int[] dp) {
        for(n=0;n<=cost.length;n++){
            if(n<=1){
                dp[n]=cost[n];
                continue;
            }

            int ans = Math.min(dp[n-1],dp[n-2]);
            dp[n] = ans + (n != cost.length ? cost[n] : 0);
        }
        
        return dp[cost.length];
    }

    public  int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        int ans = minCostClimbingStairs(cost,dp.length,dp);
        // ans = minCostClimbingStairs_DP(cost,dp.length,dp);
        return ans;
    }


    public static int boardPath_Moves(int si,int ei,int[] moves,int[] dp){
        Arrays.sort(moves);
        for(si=ei;si>=0;si--){
            if(si==ei){
                dp[si] = 1;
                continue;
            }
    
            int count=0;
            for(int i = 0; si + moves[i] <= ei ; i++){
                count+=dp[si + moves[i]]; //boardPath(si+dice,ei,dp);
            }

            dp[si] = count;
        }
        
        return dp[0];
    }

    //Leetcode 64    
    public static int minPathSum(int[][] grid,int sr,int sc,int[][] dp) {
        if(sr==grid.length-1 && sc==grid[0].length-1){
            return dp[sr][sc] = grid[sr][sc];
        }

        if(dp[sr][sc]!=0) return dp[sr][sc];

        int minCost = (int)1e8;
        if(sr + 1 < grid.length) minCost = Math.min(minCost,minPathSum(grid,sr+1,sc,dp));
        if(sc + 1 < grid[0].length) minCost = Math.min(minCost,minPathSum(grid,sr,sc+1,dp));
        
        return dp[sr][sc] = minCost + grid[sr][sc];
    }

    
    public static int minPathSum_DP(int[][] grid,int sr,int sc,int[][] dp) {
        
        for(sr=grid.length-1;sr>=0 ;sr--){
            for(sc=grid[0].length-1; sc>=0 ; sc--){
               if(sr==grid.length-1 && sc==grid[0].length-1){
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }
                int minCost = (int)1e8;
                if(sr + 1 < grid.length) minCost = Math.min(minCost,dp[sr+1][sc]);
                if(sc + 1 < grid[0].length) minCost = Math.min(minCost,dp[sr][sc+1]);
        
                dp[sr][sc] = minCost + grid[sr][sc];         
            }
        }

        return dp[0][0];
    }

    
    public static int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        // int ans= minPathSum(grid,0,0,dp);
        int ans= minPathSum_DP(grid,0,0,dp);

        return ans;
    }

    public static int goldMineProblem(int[][] coins,int sr,int sc,int[][] dp,int[][] dir){
        if(sc==coins[0].length-1){
            return dp[sr][sc] = coins[sr][sc];
        }

        if(dp[sr][sc]!=0) return dp[sr][sc];

        for(int d=0;d<3;d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(r>=0 && c>=0 && r < coins.length && c < coins[0].length){
                dp[sr][sc] = Math.max(dp[sr][sc], goldMineProblem(coins,r,c,dp,dir) + coins[sr][sc]);
            }
        }

        return dp[sr][sc];
    }

    
    public static int goldMineProblem_DP(int[][] coins,int sr,int sc,int[][] dp,int[][] dir){
        for(sc = coins[0].length - 1;sc>=0;sc--){
            for(sr = coins.length - 1; sr>=0;sr--){
                if(sc==coins[0].length-1){
                    dp[sr][sc] = coins[sr][sc];
                    continue; 
                }
                
                for(int d=0;d<3;d++){
                     int r = sr + dir[d][0];
                     int c = sc + dir[d][1];
         
                     if(r>=0 && c>=0 && r < coins.length && c < coins[0].length){
                        dp[sr][sc] = Math.max(dp[sr][sc], dp[r][c]  + coins[sr][sc]);
                     }
                 }
            }
        }

        int max = 0;
        for(int i=0;i<coins.length;i++){
            max = Math.max(max, dp[i][0]);
        }

        return max;
    }

    public static int goldMineProblem(){
        int[][] coins={{10, 33, 13, 15},
                        {22, 21, 04, 1},
                        {5, 0, 2, 3},
                        {0, 6, 14, 2}};
        int[][] dp = new int[coins.length][coins[0].length];
        int[][] dir = {{-1,1},{0,1},{1,1}};

        int max = 0;
        // for(int i=0;i<coins.length;i++){
        //     max = Math.max(max, goldMineProblem(coins,i,0,dp,dir));
        // }

        max=goldMineProblem_DP(coins,0,0,dp,dir);

        print2D(dp);
        return max;
    }

    //geeksforgeeks.org/friends-pairing-problem/
    public static int frindsPairing(int n,int[] dp){
        if(n<=1){
            return dp[n] = 1;
        }

        if(dp[n]!=0) return dp[n];

        int single = frindsPairing(n-1,dp);
        int pairUp = frindsPairing(n - 2,dp) * (n-1);

        return dp[n] = single + pairUp;
    }

    public static int frindsPairing_DP(int N,int[] dp){
        
        for(int n=0;n <= N;n++){
            if(n<=1){
                dp[n] = 1;
                continue;
            }

            int single = dp[n-1];//frindsPairing(n-1,dp);
            int pairUp = dp[n-2]*(n-1);//frindsPairing(n - 2,dp) * (n-1);
            dp[n] = single + pairUp;

            // dp[n] = dp[n-1] + dp[n-2] * (n-1);
        }

        return dp[N];
    }

    public static int frindsPairing_Opti(int N,int[] dp){
        int a = 1;
        int b = 1;
        for(int i=2; i <= N;i++){
            int sum = a * (i-1) + b;
            a = b;
            b = sum;
        }

        return b;
    }

    public static void frindsPairing(){
        int n=84;
        int[] dp =new int[n+1];
        int ans = frindsPairing(n,dp);

        print(dp);
        System.out.println(ans);
    }
 
    public static int count_of_ways(int n,int k,int[][] dp){
        if(k == 1 || k == n){
            return dp[n][k] = 1;
        }

        if(dp[n][k]!=0) return dp[n][k];

        int ownSet = count_of_ways(n-1,k-1,dp);
        int partOfSet = count_of_ways(n-1,k,dp) * k;

        return dp[n][k] = ownSet + partOfSet;
    }

    public static void count_of_ways(){

    }

    //StringSet.================================================================

    //longest Plaindromic Subsequence
    // 516
    public static int longestPSS(String str,int i ,int j,int[][] dp){
        if(i >= j){
            return dp[i][j] = (i == j ? 1 : 0);
        }

        if(dp[i][j] != 0) return dp[i][j];

        if(str.charAt(i) == str.charAt(j)) return dp[i][j] = longestPSS(str,i+1,j-1,dp) + 2;
        else return dp[i][j] = Math.max(longestPSS(str,i+1,j,dp),longestPSS(str,i,j-1,dp));
    }

    public static int longestPSS_DP(String str,int i ,int j,int[][] dp){
        int n = str.length();
        for(int gap = 0;gap < n;gap++){
            for(i=0,j=gap;j<n;i++,j++){
                if(i == j) dp[i][j] = 1;
                else if(str.charAt(i) == str.charAt(j)) dp[i][j] = dp[i+1][j-1] + 2;
                else dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }
    
          return dp[0][n-1];
    }
    

    public static void longestPSS_String(String str,int i ,int j,int[][] dp,char[] ans,int si,int ei){
        if(i >= j){
            if(i == j) {   
                ans[si] = str.charAt(i);
                for(char ch : ans) System.out.print(ch);
                System.out.println();
            }

            return;
        }
       
        if(str.charAt(i) == str.charAt(j)){
            ans[si] = ans[ei] = str.charAt(i);
            longestPSS_String(str,i+1,j-1,dp,ans,si+1,ei-1);
        }else if(dp[i+1][j] > dp[i][j-1]){
            longestPSS_String(str,i+1,j,dp,ans,si,ei);
        }else{
            longestPSS_String(str,i,j-1,dp,ans,si,ei);
        }
    }

    public static int lCSS(String s1,String s2,int i,int j,int[][] dp){
        if(i == s1.length() || j == s2.length()){
            return dp[i][j] = 0;
        }

        if(dp[i][j] != 0) return dp[i][j];

        if(s1.charAt(i) == s2.charAt(j)) return dp[i][j] = lCSS(s1,s2,i+1,j+1,dp) + 1;
        else return dp[i][j] = Math.max(lCSS(s1,s2,i+1,j,dp),lCSS(s1,s2,i,j+1,dp));        
    }

    public static int lCSS_02(String s1,String s2,int n,int m,int[][] dp){
        if(n == 0 || m == 0){
            return dp[i][j] = 0;
        }

        if(dp[i][j] != 0) return dp[i][j];

        if(s1.charAt(n-1) == s2.charAt(m-1)) return dp[i][j] = lCSS(s1,s2,m-1,,m-1,dp) + 1;
        else return dp[i][j] = Math.max(lCSS(s1,s2,n-1,j,dp),lCSS(s1,s2,i,m-1,dp));        
    }

    public static int lCSS_DP(String s1,String s2,int i,int j,int[][] dp){
        for(int i = s1.length() - 1; i >= 0; i--){
            for(int i = s2.length() - 1; j >= 0; j--){

                if(i == s1.length() || j == s2.length()){
                    dp[i][j] = 0;
                    continue;
                }
                
                if(s1.charAt(i) == s2.charAt(j)) dp[i][j] = dp[i+1][j+1] + 1;
                else dp[i][j] = Math.max(dp[i+1][j],dp[i][j+1]);        
            }
        }

        return dp[0][0];
    }


    //https://priactice.geeksforgeeks.org/problems/count-palindromic-subsequences/1

    public static int countPS(String str,int i,int j,int[][] dp)
    {
        if(i>=j){
            return dp[i][j] = ( i == j ) ? 1 : 0;
        }

        int x = countPS(str,i+1,j-1,dp);
        int y = countPS(str,i,j-1,dp);
        int z = countPS(str,i+1,j,dp);
        
        if(str.charAt(i) == str.charAt(j)) dp[i][j] = (x + 1) + (y + z - x);
        else dp[i][j] = (y + z - x);

        return dp[i][j];
    }

    public static int countPS(String str)
    {
        int n = str.length();
        int[][] dp = new int[n][n];

        System.out.println(countPS(str, 0, 0 , dp));
    }

    // Leetcode 115 - Distinct Subsequences
    public static int numDistinct(String s, String t,int n,int m , int[][] dp) {
        if(n < m){
            return dp[n][m] = 0;
        }

        if(m==0){
            return dp[n][m] = 1;
        }

        if(dp[n][m] != -1) return dp[n][m];

        int a = numDistinct(s,t,n-1,m-1,dp);
        int b = numDistinct(s,t,n-1,m,dp);

        if(s.charAt(n-1) == t.charAt(m-1)) dp[n][m] = a + b;
        else dp[n][m] = b;

        return dp[n][m];
    }


    public static int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        
        for (int[] d : dp)
            Arrays.fill(d, -1);
        
        int ans = numDistinct(s, n,t, m, dp);
        
        // for (int[] d : dp) {
        //     for (int ele : d) {
        //         System.out.print(ele + " ");
        //     }
        //     System.out.println();
        // }
        return ans;
    }

    //Leetcode 940
    public int distinctSubseqII(String S) {
        int n = S.length();
       if(n==0) return 0;
       
       int mod = (int)1e9 + 7;
       
       S = "$"+S;
       int[] dp = new int[n+1];
       int[] locc = new int[26];
       Arrays.fill(locc,-1);
       
       dp[0] = 1;
       for(int i = 1;i <= n; i++){
           char ch = S.charAt(i);
           
           dp[i] = (dp[i-1] * 2) % mod;
           if(locc[ch-'a']!=-1){
               dp[i] =(dp[i] % mod  - dp[locc[ch-'a'] - 1] % mod + mod) % mod;
           }
           
           locc[ch-'a'] = i;
       }
       
       return dp[n] - 1;   
   }

   // https://www.geeksforgeeks.org/number-subsequences-form-ai-bj-ck/
   public static void aibjck(String str){

      int aCount = 0;
      int bCount = 0;
      int cCount = 0;
      for(int i=0;i<str.length();i++){
        char ch = str.charAt(i);
        if(ch == 'a') aCount =  aCount + (1 + aCount);   // notInclude  + Include (prev + self)
        else if(ch == 'b') bCount = bCount  + (aCount + bCount) ; // notInclude  + Include (prev + self)
        else if(ch == 'c') cCount = cCount  + (bCount + cCount) ; // notInclude  + Include (prev + self)
      }

      return cCount;
   }
   
   //72
   int editDistance(String word1, String word2,int n,int m,int[][] dp) {
    if(n==0 || m==0){
        return n!=0?n:m;
    }
    
    if(dp[n][m]!=-1) return dp[n][m];
    
    if(word1.charAt(n-1)==word2.charAt(m-1)) return dp[n][m]=editDistance(word1,word2,n-1,m-1,dp);
    else{
        int insert_  = editDistance(word1,word2,n,m-1,dp);
        int delete_  = editDistance(word1,word2,n-1,m,dp);
        int replace_ =editDistance(word1,word2,n-1,m-1,dp);
        
        return dp[n][m]=Math.min(Math.min(insert_,replace_),delete_)+1;
    }
}

int editDistance_DP(String word1, String word2,int n,int m,int[][] dp) {
    int N = n, M = m;
    for(n = 0;n <= N;n++){
        for(m=0;m <= M;m++){
            if(n==0 || m==0){
                dp[n][m] = n!=0?n:m;
                continue;
            }
            
            if(word1.charAt(n-1)==word2.charAt(m-1)) dp[n][m]=dp[n-1][m-1];
            else{
                int insert_  = dp[n][m-1];
                int delete_  = dp[n-1][m];
                int replace_ = dp[n-1][m-1];
                
                dp[n][m]=Math.min(Math.min(insert_,replace_),delete_)+1;
            }
        }
    }
}

public int wildCardMatching(String str1,String str2,int n,int m,int[][] dp){
    if(n == 0 && m == 0) return dp[n][m] = 1;
    else if(n==0 || m == 0){
        if(m == 1 && str2.charAt(m-1)=='*') return dp[n][m] = 1;
        return dp[n][m] = 0;
     }
 
     if(dp[n][m]!=-1) return dp[n][m];
 
     char ch1 = str1.charAt(n-1);
     char ch2 = str2.charAt(m-1);
     int val = -1;
     if(ch1 == ch2 || ch2 == '?') val = wildCardMatching(str1,str2,n-1,m-1,dp);
     else if(ch2=='*'){
         boolean res = false;
         res  = res || wildCardMatching(str1,str2,n-1,m,dp) == 1;
         res = res || wildCardMatching(str1,str2,n,m-1,dp) == 1;
 
         val = res ? 1 : 0;
     }
     else val = 0;
 
 
     return dp[n][m] = val;
 
 }
 
 public String removeStar(String s){
     if(s.length()==0) return "";
     StringBuilder sb = new StringBuilder();
 
     sb.append(s.charAt(0));
     int i = 1;
     while(i<s.length()){
         while( i<s.length() && s.charAt(i) == '*' &&  s.charAt(i-1) == s.charAt(i)) i++;
         
         if(i<s.length())sb.append(s.charAt(i));
         i++;
     }
 
     return sb.toString();
 }
 
 public boolean isMatch(String s, String p) {	
     p = removeStar(p);
     // System.out.println(p);
     int n = s.length();
     int m = p.length();
     int[][] dp = new int[n+1][m+1];
     for(int[] a: dp) Arrays.fill(a,-1);
 
     return wildCardMatching(s,p,n,m,dp) == 1;
 }
 
int minDistance(String word1, String word2) {
    int[][] dp = new int[word1.length() + 1][word2.length() + 1];
    for (int[] d : dp)
        Arrays.fill(d, -1);
     return editDistance(word1,word2,word1.length(),word2.length(),dp);
}

 public int numDecodings(String s,int idx,int[] dp){
     if(idx >= s.length()){
         return dp[idx] = 1;
     }
     
     if(dp[idx] != 0) return dp[idx];
     
     
     char ch = s.charAt(idx);
     if(ch == '0') return 0;
     
     int count = 0;
     
     count += numDecodings(s,idx+1,dp);
     if(idx < s.length()-1){
         char ch1 = s.charAt(idx+1);
         int num = ( ch - '0') * 10 + (ch1 - '0');
         if(num <= 26){
             count += numDecodings(s,idx + 2,dp);
         }
     }
     
     return dp[idx] = count;
 }

 public int numDecodings_DP(String s,int idx,int[] dp){
    for(idx = s.length(); idx >= 0 ; idx--){
        if(idx >= s.length()){
            dp[idx] = 1;
            continue;
        }
        
        char ch = s.charAt(idx);
        if(ch == '0'){
            dp[idx] = 0;
            continue;
        }
        dp[idx] += dp[idx + 1];
        if(idx < s.length()-1){
            char ch1 = s.charAt(idx+1);
            int num = ( ch - '0') * 10 + (ch1 - '0');
            if(num <= 26){
                dp[idx] += dp[idx + 2];
            }
        }
    }

    return dp[0];
 }

 public int numDecodings_Opti(String s,int idx,int[] dp){
    int a = 1;
    int b = 0;
    for(idx = s.length()-1; idx >= 0 ; idx--){

        char ch = s.charAt(idx);
        int sum = 0;
        if(ch != '0'){
            sum += a;
            if(idx < s.length()-1){
                char ch1 = s.charAt(idx+1);
                int num = ( ch - '0') * 10 + (ch1 - '0');
                if(num >= 10 && num <= 26){
                    sum += b;
                }
            }
        }

        b = a;
        a = sum;
    }

    return a;
 }

 
 
public int numDecodings(String s) {
    if(s.length()==0) return 0;
     int[] dp = new int[s.length() + 1];
     
    int ans = numDecodings(s, 0,dp);

    for(int ele: dp) System.out.print(ele + " ");
    return ans;
 }
 

 // Leetcode 639
 long mod = (int) 1e9 + 7; 
 public long numDecodings(String s,int idx,long[] dp){
    if(idx >= s.length()){
        return dp[idx] = 1;
    }
    
    if(dp[idx] != 0) return dp[idx];
    
    
    char ch = s.charAt(idx);
    if(ch == '0') return 0;
    
    long count = 0;
    
    if(ch >= '1' && ch <= '9') count = (count % mod  + numDecodings(s,idx+1,dp) % mod ) % mod;
    else if(ch == '*') count = (count % mod  + 9 * numDecodings(s,idx+1,dp) % mod ) % mod;
    
    if(idx < s.length()-1){
        char ch1 = s.charAt(idx+1);
        if(ch !='*' && ch1 != '*'){
            int num = ( ch - '0') * 10 + (ch1 - '0');
            if(num <= 26){
                count = (count +  numDecodings(s,idx + 2,dp) % mod ) % mod;
            }
        }else if((ch >= '1' && ch <='2' && ch1 == '*')){
            if(ch == '1') count = (count % mod  + 9 * numDecodings(s,idx+2,dp) % mod ) % mod;
            if(ch == '2') count = (count % mod  + 6 * numDecodings(s,idx+2,dp) % mod ) % mod;
        }else if(ch == '*' && ch1 != '*'){
            if(ch1 >='0' && ch1 <= '6') count = (count % mod  + 2 * numDecodings(s,idx+2,dp) % mod ) % mod;
            if(ch1 >='7' && ch1 <= '9') count = (count % mod  + numDecodings(s,idx+2,dp) % mod ) % mod;
        }else if(ch == '*' && ch1 == '*') count = (count % mod  + 15 * numDecodings(s,idx+2,dp) % mod ) % mod;
    }
    
    return dp[idx] = count % mod;
}

public int numDecodings(String s) {
    if(s.length()==0) return 0;
    long[] dp = new long[s.length() + 1];
    
    return (int)numDecodings(s, 0,dp);
}

// TargetSet/ CoinChange.==========================================================


public static int coinChangePermutation(int[] arr,int tar){
    if(tar == 0){
        return dp[tar] = 1;
    }

    if(dp[tar] != 0) return dp[tar];

    int count = 0;
    for(int ele : arr){
        if(tar - ele >= 0)
          count += coinChangePermutation(arr,tar - ele); 
    }

    return dp[tar] = count;
}

public static int coinChangePermutation_DP(int[] arr,int tar){

    int Tar = tar;
    dp[0] = 1;
    for(tar = 1 ;tar <= Tar;tar++){   
        for(int ele : arr){
            if(tar - ele >= 0)
              dp[tar] += dp[tar - ele];
        }
    }

    return dp[Tar];
}

public static int coinChangeCombination_DP(int[] arr,int tar){
    int Tar = tar;
    dp[0] = 1;
    for(int ele : arr){
        for(tar = ele ;tar <= Tar;tar++){   
              dp[tar] += dp[tar - ele];
        }
    }

    return dp[Tar];
}


//Leetcode 322

public static int coinChange(int[] coins,int tar,int[] dp){
    if(tar == 0){
        return dp[tar] = 0;
    }
    
    if(dp[tar] != -1) return dp[tar];

    int minCoin = (int)1e9;
    for(int ele: coins){
        if(tar - ele >= 0){
            int ans = coinChange(coins,tar-ele,dp);
            if(ans != (int)1e9 && ans + 1 < minCoin) minCoin = ans + 1;
        }
    }

    return dp[tar] = minCoin;
}

public static int coinChange(int[] coins,int tar,int[] dp){
    int Tar = tar;
    dp[0] = 0;
    for(tar = 1; tar <= Tar; tar++){
        int minCoin = (int)1e9;
        for(int ele: coins){
            if(tar - ele >= 0){
              int ans = dp[tar - ele];
              if(ans != (int)1e9 && ans + 1 < minCoin) minCoin = ans + 1;
            }
        }

       dp[tar] = minCoin;
    }

    return dp[Tar];
}

public int coinChange(int[] coins, int amount) {
    int[] dp=new int[amount+1];
    Arrays.fill(dp,-1);
    int ans = coinChange(coins,amount,dp);
    
    return ans!=(int)1e9?ans:-1;
}

//https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
// same as coin change combination.

public static int targetSum(int[] arr,int idx,int tar,int[][] dp){
    if(tar == 0 || idx == arr.length){
        return dp[idx][tar] = (tar == 0)? 1 : 0;
    }

    if(dp[idx][tar]!=0) return dp[idx][tar];
    
    if(tar - arr[idx] >= 0) dp[idx][tar] += targetSum(arr,idx+1,tar-arr[idx],dp);
    dp[idx][tar] += targetSum(arr,idx+1,tar,dp); 

    return dp[idx][tar];
}

public static int targetSum(int[] arr,int n,int tar,int[][] dp){
    if(tar == 0 || n == 0){
        return dp[n][tar] = (tar == 0)? 1 : 0;
    }

    if(dp[n][tar]!=0) return dp[idx][tar];
    
    if(tar - arr[ n - 1] >= 0) dp[n][tar] += targetSum(arr,n - 1,tar - arr[n - 1],dp);
    dp[n][tar] += targetSum(arr,n - 1,tar,dp); 

    return dp[n][tar];
}

public static int targetSum(int[] arr,int N,int Tar,int[][] dp){
    for(int n = 0; n<=N;n++){
        for(int tar = 0 ; tar<=Tar;tar++){
            
            if(tar == 0 || n == 0){
                dp[n][tar] = (tar == 0)? 1 : 0;
                continue;
            }

            if(tar - arr[ n - 1] >= 0) dp[n][tar] += dp[n-1][tar];
            dp[n][tar] += dp[n-1][tar];             
        }
    }

    return dp[N][Tar];
}

public static void targetSum(int[] arr,int tar){


}


    public static void stringSet(){
        // String str = "geeksforgeeks";
        // int n = str.length();
        // int[][] dp = new int[n][n];
        
        // for(int[] d:dp) Arrays.fill(d,-1);

        // System.out.println(longestPSS(str,0,str.length()-1,dp));
        // System.out.println(longestPSS_DP(str,0,str.length()-1,dp));

        // int len = dp[0][n-1];
        // char[] ans = new char[len];
        // longestPSS_String(str,0,n-1,dp,ans,0,len-1);
        
        //---------------------------------------------------------------------------

        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n+1][m+1];
        System.out.println(lCSS_DP(s1,s2,0,0,dp));

        print2D(dp);
    }

    public static void Basic(){
        // int n = 10;
        // int[] dp = new int[n+1];
        // System.out.println(fibo(n,dp));
        // System.out.println(fibo_DP(n,dp));
        // System.out.println(fibo_opti(n));
        
        // int n=5;
        // int[][] dp =new int[n][n];
        // System.out.println(mazePath_HVD(0,0,n-1,n-1,dp));
        // System.out.println(mazePath_HVD_DP(0,0,n-1,n-1,dp));

        // System.out.println(mazePath_HVDJump_DP(0,0,n-1,n-1,dp));
           
        // int n = 10;
        // int[] dp = new int[n+1];
        // System.out.println(boardPath(0,n,dp));
        // System.out.println(boardPath_DP(0,n,dp));
        
        // System.out.println(goldMineProblem());
        // frindsPairing();

        // print(dp);
        // print2D(dp);
    }


    public static void solve(){
        // Basic();
        stringSet();
    }

    public static void main(String[] args){
        solve();
    }
}