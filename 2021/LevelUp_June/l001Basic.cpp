#include <iostream>
#include <vector>
using namespace std;

void printIncreasing(int a, int b)
{
    if (a > b)
        return;
    cout << a << endl;
    printIncreasing(a + 1, b);
}

void printDecreasing(int a, int b)
{

    if (a > b)
        return;
    printDecreasing(a + 1, b);
    cout << a << endl;
}

void printIncreasingDecreasing(int a, int b)
{
    if (a > b)
        return;

    cout << a << endl;
    printIncreasingDecreasing(a + 1, b);
    cout << a << endl;
}

void printOddEven(int a, int b)
{

    if (a > b)
        return;

    if (a % 2 != 0)
        cout << a << endl;
    printOddEven(a + 1, b);
    if (a % 2 == 0)
        cout << a << endl;
}

int fact(int n)
{
    // return n == 0 ? 1 : fact(n - 1) * n;

    if (n == 0)
        return 1;

    int smallAns = fact(n - 1);
    int myAns = smallAns * n;

    return myAns;
}

int power(int a, int b)
{
    return b == 0 ? 1 : power(a, b - 1) * a;
}

int powerBtr(int a, int b)
{
    if (b == 0)
        return 1;

    int smallAns = powerBtr(a, b / 2);
    smallAns *= smallAns;

    return b % 2 != 0 ? smallAns * a : smallAns;
}

void display(vector<int> &arr, int idx)
{
    if (idx == arr.size())
        return;

    cout << (arr[idx]) << endl;
    display(arr, idx + 1);
}

void displayReverse(vector<int> &arr, int idx)
{
    if (idx == arr.size())
        return;

    displayReverse(arr, idx + 1);
    cout << arr[idx] << endl;
}

int maximum(vector<int> &arr, int idx)
{
    if (idx == arr.size())
        return -1e9;
    int maxSF = maximum(arr, idx + 1);
    return max(maxSF, arr[idx]);
}

int minimum(vector<int> &arr, int idx)
{
    if (idx == arr.size())
        return 1e9;
    int minSF = minimum(arr, idx + 1);
    return min(minSF, arr[idx]);
}

bool find(vector<int> &arr, int idx, int data)
{
    if (idx == arr.size())
        return false;
    return arr[idx] == data || find(arr, idx + 1, data);
}

int firstIndex(vector<int> &arr, int idx, int data)
{
    if (idx == arr.size())
        return -1;

    if (arr[idx] == data)
        return idx;
    return firstIndex(arr, idx + 1, data);

    // return arr[idx] == data ? idx : firstIndex(arr,idx +1 ,data);
}

int lastIndex(vector<int> &arr, int idx, int data)
{
    if (idx == arr.size())
        return -1;
    int smallRes = lastIndex(arr, idx + 1, data);
    if (smallRes != -1)
        return smallRes;

    return arr[idx] == data ? idx : -1;
}

// n = 5.
int printTreePath(int n)
{
    if (n == 1 || n == 0)
    {
        cout << "base: " << n << endl;
        return n;
    }
    int ans = 0;

    cout << "pre: " << n << endl;
    ans += printTreePath(n - 1);

    cout << "In: " << n << endl;

    ans += printTreePath(n - 2);
    cout << "post: " << n << endl;

    return ans + 3;
}

vector<int> allIndex(vector<int> &arr, int idx, int data, int count)
{
    if (idx == arr.size())
    {
        vector<int> base(count, 0);
        return base;
    }

    if (arr[idx] == data)
        count++;
    vector<int> ans = allIndex(arr, idx + 1, data, count);
    if (arr[idx] == data)
        ans[count - 1] = idx;

    return ans;
}

vector<string> subSeq(string str, int idx)
{
    if (idx == str.size()())
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> recAns = subSeq(str, idx + 1);
    vector<string> myAns(recAns);
    for (string s : recAns)
        myAns.push_back(str[idx] + s);

    return myAns;
}

// top to bottom
vector<string> mazePath_HVD(int sr, int sc, int er, int ec)
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
        vector<string> Vertical = mazePath_HVD(sr + 1, sc, er, ec);
        for (string s : Vertical)
        {
            myAns.push_back("V" + s);
        }
    }

    if (sc + 1 <= ec && sr + 1 <= er)
    {
        vector<string> Diagonal = mazePath_HVD(sr + 1, sc + 1, er, ec);
        for (string s : Diagonal)
        {
            myAns.push_back("D" + s);
        }
    }
    if (sc + 1 <= ec)
    {
        vector<string> Horizontal = mazePath_HVD(sr, sc + 1, er, ec);
        for (string s : Horizontal)
        {
            myAns.push_back("H" + s);
        }
    }

    return myAns;
}

// bottom to top
int mazePath_HVD(int sr, int sc, int er, int ec, vector<string> ans, string psf)
{
    if (sr == er && sc == ec)
    {
        ans.push_back(psf);
        return 1;
    }

    int count = 0;
    if (sr + 1 <= er)
        count += mazePath_HVD(sr + 1, sc, er, ec, ans, psf + "V");
    if (sr + 1 <= er && sc + 1 <= ec)
        count += mazePath_HVD(sr + 1, sc + 1, er, ec, ans, psf + "D");
    if (sc + 1 <= ec)
        count += mazePath_HVD(sr, sc + 1, er, ec, ans, psf + "H");

    return count;
}

