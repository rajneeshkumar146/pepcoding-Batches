#include <iostream>
#include <vector>

using namespace std;

// 516
int longestPalindromeSubseq(string &s, int i, int j, vector<vector<int>> &dp)
{
    if (i >= j)
    {
        return dp[i][j] = (i == j) ? 1 : 0;
    }

    if (dp[i][j] != -1)
        return dp[i][j];

    if (s[i] == s[j])
        return dp[i][j] = longestPalindromeSubseq(s, i + 1, j - 1, dp) + 2;

    return dp[i][j] = max(longestPalindromeSubseq(s, i + 1, j, dp), longestPalindromeSubseq(s, i, j - 1, dp));
}

int longestPalindromeSubseq_DP(string &s, int I, int J, vector<vector<int>> &dp)
{
    int n = s.length();
    for (int gap = 0; gap < n; gap++)
    {
        for (int i = 0, j = gap; j < n; i++, j++)
        {

            if (i == j)
            {
                dp[i][j] = 1;
                continue;
            }

            if (s[i] == s[j])
                dp[i][j] = dp[i + 1][j - 1] + 2;
            else
                dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
        }
    }

    return dp[I][J];
}

string longestPalindromeSubseq_string(string &s)
{
    int n = s.length();
    vector<vector<string>> dp(n, vector<string>(n, ""));

    for (int gap = 0; gap < n; gap++)
    {
        for (int i = 0, j = gap; j < n; i++, j++)
        {
            if (i == j)
            {
                dp[i][j] = string(1, s[i]);
                continue;
            }

            if (s[i] == s[j])
                dp[i][j] = string(1, s[i]) + dp[i + 1][j - 1] + string(1, s[i]);
            else
            {
                dp[i][j] = (dp[i + 1][j].length() > dp[i][j - 1].length()) ? dp[i + 1][j] : dp[i][j - 1];
            }
        }
    }

    return dp[0][n - 1];
}

int longestPalindromeSubseq(string &s)
{
    int n = s.length();
    vector<vector<int>> dp(n, vector<int>(n, -1));
    return longestPalindromeSubseq_DP(s, 0, n - 1, dp);
}

//1458
int maxDotProduct_memo(vector<int> &nums1, vector<int> &nums2, int n, int m, vector<vector<int>> &dp)
{
    if (n == 0 || m == 0)
    {
        return dp[n][m] = -(int)1e7;
    }

    if (dp[n][m] != -(int)1e8)
        return dp[n][m];

    int val = nums1[n - 1] * nums2[m - 1];
    int acceptBothNumber = maxDotProduct_memo(nums1, nums2, n - 1, m - 1, dp) + val;
    int a = maxDotProduct_memo(nums1, nums2, n - 1, m, dp);
    int b = maxDotProduct_memo(nums1, nums2, n, m - 1, dp);

    return dp[n][m] = max(max(acceptBothNumber, val), max(a, b));
}

int maxDotProduct(vector<int> &nums1, vector<int> &nums2)
{
    int n = nums1.size();
    int m = nums2.size();

    vector<vector<int>> dp(n + 1, vector<int>(m + 1, -1e8));
    return maxDotProduct_memo(nums1, nums2, n, m, dp);
}


