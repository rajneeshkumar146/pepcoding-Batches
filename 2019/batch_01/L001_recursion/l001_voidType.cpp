#include <iostream>
#include <vector>
using namespace std;

void subseq(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << endl;
        return;
    }

    char ch = ques[0];
    subseq(ques.substr(1), ans + ch);
    subseq(ques.substr(1), ans);
}

void subseq_01(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << endl;
        return;
    }

    char ch = ques[0];
    int ch_ = ques[0];
    subseq_01(ques.substr(1), ans + ch);
    subseq_01(ques.substr(1), ans);
    subseq_01(ques.substr(1), ans + to_string(ch_));
}

void permutation(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << endl;
        return;
    }

    // int arr[26] = {0};
    int arr = 0;

    for (int i = 0; i < ques.length(); i++)
    {
        int mask = 1 << (ques[i] - 'a');
        // if (arr[ques[i] - 'a'] == 0)
        if ((arr & mask) == 0)
        {
            // arr[ques[i] - 'a'] = 1;
            arr |= mask;
            string nques = ques.substr(0, i) + ques.substr(i + 1);
            permutation(nques, ques[i] + ans);
        }
    }
}

string words[] = {".,:;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wx", "yz", "@$&", "-_%"};

int keyPad(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int idx = ques[0] - '0';
    int count = 0;
    string word = words[idx];

    for (int i = 0; i < word.length(); i++)
    {
        count += keyPad(ques.substr(1), ans + word[i]);
    }

    if (ques.length() >= 2)
    {
        idx = (ques[0] - '0') * 10 + (ques[1] - '0');
        if (idx >= 10 && idx < 12)
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

//==========================================

int targetSum(vector<int> &arr, int idx, int tar, string ans)
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
    for (int i = idx; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
        {
            count += targetSum(arr, i + 1, tar - arr[i],
                               ans + to_string(arr[i]) + " ");
        }
    }

    return count;
}

int targetSum_02(vector<int> &arr, int idx, int tar, string ans)
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
        count += targetSum_02(arr, idx + 1, tar - arr[idx], ans + to_string(arr[idx]) + " ");

    count += targetSum_02(arr, idx + 1, tar, ans);
    return count;
}

int equiSet(vector<int> &arr, int idx, int set1, int set2, string ans1, string ans2)
{
    if (idx == arr.size())
    {
        if (set1 == set2)
        {
            cout << ans1 << " = " << ans2 << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;

    count += equiSet(arr, idx + 1, set1 + arr[idx], set2, ans1 + to_string(arr[idx]) + " ", ans2);
    count += equiSet(arr, idx + 1, set1, set2 + arr[idx], ans1, ans2 + to_string(arr[idx]) + " ");

    return count;
}

void setType()
{
    vector<int> arr = {10, 20, 30, 40, 50, 60, 70};
    // cout<<targetSum(arr,0,100,"")<<endl;
    // cout << targetSum_02(arr, 0, 100, "") << endl;

    cout << equiSet(arr, 1, 10, 0, "10 ", "") << endl;
}

void basic()
{
    // subseq("abc","");
    permutation("aba", "");
}

//=====================================

int mazePath(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    if (sr + 1 <= er)
    {
        count += mazePath(sr + 1, sc, er, ec, ans + "V");
    }

    if (sc + 1 <= ec)
    {
        count += mazePath(sr, sc + 1, er, ec, ans + "H");
    }
    if (sc + 1 <= ec && sr + 1 <= er)
    {
        count += mazePath(sr + 1, sc + 1, er, ec, ans + "D");
    }

    return count;
}

int mazePath_01(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 1; sr + i <= er; i++)
    {
        count += mazePath_01(sr + i, sc, er, ec, ans + "V" + to_string(i));
    }

    for (int i = 1; sc + i <= ec; i++)
    {
        count += mazePath_01(sr, sc + i, er, ec, ans + "H" + to_string(i));
    }

    for (int i = 1; sc + i <= ec && sr + i <= er; i++)
    {
        count += mazePath_01(sr + i, sc + i, er, ec, ans + "D" + to_string(i));
    }

    return count;
}

void path()
{
    // mazePath(0,0,2,2,"");
    cout << mazePath_01(0, 0, 2, 2, "") << endl;
}

//=================================

int coinPermu_01(vector<int> &arr, int tar, string ans)
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
            count += coinPermu_01(arr, tar - arr[i], ans + to_string(arr[i]) + " ");
        }
    }
    return count;
}

