#include <iostream>
#include <vector>
using namespace std;

int binarySearch(vector<int> &arr, int data)
{
    int n = arr.size(), si = 0, ei = n - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2; // si + (ei - si) / 2;
        if (arr[mid] == data)
            return mid;
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return -1;
}

int firstIndex(vector<int> &arr, int data)
{
    int n = arr.size(), si = 0, ei = n - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] == data)
        {
            if (mid - 1 >= 0 && arr[mid - 1] == data)
                ei = mid - 1;
            else
                return mid;
        }
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return -1;
}

int lastIndex(vector<int> &arr, int data)
{
    int n = arr.size(), si = 0, ei = n - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] == data)
        {
            if (mid + 1 < n && arr[mid + 1] == data)
                si = mid + 1;
            else
                return mid;
        }
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return -1;
}

int firstIndex(vector<int> &arr, int data)
{
    int n = arr.size(), si = 0, ei = n - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return si < n && arr[si] == data ? si : -1;
}

int lastIndex(vector<int> &arr, int data)
{
    int n = arr.size(), si = 0, ei = n - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] <= data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    si--;
    return si >= 0 && arr[si] == data ? si : -1;
}

//34
vector<int> searchRange(vector<int> &arr, int data)
{
    return {firstIndex(arr, data), lastIndex(arr, data)};
}

// important
int insertLocation(vector<int> &arr, int data)
{
    int n = arr.size(), si = 0, ei = n - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] <= data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return si;
}

int perfectPosOfElement(vector<int> &arr, int data)
{
    int insertPos = insertLocation(arr, data);
    int lastIndex = insertPos - 1;

    return (lastIndex >= 0 && arr[lastIndex] == data) ? lastIndex : insertPos;
}

int nearestElement(vector<int> &arr, int data)
{
    if (arr.size() == 0)
        return 0;

    int n = arr.size();
    if (data <= arr[0] || data >= arr[n - 1])
        return data <= arr[0] ? arr[0] : arr[n - 1];

    int si = 0, ei = n - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] <= data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return data - arr[ei] <= arr[si] - data ? arr[ei] : arr[si];
}

int main()
{
    return 0;
}