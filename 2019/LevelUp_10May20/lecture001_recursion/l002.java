public class l002 {
	public static void main(String[] args) {
		solve();
	}

	//CoinChange.==================================================

	public static int coinPermuatation_INF(int[] arr, int idx, int tar, String ans) {
		if (tar == 0) {
			System.out.println(ans);
			return 1;
        }
        
		int count = 0;
		for (int i = idx; i < arr.length; i++)
		if (tar - arr[i] >= 0) count += coinPermuatation_INF(arr, 0, tar - arr[i], ans + arr[i] + " ");

		return count;
    }
    
	public static int coinPermuatation_SingleCoin(int[] arr, int idx, int tar, String ans) {
		if (tar == 0) {
			System.out.println(ans);
			return 1;
        }
        
		int count = 0;
		for (int i = idx; i < arr.length; i++){
		   if (arr[i] > 0 && tar - arr[i] >= 0){
               int temp=arr[i];    
               arr[i] =-arr[i];   // block
               count += coinPermuatation_SingleCoin(arr, 0, tar - temp, ans + temp + " ");
               arr[i] =-arr[i];   // unblock.
           }
        }
		return count;
	}    

	  public static int coinCombination_INF(int[] arr,int idx,int tar,String ans){
        if (tar == 0) {
			System.out.println(ans);
			return 1;
        }
        
		int count = 0;
		for (int i = idx; i < arr.length; i++)
		if (tar - arr[i] >= 0) count += coinCombination_INF(arr, i, tar - arr[i], ans + arr[i] + " ");

		return count;
      }
      
      public static int coinCombination_SingleCoin(int[] arr,int idx,int tar,String ans){
        if (tar == 0) {
			System.out.println(ans);
			return 1;
        }
        
		int count = 0;
		for (int i = idx; i < arr.length; i++)
		if (tar - arr[i] >= 0) count += coinCombination_SingleCoin(arr, i + 1, tar - arr[i], ans + arr[i] + " ");

		return count;
      }
 
      //================================================================

      public static int coinPermuatation_INF_02(int[] arr, int idx, int tar, String ans) {
		if (tar == 0) {
			System.out.println(ans);
			return 1;
        }
        
		int count = 0;
        if (tar - arr[i] >= 0) 
           count += coinPermuatation_INF_02(arr, ?, tar - arr[i], ans + arr[i] + " ");
        count += coinPermuatation_INF_02(arr, ?, tar, ans);

		return count;
    }
    
	public static int coinPermuatation_SingleCoin_02(int[] arr, int idx, int tar, String ans) {
		if (tar == 0) {
			System.out.println(ans);
			return 1;
        }
        
		int count = 0;
		if ( arr[idx] > 0 && tar - arr[idx] >= 0){ 
            int temp=arr[idx];    
            arr[idx] =-arr[idx];   // block
            count += coinPermuatation_SingleCoin_02(arr, ?, tar - arr[i], ans + arr[i] + " ");
            arr[idx] =-arr[idx];   // unblock
        }
        count += coinPermuatation_SingleCoin_02(arr, ?, tar, ans);

		return count;
    }   
    
    public static int coinCombination_INF_02(int[] arr,int idx,int tar,String ans){
        if (tar == 0) {
			System.out.println(ans);
			return 1;
        }
        
		int count = 0;
        if (tar - arr[i] >= 0) 
           count += coinCombination_INF_02(arr, ?, tar - arr[i], ans + arr[i] + " ");
        count += coinCombination_INF_02(arr, ?, tar , ans);

		return count;
      }


      public static int coinCombination_SingleCoin_02(int[] arr,int idx,int tar,String ans){
        if (tar == 0) {
			System.out.println(ans);
			return 1;
        }
        
		int count = 0;
        if (tar - arr[i] >= 0) 
           count += coinCombination_SingleCoin_02(arr, i + 1, tar - arr[i], ans + arr[i] + " ");
        count += coinCombination_SingleCoin_02(arr, i + 1, tar , ans);

		return count;
      }
      

	public static void coinChange() {
		int[] arr={2,3,5,7};
		int tar = 10;

        // System.out.println(coinPermuatation_INF(arr, 0, tar, ""));
        System.out.println(coinPermuatation_SingleCoin(arr, 0, tar, ""));
        // System.out.println(coinCombination_INF(arr, 0, tar, ""));
        // System.out.println(coinCombination_SingleCoin(arr, 0, tar, ""));
	}

	public static void solve() {
		coinChange();
	}
}