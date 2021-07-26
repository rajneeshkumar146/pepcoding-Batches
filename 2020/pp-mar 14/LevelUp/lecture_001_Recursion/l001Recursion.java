import java.util.ArrayList;

public class l001Recursion {
    public static void pppppp(int a) {
        System.out.println("I am Base case: " + a);
        return;
    }

    public static void ppppp(int a) {
        System.out.println("hi: " + a);
        pppppp(a + 1);
        System.out.println("Bye: " + a);
    }

    public static void pppp(int a) {
        System.out.println("hi: " + a);
        ppppp(a + 1);
        System.out.println("Bye: " + a);
    }

    public static void ppp(int a) {
        System.out.println("hi: " + a);
        pppp(a + 1);
        System.out.println("Bye: " + a);
    }

    public static void pp(int a) {
        System.out.println("hi: " + a);
        ppp(a + 1);
        System.out.println("Bye: " + a);
    }

    public static void p(int a) {
        System.out.println("hi: " + a);
        pp(a + 1);
        System.out.println("Bye: " + a);
    }

    public static void recursionPattern(int a, int b) {
        if (a == b) {
            System.out.println("I am Base case: " + a);
            return;
        }

        System.out.println("Hi" + a);
        recursionPattern(a + 1, b);
        System.out.println("Bye" + a);
    }

    public static void printIncreasing(int a, int b) {
        if (a > b)
            return;
        System.out.println(a);
        printIncreasing(a + 1, b);
    }

    public static void printDecreasing(int a, int b) {
        if (a > b)
            return;
        printDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void printIncreasingDecreasing(int a, int b) {
        if (a > b)
            return;
        System.out.println(a);
        printIncreasingDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void oddEven(int a, int b) {
        if (a > b)
            return;
        if (a % 2 != 0)
            System.out.println(a);
        oddEven(a + 1, b);
        if (a % 2 == 0)
            System.out.println(a);
    }

    public static int factorial(int n) {
        if (n == 0)
            return 1;

        return factorial(n - 1) * n;
    }

    public static int power(int a, int b) {
        if (b == 0)
            return 1;

        return power(a, b - 1) * a;
    }

    // O(logn)
    public static int powerBtr(int a, int b) {
        if (b == 0)
            return 1;

        int smallAns = powerBtr(a, b / 2);
        smallAns *= smallAns;

        return b % 2 == 0 ? smallAns : smallAns * a;
    }

    public static void printArray(int[] arr, int idx) {
        if (idx >= arr.length)
            return;

        System.out.println(arr[idx]);
        printArray(arr, idx + 1);
    }

    public static void printArrayReverse(int[] arr, int idx) {
        if (idx >= arr.length)
            return;

        printArrayReverse(arr, idx + 1);
        System.out.println(arr[idx]);
    }

    public static int maximum(int[] arr, int idx) {
        if (idx >= arr.length)
            return -(int) 1e9;

        int maxValue = maximum(arr, idx + 1);
        return Math.max(maxValue, arr[idx]);
    }

    public static int minimum(int[] arr, int idx) {
        if (idx >= arr.length)
            return (int) 1e9;

        int minValue = minimum(arr, idx + 1);
        return Math.min(minValue, arr[idx]);
    }

    public static boolean find(int[] arr, int data, int idx) {
        if (idx >= arr.length)
            return false;

        return arr[idx] == data || find(arr, data, idx + 1);
    }

    public static int firstIndex(int[] arr, int data, int idx) {
        if (idx >= arr.length)
            return -1;

        return arr[idx] == data ? idx : firstIndex(arr, data, idx + 1);
    }

    public static int lastIndex(int[] arr, int data, int idx) {
        if (idx >= arr.length)
            return -1;

        int ans = lastIndex(arr, data, idx + 1);
        if (ans != -1)
            return ans;

        return arr[idx] == data ? idx : -1;
    }

    public static int[] allIndex(int[] arr, int data, int idx, int count) {
        if (idx >= arr.length)
            return new int[count];

        if (arr[idx] == data)
            count++;

        int[] ans = allIndex(arr, data, idx + 1, count);

        if (arr[idx] == data)
            ans[count - 1] = idx;

        return ans;
    }

    public static ArrayList<String> subsequence(String str, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> smallAns = subsequence(str, idx + 1);
        ArrayList<String> myAns = new ArrayList<>(smallAns);
        for (String s : smallAns)
            myAns.add(str.charAt(idx) + s);

        return myAns;
    }

    public static int subsequence(String str, int idx, String asf, ArrayList<String> ans) {
        if (idx == str.length()) {
            ans.add(asf);
            return 1;
        }
        int count = 0;
        count += subsequence(str, idx + 1, asf, ans);
        count += subsequence(str, idx + 1, asf + str.charAt(idx), ans);

        return count;
    }

    public static String[] nokiaKeys = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static int nokiaKeyPad(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        return 0;
    }

    public static ArrayList<String> stairPath(int n) {
        if (n == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        for (int jump = 1; jump <= 3 && n - jump >= 0; jump++) {
            ArrayList<String> smallAns = stairPath(n - jump);
            for (String s : smallAns) {
                myAns.add(jump + s);
            }
        }

        return myAns;
    }

    public static int stairPath(int n, String psf, ArrayList<String> ans) {
        if (n == 0) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        for (int jump = 1; jump <= 3 && n - jump >= 0; jump++) {
            count += stairPath(n - jump, psf + jump, ans);
        }

        return count;
    }

    public static int boardPath(int n, String psf, ArrayList<String> ans) {
        if (n == 0) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        for (int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
            count += boardPath(n - dice, psf + dice, ans);
        }

        return count;
    }

    public static int boardPath(int[] arr, int n, String psf, ArrayList<String> ans) {
        if (n == 0) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        for (int i = 1; i < arr.length && n - arr[i] >= 0; i++) {
            count += boardPath(arr, n - arr[i], psf + arr[i], ans);
        }

        return count;
    }

    public static int mazePath_HVD(int sr, int sc, int er, int ec, String psf, ArrayList<String> ans, int[][] dir,
            String[] dirS) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                count += mazePath_HVD(r, c, er, ec, psf + dirS[d], ans, dir, dirS);
            }
        }

        return count;
    }

