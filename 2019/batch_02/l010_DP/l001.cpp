#include <iostream>
#include <vector>
#include <climits>
#include <list>

#define vi vector<int>
#define vii vector<vi>

#define vb vector<bool>
#define vbb vector<vb>

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

//set01.============================================================

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

int boardPath_01(int st, int end, vector<int> &dp)
{
    if (st == end)
    {
        dp[st] = 1;
        return 1;
    }

    if (dp[st] != 0)
        return dp[st];

    int count = 0;
    for (int dice = 1; dice <= 6 && st + dice <= end; dice++)
    {
        count += boardPath_01(st + dice, end, dp);
    }

    dp[st] = count;
    return count;
}

int boardPath_02(int st, int end, vector<int> &dp)
{
    for (int i = end; i >= st; i--)
    {

        if (i == end)
        {
            dp[i] = 1;
            continue;
        }

        int count = 0;
        for (int dice = 1; dice <= 6 && i + dice <= end; dice++)
        {
            count += dp[i + dice];
        }

        dp[i] = count;
    }

    return dp[0];
}

int boardPath_01_giveOutcomes(int st, int end, vector<int> &outcomes, vector<int> &dp)
{
    if (st == end)
    {
        dp[st] = 1;
        return 1;
    }

    if (dp[st] != 0)
        return dp[st];

    int count = 0;
    for (int i = 0; i < outcomes.size(); i++)
    {
        if (st + outcomes[i] <= end)
            count += boardPath_01_giveOutcomes(st + outcomes[i], end, outcomes, dp);
    }

    dp[st] = count;
    return count;
}

int boardPath_02_giveOutcomes(int stP, int end, vector<int> &outcomes, vector<int> &dp)
{

    for (int st = end; st >= stP; st--)
    {
        if (st == end)
        {
            dp[st] = 1;
            continue;
        }

        int count = 0;
        for (int i = 0; i < outcomes.size(); i++)
        {
            if (st + outcomes[i] <= end)
                count += dp[st + outcomes[i]];
        }

        dp[st] = count;
    }
    return dp[0];
}

//set02.=========================================================

//leetode 70
int climbStairs(int n)
{
    if (n <= 1)
        return 1;

    int a = 1, b = 1, ans = 0;
    for (int i = 2; i <= n; i++)
    {
        ans = a + b;
        a = b;
        b = ans;
    }
    return ans;
}

int minCostClimbingStairs_01(int n, vector<int> &cost, vector<int> &dp)
{
    if (n <= 1)
        return cost[n];
    if (dp[n] != 0)
        return dp[n];
    int c1 = minCostClimbingStairs_01(n - 1, cost, dp);
    int c2 = minCostClimbingStairs_01(n - 2, cost, dp);

    return dp[n] = min(c1, c2) + cost[n];
}

int minCostClimbingStairs_02(int n, vector<int> &cost, vector<int> &dp)
{

    for (int i = 0; i <= n; i++)
    {
        if (i <= 1)
        {
            dp[i] = cost[i];
            continue;
        }

        int c1 = dp[i - 1];
        int c2 = dp[i - 2];

        dp[i] = min(c1, c2) + cost[i];
    }

    return dp[n];
}

int minCostClimbingStairs_03(vector<int> &cost)
{

    if (cost.size() == 1)
        return cost[0];
    if (cost.size() == 2)
        return min(cost[0], cost[1]);

    int a = cost[0];
    int b = cost[1];
    for (int i = 2; i < cost.size(); i++)
    {
        int ans = cost[i] + min(a, b);
        a = b;
        b = ans;
    }

    return min(a, b);
}

// leetcode 746
int minCostClimbingStairs(vector<int> &cost)
{
    cost.push_back(0);
    int n = cost.size();

    vector<int> dp(n, 0);
    return minCostClimbingStairs_01(n - 1, cost, dp);
}

