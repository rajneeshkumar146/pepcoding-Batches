#include <iostream>
#include <vector>

using namespace std;

vector<int> pq;

void display()
{
    for (int ele : pq)
        cout << ele << " ";
    cout << endl;
}

void upheapify(int ci)
{
    int pi = (ci - 1) / 2;
    if (pi >= 0 && pq[pi] < pq[ci])
    {
        swap(pq[pi], pq[ci]);
        upheapify(pi);
    }
}

void downHeapify(int pi)
{
    int lci = 2 * pi + 1;
    int rci = 2 * pi + 2;
    int maxi = pi;

    if (lci < pq.size() && pq[lci] > pq[maxi])
        maxi = lci;
    if (rci < pq.size() && pq[rci] > pq[maxi])
        maxi = rci;

    if (maxi != pi)
    {
        swap(pq[maxi], pq[pi]);
        downHeapify(maxi);
    }
}

void push(int val)
{
    pq.push_back(val);
    upheapify(pq.size() - 1);
}

void pop()
{
    int n = pq.size() - 1;
    swap(pq[0], pq[n]);
    pq.erase(pq.begin() + n);
    downHeapify(0);
}

int top()
{
    return pq[0];
}

void SetPriorityQueue(vector<int> &arr)
{
    for (int ele : arr)
        pq.push_back(ele);

    for (int i = pq.size() - 1; i >= 0; i--)
        downHeapify(i);
}

void set1()
{
    vector<int> arr{10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13};
    SetPriorityQueue(arr);

    while (pq.size() != 0)
    {
        // cout << top() << " ";
        display();
        pop();

    }
}

void solve()
{
    set1();
}

int main()
{
    solve();
    return 0;
}
