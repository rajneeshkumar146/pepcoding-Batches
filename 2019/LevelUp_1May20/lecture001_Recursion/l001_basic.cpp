#include <iostream>
#include <vector>
using namespace std;

void printIncr(int a, int b)
{
    if (a == b + 1)
        return;

    cout << a << " ";
    printIncr(a + 1, b);
}

void printDec(int a, int b)
{
    if (a == b + 1)
        return;

    printDec(a + 1, b);
    cout << a << " ";
}

void printIncreDec(int a, int b)
{
    if (a == b + 1)
        return;

    if (a % 2 == 0)
        cout << a << " hi " << endl;
    printIncreDec(a + 1, b);
    if (a % 2 != 0)
        cout << a << " bye " << endl;
}

//recursionWithArray.==========================================

void recursionWithArray()
{
    // int n;
    // cin >> n;
    // vector<int> arr(n, 0);
    // for (int i = 0; i < n; i++)
    //     cin >> arr[i];

    vector<int> arr{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
}

int maximum(vector<int> &arr, int idx)
{
    if (idx == arr.size() - 1)
        return arr[idx];

    return max(arr[idx], maximum(arr, idx + 1));
}

int Minimum(vector<int> &arr, int idx)
{
    if (idx == arr.size() - 1)
        return arr[idx];

    return min(arr[idx], Minimum(arr, idx + 1));
}

bool find(vector<int> &arr, int idx, int data)
{
    if (idx == arr.size())
        return false;
    if (arr[idx] == data)
        return true;
    return find(arr, idx + 1, data);
}

//questionSet.==================================================

vector<string> words = {
    ":;/",
    "abc",
    "def",
    "ghi",
    "jkl",
    "mno",
    "pqrs",
    "tuv",
    "wx",
    "yz",
    "&*%",
    "#@$",
};
vector<string> keyPad(string str)
{
    if (str.length() == 0)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    char ch = str[0];
    string nstr = str.substr(1);
    string word = words[ch - '0'];

    vector<string> smallAns = keyPad(nstr);
    vector<string> myAns;

    for (string s : smallAns)
    {
        for (int i = 0; i < word.length(); i++)
            myAns.push_back(word[i] + s);
    }
    return myAns;
}

int keyPad(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    char ch = ques[0];
    string nques = ques.substr(1);
    string word = words[ch - '0'];

    int count = 0;
    for (int i = 0; i < word.length(); i++)
        count += keyPad(nques, ans + word[i]);

    return count;
}

int keyPad02(string ques, int idx, string ans)
{
    if (idx == ques.length())
    {
        cout << ans << endl;
        return 1;
    }

    char ch = ques[idx];
    string word = words[ch - '0'];

    int count = 0;
    for (int i = 0; i < word.length(); i++)
        count += keyPad02(ques, idx + 1, ans + word[i]);

    if (idx + 1 < ques.length())
    {
        int num = (ch - '0') * 10 + (ques[idx + 1] - '0');
        if (num == 10 || num == 11)
        {
            word = words[num];
            for (int i = 0; i < word.length(); i++)
                count += keyPad02(ques, idx + 2, ans + word[i]);
        }
    }
    return count;
}

//PathType.================================================

vector<string> mazepath_HVD(int sr, int sc, int er, int ec)
{
    if (sr == er && sc == ec)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> myAns;

    if (sr + 1 <= er)
    {
        vector<string> Verti = mazepath_HVD(sr + 1, sc, er, ec);
        for (string s : Verti)
            myAns.push_back("V" + s);
    }

    if (sc + 1 <= ec)
    {
        vector<string> Hori = mazepath_HVD(sr, sc + 1, er, ec);
        for (string s : Hori)
            myAns.push_back("H" + s);
    }

    if (sc + 1 <= ec && sr + 1 <= er)
    {
        vector<string> Diag = mazepath_HVD(sr + 1, sc + 1, er, ec);
        for (string s : Diag)
            myAns.push_back("D" + s);
    }
}

vector<string> mazepathMulti_HVD(int sr, int sc, int er, int ec)
{
    if (sr == er && sc == ec)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> myAns;

    for (int jump = 1; sr + jump <= er; jump++)
    {
        vector<string> Verti = mazepathMulti_HVD(sr + jump, sc, er, ec);
        for (string s : Verti)
            myAns.push_back("V" + to_string(jump) + s);
    }

    for (int jump = 1; sc + jump <= ec; jump++)
    {
        vector<string> Hori = mazepathMulti_HVD(sr, sc + jump, er, ec);
        for (string s : Hori)
            myAns.push_back("H" + to_string(jump) + s);
    }

    for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
    {
        vector<string> Diag = mazepathMulti_HVD(sr + jump, sc + jump, er, ec);
        for (string s : Diag)
            myAns.push_back("D" + to_string(jump) + s);
    }
}

int mazepath_HVD(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && ec == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    if (sr + 1 <= er)
        count += mazepath_HVD(sr + 1, sc, er, ec, ans + "V");
    if (sc + 1 <= ec)
        count += mazepath_HVD(sr, sc + 1, er, ec, ans + "H");
    if (sr + 1 <= er && sc + 1 <= ec)
        count += mazepath_HVD(sr + 1, sc + 1, er, ec, ans + "D");

    return count;
}

int mazepath_MultiHVD(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int jump = 1; sr + jump <= er; jump++)
        count += mazepath_MultiHVD(sr + jump, sc, er, ec, ans + "V" + to_string(jump));

    for (int jump = 1; sc + jump <= ec; jump++)
        count += mazepath_MultiHVD(sr, sc + jump, er, ec, ans + "H" + to_string(jump));

    for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
        count += mazepath_MultiHVD(sr + jump, sc + jump, er, ec, ans + "D" + to_string(jump));

    return count;
}

vector<vector<int>> dirA = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
vector<string> dirS = {"L", "N", "U", "E", "R", "S", "D", "W"};

int floodFill(int sr, int sc, int er, int ec, vector<vector<int>> &board, string ans) // 0 free cell and 1 is blocked cell
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0; //mark.
    board[sr][sc] = 1;
    for (int d = 0; d < dirA.size(); d++)
    {

        for (int rad = 1; rad <= board.size(); rad++)
        {
            int x = sr + rad * dirA[d][0];
            int y = sc + rad * dirA[d][1];
            if (x >= 0 && y >= 0 && x < board.size() && y < board[0].size() && board[x][y] == 0)
            {
                count += floodFill(x, y, er, ec, board, ans + dirS[d]);
            }
        }
    }
    board[sr][sc] = 0; //unmark
    return count;
}

void pathType()
{
    int n = 3, m = 5;
    // cout << mazepath_MultiHVD(0, 0, n - 1, m - 1, "") << endl;
    vector<vector<int>> board(3, vector<int>(3, 0));
    cout << floodFill(0, 0, 2, 2, board, "") << endl;
}

void questionSet()
{
    // vector<string> ans = keyPad("489");
    // cout << keyPad("489", "");
    cout << keyPad("1011489", "");

    // for (string s : ans)
    //     cout << s << " ";
}

void basics()
{
    int a, b;
    cin >> a >> b;

    // printIncr(a, b);
    // printDec(a, b);
    printIncreDec(a, b);
}

void solve()
{
    // basics();
    // questionSet();
    pathType();
}

int main()
{
    solve();
    return 0;
}