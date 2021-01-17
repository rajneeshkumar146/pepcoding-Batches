#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int coinChangePermutationInfi(vector<int> &arr, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int ele : arr)
    {
        if (tar - ele >= 0)
            count += coinChangePermutationInfi(arr, tar - ele, ans + to_string(ele));
    }

    return count;
}

int coinChangeCombinationInfi(vector<int> &arr, int idx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
            count += coinChangeCombinationInfi(arr, i, tar - arr[i], ans + to_string(arr[i]));
    }

    return count;
}

int coinChangeCombination(vector<int> &arr, int idx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
            count += coinChangeCombination(arr, i + 1, tar - arr[i], ans + to_string(arr[i]));
    }

    return count;
}

int coinChangePermutation(vector<int> &arr, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        int ele = arr[i];
        if (arr[i] > 0 && tar - ele >= 0)
        {
            arr[i] = -arr[i];
            count += coinChangePermutation(arr, tar - ele, ans + to_string(ele));
            arr[i] = -arr[i];
        }
    }

    return count;
}

int coinChangeCombinationInfiSubSeq(vector<int> &arr, int idx, int tar, string ans)
{
    if (idx == arr.size() || tar == 0)
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (tar - arr[idx] >= 0)
        count += coinChangeCombinationInfiSubSeq(arr, idx, tar - arr[idx], ans + to_string(arr[idx]));
    count += coinChangeCombinationInfiSubSeq(arr, idx + 1, tar, ans);

    return count;
}

int coinChangeCombinationSubSeq(vector<int> &arr, int idx, int tar, string ans)
{
    if (idx == arr.size() || tar == 0)
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (tar - arr[idx] >= 0)
        count += coinChangeCombinationSubSeq(arr, idx + 1, tar - arr[idx], ans + to_string(arr[idx]));
    count += coinChangeCombinationSubSeq(arr, idx + 1, tar, ans);

    return count;
}

int coinChangePermutationInfiSubSeq(vector<int> &arr, int idx, int tar, string ans)
{

    if (idx == arr.size() || tar == 0)
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (tar - arr[idx] >= 0)
        count += coinChangePermutationInfiSubSeq(arr, 0, tar - arr[idx], ans + to_string(arr[idx]));
    count += coinChangePermutationInfiSubSeq(arr, idx + 1, tar, ans);

    return count;
}

int coinChangePermutationSubSeq(vector<int> &arr, int idx, int tar, string ans)
{

    if (idx == arr.size() || tar == 0)
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    int ele = arr[idx];
    if (tar - ele >= 0 && arr[idx] > 0)
    {
        arr[idx] = -arr[idx];
        count += coinChangePermutationSubSeq(arr, 0, tar - ele, ans + to_string(ele));
        arr[idx] = -arr[idx];
    }
    count += coinChangePermutationSubSeq(arr, idx + 1, tar, ans);

    return count;
}

//  Questions.====================================================================

vector<int> smallAns;
vector<vector<int>> res;

void combinationSum(vector<int> &arr, int idx, int target)
{
    if (idx == arr.size() || target == 0)
    {
        if (target == 0)
        {
            res.push_back(smallAns);
        }
        return;
    }

    if (target - arr[idx] >= 0)
    {
        smallAns.push_back(arr[idx]);
        combinationSum(arr, idx, target - arr[idx]);
        smallAns.pop_back();
    }
    combinationSum(arr, idx + 1, target);
}

vector<vector<int>> combinationSum(vector<int> &arr, int target)
{
    combinationSum(arr, 0, target);
    return res;
}

void combinationSum2(vector<int> &arr, int idx, int target)
{
    if (target == 0)
    {
        res.push_back(smallAns);
        return;
    }

    int prev = -1;
    for (int i = idx; i < arr.size(); i++)
    {
        if (prev != -1 && arr[prev] == arr[i])
            continue;

        if (target - arr[i] >= 0)
        {
            smallAns.push_back(arr[i]);
            combinationSum2(arr, i + 1, target - arr[i]);
            smallAns.pop_back();
        }

        prev = i;
    }
}

vector<vector<int>> combinationSum2(vector<int> &arr, int target)
{
    sort(arr.begin(), arr.end());
    combinationSum2(arr, 0, target);
    return res;
}

// leetcode 46
void permute(vector<int> &nums, int count, vector<bool> &vis)
{
    if (count == nums.size())
    {
        res.push_back(smallAns);
        return;
    }
    for (int i = 0; i < nums.size(); i++)
    {
        if (!vis[i])
        {
            vis[i] = true;
            smallAns.push_back(nums[i]);
            permute(nums, count + 1, vis);
            smallAns.pop_back();
            vis[i] = false;
        }
    }
}