int coinPermu_02(vector<int> &arr, int idx, int tar, string ans)
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
    {
        count += coinPermu_02(arr, 0, tar - arr[idx], ans + to_string(arr[idx]) + " ");
    }
    count += coinPermu_02(arr, idx + 1, tar, ans);
    return count;
}

int coinPermu_03(vector<int> &arr, vector<bool> &vis, int tar, string ans)
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
            count += coinPermu_03(arr, vis, tar - arr[i], ans + to_string(arr[i]) + " ");
            vis[i] = false;
        }
    }
    return count;
}

int coinCombi_01(vector<int> &arr, int idx, int tar, string ans)
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
            count += coinCombi_01(arr, i + 1, tar - arr[i], ans + to_string(arr[i]) + " ");
        }
    }
    return count;
}

int coinCombi_02(vector<int> &arr, int idx, int tar, string ans)
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
    {
        count += coinCombi_02(arr, idx, tar - arr[idx], ans + to_string(arr[idx]) + " ");
    }
    count += coinCombi_02(arr, idx + 1, tar, ans);
    return count;
}

int coinCombi_03(vector<int> &arr, int idx, int tar, string ans)
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
    {
        count += coinCombi_03(arr, idx + 1, tar - arr[idx], ans + to_string(arr[idx]) + " ");
    }
    count += coinCombi_03(arr, idx + 1, tar, ans);
    return count;
}

void coinChange()
{
    vector<int> arr = {2, 3, 5, 7};
    vector<bool> vis(4, 0);
    // cout<<coinPermu_01(arr,10,"")<<endl;
    // cout << coinPermu_02(arr, 0, 10, "") << endl;
    cout << coinPermu_03(arr, vis, 10, "") << endl;

    // cout<<coinCombi_01(arr,0,10,"")<<endl;
    // cout<<coinCombi_02(arr,0,10,"")<<endl;
    // cout<<coinCombi_03(arr,0,10,"")<<endl;
}

//=======================================

int queenBoxCombi(int tnq, int tnb, int lqpsf, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lqpsf; i <= tnb; i++)
    {

        count += queenBoxCombi(tnq - 1, tnb, i + 1,
                               ans + "b" + to_string(i) + "q" +
                                   to_string(tnq) + " ");
    }
    return count;
}

int queenBoxPermu(int tnq, vector<bool> tnb, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < tnb.size(); i++)
    {
        if (!tnb[i])
        {
            tnb[i] = true;
            count += queenBoxPermu(tnq - 1, tnb,
                                   ans + "b" + to_string(i) + "q" +
                                       to_string(tnq) + " ");
            tnb[i] = false;
        }
    }
    return count;
}

int queen2dBoxCombi(int tnq, vector<vector<bool>> &tnb, int lqpsf, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lqpsf; i < tnb.size() * tnb[0].size(); i++)
    {
        int x = i / tnb[0].size();
        int y = i % tnb[0].size();

        count += queen2dBoxCombi(tnq - 1, tnb, i + 1,
                                 ans + "(" + to_string(x) + ", " +
                                     to_string(y) + ") ");
    }
    return count;
}

int queen2DBoxPermu(int tnq, vector<vector<bool>> &tnb, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < tnb.size() * tnb[0].size(); i++)
    {
        int x = i / tnb[0].size();
        int y = i % tnb[0].size();

        if (!tnb[x][y])
        {
            tnb[x][y] = true;
            count += queen2DBoxPermu(tnq - 1, tnb,
                                     ans + "(" + to_string(x) + ", " +
                                         to_string(y) + ") ");
            tnb[x][y] = false;
        }
    }
    return count;
}

