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
    cout << floodFill(board, 0, 0, 2, 2, "");

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

//========================================================

int combination(int tm, int members, string ans)
{
    if (members == tm)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = members; i <= tm; i++)
    {
        count += combination(tm, i + 1, ans + to_string(i) + " ");
    }

    return count;
}

int CoinInfiniteCombination(vector<int> &coins, int idx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < coins.size(); i++)
    {
        if (tar - coins[i] >= 0)
        {
            count += CoinInfiniteCombination(coins, i + 1, tar - coins[i], ans + to_string(coins[i]) + " ");
        }
    }

    return count;
}

int CoinInfinitePermutation(vector<int> &coins, vector<bool> &vis, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < coins.size(); i++)
    {
        if (tar - coins[i] >= 0 && !vis[i])
        {
            vis[i] = true;
            count += CoinInfinitePermutation(coins, vis, tar - coins[i], ans + to_string(coins[i]) + " ");
            vis[i] = false;
        }
    }

    return count;
}

int queenCombination(vector<bool> boxes, int bn, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = bn; i < boxes.size(); i++)
    {
        count += queenCombination(boxes, i + 1, qpsf + 1, tnq,
                                  ans + "b" + to_string(i) + "q" + to_string(qpsf) + " ");
    }
    return count;
}

int queenpermutation(vector<bool> boxes, int bn, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = bn; i < boxes.size(); i++)
    {
        if (!boxes[i])
        {
            boxes[i] = true;
            count += queenpermutation(boxes, i + 1, qpsf + 1, tnq,
                                      ans + "b" + to_string(i) + "q" + to_string(qpsf) + " ");
            boxes[i] = false;
        }
    }

    return count;
}
int row_ = 10, col_ = 10, queen_ = 10;
vector<bool> row(row_, false);
vector<bool> col(col_, false);
vector<bool> diag(row_ + col_ - 1, false);
vector<bool> antiDiag(row_ + col_ - 1, false);

int boxSize = col_;

int rowBit = 0;
int colBit = 0;
int diagBit = 0;
int antiDiagBit = 0;

int calls = 0;

vector<vector<int>> q_dir = {{0, -1}, {-1, 0}, {-1, -1}, {-1, 1}, {0, 1}, {1, 0}, {1, 1}, {1, -1}};

bool isQueenSafe_array(int r, int c)
{
    return !row[r] && !col[c] && !diag[r + c] && !antiDiag[r - c + boxSize - 1];
}

void markQueen_In_boolean_wala_array(int r, int c)
{
    row[r] = true;
    col[c] = true;
    diag[r + c] = true;
    antiDiag[r - c + boxSize - 1] = true;
}

void unMarkQueen_In_boolean_wala_array(int r, int c)
{
    row[r] = false;
    col[c] = false;
    diag[r + c] = false;
    antiDiag[r - c + boxSize - 1] = false;
}

bool isQueenSafe_bit(int r, int c)
{
    return (rowBit & (1 << r)) == 0 && (colBit & (1 << c)) == 0 && (diagBit & (1 << (r + c))) == 0 && (antiDiagBit & (1 << (r - c + boxSize - 1))) == 0;
}

void mark_UnMark_Queen_In_bit_wala_array(int r, int c)
{
    rowBit ^= 1 << r;
    colBit ^= 1 << c;
    diagBit ^= (1 << (r + c));
    antiDiagBit ^= (1 << (r - c + boxSize - 1));
}

bool isQueenSafe(vector<vector<bool>> &boxes, int r, int c)
{

    for (int d = 0; d < q_dir.size(); d++)
    {
        for (int jump = 1; jump <= boxes.size(); jump++)
        {
            int nr = r + jump * q_dir[d][0];
            int nc = c + jump * q_dir[d][1];

            if (nr >= 0 && nc >= 0 && nr < boxes.size() && nc < boxes[0].size())
            {
                if (boxes[nr][nc])
                    return false;
            }
            else
                break;
        }
    }

    return true;
}

int queen2DCombination(vector<vector<bool>> &boxes, int bn, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;

    for (int i = 0; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();
        if (!boxes[r][c] && isQueenSafe(boxes, r, c))
        {
            boxes[r][c] = true;
            count += queen2DCombination(boxes, i + 1, qpsf + 1, tnq,
                                        ans + "(" + to_string(r) + ", " + to_string(c) + ") ");
            boxes[r][c] = false;
        }
    }

    return count;
}

int queen2DCombination_02(vector<vector<bool>> &boxes, int bn, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    calls++;
    for (int i = bn; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();
        if (isQueenSafe_array(r, c))
        {
            boxes[r][c] = true;
            markQueen_In_boolean_wala_array(r, c);

            count += queen2DCombination_02(boxes, i + 1, qpsf + 1, tnq,
                                           ans + "(" + to_string(r) + ", " + to_string(c) + ") ");

            boxes[r][c] = false;
            unMarkQueen_In_boolean_wala_array(r, c);
        }
    }

    return count;
}

int queen2DCombination_03(int bn, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    calls++;
    for (int i = bn; i < row_ * col_; i++)
    {
        int r = i / col_;
        int c = i % col_;
        if (isQueenSafe_bit(r, c))
        {
            mark_UnMark_Queen_In_bit_wala_array(r, c);

            count += queen2DCombination_03(i + 1, qpsf + 1, tnq,
                                           ans + "(" + to_string(r) + ", " + to_string(c) + ") ");

            mark_UnMark_Queen_In_bit_wala_array(r, c);
        }
    }

    return count;
}

int nqueen_bestWala(int row, int tnq, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    calls++;
    for (int col = 0; col < col_; col++)
    {
        if (isQueenSafe_bit(row, col))
        {
            mark_UnMark_Queen_In_bit_wala_array(row, col);

            count += nqueen_bestWala(row + 1, tnq - 1, ans + "(" + to_string(row) + ", " + to_string(col) + ") ");

            mark_UnMark_Queen_In_bit_wala_array(row, col);
        }
    }
    return count;
}

void PandC()
{
    // vector<int> coins{1, 1, 1, 1, 1};
    // vector<bool> vis(coins.size(), false);
    // cout << combination(5, 0, "") << endl;
    // cout << CoinInfiniteCombination(coins, 0, 3, "") << endl;
    // cout << CoinInfinitePermutation(coins, vis, 10, "") << endl;

    // cout << queenCombination(vis, 0, 0, 3, "") << endl;

    vector<vector<bool>> box(row_, vector<bool>(col_, false));
    // cout << queen2DCombination(box, 0, 0, 4, "") << endl;

    // row.resize(r, false);
    // col.resize(c, false);
    // diag.resize(r + c - 1, false);
    // antiDiag.resize(r + c - 1, false);

    cout << queen2DCombination_02(box, 0, 0, queen_, "") << endl;
    // cout << queen2DCombination_03(0, 0, queen_, "") << endl;
    // cout << nqueen_bestWala(0, queen_, "") << endl;
    cout << calls << endl;
}

void solve()
{
    // basic();
    // floodFillSet();
    PandC();
}

int main()
{
    solve();
    return 0;
}