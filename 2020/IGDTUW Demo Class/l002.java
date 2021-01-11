public class l002 {
    public static int decimalToBinary(int n) {
        int ans = 0;
        int pow = 1;
        while (n != 0) {
            int rem = n % 2;
            n /= 2;

            ans = ans + rem * pow;
            pow *= 10;
        }

        return ans;
    }

    public static int decimalToAnyBase(int n, int b) {
        int ans = 0;
        int pow = 1;
        while (n != 0) {
            int rem = n % b;
            n /= b;

            ans = ans + rem * pow;
            pow *= 10;
        }

        return ans;
    }

    public static int binaryToDecimal(int n) {
        int ans = 0;
        int pow = 1;
        while (n != 0) {
            int rem = n % 10;
            n /= 10;

            ans = ans + rem * pow;
            pow *= 2;
        }

        return ans;
    }

    public static int anyBaseToDecimal(int n, int b) {
        int ans = 0;
        int pow = 1;
        while (n != 0) {
            int rem = n % 10;
            n /= 10;

            ans = ans + rem * pow;
            pow *= b;
        }

        return ans;
    }

    public static int anyBaseToAnyBase(int n, int b1, int b2) {
        int decimalNumber = anyBaseToDecimal(n, b1);
        return decimalToAnyBase(decimalNumber, b2);
    }

    public static int binaryAddition(int n, int m) {
        int res = 0;
        int pow = 1;
        int carry = 0;
        while (n != 0 || m != 0 || carry != 0) {
            int sum = carry + n % 10 + m % 10;
            n /= 10;
            m /= 10;

            int rem = sum % 2;
            carry = sum / 2;

            res = res + rem * pow;
            pow *= 10;
        }

        return res;
    }

    public static int anyBaseAddition(int n, int m,int b) {
        int res = 0;
        int pow = 1;
        int carry = 0;
        while (n != 0 || m != 0 || carry != 0) {
            int sum = carry + n % 10 + m % 10;
            n /= 10;
            m /= 10;

            int rem = sum % b;
            carry = sum / b;

            res = res + rem * pow;
            pow *= 10;
        }

        return res;
    }

    public static void main(String[] args) {

    }
}