int minPathSum(vector<vector<int>> &arr)
{
    int n = arr.size();
    int m = arr[0].size();
    for (int i = n - 1; i >= 0; i--)
    {
        for (int j = m - 1; j >= 0; j--)
        {
            if (i == n - 1 && j == m - 1)
            {
                continue;
            }

            int c1 = j + 1 < m ? arr[i][j + 1] : 1e6;
            int c2 = i + 1 < n ? arr[i + 1][j] : 1e6;

            int minCost = min(c1, c2);
            arr[i][j] += minCost;
        }
    }

    return arr[0][0];
}

//set2.====================================================

int pairAndSingle_01(int n, vi &dp)
{
    if (n <= 1)
    {
        dp[n] = n;
        return 1;
    }

    if (dp[n] != 0)
        return dp[n];

    int singleWays = pairAndSingle_01(n - 1, dp);
    int pairUpWays = pairAndSingle_01(n - 2, dp) * (n - 1);

    dp[n] = singleWays + pairUpWays;
    return dp[n];
}

int pairAndSingle_02(int n, vi &dp)
{

    for (int i = 0; i <= n; i++)
    {
        if (i <= 1)
        {
            dp[i] = 1;
            continue;
        }

        int singleWays = dp[i - 1];
        int pairUpWays = dp[i - 2] * (i - 1);

        dp[i] = singleWays + pairUpWays;
    }

    return dp[n];
}

int pairAndSingle_03(int n)
{
    if (n <= 1)
        return 1;

    int a = 1; // n=0
    int b = 1; //n=1;
    int ans = 0;
    for (int i = 2; i <= n; i++)
    {
        ans = b + a * (i - 1);
        a = b;
        b = ans;
    }

    return ans;
}

int Minjump_01(int idx, vi &arr, vector<int> &dp)
{
    if (idx == arr.size() - 1)
        return 0;

    // if (arr[idx] == 0)
    //     return (int)1e7;

    if (dp[idx] != 0)
        return dp[idx];

    int minAns = (int)1e7;
    for (int jump = 1; jump <= arr[idx] && idx + jump < arr.size(); jump++)
    {
        int minJump = Minjump_01(idx + jump, arr, dp);
        if (minJump != (int)1e7)
            minAns = min(minJump + 1, minAns);
    }

    dp[idx] = minAns;
    return minAns;
}

int Minjump_02(int idx, vi &arr, vector<int> &dp)
{
    int n = arr.size();
    dp[n - 1] = 0;
    for (int i = n - 2; i >= 0; i--)
    {
        int minAns = INT_MAX;
        for (int jump = 1; jump <= arr[i] && i + jump < n; jump++)
        {
            int minJump = dp[i + jump];
            if (minJump != INT_MAX)
                minAns = min(minJump + 1, minAns);
        }

        dp[i] = minAns;
    }

    return dp[0];
}

int jump(vector<int> &nums)
{
    vector<int> dp(nums.size(), 0);
    return Minjump_01(0, nums, dp);
}
int divideInKGroups(int n, int k, vii &dp)
{
    if (n < k)
        return 0;
    if (n == k || k == 1)
    {
        dp[n][k] = 1;
        return 1;
    }

    if (dp[n][k] != 0)
        return dp[n][k];

    int startingOfNewSet = divideInKGroups(n - 1, k - 1, dp);
    int totalGroups = divideInKGroups(n - 1, k, dp); // n-1, number will divide in k sets and generate some x group.

    int totalSets = totalGroups * k; //total set avilable for me.

    dp[n][k] = startingOfNewSet + totalSets;
    return dp[n][k];
}

int divideInKGroups_02(int n, int k, vii &dp)
{
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= k; j++)
        {
            if (i < j)
            {
                dp[i][j] = 0;
                continue;
            }
            if (i == j || j == 1)
            {
                dp[i][j] = 1;
                continue;
            }

            int startingOfNewSet = dp[i - 1][j - 1];
            int totalGroups = dp[i - 1][j]; // n-1, number will divide in k sets and generate some x group.

            int totalSets = totalGroups * j; //total set avilable for me.

            dp[i][j] = startingOfNewSet + totalSets;
        }
    }
    return dp[n][k];
}

