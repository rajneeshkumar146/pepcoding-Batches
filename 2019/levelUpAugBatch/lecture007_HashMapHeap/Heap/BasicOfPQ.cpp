#include <iostream>
#include <queue>

using namespace std;

void set1(vector<int> &arr)
{
    // priority_queue<int> pq; // maxPQ
    priority_queue<int, vector<int>, greater<int>> pq; // minPQ

    for (int ele : arr)
        pq.push(ele);
    while (pq.size() != 0)
    {
        cout << pq.top() << " ";
        pq.pop();
    }
}

class pair_
{
public:
    int val1 = 0;
    int val2 = 0;

    pair_(int val1, int val2)
    {
        this->val1 = val1;
        this->val2 = val2;
    }
};

struct comp
{
public:
    bool opeartor(pair_ const &p, pair_ const &q) const
    {
        return p.val1 > q.val1; // minPQ.
        // return q.val1 > p.val1;  // maxPQ.
    }
};

void set2(vector<vector<int>> &arr)
{
    priority_queue<pair_, vector<pair_>, comp> pq;
    

}

int main()
{
    // vector<int> arr{10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13};
    vector<vector<int>> arr = {{10, 20}, {30, -2}, {-3, -4}, {5, 6}, {7, 8}, {9, 22}, {11, 13}};
    // set1(arr);
    set2(arr);
    return 0;
}