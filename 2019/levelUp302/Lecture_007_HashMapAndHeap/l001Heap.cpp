#include <iostream>
#include <vector>
#include <queue>

using namespace std;

void test1()
{
    vector<int> arr{2, 3, 11, 5, -1, 1, 7, 23, 6, 2, 234};

    // priority_queue<int> pq;   //MaxHeap
    priority_queue<int, vector<int>, greater<int>> pq; // minPQ

    for (int ele : arr) // nlogn
        pq.push(ele);

    while (pq.size() != 0)
    {
        cout << pq.top() << " ";
        pq.pop();
    }
}

void test2()
{
    vector<vector<int>> arr{{2, 3, 11}, {5, -1, 1}, {2, 23, 6}, {20, 234, 23}};
    // priority_queue<vector<int>> pq; //MaxHeap
    priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq; // minPQ

    for (vector<int> ele : arr) // nlogn
        pq.push(ele);

    while (pq.size() != 0)
    {
        vector<int> ar = pq.top();
        for (int ele : ar)
            cout << ele << " ";
        cout << endl;
        pq.pop();
    }
}

struct compare_
{
    bool isMax = true;

public:
    bool operator()(const vector<int> &ar1, const vector<int> &ar2) const
    {
        if (!isMax)
            return ar1[0] > ar2[0];
        else
            return ar2[0] > ar1[0];
    }
};

void test3()
{
    vector<vector<int>> arr{{2, 3, 11}, {5, -1, 1}, {2, 23, 6}, {20, 234, 23}};

    bool isMax = false;
    priority_queue<vector<int>, vector<vector<int>>, compare_> pq; //MaxHeap

    for (vector<int> ele : arr) // nlogn
        pq.push(ele);

    while (pq.size() != 0)
    {
        vector<int> ar = pq.top();
        for (int ele : ar)
            cout << ele << " ";
        cout << endl;
        pq.pop();
    }
}

void solve()
{
    // test1();
    // test2();
    test3();
}

int main()
{
    solve();
    return 0;
}