vii dirArr = {{0, 1}, {-1, 1}, {1, 1}};
int goldMine_01(int x, int y, vii &arr, vii &dp)
{
    if (y == arr[0].size() - 1)
    {
        dp[x][y] = arr[x][y];
        return arr[x][y];
    }

    if (dp[x][y] != 0)
        return dp[x][y];

    int maxAns = -1e6;
    for (int d = 0; d < 3; d++)
    {
        int r = x + dirArr[d][0];
        int c = y + dirArr[d][1];

        if (r >= 0 && c >= 0 && r < arr.size() && c < arr[0].size())
            maxAns = max(maxAns, goldMine_01(r, c, arr, dp));
    }

    dp[x][y] = maxAns + arr[x][y];
    return dp[x][y];
}

int goldMine_02(vii &arr, vii &dp)
{

    for (int y = arr[0].size() - 1; y >= 0; y--)
    {
        for (int x = arr.size() - 1; x >= 0; x--)
        {
            if (y == arr[0].size() - 1)
            {
                dp[x][y] = arr[x][y];
                continue;
            }

            int maxAns = -1e6;
            for (int d = 0; d < 3; d++)
            {
                int r = x + dirArr[d][0];
                int c = y + dirArr[d][1];

                if (r >= 0 && c >= 0 && r < arr.size() && c < arr[0].size())
                    maxAns = max(maxAns, dp[r][c]);
            }

            dp[x][y] = maxAns + arr[x][y];
        }
    }

    int maxAns = -1e6;
    for (int row = 0; row < arr.size(); row++)
    {
        maxAns = max(maxAns, dp[row][0]);
    }

    return maxAns;
}

int goldMine()
{
    vii arr = {{1, 3, 3},
               {2, 1, 4},
               {0, 6, 4}};
    vii dp(arr.size(), vi(arr[0].size(), 0));

    // int maxAns = -1e6;
    // for (int row = 0; row < arr.size(); row++)
    // {
    //     maxAns = max(maxAns, goldMine_01(row, 0, arr, dp));
    // }
    // cout << maxAns << endl;

    cout << goldMine_02(arr, dp) << endl;
    display2D(dp);
}

int maxSquareAns = 0;
int maxSquareOf1s(int r, int c, vii &arr, vii &dp)
{
    if (r == arr.size() || c == arr[0].size())
    {
        return 0;
    }

    if (dp[r][c] != -1)
        return dp[r][c];

    int down = maxSquareOf1s(r + 1, c, arr, dp);
    int diag = maxSquareOf1s(r + 1, c + 1, arr, dp);
    int right = maxSquareOf1s(r, c + 1, arr, dp);

    int minPossibleSqaure = 0;
    if (arr[r][c] == 1)
        minPossibleSqaure = min(down, min(diag, right)) + 1;

    dp[r][c] = minPossibleSqaure;
    maxSquareAns = max(dp[r][c], maxSquareAns);

    return dp[r][c];
}

int maxSquareOf1s_DP(vii &arr, vii &dp)
{
    int n = arr.size() - 1;
    int m = arr[0].size() - 1;
    int maxSquareAns_ = 0;

    for (int r = n; r >= 0; r--)
    {
        for (int c = m; c >= 0; c--)
        {
            if (arr[r][c] == 1)
                dp[r][c] = min(dp[r + 1][c], min(dp[r + 1][c + 1], dp[r][c + 1])) + 1;
            else
                dp[r][c] = 0;

            maxSquareAns_ = max(dp[r][c], maxSquareAns_);
        }
    }

    return maxSquareAns_;
}

int tileFloor(int n, int m, vi &dp)
{
    if (n <= m)
    {
        dp[n] = ((n == m) ? 2 : 1);
        return dp[n];
    }

    if (dp[n] != 0)
        return dp[n];

    int horizontal = tileFloor(n - 1, m, dp);
    int vertical = tileFloor(n - m, m, dp);

    dp[n] = horizontal + vertical;
    return dp[n];
}

