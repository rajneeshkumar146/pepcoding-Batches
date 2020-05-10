#include <iostream>
#include <vector>
using namespace std;

int coinChangePermutation_INF(vector<int> &arr, int lidx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lidx; i < arr.size(); i++)
        if (tar - arr[lidx] >= 0)
            count += coinChangePermutation_INF(arr, 0, tar - arr[lidx], ans + to_string(arr[i]) + " ");

    return count;
}

int coinChangePermutation(vector<int> &arr, int lidx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lidx; i < arr.size(); i++)
    {
        int temp = arr[i];
        if (arr[i] > 0 && tar - arr[i] >= 0)
        {
            arr[i] = -arr[i];
            count += coinChangePermutation(arr, 0, tar - temp, ans + to_string(temp) + " ");
            arr[i] = -arr[i];
        }
    }
    return count;
}

int coinChangeCombination_INF(vector<int> &arr, int lidx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lidx; i < arr.size(); i++)
        if (tar - arr[i] >= 0)
            count += coinChangeCombination_INF(arr, i, tar - arr[i], ans + to_string(arr[i]) + " ");

    return count;
}

int coinChangeCombination(vector<int> &arr, int lidx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lidx; i < arr.size(); i++)
        if (tar - arr[i] >= 0)
            count += coinChangeCombination(arr, i + 1, tar - arr[i], ans + to_string(arr[i]) + " ");

    return count;
}

//Queen's===========================================================

int queensCombination(vector<bool> &rooms, int room, int qpsf, int tnq, string ans) // qpsf: queen place so far.
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int r = room; r < rooms.size(); r++)
        count += queensCombination(rooms, r + 1, qpsf + 1, tnq, ans + "Q" + to_string(qpsf) + "R" + to_string(r) + " ");

    return count;
}

int queensPermutation(vector<bool> &rooms, int room, int qpsf, int tnq, string ans) // qpsf: queen place so far.
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int r = room; r < rooms.size(); r++)
        if (!rooms[r])
        {
            rooms[r] = true;
            count += queensPermutation(rooms, 0, qpsf + 1, tnq, ans + "Q" + to_string(qpsf) + "R" + to_string(r) + " ");
            rooms[r] = false;
        }

    return count;
}

//Sudoku.====================================================================

bool isSafeToPlaceNumber(vector<vector<char>> &board, int x, int y, int num)
{
    //row
    for (int i = 0; i < 9; i++)
        if (board[x][i] - '0' == num)
            return false;

    //col
    for (int i = 0; i < 9; i++)
        if (board[i][y] - '0' == num)
            return false;

    //mat
    x = (x / 3) * 3; // x-=x%3;
    y = (y / 3) * 3; // y-=y%3;
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            if (borad[x + i][y + j] - '0' == num)
                return false;

    return true;
}

vector<int> Srow;
vector<int> Scol;
vector<vector<int>> Smat;

bool solveSudoku_(vector<vector<char>> &board, vector<int> &calls, int idx)
{
    if (idx == calls.size())
        return true;

    int r = calls[idx] / 9;
    int c = calls[idx] % 9;
    for (int num = 1; num <= 9; num++)
    {
        int mask = 1 << num;
        if ((Srow[r] & mask) == 0 && (Scol[c] & mask) == 0 && (Smat[r / 3][c / 3] & mask) == 0)
        {
            board[r][c] = (char)(num + '0');
            Srow[r] ^= mask;
            Scol[c] ^= mask;
            Smat[r / 3][c / 3] ^= mask;

            if (solveSudoku_(board, calls, idx + 1))
                return true;

            board[r][c] = '.';
            Srow[r] ^= mask;
            Scol[c] ^= mask;
            Smat[r / 3][c / 3] ^= mask;
        }
    }
    return false;
}

//==============================================================================

void solveSudoku(vector<vector<char>> &board)
{
    vector<int> calls;
    Srow.assign(9, 0);
    Scol.assign(9, 0);
    Smat.assign(3, vector<int>(3, 0));

    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            if (board[i][j] == '.')
                calls.push_back(i * 9 + j);
            else
            {
                int num = board[i][j] - '0';
                int mask = 1 << num;
                Srow[i] ^= mask;
                Scol[j] ^= mask;
                Smat[i / 3][j / 3] ^= mask;
            }
        }
    }

    solveSudoku_(board, calls, 0);
}

void queenProblem()
{
    vector<bool> rooms(16, false);
    int tnq = 4;
    cout << queensCombination(rooms, 0, 0, tnq, "") << endl;
    // cout << queensPermutation(rooms, 0, 0, tnq, "") << endl;
}

void coinChange()
{
    vector<int> arr{2, 3, 5, 7};
    // vector<int> arr{1,1,1,1,1};
    int tar = 10;
    // cout << coinChangePermutation_INF(arr,0, tar, "") << endl;
    // cout << coinChangePermutation(arr, 0, tar, "") << endl;

    // cout << coinChangeCombination_INF(arr,0, tar, "") << endl;
    cout << coinChangeCombination(arr, 0, tar, "") << endl;
}

int main()
{
    coinChange();
    // queenProblem();
    return 0;
}
