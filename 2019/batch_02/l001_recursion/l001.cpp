#include <iostream>
#include <vector>

#define vi vector<int>
#define vvi vector<vi>
#define vb vector<bool>
#define vvb vector<vb>

using namespace std;

int subseq(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    count += subseq(str.substr(1), ans);
    count += subseq(str.substr(1), ans + str[0]);
    return count;
}

string words[] = {".,:;", "abc", "def", "ghi", "jkl", "mno", "pqrs",
                  "tuv", "wx", "yz", "@$&", "-_%"};
int keyPad(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    int idx1 = ques[0] - '0';

    //  if(idx1==0){
    //      count+=keyPad(ques.substr(1),ans);
    //      return;
    //  }

    string word = words[idx1];

    for (int i = 0; i < word.length(); i++)
    {
        count += keyPad(ques.substr(1), ans + word[i]);
    }

    if (ques.length() >= 2)
    {
        int idx = idx1 * 10 + (ques[1] - '0');
        if (idx >= 10 && idx <= 11)
        {
            word = words[idx];

            for (int i = 0; i < word.length(); i++)
            {
                count += keyPad(ques.substr(2), ans + word[i]);
            }
        }
    }

    return count;
}

int permu(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < str.length(); i++)
    {
        string nstr = str.substr(0, i) + str.substr(i + 1);
        count += permu(nstr, ans + str[i]);
    }
    return count;
}

int permu2(string str, string ans)
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
        int k = str[i] - 'a';
        if (!vis[k])
        {
            vis[k] = true;
            string nstr = str.substr(0, i) + str.substr(i + 1);
            count += permu2(nstr, ans + str[i]);
        }
    }
    return count;
}

int permu3(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    int vis = 0;

    for (int i = 0; i < str.length(); i++)
    {
        int k = str[i] - 'a';
        int mask = 1 << k;
        if ((vis & mask) == 0)
        {
            vis |= mask;
            string nstr = str.substr(0, i) + str.substr(i + 1);
            count += permu3(nstr, ans + str[i]);
        }
    }
    return count;
}

void basicQues()
{
    // cout<<subseq("abc", "")<<endl;
    // cout << keyPad("10110", "") << endl;
    // cout << permu("aba", "") << endl;
    // cout<<permu2("aba","")<<endl;
    cout << permu3("aba", "") << endl;
}

//====================================

int permuInfi(vector<int> &arr, int tar, string ans)
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
            count += permuInfi(arr, tar - arr[i], ans + to_string(arr[i]) + " ");
    }
    return count;
}

int permuWithoutRepe(vector<int> &arr, vector<bool> &vis, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        if (!vis[i] && tar - arr[i] >= 0)
        {
            vis[i] = true;
            count += permuWithoutRepe(arr, vis, tar - arr[i], ans + to_string(arr[i]) + " ");
            vis[i] = false;
        }
    }
    return count;
}

int combiInfi(vector<int> &arr, int idx, int tar, string ans)
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
            count += combiInfi(arr, i, tar - arr[i], ans + to_string(arr[i]) + " ");
    }
    return count;
}

int combiWithoutRepe(vector<int> &arr, int idx, int tar, string ans)
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
            count += combiWithoutRepe(arr, i + 1, tar - arr[i], ans + to_string(arr[i]) + " ");
    }
    return count;
}

int combiInfiSubseq(vector<int> &arr, vector<bool> &vis, int idx, int tar, string ans)
{
    if (tar == 0 || idx == arr.size())
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (!vis[idx] && tar - arr[idx] >= 0)
    {
        vis[idx] = true;
        count += combiInfiSubseq(arr, vis, idx + 1, tar - arr[idx], ans + to_string(idx) + " ");
        vis[idx] = false;
    }
    count += combiInfiSubseq(arr, vis, idx + 1, tar, ans);

    return count;
}

int subset(vector<int> &arr, int idx, string ans)
{

    cout << ans << endl;
    if (idx == arr.size())
    {
        return 1;
    }
    int count = 0;

    for (int i = idx; i < arr.size(); i++)
    {
        count += subset(arr, i + 1, ans + to_string(arr[i]) + " ");
    }
    return count;
}

void combiPermu()
{
    // vector<int> arr{2, 3, 5, 7};
    vector<int> arr(5, 1);
    vector<bool> vis(arr.size(), false);
    int tar = 3;
    // cout << permuInfi(arr, tar, "") << endl;
    // cout << permuWithoutRepe(arr, vis, tar, "") << endl;

    // cout<<combiInfi(arr,0,tar,"")<<endl;
    // cout << combiWithoutRepe(arr, 0, tar, "") << endl;

    cout << combiInfiSubseq(arr, vis, 0, tar, "") << endl;

    // vector<int> arr{1, 2, 3};
    // subset(arr, 0, "");
}