int tileFloor_01(int n, int m, vi &dp)
{
    for (int i = 0; i <= n; i++)
    {
        if (i <= m)
        {
            dp[i] = ((i == m) ? 2 : 1);
            continue;
        }

        int horizontal = dp[i - 1];
        int vertical = dp[i - m];

        dp[i] = horizontal + vertical;
    }
    return dp[n];
}

int tileFloor_02(int n, int m)
{
    if (n <= m)
        return ((n == m) ? 2 : 1);

    list<int> ll;
    for (int i = 1; i <= m; i++)
        ll.push_back(1);

    for (int i = m; i <= n; i++)
    {
        int horizontal = ll.back(); //dp[i - 1];
        int vertical = ll.front();  //dp[i - m];

        ll.push_back(horizontal + vertical);
        ll.pop_front();
    }

    return ll.back();
}

//TargetType.====================================================

int coinChangePermutation(vi &arr, int tar, vi &dp)
{
    if (tar == 0)
    {
        return dp[tar] = 1;
    }

    if (dp[tar] != 0)
        return dp[tar];

    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
            count += coinChangePermutation(arr, tar - arr[i], dp);
    }

    return dp[tar] = count;
}

int coinChangePermutation_DP(vi &arr, int tar, vi &dp)
{
    dp[0] = 1;
    for (int t = 1; t <= tar; t++)
    {
        int count = 0;
        for (int i = 0; i < arr.size(); i++)
        {
            if (t - arr[i] >= 0)
                dp[t] += dp[t - arr[i]];
        }
    }

    return dp[tar];
}

int coinChangeCombination_DP(vi &arr, int tar, vi &dp)
{
    dp[0] = 1;
    for (int i = 0; i < arr.size(); i++)
    {
        for (int t = 1; t <= tar; t++)
        {
            if (t - arr[i] >= 0)
                dp[t] += dp[t - arr[i]];
        }
    }

    return dp[tar];
}

//leetcode 377
int combinationSum4(vector<int> &nums, int target) //same as permuation
{
    vector<unsigned int> dp(target + 1, 0);
    dp[0] = 1;
    for (int i = 1; i <= target; i++)
    {
        for (int coin : nums)
        {
            if (i - coin >= 0)
                dp[i] += dp[i - coin];
        }
    }
    return dp[target];
}

//leetcode 322

int minCoinsHeight(vector<int> &arr, int tar, vector<int> &dp)
{
    if (tar == 0)
        return 0;

    if (dp[tar] != 0)
        return dp[tar];

    int height = 1e8;
    for (int i = 0; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
        {
            int recAns = minCoinsHeight(arr, tar - arr[i], dp);
            if (recAns < height)
                height = recAns + 1;
        }
    }

    dp[tar] = height;
    return height;
}

int minCoinsHeight_dp(vector<int> &arr, int tar, vector<int> &dp)
{

    // for (int i = 0; i < dp.size(); i++)
    //     dp[i] = 1e8;

    dp[0] = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        for (int t = 1; t <= tar; t++)
        {

            if (t - arr[i] >= 0)
            {
                dp[t] = min(dp[t], dp[t - arr[i]] + 1);
            }
        }
    }
    return dp[tar];
}

int coinChange(vector<int> &arr, int tar)
{
    if (arr.size() == 0 || tar <= 0)
        return 0;
    vector<int> dp(tar + 1, 1e8); // Arrays.fill(dp,(int)1e8);

    // int ans = minCoinsHeight(arr, tar, dp);
    int ans = minCoinsHeight_dp(arr, tar, dp);

    return ans != 1e8 ? ans : -1;
}

int targetSum_01(vi &arr, int idx, int tar, vii &dp)
{
    if (tar == 0 || idx < 0)
    {
        return tar == 0 ? 1 : 0;
    }

    if (dp[idx][tar] != 0)
        return dp[idx][tar];

    int count = 0;
    if (tar - arr[idx] >= 0)
        count += targetSum_01(arr, idx - 1, tar - arr[idx], dp);
    count += targetSum_01(arr, idx - 1, tar, dp);

    return dp[idx][tar] = count;
}

