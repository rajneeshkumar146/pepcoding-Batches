public class l001 {

    public static void pattern_01(int rows) {
        int nst = 1, nsp = rows - 1;
        for (int r = 1; r <= rows; r++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            System.out.println();
            nst++;
            nsp--;
        }
    }

    public static void pattern_02(int rows) {
        int nst = 1;
        for (int r = 1; r <= rows; r++) {
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            System.out.println();
            nst++;
        }
    }

    public static void pattern_03(int rows) {
        int nst = rows, nsp = 0;
        for (int r = 1; r <= rows; r++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            System.out.println();
            nst--;
            nsp++;
        }
    }

    public static void pattern_04(int row) {
        int nst = 1, nsp = row - 1;
        for (int r = 1; r <= row; r++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            System.out.print("\t");

            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            System.out.println();

            nst++;
            nsp--;
        }
    }

    public static void pattern_05(int rows) {
        int nst1 = 1, nsp1 = rows - 1, nst2 = rows, nsp2 = 0;
        for (int r = 1; r <= rows; r++) {

            for (int csp = 1; csp <= nsp1; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst1; cst++)
                System.out.print("*\t");
            for (int csp = 1; csp <= nsp2; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst2; cst++)
                System.out.print("*\t");

            System.out.println();
            nst1++;
            nsp1--;
            nst2--;
            nsp2++;
        }

    }

    public static void pattern_06(int rows) {
        int n = rows, nst = 1, nsp = n - 1;
        for (int r = 1; r <= n; r++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            System.out.println();
            nst += 2;
            nsp--;
        }

    }

    // diamond
    public static void pattern_07(int rows) {
        int n = rows, nst = 1, nsp = n / 2;
        for (int r = 1; r <= n; r++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            if (r <= n / 2) {
                nst += 2;
                nsp--;
            } else {
                nst -= 2;
                nsp++;
            }
            System.out.println();
        }

        System.out.println("NST: " + nst);
        System.out.println("NSP: " + nsp);
    }

    public static void pattern_08(int rows) {
        int n = rows, nst = 1, nsp = n / 2;
        for (int r = 1; r <= n; r++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            int count = (r <= n / 2 + 1 ? r : n - r + 1);
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(count + "\t");

                count = cst <= nst / 2 ? count + 1 : count - 1;
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

    public static void pattern_09(int rows) {
        int n = rows, nst = 1, nsp = n / 2;
        for (int r = 1; r <= n; r++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            int count = (r <= n / 2 + 1 ? r : n - r + 1);
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(count + "\t");

                count = cst <= nst / 2 ? count - 1 : count + 1;
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


    public static void main(String[] args) {
        pattern_09(7);
    }

}