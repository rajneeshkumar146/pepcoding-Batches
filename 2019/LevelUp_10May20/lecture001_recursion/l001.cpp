#include <iostream>
#include <vector>
using namespace std;

void printIncreasing(int a, int b)
{
    if (a == b + 1)
        return;

    cout << a << " ";
    printIncreasing(a + 1, b);
}

void printDecreasing(int a, int b)
{
    if (a == b + 1)
        return;
    printDecreasing(a + 1, b);
    cout << a << " ";
}

void printIncreasingDecreasing(int a, int b)
{
    if (a == b + 1)
        return;
    cout << a << " hi" << endl;
    printIncreasingDecreasing(a + 1, b);
    cout << a << " bye" << endl;
}

void printIDoddEven(int a, int b)
{
    if (a == b + 1)
        return;
    if (a % 2 == 0)
        cout << a << " ";
    printIDoddEven(a + 1, b);
    if (a % 2 != 0)
        cout << a << " ";
}

int fibo(int n)
{
    if (n <= 1)
        return n;
    return fibo(n - 1) + fibo(n - 2);
}
//set2======================================================

int maximum(vector<int> &arr, int vidx)
{
    if (vidx == arr.size() - 1)
        return arr[vidx];
    return max(arr[vidx], maximum(arr, vidx + 1));
}

int minimum(vector<int> &arr, int vidx)
{
    if (vidx == arr.size() - 1)
        return arr[vidx];
    return min(arr[vidx], minimum(arr, vidx + 1));
}

bool find(vector<int> &arr, int vidx, int data)
{
    if (vidx == arr.size())
        return false;
    if (arr[vidx] == data)
        return true;
    return find(arr, vidx + 1, data);
}

//set3.===========================================================
vector<string> subseq(string str)
{
    if (str.length() == 0)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    char ch = str[0];
    string nstr = str.substr(1);

    vector<string> smallAns = subseq(nstr);
    vector<string> ans;

    for (string s : smallAns)
    {
        ans.push_back(s);
        ans.push_back(ch + s);
    }

    return ans;
}

vector<string> permutation(string str)
{
    if (str.length() == 0)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    char ch = str[0];
    string nstr = str.substr(1);

    vector<string> smallAns = permutation(nstr);
    vector<string> myAns;

    for (string s : smallAns)
        for (int i = 0; i <= s.size(); i++)
        {
            string cut = s.substr(0, i) + ch + s.substr(i);
            myAns.push_back(cut);
        }

    return myAns;
}

int permutation(string str, string ans)
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
        count += permutation(nstr, ans + ch);
    }
    return count;
}

int permutationUnique(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    vector<bool> vis(26, 0);
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        if (!vis[ch - 'a'])
        {
            vis[ch - 'a'] = true;
            string nstr = str.substr(0, i) + str.substr(i + 1);
            count += permutationUnique(nstr, ans + ch);
        }
    }
    return count;
}

//PathType.==============================================================

vector<string> mazePathMultipleJumps(int sr, int sc, int er, int ec)
{
    if (sr == er && sc == ec)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> ans;
    for (int jump = 1; sc + jump <= ec; jump++)
    {
        vector<string> horizontal = mazePathMultipleJumps(sr, sc + jump, er, ec);
        for (string s : horizontal)
            ans.push_back("H" + to_string(jump) + s);
    }

    for (int jump = 1; sr + jump <= ec; jump++)
    {
        vector<string> Vertical = mazePathMultipleJumps(sr + jump, sc, er, ec);
        for (string s : Vertical)
            ans.push_back("V" + to_string(jump) + s);
    }

    for (int jump = 1; sr + jump <= ec && sc + jump <= ec; jump++)
    {
        vector<string> Diagonal = mazePathMultipleJumps(sr + jump, sc + jump, er, ec);
        for (string s : Diagonal)
            ans.push_back("D" + to_string(jump) + s);
    }

    return ans;
}

int mazePathMultipleJumps(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 1;
    for (int jump = 1; sc + jump <= ec; jump++)
        count += mazePathMultipleJumps(sr, sc + jump, er, ec, ans + "H" + to_string(jump));

    for (int jump = 1; sr + jump <= ec; jump++)
        count += mazePathMultipleJumps(sr + jump, sc, er, ec, ans + "V" + to_string(jump));

    for (int jump = 1; sr + jump <= ec && sc + jump <= ec; jump++)
        count += mazePathMultipleJumps(sr + jump, sc + jump, er, ec, ans + "D" + to_string(jump));

    return count;
}

