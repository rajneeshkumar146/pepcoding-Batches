#include <iostream>
#include <vector>
using namespace std;

int fact(int n)
{
    // return n == 0 ? 1 : fact(n - 1) * n;
    if (n == 0)
        return 1;

    int recAns = fact(n - 1);
    return recAns * n;
}

int power(int a, int b)
{
    if (a == 0)
        return 0;
    if (b == 0)
        return 1;

    int recAns = power(a, b - 1);
    return recAns * a;
}

void printIncr(int a, int b)
{
    if (a == b + 1)
        return;

    if (a % 2 == 0)
        cout << "even: " << a << endl;

    printIncr(a + 1, b);

    if (a % 2 != 0)
        cout << "odd: " << a << endl;
}

void display(vector<int> &arr, int vidx)
{
    if (vidx == arr.size())
        return;
    cout << arr[vidx] << " ";

    display(arr, vidx + 1);
}

bool findData_01(vector<int> &arr, int vidx, int data)
{
    if (vidx == arr.size())
        return false;

    if (arr[vidx] == data)
        return true;

    return findData_01(arr, vidx + 1, data);
}

bool findData_02(vector<int> &arr, int vidx, int data)
{
    if (vidx == arr.size())
        return false;

    bool recAns = findData_02(arr, vidx + 1, data);
    if (recAns)
        return true;

    return arr[vidx] == data;
}

int firstIndex(vector<int> &arr, int vidx, int data)
{
    if (vidx == arr.size())
        return -1;

    if (arr[vidx] == data)
        return vidx;
    return firstIndex(arr, vidx + 1, data);
}

int lastIndex(vector<int> &arr, int vidx, int data)
{
    if (vidx == arr.size())
        return -1;

    int ans = lastIndex(arr, vidx + 1, data);
    if (ans != -1)
        return ans;

    return arr[vidx] == data ? vidx : -1;
}

int permutation_01(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        string nstr = str.substr(0, i) + str.substr(i + 1);
        count += permutation_01(nstr, ans + ch);
    }
    return count;
}

int permutation_02(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    vector<bool> vis(26, false);
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        if (!vis[ch - 'a'])
        {
            vis[ch - 'a'] = true;
            string nstr = str.substr(0, i) + str.substr(i + 1);
            count += permutation_02(nstr, ans + ch);
        }
    }
    return count;
}

void basic()
{
    // printIncr(0, 10);
    // vector<int> arr{10, 20, 30, 40, 50, 60, 70, 80, 90, 100}; // int[] arr={};
    // display(arr, 0);
    cout << permutation_02("aba", "") << endl;
}

//=======================================================

vector<vector<int>> dir = {{0, 1}, {1, 0}, {1, 1}};
vector<string> dir_path = {"H", "V", "D"};
vector<vector<int>> oranges{{2, 1, 0, 2, 1}, {1, 0, 1, 2, 1}, {1, 0, 0, 2, 1}};
int jump = 1;

bool isSafe(int r, int c, vector<vector<int>> &board)
{
    if (r < 0 || c < 0 || r >= board.size() || c >= board[0].size() || board[r][c])
        return false;
    return true;
}

int floodFill(vector<vector<int>> &board, int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    board[sr][sc] = 1;
    int count = 0;
    for (int d = 0; d < dir.size(); d++)
    {
        for (int jump = 1; jump < board.size(); jump++)
        {
            int nsr = sr + jump * dir[d][0];
            int nsc = sc + jump * dir[d][1];
            if (isSafe(nsr, nsc, board))
            {
                count += floodFill(board, nsr, nsc, er, ec, ans + dir_path[d] + to_string(jump) + " ");
            }
        }
    }

    board[sr][sc] = 0;
    return count;
}

bool isSafeForOranges(int r, int c, vector<vector<int>> &board)
{
    if (r < 0 || c < 0 || r >= oranges.size() || c >= oranges[0].size() || oranges[r][c] == 2 || oranges[r][c] == 0)
        return false;
    return true;
}

void rottenOranges(vector<vector<int>> &board, int sr, int sc, string ans)
{

    cout << ans << endl;
    board[sr][sc] = 1;

    for (int d = 0; d < dir.size(); d++)
    {
        int nsr = sr + jump * dir[d][0];
        int nsc = sc + jump * dir[d][1];
        if (isSafeForOranges(nsr, nsc, board))
        {
            oranges[nsr][nsc] = 2;
            rottenOranges(board, nsr, nsc, ans + "(" + to_string(nsr) + ", " + to_string(nsc) + ") ");
        }
    }

    board[sr][sc] = 0;
}

void floodFillSet()
{
    vector<vector<int>> board(3, vector<int>(3, 0));
    cout << floodFill(board, 0, 0,2,2, "");

    // int count = 0;
    // for (int i = 0; i < oranges.size(); i++)
    // {
    //     for (int j = 0; j < oranges[0].size(); j++)
    //     {
    //         if (oranges[i][j] == 2)
    //         {
    //             count++;
    //             rottenOranges(board, i, j, "(" + to_string(i) + ", " + to_string(j) + ") ");
    //         }
    //     }
    // }

    // cout << count << endl;
}

void solve()
{
    // basic();
    floodFillSet();
}

int main()
{
    solve();
    return 0;
}