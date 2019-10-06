import java.util.Scanner;

public class l001 {

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Please entre n for factorial: ");
        int n = scn.nextInt();
        int r = scn.nextInt();

        System.out.println(facto(n));
        System.out.println(ncr(n, r));
    }

    public static int ncr(int n, int r) {
        // System.out.println("Please entre n and r: ");
        // int n = scn.nextInt();
        // int r = scn.nextInt();

        int ans = facto(n) / (facto(n - r) * facto(r));

        // System.out.println(ans);
        return ans;
    }

    public static int facto(int n) {
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            ans *= i;
        }
        return ans;
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i*i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;

    }

}