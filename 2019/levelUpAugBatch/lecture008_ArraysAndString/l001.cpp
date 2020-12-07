#include <iostream>
#include <vector>
#include <queue>

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
    priority_queue<pair<int,int>> pq;  // max PQ.
    
    int n = nums.size();
    vector<int> ans;
    for(int i=0;i<n;i++){
        while(pq.size() != 0 && pq.top().second <= i - k) pq.pop();
        pq.push({nums[i],i});

        if(i >= k - 1) ans.push_back(pq.top().first);
    }

    return ans;
}

int main()
{
}