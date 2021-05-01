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
