#include <iostream>
#include <vector>
#include <climits>
#include <list>

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
    cout << tileFloor_01(n, m, dp) << endl;  // increase size of dp by 1(n+1, m+1).
    cout << tileFloor_02(n, m) << endl;

    display(dp);
    // display2D(dp);
}

void solve()
{
    // set1();
    set2();
}

int main()
{
    solve();
    return 0;
}