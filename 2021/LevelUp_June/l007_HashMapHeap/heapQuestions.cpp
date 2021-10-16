#include <iostream>
#include <queue>
#include <vector>
#include <unordered_map>

using namespace std;

//378
int kthSmallest(vector<vector<int>> &matrix, int k)
{
    int n = matrix.size();
    int m = matrix[0].size();
    //{ele,r,c}
    priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;

    for (int i = 0; i < n; i++)
        pq.push({matrix[i][0], i, 0});

    int r = 0, c = 0;
    while (k-- > 0)
    {
        vector<int> idx = pq.top();
        pq.pop();
        r = idx[1];
        c = idx[2];
        if (c + 1 < m)
            pq.push({matrix[r][c + 1], r, c + 1});
    }

    return matrix[r][c];
}

//347
vector<int> topKFrequent(vector<int> &nums, int k)
{

    unordered_map<int, int> map;
    for (int ele : nums)
        map[ele]++;

    //{freq,ele}
    priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;

    for (pair<int, int> p : map)
    {
        pq.push({p.second, p.first});
        if (pq.size() > k)
            pq.pop();
    }

    vector<int> ans;
    while (pq.size() != 0)
    {
        vector<int> key = pq.top();
        pq.pop();
        ans.push_back(key[1]);
    }

    return ans;
}

//451