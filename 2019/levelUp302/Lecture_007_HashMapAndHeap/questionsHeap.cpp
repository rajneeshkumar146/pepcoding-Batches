#include <iostream>
#include <queue>

using namespace std;

//Leetcode 215.
int findKthLargest(vector<int> &nums, int k)
{
    priority_queue<int, vector<int>, greater<int>> pq; // for java, default is MIN_PQ
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
    priority_queue<int> pq; // for java, use Collections.reverseOrder()
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
    priority_queue<int, vector<int>, greater<int>> pq;
    int size = -1;
    KthLargest(int k, vector<int> &nums)
    {
        size = k;
        for (int ele : nums)
        {
            pq.push(ele);
            if (pq.size() > size)
                pq.pop();
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

int kthSmallest(vector<vector<int>> &matrix, int k)
{
    int n = matrix.size();
    int m = matrix[0].size();

    // priority_queue<vector<int>,vector<vector<int>>,greater<vector<int>>> pq;
    priority_queue<vector<int>> pq;

    for (int i = 0; i < n; i++)
        pq.push({-matrix[i][0], i, 0});

    while (k-- > 1)
    {
        vector<int> rvtx = pq.top();
        pq.pop();
        if (++rvtx[2] < m)
            pq.push({-matrix[rvtx[1]][rvtx[2]], rvtx[1], rvtx[2]});
    }

    return -pq.top()[0];
}

void solve()
{
}

int main()
{
    solve();
    return 0;
}
