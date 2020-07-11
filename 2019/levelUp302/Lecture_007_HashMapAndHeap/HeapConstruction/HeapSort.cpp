#include <iostream>
#include <vector>

using namespace std;

void downHeapify(vector<int> &arr, int idx, int n)
{
    int lci = 2 * idx + 1;
    int rci = 2 * idx + 2;
    int maxIdx = idx;

    if (lci <= n && arr[lci] > arr[maxIdx])
        maxIdx = lci;
    if (rci <= n && arr[rci] > arr[maxIdx])
        maxIdx = rci;

    if (maxIdx != idx)
    {
        swap(arr[idx], arr[maxIdx]);
        downHeapify(arr, maxIdx, n);
    }
}

void heapSort(vector<int> &arr)
{
    int n = arr.size() - 1;
    for (int i = n; i >= 0; i--)
        downHeapify(arr, i, n);

    int idx = 0;
    while (n != 0)
    {
        swap(arr[0], arr[n--]);
        downHeapify(arr, 0, n);
    }

    for (int ele : arr)
        cout << ele << " ";
}

void solve()
{
    vector<int> arr{10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13};
    heapSort(arr);
}

int main()
{
    solve();
    return 0;
}
