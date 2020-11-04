#include <iostream>
#include <vector>
#include <list>
#include <algorithm>


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

    LIS(arr, DP_LIS);
    LDS(arr, DP_LDS);

    int maxLen = 0;
    for (int i = 0; i < n; i++)
    {
        maxLen = max(maxLen, DP_LIS[i] + DP_LDS[i] - 1);
    }

    return maxLen;
}

int LISumSubsequnece(vector<int> &arr, vector<int> &dp)
{
    int n = arr.size();
    int maxSum = 0;
    for (int i = 0; i < n; i++)
    {
        dp[i] = arr[i];
        for (int j = i - 1; j >= 0; j--)
        {
            if (arr[i] > arr[j])
                dp[i] = max(dp[i], dp[j] + arr[i]);
        }
        maxSum = max(maxSum, dp[i]);
    }

    return maxSum;
}

//https://www.geeksforgeeks.org/maximum-sum-increasing-subsequence-dp-14/
//https://www.geeksforgeeks.org/maximum-sum-bi-tonic-sub-sequence/

int minNoOfDeletions(vector<int> &arr)
{
    int n = arr.size();
    vector<int> dp(n, 0);

    int maxLen = 0;
    for (int i = 0; i < n; i++)
    {
        dp[i] = 1;
        for (int j = i - 1; j >= 0; j--)
        {
            if (arr[i] >= arr[j])
                dp[i] = max(dp[i], dp[j] + 1);
        }
        maxLen = max(maxLen, dp[i]);
    }

    return n - maxLen;
}

//673
int findNumberOfLIS(vector<int> &nums)
{
    int n = nums.size();
    if (n == 0)
        return 0;

    vector<int> dp(n, 0);
    vector<int> count(n, 0);

    int maxLen = 0, maxCount = 0;
    for (int i = 0; i < n; i++)
    {
        dp[i] = 1;
        count[i] = 1;
        for (int j = i - 1; j >= 0; j--)
        {
            if (nums[i] > nums[j])
            {
                if (dp[j] + 1 > dp[i])
                {
                    dp[i] = dp[j] + 1;
                    count[i] = count[j];
                }
                else if (dp[j] + 1 == dp[i])
                {
                    count[i] += count[j];
                }
            }
        }

        if (dp[i] > maxLen)
        {
            maxLen = dp[i];
            maxCount = count[i];
        }
        else if (dp[i] == maxLen)
        {
            maxCount += count[i];
        }
    }

    return maxCount;
}

//354
int maxEnvelopes(vector<vector<int>> &arr)
{

    int n = arr.size();
    vector<int> dp(n, 0);

    sort(arr.begin(), arr.end(), [](auto a, auto b) {
        if (a[1] == b[1])
            return b[0] < a[0];
        return a[1] < b[1]; // this - other, default behaviour.
    });

    int maxStack = 0;
    for (int i = 0; i < n; i++)
    {
        dp[i] = 1;
        for (int j = i - 1; j >= 0; j--)
        {
            if (arr[i][0] > arr[j][0])
                dp[i] = max(dp[i], dp[j] + 1);
        }

        maxStack = max(maxStack, dp[i]);
    }
    return maxStack;
}

//https://www.geeksforgeeks.org/dynamic-programming-building-bridges/

void solve()
{
}

int main()
{
    solve();
}