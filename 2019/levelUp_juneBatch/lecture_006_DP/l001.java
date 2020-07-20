import java.util.LinkedList;

public class l001 {

    // Future Planning.=============================================

    public static void display(int[] dp) {
        for (int ele : dp)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] ar : dp) {
            display(ar);
        }
        System.out.println();
    }

    public static int fibo_Rec(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = n;

        if (dp[n] != 0)
            return dp[n];

        int ans = fibo_Rec(n - 1, dp) + fibo_Rec(n - 2, dp);

        return dp[n] = ans;
    }

    public static int fibo_DP(int n, int[] dp) {
        int N = n;
        for (n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }
            int ans = dp[n - 1] + dp[n - 2];// fibo_Rec(n - 1, dp) + fibo_Rec(n - 2, dp);

            dp[n] = ans;
        }

        return dp[N];
    }

    public static int fibo_Opti(int n) {
        int a = 0;
        int b = 1;
        for (int i = 0; i < n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }

    public static int mazePath_Rec_01(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        if (sr + 1 <= er)
            count += mazePath_Rec_01(sr + 1, sc, er, ec, dp);
        if (sc + 1 <= ec)
            count += mazePath_Rec_01(sr, sc + 1, er, ec, dp);
        if (sr + 1 <= er && sc + 1 <= ec)
            count += mazePath_Rec_01(sr + 1, sc + 1, er, ec, dp);

        return dp[sr][sc] = count;
    }

    public static int mazePath_Rec_02(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        for (int jump = 1; sr + jump <= er; jump++)
            count += mazePath_Rec_02(sr + jump, sc, er, ec, dp);
        for (int jump = 1; sc + jump <= ec; jump++)
            count += mazePath_Rec_02(sr, sc + jump, er, ec, dp);
        for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
            count += mazePath_Rec_02(sr + jump, sc + jump, er, ec, dp);

        return dp[sr][sc] = count;
    }

    public static int mazePath_DP_01(int sr, int sc, int er, int ec, int[][] dp) {
        for (sr = er; sr >= 0; sr--) {
            for (sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                if (sr + 1 <= er)
                    count += dp[sr + 1][sc];// mazePath_Rec_01(sr + 1, sc, er, ec, dp);
                if (sc + 1 <= ec)
                    count += dp[sr][sc + 1];// mazePath_Rec_01(sr, sc + 1, er, ec, dp);
                if (sr + 1 <= er && sc + 1 <= ec)
                    count += dp[sr + 1][sc + 1];// mazePath_Rec_01(sr + 1, sc + 1, er, ec, dp);

                dp[sr][sc] = count;
            }
        }

        return dp[0][0];
    }

    public static int mazePath_DP_02(int sr, int sc, int er, int ec, int[][] dp) {
        for (sr = er; sr >= 0; sr--) {
            for (sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int jump = 1; sr + jump <= er; jump++)
                    count += dp[sr + jump][sc];
                for (int jump = 1; sc + jump <= ec; jump++)
                    count += dp[sr][sc + jump];
                for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
                    count += dp[sr + jump][sc + jump];

                dp[sr][sc] = count;
            }
        }

        return dp[0][0];
    }

    // to_Be_done------------------------------------------->62_and_63_of_leetcode.

    public static int boardPath_Rec_01(int si, int ei, int[] dp) {
        if (si == ei) {
            return dp[si] = 1;
        }

        if (dp[si] != 0)
            return dp[si];

        int count = 0;
        for (int dice = 1; dice <= 6 && si + dice <= ei; dice++) {
            count += boardPath_Rec_01(si + dice, ei, dp);
        }

        return dp[si] = count;
    }

    public static int boardPath_DP_01(int si, int ei, int[] dp) {

        for (si = ei; si >= 0; si--) {
            if (si == ei) {
                dp[si] = 1;
                continue;
            }

            int count = 0;
            for (int dice = 1; dice <= 6 && si + dice <= ei; dice++) {
                count += dp[si + dice];// boardPath_Rec_01(si + dice, ei, dp);
            }

            dp[si] = count;
        }

        return dp[0];
    }

    public static int boardPath_opti(int ei) {
        LinkedList<Integer> ll = new LinkedList<>();

        for (int si = 0; si <= ei; si++) {
            if (si < 2) {
                ll.addFirst(1);
                continue;
            }

            if (ll.size() <= 6)
                ll.addFirst(2 * ll.getFirst());
            else {
                ll.addFirst(2 * ll.getFirst() - ll.getLast());
                ll.removeLast();
            }
        }

        return ll.getFirst();
    }

    public static int boardPath_DP_02(int si, int ei, int[] moves, int[] dp) {
        for (si = ei; si >= 0; si--) {
            if (si == ei) {
                dp[si] = 1;
                continue;
            }

            int count = 0;
            for (int i = 0; i < moves.length; i++) {
                if (si + moves[i] <= ei)
                    count += dp[si + moves[i]];// boardPath_Rec_01(si + moves[i], ei, dp);
            }

            dp[si] = count;
        }

        return dp[0];
    }

    // ===================================================================================================
    public static void pathSet() {
        // int n = 4, m = 4;
        // int[][] dp = new int[n][m];

        // System.out.println(mazePath_Rec_01(0, 0, n - 1, m - 1, dp));
        // System.out.println(mazePath_DP_01(0, 0, n - 1, m - 1, dp));

        // System.out.println(mazePath_Rec_02(0, 0, n - 1, m - 1, dp));
        // System.out.println(mazePath_DP_02(0, 0, n - 1, m - 1, dp));

        int n = 10;
        int[] dp = new int[n + 1];
        int[] moves = { 2, 4, 1, 5 };
        // System.out.println(boardPath_Rec_01(0, n, dp));
        // System.out.println(boardPath_DP_01(0, n, dp));
        System.out.println(boardPath_opti(n));

        // System.out.println(boardPath_DP_02(0, n, moves, dp));

        display(dp);
        // display2D(dp);
    }

    public static void set1() {
        int n = 17;
        int[] dp = new int[n + 1];

        System.out.println(fibo_Rec(n, dp));
        // System.out.println(fibo_DP(n, dp));
        System.out.println(fibo_Opti(n));

        display(dp);
    }

    public static void solve() {
        // set1();
        pathSet();
    }

    public static void main(String... args) {
        solve();

    }

}