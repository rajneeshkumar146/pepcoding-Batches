#include <iostream>
#include <vector>
#include <queue>
#include <unordered_map>

using namespace std;

//Leetcode 215
int findKthLargest(vector<int> &nums, int k)
{
    priority_queue<int, vector<int>, greater<int>> pq;
    for (int ele : nums)
    {
        pq.push(ele);
        if (pq.size() > k)
            pq.pop();
    }

    return pq.top();
}

//350
vector<int> intersect(vector<int> &nums1, vector<int> &nums2)
{
    unordered_map<int, int> map;
    for (int ele : nums1)
        map[ele]++;
    vector<int> ans;
    for (int ele : nums2)
    {
        if (map.find(ele) != map.end())
        {
            ans.push_back(ele);
            map[ele]--;
            if (map[ele] == 0)
                map.erase(ele);
        }
    }

    return ans;
}

int longestConsecutive(vector<int> &nums)
{
    unordered_map<int, int> map;
    for (int ele : nums)
        map[ele]++;

    int len = 0;
    for (int ele : nums)
    {
        if (map.find(ele) == map.end())
            continue;

        int n = ele;
        int prev = n - 1;
        int next = n + 1;

        while (map.find(prev) != map.end())
        {
            map.erase(prev);
            prev--;
        }

        while (map.find(next) != map.end())
        {
            map.erase(next);
            next++;
        }

        map.erase(ele);
        len = max(len, next - prev - 1);
    }

    return len;
}

//347
vector<int> topKFrequent(vector<int> &nums, int k)
{
    unordered_map<int, int> map;
    for (int ele : nums)
        map[ele]++;

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

    for (pair<int, int> key : map)
    {
        pq.push({key.second, key.first});
        if (pq.size() > k)
            pq.pop();
    }

    vector<int> ans;
    while (pq.size() != 0)
    {
        ans.push_back(pq.top().second);
        pq.pop();
    }

    return ans;
}
