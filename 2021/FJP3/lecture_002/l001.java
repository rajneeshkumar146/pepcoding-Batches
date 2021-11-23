import java.util.Scanner;

public class l001 {

    // nsp : no of spaces, nst = no of stars
    // csp = count of space, cst = count of star
    public static void pattern0(int n) {
        int nsp = n, nst = n;
        for (int r = 1; r <= n; r++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            nsp += 0;
            nst += 0;
            System.out.println();
        }

    }

    public static void pattern1(int n) {
        int nsp = 0, nst = 1;
        for (int r = 1; r <= n; r++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            nst++;
            System.out.println();
        }

    }

    public static void pattern2(int n) {
        int nsp = 0, nst = n;
        for (int r = 1; r <= n; r++) {

            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            nst--;
            System.out.println();
        }

    }

    public static void pattern3(int n) {
        int nsp = n - 1, nst = 1;
        for (int r = 1; r <= n; r++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            nsp--;
            nst++;
            System.out.println();
        }
    }

    public static void pattern4(int n) {
        int nsp = 0, nst = n;
        for (int r = 1; r <= n; r++) {

            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            nsp++;
            nst--;
            System.out.println();
        }

    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        pattern4(n);
    }
}