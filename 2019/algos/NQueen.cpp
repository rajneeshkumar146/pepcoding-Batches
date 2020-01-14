// to place n queens in an array
using namespace std;
#include <iostream>
#include <vector>
#include <math.h>

/* tnq = total number of queens 
    qpsf = # of queens placed so far
    lqpl = last queen placed location */

// Simple 1-D setup without any chess queen restrictions
// Combination
int NQueens1DCombi(int nbox, int qpsf, int lqpl, int tnq, string ans)
{
    if (qpsf == tnq || lqpl == nbox)
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;

    count += NQueens1DCombi(nbox, qpsf + 1, lqpl + 1, tnq, ans + 'b' + to_string(lqpl) + 'q' + to_string(qpsf) + ' ');

    count += NQueens1DCombi(nbox, qpsf, lqpl + 1, tnq, ans);

    return count;
}

// permutation with subsequence method( 0 and 1)
int NQueens1DPerm_subsequence(vector<int> &arr, int qpsf, int idx, int tnq, string ans)
{
    if (qpsf == tnq || idx == arr.size())
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;

    if (!arr[idx])
    {
        arr[idx] = 1;
        count += NQueens1DPerm_subsequence(arr, qpsf + 1, 0, tnq, ans + 'b' + to_string(idx) + 'q' + to_string(qpsf) + ' ');
        arr[idx] = 0;
    }

    count += NQueens1DPerm_subsequence(arr, qpsf, idx + 1, tnq, ans);

    return count;
}

//permutation in normal method (for loop)
int NQueen1DPerm_Normal(vector<int> &arr, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;

    for (int j = 0; j < arr.size(); j++)
    {
        if (!arr[j])
        {
            arr[j] = 1;
            count += NQueen1DPerm_Normal(arr, qpsf + 1, tnq, ans + "b" + to_string(j) + "q" + to_string(qpsf) + ' ');
            arr[j] = 0;
        }
    }
    return count;
}

bool IsSafeToPlaceQueen(vector<vector<bool>> &board, int x, int y, int choice) // choice = 0 for combination function, choice = 1 for permuation function
{
    vector<vector<int>> combi{{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    vector<vector<int>> permu{{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    vector<vector<int>> dir;
    if (choice == 0)
        dir = combi;
    else if (choice == 1)
        dir = permu;

    for (int d = 0; d < dir.size(); d++)
        for (int radius = 1; radius < max(board.size(), board[0].size()); radius++)
        {
            int r = x + radius * dir[d][0];
            int c = y + radius * dir[d][1];
            if (r >= 0 && c >= 0 && r < board.size() && c < board[0].size())
            {
                if (board[r][c])
                {
                    // cout << "Returning False" << endl;
                    return false;
                }
            }
            else
            {
                break;
            }
        }
    return true;
}

// 2-D setup with actual chess queen constraints
// Combination in normal approach
int NQueen2DCombi(vector<vector<bool>> &board, int lqpl, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lqpl; i < board.size() * board[0].size(); i++)
    {
        int x = i / board.size();
        int y = i % board[0].size();
        if (IsSafeToPlaceQueen(board, x, y, 0))
        {
            // cout << "returned true for " << x << "," << y << endl;
            board[x][y] = true;
            count += NQueen2DCombi(board, i + 1, qpsf + 1, tnq, ans + '(' + to_string(x) + ',' + to_string(y) + ") ");
            board[x][y] = false;
        }
    }
    return count;
}

// permutation in normal approach
int NQueen2DPermutation(vector<vector<bool>> &board, int lqpl, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;

    for (int i = 0; i < board.size() * board[0].size(); i++)
    {
        int x = i / board.size();
        int y = i % board[0].size();
        if (!(board[x][y]) && IsSafeToPlaceQueen(board, x, y, 1))
        {
            // cout << "returned true for " << x << "," << y << endl;
            board[x][y] = true;
            count += NQueen2DPermutation(board, i + 1, qpsf + 1, tnq, ans + '(' + to_string(x) + ',' + to_string(y) + ") ");
            board[x][y] = false;
        }
    }
    return count;
}

// Using the fact that one queen will only be placed on a row, hence calling next recursion on the next row directly, rather than next column in same row.
int NQueen2D_RowMethod(vector<vector<bool>> &board, int qpsf, int tnq, int row, string ans)
{
    if (qpsf == tnq || row == board.size())
    {
        if (tnq == qpsf)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;

    for (int j = 0; j < board[0].size(); j++)
    {
        if (!board[row][j] && IsSafeToPlaceQueen(board, row, j, 0))
        {
            board[row][j] = true;
            count += NQueen2D_RowMethod(board, qpsf + 1, tnq, row + 1, ans + "(" + to_string(row) + ',' + to_string(j) + ')' + ' ');
            board[row][j] = false;
        }
    }
    return count;
}

// Using bit masking to optimize time and space complexity
int NQueen2D_bitMasking(vector<vector<bool>> &board, int row, int qpsf, int tnq, int bit_col, int bit_diag, int bit_antidiag, string ans)
{
    if (qpsf == tnq || row == board.size())
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;

    for (int col = 0; col < board[0].size(); col++)
    {
        int col_mask = (1 << col);
        int diag_mask = (1 << (row + col));
        int anti_diag_mask = (1 << (row - col + board[0].size() - 1));

        if (!(bit_col & col_mask) && !(bit_diag & diag_mask) && !(bit_antidiag & anti_diag_mask))
        {
            bit_col ^= col_mask;
            bit_diag ^= diag_mask;
            bit_antidiag ^= anti_diag_mask;

            count += NQueen2D_bitMasking(board, row + 1, qpsf + 1, tnq, bit_col, bit_diag, bit_antidiag, ans + "(" + to_string(row) + ", " + to_string(col) + ')' + ' ');

            bit_col ^= col_mask;
            bit_diag ^= diag_mask;
            bit_antidiag ^= anti_diag_mask;
        }
    }

    return count;
}

int main()
{
    vector<int> arr(5, 0);
    vector<vector<bool>> board(5, vector<bool>(5, false));
    // cout << NQueens1DCombi(5, 0, 0, 3, "");
    // cout << NQueens1DPerm_subsequence(arr, 0, 0, 3, "") << endl;
    // cout << NQueen1DPerm_Normal(arr, 0, 3, "");
    // cout << NQueen2DCombi(board, 0, 0, board.size(), "") << endl;
    // cout << NQueen2DPermutation(board, 0, 0, board.size(), "") << endl;
    // cout << NQueen2D_RowMethod(board, 0, board.size(), 0, "");
    cout << NQueen2D_bitMasking(board, 0, 0, board.size(), 0, 0, 0, "");
    return 0;
}
