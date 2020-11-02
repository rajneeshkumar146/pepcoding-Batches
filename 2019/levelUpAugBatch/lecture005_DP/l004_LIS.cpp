#include <iostream>
#include <vector>
#include <list>

using namespace std;

void print(vector<int> &arr)
{
    for (int ele : arr)
    {
        cout << ele << " ";
    }
    cout << endl;
}

void print2D(vector<vector<int>> &arr)
{
    for (vector<int> ar : arr)
    {
        print(ar);
    }
    cout << endl;
}

int LIS_rec(vector<int> &arr, int idx, vector<int> &dp)
{
    if (dp[idx] != 0)
        return dp[idx];

    int maxLen = 1;
    for (int i = idx - 1; i >= 0; i--)
    {
        if (arr[i] < arr[idx])
        {
            int len = LIS_rec(arr, i, dp);
            maxLen = max(maxLen, len + 1);
        }
    }

    return maxLen;
}

int LISRec()
{
    vector<int> arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14};
    int maxLen = 0;

    vector<int> dp(arr.size(), 0);
    for (int i = arr.size(); i >= 0; i--)
    {
        maxLen = max(maxLen, LIS_rec(arr, i, dp));
    }

    return maxLen;
}

// Dp.======================================================

int LIS(vector<int> &arr, vector<int> &dp)
{
    int n = arr.size();
    int maxLen = 0;
    for (int i = 0; i < n; i++)
    {
        dp[i] = 1;
        for (int j = i - 1; j >= 0; j--)
        {
            if (arr[i] > arr[j])
                dp[i] = max(dp[i], dp[j] + 1);
        }
        maxLen = max(maxLen, dp[i]);
    }

    return maxLen;
}

int LDS(vector<int> &arr, vector<int> &dp)
{
    int n = arr.size();
    int maxLen = 0;
    for (int i = n - 1; i >= 0; i--)
    {
        dp[i] = 1;
        for (int j = i + 1; j < n; j++)
        {
            if (arr[i] > arr[j])
                dp[i] = max(dp[i], dp[j] + 1);
        }
        maxLen = max(maxLen, dp[i]);
    }

    return maxLen;
}

int LBS(vector<int> &arr)
{

    int n = arr.size();
    vector<int> DP_LIS(n, 0);
    vector<int> DP_LDS(n, 0);

    LIS(arr,DP_LIS);
    LDS(arr,DP_LDS);

    int maxLen = 0;
    for(int i=0;i<n;i++){
        maxLen = max(maxLen, DP_LIS[i] + DP_LDS[i] - 1);
    }

    return maxLen;
}

void solve()
{
}

int main()
{
    solve();
}