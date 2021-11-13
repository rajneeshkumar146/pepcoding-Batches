public class l005_bit {
    // ON -> OFF
    // OFF -> OFF
    public static int onOFF(int x, int k) {
        int mask = (~(1 << k));
        return (x &= mask);
    }

    // OFF -> ON
    // ON -> ON
    public static int oFFON(int x, int k) {
        int mask = (1 << k);
        return (x |= mask);
    }

    public static boolean isOn(int x, int k) {
        int mask = (1 << k);
        return (x & mask) != 0;
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public boolean isPowerOfFour(int n) {
        if (n <= 0 || (n & (n - 1)) != 0)
            return false;

        int count = 0;
        while (n != 1) {
            n >>>= 1;
            count++;
        }

        return (count & 1) == 0;
    }

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }

        return count;

    }

    // 338
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++)
            ans[i] = ans[(i & (i - 1))] + 1;

        return ans;
    }

    //260
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int ele : nums)
            xor ^= ele;

        int mask = (xor & (-xor));

        int A = 0, B = 0;
        for (int ele : nums) {
            if ((ele & mask) == 0)
                A ^= ele;
            else
                B ^= ele;
        }

        return new int[] { A, B };
    }
}