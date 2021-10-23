#include <iostream>
#include <queue>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

int longestSubsequence(vector<int> &arr, int d)
{
    unordered_map<int, int> map;
    int maxLen = 0;
    for (int ele : arr)
    {
        map[ele] = map.find(ele - d) != map.end() ? map[ele - d] + 1 : 1;
        maxLen = max(maxLen, map[ele]);
    }

    return maxLen;
}

// 1027
int longestArithSeqLength(vector<int> &nums)
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int n = nums.size(), maxLen = 0;
    vector<vector<int>> map(n, vector<int>(1000 + 5, -1));

    for (int i = 0; i < n; i++)
    {
        for (int j = i - 1; j >= 0; j--)
        {
            int diff = (nums[i] - nums[j]) + 500;

            int currLen = map[i][diff] != -1 ? map[i][diff] : 0;
            int newLen = (map[j][diff] != -1 ? map[j][diff] : 1) + 1;

            map[i][diff] = max(currLen, newLen);
            maxLen = max(maxLen, map[i][diff]);
        }
    }

    return maxLen;
}

bool canReorderDoubled(vector<int> &arr)
{
    unordered_map<int, int> map;
    for (int ele : arr)
        map[ele]++;

    sort(arr.begin(), arr.end(), [](int a, int b)
         { return abs(a) < abs(b); });

    for (int ele : arr)
    {
        if (map[ele] == 0)
            continue;

        if (map[2 * ele] == 0)
            return false;

        map[ele]--;
        map[2 * ele]--;
    }
    return true;
}

// 380
class RandomizedSet
{
public:
    unordered_map<int, int> map;
    vector<int> list;

    RandomizedSet()
    {
    }

    bool insert(int val)
    {
        if (map.find(val) != map.end())
            return false;

        int idx = list.size();
        map[val] = idx;
        list.push_back(val);
        return true;
    }

    bool remove(int val)
    {
        if (map.find(val) == map.end())
            return false;

        int idx = map[val];
        int lidx = list.size() - 1;
        list[idx] = list[lidx];

        list.pop_back();
        map[list[idx]] = idx;
        map.erase(val);

        return true;
    }

    int getRandom()
    {
        int idx = rand() % list.size();
        return list[idx];
    }
};
