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

    public static void pattern_10_hollowDiamond(int rows) {
        int n = rows, nst = n / 2 + 1, nsp = 1;

        for (int r = 1; r <= n; r++) {
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            System.out.println();
            if (r <= n / 2) {
                nsp += 2;
                nst--;
            } else {
                nsp -= 2;
                nst++;
            }
        }
    }

    public static void pattern_07_pep(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i - j == 0)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }

            System.out.println();
        }
    }

    public static void pattern_08_pep(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + j == n - 1)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }

            System.out.println();
        }
    }

    public static void pattern_09_pep(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + j == n - 1 || i - j == 0)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }

            System.out.println();
        }
    }

    public static void pattern_10_pep(int n) {
        int nsp1 = n / 2, nsp2 = -1;
        for (int r = 1; r <= n; r++) {
            for (int csp = 1; csp <= nsp1; csp++)
                System.out.print("\t");

            System.out.print("*\t");

            for (int csp = 1; csp <= nsp2; csp++)
                System.out.print("\t");

            if (r > 1 && r < n)
                System.out.print("*\t");

            if (r <= n / 2) {
                nsp1--;
                nsp2 += 2;
            } else {
                nsp1++;
                nsp2 -= 2;
            }
            System.out.println();
        }
    }

    public static void pattern11(int rows) {
        int n = rows, nst = 1, count = 1;
        for (int r = 1; r <= n; r++) {
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(count++ + "\t");
            }

            nst++;
            System.out.println();
        }
    }

    public static void pattern12(int rows) {
        int n = rows, nst = 1, a = 0, b = 1;
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

    public static void pattern_13(int rows) {
        int n = rows;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == n - 1 || i - j == 0 || i + j == n - 1) {
                    System.out.print("*\t");
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    public static void pattern_14(int rows) {
        int n = rows, nsp = n / 2, nst = 1;
        for (int r = 1; r <= n; r++) {

            for (int csp = 1; csp <= nsp; csp++)
                System.out.print((r == n / 2 + 1 ? "*\t" : "\t"));

            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            if (r <= n / 2)
                nst++;
            else
                nst--;

            System.out.println();
        }
    }

    public static void pattern_14_02(int rows) {
        int n = rows, nsp = n / 2, nst = 1;
        for (int r = 1; r <= n; r++) {

            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");

            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            if (r < n / 2)
                nst++;
            else if (r == n / 2) {
                nsp -= n / 2;
                nst += n / 2 + 1;
            } else if (r == n / 2 + 1) {
                nsp += n / 2;
                nst -= n / 2 + 1;
            } else
                nst--;

            System.out.println();
        }
    }

    public static void pattern_16_part1(int rows) {
        int n = rows, nst = 1, nsp = 2 * n - 3;

        for (int r = 1; r <= n; r++) {
            int count = 1;
            for (int cst = 1; cst <= nst; cst++)
                System.out.print(count++ + "\t");
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            if (r == n) {
                nst--;
                count--;
            }
            for (int cst = 1; cst <= nst; cst++)
                System.out.print(--count + "\t");

            nsp -= 2;
            nst++;
            System.out.println();
        }
    }

    public static void pattern_014_pep(int num) {
        int n = 10;
        for (int r = 1; r <= n; r++) {
            System.out.println(num + " * " + r + " = " + (num * r));
        }
    }

    public static void tableInRange(int a, int b) {

        for (int i = a; i <= b; i++) {
            pattern_014_pep(i);
            System.out.println("-----------------------");
        }
    }

    public static void pattern_013(int rows) {
        for (int n = 0; n < rows; n++) {
            int val = 1;
            for (int r = 0; r <= n; r++) {
                System.out.print(val + "\t");
                val = (val * (n - r)) / (r + 1);
            }
            System.out.println();
        }
    }

    public static void pattern_Butterfly_Part_01(int rows) {
        int n = rows, nst = 1, nsp = 2 * n - 2;
        for (int r = 1; r <= n; r++) {
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            nsp -= 2;
            nst++;
            System.out.println();
        }
    }

    public static void pattern_Butterfly_Part_02(int rows) {
        int n = rows, nst = n, nsp = 0;
        for (int r = 1; r <= n; r++) {
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            nsp += 2;
            nst--;
            System.out.println();
        }
    }

    public static void butterfly(int n) {
        pattern_Butterfly_Part_01(n / 2);
        pattern_Butterfly_Part_02(n / 2);
    }

    public static void pattern_014_rom(int rows) {
        int n = rows, nst = n, nsp = n - 1;
        for (int r = 1; r <= n; r++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");

            System.out.println();
            nsp--;
        }
    }

    public static void pattern_015_hollowRom(int row) {
        int n = row, nsp1 = n - 1, nsp2 = n - 2;
        for (int r = 1; r <= n; r++) {
            for (int csp = 1; csp <= nsp1; csp++)
                System.out.print("\t");
            System.out.print("*\t");
            for (int csp = 1; csp <= nsp2; csp++)
                System.out.print((r == 1 || r == n) ? "*\t" : "\t");
            System.out.print("*\t");

            nsp1--;
            System.out.println();
        }
    }

    public static void pattern_016(int rows) {
        int n = rows, nsp = n / 2;

        for (int r = 1; r <= n; r++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print((r == n / 2 + 1) ? "*\t" : "\t");

            System.out.print("*\t");

            if (r <= n / 2)
                nsp++;
            else
                nsp--;

            System.out.println();
        }
    }

    public static void heartShape(int n) {
        int nst = n;
        int nsp = n % 2 == 0 ? n - 1 : n;

        for (int r = 1; r <= n; r += 2) {

            for (int csp = 1; csp <= nsp / 2; csp++)
                System.out.print(" ");

            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*");

            for (int csp = 1; csp <= nsp; csp++)
                System.out.print(" ");

            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*");

            System.out.println();
            nst += 2;
            nsp -= 2;
        }

        nst = 2 * nst - 3;
        nsp = 0;
        while (nst > 0) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print(" ");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*");

            nst -= 2;
            nsp++;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        heartShape(10);
    }

}