// leetcode 47
void permuteUnique(vector<int> &nums, int count, vector<bool> &vis)
{
    if (count == nums.size())
    {
        res.push_back(smallAns);
        return;
    }

    vector<int> visLev(21, 0);
    for (int i = 0; i < nums.size(); i++)
    {
        if (!vis[i] && !visLev[nums[i] + 10])
        {
            vis[i] = true;
            visLev[nums[i] + 10] = true;

            smallAns.push_back(nums[i]);
            permuteUnique(nums, count + 1, vis);
            smallAns.pop_back();
            vis[i] = false;
        }
    }
}

// leetcode 47
void permuteUnique_(vector<int> &nums, int count, vector<bool> &vis)
{
    if (count == nums.size())
    {
        res.push_back(smallAns);
        return;
    }

    int prev = -1;
    for (int i = 0; i < nums.size(); i++)
    {
        if (prev != -1 && nums[prev] == nums[i])
            continue;

        if (!vis[i])
        {
            vis[i] = true;

            smallAns.push_back(nums[i]);
            permuteUnique_(nums, count + 1, vis);
            smallAns.pop_back();
            vis[i] = false;

            prev = i;
        }
    }
}
vector<vector<int>> permuteUnique(vector<int> &nums)
{
    sort(nums.begin(), nums.end());
    vector<bool> vis(nums.size(), false);
    permuteUnique(nums, 0, vis);
    return res;
}
bool isSafeToPlaceQueen(vector<vector<bool>> &boxes, int r, int c)
{
    vector<vector<int>> dir = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    for (int d = 0; d < dir.size(); d++)
    {
        for (int rad = 1; rad < boxes.size(); rad++)
        {
            int x = r + rad * dir[d][0];
            int y = c + rad * dir[d][1];

            if (x >= 0 && y >= 0 && x < boxes.size() && y < boxes[0].size())
            {
                if (boxes[x][y])
                    return false;
            }
            else
                break;
        }
    }

    return true;
}

int nQueen01(vector<vector<bool>> &boxes, int idx, int tnq, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    int n = boxes.size();
    int m = boxes[0].size();
    for (int i = idx; i < n * m; i++)
    {
        int r = i / m;
        int c = i % m;
        if (isSafeToPlaceQueen(boxes, r, c))
        {
            boxes[r][c] = true;
            count += nQueen01(boxes, i + 1, tnq - 1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
            boxes[r][c] = false;
        }
    }
    return count;
}

vector<bool> rowA;
vector<bool> colA;
vector<bool> diagA;
vector<bool> aDiagA;

void toggleNQueen(int r, int c, int n)
{
    rowA[r] = !rowA[r];
    colA[c] = !colA[c];
    diagA[r - c + n - 1] = !diagA[r - c + n - 1];
    aDiagA[r + c] = !aDiagA[r + c];
}

int nQueen02(int n, int idx, int tnq, string ans)
{
    if (tnq == 0)
    {
        cout << (ans) << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < n * n; i++)
    {
        int r = i / n;
        int c = i % n;
        if (!rowA[r] && !colA[c] && !diagA[r - c + n - 1] && !aDiagA[r + c])
        {
            toggleNQueen(r, c, n);
            count += nQueen02(n, i + 1, tnq - 1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
            toggleNQueen(r, c, n);
        }
    }
    return count;
}

vector<vector<int>> board = {{3, 0, 0, 0, 0, 0, 0, 0, 0},
                             {5, 2, 0, 0, 0, 0, 0, 0, 0},
                             {0, 8, 7, 0, 0, 0, 0, 3, 1},
                             {0, 0, 3, 0, 1, 0, 0, 8, 0},
                             {9, 0, 0, 8, 6, 3, 0, 0, 5},
                             {0, 5, 0, 0, 9, 0, 6, 0, 0},
                             {1, 3, 0, 0, 0, 0, 2, 5, 0},
                             {0, 0, 0, 0, 0, 0, 0, 7, 4},
                             {0, 0, 5, 2, 0, 6, 3, 0, 0}};

void display()
{
    for (vector<int> &ar : board)
    {
        for (int ele : ar)
        {
            cout << ele << " ";
        }
        cout << endl;
    }
}

bool isSafeToPlaceNumber(int r, int c, int num)
{
    // Row
    for (int i = 0; i < board[0].size(); i++)
        if (board[r][i] == num)
            return false;

    //Col
    for (int i = 0; i < board.size(); i++)
        if (board[i][c] == num)
            return false;

    // 3 X 3 matrix
    r = (r / 3) * 3;
    c = (c / 3) * 3;
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (board[r + i][c + j] == num)
                return false;
        }
    }

    return true;
}

