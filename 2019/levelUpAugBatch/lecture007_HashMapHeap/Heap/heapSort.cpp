#include <iostream>
#include <vector>

using namespace std;

bool compareTo(int a, int b, vector<int> &arr, bool isMax)
{
    if (isMax)
        return arr[a] > arr[b];
    else
        return arr[b] > arr[a];
}

void downHeapify(int pi, int n, vector<int> &arr, bool isMax)
{
    int maxIdx = pi;
    int lci = 2 * pi + 1;
    int rci = 2 * pi + 2;

    if (lci <= n && compareTo(lci, maxIdx, arr, isMax))
        maxIdx = lci;
    if (rci <= n && compareTo(rci, maxIdx, arr, isMax))
        maxIdx = rci;

    if (maxIdx != pi)
    {
        swap(arr[maxIdx], arr[pi]);
        downHeapify(maxIdx, n, arr, isMax);
    }
}

void heapSort()
{
    vector<int> arr = {10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13};
    bool isMax = false;
    int n = arr.size() - 1;
    for (int i = n; i >= 0; i--)
        downHeapify(i, n, arr, isMax);

    for (int i = n; i >= 0; i--)
    {
        swap(arr[0], arr[n--]);
        downHeapify(0, n, arr, isMax);
    }

    for (int d : arr)
        cout << d << " ";
}
int main()
{
    heapSort();
    return 0;
}