import java.util.Scanner;

public class l001 {
    public static Scanner scn = new Scanner(System.in);

    public static void name() {
        System.out.println("HI, Name is Rajneesh");
    }

    public static void add(int a, int b) {
        System.out.println("sum: " + (a + b));
    }

    public static int sum(int a, int b, int c) {
        return a + b + c;
    }

    public static long sum() {
        int a = scn.nextInt(), b = scn.nextInt(), c = scn.nextInt();
        return a + b + c;
    }

    public static int getDigitFrequency(int num, int d) {
        int count = 0;
        while (num != 0) {
            int lastDigit = num % 10;
            num /= 10;

            if (lastDigit == d)
                count++;
        }

        return count;
    }

    public static int reverse(int num) {
        int ans = 0;
        while (num != 0) {
            int ld = num % 10;
            num = num / 10;

            ans = ans * 10 + ld;
        }

        return ans;
    }

    public static int NoOfDigitsInNumber(int num) {
        int count = 0;
        while (num != 0) {
            num /= 10;
            count++;
        }

        return count;
    }

    public static int decimalToBinary(int num) {
        int pow = 1, ans = 0;
        while (num != 0) {
            int rem = num % 2;
            num /= 2;

            ans = ans + rem * pow;
            pow *= 10;
        }
        return ans;
    }

    public static int decimalToAnyBase(int num, int base) {
        int pow = 1, ans = 0;
        while (num != 0) {
            int rem = num % base;
            num /= base;

            ans = ans + rem * pow;
            pow *= 10;
        }
        return ans;
    }

    public static int binaryToDecimal(int bNum) {
        int pow = 1, ans = 0;
        while (bNum != 0) {
            int rem = bNum % 10;
            bNum /= 10;

            ans = ans + rem * pow;
            pow *= 2;
        }
        return ans;
    }

    public static int getValueIndecimal(int bNum, int base) {
        int pow = 1, ans = 0;
        while (bNum != 0) {
            int rem = bNum % 10;
            bNum /= 10;

            ans = ans + rem * pow;
            pow *= base;
        }
        return ans;
    }

    public static int anyBaseToAnyBase(int num, int a, int b) {
        int decimal = getValueIndecimal(num, a);
        int res = decimalToAnyBase(decimal, b);
        return res;
    }

    public static int addToDecimal(int n, int m) {
        int carry = 0, res = 0, pow = 1;
        while (n != 0 || m != 0 || carry != 0) {
            int sum = n % 10 + m % 10 + carry;
            n /= 10;
            m /= 10;
            int ldigit = sum % 10;
            carry = sum / 10;

            res = res + ldigit * pow;
            pow *= 10;
        }

        return res;
    }

    public static int anyBaseSum(int b, int n, int m) {
        int carry = 0, res = 0, pow = 1;
        while (n != 0 || m != 0 || carry != 0) {
            int sum = n % 10 + m % 10 + carry;
            n /= 10;
            m /= 10;
            int ldigit = sum % b;
            carry = sum / b;

            res = res + ldigit * pow;
            pow *= 10;
        }

        return res;
    }

    public static int[] inputArray() {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }

        return arr;
    }

    public static void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static int findData(int[] arr, int data) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == data)
                return i;
        }

        return -1;
    }

    public static int maximum(int[] arr) {
        if (arr.length == 0)
            return Integer.MIN_VALUE; // -(int)1e9;
        int maxEle = arr[0];
        for (int ele : arr) {
            maxEle = Math.max(maxEle, ele);
        }

        return maxEle;
    }

    public static int firstIndex(int[] arr, int data) {
        int idx = -1;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == data) {
                idx = i;
                break;
            }

        return idx;
    }

    public static int lastIndex(int[] arr, int data) {
        int idx = -1;
        for (int i = arr.length - 1; i >= 0; i--)
            if (arr[i] == data) {
                idx = i;
                break;
            }

        return idx;
    }

    public static void main(String[] args) {
        int[] arr = inputArray();
        printArray(arr);
    }
}