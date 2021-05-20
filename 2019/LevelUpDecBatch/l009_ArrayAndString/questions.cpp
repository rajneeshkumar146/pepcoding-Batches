#include <iostream>
#include <vector>
#include <unordered_map>
#include <queue>
#include <list>

using namespace std;

void reverse(vector<int> &arr, int i, int j)
{
    while (i < j)
    {
        swap(arr[i++], arr[j--]);
    }
}

void rotateByK(vector<int> &arr, int r)
{
    int n = arr.size();
    // r %= n;
    // if (r < 0)
    // r += n;

    r = (r % n + n) % n;

    reverse(arr, 0, n - 1);
    reverse(arr, n - r, n - 1);
    reverse(arr, 0, n - r - 1);
}

void segregatePositiveAndNegative(vector<int> &arr)
{
    int n = arr.size(), pt = -1, itr = 0;
    while (itr < n)
    {
        if (arr[itr] < 0)
            swap(arr[++pt], arr[itr]);
        itr++;
    }
}

void segregateZeroAndOne(vector<int> &arr)
{
    int n = arr.size(), pt = -1, itr = 0;
    while (itr < n)
    {
        if (arr[itr] == 0)
            swap(arr[++pt], arr[itr]);
        itr++;
    }
}

void segregateZeroOneAndTwo(vector<int> &arr)
{
    int n = arr.size(), pt1 = -1, pt2 = n - 1, itr = 0;
    while (itr <= pt2)
    {
        if (arr[itr] == 0)
            swap(arr[++pt1], arr[itr++]);
        else if (arr[itr] == 2)
            swap(arr[pt2--], arr[itr]);
        else
            itr++;
    }
}

// https://practice.geeksforgeeks.org/problems/max-sum-in-the-configuration/1
int max_sum(vector<int> &arr, int n)
{
    int sum = 0, sumWithIndex = 0;
    for (int i = 0; i < n; i++)
    {
        sum += arr[i];
        sumWithIndex += arr[i] * i;
    }

    int maxAns = sumWithIndex;
    for (int i = 1; i < n; i++)
    {
        sumWithIndex = sumWithIndex - sum + arr[i - 1] * n;
        maxAns = max(maxAns, sumWithIndex);
    }

    return maxAns;
}

int atMostKDistinct(vector<int> &arr, int k)
{
    unordered_map<int, int> freq;
    int n = arr.size(), si = 0, ei = 0, ans = 0;

    while (ei < n)
    {
        freq[arr[ei++]]++;
        while (freq.size() > k)
        {
            if (freq[arr[si++]]-- == 1)
                freq.erase(arr[si - 1]);
        }

        ans += ei - si;
    }

    return ans;
}

int subarraysWithKDistinct(vector<int> &arr, int k)
{
    return atMostKDistinct(arr, k) - atMostKDistinct(arr, k - 1);
}

vector<int> maxSlidingWindow(vector<int> &nums, int k)
{
    // {val, index}
    priority_queue<vector<int>> pq;

    int n = nums.size(), idx = 0;
    vector<int> ans;
    for (int i = 0; i < nums.size(); i++)
    {
        while (pq.size() != 0 && pq.top()[1] <= i - k)
            pq.pop();

        pq.push({nums[i], i});

        if (i >= k - 1)
            ans.push_back(pq.top()[0]);
    }
    return ans;
}

vector<int> maxSlidingWindow(vector<int> &nums, int k)
{
    list<int> deque;
    int n = nums.size(), idx = 0;
    vector<int> ans;
    for (int i = 0; i < n; i++)
    {
        while (deque.size() != 0 && deque.front() <= i - k)
            deque.pop_front();

        while (deque.size() != 0 && nums[deque.back()] <= nums[i])
            deque.pop_back();

        deque.push_back(i);

        if (i >= k - 1)
            ans.push_back(nums[deque.front()]);
    }
    return ans;
}


