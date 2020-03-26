#include <iostream>
#include <vector>

#define vi vector<int>
#define vii vector<vi>

using namespace std;

void display(vi &dp)
{
    for (int ele : dp)
        cout << ele << " ";
    cout << endl;
}

void display2D(vii &dp)
{
    for (vi &ar : dp)
    {
        for (int &ele : ar)
            cout << ele << " ";
        cout << endl;
    }
    cout << endl;
}

int fibo_01(int n, vector<int> &dp)
{
    if (n <= 1)
    {
        dp[n] = n;
        return n;
    }

    if (dp[n] != 0)
        return dp[n];
    int ans = fibo_01(n - 1, dp) + fibo_01(n - 2, dp);
    dp[n] = ans; // dp will on and off.
    return ans;
}

int fibo_02(int n, vector<int> &dp)
{

    for (int i = 0; i <= n; i++)
    {
        if (i <= 1)
        {
            dp[i] = i;
            continue;
        }

        dp[i] = dp[i - 1] + dp[i - 2];
    }

    return dp[n];
}

int mazePathHV_01(int sr, int sc, int er, int ec, vii &dp)
{
    if (sr == er && sc == ec)
    {
        dp[sr][sc] = 1;
        return 1;
    }

    int count = 0;
    if (dp[sr][sc] != 0)
        return dp[sr][sc];
    if (er == ec && dp[sc][sr] != 0)
        return dp[sc][sr];

    if (sr + 1 <= er)
        count += mazePathHV_01(sr + 1, sc, er, ec, dp);
    if (sc + 1 <= ec)
        count += mazePathHV_01(sr, sc + 1, er, ec, dp);
    if (sc + 1 <= ec && sr + 1 <= er)
        count += mazePathHV_01(sr + 1, sc + 1, er, ec, dp);

    dp[sr][sc] = count;
    if (er == ec)
        dp[sc][sr] = count;

    return count;
}

int mazePathHV_02(int er, int ec, vii &dp)
{

    for (int sr = er; sr >= 0; sr--)
    {
        for (int sc = ec; sc >= 0; sc--)
        {
            if (sr == er && sc == ec)
            {
                dp[sr][sc] = 1;
                continue;
            }

            int count = 0;
            if (sr + 1 <= er)
                count += dp[sr + 1][sc];
            if (sc + 1 <= ec)
                count += dp[sr][sc + 1];
            if (sc + 1 <= ec && sr + 1 <= er)
                count += dp[sr + 1][sc + 1];

            dp[sr][sc] = count;
        }
    }

    return dp[0][0];
}

int mazePathMulti_01(int sr, int sc, int er, int ec, vii &dp)
{
    if (sr == er && sc == ec)
    {
        dp[sr][sc] = 1;
        return 1;
    }

    if (dp[sr][sc] != 0)
        return dp[sr][sc];

    int count = 0;
    for (int jump = 1; sr + jump <= er; jump++)
        count += mazePathMulti_01(sr + jump, sc, er, ec, dp);
    for (int jump = 1; sc + jump <= ec; jump++)
        count += mazePathMulti_01(sr, sc + jump, er, ec, dp);
    for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
        count += mazePathMulti_01(sr + jump, sc + jump, er, ec, dp);

    dp[sr][sc] = count;
    return count;
}

int mazePathMulti_02(int er, int ec, vii &dp)
{

    for (int sr = er; sr >= 0; sr--)
    {
        for (int sc = ec; sc >= 0; sc--)
        {
            if (sr == er && sc == ec)
            {
                dp[sr][sc] = 1;
                continue;
            }

            int count = 0;
            for (int jump = 1; sr + jump <= er; jump++)
                count += dp[sr + jump][sc];
            for (int jump = 1; sc + jump <= ec; jump++)
                count += dp[sr][sc + jump];
            for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
                count += dp[sr + jump][sc + jump];

            dp[sr][sc] = count;
        }
    }
    return dp[0][0];
}

void set1()
{
    int n = 3;
    // vector<int> dp(n + 1, 0);
    // cout << fibo_01(n, dp) << endl;
    // display(dp);

    vii dp(n, vi(n, 0));
    // cout << mazePathHV_01(0, 0, n - 1, n - 1, dp) << endl;
    // cout << mazePathHV_02(n - 1, n - 1, dp) << endl;

    // cout << mazePathMulti_01(0, 0, n - 1, n - 1, dp) << endl;
    cout << mazePathMulti_02( n - 1, n - 1, dp) << endl;
    
    display2D(dp);
}

void solve()
{
    set1();
}

int main()
{
    solve();
    return 0;
}