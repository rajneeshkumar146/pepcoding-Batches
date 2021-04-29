#include <iostream>
#include <queue>
#include <vector>
using namespace std;

void test1_maxPQ(vector<int> &arr)
{
    priority_queue<int> pq; // Max PQ.
    for (int ele : arr)
        pq.push(ele);

    while (pq.size() != 0)
    {
        cout << pq.top();
        pq.pop();
    }
}

void test2_maxPQ(vector<int> &arr)
{
    priority_queue<int, vector<int>, greater<int>> pq; // Min PQ.
    for (int ele : arr)
        pq.push(ele);

    while (pq.size() != 0)
    {
        cout << pq.top();
        pq.pop();
    }
}

class compator
{
public:
    bool operator()(const vector<int> &a, const vector<int> &b) const
    {
        return a[0] > b[0]; // this > other, min PQ.
        // return b[0] > a[0]; // this > other, max PQ.
    }
};

void test2_maxPQ(vector<vector<int>> &arr)
{
    priority_queue<vector<int>, vector<vector<int>>, compator> pq; // Min PQ.
    for (vector<int> &a : arr)
        pq.push(a);

    while (pq.size() != 0)
    {
        vector<int> a = pq.top();
        int i = a[0];
        int j = a[1];
        cout << "(" << i << "," << j << ") " << endl;
        pq.pop();
    }
}

int main()
{
    vector<vector<int>> arr{{2, 5}, {1, -1}, {0, -4}, {-6, 3}, {6, 9}, {9, 40}};
    test2_maxPQ(arr);
    return 0;
}
