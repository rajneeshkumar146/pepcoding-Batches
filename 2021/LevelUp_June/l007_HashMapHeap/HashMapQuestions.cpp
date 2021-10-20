#include <iostream>
#include <queue>
#include <vector>
#include <unordered_map>

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