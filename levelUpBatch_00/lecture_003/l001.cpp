#include <iostream>
#include <vector>
using namespace std;

int coinPermInfinite(vector<int> &arr, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
        {
            count += coinPermInfinite(arr, tar - arr[i], ans + to_string(arr[i]));
        }
    }
    return count;
}

int coinPermWithoutRepe(vector<int> &arr, vector<bool> &vis, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0 && !vis[i])
        {
            vis[i] = true;
            count += coinPermWithoutRepe(arr, vis, tar - arr[i], ans + to_string(arr[i]));
            vis[i] = false;
        }
    }
    return count;
}

int coinCombiInfinite(vector<int> &arr, int idx, int tar, string ans)
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
        {
            count += coinCombiInfinite(arr, i, tar - arr[i], ans + to_string(arr[i]));
        }
    }
    return count;
}

int coinCombiWithouRepe(vector<int> &arr, int idx, int tar, string ans)
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
        {
            count += coinCombiWithouRepe(arr, i + 1, tar - arr[i], ans + to_string(arr[i]));
        }
    }
    return count;
}

void coinChange()
{
    vector<int> arr = {2, 3, 5, 7};
    vector<bool> vis(arr.size(), false);
    int tar = 10;
    // cout << coinPermInfinite(arr, tar, "") << endl;
    cout << coinPermWithoutRepe(arr, vis, tar, "") << endl;

    // cout << coinCombiInfinite(arr, 0, tar, "") << endl;
    // cout << coinCombiWithouRepe(arr, 0, tar, "") << endl;
}

// lqpl = last queen place location.
// qpsf = queen placed so far.
int queenPermu(int nbox, int idx, int qpsf, int tnq, vector<bool> &vis, string ans)
{
    if (idx == nbox || qpsf == tnq)
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (!vis[idx])
    {
        vis[idx] = true;
        count += queenPermu(nbox, 0, qpsf + 1, tnq, vis, ans + "b" + to_string(idx) + "q" + to_string(qpsf));
        vis[idx] = false;
    }
    count += queenPermu(nbox, idx + 1, qpsf, tnq, vis, ans);
    return count;
}

int queenPermu2(int nbox, int qpsf, int tnq, vector<bool> &vis, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < nbox; i++)
        if (!vis[i])
        {
            vis[i] = true;
            count += queenPermu2(nbox, qpsf + 1, tnq, vis, ans + "b" + to_string(i) + "q" + to_string(qpsf));
            vis[i] = false;
        }
    return count;
}

int queenCombi(int nbox, int lqpl, int qpsf, int tnq, string ans)
{
    if (lqpl == nbox || qpsf == tnq)
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    count += queenCombi(nbox, lqpl + 1, qpsf + 1, tnq, ans + "b" + to_string(lqpl) + "q" + to_string(qpsf));
    count += queenCombi(nbox, lqpl + 1, qpsf, tnq, ans);

    return count;
}

void queen()
{
    // cout << queenCombi(5, 0, 0, 3, "") << endl;

    vector<bool> vis(5, false);
    // cout << queenPermu(5, 0, 0, 3, vis, "") << endl;
    cout << queenPermu2(5, 0, 3, vis, "") << endl;
}

//===============================================

