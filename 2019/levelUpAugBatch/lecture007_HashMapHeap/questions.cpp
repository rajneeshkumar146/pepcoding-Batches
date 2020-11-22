#include <iostream>
#include <vector>
#include <queue>

using namespace std;

//Leetcode 215
int findKthLargest(vector<int> &nums, int k)
{
    priority_queue<int,vector<int>,greater<int>> pq;
    for(int ele : nums){
        pq.push(ele);
        if(pq.size() > k) pq.pop();
    }

    return pq.top();
}
