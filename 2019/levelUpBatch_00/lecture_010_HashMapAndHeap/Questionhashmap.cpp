#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

//leetcode 349.=============================================
vector<int> intersection(vector<int> &one, vector<int> &two)
{
    unordered_map<int, bool> map;
    for (int ele : one)
    {
        map[ele] = true;
    }

    vector<int> ans;
    for (int ele : two)
    {
        if (map.count(ele))
        {
            map.erase(ele);
            ans.push_back(ele);
        }
    }
    return ans;
}

//leetcode 350.==============================================

vector<int> intersect(vector<int> &one, vector<int> &two)
{
    unordered_map<int, int> map;
    for (int ele : one)
    {
        map[ele]++;
    }

    vector<int> ans;

    for (int ele : two)
    {
        if (map.count(ele))
        {
            if (map[ele] > 0)
            {
                ans.push_back(ele);
                map[ele]--;
            }

            if (map[ele] == 0)
                map.erase(ele);
        }
    }

    return ans;
}

//leetcode 128.======================================
int longestConsecutive(vector<int> &nums)
{
    unordered_map<int, int> map;
    for (int ele : nums)
        map[ele]++;

    int len = 0;
    for (int ele : nums)
    {
        if (!map.count(ele))
            continue;

        map.erase(ele);
        int prevEle = ele - 1;
        int nextEle = ele + 1;

        while (map.count(prevEle))
            map.erase(prevEle--);
        while (map.count(nextEle))
            map.erase(nextEle++);

        len = max(len, nextEle - prevEle - 1);
    }

    return len;
}

//leetcode 1027.=====================================================

int longestArithSeqLength(vector<int> &arr)
{
    int n = arr.size();
    int len = 0;
    vector<unordered_map<int, int>> dp(n);

    for (int i = 0; i < n; i++)
    {
        for (int j = i-1; j >= 0; j--)
        {
            int diff = arr[i] - arr[j];
            int maxEndingHere = dp[j].count(diff) > 0 ? dp[j][diff] + 1 : 2;
            int maxPrevLen=dp[i][diff];
            dp[i][diff]=max(maxEndingHere,maxPrevLen);
            len = max(len, dp[i][diff]);
        }
    }

    return len;
}
