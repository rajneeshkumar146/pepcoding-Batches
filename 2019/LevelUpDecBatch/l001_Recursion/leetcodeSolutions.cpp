#include <iostream>
#include <vector>
#include <unordered_set>
using namespace std;

//37
vector<int> row;
vector<int> col;
vector<vector<int>> mat;

void toggleSudokuBit(int r, int c, int num)
{
    int mask = (1 << num);
    row[r] ^= mask;
    col[c] ^= mask;
    mat[r / 3][c / 3] ^= mask;
}

bool sudokuSolver_Bit(vector<vector<char>> &board, vector<int> &loc, int idx)
{
    if (idx == loc.size())
    {
        return true;
    }

    int r = loc[idx] / board[0].size();
    int c = loc[idx] % board[0].size();
    bool res = false;

    for (int num = 1; num <= 9; num++)
    {
        int mask = (1 << num);
        if ((row[r] & mask) == 0 && (col[c] & mask) == 0 && (mat[r / 3][c / 3] & mask) == 0)
        {
            board[r][c] = (char)(num + '0');
            toggleSudokuBit(r, c, num);

            if (sudokuSolver_Bit(board, loc, idx + 1))
                return true;

            board[r][c] = '.';
            toggleSudokuBit(r, c, num);
        }
    }

    return res;
}

void solveSudoku(vector<vector<char>> &board)
{
    vector<int> loc;

    row.resize(9, 0);
    col.resize(9, 0);
    mat.resize(3, vector<int>(3, 0));

    int n = board.size();
    int m = board[0].size();

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (board[i][j] == '.')
                loc.push_back(i * m + j);
            else
                toggleSudokuBit(i, j, board[i][j] - '0');
        }
    }
    sudokuSolver_Bit(board, loc, 0);
}

//36
bool isValidSudoku(vector<vector<char>> &board)
{
    vector<unordered_set<int>> row(9);
    vector<unordered_set<int>> col(9);
    vector<vector<unordered_set<int>>> mat(3, vector<unordered_set<int>>(3));

    int n = board.size();
    int m = board[0].size();

    for (int r = 0; r < n; r++)
    {
        for (int c = 0; c < m; c++)
        {
            if (board[r][c] != '.')
            {
                int num = board[r][c] - '0';
                if (row[r].find(num) == row[r].end() && col[c].find(num) == col[c].end() && mat[r / 3][c / 3].find(num) == mat[r / 3][c / 3].end())
                {
                    row[r].insert(num);
                    col[c].insert(num);
                    mat[r / 3][c / 3].insert(num);
                }
                else
                    return false;
            }
        }
    }

    return true;
}

vector<int> row;
vector<int> col;
vector<vector<int>> mat;

void toggleSudokuBit(int r, int c, int num)
{
    int mask = (1 << num);
    row[r] ^= mask;
    col[c] ^= mask;
    mat[r / 3][c / 3] ^= mask;
}
bool isValidSudoku(vector<vector<char>> &board)
{

    row.resize(9, 0);
    col.resize(9, 0);
    mat.resize(3, vector<int>(3, 0));

    int n = board.size();
    int m = board[0].size();

    for (int r = 0; r < n; r++)
    {
        for (int c = 0; c < m; c++)
        {
            if (board[r][c] != '.')
            {
                int mask = 1 << (board[r][c] - '0');
                if ((row[r] & mask) == 0 && (col[c] & mask) == 0 && (mat[r / 3][c / 3] & mask) == 0)
                    toggleSudokuBit(r, c, board[r][c] - '0');
                else
                    return false;
            }
        }
    }

    return true;
}