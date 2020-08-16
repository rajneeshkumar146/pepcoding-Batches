public class l003_bitmasking{

    public static int offOn(int n,int k){
        int mask = (1 << k);
        n = ( n | mask);
        return n;
    }

    
    public static int onOff(int n,int k){
        int mask = (~(1 << k));
        n = ( n & mask);
        return n;
    }

    public static int countBits(int n){
        int count=0;
        for(int i=0;i<32;i++){
            int mask = (1 << i);
            if((n & mask) != 0) count++;
        }
        return count;
    }

    public static int countBits_02(int n){
        int count=0;
        while(n!=0){
            if((n & 1) != 0) count++;
            n >>>= 1;
        }
        return count;
    }

    
    public static int countBits_02(int n){
        int count=0;
        while(n!=0){
            count++;
            int mask = (n&(-n));
            n^=mask;
        }
        return count;
    }

    public static int countBits_03(int n){
        
        int count=0;
        while(n!=0){
            count++;
            n&=(n-1);
        }

        return count;
    }

    //Leetcode 231
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n-1))==0;        
    }
    
    //leetcode 342
    public boolean isPowerOfFour(int num) {
        if(n > 0 && (n & (n-1))==0){
           int countZeros = 0;
           while(num != 1){
               num>>>=1;
               countZeros++;
           }

           return (countZeros & 1)==0;  // even
        }

        return false;
    }

    //leetcode 342
    public boolean isPowerOfFour(int num) {
        if(num > 0 && (num & (num - 1))==0){
             return (num-1)%3 == 0; 
        }
        return false;
    }

    //Leetcode 136
    public int singleNumber(int[] nums) {
        int ans=0;
        for(int ele: nums) ans^=ele;
        return ans;
    }

    //Leetcode 137
    public int singleNumber(int[] nums) {
        int ans=0;
        for(int i=0;i<32;i++){
            int count=0;
            int mask = (1 << i);
            for(int ele: nums){
                if((ele & mask) != 0) count++;
            }

            if(count % 3 != 0) ans |= mask;
        }

        return ans;
    }

    //Leetcode 260
    public int[] singleNumber(int[] nums) {
        int xorValue=0;
        for(int ele: nums) xorValue^=ele;
        
        int mask = xorValue & -xorValue;
        int a=0;
        int b=0;

        for(int ele: nums){
            if((ele & mask)!=0) a^=ele;
            else b^=ele;
        }

        return new int[]{a,b};
    }

    //MajorityElement(Boyer-Moore Voting Algorithm)
    public int majorityElement(int[] nums) {
        if(nums.size()==0) return -1;

        int idx=0;
        int count=1;

        for(int i=1; i < nums.length; i++){
            if(nums[i]==nums[idx]) count++;
            else count--;

            if(count==0){
                idx = i;
                count = 1;
            }
        }

        // // to verify 
        // count = 0;
        // for(int ele: nums){
        //    if(ele == nums[idx]) count++;
        // }
        // return count > nums.length/2;

        return nums[idx];

    }


    //Leetcode 78
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        int n = nums.length;

        for(int i=0; i < (1<<n); i++){
            List<Integer> ans=new ArrayList<>();
            for(int j=0;j < n;j++){
                if( (i & (1<<j)) != 0){
                    ans.add(nums[j]);
                }
            }
            res.add(ans);
        }

        return res;
    
   }
}