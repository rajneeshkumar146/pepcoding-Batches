#include <iostream>
#include <vector>
#include <unordered_map>
#include <priority_queue>

using namespace std;

//leetcode 215.===========================================

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

//leetcode 703.===============================================

class KthLargest
{
public:
    priority_queue<int, vector<int>, greater<int>> pq;
    int size;
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

//leetcode 347.=======================================================

vector<int> topKFrequent(vector<int> &nums, int k)
{
    unordered_map<int, int> map;
    for (int ele : nums)
        map[ele]++;

    // first -> freq, second -> number and by default it is min PQ.
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

    for (pair<int, int> key : map)
    {
        pq.push({key.second, key.first});
        if(pq.size()>k) pq.pop();
    }

    vector<int> ans;
    while(pq.size()!=0){
        res.push_back(pq.top().second);
        pq.pop();
    }
    return ans;
}
