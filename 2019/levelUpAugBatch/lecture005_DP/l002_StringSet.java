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

    public static void main(String[] args){


        
    }


}