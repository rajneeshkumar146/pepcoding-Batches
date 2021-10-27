#include <iostream>
#include <vector>

using namespace std;

int binarySearch(vector<int> &arr, int si, int ei, int data)
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

int firstIndex(vector<int> &arr, int data)
{
    if (arr[0] == data)
        return 0;

    int si = 0, ei = arr.size() - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] == data)
        {
            if (arr[mid - 1] == data)
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
    if (arr[arr.size() - 1] == data)
        return arr.size() - 1;

    int si = 0, ei = arr.size() - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] == data)
        {
            if (arr[mid + 1] == data)
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

vector<int> searchRange(vector<int> &nums, int target)
{
    if (nums.size() == 0)
        return {-1, -1};                                        // new int[]{-1,-1};
    return {firstIndex(nums, target), lastIndex(nums, target)}; // new int[]{};
}

int closesetElement(vector<int> &arr, int data)
{
    int si = 0, ei = arr.size() - 1;
    if (data < arr[si])
        return si;
    else if (data > arr[ei])
        return ei;

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

    return data - arr[ei] < arr[si] - data ? ei : si;
}

int perfectLocation(vector<int> &arr, int data)
{
    int si = 0, ei = arr.size();

    while (si < ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] <= data)
            si = mid + 1;
        else
            ei = mid;
    }

    return ei; // si
}

bool searchMatrix(vector<vector<int>> &matrix, int target)
{
    int n = matrix.size(), m = matrix[0].size();
    int si = 0, ei = n * m - 1;

    while (si <= ei)
    {
        int mid = (si + ei) / 2, r = mid / m, c = mid % m;
        if (matrix[r][c] == target)
            return true;
        else if (matrix[r][c] < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return false;
}

// 658
vector<int> findClosestElements(vector<int> &arr, int k, int x)
{
    int n = arr.size();
    if (x <= arr[0])
        return {arr.begin(), arr.begin() + k};
    else if (x >= arr[n - 1])
        return {arr.end() - k, arr.end()};

    int idx = perfectLocation(arr, x);
    int si = max(0, idx - k);
    int ei = min(n - 1, idx + k);

    while ((ei - si + 1) > k)
    {
        if ((x - arr[si]) > (arr[ei] - x))
            si++;
        else
            ei--;
    }

    return {arr.begin() + si, arr.begin() + ei + 1};
}