vector<string> mazePath_HVD_multi(int sr, int sc, int er, int ec)
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
        vector<string> Vertical = mazePath_HVD_multi(sr + jump, sc, er, ec);
        for (string s : Vertical)
        {
            myAns.push_back(string(1, 'v') + to_string(jump) + s);
        }
    }

    for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
    {
        vector<string> Diagonal = mazePath_HVD_multi(sr + jump, sc + jump, er, ec);
        for (string s : Diagonal)
        {
            myAns.push_back(string(1, 'D') + to_string(jump) + s);
        }
    }

    for (int jump = 1; sc + jump <= ec; jump++)
    {
        vector<string> Horizontal = mazePath_HVD_multi(sr, sc + jump, er, ec);
        for (string s : Horizontal)
        {
            myAns.push_back(string(1, 'H') + to_string(jump) + s);
        }
    }

    return myAns;
}

//==================================================================

int floodFill(int sr, int sc, vector<vector<int>> &vis, string psf, vector<string> &res, vector<vector<int>> &dir,
              vector<string> &dirS)
{
    int n = vis.size(), m = vis[0].size();
    if (sr == n - 1 && sc == m - 1)
    {
        res.push_back(psf);
        return 1;
    }

    vis[sr][sc] = 0; // block
    int count = 0;
    for (int d = 0; d < dir.size(); d++)
    {
        int r = sr + dir[d][0];
        int c = sc + dir[d][1];

        if (r >= 0 && c >= 0 && r < n && c < m && vis[r][c] == 1)
        {
            count += floodFill(r, c, vis, psf + dirS[d], res, dir, dirS);
        }
    }

    vis[sr][sc] = 1; // unblock
    return count;
}

vector<string> findPath(vector<vector<int>> &m, int n)
{
    vector<vector<int>> dir = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    vector<string> dirS = {"D", "L", "R", "U"};

    vector<string> res;
    if (m[0][0] == 0 || m[n - 1][n - 1] == 0)
        return res;

    int count = floodFill(0, 0, m, "", res, dir, dirS);
    return res;
}

// https://practice.geeksforgeeks.org/problems/special-matrix4201/1#
int floodFill(int sr, int sc, vector<vector<int>> &vis, vector<vector<int>> &dir)
{
    int n = vis.size(), m = vis[0].size();
    if (sr == n - 1 && sc == m - 1)
    {
        return 1;
    }

    vis[sr][sc] = 1; // block
    int count = 0;
    for (int d = 0; d < dir.size(); d++)
    {
        int r = sr + dir[d][0];
        int c = sc + dir[d][1];

        if (r >= 0 && c >= 0 && r < n && c < m && vis[r][c] == 0)
        {
            count += floodFill(r, c, vis, dir);
        }
    }

    vis[sr][sc] = 0; // unblock
    return count;
}

int FindWays(int n, int m, vector<vector<int>> blocked_cells)
{

    vector<vector<int>> vis(n + 1, vector<int>(m + 1, 0));
    for (vector<int> &bc : blocked_cells)
    {
        vis[bc[0]][bc[1]] = 1; // 1 means blocked
    }

    if (vis[1][1] == 1 || vis[n][m] == 1)
        return 0;

    vector<vector<int>> dir = {{1, 0}, {0, 1}};

    return floodFill(1, 1, vis, dir);
}

// https://www.geeksforgeeks.org/rat-in-a-maze-with-multiple-steps-jump-allowed/?ref=rp

void display1D(vector<int> &arr)
{
    for (int ele : arr)
        cout << ele << " ";

    cout << endl;
}

void display2D(vector<vector<int>> &arr)
{
    for (vector<int> &a : arr)
        display1D(a);

    cout << endl;
}
int floodFill(int sr, int sc, vector<vector<int>> &jumpMat, vector<vector<int>> &dir, vector<vector<int>> &ans)
{
    int n = jumpMat.size(), m = jumpMat[0].size();
    if (sr == n - 1 && sc == m - 1)
    {
        ans[sr][sc] = 1;
        display2D(ans);
        ans[sr][sc] = 0;
        return 1;
    }

    int jump = jumpMat[sr][sc];
    jumpMat[sr][sc] = 0; // block
    ans[sr][sc] = 1;     // psf

    int count = 0;
    for (int d = 0; d < dir.size(); d++)
    {
        for (int rad = 1; rad <= jump; rad++)
        {
            int r = sr + rad * dir[d][0];
            int c = sc + rad * dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m)
            {
                if (jumpMat[r][c] != 0)
                    count += floodFill(r, c, jumpMat, dir, ans);
            }
            else
                break;
        }
    }

    jumpMat[sr][sc] = jump; // unblock
    ans[sr][sc] = 0;        // psf
    return count;
}

class pair_
{
public:
    string psf = "";
    int len = 0;

    pair_(string psf, int len)
    {
        this->len = len;
        this->psf = psf;
    }
};

pair_ longestPath(int sr, int sc, vector<vector<bool>> &vis, vector<vector<int>> &dir, vector<string> &dirS)
{
    int n = vis.size(), m = vis[0].size();
    if (sr == n - 1 && sc == m - 1)
    {
        pair_ base("", 0);
        return base;
    }

    vis[sr][sc] = true; // blocked
    pair_ myAns("", -1);
    for (int d = 0; d < dir.size(); d++)
    {
        int r = sr + dir[d][0];
        int c = sc + dir[d][1];

        if (r >= 0 && c >= 0 && r < n && c < m)
        {
            if (!vis[r][c])
            {
                pair_ recAns = longestPath(r, c, vis, dir, dirS);
                if (recAns.len != -1 && recAns.len + 1 > myAns.len)
                {
                    myAns.len = recAns.len + 1;
                    myAns.psf = dirS[d] + recAns.psf;
                }
            }
        }
    }

    vis[sr][sc] = false; // unblocked
    return myAns;
}

void mazePath()
{
    int sr = 0, sc = 0, er = 2, ec = 2;
    vector<string> ans = mazePath_HVD(sr, sc, er, ec);

    for (string s : ans)
        cout << s << " ";
}

int main()
{
    mazePath();
    return 0;
}