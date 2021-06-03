#include <iostream>
#include <vector>
#include <unordered_map>
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

//33
int search(vector<int> &nums, int target)
{
    int n = nums.size(), si = 0, ei = n - 1;

    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[si] <= nums[mid])
        {
            if (nums[si] <= target && target < nums[mid])
                ei = mid - 1;
            else
                si = mid + 1;
        }
        else
        {
            if (nums[mid] < target && target <= nums[ei])
                si = mid + 1;
            else
                ei = mid - 1;
        }
    }

    return -1;
}

//81
bool search(vector<int> &arr, int tar)
{
    int n = arr.size(), si = 0, ei = n - 1;

    while (si <= ei)
    {

        int mid = (si + ei) / 2;

        if (arr[mid] == tar || arr[ei] == tar)
            return true;
        else if (arr[si] < arr[mid])
        {
            if (arr[si] <= tar && tar < arr[mid])
                ei = mid - 1;
            else
                si = mid + 1;
        }
        else if (arr[mid] < arr[ei])
        {
            if (arr[mid] < tar && tar <= arr[ei])
                si = mid + 1;
            else
                ei = mid - 1;
        }
        else
            ei--;
    }

    return false;
}

//153
int findMin(vector<int> &arr)
{
    int n = arr.size(), si = 0, ei = n - 1;
    if (arr[si] <= arr[ei])
        return arr[si];

    while (si < ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] < arr[ei])
            ei = mid;
        else
            si = mid + 1; // (arr[si] <= arr[mid])
    }

    return arr[si];
}

int findMin(vector<int> &arr)
{
    int n = arr.size(), si = 0, ei = n - 1;
    if (arr[si] < arr[ei])
        return arr[si];

    while (si < ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] < arr[ei])
            ei = mid;
        else if (arr[mid] > arr[ei])
            si = mid + 1;
        else
            ei--;
    }

    return arr[si];
}

// 167
vector<int> twoSum(vector<int> &arr, int target)
{

    int n = arr.size(), si = 0, ei = n - 1;

    while (si < ei)
    {
        int sum = arr[si] + arr[ei];
        if (sum == target)
            return {si + 1, ei + 1};
        else if (sum < target)
            si++;
        else
            ei--;
    }

    return {};
}

vector<vector<int>> twoSum(vector<int> &arr, int target, int si, int ei)
{
    vector<vector<int>> ans;
    while (si < ei)
    {
        int sum = arr[si] + arr[ei];
        if (sum == target)
        {
            ans.push_back({arr[si], arr[ei]});

            si++;
            ei--;
            while (si < ei && arr[si] == arr[si - 1])
                si++;
            while (si < ei && arr[ei] == arr[ei + 1])
                ei--;
        }
        else if (sum < target)
            si++;
        else
            ei--;
    }

    return ans;
}

void prepareAns(vector<vector<int>> &ans, vector<vector<int>> &smallAns, int fixEle)
{

    for (vector<int> &arr : smallAns)
    {
        vector<int> ar;
        ar.push_back(fixEle);
        for (int ele : arr)
            ar.push_back(ele);
        ans.push_back(ar);
    }
}

vector<vector<int>> threeSum(vector<int> &arr, int target, int si, int ei)
{
    vector<vector<int>> ans;
    for (int i = si; i < ei;)
    {
        vector<vector<int>> smallAns = twoSum(arr, target - arr[i], i + 1, ei);
        prepareAns(ans, smallAns, arr[i]);
        i++;
        while (i < ei && arr[i] == arr[i - 1])
            i++;
    }

    return ans;
}

vector<vector<int>> fourSum(vector<int> &arr, int target, int si, int ei)
{
    vector<vector<int>> ans;
    for (int i = si; i < ei;)
    {
        vector<vector<int>> smallAns = threeSum(arr, target - arr[i], i + 1, ei);
        prepareAns(ans, smallAns, arr[i]);
        i++;
        while (i < ei && arr[i] == arr[i - 1])
            i++;
    }

    return ans;
}

vector<vector<int>> kSum(vector<int> &arr, int target, int k, int si, int ei)
{
    if (k == 2)
        return twoSum(arr, target, si, ei);

    vector<vector<int>> ans;
    for (int i = si; i < ei;)
    {
        vector<vector<int>> smallAns = kSum(arr, target - arr[i], k - 1, i + 1, ei);
        prepareAns(ans, smallAns, arr[i]);
        i++;
        while (i < ei && arr[i] == arr[i - 1])
            i++;
    }

    return ans;
}

//454
int twoSumCount(vector<int> &nums1, vector<int> &nums2, int target)
{
    unordered_map<int, int> map;
    for (int ele : nums1)
        map[ele]++;

    int count = 0;
    for (int ele : nums2)
        if (map.find(target - ele) != map.end())
            count += map[target - ele];

    return count;
}

int fourSumCount(vector<int> &nums1, vector<int> &nums2, vector<int> &nums3, vector<int> &nums4)
{
    unordered_map<int, int> map;
    for (int e1 : nums1)
        for (int e2 : nums2)
            map[e1 + e2]++;

    int count = 0, target = 0;
    for (int e1 : nums3)
        for (int e2 : nums4)
            if (map.find(target - (e1 + e2)) != map.end())
                count += map[target - (e1 + e2)];

    return count;
}

int insertPosition(vector<int> &arr, int data)
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

vector<int> findClosestElements(vector<int> arr, int k, int x)
{
    int n = arr.size();

    int idx = insertPosition(arr, x);
    int lr = max(0, idx - k);
    int rr = min(n - 1, idx + k);

    while ((rr - lr + 1) > k)
    {
        if (x - arr[lr] > arr[rr] - x)
            lr++;
        else
            rr--;
    }
    return {arr.begin() + lr, arr.begin() + rr + 1};
}

int insertPosition(vector<int> &list, int data)
{
    int n = list.size(), si = 0, ei = n - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (list[mid] <= data)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    int insertPos = si;
    int lastIndex = si - 1;
    return lastIndex >= 0 && list[lastIndex] == data ? lastIndex : insertPos;
}

int lengthOfLIS(vector<int> &nums)
{
    int n = nums.size();
    if (n <= 1)
        return n;

    vector<int> list;
    for (int ele : nums)
    {
        int loc = insertPosition(list, ele);
        if (loc == list.size())
            list.push_back(ele);
        else
            list[loc] = ele;
    }

    return list.size();
}

int main()
{
    return 0;
}