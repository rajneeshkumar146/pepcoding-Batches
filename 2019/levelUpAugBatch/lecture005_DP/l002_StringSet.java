import java.util.Arrays;
import java.util.LinkedList;

public class l002_StringSet{
    public static void print(int[] arr){
        for(int ele: arr)
          System.out.print(ele + " "); 
        
        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] a: arr)
          print(a);

        System.out.println();
    }

    public static void palindromicSubstring(String str,boolean[][] dp){
        int n = str.length();
        for(int gap = 0;gap < n;gap++){
            for(int i=0,j=gap; j < n;i++,j++){
                if(gap == 0) dp[i][j] = true;
                else if(gap == 1) dp[i][j] = str.charAt(i) == str.charAt(j);
                else dp[i][j] = str.charAt(i) == str.charAt(j)  && dp[i+1][j-1];
            }
        }
    } 


    //Leetcode 647
    public int countSubstrings(String s) {
        int n = str.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0; 
        for(int gap = 0;gap < n;gap++){
            for(int i=0,j=gap; j < n;i++,j++){
                if(gap == 0) dp[i][j] = true;
                else if(gap == 1) dp[i][j] = str.charAt(i) == str.charAt(j);
                else dp[i][j] = str.charAt(i) == str.charAt(j)  && dp[i+1][j-1];
            
                if(dp[i][j]) count++; 
            }
        }
        return count;
    }

    // Leetcode 005
    public String longestPalindrome(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        
        int si = 0,ei = 0,length = 0; // starting index, ending index of longest palindromic susbtring.
        
        for(int gap = 0;gap < n;gap++){
            for(int i=0,j=gap; j < n;i++,j++){

                if(gap == 0) dp[i][j] = 1;
                else if(gap == 1 && str.charAt(i) == str.charAt(j)) dp[i][j] = 2;
                else if(str.charAt(i) == str.charAt(j) && dp[i+1][j-1] > 0) dp[i][j] = dp[i+1][j-1] + 2;
            
                if(dp[i][j] > length){
                    length = dp[i][j];
                    si = i;
                    ei = j;
                } 
            }
        }

        return str.substring(si,ei+1);
    }

    // Leetcode 516
    
    public int longestPalindromeSubseq(String s,int i,int j,int[][] dp) {
        if( i > j || i == j) return dp[i][j] = (i == j) ? 1: 0;

        if(dp[i][j]!=0) return dp[i][j];

        if(s.charAt(i) == s.charAt(j)) dp[i][j] = longestPalindromeSubseq(s,i+1,j-1,dp) + 2;
        else dp[i][j] = Math.max(longestPalindromeSubseq(s,i+1,j,dp),longestPalindromeSubseq(s,i,j-1,dp));

        return dp[i][j];
    }

    
    public int longestPalindromeSubseqDP(String s,int I,int J,int[][] dp) {
        int n = s.length();
        String[][] sdp = new String[n][n];
        for(String[] d: sdp) Arrays.fill(d,"");

        for(int gap = 0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                
                if(i == j) {
                    dp[i][j] = (i == j) ? 1: 0;
                    sdp[i][j] = s.charAt(i) + "";
                    continue;
                }

                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                    sdp[i][j] = s.charAt(i) + sdp[i+1][j-1] +s.charAt(i);
                }
                else{
                    if(dp[i+1][j] > dp[i][j-1]){
                        dp[i][j] = dp[i+1][j];
                        sdp[i][j] = sdp[i+1][j];
                    }else{
                        dp[i][j] = dp[i][j-1];
                        sdp[i][j] = sdp[i][j-1];
                    }
                }               
            }
        }

        return dp[I][J];
    }

     // ls = left palindrome string,rs = right palindrome string
     public static void generateString(int[][] dp,String s,int i,int j,LinkedList<Character> ls,LinkedList<Character> rs){
        if(i > j || i == j){
            if(i==j){
                ls.addLast(s.charAt(i));
            }

            System.out.print(ls);
            System.out.print(rs);
            System.out.println();
            
            if(i==j){
                ls.removeLast();
            }
 
            return;
        }

        if(s.charAt(i) == s.charAt(j)){            
            ls.addLast(s.charAt(i));
            rs.addFirst(s.charAt(i));

            generateString(dp,s,i+1,j-1,ls,rs);

            ls.removeLast();
            rs.removeFirst();
        } 
        else{

            if(dp[i+1][j] > dp[i][j-1]) generateString(dp,s,i+1,j,ls,rs);
            else if(dp[i+1][j] < dp[i][j-1]) generateString(dp,s,i,j-1,ls,rs);
            else{
                generateString(dp,s,i,j-1,ls,rs);
                generateString(dp,s,i+1,j,ls,rs);
            }
        }
    }

    public static void generateString(int[][] dp,String s,int i,int j,String ls,String rs){
        if(i > j || i == j){
            if(i==j){
                ls += s.charAt(i);
            }
            System.out.println(ls + rs);
            return;
        }

        if(s.charAt(i) == s.charAt(j)){            
            generateString(dp,s,i+1,j-1,ls + s.charAt(i),s.charAt(i) + rs);
        } 
        else{
            if(dp[i+1][j] > dp[i][j-1]) generateString(dp,s,i+1,j,ls,rs);
            else if(dp[i+1][j] < dp[i][j-1]) generateString(dp,s,i,j-1,ls,rs);
            // else{
            //     generateString(dp,s,i,j-1,ls,rs);
            //     generateString(dp,s,i+1,j,ls,rs);
            // }
        }
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        // int ans = longestPalindromeSubseq(s,0,n-1,dp);
        int ans = longestPalindromeSubseqDP(s,0,n-1,dp);

        return ans;
    }

    //Leetcode 115
    public int numDistinct(String s, String t,int n,int m,int[][] dp){
        if(m == 0){
            return dp[n][m] = 1;
        }

        if(n < m){
            return dp[n][m] = 0;
        }

        if(dp[n][m] != -1) return dp[n][m];

        int count = 0;
        if(s.charAt(n-1) == t.charAt(m-1)){
            count += numDistinct(s,t,n-1,m-1,dp);
            count += numDistinct(s,t,n-1,m,dp);   
        }else
            count += numDistinct(s,t,n-1,m,dp);

        return dp[n][m] = count;
    }

    public int numDistinctDP(String s, String t,int N,int M,int[][] dp){
        for(int n = 0;n <= N;n++){
            for(int m = 0; m <= M;m++){
                if(m == 0){
                    dp[n][m] = 1;
                    continue;
                }
        
                if(n < m){
                    dp[n][m] = 0;
                    continue;
                }
        
                int count = 0;
                if(s.charAt(n-1) == t.charAt(m-1)){
                    count += dp[n-1][m-1];
                    count += dp[n-1][m];   
                }else
                    count += dp[n-1][m];
        
                dp[n][m] = count;
            }
        }

        return dp[N][M];
    }

    public int numDistinct(String s, String t){
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n+1][m+1];

        for(int[] d:dp) Arrays.fill(d,-1);
        int ans = numDistinct(s,t,n,m,dp);
        return ans;
    }

    // https://practice.geeksforgeeks.org/problems/count-palindromic-subsequences/1
    public int countPS(String str,int i,int j,int[][] dp)
    {
        if(i >= j ){
            return dp[i][j] = (i == j)?1:0;
        }
        if(dp[i][j]!=0) return dp[i][j];
        
        int x = countPS(str,i+1,j-1,dp);
        int y = countPS(str,i,j-1,dp);
        int z = countPS(str,i+1,j,dp);
        
        if(str.charAt(i) == str.charAt(j)) dp[i][j] = (x + 1) + (y + z - x); //(y + z + 1);
        else dp[i][j] = (y + z - x);
        
        return dp[i][j];
    }
    
    public int countPS_DP(String str,int I,int J,int[][] dp)
    {
        for(int gap = 0;gap < str.length();gap++){
            for(int i =0,j = gap; j < str.length();i++,j++){
        if(i >= j ){
            dp[i][j] = (i == j)?1:0;
            continue;
        }
        
        int x = dp[i+1][j-1];//countPS(str,i+1,j-1,dp);
        int y = dp[i][j-1];//countPS(str,i,j-1,dp);
        int z = dp[i+1][j];//countPS(str,i+1,j,dp);
        
        if(str.charAt(i) == str.charAt(j)) dp[i][j] = (x + 1) + (y + z - x); //(y + z + 1);
        else dp[i][j] = (y + z - x);
        
            }
        }
    
        return dp[I][J];
    }
    public  int countPS(String str)
    {
        int n = str.length();
        int[][] dp = new int[n][n];

        return (countPS_DP(str, 0, n - 1 , dp));
    }

    // Leetcode 1143
    public int longestCommonSubsequence(String s1, String s2,int n,int m,int[][] dp) {
        if(n==0 || m == 0) return 0;
        if(dp[n][m]!=-1) return dp[n][m];
        
        if(s1.charAt(n-1) == s2.charAt(m-1)) dp[n][m] = longestCommonSubsequence(s1,s2,n-1,m-1,dp) + 1;
        else dp[n][m] = Math.max(longestCommonSubsequence(s1,s2,n,m-1,dp),longestCommonSubsequence(s1,s2,n-1,m,dp));
        
        return dp[n][m];
    }

    public int longestCommonSubsequenceDP(String s1, String s2,int N,int M,int[][] dp) {
        for(int n=0;n<=N;n++){
            for(int m =0;m<=M;m++){
                if(n==0 || m == 0){
                    dp[n][m] = 0;
                    continue;
                }

                if(s1.charAt(n-1) == s2.charAt(m-1)) dp[n][m] = dp[n-1][m-1] + 1;
                else dp[n][m] = Math.max(dp[n][m-1],dp[n-1][m]);
            }
        }

        return dp[N][M];
    }
    
    public int longestCommonSubsequence(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        
        int[][] dp = new int[n+1][m+1];
        // for(int[] d : dp) Arrays.fill(d,-1);
        
        return longestCommonSubsequenceDP(s1,s2,n,m,dp);
    }

    //1035
    public int maxUncrossedLines(int[] A, int[] B) {
        int N=A.length;
        int M=B.length;
        
        int[][] dp=new int[N+1][M+1];
    
        for(int n=0;n<=N;n++){
            for(int m=0;m<=M;m++){
                
                if(n==0|| m==0) {
                    dp[n][m]=0;
                    continue;
                }
                if(A[n-1]==B[m-1]) dp[n][m]=dp[n-1][m-1]+1;
                else dp[n][m]=Math.max(dp[n-1][m],dp[n][m-1]);
            }
            
        }
        
        return dp[N][M];
    }

    //Leetcode 72
    public int minDistance(String word1, String word2,int n,int m,int[][] dp) {
        if(n==0 || m==0){
            //if(n==0 && m==0) return dp[n][m] = 0;
            return dp[n][m] = n != 0 ? n : m;
        }

        if(dp[n][m] != 0) return dp[n][m];

        if(word1.charAt(n-1) == word2.charAt(m-1))dp[n][m] = minDistance(word1,word2,n-1,m-1,dp);
        else dp[n][m] = 1 + Math.min(Math.min(minDistance(word1,word2,n,m-1,dp),minDistance(word1,word2,n-1,m,dp)),minDistance(word1,word2,n-1,m-1,dp));
        
        return dp[n][m];
    }

    public int minDistanceDP(String word1, String word2,int N,int M,int[][] dp) {
        for(int n=0;n<=N;n++){
            for(int m=0;m<=M;m++){
                if(n==0 || m==0){
                    dp[n][m] = n != 0 ? n : m;
                    continue;
                }
        
                if(word1.charAt(n-1) == word2.charAt(m-1)) dp[n][m] = dp[n-1][m-1];
                else dp[n][m] = 1 + Math.min(Math.min( dp[n-1][m], dp[n][m-1]), dp[n-1][m-1]);
            }
        }

        return dp[N][M];
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];
        return minDistanceDP(word1,word2,n,m,dp);
    }

    // Leetcode 44
    public int isMatch(String s, String p,int n,int m,int[][] dp) {
        if(n == 0 || m == 0){
            if(n==0 && m == 0) return dp[n][m] = 1;
            else if(m == 1 && p.charAt(m-1) == '*') return dp[n][m] = 1;
            return dp[n][m]  = 0;
        }

        if(dp[n][m] != -1) return dp[n][m];

        char ch1 = s.charAt(n - 1);
        char ch2 = p.charAt(m - 1);
        int val = -1;
        if(ch1 == ch2 || ch2 == '?')  val = isMatch(s,p,n-1,m-1,dp);
        else if(ch2 == '*'){
            boolean res = false;
            res = res || (isMatch(s,p,n,m-1,dp) == 1);
            res = res || (isMatch(s,p,n-1,m,dp) == 1);

            val = res ? 1: 0;
        }else val = 0;

        return dp[n][m] = val;
    }

    public static String removeStar(String str){
        if(str.length() == 0) return "";
        StringBuilder sb = new StringBuilder();

        sb.append(str.charAt(0));
        int i = 1;
        while(i < str.length()){
            while(i < str.length() && str.charAt(i - 1) == '*'&& str.charAt(i) == '*') i++;
            
            if(i < str.length()) sb.append(str.charAt(i));
            i++;
        }

        return sb.toString();
    }

    public boolean isMatch(String s, String p) {
        p = removeStar(p);
        int n = s.length();
        int m = p.length();

        int[][] dp = new int[n + 1][m + 1];
        for(int[] d: dp) Arrays.fill(d,-1);

        return isMatch(s,p,n,m,dp) == 1;
    }
    
    public static void main(String[] args){


    }


}