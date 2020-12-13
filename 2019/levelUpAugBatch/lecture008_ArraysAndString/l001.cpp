#include <iostream>
#include <vector>
#include <queue>
#include <unordered_map>

using namespace std;

void seperatePositiveNegative(vector<int> &arr)
{
    if (arr.size() == 0)
        return;

    int piviot = -1, idx = 0, n = arr.size();
    while (idx < n)
    {
        if (arr[idx] >= 0)
            swap(arr[++piviot], arr[idx]);
        idx++;
    }
}

void segregateZeroOnes(vector<int> &arr)
{
    if (arr.size() == 0)
        return;

    int piviot = -1, idx = 0, n = arr.size();
    while (idx < n)

    {
        if (arr[idx] == 0)
            swap(arr[++piviot], arr[idx]);
        idx++;
    }
}

//Leetcode 75
void segregateZeroOnesAndTwo(vector<int> &arr)
{
    if (arr.size() == 0)
        return;

    int p1 = -1, idx = 0, n = arr.size(), p2 = n - 1;
    while (idx <= p2)
    {
        if (arr[idx] == 0)
            swap(arr[++p1], arr[idx++]);
        else if (arr[idx] == 2)
            swap(arr[idx], arr[p2--]);
        else
            idx++;
    }
}

int lengthOfLongestSubstring(string s)
{
    if (s.length() <= 1)
        return s.length();

    int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
    int Maxsi = 0, Maxei = 0;
    vector<int> map(128, 0);

    while (ei < n)
    {
        if (map[s[ei++]]++ > 0)
            count++;

        while (count > 0)
        {
            if (map[s[si++]]-- > 1)
                count--;
        }

        // len = max(len,ei - si);
        if (ei - si > len)
        {
            len = ei - si;
            Maxei = ei;
            Maxsi = si;
        }
    }

    return len;
}

int lengthOfLongestSubstringTwoDistinct(string s)
{
    int n = s.length();
    int si = 0, ei = 0, head = 0, len = 0, distinctCount = 0;

    vector<int> freq(128, 0);
    while (ei < n)
    {
        if (freq[s[ei++]]++ == 0)
            distinctCount++;

        while (distinctCount > 2)
            if (freq[s[si++]]-- == 1)
                distinctCount--;

        len = (ei - si > len) ? ei - (head = si) : len;
    }

    return len;
}

bool isVowel(char ch)
{
    return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
}

int maxVowels(string s, int k)
{
    int n = s.length();
    int si = 0, ei = 0, vowelsCount = 0, maxVowelsCount = 0;
    while (ei < n)
    {
        if (isVowel(s[ei++]))
            vowelsCount++;

        if ((ei - si) == k)
        {
            maxVowelsCount = max(maxVowelsCount, vowelsCount);
            if (isVowel(s[si++]))
                vowelsCount--;
        }
    }

    return maxVowelsCount;
}

vector<int> maxSlidingWindow(vector<int> &nums, int k)
{
    // {arr[i],i}
    priority_queue<pair<int, int>> pq; // max PQ.

    int n = nums.size();
    vector<int> ans;
    for (int i = 0; i < n; i++)
    {
        while (pq.size() != 0 && pq.top().second <= i - k)
            pq.pop();
        pq.push({nums[i], i});

        if (i >= k - 1)
            ans.push_back(pq.top().first);
    }

    return ans;
}

// https://practice.geeksforgeeks.org/problems/largest-subarray-of-0s-and-1s/1
int maxLen(int arr[], int N)
{
    if (N == 0)
        return 0;
    int len = 0;
    unordered_map<int, int> map;   // sum , index
    map[0] = -1;

    int sum = 0;
    for (int i = 0; i < N; i++)
    {
        int val = arr[i];
        if(val == 0) val = -1;
        sum += val;

        if(map.find(sum) != map.end()) len = max(len, i - map[sum]);
        else map[sum] = i;
    }

    return len;
}

int maxLen(int arr[], int N)
{
    if (N == 0)
        return 0;

    unordered_map<int, int> map;   // sum , index
    map[0] = 1;

    int sum = 0, count = 0;
    for (int i = 0; i < N; i++)
    {
        int val = arr[i];
        if(val == 0) val = -1;
        sum += val;
        count += map[sum];
        map[sum]++;

    }

    // for(pair<int,int> key : map){
    //     count += (key.second * (key.second - 1)) / 2;
    // }

    return count;
}

int longSubarrWthSumDivByK(int arr[], int n, int k){
    if (n == 0)
        return 0;
        
    int len = 0;
    vector<int> map(1000000 + 1,-2);
    map[0] = -1;

    int sum = 0;
    for (int i = 0; i < n; i++){
        sum += arr[i];

        int SUM = (sum % k + k) % k;
        if(map[SUM] != -2) len = max(len, i - map[SUM]);
        else map[SUM] = i;
    }

    return len;
}

// ======================================== 904 -> for you

int totalFruit_(vector<int> &arr)
{
    int n = arr.size();
    int si = 0, ei = 0, count = 0, len = 0;
    vector<int> freq(40002, 0); // 1 <= arr.length, arr[i] <= 40000

    while (ei < n)
    {
        if (freq[arr[ei++]]++ == 0)
            count++;

        while (count > 2)
        {
            if (freq[arr[si++]]-- == 1)
                count--;
        }
        len = max(len, ei - si);
    }
    return len;
}

int totalFruit(vector<int> &tree)
{
    return totalFruit_(tree);
}



int main()
{
}