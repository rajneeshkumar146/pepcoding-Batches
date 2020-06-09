import java.util.Scanner;

public class l001_basic {
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        // int n = scn.nextInt();
        // System.out.println(fact(n));
        // System.out.println(power(2, 10));
        // System.out.println(power_Btr(2, 10));
        System.out.println(calls(5));
    }

    public static int fact(int n) {
        if (n <= 1)
            return 1;

        int res = fact(n - 1);
        return res * n;
    }

    public static int power(int a, int b) {
        if (b == 0)
            return 1;

        int res = power(a, b - 1);
        return res * a;
    }

    public static int power_Btr(int a, int b) {
        if (b == 0)
            return 1;

        int res = power_Btr(a, b / 2);
        res *= res;
        return ((b & 1) == 0) ? res : res * a;
        // return ((b & 1) == 0) ? res * res : res * res * a;

    }

    public static int calls(int n) {
        if (n <= 1) {
            System.out.println("base: " + n);
            return n + 1;
        }

        int count = 0;

        System.out.println("Pre: " + n);
        count += calls(n - 1);

        System.out.println("In: " + n);

        count += calls(n - 2);
        System.out.println("Post: " + n);

        return count + 3;
    }

    public static int calls_01(int n, int level) {
        if (n <= 1) {
            System.out.println("base: " + n + "@" + level);
            return n + 1;
        }

        int count = 0;
 
        System.out.println("Pre: " + n + "@" + level);
        count += calls_01(n - 1, level + 1);

        System.out.println("In 1: " + n + "@" + level);

        count += calls_01(n - 2, level + 1);

        System.out.println("In 2: " + n + "@" + level);

        count += calls_01(n - 3, level + 1);
        System.out.println("Post: " + n + "@" + level);

        return count + 3;
    }

}