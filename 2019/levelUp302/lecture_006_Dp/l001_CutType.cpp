#include <iostream>
#include <vector>
#include <list>
#include <algorithm>

using namespace std;

void display(vector<int> &arr)
{
    for (int ele : arr)
        cout << ele << " ";
    cout << endl;
}

void display2D(vector<vector<int>> &arr)
{
    for (vector<int> ar : arr)
        display(ar);
    cout << endl;
}

//CutType.=============================================================

int MCM_rec(vector<int> &arr, int si, int ei, vector<vector<int>> &dp)
{
    if (si + 1 == ei)
        return dp[si][ei] = 0;

    if (dp[si][ei] != -1)
        return dp[si][ei];

    int ans = 1e8;
    for (int cut = si + 1; cut < ei; cut++)
    {
        int leftCost = MCM_rec(arr, si, cut, dp);
        int rightCost = MCM_rec(arr, cut, ei, dp);

        int myCost = leftCost + arr[si] * arr[cut] * arr[ei] + rightCost;
        if (myCost < ans)
            ans = myCost;
    }

    return dp[si][ei] = ans;
}

int MCM_DP(vector<int> &arr, int si, int ei, vector<vector<int>> &dp)
{

    for (int gap = 1; gap < arr.size(); gap++)
    {
        for (si = 0, ei = gap; ei < arr.size(); si++, ei++)
        {
            if (si + 1 == ei)
            {
                dp[si][ei] = 0;
                continue;
            }

            int ans = 1e8;
            for (int cut = si + 1; cut < ei; cut++)
            {
                int leftCost = dp[si][cut];  //MCM_rec(arr, si, cut, dp);
                int rightCost = dp[cut][ei]; //MCM_rec(arr, cut, ei, dp);

                int myCost = leftCost + arr[si] * arr[cut] * arr[ei] + rightCost;
                if (myCost < ans)
                    ans = myCost;
            }

            dp[si][ei] = ans;
        }
    }

    return dp[0][arr.size() - 1];
}

int MCM_DP_Ans(vector<int> &arr)
{

    int n = arr.size();
    vector<vector<string>> sdp(n, vector<string>(n, ""));
    vector<vector<int>> dp(n, vector<int>(n, -1));

    for (int gap = 1; gap < arr.size(); gap++)
    {
        for (int si = 0, ei = gap; ei < arr.size(); si++, ei++)
        {
            if (si + 1 == ei)
            {
                dp[si][ei] = 0;
                sdp[si][ei] = (char)(si + 'A');
                continue;
            }

            int ans = 1e8;
            string sans = "";
            for (int cut = si + 1; cut < ei; cut++)
            {
                int leftCost = dp[si][cut];  //MCM_rec(arr, si, cut, dp);
                int rightCost = dp[cut][ei]; //MCM_rec(arr, cut, ei, dp);

                int myCost = leftCost + arr[si] * arr[cut] * arr[ei] + rightCost;
                if (myCost < ans)
                {
                    ans = myCost;
                    sans = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                }
            }

            dp[si][ei] = ans;
            sdp[si][ei] = sans;
        }
    }

    cout << sdp[0][arr.size() - 1] << endl;
    cout << dp[0][arr.size() - 1] << endl;
}

int costOfSearching(vector<int> &freq, int si, int ei)
{
    int sum = 0;
    for (int i = si; i <= ei; i++)
        sum += freq[i];
    return sum;
}

int OBST_rec(vector<int> &freq, int si, int ei, vector<vector<int>> &dp)
{
    if (dp[si][ei] != 0)
        return dp[si][ei];

    int ans = 1e8;
    for (int cut = si; cut <= ei; cut++)
    {
        int leftTreeCost = (cut == si) ? 0 : OBST_rec(freq, si, cut - 1, dp);
        int rightTreeCost = (cut == ei) ? 0 : OBST_rec(freq, cut + 1, ei, dp);

        int myCost = leftTreeCost + costOfSearching(freq, si, ei) + rightTreeCost;
        if (myCost < ans)
            ans = myCost;
    }

    return dp[si][ei] = ans;
}

int OBST_DP(vector<int> &freq, int si, int ei, vector<vector<int>> &dp)
{
    vector<int> prefixSum(freq.size() + 1, 0);
    for (int i = 1; i < prefixSum.size(); i++)
        prefixSum[i] = prefixSum[i - 1] + freq[i - 1];

    for (int gap = 0; gap < freq.size(); gap++)
    {
        for (int si = 0, ei = gap; ei < freq.size(); si++, ei++)
        {
            int ans = 1e8;
            for (int cut = si; cut <= ei; cut++)
            {
                int leftTreeCost = (cut == si) ? 0 : dp[si][cut - 1];
                int rightTreeCost = (cut == ei) ? 0 : dp[cut + 1][ei];

                // int myCost = leftTreeCost + costOfSearching(freq, si, ei) + rightTreeCost;
                int myCost = leftTreeCost + (prefixSum[ei + 1] - prefixSum[si]) + rightTreeCost;
                if (myCost < ans)
                    ans = myCost;
            }

            dp[si][ei] = ans;
        }
    }

    return dp[0][freq.size() - 1];
}

int burstBallon(vector<int> &arr, int si, int ei, vector<vector<int>> &dp)
{

    if (dp[si][ei] != 0)
        return dp[si][ei];

    int lVal = si == 0 ? 1 : arr[si - 1];
    int rVal = (ei == arr.size() - 1) ? 1 : arr[ei + 1];

    int ans = 0;
    for (int cut = si; cut <= ei; cut++)
    {
        int leftTreeCost = (cut == si) ? 0 : burstBallon(arr, si, cut - 1, dp);
        int rightTreeCost = (cut == ei) ? 0 : burstBallon(arr, cut + 1, ei, dp);

        int myCost = leftTreeCost + lVal * arr[cut] * rVal + rightTreeCost;
        if (myCost > ans)
            ans = myCost;
    }

    return dp[si][ei] = ans;
}

int maxCoins(vector<int> &nums)
{
    int n = nums.size();
    vector<vector<int>> dp(n, vector<int>(n, 0);
    cout<<burstBallon(nums,0,n-1,dp)<<endl;
}

void optimalBinarySearchTree()
{
    vector<int> keys{10, 12, 20};
    vector<int> freq{34, 8, 50};

    int n = freq.size();
    vector<vector<int>> dp(n, vector<int>(n, 0));

    // cout << OBST_rec(freq, 0, n - 1, dp) << endl;
    cout << OBST_DP(freq, 0, n - 1, dp) << endl;

    display2D(dp);
}

void MCM()
{
    vector<int> arr{3, 7, 2, 6, 5, 4};
    int n = arr.size();
    vector<vector<int>> dp(n, vector<int>(n, -1));
    // cout << MCM_rec(arr, 0, n - 1, dp) << endl;
    // cout << MCM_DP(arr, 0, n - 1, dp) << endl;
    // MCM_DP_Ans(arr);

    display2D(dp);
}

void solve()
{
    // MCM();
    optimalBinarySearchTree();
}

int main()
{
    solve();
    return 0;
}