//=====================================

int queenCombi(int box, int idx, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < box; i++)
    {
        count += queenCombi(box, i + 1, qpsf + 1, tnq, ans + "b" + to_string(i) + "q" + to_string(qpsf));
    }
    return count;
}

int queenPermu(vector<bool> &box, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < box.size(); i++)
    {
        if (!box[i])
        {
            box[i] = true;
            count += queenPermu(box, qpsf + 1, tnq, ans + "b" + to_string(i) + "q" + to_string(qpsf));
            box[i] = false;
        }
    }
    return count;
}

bool isSafeToPlacQueen(vector<vector<bool>> &board, int x, int y)
{
    vector<vector<int>> dir = {{-1, 0}, {0, -1}, {-1, -1}, {-1, 1}, {1, 0}, {0, 1}, {1, 1}, {1, -1}};
    for (int i = 0; i < dir.size(); i++)
    {
        for (int rad = 1; rad < max(board.size(), board[0].size()); rad++)
        {
            int nx = x + rad * dir[i][0];
            int ny = y + rad * dir[i][1];
            if (nx < 0 || ny < 0 || nx >= board.size() || ny >= board[0].size())
                break;
            if (board[nx][ny])
                return false;
        }
    }
    return true;
}

// lqpl= last queen placed location
int calls = 0;
int queen2DCombi(vector<vector<bool>> &board, int lqpl, int tnq, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    calls++;
    for (int i = lqpl; i < board[0].size() * board.size(); i++)
    {
        int x = i / board[0].size();
        int y = i % board[0].size();

        if (isSafeToPlacQueen(board, x, y))
        {
            board[x][y] = true;
            count += queen2DCombi(board, i + 1, tnq - 1,
                                  ans + "(" + to_string(x) + ", " + to_string(y) + ") ");
            board[x][y] = false;
        }
    }
    return count;
}

// lqpl= last queen placed location
int queen2DPermu(vector<vector<bool>> &board, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < board.size() * board[0].size(); i++)
    {
        int x = i / board[0].size();
        int y = i % board[0].size();
        if (!board[x][y] && isSafeToPlacQueen(board, x, y))
        {
            board[x][y] = true;
            count += queen2DPermu(board, qpsf + 1, tnq,
                                  ans + "(" + to_string(x) + ", " + to_string(y) + ") ");
            board[x][y] = false;
        }
    }
    return count;
}

