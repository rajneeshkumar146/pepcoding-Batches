#include <iostream>
#include <vector>

using namespace std;

int binarySearch(vector<int> &arr, int data)
{
    int si = 0, ei = arr.size() - 1;

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

// int binarySearchFirstIndex(vector<int> &arr, int data)
// {
// }

// int binarySearchLastIndex(vector<int> &arr, int data)
// {
// }

// int binarySearchNearestIndex(vector<int> &arr, int data)
// {
// }

int searchInsert(vector<int> &arr, int target){
    int si = 0, ei = arr.size();
    while (si < ei){
        int mid = (si + ei) / 2;
        if (arr[mid] < target)
            si = mid + 1;
        else
            ei = mid;
    }

    return ei;
}