bool isSafeToPlaceNumber(vector<vector<int>> &board, int num, int x, int y)
{
    //row
    for (int c = 0; c < 9; c++)
    {
        if (board[x][c] == num)
            return false;
    }

    //col
    for (int r = 0; r < 9; r++)
    {
        if (board[r][y] == num)
            return false;
    }

    //matrix
    int r = (x / 3) * 3;
    int c = (y / 3) * 3;
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

bool sudoku_01(vector<vector<int>> &board, vector<int> &calls, int idx)
{
    if (idx == calls.size())
    {
        for (vector<int> ar : board)
        {
            for (int ele : ar)
            {
                cout << ele << " ";
            }
            cout << endl;
        }
        cout << endl;
        return true;
    }

    int i = calls[idx] / 9;
    int j = calls[idx] % 9;
    bool res = false;

    for (int num = 1; num < 10; num++)
    {
        if (isSafeToPlaceNumber(board, num, i, j))
        {
            board[i][j] = num;
            res = res || sudoku_01(board, calls, idx + 1);
            if (res)
                return res;
            board[i][j] = 0;
        }
    }

    return res;
}

bool sudoku_02(vector<vector<int>> &board, vector<int> &calls, int idx, vector<int> &rows, vector<int> &cols, vector<vector<int>> &mat)
{
    if (idx == calls.size())
    {
        for (vector<int> ar : board)
        {
            for (int ele : ar)
            {
                cout << ele << " ";
            }
            cout << endl;
        }
        cout << endl;
        return true;
    }

    int i = calls[idx] / 9;
    int j = calls[idx] % 9;
    bool res = false;

    for (int num = 1; num < 10; num++)
    {
        int mask = (1 << num);
        if ((rows[i] & mask) == 0 && (cols[j] & mask) == 0 && (mat[i / 3][j / 3] & mask) == 0)
        {
            board[i][j] = num;
            rows[i] ^= mask;
            cols[j] ^= mask;
            mat[i / 3][j / 3] ^= mask;

            res = res || sudoku_02(board, calls, idx + 1, rows, cols, mat);

            board[i][j] = 0;
            rows[i] ^= mask;
            cols[j] ^= mask;
            mat[i / 3][j / 3] ^= mask;
        }
    }

    return res;
}

void sudoku()
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

    vector<int> calls, rows(9, 0), cols(9, 0);
    vector<vector<int>> mat(3, vector<int>(3, 0));
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            if (board[i][j] == 0)
            {
                calls.push_back((i * 9 + j));
            }
            else
            {
                int mask = 1 << board[i][j];
                if ((rows[i] & mask) == 0 && (cols[j] & mask) == 0 && (mat[i / 3][j / 3] & mask) == 0)
                {
                    rows[i] |= mask;
                    cols[j] |= mask;
                    mat[i / 3][j / 3] |= mask;
                }
                else
                {
                    cout << "Sudoku is not valid!" << endl;
                    return;
                }
            }
        }
    }

    // cout << sudoku_01(board, calls, 0) << endl;
    cout << sudoku_02(board, calls, 0, rows, cols, mat) << endl;
}

//=======================================
string str1 = "send";
string str2 = "more";
string str3 = "money";
int vis = 0;
vector<int> maping(26, 0);

int getIntFromString(string str)
{
    int res = 0;
    for (int i = 0; i < str.length(); i++)
    {
        res = res * 10 + maping[(str[i] - 'a')];
    }
    return res;
}

int crypto(string str, int idx)
{
    if (idx == str.length())
    {
        int a = getIntFromString(str1);
        int b = getIntFromString(str2);
        int c = getIntFromString(str3);
        if (a + b == c)
        {
            cout << a << endl
                 << "+" << b << endl
                 << "------" << endl
                 << c << endl
                 << endl;
            return 1;
        }
        return 0;
    }
    int ch = str[idx] - 'a';
    int count = 0;
    for (int num = 0; num < 10; num++)
    {
        int mask = 1 << num;
        if ((vis & mask) == 0)
        {
            if (str1[0] == str[idx] && num == 0)
                continue;
            if (str2[0] == str[idx] && num == 0)
                continue;
            if (str3[0] == str[idx] && num == 0)
                continue;

            vis ^= mask;
            maping[ch] = num;

            count += crypto(str, idx + 1);

            vis ^= mask;
            maping[ch] = 0;
        }
    }
    return count;
}

void crypto()
{
    string str = str1 + str2 + str3;
    int freq[26] = {0};
    for (int i = 0; i < str.length(); i++)
    {
        freq[str[i] - 'a']++;
    }

    string ans = "";
    for (int i = 0; i < 26; i++)
    {
        // cout<<freq[i];
        if (freq[i] != 0)
        {
            ans += (char)(i + 'a');
        }
    }
    cout << endl
         << ans << endl;
    cout << crypto(ans, 0) << endl;
}

