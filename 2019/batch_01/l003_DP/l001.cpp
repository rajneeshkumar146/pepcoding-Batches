#include <iostream>
#include <vector>

#define vi vector<int>
#define vvi vector<vi>
#define vb vector<bool>
#define vvb vector<vb>

using namespace std;

void multiplication(vector<vector<int>> &a, vector<vector<int>> &b)
{

    int a0 = a[0][0] * b[0][0] + a[0][1] * b[1][0];
    int a1 = a[0][0] * b[0][1] + a[0][1] * b[1][1];
    int a2 = a[1][0] * b[0][0] + a[1][1] * b[1][0];
    int a3 = a[1][0] * b[0][1] + a[1][1] * b[1][1];

    a[0][0] = a0;
    a[0][1] = a1;
    a[1][0] = a2;
    a[1][1] = a3;
}

void power(vector<vector<int>> &a, vector<vector<int>> &I, int pow)
{
    if (pow == 1)
        return;

    power(a, I, pow / 2);
    multiplication(a, a);

    if (pow % 2 != 0)
        multiplication(a, I);
}

int boardPath_memo(int start, int end, vector<int> &dp)
{
    if (start == end)
    {
        dp[start] = 1;
        return 1;
    }

    // if(dp[start]!=0) return dp[start];

    int count = 0;
    for (int dice = 1; dice <= 6; dice++)
    {
        if (start + dice <= end)
        {
            count += boardPath_memo(start + dice, end, dp);
        }
    }

    dp[start] = count;

    return count;
}

int mazePath(int sr, int sc, int er, int ec, vector<vector<int>> &dp)
{
    if (sr == er && sc == ec)
    {
        dp[sr][sc] = 1;
        return 1;
    }

    if (dp[sr][sc] != 0)
        return dp[sr][sc];

    if (dp.size() == dp[0].size() && dp[sc][sr] != 0)
        return dp[sc][sr];

    int count = 0;
    if (sr + 1 <= er)
    {
        count += mazePath(sr + 1, sc, er, ec, dp);
    }

    if (sc + 1 <= ec)
    {
        count += mazePath(sr, sc + 1, er, ec, dp);
    }

    if (sc + 1 <= ec && sr + 1 <= er)
    {
        count += mazePath(sr + 1, sc + 1, er, ec, dp);
    }

    dp[sr][sc] = count;
    if (dp.size() == dp[0].size())
        dp[sc][sr] = count;
    return count;
}

int mazePathDP(int sr, int sc, int er, int ec)
{

    vector<vector<int>> dp(er + 1, vector<int>(ec + 1));

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
            if (sr + 1 <= er && sc + 1 <= ec)
                count += dp[sr + 1][sc + 1];

            dp[sr][sc] = count;
        }
    }

    return dp[0][0];
}

int mazePathMulti(int sr, int sc, int er, int ec, vector<vector<int>> &dp)
{
    if (sr == er && sc == ec)
    {
        dp[sr][sc] = 1;
        return 1;
    }

    if (dp[sr][sc] != 0)
        return dp[sr][sc];
    if (dp.size() == dp[0].size() && dp[sc][sr] != 0)
        return dp[sc][sr];

    int count = 0;
    for (int jump = 1; sr + jump <= er; jump++)
    {
        count += mazePathMulti(sr + jump, sc, er, ec, dp);
    }

    for (int jump = 1; sc + jump <= ec; jump++)
    {
        count += mazePathMulti(sr, sc + jump, er, ec, dp);
    }

    for (int jump = 1; sc + jump <= ec && sr + jump <= er; jump++)
    {
        count += mazePathMulti(sr + jump, sc + jump, er, ec, dp);
    }

    dp[sr][sc] = count;
    if (dp.size() == dp[0].size())
        dp[sc][sr] = count;
    return count;
}

