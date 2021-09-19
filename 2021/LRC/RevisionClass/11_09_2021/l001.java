import java.util.ArrayList;

public class l001 {

    public static int decimalToBinary(int n) {
        int pow = 1, res = 0;
        while (n != 0) {
            int rem = n % 2;
            n /= 2;

            res = res + rem * pow;
            pow *= 10;
        }
        return res;
    }

    public static int decimalToAnyBase(int n, int base) {
        int pow = 1, res = 0;
        while (n != 0) {
            int rem = n % base;
            n /= base;

            res = res + rem * pow;
            pow *= 10;
        }
        return res;
    }

    public static int binaryToDecimal(int n) {
        int pow = 1, res = 0;
        while (n != 0) {
            int rem = n % 10;
            n /= 10;

            res = res + rem * pow;
            pow *= 2;
        }
        return res;
    }

    public static int anyBaseToDecimal(int n, int base) {
        int pow = 1, res = 0;
        while (n != 0) {
            int rem = n % 10;
            n /= 10;

            res = res + rem * pow;
            pow *= base;
        }
        return res;
    }

    // sb : source Base, db : destination base
    public static int anyBaseToanyBase(int n, int sb, int db) {
        int decimalNumber = anyBaseToDecimal(n, sb);
        int res = decimalToAnyBase(decimalNumber, db);
        return res;
    }

    public static int addTwoDecimal(int n, int m) {
        int carry = 0, res = 0, pow = 1;

        while (n != 0 || m != 0 || carry != 0) {
            int sum = n % 10 + m % 10 + carry;
            n /= 10;
            m /= 10;

            int lastDigit = sum % 10;
            carry = sum / 10;

            res += lastDigit * pow;
            pow *= 10;
        }

        return res;
    }

    public static int anyBaseaddition(int n, int m, int base) {
        int carry = 0, res = 0, pow = 1;

        while (n != 0 || m != 0 || carry != 0) {
            int sum = n % 10 + m % 10 + carry;
            n /= 10;
            m /= 10;

            int lastDigit = sum % base;
            carry = sum / base;

            res += lastDigit * pow;
            pow *= 10;
        }

        return res;
    }

    public static int subtractTwoDecimal(int n, int m) {
        int borrow = 0, res = 0, pow = 1;

        while (n != 0 || m != 0) {
            int diff = borrow + n % 10 - m % 10;
            n /= 10;
            m /= 10;

            if (diff < 0) {
                borrow = -1;
                diff += 10;
            } else {
                borrow = 0;
            }

            res += diff * pow;
            pow *= 10;
        }

        return res;
    }

    public static int subtractTwoAnyBase(int n, int m, int base) {
        int borrow = 0, res = 0, pow = 1;

        while (n != 0 || m != 0) {
            int diff = borrow + n % 10 - m % 10;
            n /= 10;
            m /= 10;

            if (diff < 0) {
                borrow = -1;
                diff += base;
            } else {
                borrow = 0;
            }

            res += diff * pow;
            pow *= 10;
        }

        return res;
    }

    public static int multiplyAnyBaseWithDigit(int n, int d, int base) {

        int pow = 1, res = 0, carry = 0;
        while (n != 0 || carry != 0) {

            int ans = carry + (n % 10) * d;
            n /= 10;

            int lastDigit = ans % base;
            carry = ans / base;

            res += lastDigit * pow;
            pow *= 10;
        }

        return res;
    }

    public static int multiplyAnyBaseNumber(int n, int m, int base) {
        int pow = 1, res = 0;
        while (m != 0) {
            int d = m % 10;
            m /= 10;

            int multiplyRes = multiplyAnyBaseWithDigit(n, d, base);
            res = anyBaseaddition(res, multiplyRes * pow, base);
            pow *= 10;
        }

        return res;
    }

    public static boolean findData(int[] arr, int data) {
        // for (int i = 0; i < arr.length; i++)
        // if (arr[i] == data)
        // return true;

        for (int ele : arr) {
            if (ele == data)
                return true;
        }

        return false;
    }

    public static void addTwoArrays(int[] a, int[] b) {
        int n = a.length, m = b.length;
        int len = Math.max(n, m) + 1;
        int[] ans = new int[len];

        int i = n - 1, j = m - 1, k = len - 1, carry = 0;
        while (k >= 0) {
            int sum = carry + (i >= 0 ? a[i--] : 0) + (j >= 0 ? b[j--] : 0);
            carry = sum / 10;
            ans[k--] = sum % 10;
        }

        int idx = 0;
        if (ans[0] == 0)
            idx++;
        while (idx < len)
            System.out.print(ans[idx]);
    }

