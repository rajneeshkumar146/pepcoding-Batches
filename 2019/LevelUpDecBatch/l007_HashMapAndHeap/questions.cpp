#include <iostream>
#include <queue>
#include <uonorderd_map>
using namespace std;

int findKthLargest(vector<int> &nums, int k)
{
    //215
    priority_queue<int, vector<int>, greater<int>> pq; // min
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
    priority_queue<int> pq; // max
    for (int ele : nums)
    {
        pq.push(ele);
        if (pq.size() > k)
            pq.pop();
    }

    return pq.top();
}

//703
class KthLargest
{
public:
    priority_queue<int, vector<int>, greater<int>> pq;
    int K = 0;

    KthLargest(int k, vector<int> &nums)
    {
        this->K = k;
        for (int ele : nums)
        {
            this->pq.push(ele);
            if (this->pq.size() > this->K)
                this->pq.pop();
        }
    }

    int add(int val)
    {
        this->pq.push(val);
        if (this->pq.size() > this->K)
            this->pq.pop();

        return this->pq.top();
    }
};

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

//347
vector<int> topKFrequent(vector<int> &nums, int k)
{

    unordered_map<int, int> map;
    for (int ele : nums)
        map[ele]++;

    //{freq,val}
    priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;

    for (pair<int, int> key : map)
    {
        int val = key.first;
        int freq = key.second;

        pq.push({freq, val});
        if (pq.size() > k)
            pq.pop();
    }

    vector<int> ans;
    while (pq.size() != 0)
    {
        vector<int> p = pq.top();
        pq.pop();

        int freq = p[0];
        int val = p[1];

        ans.push_back(val);
    }

    return ans;
}

//973
class comp
{
public:
    bool operator()(const vector<int> &a, const vector<int> &b) const
    {
        int d1 = a[0] * a[0] + a[1] * a[1]; // x1^2 + y1^2
        int d2 = b[0] * b[0] + b[1] * b[1]; // x2^2 + y2^2

        return d2 > d1;
    }
};

vector<vector<int>> kClosest(vector<vector<int>> &points, int k)
{
    priority_queue<vector<int>, vector<vector<int>>, comp> pq;

    for (vector<int> &p : points)
    {
        pq.push(p);
        if (pq.size() > k)
            pq.pop();
    }

    vector<vector<int>> ans;
    while (pq.size() != 0)
    {
        ans.push_back(pq.top());
        pq.pop();
    }

    return ans;
}

vector<vector<int>> kClosest(vector<vector<int>> &points, int k)
{
    //{d,x,y}
    priority_queue<vector<int>> pq; // maxPQ

    for (vector<int> &p : points)
    {
        int x = p[0];
        int y = p[1];
        pq.push({x * x + y * y, x, y});
        if (pq.size() > k)
            pq.pop();
    }

    vector<vector<int>> ans;
    while (pq.size() != 0)
    {
        vector<int> p = pq.top();
        pq.pop();
        int x = p[1];
        int y = p[2];

        ans.push_back({x, y});
    }

    return ans;
}

int kthSmallest(vector<vector<int>> &matrix, int k)
{

    int n = matrix.size(), m = matrix[0].size();
    //{val,x,y};
    priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;

    for (int i = 0; i < n; i++)
    {
        pq.push({matrix[i][0], i, 0});
    }

    int ans = 0;
    while (k-- > 0)
    {
        vector<int> rv = pq.top();
        pq.pop();
        int val = rv[0];
        int x = rv[1];
        int y = rv[2];

        ans = val;
        y++;
        if (y < m)
            pq.push({matrix[x][y], x, y});
    }

    return ans;
}