int mazePathMultiDP(int sr, int sc, int er, int ec)
{

    vector<vector<int>> dp(er + 1, vector<int>(ec + 1));

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
                count += dp[sr + 1][sc + 1];

            dp[sr][sc] = count;
        }
    }

    return dp[0][0];
}

vector<vector<int>> dirArray = {{0, 1}, {1, 0}, {1, 1}};

int minCost(vector<vector<int>> &costArray, int sr, int sc, vector<vector<int>> &dp)
{

    if (sr == costArray.size() - 1 && sc == costArray[0].size() - 1)
    {
        dp[sr][sc] = costArray[sr][sc];
        return costArray[sr][sc];
    }

    if (dp[sr][sc] != -1)
        return dp[sr][sc];

    int mincost = 1e6;
    for (int d = 0; d < dirArray.size(); d++)
    {
        int r = sr + dirArray[d][0];
        int c = sc + dirArray[d][1];

        if (r < costArray.size() && c < costArray[0].size())
        {
            mincost = min(mincost, minCost(costArray, r, c, dp));
        }
    }

    dp[sr][sc] = costArray[sr][sc] + mincost;

    return costArray[sr][sc] + mincost;
}

int minCostDP(vector<vector<int>> &costArray, vector<vector<int>> &dp)
{
    for (int sr = costArray.size() - 1; sr >= 0; sr--)
    {
        for (int sc = costArray[0].size() - 1; sc >= 0; sc--)
        {
            if (sr == costArray.size() - 1 && sc == costArray[0].size() - 1)
            {
                dp[sr][sc] = costArray[sr][sc];
                continue;
            }

            int mincost = 1e6;
            for (int d = 0; d < dirArray.size(); d++)
            {
                int r = sr + dirArray[d][0];
                int c = sc + dirArray[d][1];

                if (r < costArray.size() && c < costArray[0].size())
                {
                    mincost = min(mincost, dp[r][c]);
                }
            }

            dp[sr][sc] = costArray[sr][sc] + mincost;
        }
    }

    return dp[0][0];
}

void pathType()
{
    // int n = 10;
    // vector<int> dp(n + 1, 0);
    // cout << boardPath_memo(0, n, dp) << endl;

    // for (int ele : dp)
    // {
    //     cout << ele << " ";
    // }

    // int n = 3;
    // int m = 3;
    // vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));
    // cout << mazePath(0, 0, n, m, dp) << endl;
    // cout << mazePathDP(0, 0, n, m) << endl;

    // cout << mazePathMulti(0, 0, 2, 2, dp) << endl;

    vector<vector<int>> costArray = {{2, 3, 0, 4},
                                     {0, 6, 5, 2},
                                     {8, 0, 3, 7},
                                     {2, 0, 4, 2}};
    vector<vector<int>> dp(costArray.size(), vector<int>(costArray[0].size(), -1));
    // cout << minCost(costArray, 0, 0, dp) << endl;
    cout << minCostDP(costArray, dp) << endl;

    for (vector<int> &ar : dp)
    {
        for (int &ele : ar)
        {
            cout << ele << " ";
        }
        cout << endl;
    }
}

//===================================

int minJumps(vector<int> &arr)
{
    vector<int> dp(arr.size(), 1e6);
    dp[arr.size() - 1] = 0;

    for (int i = arr.size() - 2; i >= 0; i--)
    {
        int min_ = (int)1e6;
        for (int j = 1; j <= arr[i] && i + j < arr.size(); j++)
        {
            min_ = min(min_, dp[i + j]);
        }
        dp[i] = min_ + 1;
    }
    return dp[0];
}

int PairAndSingle(int n)
{
    vector<int> dp(n + 1, 0);
    dp[1] = 1;
    dp[2] = 2;
    for (int i = 3; i <= n; i++)
    {
        dp[i] += dp[i - 1];           // single
        dp[i] += dp[i - 2] * (i - 1); //pairUp ways.
    }

    return dp[n];
}

