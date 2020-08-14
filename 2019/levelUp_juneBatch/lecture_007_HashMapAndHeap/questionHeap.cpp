#include <iostream>
#include <vector>
#include <queue>

using namespace std;

// Leetcode 215
int findKthLargest(vector<int> &nums, int k)
{
    // priority_queue<int> pq;  // max heap
    priority_queue<int, vector<int>, greater<int>> pq; // min  heap

    for (int ele : nums)
    {
        pq.push(ele);
        if (pq.size() > k)
            pq.pop();
    }

    return pq.top();
}

int findKthSmallest(vector<int> &nums, int k)
{
    priority_queue<int> pq; // max heap
    // priority_queue<int, vector<int>, greater<int>> pq;   // min  heap

    for (int ele : nums)
    {
        pq.push(ele);
        if (pq.size() > k)
            pq.pop();
    }

    return pq.top();
}

class KthLargest
{
public:
    priority_queue<int, vector<int>, greater<int>> pq; // min
    int size = -1;
    KthLargest(int k, vector<int> &nums)
    {
        size = k;
        for (int ele : nums)
        {
            add(ele);
        }
    }

    int add(int val)
    {
        pq.push(val);
        if (pq.size() > size)
            pq.pop();
        return pq.top();
    }
};

//Leetcode 378
int kthSmallest(vector<vector<int>> &matrix, int k)
{
    int n = matrix.size();
    int m = matrix[0].size();

    // val, i, j
    priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;
    // priority_queue<vector<int>> pq;

    for (int i = 0; i < n; i++)
        pq.push({matrix[i][0], i, 0});

    while (k-- > 1)
    {
        vector<int> rvtx = pq.top();
        pq.pop();
        int r = rvtx[1];
        int c = rvtx[2];
        if (c + 1 < m)
            pq.push({matrix[r][c + 1], r, c + 1});
    }

    return pq.top()[0];
}

//Leetcode 973
class kClosestPair
{
public:
    int i = 0;
    int j = 0;

    kClosestPair(int i, int j)
    {
        this->i = i;
        this->j = j;
    }
};

struct compa_
{
public:
    bool operator()(const kClosestPair p1, const kClosestPair p2)
    {
        int r1 = p1.i * p1.i + p1.j * p1.j;
        int r2 = p2.i * p2.i + p2.j * p2.j;

        return r2 > r1; // other - this, just replace '-' with '>'
    }
};

vector<vector<int>>
kClosest(vector<vector<int>> &arr, int k)
{
    priority_queue<kClosestPair, vector<kClosestPair>, compa_> pq;

    for (vector<int> &p : arr)
    {
        pq.push(kClosestPair(p[0], p[1]));
        if (pq.size() > k)
            pq.pop();
    }

    vector<vector<int>> ans;
    while (pq.size() != 0)
    {
        kClosestPair p = pq.top();
        pq.pop();
        ans.push_back({p.i, p.j});
    }

    return ans;
}