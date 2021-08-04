public class l001 {

    // 0 -> 1, 1 -> 1 / false -> true, true -> true // -> arr[idx] = true;
    public static void offToOn(int n, int k) {
        int mask = (1 << k);
        n |= mask;
        System.out.println(n);
    }

    // 1 -> 0, 0 -> 0 / true -> false, false -> false // -> arr[idx] = false;
    public static void onToOff(int n, int k) {
        int mask = (1 << k);
        n &= (~mask);

        System.out.println(n);
    }

    public static int countSetBits(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            int mask = (1 << i);
            if ((n & mask) != 0)
                count++;
        }
        return count;
    }

    public static int countSetBits_02(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) != 0)
                count++;
            n >>>= 1;
        }
        return count;
    }

    public static int countSetBits_03(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    // 231
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // 342
    public boolean isPowerOfFour(int n) {
        if (n <= 0 || (n & (n - 1)) != 0)
            return false;

        int count = 0;
        while (n != 1) {
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

    // 338
    public int[] countBits(int n) {

        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i & (i - 1)] + 1;
        }

        return arr;

    }

    // 190

    // 268
    public int missingNumber(int[] nums) {
        int ans = 0;
        int i = 0;
        for (int ele : nums) {
            ans ^= ele;
            ans ^= i;
            i++;
        }

        return ans ^ i;
    }

    public int missingNumber(int[] nums) {
        int ans = 0, n = nums.length;
        for (int ele : nums) {
            ans += ele;
        }

        return ((n * (n + 1)) / 2) - ans;
    }

    // 260
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int ele : nums)
            xor ^= ele;
        int xor_mask = xor & (-xor);

        int A = 0, B = 0;
        for (int ele : nums)
            if ((ele & xor_mask) == 0)
                A ^= ele;
            else
                B ^= ele;

        return new int[] { A, B };
    }

    // 137
    public int singleNumber(int[] nums) {
        int k = 3;
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int mask = (1 << i);
            int count = 0;
            for (int ele : nums) {
                if ((ele & mask) != 0)
                    count++;
            }

            ans |= (count % k) != 0 ? mask : 0;
        }

        return ans;
    }

    public static void main(String[] args) {
        int a = 4;
        int b = 5;
        System.out.println(a & b);
    }
}