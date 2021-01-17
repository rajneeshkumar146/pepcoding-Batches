import java.util.ArrayList;
import java.util.List;

public class l003CoinPermutation {
    List<Integer> smallAns = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    public void combinationSum(int[] arr, int idx, int target) {
        if (idx == arr.length || target == 0) {
            if (target == 0) {
                List<Integer> base = new ArrayList<>(smallAns);
                res.add(base);
            }
            return;
        }

        if (target - arr[idx] >= 0) {
            smallAns.add(arr[idx]);
            combinationSum(arr, idx, target - arr[idx]);
            smallAns.remove(smallAns.size() - 1);
        }
        combinationSum(arr, idx + 1, target);
    }

    public List<List<Integer>> combinationSum(int[] arr, int target) {
        combinationSum(arr, 0, target);
        return res;

    }

    // 46
    public void permute(int[] nums, int count, boolean[] vis) {
        if (count == nums.length) {

            res.add(new ArrayList<>(smallAns));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!vis[i]) {
                vis[i] = true;
                smallAns.add(nums[i]);
                permute(nums, count + 1, vis);
                smallAns.remove(smallAns.size() - 1);
                vis[i] = false;
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        boolean[] vis = new boolean[nums.length];
        permute(nums, 0, vis);
        return res;
    }

    // Queen

    // tnq = total no of queens, qpsf = queen placed so far
    // tnb = total no of boxes, bno : box no
    public static int queenCombination1D(int tnb, int bno, int tnq, int qpsf, String ans) {
        if (qpsf == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int b = bno; b < tnb; b++) {
            count += queenCombination1D(tnb, b + 1, tnq, qpsf + 1, ans + "b" + b + "q" + qpsf + " ");
        }

        return count;

    }

    public static int queenPermutation1D(boolean[] tnb, int tnq, int qpsf, String ans) {
        if (qpsf == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int b = 0; b < tnb.length; b++) {
            if (!tnb[b]) {
                tnb[b] = true;
                count += queenPermutation1D(tnb, tnq, qpsf + 1, ans + "b" + b + "q" + qpsf + " ");
                tnb[b] = false;
            }
        }

        return count;
    }

    public static int queenCombination2D(boolean[][] tnb, int idx, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = tnb.length;
        int m = tnb[0].length;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            count += queenCombination2D(tnb, i + 1, tnq - 1, ans + "(" + r + "," + c + ") ");
        }

        return count;
    }

    public static int queenPermutation2D(boolean[][] tnb, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = tnb.length;
        int m = tnb[0].length;
        for (int i = 0; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!tnb[r][c]) {
                tnb[r][c] = true;
                count += queenPermutation2D(tnb, tnq - 1, ans + "(" + r + "," + c + ") ");
                tnb[r][c] = false;
            }
        }

        return count;
    }

    // nQueen_Problem==================================================================

    public static boolean isSafeToPlaceQueen(boolean[][] boxes, int r, int c) {
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad < boxes.length; rad++) {
                int x = r + rad * dir[d][0];
                int y = c + rad * dir[d][1];

                if (x >= 0 && y >= 0 && x < boxes.length && y < boxes[0].length) {
                    if (boxes[x][y])
                        return false;

                } else
                    break;
            }
        }

        return true;
    }

    public static int nQueen01(boolean[][] boxes, int idx, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = boxes.length;
        int m = boxes[0].length;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (isSafeToPlaceQueen(boxes, r, c)) {
                boxes[r][c] = true;
                count += nQueen01(boxes, i + 1, tnq - 1, ans + "(" + r + "," + c + ") ");
                boxes[r][c] = false;
            }
        }
        return count;
    }

    static boolean[] rowA;
    static boolean[] colA;
    static boolean[] diagA;
    static boolean[] aDiagA;

    public static void toggleNQueen(int r, int c, int n) {
        rowA[r] = !rowA[r];
        colA[c] = !colA[c];
        diagA[r - c + n - 1] = !diagA[r - c + n - 1];
        aDiagA[r + c] = !aDiagA[r + c];
    }

    public static int nQueen02(int n, int idx, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < n * n; i++) {
            int r = i / n;
            int c = i % n;
            if (!rowA[r] && !colA[c] && !diagA[r - c + n - 1] && !aDiagA[r + c]) {
                toggleNQueen(r, c, n);
                count += nQueen02(n, i + 1, tnq - 1, ans + "(" + r + "," + c + ") ");
                toggleNQueen(r, c, n);
            }
        }
        return count;
    }

    public static int nQueen03_Perm(boolean[][] boxes, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = boxes.length;
        for (int i = 0; i < n * n; i++) {
            int r = i / n;
            int c = i % n;
            if (!boxes[r][c] && !rowA[r] && !colA[c] && !diagA[r - c + n - 1] && !aDiagA[r + c]) {
                toggleNQueen(r, c, n);
                boxes[r][c] = true;

                count += nQueen03_Perm(boxes, tnq - 1, ans + "(" + r + "," + c + ") ");
                toggleNQueen(r, c, n);
                boxes[r][c] = false;
            }
        }
        return count;
    }

    public static int nQueen04(int n, int r, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int c = 0; c < n; c++) {
            if (!rowA[r] && !colA[c] && !diagA[r - c + n - 1] && !aDiagA[r + c]) {
                toggleNQueen(r, c, n);
                count += nQueen04(n, r + 1, tnq - 1, ans + "(" + r + "," + c + ") ");
                toggleNQueen(r, c, n);
            }
        }

        return count;
    }

    static int colB = 0;
    static int diagB = 0;
    static int aDiagB = 0;

    public static void toggleNQueenBit(int r, int c, int n) {
        colB ^= (1 << c);
        diagB ^= (1 << (r - c + n - 1));
        aDiagB ^= (1 << (r + c));
    }

    public static int nQueen04BitMask(int n, int r, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int c = 0; c < n; c++) {
            if ((colB & (1 << c)) == 0 && (diagB & (1 << (r - c + n - 1))) == 0 && (aDiagB & (1 << (r + c))) == 0) {
                toggleNQueenBit(r, c, n);
                count += nQueen04BitMask(n, r + 1, tnq - 1, ans + "(" + r + "," + c + ") ");
                toggleNQueenBit(r, c, n);
            }
        }

        return count;
    }

    public static void subseq(String str, int idx, String ans) {
        if (idx == str.length()) {
            return;
        }

        for (int i = idx; i < str.length(); i++) {
            char ch = str.charAt(i);
            System.out.println(ans + ch);
            subseq(str, i + 1, ans + ch);
        }
    }

    


    public static void main(String[] args) {
        // System.out.println(queenCombination1D(5, 0, 3, 0, ""));
        // boolean[] tnb = new boolean[5];
        // System.out.println(queenPermutation1D(tnb, 3, 0, ""));

        int n = 4;
        boolean[][] tnb = new boolean[n][n];
        // System.out.println(queenCombination2D(tnb, 0, n, ""));
        // System.out.println(queenPermutation2D(tnb, n, ""));

        // System.out.println(nQueen01(tnb, 0, n, ""));
        // rowA = new boolean[n];
        // colA = new boolean[n];
        // diagA = new boolean[n + n - 1];
        // aDiagA = new boolean[n + n - 1];
        // // System.out.println(nQueen02(n, 0, n, ""));

        subseq("abc", 0, "");
    }

}