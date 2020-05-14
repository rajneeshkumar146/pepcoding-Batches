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
            if (board[x + i][y + j] - '0' == num)
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

//Crypto.==========================================================
string str1 = "send";
string str2 = "more";
string str3 = "money";
int isNumberUsed = 0;
vector<int> assignedNumber(26, -1);

int stringToNumber(string str)
{
    int res = 0;
    for (int i = 0; i < str.length(); i++)
        res = res * 10 + assignedNumber[str[i] - 'a'];
    return res;
}

int cryptoSolver_(string &str, int idx)
{
    if (idx == str.length())
    {
        int num1 = stringToNumber(str1);
        int num2 = stringToNumber(str2);
        int num3 = stringToNumber(str3);

        if (assignedNumber[str1[0] - 'a'] != 0 && assignedNumber[str2[0] - 'a'] != 0 && assignedNumber[str3[0] - 'a'] != 0 && num1 + num2 == num3)
        {
            cout << num1 << endl
                 << "+" << num2 << endl
                 << "------" << endl
                 << num3 << endl
                 << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    for (int num = 0; num <= 9; num++)
    {
        int mask = 1 << num;
        if ((isNumberUsed & mask) == 0)
        {
            isNumberUsed ^= mask;
            assignedNumber[str[idx] - 'a'] = num;

            count += cryptoSolver_(str, idx + 1);

            isNumberUsed ^= mask;
            assignedNumber[str[idx] - 'a'] = -1;
        }
    }
    return count;
}

void crypto()
{
    string str = str1 + str2 + str3;
    vector<int> freq(26, 0);
    for (int i = 0; i < str.length(); i++)
        freq[str[i] - 'a']++;

    str = "";
    for (int i = 0; i < 26; i++)
        if (freq[i] > 0)
            str += (char)(i + 'a');

    // cout << str << endl;
    cout << cryptoSolver_(str, 0) << endl;
}

//SetProblem.===============================================================

int equiSet(vector<int> &arr, int idx, int set1, int set2, string set1S, string set2S)
{
    if (idx == arr.size())
    {
        if (set1 == set2)
        {
            cout << set1S + " = " + set2S << endl;
            return 1;
        }

        return 0;
    }

    int count = 0;
    count += equiSet(arr, idx + 1, set1 + arr[idx], set2, set1S + " " + to_string(arr[idx]), set2S);
    count += equiSet(arr, idx + 1, set1, set2 + arr[idx], set1S, set2S + " " + to_string(arr[idx]));

    return count;
}

int equiSet_02(vector<int> &arr, int idx, int set1, int set2, string set1S, string set2S)
{
    if (idx == arr.size())
    {
        if (set1 == set2 && set1 != 0)
        {
            cout << set1S + " = " + set2S << endl;
            return 1;
        }

        return 0;
    }
    int count = 0;
    count += equiSet_02(arr, idx + 1, set1 + arr[idx], set2, set1S + to_string(arr[idx]) + " ", set2S);
    count += equiSet_02(arr, idx + 1, set1, set2 + arr[idx], set1S, set2S + to_string(arr[idx]) + " ");

    count += equiSet_02(arr, idx + 1, set1, set2, set1S, set2S);

    return count;
}

//CROSS WORD.=================================================
vector<vector<char>> board{{'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '-', '-', '-', '-', '-', '-', '+', '+'},
                           {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '-', '-', '-', '-', '-', '+', '+', '+'},
                           {'+', '-', '+', '+', '+', '-', '+', '+', '+', '+'},
                           {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
                           {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
                           {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}};
vector<string> words = {"agra", "norway", "england", "gwalior"};

bool canPlaceHorizontal(string word, int x, int y)
{

    if (y == 0 && word.length() < board[0].size())
    {
        if (board[x][y + word.length()] != '+')
            return false;
    }
    else if ((y + word.length()) == board[0].size() && (word.length() != board[0].size()))
    {
        if (board[x][y - 1] != '+')
            return false;
    }
    else
    {
        if (((y - 1) >= 0 && board[x][y - 1] != '+') || ((y + word.length()) < board[0].size() && board[x][y + word.length()] != '+'))
            return false;
    }

    for (int i = 0; i < word.length(); i++)
    {
        if ((y + i) == board[0].size())
            return false;
        if (board[x][y + i] != '-' && board[x][y + i] != word[i])
            return false;
    }

    return true;
}

vector<bool> placeWordHorizontal(string word, int x, int y)
{
    vector<bool> loc(word.length(), false);
    for (int i = 0; i < word.length(); i++)
    {
        if (board[x][y + i] == '-')
        {
            loc[i] = true;
            board[x][y + i] = word[i];
        }
    }
    return loc;
}

void unPlaceWordHorizontal(string word, int x, int y, vector<bool> &loc)
{
    for (int i = 0; i < word.length(); i++)
        if (loc[i])
            board[x][y + i] = '-';
}

bool canPlaceVertical(string word, int x, int y)
{

    if (x == 0 && word.length() < board.size())
    {
        if (board[x + word.length()][y] != '+')
            return false;
    }
    else if ((x + word.length()) == board.size() && (word.length() != board.size()))
    {
        if (board[x - 1][y] != '+')
            return false;
    }
    else
    {
        if (((x - 1) >= 0 && board[x - 1][y] != '+') || ((x + word.length()) < board.size() && board[x + word.length()][y] != '+'))
            return false;
    }

    for (int i = 0; i < word.length(); i++)
    {
        if ((x + i) == board.size())
            return false;
        if (board[x + i][y] != '-' && board[x + i][y] != word[i])
            return false;
    }

    return true;
}

vector<bool> placeWordVertical(string word, int x, int y)
{
    vector<bool> loc(word.length(), false);
    for (int i = 0; i < word.length(); i++)
    {
        if (board[x + i][y] == '-')
        {
            loc[i] = true;
            board[x + i][y] = word[i];
        }
    }

    return loc;
}

void unPlaceWordVertical(string word, int x, int y, vector<bool> &loc)
{
    for (int i = 0; i < word.length(); i++)
        if (loc[i])
            board[x + i][y] = '-';
}

int crossWord_(int idx)
{
    if (idx == words.size())
    {
        for (int i = 0; i < board.size(); i++)
        {
            for (int j = 0; j < board[0].size(); j++)
                cout << board[i][j] << " ";
            cout << endl;
        }

        return 1;
    }

    string word = words[idx];
    int count = 0;
    for (int i = 0; i < board.size(); i++)
    {
        for (int j = 0; j < board[0].size(); j++)
        {
            if (board[i][j] == '-' || board[i][j] == word[0])
            {
                if (canPlaceHorizontal(word, i, j))
                {
                    vector<bool> loc = placeWordHorizontal(word, i, j);
                    count += crossWord_(idx + 1);
                    unPlaceWordHorizontal(word, i, j, loc);
                }

                if (canPlaceVertical(word, i, j))
                {
                    vector<bool> loc = placeWordVertical(word, i, j);
                    count += crossWord_(idx + 1);
                    unPlaceWordVertical(word, i, j, loc);
                }
            }
        }
    }
    return count;
}

void crossWord()
{
    crossWord_(0);
}

void SetProblem()
{
    vector<int> arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    // cout << equiSet(arr, 1, 10, 0, "10 ", "") << endl;
    cout << equiSet_02(arr, 0, 0, 0, "", "") << endl;
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
    // vector<int> arr{2, 3, 5, 7};
    // vector<int> arr{1,1,1,1,1};
    int tar = 10;
    // cout << coinChangePermutation_INF(arr,0, tar, "") << endl;
    // cout << coinChangePermutation(arr, 0, tar, "") << endl;

    // cout << coinChangeCombination_INF(arr,0, tar, "") << endl;
    // cout << coinChangeCombination(arr, 0, tar, "") << endl;
}

int main()
{
    // coinChange();
    // queenProblem();
    // crypto();
    // SetProblem();
    crossWord();
    return 0;
}