    public static int mazePath_HVD_multi(int sr, int sc, int er, int ec, String psf, ArrayList<String> ans, int[][] dir,
            String[] dirS) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(er, ec); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                    count += mazePath_HVD_multi(r, c, er, ec, psf + dirS[d] + rad, ans, dir, dirS);
                } else
                    break;
            }
        }

        return count;
    }

    public static void mazePath() {
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
        String[] dirS = { "H", "V", "D" };

        ArrayList<String> ans = new ArrayList<>();
        System.out.println(mazePath_HVD_multi(0, 0, 2, 2, "", ans, dir, dirS));

        System.out.println(ans);
    }

    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1

    public static int ratInMaze(int sr, int sc, int[][] mat, String psf, ArrayList<String> ans, int[][] dir,
            String[] dirS) {

        int n = mat.length, m = mat[0].length;
        if (sr == n - 1 && sc == m - 1) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        mat[sr][sc] = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && mat[r][c] == 1) {
                count += ratInMaze(r, c, mat, psf + dirS[d], ans, dir, dirS);
            }
        }
        mat[sr][sc] = 1;

        return count;
    }

    public static void ratInMaze(int[][] mat) {
        int[][] dir = { { 1, 0 }, { 0, 1 } };
        String[] dirS = { "D", "R" };
        ArrayList<String> ans = new ArrayList<>();

        ratInMaze(0, 0, mat, "", ans, dir, dirS);
    }

    public static int specialMatrix(int sr, int sc, boolean[][] mat, int[][] dir) {

        int n = mat.length, m = mat[0].length;
        if (sr == n - 1 && sc == m - 1) {
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && !mat[r][c]) {
                count += specialMatrix(r, c, mat, dir);
            }
        }

        return count;
    }

    public static void specialMatrix(int n, int m, int[][] blockedCells) {
        int[][] dir = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };

        boolean[][] mat = new boolean[n][m];
        for (int[] cell : blockedCells) {
            mat[cell[0] - 1][cell[0] - 1] = true;
        }

        System.out.println(specialMatrix(0, 0, mat, dir));
    }

    public static void main(String[] args) {
        mazePath();
    }

}