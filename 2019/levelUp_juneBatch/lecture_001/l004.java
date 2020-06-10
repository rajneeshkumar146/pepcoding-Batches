import java.util.ArrayList;

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

    public static boolean cryptoSolver(String str, int idx) {
       if(idx==str.length()){
         if(verify arrangment of numbers){
            return true;
         }
         return false;
       }

        for (int num = 0; num <= 9; num++) {
            int mask=1<<num;
            if((isUsed& mask)==0){
                isUsed^=mask;
                charToNumMapping[str.charAt(idx)-'a'] = num; 
 
                if(cryptoSolver(str,idx+1)) return true;

                isUsed^=mask;
                charToNumMapping[str.charAt(idx)-'a'] = 0;  
            }
        }

        return false;
    }

    public static boolean isSolvable(String[] words, String result) {

        int[] freq = new int[26];
        String str = words[0] + words[1] + result;
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i) - 'a']++;
        }

        str = "";
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                str += (char) (i + 'a');
            }
        }

        return cryptoSolver(str, 0);
    }

}