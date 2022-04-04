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

    }

    // 268
    public int missingNumber(int[] nums) {

    }

    // 191
    public int hammingWeight(int n) {

    }

    // 338
    public int[] countBits(int n) {

    }

    public static void main(String... args) {
        System.out.println(rightShift(40));
    }
}