int dirA[8][2] = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
string dirS[8] = {"L", "N", "U", "E", "R", "S", "D", "W"};

int floodFill(vector<vector<int>> &board, int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    board[sr][sc] = 1;
    int count = 0;
    for (int d = 0; d < 8; d++)
    {
        int x = sr + dirA[d][0];
        int y = sc + dirA[d][1];
        if (x >= 0 && y >= 0 && x < board.size() && y < board[0].size() && board[x][y] == 0)
            count += floodFill(board, x, y, er, ec, ans + dirS[d]);
    }

    board[sr][sc] = 0;
    return count;
}

int floodFill02(vector<vector<int>> &board, int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    board[sr][sc] = 1;
    int count = 0;
    for (int d = 0; d < 8; d++)
    {
        for (int rad = 1; rad <= board.size(); rad++)
        {
            int x = sr + rad * dirA[d][0];
            int y = sc + rad * dirA[d][1];
            if (x >= 0 && y >= 0 && x < board.size() && y < board[0].size() && board[x][y] == 0)
                count += floodFill02(board, x, y, er, ec, ans + dirS[d] + to_string(rad) + " ");
        }
    }

    board[sr][sc] = 0;
    return count;
}

class pairPath
{
public:
    string s;
    int len;

    pairPath(string s, int len)
    {
        this->s = s;
        this->len = len;
    }
};

pairPath floodFill_longestPath(vector<vector<int>> &board, int sr, int sc, int er, int ec)
{
    if (sr == er && sc == ec)
    {
        return pairPath("", 0);
    }

    board[sr][sc] = 1;
    pairPath p("", 0);
    for (int d = 0; d < 8; d++)
    {
        for (int rad = 1; rad <= board.size(); rad++)
        {
            int x = sr + rad * dirA[d][0];
            int y = sc + rad * dirA[d][1];
            if (x >= 0 && y >= 0 && x < board.size() && y < board[0].size() && board[x][y] == 0)
            {
                pairPath smallAns = floodFill_longestPath(board, x, y, er, ec);
                if (smallAns.len + 1 > p.len)
                {
                    p.len = smallAns.len;
                    p.s = smallAns.s + dirS[d] + to_string(rad);
                }
            }
        }
    }

    board[sr][sc] = 0;
    return p;
}

//QueenSet.============================================================

int queenCombination1D(vector<bool> &boxes, int vidx, int qpsf, int tnq, string ans) // qpsf queen place so far.
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = vidx; i < boxes.size(); i++)
    {
        count += queenCombination1D(boxes, i + 1, qpsf + 1, tnq, ans + "B" + to_string(i) + "Q" + to_string(qpsf) + " ");
    }
    return count;
}

int queenPermutation1D(vector<bool> &boxes, int vidx, int qpsf, int tnq, string ans) // qpsf queen place so far.
{

    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = vidx; i < boxes.size(); i++)
    {
        if (!boxes[i])
        {
            boxes[i] = true;
            count += queenPermutation1D(boxes, 0, qpsf + 1, tnq, ans + "B" + to_string(i) + "Q" + to_string(qpsf) + " ");
            boxes[i] = false;
        }
    }
    return count;
}

int queenCombination2D(vector<vector<bool>> &boxes, int vidx, int qpsf, int tnq, string ans) // qpsf queen place so far.
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = vidx; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();
        count += queenCombination2D(boxes, 0, qpsf + 1, tnq, ans + "(" + to_string(r) + ", " + to_string(c) + ") ");
    }
    return count;
}

int queenPermutation2D(vector<vector<bool>> &boxes, int vidx, int qpsf, int tnq, string ans) // qpsf queen place so far.
{

    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = vidx; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();
        if (!boxes[r][c])
        {
            boxes[r][c] = true;
            count += queenPermutation2D(boxes, 0, qpsf + 1, tnq, ans + "(" + to_string(r) + ", " + to_string(c) + ") ");
            boxes[r][c] = false;
        }
    }
    return count;
}

//NQueen.==============================================================

bool isSafeToPlaceQueen(vector<vector<bool>> &boxes, int r, int c)
{
    // int dir[4][2] = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    int dir[8][2] = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    int len = 8;
    for (int d = 0; d < len; d++)
        for (int rad = 1; rad <= boxes.size(); rad++)
        {
            int x = r + rad * dir[d][0];
            int y = c + rad * dir[d][1];

            if (x >= 0 && y >= 0 && x < boxes.size() && y < boxes[0].size())
                if (boxes[x][y])
                    return false;
        }

    return true;
}

