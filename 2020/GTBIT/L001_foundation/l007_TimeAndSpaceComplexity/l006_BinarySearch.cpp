#include <iostream>
#include <vector>

using namespace std;

void input(vector<int> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        cin >> arr[i];
    }
}

void print(vector<int> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        cout << arr[i] << " ";
    }
    cout << endl;
}

int BinarySearch(vector<int> &arr, int data, int si, int ei)
{
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] == data)
            return mid;
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return -1;
}

int nearestEle(vector<int> &arr, int data, int si, int ei)
{
    if (data > arr[ei])
        return arr[ei];
    else if (data < arr[si])
        return arr[si];

    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] == data)
            return mid;
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return (arr[si] - data <= data - arr[ei] ? arr[si] : arr[ei]);
}

int main()
{
    int n;
    cin >> n;
    vector<int> A(n, 0);
    input(A);

    int data;
    cin >> data;
    cout << BinarySearch(A, data, 0, n - 1);
    return 0;
}