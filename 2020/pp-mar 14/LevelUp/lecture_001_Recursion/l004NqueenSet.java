public class l004NqueenSet {

    public static boolean isSafeToPlaceQueen(boolean[][] box, int sr, int sc) {
        // int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };

        int n = box.length, m = box[0].length;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= n; rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];
                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (box[r][c])
                        return false;
                } else
                    break;
            }
        }

        return true;
    }

    public static int nQueen_01(boolean[][] box, int bno, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        int n = box.length, m = box[0].length;
        for (int b = bno; b < n * m; b++) {
            int r = b / m;
            int c = b % m;

            if (isSafeToPlaceQueen(box, r, c)) {
                box[r][c] = true;
                count += nQueen_01(box, b + 1, tnq - 1, asf + "(" + r + "," + c + ") ");
                box[r][c] = false;
            }
        }
        return count;
    }

    // Permutation
    public static int nQueen_02(boolean[][] box, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        int n = box.length, m = box[0].length;
        for (int b = 0; b < n * m; b++) {
            int r = b / m;
            int c = b % m;

            if (!box[r][c] && isSafeToPlaceQueen(box, r, c)) {
                box[r][c] = true;
                count += nQueen_02(box, tnq - 1, asf + "(" + r + "," + c + ") ");
                box[r][c] = false;
            }
        }
        return count;
    }

    static boolean[] row, col, diag, adiag;
    static int call = 0;

    // isSafe optimization
    public static int nQueen_03(int n, int m, int bno, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        call++;
        int count = 0;
        for (int b = bno; b < n * m; b++) {
            int r = b / m;
            int c = b % m;

            if (!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = true;

                count += nQueen_03(n, m, b + 1, tnq - 1, asf + "(" + r + "," + c + ") ");

                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    // isSafe optimization, permutation
    public static int nQueen_04(int n, int m, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int b = 0; b < n * m; b++) {
            int r = b / m;
            int c = b % m;

            if (!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = true;

                count += nQueen_04(n, m, tnq - 1, asf + "(" + r + "," + c + ") ");

                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    // Combination_optimized
    public static int nQueen_05(int n, int m, int floor, int tnq, String asf) {
        // if (tnq == 0 || floor >= n) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        // return 0;
        // }

        call++;
        int count = 0;

        for (int room = 0; room < m; room++) {
            int r = floor, c = room;
            if (!col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nQueen_05(n, m, floor + 1, tnq - 1, asf + "(" + r + "," + c + ") ");
                col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }

        // count += nQueen_05(n, m, floor + 1, tnq, asf);
        return count;
    }

    // permutation_optimized
    public static int nQueen_06(int n, int m, int floor, int tnq, String asf) {
        if (tnq == 0 || floor >= n) {
            if (tnq == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        call++;
        int count = 0;

        for (int room = 0; room < m; room++) {
            int r = floor, c = room;
            if (!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nQueen_06(n, m, 0, tnq - 1, asf + "(" + r + "," + c + ") ");
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }

        count += nQueen_06(n, m, floor + 1, tnq, asf);
        return count;
    }

    static int r = 0, c = 0, d = 0, ad = 0;

    public static int nQueen_07(int n, int m, int floor, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int room = 0; room < m; room++) {
            int r = floor, c = room;
            if ((c & (1 << c)) == 0 && (d & (1 << (r + c))) == 0 && (ad & (1 << (r - c + m - 1))) == 0) {
                c ^= (1 << c);
                d ^= (1 << (r + c));
                ad ^= (1 << (r - c + m - 1));
                count += nQueen_07(n, m, floor + 1, tnq - 1, asf + "(" + r + "," + c + ") ");
                c ^= (1 << c);
                d ^= (1 << (r + c));
                ad ^= (1 << (r - c + m - 1));
            }
        }

        return count;
    }

    public static void nqueen() {
        int n = 4, tnq = 4;
        // boolean[][] box = new boolean[n][n];
        // System.out.println(nQueen_01(box, 0, tnq, ""));
        // System.out.println(nQueen_02(box, tnq, ""));
        row = new boolean[n];
        col = new boolean[n];
        diag = new boolean[n + n - 1];
        adiag = new boolean[n + n - 1];
        // System.out.println(nQueen_03(n, n, 0, tnq, ""));
        System.out.println(nQueen_06(n, n, 0, tnq, ""));

        System.out.println(call);
    }

    public static void main(String[] args) {
        nqueen();

    }

}