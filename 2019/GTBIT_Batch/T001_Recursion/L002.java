public class L002{

    public static int coinChangePermutation_Infi(int[] coins,int tar,String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int ele: coins){
            if(tar - ele >= 0)
               count += coinChangePermutation_Infi(coins,tar-ele,ans + ele + " ");
        }

        return count;
    }

    public static int coinChangeCombination_Infi(int[] coins,int tar,int idx,String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = idx; i < coins.length; i++){
            if(tar - coins[i] >= 0)
               count += coinChangeCombination_Infi(coins,tar - coins[i],i,ans + coins[i] + " ");
        }

        return count;
    }

    public static int coinChangeCombination(int[] coins,int tar,int idx,String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = idx; i < coins.length; i++){
            if(tar - coins[i] >= 0)
               count += coinChangeCombination(coins,tar - coins[i],i + 1,ans + coins[i] + " ");
        }

        return count;

    }

    
    public static int coinChangePermutation(int[] coins,int tar,String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = 0; i < coins.length; i++){
            int val = coins[i];
            if(tar - val >= 0 && val > 0){
                coins[i] = -coins[i];
                count += coinChangePermutation(coins,tar - val,ans + val + " ");
                coins[i] = -coins[i];
            }
        }

        return count;

    }

    public static int coinChangeCombination2(int[] coins,int tar,int idx,String ans){
        if(tar == 0 || idx == coins.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(tar - coins[idx] >= 0)
          count += coinChangeCombination2(coins,tar - coins[idx], idx + 1, ans + coins[idx] + " ");
        count += coinChangeCombination2(coins,tar, idx + 1, ans);
    
        return count;
    }

    public static void main(String[] args){
        int[] coins = {2,3,5,7};
        int tar = 10;

        // System.out.println(coinChangePermutation_Infi(coins,tar,""));
        // System.out.println(coinChangeCombination_Infi(coins,tar,0,""));
        // System.out.println(coinChangeCombination(coins,tar,0,""));
        // System.out.println(coinChangePermutation(coins,tar,""));

        System.out.println(coinChangeCombination2(coins,tar,0,""));
    }






}