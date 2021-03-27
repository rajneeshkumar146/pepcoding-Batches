#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// {{sp1,ep1},{sp2,ep2}.....}
int buildingBridges(vector<vector<int>> &arr)
{
    sort(arr.begin(), arr.end(), [](vector<int> &a, vector<int> &b) {
        return a[0] < b[0]; // this < other, default behaviour.
        // return b[0] < a[0];  // other < this, reverse default behaviour.
    });

    int n = arr.size();
    vector<int> dp(n, 0);
    int len = 0;
    for (int i = 0; i < n; i++)
    {
        dp[i] = 1;
        for (int j = i - 1; j >= 0; j--)
        {
            if (arr[j][0] < arr[i][0] && arr[j][1] < arr[i][1])
            {
                dp[i] = max(dp[i], dp[j] + 1);
            }
        }

        len = max(len, dp[i]);
    }

    return len;
}

int main()
{
    return 0;
}