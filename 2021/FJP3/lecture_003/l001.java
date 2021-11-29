import java.util.Scanner;

public class l001 {
    public static int getDigitFrequency(int n, int d) {
        int occu = 0;
        while (n != 0) {
            int ld = n % 10;
            n /= 10;
            if (ld == d)
                occu++;
        }

        return occu;
    }

    public static int decimalToBinary(int n) {
        int ans = 0, pow = 1;
        while (n != 0) {
            int rem = n % 2;
            n /= 2;

            ans += rem * pow;
            pow = pow * 10;
        }

        return ans;
    }

    public static int binaryToDecimal(int n) {
        int ans = 0, pow = 1;
        while (n != 0) {
            int rem = n % 10;
            n /= 10;

            ans += rem * pow;
            pow = pow * 2;
        }

        return ans;
    }

    // 1 < b <= 10
    public static int decimalToanyBase(int n, int b) {
        int pow = 1, ans = 0;
        while (n != 0) {
            int rem = n % b;
            n /= b;

            ans += rem * pow;
            pow *= 10;
        }

        return ans;
    }

    // 1 < b <= 10
    public static int anyBaseToDecimal(int n, int b) {
        int pow = 1, ans = 0;
        while (n != 0) {
            int rem = n % 10;
            n /= 10;

            ans += rem * pow;
            pow *= b;
        }

        return ans;
    }

    public static int anyBaseToanyBase(int n, int b1, int b2) {
        int decimal = anyBaseToDecimal(n, b1);
        int ans = decimalToanyBase(decimal, b2);

        return ans;
    }

    public static int decimalAddition(int n, int m) {
        int ans = 0, pow = 1, carry = 0;

        while (n != 0 || m != 0 || carry != 0) {
            int sum = carry + n % 10 + m % 10;
            n /= 10;
            m /= 10;
            carry = sum / 10;
            sum %= 10;

            ans += sum * pow;
            pow *= 10;
        }

        return ans;
    }

    public static int anyBaseAddition(int n, int m, int b) {
        int ans = 0, pow = 1, carry = 0;

        while (n != 0 || m != 0 || carry != 0) {
            int sum = carry + n % 10 + m % 10;
            n /= 10;
            m /= 10;

            // carry = sum / 2;
            // sum %= 2;

            carry = sum / b;
            sum %= b;

            ans += sum * pow;
            pow *= 10;
        }

        return ans;
    }

    // m - n = ??
    public static int decimalSubtraction(int n, int m) {
        int ans = 0, pow = 1, borrow = 0;
        while (m != 0) {
            int sub = borrow + m % 10 - n % 10;
            n /= 10;
            m /= 10;

            if (sub < 0) {
                sub += 10;
                borrow = -1;
            } else
                borrow = 0;

            ans += sub * pow;
            pow *= 10;
        }

        return ans;
    }

    public static int anyBaseSubtraction(int n, int m, int base) {
        int ans = 0, pow = 1, borrow = 0;
        while (m != 0) {
            int sub = borrow + m % 10 - n % 10;
            n /= 10;
            m /= 10;

            if (sub < 0) {
                sub += base;
                borrow = -1;
            } else
                borrow = 0;

            ans += sub * pow;
            pow *= 10;
        }

        return ans;
    }

    public static int multiplyDigitWithNumber(int n, int digit, int base) {
        int ans = 0, carry = 0, pow = 1;
        while (n != 0 || carry != 0) {
            int prod = carry + (n % 10) * digit;
            n /= 10;

            carry = prod / base;
            prod %= base;

            ans += prod * pow;
            pow *= 10;
        }

        return ans;
    }

    public static int getProduct(int b, int n, int m) {
        int ans = 0, pow = 1;
        while (m != 0) {
            int digitMultiply = multiplyDigitWithNumber(n, m % 10, b);
            m /= 10;

            ans = anyBaseAddition(ans, digitMultiply * pow, b);
            pow *= 10;
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        // int n = scn.nextInt();

        // int binary = decimalToBinary(n);
        // System.out.println(binary);

        // int decimal = binaryToDecimal(binary);
        // System.out.println(decimal);

        System.out.println(decimalAddition(scn.nextInt(), scn.nextInt()));
    }

}