int targetSum_02(vi &arr, int tar, vii &dp)
{

    dp[0][0] = 1; //if zero append at 0 index of coins array.
    for (int idx = 1; idx < arr.size(); idx++)
    {
        for (int t = 0; t <= tar; t++)
        {
            if (t == 0)
            {
                dp[idx][t] = 1;
                continue;
            }

            if (t - arr[idx] >= 0)
                dp[idx][t] += dp[idx - 1][t - arr[idx]];
            dp[idx][t] += dp[idx - 1][t];
        }
    }
    return dp[arr.size() - 1][tar];
}

int knapSack_01(vi &weight, vi &cost, int idx, int cap, vii &dp)
{
    if (cap == 0 || idx == -1)
    {
        return 0;
    }

    if (dp[idx][cap] != 0)
        return dp[idx][cap];

    int picked = 0, unpicked = 0;
    if (cap - weight[idx] >= 0)
        picked = knapSack_01(weight, cost, idx - 1, cap - weight[idx], dp) + cost[idx];
    unpicked = knapSack_01(weight, cost, idx - 1, cap, dp);

    return dp[idx][cap] = max(picked, unpicked);
}

int knapSack_02(vi &weight, vi &cost, int capacity, vii &dp)
{

    for (int idx = 1; idx < dp.size(); idx++)
    {
        int weightIdx = idx - 1;
        for (int cap = 0; cap <= capacity; cap++)
        {
            int picked = 0, unpicked = 0;
            if (cap - weight[weightIdx] >= 0)
                picked = dp[idx - 1][cap - weight[weightIdx]] + cost[weightIdx];
            unpicked = dp[idx - 1][cap];

            dp[idx][cap] = max(picked, unpicked);
        }
    }

    return dp[dp.size() - 1][dp[0].size() - 1];
}

int unboundedKnapSack(vi &weight, vi &cost, int capacity)
{
    vi dp(capacity + 1, 0);
    dp[0] = 0;
    for (int idx = 0; idx < weight.size(); idx++)
    {
        for (int cap = 1; cap <= capacity; cap++)
        {
            if (cap - weight[idx] >= 0)
            {
                int picked = dp[cap - weight[idx]] + cost[idx];
                int unpicked = dp[cap];
                dp[cap] = max(picked, unpicked);
            }
        }
    }
    display(dp);
    return dp[capacity];
}

// stringSet.====================================================

vbb isPlaindromeSubstring(string str)
{

    vbb dp(str.length(), vb(str.length(), false));

    for (int gap = 0; gap < str.length(); gap++)
    {
        for (int i = 0, j = gap; j < str.length(); j++, i++)
        {
            if (gap == 0)
                dp[i][j] = true;
            else if (str[i] == str[j])
            {
                if (gap == 1)
                    dp[i][j] = true;
                else if (dp[i + 1][j - 1])
                    dp[i][j] = true;
            }
        }
    }

    return dp;
}

int LongestPlaindromeSubstring(string str, vii &dp)
{

    vbb isPlai = isPlaindromeSubstring(str);

    for (int gap = 0; gap < str.length(); gap++)
    {
        for (int i = 0, j = gap; j < str.length(); j++, i++)
        {
            if (gap == 0)
                dp[i][j] = 1;
            else if (str[i] == str[j] && gap == 1)
                dp[i][j] = 2;
            else if (str[i] == str[j] && isPlai[i + 1][j - 1])
                dp[i][j] = dp[i + 1][j - 1] + 2;
            else
                dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
        }
    }

    return dp[0][str.length() - 1];
}

int LongestPlaindromeSubsequence(string str, vii &dp)
{
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

int countPlaindromeSubsequence(string str, vii &dp)
{
    for (int gap = 0; gap < str.length(); gap++)
    {
        for (int i = 0, j = gap; j < str.length(); j++, i++)
        {
            if (gap == 0)
                dp[i][j] = 1;
            else if (str[i] == str[j])
            {
                dp[i][j] += dp[i + 1][j - 1] + 1;                           // when both first and last character is inclded.
                dp[i][j] += dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1]; // when one character is included from first and last at a time.

                // dp[i][j] = dp[i + 1][j] + dp[i][j - 1] + 1;
            }
            else
                dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
        }
    }

    return dp[0][str.length() - 1];
}

