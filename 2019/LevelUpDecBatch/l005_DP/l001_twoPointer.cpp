#include <iostream>
#include <vector>

using namespace std;

void print1D(vector<int> &arr)
{
    for (int ele : arr)
    {
        cout << (ele + " ");
    }
    cout << endl;
}

void print2D(vector<vector<int>> &arr)
{
    for (vector<int> &ar : arr)
    {
        print1D(ar);
    }
}

int fib_memo(int n, vector<int> &dp)
{
    if (n <= 1)
    {
        return dp[n] = n;
    }

    if (dp[n] != -1)
        return dp[n];

    int ans = fib_memo(n - 1, dp) + fib_memo(n - 2, dp);
    return dp[n] = ans;
}

int fib_DP(int N, vector<int> &dp)
{

    for (int n = 0; n <= N; n++)
    {
        if (n <= 1)
        {
            dp[n] = n;
            continue;
        }

        int ans = dp[n - 1] + dp[n - 2]; // fib_01(n - 1, dp) + fib_01(n - 2, dp);

        dp[n] = ans;
    }

    return dp[N];
}

int fib_twoPointer(int N)
{
    int a = 0, b = 1;
    for (int n = 0; n < N; n++)
    {
        // System.out.print(a + " ");

        int sum = a + b;
        a = b;
        b = sum;
    }

    return a;
}

void fibo()
{
    int n = 8;
    vector<int> dp(n + 1, -1);

    cout << (fib_memo(n, dp)) << endl;
    cout << (fib_DP(n, dp)) << endl;
    cout << (fib_twoPointer(n)) << endl;

    print1D(dp);
}

int climbStairs_memo(int n, vector<int> &dp)
{
    if (n <= 1)
    {
        return dp[n] = 1;
    }

    if (dp[n] != 0)
        return dp[n];

    return dp[n] = climbStairs_memo(n - 1, dp) + climbStairs_memo(n - 2, dp);
}

int climbStairs_dp(int N, vector<int> &dp)
{

    for (int n = 0; n <= N; n++)
    {
        if (n <= 1)
        {
            dp[n] = 1;
            continue;
        }

        dp[n] = dp[n - 1] + dp[n - 2]; //climbStairs_memo(n - 1, dp) + climbStairs_memo(n - 2, dp);
    }

    return dp[N];
}

int climbStairs(int n)
{
    vector<int> dp(n + 1, 0);
    return climbStairs_memo(n, dp);
}

int main()
{
    fibo();
    return 0;
}
