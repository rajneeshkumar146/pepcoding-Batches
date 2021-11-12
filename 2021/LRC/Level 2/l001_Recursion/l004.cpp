#include <iostream>
#include <vector>
using namespace std;

bool isSafeToPlaceQueen(vector<vector<bool>> &board, int sr, int sc)
{

    int n = board.size(), m = board[0].size();
    vector<vector<int>> dir{{0, -1}, {-1, 0}, {-1, -1}, {-1, 1}};
    for (vector<int> &d : dir)
    {
        int r = sr + d[0], c = sc + d[1];
        while (r >= 0 && c >= 0 && r < n && c < m)
        {
            if (board[r][c])
                return false;

            r += d[0];
            c += d[1];
        }
    }

    return true;
}

int calls = 0;

int nqueen_01(vector<vector<bool>> &board, int idx, int tnq, string asf)
{
    if (tnq == 0)
    {
        cout << asf << endl;
        return 1;
    }

    calls++;
    int count = 0, n = board.size(), m = board[0].size();
    for (int i = idx; i < n * m; i++)
    {
        int r = i / m, c = i % m;
        if (isSafeToPlaceQueen(board, r, c))
        {
            board[r][c] = !board[r][c];
            count += nqueen_01(board, i + 1, tnq - 1, asf + "(" + to_string(r) + "," + to_string(c) + ") ");
            board[r][c] = !board[r][c];
        }
    }

    return count;
}

vector<bool> rows, cols, diag, adiag;

int nqueen_02(int n, int m, int idx, int tnq, string asf)
{
    if (tnq == 0)
    {
        cout << asf << endl;
        return 1;
    }

    calls++;
    int count = 0;
    for (int i = idx; i < n * m; i++)
    {
        int r = i / m, c = i % m;
        if (!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1])
        {
            rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;
            count += nqueen_02(n, m, i + 1, tnq - 1, asf + "(" + to_string(r) + "," + to_string(c) + ") ");
            rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
        }
    }

    return count;
}

int nqueen_03_permutation(int n, int m, int tnq, string asf)
{
    if (tnq == 0)
    {
        cout << asf << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < n * m; i++)
    {
        int r = i / m, c = i % m;
        if (!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1])
        {
            rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;
            count += nqueen_03_permutation(n, m, tnq - 1, asf + "(" + to_string(r) + "," + to_string(c) + ") ");
            rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
        }
    }

    return count;
}

int nqueen_04_opti(int n, int m, int floor, int queen, string asf)
{
    if (queen == 0)
    {
        cout << asf << endl;
        return 1;
    }

    int count = 0;
    for (int room = 0; room < m; room++)
    {
        int r = floor, c = room;
        if (!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1])
        {
            rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;
            count += nqueen_04_opti(n, m, floor + 1, queen - 1, asf + "(" + to_string(r) + "," + to_string(c) + ") ");
            rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
        }
    }

    return count;
}

int nqueen_05_opti(int n, int m, int floor, int queen, string asf)
{
    if (queen == 0 || floor == n)
    {
        if (queen == 0)
        {
            cout << asf << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    for (int room = 0; room < m; room++)
    {
        int r = floor, c = room;
        if (!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1])
        {
            rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;
            count += nqueen_05_opti(n, m, floor + 1, queen - 1, asf + "(" + to_string(r) + "," + to_string(c) + ") ");
            rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
        }
    }

    count += nqueen_05_opti(n, m, floor + 1, queen, asf);
    return count;
}

void nQueen()
{
    int n = 4, tnq = 4;
    vector<vector<bool>> board(n, vector<bool>(n, false));
    rows.resize(n, false);
    cols.resize(n, false);
    diag.resize(2 * n - 1, false);
    adiag.resize(2 * n - 1, false);

    // cout << nqueen_01(board, 0, tnq, "") << endl;
    // cout << nqueen_02(n, n, 0, tnq, "") << endl;
    cout << nqueen_03_permutation(n, n, tnq, "") << endl;
    cout << calls << endl;
}

// sudoku.==================================================

bool isSafeToPlaceNumber(vector<vector<char>> &board, int num, int r, int c)
{
    // row
    for (int j = 0; j < 9; j++)
        if (board[r][j] - '0' == num)
            return false;

    // col
    for (int i = 0; i < 9; i++)
        if (board[i][c] - '0' == num)
            return false;

    // mat
    r = (r / 3) * 3; // division -> (Integer Part) * 3 -> decompress
    c = (c / 3) * 3;

    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (board[r + i][c + j] - '0' == num)
                return false;
        }
    }

    return true;
}

bool solveSudoku(vector<vector<char>> &board, int idx, vector<pair<int, int>> &cells)
{
    if (idx == cells.size())
        return true;

    // int r = cells[idx] / 9, c = cells[idx] % 9;
    int r = cells[idx].first, c = cells[idx].second;
    for (int num = 1; num <= 9; num++)
    {
        if (isSafeToPlaceNumber(board, num, r, c))
        {
            board[r][c] = (char)('0' + num);
            if (solveSudoku(board, idx + 1, cells))
                return true;
            board[r][c] = '.';
        }
    }

    return false;
}

void solveSudoku(vector<vector<char>> &board)
{
    // vector<int> cells;
    vector<pair<int, int>> cells;
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            if (board[i][j] == '.')
            {
                // cells.push_back(i * 9 + j);
                cells.push_back({i, j});
            }
        }
    }

    solveSudoku(board, 0, cells);
}

int main()
{
    nQueen();
    return 0;
}