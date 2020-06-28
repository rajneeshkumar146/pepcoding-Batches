import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class l004 {

    public static boolean isValidToPlaceNumber(char[][] board, int x, int y, int num) {
        // row
        for (int j = 0; j < 9; j++)
            if (board[x][j] - '0' == num)
                return false;

        // col
        for (int i = 0; i < 9; i++)
            if (board[i][y] - '0' == num)
                return false;

        // 3X3 matrix
        int r = (x / 3) * 3;
        int c = (y / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[r + i][c + j] - '0' == num)
                    return false;
            }
        }

        return true;
    }

    static ArrayList<Integer> xpoints, ypoints;

    public static boolean sudokuSolver(char[][] board, int idx) {
        if (idx == xpoints.size())
            return true;

        int x = xpoints.get(idx);
        int y = ypoints.get(idx);

        for (int num = 1; num <= 9; num++) {
            if (isValidToPlaceNumber(board, x, y, num)) {
                board[x][y] = (char) (num + '0');
                if (sudokuSolver(board, idx + 1))
                    return true;
                board[x][y] = '.';
            }
        }
        return false;
    }

    static int[] row;
    static int[] col;
    static int[][] mat;

    public static boolean sudokuSolver_02(char[][] board, int idx) {
        if (idx == xpoints.size())
            return true;

        int i = xpoints.get(idx);
        int j = ypoints.get(idx);

        for (int num = 1; num <= 9; num++) {
            int mask = 1 << num;
            if ((row[i] & mask) == 0 && (col[j] & mask) == 0 && (mat[i / 3][j / 3] & mask) == 0) {

                row[i] ^= mask;
                col[j] ^= mask;
                mat[i / 3][j / 3] ^= mask;
                board[i][j] = (char) (num + '0');

                if (sudokuSolver_02(board, idx + 1))
                    return true;

                board[i][j] = '.';
                row[i] ^= mask;
                col[j] ^= mask;
                mat[i / 3][j / 3] ^= mask;

            }
        }
        return false;
    }

    public static void solveSudoku(char[][] board) {
        xpoints = new ArrayList<>();
        ypoints = new ArrayList<>();
        row = new int[9];
        col = new int[9];
        mat = new int[3][3];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    xpoints.add(i);
                    ypoints.add(j);
                } else {
                    int mask = 1 << (board[i][j] - '0');
                    row[i] ^= mask;
                    col[j] ^= mask;
                    mat[i / 3][j / 3] ^= mask;

                }

            }
        }

        sudokuSolver(board, 0);
    }

    public boolean isValidSudoku(char[][] board) {
        row = new int[9];
        col = new int[9];
        mat = new int[3][3];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int mask = 1 << (board[i][j] - '0');
                    if ((row[i] & mask) == 0 && (col[j] & mask) == 0 && (mat[i / 3][j / 3] & mask) == 0) {
                        row[i] ^= mask;
                        col[i] ^= mask;
                        mat[i / 3][j / 3] ^= mask;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    static int[] charToNumMapping = new int[26];
    static int isUsed = 0;
    static String str1 = "send";
    static String str2 = "more";
    static String str3 = "money";

    public static int stringToNumber(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            res = res * 10 + charToNumMapping[str.charAt(i) - 'a'];
        }
        return res;
    }

    public static int cryptoSolver(String str, int idx) {
        if (idx == str.length()) {
            int num1 = stringToNumber(str1);
            int num2 = stringToNumber(str2);
            int num3 = stringToNumber(str3);
            if (num1 + num2 == num3 && charToNumMapping[str3.charAt(0) - 'a'] != 0) {

                System.out.println(num1);
                System.out.println("+" + num2);
                System.out.println("------");
                System.out.println(num3);
                System.out.println();

                return 1;
            }
            return 0;
        }

        int count = 0;
        for (int num = 0; num <= 9; num++) {
            int mask = 1 << num;
            if ((isUsed & mask) == 0) {
                isUsed ^= mask;
                charToNumMapping[str.charAt(idx) - 'a'] = num;

                count += cryptoSolver(str, idx + 1);

                isUsed ^= mask;
                charToNumMapping[str.charAt(idx) - 'a'] = 0;
            }
        }

        return count;
    }

    public static void isSolvable() {
        int[] freq = new int[26];
        String str = str1 + str2 + str3;
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i) - 'a']++;
        }

        str = "";
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                str += (char) (i + 'a');
            }
        }

        System.out.println(cryptoSolver(str, 0));
    }

    public static int permuatationWithDuplicates(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String ros = str.substring(0, i) + str.substring(i + 1); // rest of string
            count += permuatationWithDuplicates(ros, ans + ch);
        }
        return count;
    }

    public static int permuatationWithoutDuplicates(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        boolean[] vis = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!vis[ch - 'a']) {
                vis[ch - 'a'] = true;
                String ros = str.substring(0, i) + str.substring(i + 1); // rest of string
                count += permuatationWithoutDuplicates(ros, ans + ch);
            }
        }

        return count;
    }

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> sans = new ArrayList<>();
    boolean[] vis;

    public void permuteUnique(int[] nums, int count) {
        if (count == nums.length) {
            List<Integer> base = new ArrayList<>(sans);
            ans.add(base);
            return;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!vis[i] && !set.contains(nums[i])) {

                vis[i] = true;
                set.add(nums[i]);
                sans.add(nums[i]);

                permuteUnique(nums, count + 1);

                vis[i] = false;
                sans.remove(sans.size() - 1);

            }
        }

    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        vis = new boolean[nums.length];
        permuteUnique(nums, 0);
        return ans;
    }

    static char[][] board = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' }, { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' }, { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } };

    public static void print() {
        for (char[] ar : board) {
            for (char ch : ar) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }

    public static boolean canPlaceH(int r, int c, String word) {
        // if (c + word.length() >= board[0].length)
        // return false;
        // if (c + word.length() < board[0].length && board[r][c + word.length()] !=
        // '+')
        // return false;

        for (int i = 0; i < word.length(); i++) {
            if (c + i < board[0].length && board[r][c + i] != '-' && board[r][c + i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean[] placeH(int r, int c, String word) {
        boolean[] loc = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (board[r][c + i] == '-') {
                board[r][c + i] = word.charAt(i);
                loc[i] = true;
            }
        }
        return loc;
    }

    public static void unplaceH(int r, int c, String word, boolean[] loc) {
        for (int i = 0; i < word.length(); i++) {
            if (loc[i])
                board[r][c + i] = '-';
        }
    }

    public static boolean canPlaceV(int r, int c, String word) {
        // if (c + word.length() >= board[0].length)
        // return false;
        // if (c + word.length() < board[0].length && board[r][c + word.length()] !=
        // '+')
        // return false;

        for (int i = 0; i < word.length(); i++) {
            if (r + i < board[0].length && board[r + i][c] != '-' && board[r + i][c] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean[] placeV(int r, int c, String word) {
        boolean[] loc = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (board[r + i][c] == '-') {
                board[r + i][c] = word.charAt(i);
                loc[i] = true;
            }
        }
        return loc;
    }

    public static void unplaceV(int r, int c, String word, boolean[] loc) {
        for (int i = 0; i < word.length(); i++) {
            if (loc[i])
                board[r + i][c] = '-';
        }
    }

    public static int crossWord(String[] words, int idx) {
        if (idx == words.length) {
            print();
            return 1;
        }

        String word = words[idx];
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '-' || board[i][j] == word.charAt(0)) {

                    if (canPlaceH(i, j, word)) {
                        boolean[] loc = placeH(i, j, word);
                        count += crossWord(words, idx + 1);
                        unplaceH(i, j, word, loc);
                    }

                    if (canPlaceV(i, j, word)) {
                        boolean[] loc = placeV(i, j, word);
                        count += crossWord(words, idx + 1);
                        unplaceV(i, j, word, loc);
                    }
                }
            }
        }
        return count;
    }

    public static void crossWord() {
        String[] words = { "agra", "norway", "england", "gwalior" };
        int ans = crossWord(words, 0);
        System.out.println(ans);
    }

    public static void main(String[] args) {
        // isSolvable();
        // int ans = permuatationWithDuplicates("aaa", "");
        // int ans = permuatationWithoutDuplicates("aba", "");
        // System.out.println(ans);

        crossWord();
    }

}