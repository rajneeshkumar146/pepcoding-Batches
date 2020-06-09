#include <iostream>
#include <queue>

using namespace std;

void downheapify(vector<int> &arr, int idx, int lidx)
{
    int maxidx = idx;
    int lci = idx >> 1 + 1;
    int rci = idx >> 1 + 2;

    if (lci <= lidx && arr[lci] > arr[maxidx])
        maxidx = lci;
    if (rci <= lidx && arr[rci] > arr[maxidx])
        maxidx = rci;

    if (idx != maxidx)
    {
        swap(arr[idx], arr[maxidx]);
        downheapify(arr, maxidx, lidx);
    }
}

int findKthLargest(vector<int> &arr, int k)
{
    int n = arr.size() - 1;
    for (int i = n; i >= 0; i--)
        downheapify(arr, i, n);

    for (int i = 0; i < k; i++)
    {
        swap(arr[0], arr[n - i]);
        downheapify(arr, 0, n - i - 1);
    }

    return arr[n - k + 1];
}

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
            if (pq.size() > k)
                pq.pop();
        }
    }

    int add(int val)
    {
        pq.push(ele);
        if (pq.size() > size)
            pq.pop();
        return pq.top();
    }
}
}
;

int main()
{

    return 0;
}