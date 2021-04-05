import java.util.*;

public class AbAddition {
    public static Scanner scn = new Scanner(System.in);

    public static int anyBaseAddition(int n, int m, int b) {
        int pow = 1;
        int ans = 0, carry = 0;
        while (n != 0 || m != 0 || carry != 0) {
            int sum = carry + n % 10 + m % 10;
            n /= 10;
            m /= 10;

            int digit = sum % b;
            carry = sum / b;

            ans += digit * pow;
            pow *= 10;
        }

        return ans;
    }

    // m > n
    public static int anyBaseSubtraction(int n, int m, int b) {
        int pow = 1, res = 0, borrow = 0;
        while (m != 0) {
            int diff = borrow + m % 10 - n % 10;
            n /= 10;
            m /= 10;

            if (diff < 0) {
                diff += b;
                borrow = -1;
            } else
                borrow = 0;

            res += diff * pow;
            pow *= 10;
        }

        return res;
    }

    public static int multiplyNumberWithDigit(int n, int d, int b) {
        int ans = 0, pow = 1, carry = 0;
        while (n != 0 || carry != 0) {
            int prod = carry + (n % 10) * d;
            n /= 10;

            int digit = prod % b;
            carry = prod / b;

            ans += digit * pow;
            pow *= 10;
        }

        return ans;
    }

    public static int anyBaseMultiplication(int n, int m, int b) {
        int ans = 0, pow = 1;
        while (m != 0) {
            int d = m % 10;
            m /= 10;
            int prodRes = multiplyNumberWithDigit(n, d, b) * pow;
            ans = anyBaseAddition(ans, prodRes, b);
            pow *= 10;
        }

        return ans;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        int b = scn.nextInt();

        System.out.println(anyBaseMultiplication(n, m, b));
    }

}