public class l002RecursionTrees {
    public static int permutationWithInfi(int[] arr, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0) {
                count += permutationWithInfi(arr, tar - ele, ans + ele);
            }
        }
        return count;
    }

    public static int combinationWithInfi(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationWithInfi(arr, tar - arr[i], i, ans + arr[i]);
        }
        return count;
    }

    public static int combinationWithSingle(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationWithSingle(arr, tar - arr[i], i + 1, ans + arr[i]);
        }

        return count;
    }

    public static int permutationWithSingleCoin(int[] arr, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0 && tar - arr[i] >= 0) {
                int val = arr[i];

                arr[i] = -arr[i];
                count += permutationWithSingleCoin(arr, tar - val, ans + val);
                arr[i] = -arr[i];
            }
        }

        return count;
    }

    public static int permutationWithSingleCoin(int[] arr, boolean[] vis, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!vis[i] && tar - arr[i] >= 0) {
                vis[i] = true;
                count += permutationWithSingleCoin(arr, vis, tar - arr[i], ans + arr[i]);
                vis[i] = false;
            }
        }

        return count;
    }

    // ====================================================================================================

    public static int permutationWithInfi_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += permutationWithInfi_subSeq(arr, tar - arr[idx], 0, ans + arr[idx]);
        count += permutationWithInfi_subSeq(arr, tar, idx + 1, ans);

        return count;
    }

    public static int combinationWithInfi_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += combinationWithInfi_subSeq(arr, tar - arr[idx], idx, ans + arr[idx]);
        count += combinationWithInfi_subSeq(arr, tar, idx + 1, ans);

        return count;
    }

    public static int combinationWithSingle_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += combinationWithSingle_subSeq(arr, tar - arr[idx], idx + 1, ans + arr[idx]);
        count += combinationWithSingle_subSeq(arr, tar, idx + 1, ans);

        return count;
    }

    public static int permutationWithSingleCoin_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (arr[idx] > 0 && tar - arr[idx] >= 0) {
            int val = arr[idx];
            arr[idx] = -arr[idx];
            count += permutationWithSingleCoin_subSeq(arr, tar - val, 0, ans + val);
            arr[idx] = -arr[idx];
        }
        count += permutationWithSingleCoin_subSeq(arr, tar, idx + 1, ans);
        return count;
    }

    // 1D_Queen_Set=================================================================================

    // tboxes = total Bpxes, tqn = total queen, qpsf = queen placed so far, bn =
    // box_no,
    public static int queenCombination(int tboxe, int tqn, int qpsf, int bn, String ans) {
        if (qpsf == tqn) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = bn; i < tboxe; i++) {
            count += queenCombination(tboxe, tqn, qpsf + 1, i + 1, ans + "b" + i + "q" + qpsf + " ");
        }
        return count;
    }

    public static int queenPermutation(boolean[] tboxe, int tqn, int qpsf, int bn, String ans) {
        if (qpsf == tqn) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = bn; i < tboxe.length; i++) {
            if (!tboxe[i]) {
                tboxe[i] = true;
                count += queenPermutation(tboxe, tqn, qpsf + 1, 0, ans + "b" + i + "q" + qpsf + " ");
                tboxe[i] = false;
            }
        }
        return count;
    }

    // 2D_Queen_Set=================================================================================

    // tboxes = total Bpxes, tqn = total queen, qpsf = queen placed so far, bn =
    // box_no,
    public static int queenCombination2D(boolean[][] boxes, int tqn, int bn, String ans) {
        if (tqn == 0) {
            System.out.println(ans);
            return 1;
        }

        int n = boxes.length, m = boxes[0].length, count = 0;
        for (int i = bn; i < n * m; i++) {
            int r = i / m;
            int c = i % m;

            boxes[r][c] = true;
            count += queenCombination2D(boxes, tqn - 1, i + 1, ans + "(" + r + ", " + c + ") ");
            boxes[r][c] = false;
        }

        return count;
    }

    public static int queenPermutation2D(boolean[][] boxes, int tqn, int bn, String ans) {
        if (tqn == 0) {
            System.out.println(ans);
            return 1;
        }

        int n = boxes.length, m = boxes[0].length, count = 0;
        for (int i = bn; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!boxes[r][c]) {
                boxes[r][c] = true;
                count += queenPermutation2D(boxes, tqn - 1, 0, ans + "(" + r + ", " + c + ") ");
                boxes[r][c] = false;
            }
        }

        return count;
    }

    // Nqueen Series.===================================================

    public static boolean isSafeToPlaceQueen(boolean[][] boxes, int r, int c) {
        // int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };

        int n = boxes.length, m = boxes[0].length;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad < n; rad++) {
                int x = r + rad * dir[d][0];
                int y = c + rad * dir[d][1];
                if (x >= 0 && y >= 0 && x < n && y < m) {
                    if (boxes[x][y])
                        return false;
                } else
                    break;
            }
        }

        return true;
    }

    public static int nqueen_Combination01(boolean[][] boxes, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int n = boxes.length, m = boxes[0].length, count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (isSafeToPlaceQueen(boxes, r, c)) {
                boxes[r][c] = true;
                count += nqueen_Combination01(boxes, tnq - 1, i + 1, ans + "(" + r + ", " + c + ") ");
                boxes[r][c] = false;
            }
        }

        return count;
    }

    public static int nqueen_Permutation01(boolean[][] boxes, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int n = boxes.length, m = boxes[0].length, count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!boxes[r][c] && isSafeToPlaceQueen(boxes, r, c)) {
                boxes[r][c] = true;
                count += nqueen_Permutation01(boxes, tnq - 1, 0, ans + "(" + r + ", " + c + ") ");
                boxes[r][c] = false;
            }
        }

        return count;
    }

    public static int nqueen_Combination02(boolean[][] boxes, int tnq, int idx, String ans) {
        int n = boxes.length, m = boxes[0].length, count = 0;

        if (tnq == 0 || idx >= n * m) {
            if (tnq == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int r = idx / m;
        int c = idx % m;
        if (isSafeToPlaceQueen(boxes, r, c)) {
            boxes[r][c] = true;
            count += nqueen_Combination02(boxes, tnq - 1, idx + 1, ans + "(" + r + ", " + c + ") ");
            boxes[r][c] = false;
        }
        count += nqueen_Combination02(boxes, tnq, idx + 1, ans);

        return count;
    }

    static boolean[] rows;
    static boolean[] cols;
    static boolean[] diag;
    static boolean[] adiag;

    static int calls = 0;

    public static int nqueen_Combination03(int n, int m, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        calls++;
        int count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nqueen_Combination03(n, m, tnq - 1, i + 1, ans + "(" + r + ", " + c + ") ");
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }

        return count;
    }

    public static int nqueen_Permutation03(int n, int m, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nqueen_Permutation03(n, m, tnq - 1, 0, ans + "(" + r + ", " + c + ") ");
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }

        return count;
    }

    public static int Nqueen04(int floor, int tnq, int m, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        calls++;
        int count = 0;
        for (int room = 0; room < m; room++) {
            int r = floor, c = room;
            if (!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;

                count += Nqueen04(floor + 1, tnq - 1, m, ans + "(" + r + ", " + c + ") ");

                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }

        return count;
    }

    public static int Nqueen04_perm(int floor, int tnq, int m, String ans) {
        if (tnq == 0 || floor >= m) {
            if (tnq == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        calls++;
        int count = 0;
        for (int room = 0; room < m; room++) {
            int r = floor, c = room;
            if (!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;

                count += Nqueen04(0, tnq - 1, m, ans + "(" + r + ", " + c + ") ");

                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }

        count += Nqueen04(floor + 1, tnq, m, ans);

        return count;
    }

    public static void Nqueen() {
        int n = 4, m = 4, q = 4;
        boolean[][] boxes = new boolean[n][m];
        // System.out.println(nqueen_Combination01(boxes, q, 0, ""));
        // System.out.println(nqueen_Permutation01(boxes, q, 0, ""));
        // System.out.println(nqueen_Combination02(boxes, q, 0, ""));

        rows = new boolean[n];
        cols = new boolean[m];
        diag = new boolean[n + m - 1];
        adiag = new boolean[n + m - 1];

        // System.out.println(nqueen_Combination03(n, m, q, 0, ""));
        System.out.println(Nqueen04(0, q, m, ""));
        System.out.println(calls);
    }

    public static void coinChange() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;

        // System.out.println(permutationWithInfi(arr, tar, ""));
        // System.out.println(coFmbinationWithInfi(arr, tar,0, ""));
        // System.out.println(combinationWithSingle(arr, tar, 0, ""));
        // System.out.println(permutationWithSingleCoin(arr, tar, ""));

        // System.out.println(permutationWithInfi_subSeq(arr, tar, 0, ""));
        // System.out.println(combinationWithInfi_subSeq(arr, tar, 0, ""));
        // System.out.println(combinationWithSingle_subSeq(arr, tar, 0, ""));
        // System.out.println(permutationWithSingleCoin_subSeq(arr, tar, 0, ""));
    }

    public static void queenSet() {
        // boolean[] boxes = new boolean[6];
        // System.out.println(queenCombination(16, 4, 0, 0, ""));
        // System.out.println(queenPermutation(boxes, 4, 0, 0, ""));

        // boolean[][] boxes = new boolean[4][4];
        // System.out.println(queenCombination2D(boxes, 4, 0, ""));
        // System.out.println(queenPermutation2D(boxes, 4, 0, ""));
    }

    public static void main(String[] args) {
        // queenSet();
        Nqueen();
    }
}