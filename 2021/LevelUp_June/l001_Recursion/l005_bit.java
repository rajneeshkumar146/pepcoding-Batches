public class l005_bit {

    public static void leftShift() {
        int n = 5;
        for (int i = 1; i <= 4; i++) {
            System.out.println((n << i));
        }
    }

    public static void rightShift() {
        int n = -1;
        // for (int i = 1; i <= 4; i++) {
        // System.out.println((n >>> i));
        // }

        while (n != 0) {
            n >>>= 1;
        }
    }

    public static void OffOn(int n, int k) {
        int mask = (~(1 << k));
        n &= mask;
    }

    public int hammingWeight_01(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0)
                count++;
        }

        return count;
    }

    public int hammingWeight_02(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) != 0)
                count++;

            n >>>= 1;
        }

        return count;
    }

    public int hammingWeight_03(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= (n - 1);
        }

        return count;
    }

    public static void onOff(int n, int k) {
        int mask = (1 << k);
        n |= mask;
    }

    // 338
    public int[] countBits(int num) {

        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }

        return ans;

    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // 342
    public boolean isPowerOfFour(int n) {
        if (n <= 0 || (n & (n - 1)) != 0)
            return false;

        int count = 0;
        while (n != 0) {
            if ((n & 1) == 0)
                count++;

            n >>>= 1;
        }

        return (count & 1) == 0;
    }

    // 136
    public int singleNumber(int[] nums) {

        int ans = 0;
        for (int ele : nums)
            ans ^= ele;
        return ans;
    }

    //137
    public int singleNumber(int[] nums) {
        
    }

    public static void main(String[] args) {
        rightShift();
    }

}