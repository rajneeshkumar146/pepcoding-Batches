// solving a sudoku game using recursion
using namespace std;
#include <iostream>
#include <vector>

// Display function for printing sudoku board
void display(vector<vector<int>> &board)
{
    for (int i = 0; i < board.size(); i++)
    {
        for (int j = 0; j < board[0].size(); j++)
        {
            cout << board[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
}

// Function to check if a given num could be placed at the given index of a sudoku board
bool checkNum(vector<vector<int>> &board, int num, int x, int y)
{
    for (int var = 0; var < 9; var++)
    {
        if (board[var][y] == num) //checking the present column
        {
            // cout << "Found num at " << var << ',' << y << endl;
            return false;
        }
        if (board[x][var] == num) //checking the present row
        {
            // cout << "Found num at " << x << ',' << var << endl;
            return false;
        }
    }

    int r = (x / 3) * 3;
    int c = (y / 3) * 3;

    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
        {
            if (board[r + i][c + j] == num)
            {
                // cout << "Found num at " << r + i << ',' << c + j << endl;
                return false;
            }
        }

    return true;
}

// returns the first solution. use count method for all possible solutions
bool solveSudoku_singleSol(vector<vector<int>> &board, int idx)
{
    if (idx == board.size() * board[0].size())
    {
        display(board);
        return true;
    }

    bool res = false;
    int x = idx / board[0].size();
    int y = idx % board[0].size();

    if (board[x][y] == 0)
    {
        for (int num = 1; num <= 9; num++)
        {
            // cout << "checking for " << num << endl;
            if (checkNum(board, num, x, y))
            {
                board[x][y] = num;
                // cout << "set the value at (" << x << ',' << y << ") as :" << num << endl;
                res = res || solveSudoku_singleSol(board, idx + 1);
                if (res)
                {
                    // cout << "returning True";
                    return res;
                }
                // cout << "UNset the value at (" << x << ',' << y << ") " << endl;

                board[x][y] = 0;
            }
            else
            {
                // cout << num << " didnt work for "
                //      << "(" << x << ',' << y << ")" << endl;
            }
        }
    }
    else
    {
        res = res || solveSudoku_singleSol(board, idx + 1);
    }

    return res;
}

// optimized version of above code: use an array of indexes having
int solveSudoku_only0indices(vector<vector<int>> &board, int idx, vector<int> &indices)
{
    if (idx == indices.size())
    {
        display(board);
        return 1;
    }

    int count = 0;
    int cell_no = indices[idx];
    int x = cell_no / board[0].size();
    int y = cell_no % board[0].size();

    for (int num = 1; num <= 9; num++)
    {
        if (checkNum(board, num, x, y))
        {
            board[x][y] = num;
            count += solveSudoku_only0indices(board, idx + 1, indices);
            board[x][y] = 0;
        }
    }

    return count;
}

// Debug function to print all set indices in a bit variable
void printBitIndices(int binary_no, int size)
{
    cout << "The indices set are : ";
    for (int i = 0; i <= size; i++)
    {
        int mask = (1 << i);
        if (binary_no & mask)
            cout << i << " ";
    }
    cout << endl;
}

// using bitMasking with optimization used above
int Sudoku_bitMasking(vector<vector<int>> &board, int idx, vector<int> &indices, vector<int> &bit_r, vector<int> &bit_c, vector<vector<int>> &bit_mat)
{
    if (idx == indices.size())
    {
        display(board);
        return 1;
    }

    int count = 0;
    int cell_no = indices[idx];
    int x = cell_no / board[0].size();
    int y = cell_no % board[0].size();

    for (int num = 1; num <= 9; num++)
    {
        int mask = (1 << num);
        if (!(bit_r[x] & mask) && !(bit_c[y] & mask) && !(bit_mat[(x / 3)][(y / 3)] & mask))
        {
            board[x][y] = num;
            bit_r[x] ^= mask;
            bit_c[y] ^= mask;
            bit_mat[(x / 3)][(y / 3)] ^= mask;

            count += Sudoku_bitMasking(board, idx + 1, indices, bit_r, bit_c, bit_mat);

            board[x][y] = 0;
            bit_r[x] ^= mask;
            bit_c[y] ^= mask;
            bit_mat[(x / 3)][(y / 3)] ^= mask;
        }
    }
    return count;
}

int main()
{
    vector<vector<int>> board = {{0, 0, 6, 0, 0, 8, 0, 0, 0},
                                 {5, 2, 0, 0, 0, 0, 0, 0, 0},
                                 {0, 8, 7, 0, 0, 0, 0, 3, 1},
                                 {0, 0, 3, 0, 1, 0, 0, 8, 0},
                                 {9, 0, 0, 8, 6, 3, 0, 0, 5},
                                 {0, 5, 0, 0, 9, 0, 6, 0, 0},
                                 {1, 3, 0, 0, 0, 0, 2, 5, 0},
                                 {0, 0, 0, 0, 0, 0, 0, 7, 4},
                                 {0, 0, 5, 2, 0, 6, 3, 0, 0}};

    // cout << solveSudoku_singleSol(board, 0);

    // creating a list of all indexes having 0 lvalues
    vector<int> indices, bit_r(9, 0), bit_c(9, 0);
    vector<vector<int>> bit_mat(3, vector<int>(3, 0));

    for (int i = 0; i < board.size(); i++)
    {
        for (int j = 0; j < board[0].size(); j++)
        {
            if (board[i][j] == 0)
            {
                indices.push_back(i * 9 + j);
            }
            else
            {
                int mask = (1 << board[i][j]);

                if (!(bit_r[i] & mask) && !(bit_c[j] & mask) && !(bit_mat[(i / 3)][(j / 3)] & mask))
                {
                    bit_r[i] |= mask;
                    bit_c[j] |= mask;
                    bit_mat[(i / 3)][(j / 3)] |= mask;
                }
                else
                {
                    cout << "Sudoku not valid";
                }
            }
        }
    }

    // cout << solveSudoku_only0indices(board, 0, indices);

    cout << Sudoku_bitMasking(board, 0, indices, bit_r, bit_c, bit_mat);

    return 0;
}