int queen2(int n, int r, int tnq, int &col, int &diag, int &adiag, string ans)
{
    if (r == n || tnq == 0)
    {
        if (tnq == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;
    for (int c = 0; c < n; c++)
    {
        int w = 1 << c;
        int x = 1 << (r + c);
        int y = 1 << (r - c + n - 1);
        if (!(col & w) && !(diag & x) && !(adiag & y))
        {
            col ^= w;
            diag ^= x;
            adiag ^= y;

            count += queen2(n, r + 1, tnq - 1, col, diag, adiag,
                            ans + "(" + to_string(r) + ", " + to_string(c) + ") ");
            col ^= w;
            diag ^= x;
            adiag ^= y;
        }
    }

    return count;
}

int queen(int r, int tnq, vb &col, vb &diag, vb &adiag, string ans)
{
    int n = col.size();
    if (r == n || tnq == 0)
    {
        if (tnq == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;
    for (int c = 0; c < n; c++)
    {
        if (!col[c] && !diag[r + c] && !adiag[r - c + n - 1])
        {
            col[c] = true;
            diag[r + c] = true;
            adiag[r - c + n - 1] = true;
            count += queen(r + 1, tnq - 1, col, diag, adiag,
                           ans + "(" + to_string(r) + ", " + to_string(c) + ") ");
            col[c] = false;
            diag[r + c] = false;
            adiag[r - c + n - 1] = false;
        }
    }

    return count;
}

void queens()
{
    //  vector<bool> box(16,false);
    // cout << queenCombi(16, 0, 0, 4, "") << endl;
    // cout<<queenPermu(box,0,3,"")<<endl;

    // vector<vector<bool>> board(10, vector<bool>(10, false));
    // cout << queen2DCombi(board, 0, 10, "") << endl;
    // cout << queen2DPermu(board, 0, 4, "") << endl;

    int r = 10, c = 10;
    // vb col(c, false);
    // vb diag(r + c - 1, false);
    // vb adiag(r + c - 1, false);
    // cout << queen(0, r, col, diag, adiag, "") << endl;

    int col = 0;
    int diag = 0;
    int adiag = 0;
    cout << queen2(c, 0, r, col, diag, adiag, "") << endl;
    // cout << calls << endl;
}

void display(vvi &board)
{
    for (vi ar : board)
    {
        for (int ele : ar)
        {
            cout << ele << " ";
        }
        cout << endl;
    }
    cout << endl;
}
vi row(9, 0);
vi col(9, 0);
vvi mat(3, vi(3, 0));

int sudoku_01(vvi &board, vi &calls, int idx)
{
    if (idx == calls.size())
    {
        display(board);
        return 1;
    }

    int x = calls[idx] / 9;
    int y = calls[idx] % 9;
    int count = 0;
    for (int num = 1; num <= 9; num++)
    {
        int mask = 1 << num;
        if (!(row[x] & mask) && !(col[y] & mask) && !(mat[x / 3][y / 3] & mask))
        {
            board[x][y] = num;
            row[x] ^= mask;
            col[y] ^= mask;
            mat[x / 3][y / 3] ^= mask;

            count += sudoku_01(board, calls, idx + 1);

            board[x][y] = 0;
            row[x] ^= mask;
            col[y] ^= mask;
            mat[x / 3][y / 3] ^= mask;
        }
    }
    return count;
}

void sudoku()
{
    vvi board = {{0, 0, 6, 0, 0, 8, 0, 0, 0},
                 {5, 2, 0, 0, 0, 0, 0, 0, 0},
                 {0, 8, 7, 0, 0, 0, 0, 3, 1},
                 {0, 0, 3, 0, 1, 0, 0, 8, 0},
                 {9, 0, 0, 8, 6, 3, 0, 0, 5},
                 {0, 5, 0, 0, 9, 0, 6, 0, 0},
                 {1, 3, 0, 0, 0, 0, 2, 5, 0},
                 {0, 0, 0, 0, 0, 0, 0, 7, 4},
                 {0, 0, 5, 2, 0, 6, 3, 0, 0}};

    vi calls;
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            if (board[i][j] == 0)
                calls.push_back(i * 9 + j);
            else
            {
                int mask = 1 << board[i][j];
                row[i] |= mask;
                col[j] |= mask;
                mat[i / 3][j / 3] |= mask;
            }
        }
    }
    cout << sudoku_01(board, calls, 0) << endl;
}

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

bool isSafePWH(int x, int y, string word)
{
    for (int i = 0; i < word.size(); i++)
    {
        if (board[x][y + i] != '-' && board[x][y + i] != word[i])
            return false;
    }
    return true;
}

vb PWH(int x, int y, string word)
{
    vb loc(word.size(), false);
    for (int i = 0; i < word.size(); i++)
    {
        if (board[x][y] == '-')
        {
            loc[i] = true;
            board[x][y + i] = word[i];
        }
    }
}

void UnPWH(int x, int y, vb &loc)
{
    for (int i = 0; i < loc.size(); i++)
    {
        if (loc[i])
        {
            board[x][y + i] = '-';
        }
    }
}

bool isSafePWV(int x, int y, string word)
{
    for (int i = 0; i < word.size(); i++)
    {
        if (board[x + i][y] != '-' && board[x + i][y] != word[i])
            return false;
    }
    return true;
}

vb PWV(int x, int y, string word)
{
    vb loc(word.size(), false);
    for (int i = 0; i < word.size(); i++)
    {
        if (board[x][y] == '-')
        {
            loc[i] = true;
            board[x + i][y] = word[i];
        }
    }
}

void UnPWV(int x, int y, vb &loc)
{
    for (int i = 0; i < loc.size(); i++)
    {
        if (loc[i])
        {
            board[x + i][y] = '-';
        }
    }
}

int crossWord(vector<string> &arr, int idx)
{
    if (idx == arr.size())
    {
        for (int i = 0; i < board.size(); i++)
        {
            for (int j = 0; j < board[0].size(); j++)
            {
                cout << board[i][j] << " ";
            }
            cout << endl;
        }
        return 1;
    }

    string word = arr[idx];
    int count = 0;
    for (int i = 0; i < board.size(); i++)
    {
        for (int j = 0; j < board[0].size(); j++)
        {
            if (board[i][j] == '-' || board[i][j] == word[0])
            {
                if (isSafePWH(i, j, word))
                {
                    vb loc = PWH(i, j, word);
                    count += crossWord(arr, idx + 1);
                    UnPWH(i, j, loc);
                }

                if (isSafePWV(i, j, word))
                {
                    vb loc = PWV(i, j, word);
                    count += crossWord(arr, idx + 1);
                    UnPWV(i, j, loc);
                }
            }
        }
        return count;
    }
}

void crossW()
{
    vector<string> words = {"agra", "norway", "england", "gwalior"};
    cout << crossWord(words, 0) << endl;
}

void solve()
{
    // basicQues();
    // combiPermu();
    // queens();
    // sudoku();
    crossW();
}

int main()
{
    solve();
    return 0;
}