void queenBox()
{
    // vector<bool> tnb(5,false);
    // cout << queenBoxCombi(4, 16, 1, "") << endl;
    // cout<<queenBoxPermu(3,tnb,"")<<endl;

    vector<vector<bool>> tnb(4, vector<bool>(4, 0));
    // cout << queen2dBoxCombi(4, tnb, 0, "") << endl;
    cout << queen2DBoxPermu(4, tnb, "") << endl;
}

//================================================

bool isSafeToPlaceQueen(vector<vector<bool>> &boxes, int x, int y)
{
    int xdir[4] = {0, -1, -1, -1};
    int ydir[4] = {-1, -1, 0, 1};

    for (int rad = 1; rad < max(boxes.size(), boxes[0].size()); rad++)
    {
        for (int d = 0; d < 4; d++)
        {
            int r = x + rad * xdir[d];
            int c = y + rad * ydir[d];
            if (r >= 0 && c >= 0 && r < boxes.size() && c < boxes[0].size() && boxes[r][c])
                return false;
        }
    }
    
    return true;
}

int nQueen(int tnq, vector<vector<bool>> &boxes, int lqpsf, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lqpsf; i < boxes.size() * boxes[0].size(); i++)
    {
        int x = i / boxes[0].size();
        int y = i % boxes[0].size();
        if (isSafeToPlaceQueen(boxes, x, y))
        {
            boxes[x][y] = true;
            count += nQueen(tnq - 1, boxes, i + 1,
                            ans + "(" + to_string(x) + ", " +
                                to_string(y) + ") ");
            boxes[x][y] = false;
        }
    }
    return count;
}

void nqueen()
{
    vector<vector<bool>> boxes(7, vector<bool>(7, 0));
    cout << nQueen(7, boxes, 0, "") << endl;
}

//crypto.===============================
string str1 = "send";
string str2 = "more";
string str3 = "money";
vector<int> mapCharToNumber(26, 0);
vector<bool> numberUsed(10, 0); // can used bit.

int stringToNumber(string str)
{
    int num = 0;
    for (int i = 0; i < str.length(); i++)
        num = num * 10 + mapCharToNumber[str[i] - 'a'];

    return num;
}