    // a > b : 1, a < b : -1, a == b : 0
    public static int compareTo(int[] a, int[] b) {
        int n = a.length, m = b.length;
        if (n > m)
            return 1;
        else if (n < m)
            return -1;
        else {
            int i = 0, j = 0;
            while (i < n) {
                int diff = a[i] - a[j];
                if (diff != 0)
                    return diff > 0 ? 1 : -1;
            }
        }

        return 0;
    }

    public static void subtractTwoArrays(int[] a, int[] b) {
        int n = a.length, m = b.length, len = Math.max(n, m);
        int[] ans = new int[len];
        int res = compareTo(a, b);
        if (res < 0) {
            int[] temp = a;
            a = b;
            b = temp;
        }

        if (res == 0)
            System.out.println("0");

        int i = n - 1, j = m - 1, k = len - 1, borrow = 0;
        while (k >= 0) {
            int diff = borrow + (i >= 0 ? a[i--] : 0) - (j >= 0 ? b[j--] : 0);
            if (diff < 0) {
                diff += 10;
                borrow = -1;
            } else {
                borrow = 0;
            }
            ans[k--] = diff;
        }

        int idx = 0;
        boolean isNonZeroNumberFound = false;
        while (idx < len) {
            if (!isNonZeroNumberFound && ans[idx] > 0) {
                isNonZeroNumberFound = true;
            }

            if (isNonZeroNumberFound)
                System.out.println(ans[idx]);

            idx++;
        }
    }

    public static void spiralDisplay(int[][] arr) {
        int n = arr.length, m = arr[0].length, rmin = 0, cmin = 0, rmax = n - 1, cmax = m - 1, count = n * m;
        while (count > 0) {
            for (int c = cmin; c <= cmax && count > 0; c++) {
                System.out.print(arr[rmin][c]);
                count--;
            }
            rmin++;

            for (int r = rmin; r <= rmax && count > 0; r++) {
                System.out.print(arr[r][cmax]);
                count--;
            }
            cmax--;

            for (int c = cmax; c >= cmin && count > 0; c--) {
                System.out.print(arr[rmax][c]);
                count--;
            }
            rmax--;

            for (int r = rmax; r >= rmin && count > 0; r--) {
                System.out.print(arr[r][cmin]);
                count--;
            }
            cmin++;
        }
    }

    public static int minElementInRow(int[] arr) {
        int minEle = arr[0];
        int minIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minEle) {
                minEle = arr[i];
                minIdx = i;
            }
        }

        return minIdx;
    }

    public static int maxElementInCol(int[][] arr, int c) {
        int maxEle = arr[0][c];
        int maxIdx = 0;

        for (int r = 0; r < arr.length; r++) {
            if (arr[r][c] > maxEle) {
                maxEle = arr[r][c];
                maxIdx = r;
            }
        }
        return maxIdx;
    }

    public static int saddlePrice(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            int c = minElementInRow(arr[r]);
            int ele = arr[r][c];
            int maxEleRowIdx = maxElementInCol(arr, c);
            if (maxEleRowIdx == r)
                return ele;
        }

        return -1;
    }

    public static void ArrayListTest() {
        ArrayList<ArrayList<StringBuilder>> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arr.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                arr.get(i).add(new StringBuilder("(" + i + ", " + j + ") "));
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(arr.get(i).get(j) + " ");
            }
            System.out.println();
        }

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] ar = new ArrayList[10];
        for (int i = 0; i < 10; i++) {
            ar[i] = new ArrayList<>();
        }

        ar[3].add(2873);
    }

    public static void ArrayListTest_02() {
        ArrayList<ArrayList<int[][]>> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arr.add(new ArrayList<>());
            for (int j = 0; j < 4; j++)
                arr.get(i).add(new int[3][3]);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                int[][] ar = arr.get(i).get(j);

            }

        }
    }

    public static void toh(int n, int src, int dest, int help) {
        if (n == 0) {
            return;
        }

        toh(n - 1, src, help, dest);
        System.out.println(n + "[" + src + " -> " + dest + "]");
        toh(n - 1, help, dest, src);
    }

    public static void main(String[] args) {
        ArrayListTest();
    }
}