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

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int binary = decimalToBinary(n);
        System.out.println(binary);

        int decimal = binaryToDecimal(binary);
        System.out.println(decimal);
    }

}