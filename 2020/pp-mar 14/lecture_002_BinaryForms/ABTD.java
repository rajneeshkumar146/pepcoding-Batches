import java.util.*;

public class ABTD {
    // decimal to Binary

    public static Scanner scn = new Scanner(System.in);

    public static int anyBaseToDecimal(int n,int b) {
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

    public static void main(String[] args) {

    }

}