bool sudokuSolver(int idx)
{
    if (idx == board.size() * board[0].size())
    {
        display();
        cout << endl;
        return true;
    }

    int r = idx / board[0].size();
    int c = idx % board[0].size();
    bool res = false;

    if (board[r][c] != 0)
    {
        return sudokuSolver(idx + 1);
    }

    for (int num = 1; num <= 9; num++)
    {
        if (isSafeToPlaceNumber(r, c, num))
        {
            board[r][c] = num;
            res = res || sudokuSolver(idx + 1);
            board[r][c] = 0;
        }
    }

    return res;
}

bool sudokuSolver_01(vector<int> &loc, int idx)
{
    if (idx == loc.size())
    {
        display();
        cout << endl;
        return true;
    }

    int r = loc[idx] / board[0].size();
    int c = loc[idx] % board[0].size();
    bool res = false;

    for (int num = 1; num <= 9; num++)
    {
        if (isSafeToPlaceNumber(r, c, num))
        {
            board[r][c] = num;
            res = res || sudokuSolver_01(loc, idx + 1);
            board[r][c] = 0;
        }
    }

    return res;
}

vector<int> row(9, 0);
vector<int> col(9, 0);
vector<vector<int>> mat(3, vector<int>(3, 0));

void toggleSudokuBit(int r, int c, int num)
{
    int mask = (1 << num);
    row[r] ^= mask;
    col[c] ^= mask;
    mat[r / 3][c / 3] ^= mask;
}

bool sudokuSolver_Bit(vector<int> &loc, int idx)
{
    if (idx == loc.size())
    {
        display();
        cout << endl;
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
            board[r][c] = num;
            toggleSudokuBit(r, c, num);

            res = res || sudokuSolver_Bit(loc, idx + 1);

            board[r][c] = 0;
            toggleSudokuBit(r, c, num);
        }
    }

    return res;
}

void sudoku()
{
    vector<int> loc;
    int n = board.size();
    int m = board[0].size();
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (board[i][j] == 0)
                loc.push_back(i * m + j);
            else
                toggleSudokuBit(i, j, board[i][j]);
        }
    }
    cout << sudokuSolver_01(loc, 0) << endl;
}

string s1 = "send";
string s2 = "more";
string s3 = "money";
vector<int> mapping;
vector<bool> isNumberTaken;

int stringToNumber(string str)
{
    int res = 0;
    for (int i = 0; i < str.length(); i++)
    {
        res = res * 10 + mapping[str[i] - 'a'];
    }

    return res;
}

int cryptoArith(string &str, int idx)
{
    if (idx == str.length())
    {
        int num1 = stringToNumber(s1);
        int num2 = stringToNumber(s2);
        int num3 = stringToNumber(s3);
        // if (num1 + num2 == num3 && mapping[s1[0] - 'a'] != 0 && mapping[s2[0] - 'a'] != 0 && mapping[s3[0] - 'a'] != 0)
        if (num1 + num2 == num3)
        {

            cout << to_string(num1) << endl
                 << "+" + to_string(num2) << endl
                 << "----" << endl
                 << num3 << endl
                 << endl;
            return 1;
        }

        return 0;
    }

    char ch = str[idx];
    int count = 0;
    for (int num = 0; num <= 9; num++)
    {
        if ((ch == s1[0] || ch == s2[0] || ch == s3[0]) && num == 0)
            continue;

        if (!isNumberTaken[num])
        {
            isNumberTaken[num] = true;
            mapping[ch - 'a'] = num;
            count += cryptoArith(str, idx + 1);
            isNumberTaken[num] = false;
            mapping[ch - 'a'] = -1;
        }
    }
    return count;
}

int cryptoArith()
{
    mapping.resize(26, -1);
    isNumberTaken.resize(10, 0);

    string str = s1 + s2 + s3;
    int uniqueChar = 0;
    for (int i = 0; i < str.length(); i++)
    {
        uniqueChar |= (1 << (str[i] - 'a'));
    }

    str = "";
    for (int i = 0; i < 26; i++)
    {
        int mask = (1 << i);
        if ((uniqueChar & mask) != 0)
            str += (char)(i + 'a');
    }

    // cout << str << endl;
    cout << cryptoArith(str, 0) << endl;
}

int main()
{
    // vector<int> arr{2, 3, 5, 7};
    // int tar = 10;
    // cout << coinChangePermutationInfi(arr, tar, "");
    // cout << coinChangeCombinationInfi(arr, 0,tar, "");
    // cout << coinChangeCombination(arr, 0, tar, "")<<endl;
    // cout << coinChangePermutation(arr,tar, "");

    // cout << coinChangePermutationInfiSubSeq(arr, 0, tar, "") << endl;
    // cout << coinChangeCombinationInfiSubSeq(arr, 0, tar, "") << endl;
    // cout << coinChangeCombinationSubSeq(arr, 0, tar, "") << endl;
    // cout << coinChangePermutationSubSeq(arr, 0, tar, "") << endl;
    // rowA.resize(n, false);

    // sudoku();
    cryptoArith();

    return 0;
}