int PairAndSingle2(int n)
{

    int a = 1;
    int b = 2;
    int c = 0;
    for (int i = 3; i <= n; i++)
    {
        c = b + a * (i - 1); // f(n)=f(n-1) + f(n-2) * (n-1).
        a = b;
        b = c;
    }

    return c;
}

int divideInKGroups(int n, int k)
{
    vector<vector<int>> dp(k + 1, vector<int>(n + 1, 0));
    dp[0][0] = 1;
    for (int i = 1; i <= k; i++)
    {
        for (int j = i; j <= n; j++)
        {
            dp[i][j] += dp[i - 1][j - 1] + dp[i][j - 1] * i;
        }
    }
    return dp[k][n];
}

vector<vector<bool>> isPali(string str)
{
    vector<vector<bool>> isPlaidrome(str.length(), vector<bool>(str.length(), false));

    for (int gap = 0; gap < str.length(); gap++)
    {
        for (int i = 0, j = gap; j < str.length(); j++, i++)
        {
            if (gap == 0)
            {
                isPlaidrome[i][j] = true;
            }
            else if (str[i] == str[j])
            {
                if (gap == 1)
                    isPlaidrome[i][j] = true;
                else if (isPlaidrome[i + 1][j - 1])
                    isPlaidrome[i][j] = true;
            }
        }
    }

    return isPlaidrome;
}

// int lpSubstring(string str)
// {
//     vector<vector<int>> dp(str.length(), vector<int>(str.length(), 0));
//     vvb isPalindrome = isPali(str);

//     for (int gap = 0; gap < str.length(); gap++)
//     {
//         for (int i = 0, j = gap; j < str.length(); j++, i++)
//         {
//             if (gap == 0)
//             {
//                 dp[i][j] = 1;
//             }
//             else if (gap == 1 && str[i] == str[j])
//             {
//                 dp[i][j] = 2;
//             }
//             else if (str[i] == str[j] && isPalindrome[i + 1, j - 1])
//             {
//                 dp[i][j] = dp[i + 1][j - 1] + 2;
//             }
//             else
//             {
//                 dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
//             }
//         }
//     }

//     return dp[0][str.length() - 1];
// }

int lpSubsequence(string str)
{
    vector<vector<int>> dp(str.length(), vector<int>(str.length(), 0));

    for (int gap = 0; gap < str.length(); gap++)
    {
        for (int i = 0, j = gap; j < str.length(); j++, i++)
        {
            if (gap == 0)
                dp[i][j] = 1;
            else if (str[i] == str[j])
                dp[i][j] = dp[i + 1][j - 1] + 2;
            else
                dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
        }
    }
    return dp[0][str.length() - 1];
}

int countPSubseq(string str)
{
    int n = str.length();
    vector<vector<int>> dp(n, vector<int>(n, 0));

    for (int gap = 0; gap < n; gap++)
    {
        for (int i = 0, j = gap; j < n; j++, i++)
        {
            if (gap == 0)
            {
                dp[i][j] = 1;
            }
            else if (str[i] == str[j])
                dp[i][j] = dp[i + 1][j] + dp[i][j - 1] + 1;
            else
                dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
        }
    }

    return dp[0][n - 1];
}

int LIS(vector<int> &arr)
{
    vector<int> dp(arr.size(), 1);
    int max_ = 1;
    for (int i = 1; i < arr.size(); i++)
    {
        for (int j = 0; j < i; j++)
        {
            if (arr[i] > arr[j] && dp[j] + 1 > dp[i])
            {
                dp[i] = dp[j] + 1;
                max_ = max(max_, dp[i]);
            }
        }
    }
    return max_;
}

int LDS(vector<int> &arr)
{
    vector<int> dp(arr.size(), 1);
    int max_ = 1;
    for (int i = arr.size() - 2; i >= 0; i--)
    {
        for (int j = i + 1; j < arr.size(); j++)
        {
            if (arr[i] > arr[j] && dp[j] + 1 > dp[i])
            {
                dp[i] = dp[j] + 1;
                max_ = max(max_, dp[i]);
            }
        }
    }
    return max_;
}

