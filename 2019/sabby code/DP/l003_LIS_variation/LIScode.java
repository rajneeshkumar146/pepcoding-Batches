import java.util.Arrays;
public class LIScode{
    public static void main(String[] args){


    }


    public static void LIS_leftToRight(int[] arr,int[] dp){
        int n=arr.length;
        
        int max=0;
        for(int i=0;i<n;i++){
            dp[i]=1;  // at least length.
            for(int j=i-1;j>=0;j--){
                if(arr[i]>arr[j])
                  dp[i]=Math.max(dp[i],dp[j]+1);
            }

            max=Math.max(max,dp[i]);
        }
    }

    public static void LDS_leftToRight(int[] arr,int[] dp){
        int n=arr.length;
        
        int max=0;
        for(int i=0;i<n;i++){
            dp[i]=1;  // at least length.
            for(int j=i-1;j>=0;j--){
                if(arr[i] < arr[j])
                  dp[i]=Math.max(dp[i],dp[j]+1);
            }

            max=Math.max(max,dp[i]);
        }
    }

    public static void LIS_rightToleft(int[] arr,int[] dp){
        int n=arr.length;
        
        int max=0;
        for(int i=n-1;i>=0;i--){
            dp[i]=1;  // at least length.
            for(int j=i+1;j<n;j++){
                if(arr[i]>arr[j])
                  dp[i]=Math.max(dp[i],dp[j]+1);
            }

            max=Math.max(max,dp[i]);
        }
    }

    public static void LDS_rightToleft(int[] arr,int[] dp){
        int n=arr.length;
        
        int max=0;
        for(int i=n-1;i>=0;i--){
            dp[i]=1;  // at least length.
            for(int j=i+1;j<n;j++){
                if(arr[i] < arr[j])
                  dp[i]=Math.max(dp[i],dp[j]+1);
            }

            max=Math.max(max,dp[i]);
        }
    }

    //leetcode 354
    public static int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes,(int[] a,int[] b)->{  //0 index is height and 1 index is width
           return a[0]-b[0];  // sort on height (increasing order: this-other)  , for reverse sorting (decreasing order:other -this)
        });
        
        int[] dp=new int[envelopes.length];
        int max=0;
        for(int i=0;i<n;i++){
            dp[i]=1;  // at least length.
            for(int j=i-1;j>=0;j--){
                if(envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1])   // both height and width should be grater for LIS.
                  dp[i]=Math.max(dp[i],dp[j]+1);
            }

            max=Math.max(max,dp[i]);
        }

        return max;
    }

    public int findNumberOfLIS(int[] arr) {
      int[] dp=new int[arr.length];
      int[] count=new int[arr.length];

      int maxLen=0;
      int maxCount=0;
      for(int i=0;i<n;i++){
          dp[i]=1;  // at least length.
          count[i]=1; // by default count is 1.
          for(int j=i-1;j>=0;j--){
              if(arr[i] > arr[j])   // both height and width should be grater for LIS.
                if(dp[j] + 1 > dp[i]){  // agar mera length update hoga to.
                    dp[i] = dp[j] + 1;
                    count[i] = count[j];
                }else if(dp[j] + 1 == dp[i]) 
                    count[i] += count[j];
          }
          
          if(dp[i] > maxLen){
              maxLen=dp[i];
              maxCount=count[i];
          }else if(dp[i] == maxLen){
              maxCount += count[i];
          }
      }
      return maxCount;
    }

    static int m=(int)1e9+7;
	public static long decodeWaysII(String s,int idx,long[] dp){
		if(idx==s.length()) return dp[idx]=1;

		if(dp[idx]!=0) return dp[idx];
		
        long count=0;
        int n=s.length();
		char ch=s.charAt(idx);
		if(ch=='*'){   // *(char) , **
           count = (count +  9*decodeWaysII(s,idx+1,dp)) % m; // value of '*' vary from 1-9
           
           if(idx + 1 < n && s.charAt(idx+1) >= '1' && s.charAt(idx+1) <= '6')  // *(char) : char vary from 1-6 it make only 2 calls of two length 
              count = (count +  2 * decodeWaysII(s,idx+2,dp)) % m; // value of '*' vary from 1-2

           if(idx + 1 < n && s.charAt(idx+1) >= '7' && s.charAt(idx+1) <= '9')  // *(char) : char vary from 7-9 it make only 2 calls of two length
               count = (count +   decodeWaysII(s,idx+2,dp)) % m; // value of '*' vary from 1-1     
            
           if(idx + 1 < n && s.charAt(idx+1) >= '*')  // ** : star vary from 1-9 it make only 15 calls of two length
               count = (count +  15*decodeWaysII(s,idx+2,dp)) % m;      
        }else if(ch!='0'){
            count = (count +  9*decodeWaysII(s,idx+1,dp)) % m; 

            if(idx + 1 < n){
                if(s.charAt(idx+1) == '*'){
                    if(s.charAt(idx) == '1')
                       count = (count +   9*decodeWaysII(s,idx+2,dp)) % m;
                    else if(s.charAt(idx) == '2')
                       count = (count +   6*decodeWaysII(s,idx+2,dp)) % m;

                }else{
                    int digit = (ch - '0') * 10 + (s.charAt(idx+1) - '0');
                    if(digit >= 10 && digit <= 26)
                      count = (count +   decodeWaysII(s,idx+2,dp)) % m; 
                }
            }


        }
}