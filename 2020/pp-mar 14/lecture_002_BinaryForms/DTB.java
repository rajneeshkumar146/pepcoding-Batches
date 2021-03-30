import java.util.*;

public class DTB {
    // decimal to Binary

    public static Scanner scn = new Scanner(System.in);

    public static int decimalToBinary(int n) {
        int pow = 1;
        int ans = 0;
        while (n != 0) {
            int rem = n % 2;
            n /= 2;

            ans += rem * pow;
            pow *= 10;
        }

        return ans;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        System.out.println(decimalToBinary(n));
    }

}