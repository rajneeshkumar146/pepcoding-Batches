import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class leetcodeSolutions {

    // 37
    int[] row;
    int[] col;
    int[][] mat;

    public void toggleSudokuBit(int r, int c, int num) {
        int mask = (1 << num);
        row[r] ^= mask;
        col[c] ^= mask;
        mat[r / 3][c / 3] ^= mask;
    }

    public boolean sudokuSolver_Bit(char[][] board, ArrayList<Integer> loc, int idx) {
        if (idx == loc.size()) {
            return true;
        }

        int r = loc.get(idx) / board[0].length;
        int c = loc.get(idx) % board[0].length;

        for (int num = 1; num <= 9; num++) {
            int mask = (1 << num);
            if ((row[r] & mask) == 0 && (col[c] & mask) == 0 && (mat[r / 3][c / 3] & mask) == 0) {
                board[r][c] = (char) (num + '0');
                toggleSudokuBit(r, c, num);

                if (sudokuSolver_Bit(board, loc, idx + 1))
                    return true;

                board[r][c] = '.';
                toggleSudokuBit(r, c, num);
            }
        }

        return false;
    }

    public void solveSudoku(char[][] board) {
        ArrayList<Integer> loc = new ArrayList<>();

        row = new int[9];
        col = new int[9];
        mat = new int[3][3];

        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '.')
                    loc.add(i * m + j);
                else
                    toggleSudokuBit(i, j, board[i][j] - '0');
            }
        }
        sudokuSolver_Bit(board, loc, 0);
    }

    // 36
    boolean isValidSudoku(char[][] board) {
        @SuppressWarnings("unchecked")
        HashSet<Integer>[] row = new HashSet[9];
        @SuppressWarnings("unchecked")
        HashSet<Integer>[] col = new HashSet[9];
        @SuppressWarnings("unchecked")
        HashSet<Integer>[][] mat = new HashSet[3][3];

        int n = board.length;
        int m = board[0].length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (board[r][c] != '.') {
                    int num = board[r][c] - '0';
                    if (!row[r].contains(num) && !col[c].contains(num) && !mat[r / 3][c / 3].contains(num)) {
                        row[r].add(num);
                        col[c].add(num);
                        mat[r / 3][c / 3].add(num);
                    } else
                        return false;
                }
            }
        }
        return true;
    }

    public static void wordBreak(String str, String ans, HashSet<String> dict) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }
        for (int len = 1; len <= str.length(); len++) {
            String word = str.substring(0, len);

            if (dict.contains(word)) {
                wordBreak(str.substring(len), ans + word + " ", dict);
            }
        }
    }

    public static boolean isPossibleToPlaceH(char[][] arr, String word, int r, int c) {
        for (int i = 0; i < word.length(); i++) {
            if (c + i >= arr[0].length)
                return false;

            if (arr[r][c + i] != '-' && arr[r][c + i] != word.charAt(i))
                return false;
        }

        return true;
    }

    public static boolean[] placeH(char[][] arr, String word, int r, int c) {
        boolean[] charLoc = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (arr[r][c + i] == '-') {
                charLoc[i] = true;
                arr[r][c + i] = word.charAt(i);
            }
        }

        return charLoc;
    }

    public static void unPlaceH(char[][] arr, String word, int r, int c, boolean[] charLoc) {
        for (int i = 0; i < word.length(); i++) {
            if (charLoc[i]) {
                arr[r][c + i] = '-';
            }
        }
    }

    public static boolean isPossibleToPlaceV(char[][] arr, String word, int r, int c) {
        for (int i = 0; i < word.length(); i++) {
            if (r + i >= arr.length)
                return false;

            if (arr[r + i][c] != '-' && arr[r + i][c] != word.charAt(i))
                return false;
        }

        return true;
    }

    public static boolean[] placeV(char[][] arr, String word, int r, int c) {
        boolean[] charLoc = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (arr[r + i][c] == '-') {
                charLoc[i] = true;
                arr[r + i][c] = word.charAt(i);
            }
        }
        return charLoc;
    }

    public static void unPlaceV(char[][] arr, String word, int r, int c, boolean[] charLoc) {
        for (int i = 0; i < word.length(); i++) {
            if (charLoc[i]) {
                arr[r + i][c] = '-';
            }
        }
    }

    public static void displayCharArray(char[][] arr) {
        for (char[] ar : arr) {
            for (char ele : ar) {
                System.out.println(ele + " ");
            }
            System.out.println();
        }

    }

    public static int crossWord(char[][] arr, String[] words, int vidx) {
        if (vidx == words.length) {
            displayCharArray(arr);
            return 1;
        }

        String word = words[vidx];
        int count = 0;
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                if (arr[r][c] == '-' || arr[r][c] == word.charAt(0)) {
                    if (isPossibleToPlaceH(arr, word, r, c)) {
                        boolean[] charLoc = placeH(arr, word, r, c);
                        count += crossWord(arr, words, vidx + 1);
                        unPlaceH(arr, word, r, c, charLoc);
                    }

                    if (isPossibleToPlaceV(arr, word, r, c)) {
                        boolean[] charLoc = placeV(arr, word, r, c);
                        count += crossWord(arr, words, vidx + 1);
                        unPlaceV(arr, word, r, c, charLoc);
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        char[][] arr = new char[10][];
        for (int i = 0; i < 10; i++) {
            arr[i] = scn.nextLine().toCharArray();
        }

        String[] words = scn.nextLine().split(";");
        crossWord(arr, words, 0);
    }

}