int LIS_(vector<int> &arr)
{
    vector<int> list;
    list.push_back(arr[0]);
    int len = 1;
    for (int i = 1; i < arr.size(); i++)
    {
        if (arr[i] > list[len - 1])
        {
            list.push_back(arr[i]);
            len++;
        }
        else
        {
            int li = 0;
            int ri = len - 1;
            while (li < ri)
            {
                int mid = (li + ri) / 2;
                if (list[mid] < arr[i])
                    li = mid + 1;
                else
                    ri = mid;
            }
            list[ri] = arr[i];
        }
    }
    return len;
}

int coinChangePermu(vector<int> &coins, int tar)
{
    vector<int> dp(tar + 1, 0);
    dp[0] = 1;

    for (int t = 1; t <= tar; t++)
    {
        for (int c = 0; c < coins.size(); c++)
        {
            if (t - coins[c] >= 0)
                dp[t] += dp[t - coins[c]];
        }
    }
    return dp[tar];
}

int coinChangeCombi(vector<int> &coins, int tar)
{
    vector<int> dp(tar + 1, 0);
    dp[0] = 1;

    for (int c = 0; c < coins.size(); c++)
    {
        for (int t = 1; t <= tar; t++)
        {
            if (t - coins[c] >= 0)
                dp[t] += dp[t - coins[c]];
        }
    }
    return dp[tar];
}

int knapsack(vi &cost, vi &weight, int tar)
{
    vvi dp(cost.size(), vi(tar + 1, 0));
    for (int i = 0; i < cost.size(); i++)
    {
        for (int j = 1; j <= tar; j++)
        {
            if (i == 0)
                dp[i][j] = j - weight[i] >= 0 ? cost[i] : 0;
            else
            {
                int taken = 0;
                if (j - weight[i] >= 0)
                    taken = dp[i - 1][j - weight[i]] + cost[i];
                dp[i][j] = max(dp[i - 1][j], taken);
                // dp[i][j] =dp[i-1][j] || (j-coin[i]>=0 && dp[i-1][j-coin[i]]);
            }
        }
    }
    return dp[cost.size() - 1][tar];
}

int mcm_rec(int st, int end, vi &row, vi &col, vvi &dp)
{
    if (st == end)
    {
        return 0;
    }

    if (dp[st][end] != 0)
        return dp[st][end];

    int min_ = 1e8;
    for (int cut = st; cut < end; cut++)
    {
        int left = mcm_rec(st, cut, row, col, dp);       //dp[st][cut]
        int right = mcm_rec(cut + 1, end, row, col, dp); //dp[cut+1][end]

        int cost = left + row[st] * col[cut] * col[end] + right;
        min_ = min(cost, min_);
    }

    dp[st][end] = min_;

    return min_;
}

string mcm_dp(vi &row, vi &col, vvi &dp)
{

    vector<vector<string>> ans(row.size(), vector<string>(row.size(), " "));

    for (int gap = 0; gap < row.size(); gap++)
    {
        for (int st = 0, end = gap; end < row.size(); st++, end++)
        {
            if (gap == 0)
            {
                ans[st][end] = string(1, (char)('A' + st));
                continue;
            }

            int min_ = 1e8;
            for (int cut = st; cut < end; cut++)
            {
                int left = dp[st][cut];
                int right = dp[cut + 1][end];

                int cost = left + row[st] * col[cut] * col[end] + right;
                if (cost < min_)
                {
                    min_ = cost;
                    ans[st][end] = "(" + ans[st][cut] + ans[cut + 1][end] + ")";
                }
            }
            dp[st][end] = min_;
        }
    }

    for (vector<string> ar : ans)
    {
        for (string s : ar)
        {
            cout << s << " ";
        }
        cout << endl;
    }

    return (ans[0][row.size() - 1] + " -> " + to_string(dp[0][row.size() - 1]));
}

