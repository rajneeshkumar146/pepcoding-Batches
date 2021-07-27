public class l004NqueenSet {

    public static boolean isSafeToPlaceQueen(boolean[][] box, int sr, int sc) {
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };

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

    public static void nqueen() {
        int n = 4, tnq = 4;
        boolean[][] box = new boolean[n][n];
        System.out.println(nQueen_01(box, 0, tnq, ""));
    }

    public static void main(String[] args) {
        nqueen();

    }

}