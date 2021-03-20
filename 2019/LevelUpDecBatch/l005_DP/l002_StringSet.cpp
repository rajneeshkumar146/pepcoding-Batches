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
