public class question{
    public static void main(String[] args){
        System.out.println(keyPadProblem(2));
    }
    
    public static int keyPadProblem(int num,int k,int[][] moves,int[][] dp){  // dp (K+1)*10
        if(k==1) return 1;  // iss number pe khade hoke ek digit ka sirf ek number number possible hai
        if(dp[k][num]!=0) return dp[k][num];
        int count=0;
        for(int ele: moves[num]){
            count+=keyPadProblem(ele,k-1,moves,dp);
        }

        return dp[k][num]=count;
    }

    public static int keyPadProblem_DP(int K,int[][] moves,int[][] dp){  // dp (K+1)*10
        
        for(int num=0;num<=9;num++){
            for(int k=1;k<=K;k++){
                if(k==1) {
                    dp[k][num]=1;
                    continue;
                }

                int count=0;
                for(int ele: moves[num]){
                   count+=dp[k-1][ele];
                }
            }

            dp[k][num]=count;
        }


        int count=0;
        for(int key=0;key<=9;key++)  // haar key ko press krke usse k-1 digit ka number mangwayenge.
          count+= dp[k][key];  // iss key ko mene press kiya bakki k-1 digit tum lekr ajao.

        return count; 
    }

     public static int keyPadProblem(int k){  // k digit ka number bnana hai.
        int[][] moves={ {0,8},
                        {1,2,4},
                        {2,1,3,5},
                        {3,2,6},
                        {4,1,5,7},
                        {5,2,4,6,8},
                        {6,5,3,9},
                        {7,4,8},
                        {8,0,5,7,9},
                        {9,8,6},
        };

        int[][] dp=new int[k+1][10];
        int count=0;
        for(int key=0;key<=9;key++)  // haar key ko press krke k digit ka number bnane ka try krenge.
          count+= keyPadProblem(key,k,moves,dp);  // iss key ko starting mein press krte hue k digit ka number bnado.

        return count;
     }


     public int maxCoins(int[] nums,int si,int ei,int[][] dp) {
       if(dp[si][ei]!=0) return dp[si][ei];

       int leftMultiplier = (si-1 == -1) ? 1 : nums[si - 1];
       int rightMultiplier = (ei+1 == nums.length) ? 1 : nums[ei + 1];
       int max_=0;
       for(int cut=si;cut<=ei;cut++){
           int leftAns = si==cut?0: maxCoins(nums,si,cut-1,dp);
           int rightAns = ei==cut?0: maxCoins(nums,cut+1,ei,dp);
           
           int myCost=leftAns + leftMultiplier * nums[cut] * rightMultiplier + rightAns;
           max_=Math.max(max_,myCost);
       }
       return dp[si][ei]=max_;
    }

    public int maxCoins_DP(int[] nums,int si,int ei,int[][] dp) {
        for(int gap=0;gap<nums.length;gap++){
            for(si=0,ei=gap;ei<nums.length;si++,ei++){

                int leftMultiplier = (si-1 == -1) ? 1 : nums[si - 1];
                int rightMultiplier = (ei+1 == nums.length) ? 1 : nums[ei + 1];
                int max_=0;
                for(int cut=si;cut<=ei;cut++){
                    int leftAns=si==cut?0:dp[si][cut-1];
                    int rightAns=ei==cut?0:dp[cut+1][ei];
                    
                    int myCost=leftAns + leftMultiplier * nums[cut] * rightMultiplier + rightAns;
                    max_=Math.max(max_,myCost);
                }
                dp[si][ei]=max_;        
            }

        }
    }

	public int maxCoins(int[] nums) {
        if(nums.length==0) return 0;
		int[][] dp=new int[nums.length][nums.length];
		return maxCoins(nums,0,nums.length-1,dp);
	}
   


}