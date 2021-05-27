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

//74
bool searchMatrix(vector<vector<int>> &matrix, int target)
{
    int n = matrix.size(), m = matrix[0].size(), si = 0, ei = n * m - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        int r = mid / m, c = mid % m;
        if (matrix[r][c] == target)
            return true;
        else if (matrix[r][c] < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return false;
}

//240
bool searchMatrix(vector<vector<int>> &matrix, int target)
{

    int n = matrix.size(), m = matrix[0].size(), si = n - 1, ei = 0;
    while (si >= 0 && ei < m)
    {
        int ele = matrix[si][ei];
        if (ele == target)
            return true;
        else if (ele < target)
            ei++;
        else
            si--;
    }

    return false;
}

long long InversionAcrossArray(vector<long long> &arr, int l, int r, int mid, vector<long long> &sortedArray)
{
    int lsi = l, lei = mid;
    int rsi = mid + 1, rei = r;

    int k = 0;
    long count = 0;
    while (lsi <= lei && rsi <= rei)
    {
        if (arr[lsi] > arr[rsi])
        {
            count += (lei - lsi + 1);
            sortedArray[k++] = arr[rsi++];
        }
        else
            sortedArray[k++] = arr[lsi++];
    }

    while (lsi <= lei)
        sortedArray[k++] = arr[lsi++];
    while (rsi <= rei)
        sortedArray[k++] = arr[rsi++];

    // for (k = 0; k < sortedArray.length; k++)
    // arr[k + l] = sortedArray[k];

    k = 0;
    for (int i = l; i <= r; i++)
        arr[i] = sortedArray[k++];

    return count;
}

long long inversionCount(vector<long long> &arr, int si, int ei, vector<long long> &sortedArray)
{
    if (si >= ei)
        return 0;

    int mid = (si + ei) / 2;
    long ICL = inversionCount(arr, si, mid, sortedArray); // IC : Inversion Count, L = left , R = Right
    long ICR = inversionCount(arr, mid + 1, ei, sortedArray);

    return (ICL + ICR + InversionAcrossArray(arr, si, ei, mid, sortedArray));
}

long long inversionCount(long long ar[], long N)
{
    if (N <= 1)
        return 0;

    vector<long long> arr;
    for (int i = 0; i < N; i++)
        arr.push_back(ar[i]);

    vector<long long> sortedArray(N, 0);
    return inversionCount(arr, 0, (int)N - 1, sortedArray);
}

int main()
{
    return 0;
}