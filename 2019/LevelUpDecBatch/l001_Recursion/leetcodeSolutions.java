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

}