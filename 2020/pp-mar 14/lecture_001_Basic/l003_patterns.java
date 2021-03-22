import java.util.*;

public class l003_patterns {
    public static Scanner scn = new Scanner(System.in);

    public static void squarePrint(int n) {
        int nst = n;

        for (int row = 1; row <= n; row++) {
            for (int cst = 1; cst <= nst; cst++) // cst is count of star, nst = number of star
                System.out.print("*\t");

            nst = n;
            System.out.println();
        }
    }

    public static void trainglePrint(int n) {
        int nst = 1;
        for (int row = 1; row <= n; row++) {
            for (int cst = 1; cst <= nst; cst++) // cst is count of star, nst = number of star
                System.out.print("*\t");

            nst++;
            System.out.println();
        }
    }

    public static void invertedTrainglePrint(int n) {
        int nst = n;
        for (int row = 1; row <= n; row++) {
            for (int cst = 1; cst <= nst; cst++) // cst is count of star, nst = number of star
                System.out.print("*\t");

            nst--;
            System.out.println();
        }
    }

    public static void mirrorTraingle(int n) {
        int nst = 1;
        int nsp = n - 1;
        for (int row = 1; row <= n; row++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");
            nst++;
            nsp--;
            System.out.println();
        }
    }

    public static void halfDiamond(int n) {
        int nst = 1, nsp = n - 1;
        for (int row = 1; row <= n; row++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            nsp--;
            nst += 2;
            System.out.println();
        }
    }

    public static void fullDiamond(int n) {
        int nst = 1, nsp = n / 2;
        for (int row = 1; row <= n; row++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            if (row <= n / 2) {
                nsp--;
                nst += 2;
            } else {
                nsp++;
                nst -= 2;
            }
            System.out.println();
        }
    }

    public static void hollowDiamond(int n) {
        int nst = (n / 2) + 1, nsp = 1;
        for (int row = 1; row <= n; row++) {
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            if (row <= n / 2) {
                nsp += 2;
                nst--;
            } else {
                nsp -= 2;
                nst++;
            }
            System.out.println();
        }
    }

    public static void numberDiamond(int n) {
        int nst = 1, nsp = n / 2;
        for (int row = 1; row <= n; row++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            int val = row;
            if (row > n / 2 + 1)
                val = n - row + 1;

            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(val + "\t");
                if (cst <= nst / 2)
                    val++;
                else
                    val--;
            }

            if (row <= n / 2) {
                nsp--;
                nst += 2;
            } else {
                nsp++;
                nst -= 2;
            }
            System.out.println();
        }
    }

    // pattern 7
    public static void backwardSlash(int n) {

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if (i == j)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println();
        }
    }

    //pattern 8
    public static void forwardSlash(int n) {

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i + j == n + 1)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        forwardSlash(n);
    }
}