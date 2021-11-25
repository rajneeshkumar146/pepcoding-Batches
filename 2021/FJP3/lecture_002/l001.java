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

    public static void extraPattern_01(int n) {
        int nsp = n - 1, nst = 1;
        for (int r = 1; r <= n; r++) {

            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            nsp--;
            nst += 2;
            System.out.println();
        }
    }

    public static void extraPattern_02(int n) {
        int nsp = 0, nst = 2 * n - 1;
        for (int r = 1; r <= n; r++) {

            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            nsp++;
            nst -= 2;
            System.out.println();
        }

    }

    public static void pattern5_diamond(int n) {
        if (n % 2 == 0)
            n++;
        int nsp = n / 2, nst = 1;
        for (int r = 1; r <= n; r++) {

            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            if (r <= n / 2) {
                nsp--;
                nst += 2;
            } else {
                nsp++;
                nst -= 2;
            }
            System.out.println();
        }
    }

    public static void pattern6_HollowDiamond(int n) {
        if (n % 2 == 0)
            n++;
        int nsp = 1, nst = (n / 2 + 1);
        for (int r = 1; r <= n; r++) {

            // stars
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            // spaces
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            // stars
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            if (r <= n / 2) {
                nst--;
                nsp += 2;
            } else {
                nst++;
                nsp -= 2;
            }
            System.out.println();
        }
    }

    public static void printMatrix(int rows) {
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= rows; c++) {
                System.out.print(r + "" + c + " ");
            }
            System.out.println();
        }
    }

    public static void pattern7(int rows) {
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= rows; c++) {
                if (r == c)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void pattern8(int rows) {
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= rows; c++) {
                if (r + c == rows + 1)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void pattern9(int rows) {
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= rows; c++) {
                if (r == c || r + c == rows + 1)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void pattern9_01(int n) {
        int nsp1 = 0, nsp2 = n - 2;
        for (int r = 1; r <= n; r++) {
            for (int csp1 = 1; csp1 <= nsp1; csp1++)
                System.out.print("\t");

            System.out.print("*\t");

            for (int csp2 = 1; csp2 <= nsp2; csp2++)
                System.out.print("\t");

            if (r != n / 2 + 1)
                System.out.print("*\t");

            if (r <= n / 2) {
                nsp1++;
                nsp2 -= 2;
            } else {
                nsp1--;
                nsp2 += 2;
            }

            System.out.println();
        }
    }

    public static void extraPattern_03(int n) {
        int nst = 1;
        for (int r = 1; r <= n; r++) {
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(nst + "\t");
            }
            System.out.println();
            nst++;
        }

    }

    public static void pattern11(int n) {
        int nst = 1, count = 1;
        for (int r = 1; r <= n; r++) {
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(count++ + "\t");
            }
            System.out.println();
            nst++;
        }

    }

    public static void extraPattern_04_diamond(int n) {
        int nst = 1, nsp = n / 2;
        for (int r = 1; r <= n; r++) {

            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }

            int count = (r <= n / 2 + 1 ? r : n - r + 1);

            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(count + "\t");
            }

            if (r <= n / 2) {
                nsp--;
                nst += 2;
            } else {
                nsp++;
                nst -= 2;
            }

            System.out.println();
        }
    }

    public static void pattern12(int n) {

        // int a = 0, b = 1;
        // for (int i = 0; i <= n; i++) {
        // System.out.print(a + " ");
        // int sum = a + b;
        // a = b;
        // b = sum;
        // }

        int nst = 1, a = 0, b = 1;
        for (int r = 1; r <= n; r++) {
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(a + "\t");

                int sum = a + b;
                a = b;
                b = sum;
            }

            nst++;
            System.out.println();

        }

    }

    public static void pattern13(int rows) {
        for (int n = 0; n <= rows; n++) {
            int val = 1;
            for (int r = 0; r <= n; r++) {
                // System.out.print(n + "C" + r + "\t");
                System.out.print(val + "\t");
                val = ((n - r) * val) / (r + 1);
            }

            System.out.println();
        }
    }

    public static void pattern14(int n) {
        for (int r = 1; r <= 10; r++) {
            System.out.println(n + " * " + r + " = " + (n * r));
        }
    }

    public static void pattern16(int n) {
        int nst = 1, nsp = 2 * n - 3;

        for (int r = 1; r <= n; r++) {
            int count = 1;
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(count++ + "\t");
            }

            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }

            if (r == n) {
                nst--;
                count--;
            }

            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(--count + "\t");
            }

            nst++;
            nsp -= 2;
            System.out.println();
        }

    }

    public static void pattern15(int n) {
        int nst = 1, nsp = n / 2;
        for (int r = 1; r <= n; r++) {

            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            int count = (r <= n / 2 + 1) ? r : n - r + 1;

            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(count + "\t");
                count = (cst <= nst / 2) ? count + 1 : count - 1;
            }

            if (r <= n / 2) {
                nst += 2;
                nsp--;
            } else {
                nst -= 2;
                nsp++;
            }
            System.out.println();
        }
    }

    public static void pattern15_extra(int n) {
        int nst = 1, nsp = n / 2;
        for (int r = 1; r <= n; r++) {

            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            int count = (r <= n / 2 + 1) ? r : n - r + 1;

            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(count + "\t");
                count = (cst <= nst / 2) ? count - 1 : count + 1;
            }

            if (r <= n / 2) {
                nst += 2;
                nsp--;
            } else {
                nst -= 2;
                nsp++;
            }
            System.out.println();
        }
    }

    public static void pattern10(int rows) {
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= rows; c++) {
                // ????
                if (r + c == rows + 1)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        pattern13(n);
    }
}