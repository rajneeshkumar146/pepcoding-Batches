import java.util.Scanner;

public class l001_basic {
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scn.nextInt();
        System.out.println(fact(n));
    }

    public static int fact(int n) {
        if (n <= 1)
            return 1;

        int res = fact(n - 1);
        return res * n;
    }

}