//LIS Set.=====================================================================

int LISmax_ = 0;
int LIS_Rec(vi &arr, int ei, vi &dp)
{
    if (ei == 0)
        return 1;

    int max_ = 1;
    for (int i = ei - 1; i >= 0; i--)
    {
        int recAns = LIS_Rec(arr, i, dp);
        if (arr[i] < arr[ei])
        {
            LISmax_ = max(LISmax_, recAns + 1);
            max_ = max(max_, recAns + 1);
        }
    }

    // dp[ei]=max_;
    return max_;
}

vector<int> LIS_DP(vi &arr)
{
    vi dp(arr.size(), 1);
    int max_ = 1;
    for (int i = 1; i < arr.size(); i++)
    {
        for (int j = 0; j < i; j++) // har ek j cell uss tak ka maximum increasing subsequence store krke apne pass rakhta hai.
        {
            if (arr[i] > arr[j]) // agar i cell j se bada hoga to length ek se increase hojayegi.
            {
                dp[i] = max(dp[i], dp[j] + 1);
            }
        }
        max_ = max(max_, dp[i]);
    }

    cout << max_ << endl;
    return dp;
}

vector<int> LDS_DP(vi &arr)
{
    vi dp(arr.size(), 1);
    int max_ = 1;
    for (int i = arr.size() - 2; i >= 0; i--)
    {
        for (int j = i + 1; j < arr.size(); j++)
        {
            if (arr[i] > arr[j])
            {
                dp[i] = max(dp[i], dp[j] + 1);
            }
        }
        max_ = max(max_, dp[i]);
    }

    // cout << max_ << endl;
    return dp;
}

vector<int> LBS_DP(vi &arr)
{
    vi LIS = LIS_DP(arr);
    vi LDS = LDS_DP(arr);

    vi LBS(arr.size(), 1);

    int max_ = 1;
    for (int i = 0; i < arr.size(); i++)
    {
        LBS[i] = LIS[i] + LDS[i] - 1;
        max_ = max(max_, LBS[i]);
    }
}

vector<int> maximumSumSubsequnece(vi &arr)
{
    vi dp(arr.size(), 1);
    int max_ = 1;
    for (int i = 1; i < arr.size(); i++)
    {
        dp[i] = arr[i];
        for (int j = 0; j < i; j++) // har ek j cell uss tak ka maximum increasing subsequence store krke apne pass rakhta hai.
        {
            if (arr[i] > arr[j]) // agar i cell j se bada hoga to length ek se increase hojayegi.
            {
                dp[i] = max(dp[i], dp[j] + arr[i]);
            }
        }
        max_ = max(max_, dp[i]);
    }

    // cout << max_ << endl;
    return dp;
}

// Minimum number of deletion to make sorted sequence.
int minimumDeletion(vi &arr)
{
    vi dp(arr.size(), 1);
    int max_ = 1;
    for (int i = 1; i < arr.size(); i++)
    {
        for (int j = 0; j < i; j++)
        {
            if (arr[i] >= arr[j]) // for equal numbers.
            {
                dp[i] = max(dp[i], dp[j] + 1);
            }
        }
        max_ = max(max_, dp[i]);
    }

    return arr.size() - max_;
}

// non - verlapping bridges.
// russian doll.
// activity selection.

//cutType.=========================================================

int MCM_memo(vi &arr, int si, int ei, vii &dp)
{
    if (si + 1 == ei) //cost of multiplication of a single matrix is zero.
        return 0;

    if (dp[si][ei] != 0)
        return dp[si][ei];

    int minAns = 1e7;
    for (int cut = si + 1; cut < ei; cut++)
    {
        int left = MCM_memo(arr, si, cut, dp);
        int right = MCM_memo(arr, cut, ei, dp);

        int myCost = left + arr[si] * arr[cut] * arr[ei] + right;
        minAns = min(minAns, myCost);
    }

    dp[si][ei] = minAns;
    return minAns;
}

