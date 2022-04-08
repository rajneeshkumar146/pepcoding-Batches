public class l005_bits {

    public static int leftShift(int x) {
        return x << 2;
    }

    public static int rightShift(int x) {
        return x >> 2;
    }

    public static int setTrue(int x, int idx) {
        int mask = 1 << idx;
        return x | mask;
    }

    public static int setFalse(int x, int idx) {
        int mask = ~(1 << idx);
        return x & mask;
    }

    public static int multiplyBy2(int num, int pow) {
        return (num << pow);
    }

    public static int divideBy2(int num, int pow) {
        return (num >> pow);
    }

    public static boolean isEven(int x) {
        return (x & 1) == 0;
    }

    // 231
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // 342
    public boolean isPowerOfFour(int n) {
        if (!isPowerOfTwo(n))
            return false;
        // for c++. unsigned int N = n;
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 0)
                count++;
            n >>>= 1; // only for java, cpp N >>=1;
        }

        return (count & 1) == 0;
    }

    // 136
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int ele : nums) {
            ans ^= ele;
        }

        return ans;

    }

    // 268
    public int missingNumber(int[] nums) {
        int n = nums.length, ans = n, i = 0;
        while (i < n) {
            ans ^= nums[i] ^ (i++);

        }
        return ans;
    }

    // 191

    public int hammingWeight_1(int n) {
        int count = 0, i = 0;
        while (i < 32) {
            if ((n & (1 << i)) != 0)
                count++;
            i++;
        }

        return count;
    }

    public int hammingWeight_2(int n) {
        int count = 0, i = 0;
        while (n != 0) {
            if ((n & 1) != 0)
                count++;
            n >>>= 1;
        }

        return count;
    }

    public int hammingWeight_3(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n & (n - 1));
        }

        return count;
    }

    // 338
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }

        return ans;

    }

    //260
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for(int ele : nums) xor ^= ele;
        int mask = xor & (-xor);
        
        int a = 0, b = 0;
        for(int ele : nums){
            if((ele & mask) == 0) a ^= ele;
            else b ^= ele;
        }
        
        return new int[]{a,b};
    }

    //287 -> after linkedlist
    


    public static void main(String... args) {
        System.out.println(rightShift(40));
    }
}