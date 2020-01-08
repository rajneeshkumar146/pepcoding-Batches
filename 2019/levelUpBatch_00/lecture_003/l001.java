import java.util.*;

public class l001 {

    public static void main(String[] args) {
        // int[] arr={2,3,5,7};
        // boolean[] vis=new boolean[arr.length]; //default false.
        // int tar=10;
        // System.out.println(coinCombiInfi(arr,0,tar,""));

        // boolean[][] box = new boolean[10][10];
        // System.out.println(queen2DCombi(box, 0, box.length, ""));
        // System.out.println(queen2DCombi_02(box, 0, 0, 0, 0, box.length, ""));
        // System.out.println(calls);

        // basicQuest();
        // allSubstring();
        // ArrayList<String> words = new ArrayList<>(
        // Arrays.asList("i", "like", "ilike", "man", "go", "mango", "ice", "cream",
        // "icecream", "and"));

        // System.out.println(wordBreak("ilikeicecreamandmango", "", words));

        char[][] board = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' },
                { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
                { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
                { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
                { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } };

        String[] words = { "agra", "norway", "england", "gwalior" };
        System.out.println(crossWord(board, words, 0));
    }

    public static int coinCombiInfi(int[] arr, int idx, int tar, String ans) {
        if (idx == arr.length || tar == 0) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;

        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += coinCombiInfi(arr, 0, tar - arr[idx], ans + arr[idx]);
        count += coinCombiInfi(arr, idx + 1, tar, ans);
        return count;
    }

    public static boolean isSafeToPlaceQueen(boolean[][] box, int x, int y) {
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(box.length, box[0].length); rad++) {
                int r = x + rad * dir[d][0];
                int c = y + rad * dir[d][1];
                if (r >= 0 && c >= 0 && r < box.length && c < box[0].length) {
                    if (box[r][c])
                        return false;
                } else {
                    break;
                }
            }

        }

        return true;

    }

    public static int queen2DCombi(boolean[][] box, int lqpl, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        calls++;
        for (int i = lqpl; i < box.length * box[0].length; i++) {
            int x = i / box[0].length;
            int y = i % box[0].length;
            if (!box[x][y] && isSafeToPlaceQueen(box, x, y)) {
                box[x][y] = true;
                count += queen2DCombi(box, i + 1, tnq - 1, ans + "(" + x + ", " + y + ") ");
                box[x][y] = false;
            }
        }
        return count;

    }

    static int calls = 0;

    public static int queen2DCombi_02(boolean[][] box, int row, int bitc, int bitd, int bitad, int tnq, String ans) {
        if (row == box.length || tnq == 0) {
            if (tnq == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        for (int col = 0; col < box[0].length; col++) {
            int a = (1 << col);
            int b = (1 << (row + col));
            int c = (1 << (row - col + box[0].length - 1));
            if ((bitc & a) == 0 && (bitd & b) == 0 && (bitad & c) == 0) {
                box[row][col] = true;
                bitc ^= a;// bitc[a]=true;
                bitd ^= b; // bitd[b]=true;
                bitad ^= c; // bitad[c]=true;

                count += queen2DCombi_02(box, row + 1, bitc, bitd, bitad, tnq - 1, ans + "(" + row + ", " + col + ") ");

                box[row][col] = false;
                bitc ^= a;// bitc[a]=false;
                bitd ^= b; // bitd[b]=false;
                bitad ^= c; // bitad[c]=false;

            }
        }
        return count;
    }

    public static void basicQuest() {
        // System.out.println(permu("aba", ""));
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        System.out.println(eqiSet(arr, 1, arr[0], 0, arr[0] + "", ""));
    }

    public static int permu(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        int seen = 0;

        for (int i = 0; i < str.length(); i++) {
            int k = str.charAt(i) - 'a';
            int mask = 1 << k;
            if ((seen & mask) == 0) {
                seen |= mask;
                String nstr = str.substring(0, i) + str.substring(i + 1);
                count += permu(nstr, ans + str.charAt(i)); // str[i]
            }
        }

        return count;
    }

    public static int eqiSet(int[] arr, int idx, int sum1, int sum2, String set1, String set2) {
        if (idx == arr.length) {
            if (sum1 == sum2) {
                System.out.println(set1 + " = " + set2);
                return 1;
            }
            return 0;
        }

        int count = 0;
        count += eqiSet(arr, idx + 1, sum1 + arr[idx], sum2, set1 + arr[idx] + " ", set2);
        count += eqiSet(arr, idx + 1, sum1, sum2 + arr[idx], set1, set2 + arr[idx] + " ");
        return count;
    }

    public static int wordBreak(String que, String ans, ArrayList<String> words) {
        if (que.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 1; i <= que.length(); i++) {
            String word = que.substring(0, i);
            if (words.contains(word)) {
                count += wordBreak(que.substring(i), ans + word + " ", words);
            }
        }

        return count;
    }

    public static void allSubstring() {
        String s = "abcdefghijkl";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                System.out.println(s.substring(i, j));
            }
        }
    }

    // ==============================

    public static boolean canHPword(char[][] board, int x, int y, String word) {

        for (int j = 0; j < word.length(); j++) {
            if (board[x][y + j] != '-' && board[x][y + j] != word.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public static boolean[] HPWord(char[][] board, int x, int y, String word) {
        boolean[] loc = new boolean[word.length()];
        for (int j = 0; j < word.length(); j++) {
            if (board[x][y + j] == '-') {
                loc[j] = true;
                board[x][y + j] = word.charAt(j);
            }
        }

        return loc;
    }

    public static void UnPHWord(char[][] board, int x, int y, boolean[] loc) {
        for (int j = 0; j < loc.length; j++) {
            if (loc[j]) {
                board[x][y + j] = '-';
            }
        }
    }

    public static boolean canVPword(char[][] board, int x, int y, String word) {

        for (int j = 0; j < word.length(); j++) {
            if (board[x + j][y] != '-' && board[x + j][y] != word.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public static boolean[] HVWord(char[][] board, int x, int y, String word) {
        boolean[] loc = new boolean[word.length()];
        for (int j = 0; j < word.length(); j++) {
            if (board[x + j][y] == '-') {
                loc[j] = true;
                board[x + j][y] = word.charAt(j);
            }
        }

        return loc;
    }

    public static void UnPVWord(char[][] board, int x, int y, boolean[] loc) {
        for (int j = 0; j < loc.length; j++) {
            if (loc[j]) {
                board[x + j][y] = '-';
            }
        }
    }

    public static int crossWord(char[][] board, String[] words, int idx) {

        if (idx == words.length) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }

            return 1;
        }

        String word = words[idx];
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '-' || board[i][j] == word.charAt(0)) {
                    if (canHPword(board, i, j, word)) {
                        boolean[] loc = HPWord(board, i, j, word);
                        count += crossWord(board, words, idx + 1);
                        UnPHWord(board, i, j, loc);
                    }

                    if (canVPword(board, i, j, word)) {
                        boolean[] loc = HVWord(board, i, j, word);
                        count += crossWord(board, words, idx + 1);
                        UnPVWord(board, i, j, loc);
                    }
                }
            }

        }

        return count;
    }

}