int minPalindromicCut(string &str, int st, int end, vvi &dp, vvb &isPali)
{
    if (st == end || isPali[st][end])
        return 0;

    if (dp[st][end] != 0)
        return dp[st][end];

    int min_ = 1e8;
    for (int cut = st; cut < end; cut++)
    {
        int left = minPalindromicCut(str, st, cut, dp, isPali);
        int right = minPalindromicCut(str, cut + 1, end, dp, isPali);
        int cost = left + right + 1;
        min_ = min(min_, cost);
    }
    dp[st][end] = min_;
    return min_;
}

int minPalindromicCut_DP(string &str, int st, int end, vvi &dp, vvb &isPali)
{

    for (int gap = 0; gap < str.length(); gap++)
    {
        for (int i = 0, j = gap; j < str.length(); j++, i++)
        {
            if (st == end || isPali[st][end])
            {
                dp[st][end] = 0;
                continue;
            }

            int min_ = 1e8;
            for (int cut = st; cut < end; cut++)
            {
                int left = dp[st][cut];
                int right = dp[cut + 1][end];

                int cost = left + right + 1;
                min_ = min(min_, cost);
            }
            dp[st][end] = min_;
        }
    }

    dp[0][str.length()-1];
}

int OBST(vi &keys, vi &freq)
{
    vvi dp(keys.size(), vi(keys.size(), 0));
    vi sum(keys.size(), 0);
    sum[0] = freq[0];

    for (int i = 1; i < keys.size(); i++)
        sum[i] += sum[i - 1] + freq[i];

    for (int gap = 0; gap < keys.size(); gap++)
    {
        for (int st = 0, end = gap; end < keys.size(); end++, st++)
        {
            if (gap == 0)
            {
                dp[st][end] = freq[st];
                continue;
            }

            int min_ = 1e8;
            for (int cut = st; cut <= end; cut++)
            {
                int left = cut == st ? 0 : dp[st][cut - 1];
                int right = cut == end ? 0 : dp[cut + 1][end];

                int cost = left + sum[end] - ((st - 1) < 0 ? 0 : sum[st - 1]) + right;
                min_ = min(cost, min_);
            }
            dp[st][end] = min_;
        }
    }

    return dp[0][keys.size() - 1];
}

void set3()
{
    // vi row{40, 20, 30, 10};
    // vi col{20, 30, 10, 30};
    // vvi dp(row.size(), vi(row.size(), 0));

    // cout << mcm_rec(0, row.size() - 1, row, col, dp) << endl;
    // cout << mcm_dp(row, col, dp) << endl;
    string str = "aacccccdef";
    vvb ispali = isPali(str);
    vvi dp(str.size(), vi(str.size(), 0));
    cout << minPalindromicCut(str, 0, str.length() - 1, dp, ispali) << endl;
}

void set2()
{

    vector<int> arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
    cout << LIS(arr) << endl;
    cout << LIS_(arr);
}

void minType()
{
    // vector<int> jumps = {0, 3, 0, 4, 0, 0, 2, 1, 1, 0};
    // cout<<minJumps(jumps)<<endl;
    // cout<<PairAndSingle(5)<<endl;
    // cout << PairAndSingle2(5) << endl;
    // cout << divideInKGroups(6, 4) << endl;

    cout << countPSubseq("babad") << endl;
}

void solve()
{
    // vector<vector<int>> a = {{1, 1}, {1, 0}};
    // vector<vector<int>> I = {{1, 1}, {1, 0}};
    // int n = 13;
    // power(a, I, n + 1);
    // cout << a[1][1] << endl;

    // pathType();
    // minType();
    // set2();
    set3();
}

int main()
{
    solve();
    return 0;
}