import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class l002 {

    public int getMaximumGold(int[][] grid, int sr, int sc, int[][] dir) {
        int maxGold = 0, n = grid.length, m = grid[0].length;
        int val = grid[sr][sc];
        grid[sr][sc] = -grid[sr][sc];
        for (int d = 0; d < 4; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] > 0) {
                int recGold = getMaximumGold(grid, r, c, dir);
                if (recGold > maxGold)
                    maxGold = recGold;
            }
        }
        grid[sr][sc] = -grid[sr][sc];

        return maxGold + val;
    }

    public int getMaximumGold(int[][] grid) {
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int maxGold = 0, n = grid.length, m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] > 0) {
                    maxGold = Math.max(maxGold, getMaximumGold(grid, i, j, dir));
                }
            }
        }

        return maxGold;
    }

    // https://www.geeksforgeeks.org/gold-mine-problem/

    public static int c = 1;

    public static void friendsPairing(int count, int n, boolean[] used, String asf) {
        if (count == n) {
            System.out.println(c++ + "." + asf);
            return;
        }

        int fup = 0; // first un used person
        while (fup <= n && used[fup])
            fup++;

        used[fup] = true;
        friendsPairing(count + 1, n, used, asf + "(" + fup + ") ");

        for (int pp = fup + 1; pp <= n; pp++) { // pair person
            if (!used[pp]) {
                used[pp] = true;
                friendsPairing(count + 2, n, used, asf + "(" + fup + "," + pp + ") ");
                used[pp] = false;
            }
        }

        used[fup] = false;
    }

    public static int wordBreak(String str, String ans, HashSet<String> dict) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int ei = 0; ei < str.length(); ei++) {
            String pword = str.substring(0, ei + 1);
            if (dict.contains(pword)) {
                count += wordBreak(str.substring(ei + 1), ans + pword + " ", dict);
            }
        }

        return count;
    }

    public static String max = "";

    public static void findMaximum(String str, int k, int ii) {
        if (k == 0)
            return;

        for (int i = ii; i < str.length(); i++) {
            int idx = -1;
            char maxCh = '0';
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) < str.charAt(j) && maxCh < str.charAt(j)) {
                    idx = j;
                    maxCh = str.charAt(j);
                }
            }

            if (idx != -1) {
                for (int j = idx; j < str.length(); j++) {
                    if (str.charAt(j) == maxCh) {
                        String temp = swap(str, i, j);
                        if (isGreater(temp, max))
                            max = temp;
                        findMaximum(temp, k - 1, i + 1);
                    }
                }
            }
        }
    }

    public static boolean isGreater(String temp, String str) {
        if (temp.length() > str.length())
            return true;
        else if (temp.length() < str.length())
            return false;

        for (int i = 0; i < str.length(); i++) {
            if (temp.charAt(i) > str.charAt(i))
                return true;
            else if (temp.charAt(i) < str.charAt(i))
                return false;
        }

        return true;
    }

    public static String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        char c1 = str.charAt(i);
        char c2 = str.charAt(j);

        sb.setCharAt(i, c2);
        sb.setCharAt(j, c1);

        return sb.toString();
    }

}