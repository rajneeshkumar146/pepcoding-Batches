import java.util.*;

public class ABTAB {
    // decimal to Binary

    public static Scanner scn = new Scanner(System.in);

    public static int anyBaseToDecimal(int n, int b) {
        int pow = 1;
        int ans = 0;
        while (n != 0) {
            int rem = n % 10;
            n /= 10;

            ans += rem * pow;
            pow *= b;
        }

        return ans;
    }

    public static int decimalToAnyBase(int n, int b) {
        int pow = 1;
        int ans = 0;
        while (n != 0) {
            int rem = n % b;
            n /= b;

            ans += rem * pow;
            pow *= 10;
        }

        return ans;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int b1 = scn.nextInt();
        int b2 = scn.nextInt();

        int decimalNumber = anyBaseToDecimal(n,b1);
        System.out.println(decimalToAnyBase(decimalNumber, b2));
    }

}