pair<int, string> MCM_memoString(vi &arr, int si, int ei, vector<vector<pair<int, string>>> &dp)
{
    if (si + 1 == ei) //cost of multiplication of a single matrix is zero.
    {
        string str = string(1, (char)(si + 'A'));
        dp[si][ei] = {0, str};
        return dp[si][ei];
    }

    if (dp[si][ei].first != 0)
        return dp[si][ei];

    pair<int, string> minAns = {1e7, ""};
    for (int cut = si + 1; cut < ei; cut++)
    {
        pair<int, string> left = MCM_memoString(arr, si, cut, dp);
        pair<int, string> right = MCM_memoString(arr, cut, ei, dp);

        int myCost = left.first + arr[si] * arr[cut] * arr[ei] + right.first;
        if (myCost < minAns.first)
        {
            minAns.first = myCost;
            minAns.second = "(" + left.second + right.second + ")";
        }
    }

    dp[si][ei] = minAns;
    return minAns;
}

int mcm_DP(vi &arr, vii &dp)
{

    int n = arr.size();
    vector<vector<string>> sdp(n, vector<string>(n, ""));

    for (int gap = 1; gap < arr.size(); gap++)
    {
        for (int si = 0, ei = gap; ei < arr.size(); si++, ei++)
        {
            if (si + 1 == ei) //cost of multiplication of a single matrix is zero.
            {
                sdp[si][ei] = string(1, char(si + 'A'));
                continue;
            }

            int minAns = 1e7;
            for (int cut = si + 1; cut < ei; cut++)
            {
                int left = dp[si][cut];
                int right = dp[cut][ei];

                int myCost = left + arr[si] * arr[cut] * arr[ei] + right;
                if (myCost < minAns)
                {
                    dp[si][ei] = myCost;
                    minAns = myCost;
                    sdp[si][ei] = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                }
            }
        }
    }

    cout << sdp[0][arr.size() - 1] << " -> " << dp[0][arr.size() - 1] << endl;
    return dp[0][arr.size() - 1];
}

int minimumPalindromicCut_rec(string str, int si, int ei, vii &dp, vbb &isPali)
{
    if (isPali[si][ei])
    {
        dp[si][ei] = 0;
        return 0;
    }
    if (dp[si][ei] != 0)
        return dp[si][ei];

    int min_ = 1e7;
    for (int cut = si; cut < ei; cut++)
    {
        int left = minimumPalindromicCut_rec(str, si, cut, dp, isPali);
        int right = minimumPalindromicCut_rec(str, cut + 1, ei, dp  , isPali);

        min_ = min(min_, left + 1 + right);
    }

    dp[si][ei] = min_;
    return min_;
}

int minimumPalindromicCut_DP(string str, vii &dp, vbb &isPali)
{

    for (int gap = 1; gap < str.length(); gap++)
    {
        for (int si = 0, ei = gap; ei < str.length(); si++, ei++)
        {
            if (isPali[si][ei])
            {
                dp[si][ei] = 0;
                continue;
            }

            int min_ = 1e7;
            for (int cut = si; cut < ei; cut++)
            {
                int left = dp[si][cut];
                int right = dp[cut + 1][ei];
                min_ = min(min_, left + 1 + right);
            }

            dp[si][ei] = min_;
        }
    }

    return dp[0][str.length() - 1];
}

