public class l004CatlaNumber{

    public static int ncr(int n,int r){  // O(min(r,n-r));
        if(r > n - r)
            r = n- r;
        
        long res = 0;
        for(long i = 0 ; i < k;i++){
            res = res * (n - i);
            res = res / (i + 1);
        }

        return (int)res;
    }

    public static void NoOfBST(int n){  // O(min(r,n-r));
        int c = ncr(2n,n);
        System.out.println(c/(n+1));
    }

    public static void NoOFBST2(int n){ //O(n2)
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        for(int i=2;i<n;i++){
            for(int j=i-1,k=0; j >=0 ; j--,k++){
                dp[i] += dp[j] * dp[k];
            }
        }
    }

    // https://en.wikipedia.org/wiki/Catalan_number#:~:text=1%2C%201%2C%202%2C%205,sequence%20A000108%20in%20the%20OEIS).

    //600
    public int findIntegers(int num) {
        if(num == 0) return 0;
        
        int[] dp = new int[32];
        dp[0] = 1;
        dp[1] = 2;
        for(int i = 2;i<32;i++) dp[i] = dp[i-1] + dp[i-2];
        
        int ans = 0;
        int bits = 31;
        boolean prevBit = false;
        while(bits>=0){
            if(( num & (1 << bits)) != 0){
                ans += dp[bits];
                if(prevBit) return ans;
                prevBit = true;
            }else prevBit = false;
            
            bits--;
        }
        
        return ans + 1;
    }






}