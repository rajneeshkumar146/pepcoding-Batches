public class l002{
    
    public static void print(int[] arr){
        for(int ele: arr)
        System.out.print(ele+" ");

        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] ar: arr) print(ar);
        System.out.println();
    }


    public static int LIS_rec(int[] arr,int n,int[] dp){
        if(n == 0) return dp[n] = 1; 
        if(dp[n]!=0) return dp[n];
        
        int maxLen = 0;
        for(int i = n-1;i>=0;i--){
            if(arr[i] < arr[n]){
                maxLen = Math.max(maxLen,LIS_rec(arr,i,dp) + 1);
            }
        }
    
        return  dp[n] = maxLen;
       }
    
    public static int LIS_rec(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];

        int len = 0;
        for(int i = n - 1 ; i >= 0; i--){
            len = Math.max(len,LIS_rec(arr,i,dp));
        }
        
        print(dp);
        return len;
    }

    //left to Right
    public static int[] LIS_DP(int[] arr){
        int n = arr.length();
        int[] dp = new int[n];

        dp[0] = 1;
        int len = 0;

        for(int i = 1; i < n; i++ ){   
            dp[i] = 1;
            for(int j = i-1;j>=0;j--){
               if(arr[j] < arr[i]){
                  dp[i] = Math.max(dp[i],dp[j] + 1);
               }
            }

            len = Math.max(len,dp[i]);
        }

        return dp;

    }

    //Right to Left
    public static int[] LDS_DP(int[] arr){
        int n = arr.length();
        int[] dp = new int[n];

        int len = 0;

        for(int i = n-1; i >= 0 ; i-- ){   
            dp[i] = 1;
            for(int j = i-1;j>=0;j--){
               if(arr[j] < arr[i]){
                  dp[i] = Math.max(dp[i],dp[j] + 1);
               }
            }

            len = Math.max(len,dp[i]);
        }

        return dp;
    }

    public static int LBS(int[] arr){
        int[] LIS = LIS_DP(arr);
        int[] LDS = LDS_DP(arr);

        int maxLen = 0;
        for(int i=0;i<arr.length;i++){
            int len = LIS[i] + LDS[i] - 1;
            maxLen = Math.max(maxLen,len);
        }

        return maxLen;
    }

    // left to right
    public static int[] LDS_DP_02(int[] arr){
        int n = arr.length();
        int[] dp = new int[n];

        dp[0] = 1;
        int len = 0;

        for(int i = 1; i < n; i++ ){   
            dp[i] = 1;
            for(int j = i-1;j>=0;j--){
               if(arr[j] > arr[i]){
                  dp[i] = Math.max(dp[i],dp[j] + 1);
               }
            }

            len = Math.max(len,dp[i]);
        }

        return dp;
    }

    //Right to Left
    public static int[] LIS_DP_02(int[] arr){
        int n = arr.length();
        int[] dp = new int[n];

        int len = 0;

        for(int i = n-1; i >= 0 ; i-- ){   
            dp[i] = 1;
            for(int j = i-1;j>=0;j--){
               if(arr[j] > arr[i]){
                  dp[i] = Math.max(dp[i],dp[j] + 1);
               }
            }

            len = Math.max(len,dp[i]);
        }

        return dp;
    }

    public static int minNoOFDeletion(int[] arr){
        int n = arr.length();
        int[] dp = new int[n];

        int len = 0;
        for(int i = 0; i < n; i++ ){   
            dp[i] = 1;
            for(int j = i-1;j>=0;j--){
               if(arr[j] >= arr[i]){
                  dp[i] = Math.max(dp[i],dp[j] + 1);
               }
            }

            len = Math.max(len,dp[i]);
        }

        return n - len;
    }

    //Leetcode 673
    public int findNumberOfLIS(int[] arr) {
        int n=arr.length;
        int[] dp=new int[n];
        int[] count=new int[n];

        int maxLen=0;
        int maxCount=0;

        for(int i = 0; i < n; i++){
            
            dp[i] = 1;
            count[i] = 1;

            for(int j = i-1; j>=0; j--){
                if(arr[i] > arr[j]){

                    if(dp[j] + 1 > dp[i]){
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }else if(dp[j] + 1 == dp[i]){
                        count[i] += count[j]; 
                    }
                }
            }

            if(dp[i] > maxLen){
                maxLen = dp[i];
                maxCount = count[i];
            }else if(dp[i] == maxLen){
                maxCount += count[i];
            }
        }

        return maxCount;
    }

    // 354
    public static int maxEnvelopes(int[][] arr) {
        if(arr.length==0) return 0;
        
        int n = arr.length;
        Arrays.sort(arr,(a,b)->{
            if(a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];  // this - other, Increasing Order
        });

        int[] dp = new int[n];
        int maxLen = 0;
        for(int i = 0;i<n;i++){
            dp[i] = 1;
            for(int j = i - 1;j>=0;j--){
                if(arr[i][1] > arr[j][1]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen,dp[i]);
        }
        return maxLen;
    }

    public static void LISset(){

        int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15,10};
        System.out.println(LIS_rec(arr));
    }

       public static void solve(){
           LISset();
       }


       public static void main(String[] args){
           solve();
       }
    
}