void set1()
{
    int n = 10;
    // int m=3;
    vector<int> dp(n + 1, 0);
    // vii dp(n, vi(m, 0));

    // cout << fibo_01(n, dp) << endl;

    // cout << mazePathHV_01(0, 0, n - 1, n - 1, dp) << endl;
    // cout << mazePathHV_02(n - 1, n - 1, dp) << endl;

    // cout << mazePathMulti_01(0, 0, n - 1, n - 1, dp) << endl;
    // cout << mazePathMulti_02( n - 1, n - 1, dp) << endl;

    // cout << boardPath_01(0, n, dp) << endl;
    // cout << boardPath_02(0, n, dp) << endl;

    // vi outcomes = {2, 3, 7, 5};
    // cout << boardPath_01_giveOutcomes(0, n, outcomes, dp) << endl;
    // cout << boardPath_02_giveOutcomes(0, n, outcomes, dp) << endl;

    // display(dp);
    // display2D(dp);
}

void set2()
{
    int n = 170;
    int m = 6;
    vector<int> dp(n + 1, 0);
    // vii dp(n + 1, vi(m + 1, 0));

    // cout << pairAndSingle_01(n, dp) << endl;
    // cout << pairAndSingle_02(n, dp) << endl;
    // cout << pairAndSingle_03(n) << endl;

    // cout << divideInKGroups(n, m, dp) << endl;
    // cout << divideInKGroups_02(n, m, dp) << endl;

    // goldMine();

    // cout << tileFloor(n, m, dp) << endl; //(7,4) -> 5
    cout << tileFloor_01(n, m, dp) << endl; // increase size of dp by 1(n+1, m+1).
    cout << tileFloor_02(n, m) << endl;

    display(dp);
    // display2D(dp);
}

void targetType()
{
    // vi arr{0, 2, 3, 5, 7};
    // int tar = 10;

    // vector<int> dp(tar + 1, 0);
    // vii dp(arr.size(), vi(tar + 1, 0));
    // cout << coinChangePermutation(arr, tar, dp) << endl;
    // cout << coinChangePermutation_DP(arr, tar, dp) << endl;

    // cout << coinChangeCombination_DP(arr, tar, dp) << endl;

    // cout << targetSum_01(arr, arr.size() - 1, tar, dp) << endl;
    // cout << targetSum_02(arr, tar, dp) << endl;

    vi weight = {2, 5, 1, 3, 4};
    vi cost = {15, 14, 10, 45, 30};
    int cap = 7;
    vii dp(weight.size() + 1, vi(cap + 1, 0));
    // cout << knapSack_01(weight, cost, cost.size() - 1, cap, dp) << endl;
    // cout << knapSack_02(weight, cost, cap, dp) << endl;

    cout << unboundedKnapSack(weight, cost, cap) << endl;

    // display(dp);
    // display2D(dp);
}

void stringSet()
{
    string str = "efabcbadd";
    int n = str.length();
    vii dp(n, vi(n, 0));

    cout << LongestPlaindromeSubstring(str, dp) << endl;

    display2D(dp);
}

void LISset()
{
    vi arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
    display(arr);

    vector<int> dp;
    LIS_Rec(arr, arr.size() - 1, dp);
    cout << LISmax_ << endl;

    vi LIS = LIS_DP(arr);
    // display(LIS);

    // vi LDS = LDS_DP(arr);
    // display(LDS);
}

void cutType()
{
    // vi arr = {10, 20, 30, 40, 30};
    // int n=arr.size();
    string str = "abcbddf";
    int n = str.length();

    vii dp(n, vi(n, 0));

    // vector<vector<pair<int, string>>> ndp(arr.size(), vector<pair<int, string>>(arr.size(), {0, ""}));

    // cout << MCM_memo(arr, 0, arr.size() - 1, dp) << endl;
    // cout << mcm_DP(arr, dp) << endl;
    // pair<int, string> ans = MCM_memoString(arr, 0, arr.size() - 1, ndp);
    // cout << ans.second << " -> " << ans.first << endl;

    vbb isPali = isPlaindromeSubstring(str);
    cout << minimumPalindromicCut_rec(str, 0, n - 1, dp, isPali) << endl;
    // cout << minimumPalindromicCut_DP(str, dp, isPali) << endl;

    display2D(dp);
}

void solve()
{
    // set1();
    // set2();
    // targetType();
    // stringSet();
    // LISset();
    cutType();
}

int main()
{
    solve();
    return 0;
}