int crytoSimple(string s, int idx, int num)
{
    if (num == -1 && s.length() != idx)
    {
        return 0;
    }
    if (idx == s.length())
    {
        int num1 = stringToNumber(str1);
        int num2 = stringToNumber(str2);
        int num3 = stringToNumber(str3);
        if (num1 + num2 == num3 && mapCharToNumber[str3[0] - 'a'] != 0)
        {
            cout << " " << num1 << endl
                 << "+" << num2 << endl
                 << "------" << endl
                 << num3 << endl;
            cout << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    //    for(int num=9;num>=0;num--){
    if (!numberUsed[num])
    {
        numberUsed[num] = true;
        mapCharToNumber[s[idx] - 'a'] = num;

        count += crytoSimple(s, idx + 1, 9);

        numberUsed[num] = false;
    }

    count += crytoSimple(s, idx, num - 1);
    //    }

    return count;
}

void crypto()
{
    vector<int> freqMap(26, 0);
    string str = str1 + str2 + str3;

    for (int i = 0; i < str.length(); i++)
        freqMap[str[i] - 'a']++;

    string s = "";

    for (int i = 0; i < 26; i++)
    {
        if (freqMap[i] > 0)
            s += (char)(i + 'a');
    }

    cout << crytoSimple(s, 0, 9) << endl;
}

//sudoku.=========================================

bool isSafeToPlaceNumber(vector<vector<int>> &board, int num,
                         int r, int c)
{
    //loop1 for row.
    for (int i = 0; i < 9; i++)
    {
        if (board[r][i] == num)
            return false;
    }

    // loop2 for col.
    for (int i = 0; i < 9; i++)
    {
        if (board[i][c] == num)
            return false;
    }

    //loop3 for 3x3 matrix.

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

void print2D(vector<vector<int>> &board)
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
}

int sudoku_01(vector<vector<int>> &board, int vidx)
{
    if (vidx == 81)
    {
        print2D(board);
        return 1;
    }

    int i = vidx / 9;
    int j = vidx % 9;
    int count = 0;

    if (board[i][j] == 0)
    {
        for (int num = 1; num <= 9; num++)
        {
            if (isSafeToPlaceNumber(board, num, i, j))
            {
                board[i][j] = num;
                count += sudoku_01(board, vidx + 1);
                board[i][j] = 0;
            }
        }
    }
    else
    {
        count += sudoku_01(board, vidx + 1);
    }

    return count;
}

int sudoku_02(vector<vector<int>> &board, vector<int> &calls, int vidx)
{
    if (vidx == calls.size())
    {
        print2D(board);
        return 1;
    }

    int i = calls[vidx] / 9;
    int j = calls[vidx] % 9;
    int count = 0;

    for (int num = 1; num <= 9; num++)
    {
        if (isSafeToPlaceNumber(board, num, i, j))
        {
            board[i][j] = num;
            count += sudoku_02(board, calls, vidx + 1);
            board[i][j] = 0;
        }
    }

    return count;
}

int rows[9] = {0};
int cols[9] = {0};
int matrix[3][3] = {0};

bool sudoku_03(vector<vector<int>> &board, vector<int> &calls, int vidx)
{
    if (vidx == calls.size())
    {
        print2D(board);
        return true;
    }

    int i = calls[vidx] / 9;
    int j = calls[vidx] % 9;
    // int count = 0;
    bool res = false;

    for (int num = 1; num <= 9 && !res; num++)
    {
        int mask = 1 << num;
        if (((rows[i] & mask) == 0) && ((cols[j] & mask) == 0) && ((matrix[i / 3][j / 3] & mask) == 0))
        {
            board[i][j] = num;
            rows[i] ^= mask;
            cols[j] ^= mask;
            matrix[i / 3][j / 3] ^= mask;

            res = res || sudoku_03(board, calls, vidx + 1);
            // if(count!=0) return count;

            board[i][j] = 0;
            rows[i] ^= mask;
            cols[j] ^= mask;
            matrix[i / 3][j / 3] ^= mask;
        }
    }

    return res;
}

void sudoku()
{
    vector<vector<int>> boxe = {{0, 0, 6, 0, 0, 8, 0, 0, 0},
                                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                                {0, 0, 5, 2, 0, 6, 3, 0, 0}};
    vector<int> calls;
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            if (boxe[i][j] == 0)
            {
                int num = i * 9 + j;
                calls.push_back(num);
            }
            else
            {
                int mask = 1 << boxe[i][j];
                if (((rows[i] & mask) == 0) && ((cols[j] & mask) == 0) && ((matrix[i / 3][j / 3] & mask) == 0))
                {
                    rows[i] |= mask;
                    cols[j] |= mask;
                    matrix[i / 3][j / 3] |= mask;
                }
                else
                {
                    cout << "Invalid Sudoku!" << endl;
                }
            }
        }
    }

    // for (int i = 0; i < 9; i++)
    // {
    //     cout << rows[i] << " ";
    // }
    // cout << endl;

    // // loop2 for col.
    // for (int i = 0; i < 9; i++)
    // {
    //     cout << cols[i] << " ";
    // }
    // cout << endl;

    // for (int i = 0; i < 3; i++)
    // {
    //     for (int j = 0; j < 3; j++)
    //     {
    //         cout << matrix[i][j] << " ";
    //     }
    //     cout << endl;
    // }
    // cout << endl;

    // cout << sudoku_01(boxe, 0) << endl;
    // cout << sudoku_02(boxe, calls, 0) << endl;
    cout << sudoku_03(boxe, calls, 0) << endl;
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
                unplaceWordHorizontal(i,j,pos);
            }

            if (canPlaceVertical(word, i, j))
            {
                vector<bool> pos = placeWordVertical(word, i, j);
                count += crossWordUtil(words, idx + 1);
                unplaceWordVertical(i,j,pos);
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

void solve()
{
    // basic();
    // path();
    // setType();
    // coinChange();
    queenBox();
    // nqueen();
<<<<<<< HEAD
=======
    // crypto();
    // sudoku();
    // crossWord();
>>>>>>> 95c1ad7037670218b089696c7d427b41e6c3abee
}

int main()
{
    solve();
    return 0;
}