//======================================

vector<vector<char>> board = {
    {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
    {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
    {'+', '-', '-', '-', '-', '-', '-', '-', '+', '+'},
    {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
    {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
    {'+', '-', '-', '-', '-', '-', '-', '+', '+', '+'},
    {'+', '-', '+', '+', '+', '-', '+', '+', '+', '+'},
    {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
    {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
    {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}};

bool canPlaceVertical(string word, int r, int c)
{
    if (r == 0 && word.length() != board.size())
    {
        if (board[r + word.length()][c] != '+')
            return false;
    }
    else if ((r + word.length()) == board.size() && word.length() != board.size())
    {
        if (board[r - 1][c] != '+')
            return false;
    }
    else
    {
        if (board[r - 1][c] != '+' || board[r + word.length()][c] != '+')
            return false;
    }

    for (int i = 0; i < word.length(); i++)
    {
        if (!(board[r + i][c] == '-' || word[i] == board[r + i][c]))
        {
            return false;
        }
    }
    return true;
}

vector<bool> placeWordVertical(string word, int r, int c)
{
    vector<bool> pos(word.length(), false);
    for (int i = 0; i < word.length(); i++)
    {
        if (board[r + i][c] == '-')
        {
            board[r + i][c] = word[i];
            pos[i] = true;
        }
    }

    return pos;
}

void unplaceWordVertical(int r, int c, vector<bool> pos)
{
    for (int i = 0; i < pos.size(); i++)
    {
        if (pos[i])
        {
            board[r + i][c] = '-';
        }
    }
}

bool canPlaceHorizontal(string word, int r, int c)
{
    if (c == 0 && word.length() != board[0].size())
    {
        if (board[r][c + word.length()] != '+')
            return false;
    }
    else if ((c + word.length()) == board[0].size() && word.length() != board[0].size())
    {
        if (board[r][c - 1] != '+')
            return false;
    }
    else
    {
        if (board[r][c - 1] != '+' || board[r][c + word.length()] != '+')
            return false;
    }

    for (int i = 0; i < word.length(); i++)
    {
        if (!(board[r][c + i] == '-' || word[i] == board[r][c + i]))
        {
            return false;
        }
    }
    return true;
}

vector<bool> placeWordHorizontal(string word, int r, int c)
{
    vector<bool> pos(word.length(), false);
    for (int i = 0; i < word.length(); i++)
    {
        if (board[r][c + i] == '-')
        {
            board[r][c + i] = word[i];
            pos[i] = true;
        }
    }

    return pos;
}

void unplaceWordHorizontal(int r, int c, vector<bool> pos)
{
    for (int i = 0; i < pos.size(); i++)
    {
        if (pos[i])
        {
            board[r][c + i] = '-';
        }
    }
}

int crossWordUtil(vector<string> &words, int idx)
{
    if (idx == words.size())
    {
        for (vector<char> ar : board)
        {
            for (char ele : ar)
            {
                cout << ele << " ";
            }
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

            if (canPlaceHorizontal(word, i, j))
            {
                vector<bool> pos = placeWordHorizontal(word, i, j);
                count += crossWordUtil(words, idx + 1);
                unplaceWordHorizontal(i, j, pos);
            }

            if (canPlaceVertical(word, i, j))
            {
                vector<bool> pos = placeWordVertical(word, i, j);
                count += crossWordUtil(words, idx + 1);
                unplaceWordVertical(i, j, pos);
            }
        }
    }
    return count;
}

void crossWord()
{
    vector<string> words = {"agra", "norway", "england", "gwalior"};
    cout << crossWordUtil(words, 0) << endl;
}

int main()
{
    // coinChange();
    // queen();
    // sudoku();
    // crypto();
    crossWord();
    return 0;
}