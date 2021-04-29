#include <iostream>
#include <queue>
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