int nqueen_01(vector<vector<bool>> &boxes, int vidx, int tnq, string ans) // qpsf queen place so far.
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = vidx; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();
        if (isSafeToPlaceQueen(boxes, r, c))
        {
            boxes[r][c] = true;
            count += nqueen_01(boxes, i + 1, tnq - 1, ans + "(" + to_string(r) + ", " + to_string(c) + ") ");
            boxes[r][c] = false;
        }
    }
    return count;
}

bool nqueen_02(vector<vector<bool>> &boxes, int vidx, int tnq, string ans) // qpsf queen place so far.
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return true;
    }

    bool res = false;
    for (int i = vidx; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();
        if (!boxes[r][c] && isSafeToPlaceQueen(boxes, r, c))
        {
            boxes[r][c] = true;
            res = res || nqueen_02(boxes, 0, tnq - 1, ans + "(" + to_string(r) + ", " + to_string(c) + ") ");
            boxes[r][c] = false;
        }
    }
    return res;
}

int nqueen_03(vector<vector<bool>> &boxes, int vidx, int tnq, string ans) // qpsf queen place so far.
{
    if (tnq == 0 || vidx == boxes.size() * boxes[0].size())
    {
        if (tnq == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    int r = vidx / boxes[0].size();
    int c = vidx % boxes[0].size();
    if (isSafeToPlaceQueen(boxes, r, c))
    {
        boxes[r][c] = true;
        count += nqueen_03(boxes, vidx + 1, tnq - 1, ans + "(" + to_string(r) + ", " + to_string(c) + ") ");
        boxes[r][c] = false;
    }

    count += nqueen_03(boxes, vidx + 1, tnq, ans);

    return count;
}

bool knightTourPath(vector<vector<int>> &board, int r, int c, int move)
{
    board[r][c] = move;
    if (move == 63)
    {
        for (vector<int> &ar : board)
        {
            for (int ele : ar)
                cout << ele << " ";
            cout << endl;
        }
        return true;
    }

    int xMove[8] = {2, 1, -1, -2, -2, -1, 1, 2};
    int yMove[8] = {1, 2, 2, 1, -1, -2, -2, -1};

    bool res = false;
    for (int d = 0; d < 8; d++)
    {
        int x = r + xMove[d];
        int y = c + yMove[d];
        if (x >= 0 && y >= 0 && x < board.size() && y < board[0].size() && board[x][y] == -1)
            res = res || knightTourPath(board, x, y, move + 1);
    }

    board[r][c] = -1;
    return res;
}

//=====================================================================

void nqueen()
{
    // vector<vector<bool>> boxes(4, vector<bool>(4, false));
    // int tnq = 4;
    // cout << nqueen_01(boxes, 0, tnq, "") << endl;
    // cout << nqueen_02(boxes, 0, tnq, "") << endl;
    // cout << nqueen_03(boxes, 0, tnq, "") << endl;

    vector<vector<int>> board(8, vector<int>(8, -1));
    cout << knightTourPath(board, 0, 0, 0) << endl;
}

void QueenSet()
{
    // vector<bool> boxes(5, false);
    // int tnq = 3;

    // cout << queenCombination1D(boxes, 0, 0, tnq, "") << endl;
    // cout << queenPermutation1D(boxes, 0, 0, tnq, "") << endl;

    vector<vector<bool>> boxes(4, vector<bool>(4, false));
    int tnq = 4;
    cout << queenCombination2D(boxes, 0, 0, tnq, "") << endl;
    // cout << queenPermutation2D(boxes, 0, 0, tnq, "") << endl;
}

void pathType()
{
    // cout << mazePathMultipleJumps(0, 0, 2, 2, "") << endl;
    vector<vector<int>> board(3, vector<int>(3, 0));
    // cout << floodFill(board, 0, 0, 2, 2, "") << endl;
    cout << floodFill02(board, 0, 0, 2, 2, "") << endl;
}

void set3()
{
    // cout << permutation("aba", "") << endl;
    cout << permutationUnique("aba", "") << endl;
}

void set2()
{
    int n;
    cin >> n;
    vector<int> arr(n, 0);
    for (int i = 0; i < n; i++)
        cin >> arr[i];
}

void set1()
{
    int a, b;
    cin >> a >> b;
    // printIncreasing(a,b);
    // printDecreasing(a, b);
    // printIncreasingDecreasing(a,b);
    printIDoddEven(a, b);
}

void solve()
{
    // set1();
    // set2();
    // set3();
    // pathType();
    // QueenSet();
    nqueen();
}

int main()
{
    solve();
    return 0;
}