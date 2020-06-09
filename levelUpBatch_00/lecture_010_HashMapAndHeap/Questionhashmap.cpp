#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

//leetcode 349.=============================================
vector<int> intersection(vector<int> &one, vector<int> &two)
{
    unordered_map<int, bool> map;
    for (int ele : one)
    {
        map[ele] = true;
    }

    vector<int> ans;
    for (int ele : two)
    {
        if (map.count(ele))
        {
            map.erase(ele);
            ans.push_back(ele);
        }
    }
    return ans;
}

//leetcode 350.==============================================

vector<int> intersect(vector<int> &one, vector<int> &two)
{
    unordered_map<int, int> map;
    for (int ele : one)
    {
        map[ele]++;
    }

    vector<int> ans;

    for (int ele : two)
    {
        if (map.count(ele))
        {
            if (map[ele] > 0)
            {
                ans.push_back(ele);
                map[ele]--;
            }

            if (map[ele] == 0)
                map.erase(ele);
        }
    }

    return ans;
}

//leetcode 128.======================================
int longestConsecutive(vector<int> &nums)
{
    unordered_map<int, int> map;
    for (int ele : nums)
        map[ele]++;

    int len = 0;
    for (int ele : nums)
    {
        if (!map.count(ele))
            continue;

        map.erase(ele);
        int prevEle = ele - 1;
        int nextEle = ele + 1;

        while (map.count(prevEle))
            map.erase(prevEle--);
        while (map.count(nextEle))
            map.erase(nextEle++);

        len = max(len, nextEle - prevEle - 1);
    }

    return len;
}

//leetcode 1027.=====================================================

int longestArithSeqLength(vector<int> &arr)
{
    int n = arr.size();
    int len = 0;
    vector<unordered_map<int, int>> dp(n);

    for (int i = 0; i < n; i++)
    {
        for (int j = i - 1; j >= 0; j--)
        {
            int diff = arr[i] - arr[j];
            int maxEndingHere = dp[j].count(diff) > 0 ? dp[j][diff] + 1 : 2;
            int maxPrevLen = dp[i][diff];
            dp[i][diff] = max(maxEndingHere, maxPrevLen);
            len = max(len, dp[i][diff]);
        }
    }

    return len;
}

// https://www.geeksforgeeks.org/longest-subarray-sum-divisible-k/

int longestSubstringDivisbleByK(vector<int> &arr, int k)
{
    if (arr.size() == 0)
        return 0;

    unordered_map<int, int> umap;
    umap[0] = -1;

    int sum = 0;
    int len = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        sum += arr[i];

        int rem = sum % k;
        if (rem < 0)
            rem += k;

        if (umap.count(rem) == 0)
            umap[rem] = i;
        else
            len = max(len, i - umap[rem]);
    }

    return len;
}

//https://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/
int longestSubarrayOfEqualZeroAndOnes(vector<int> &arr)
{
    if (arr.size() == 0)
        return 0;

    unordered_map<int, int> umap;
    umpa[0] = -1;

    int sum = 0;
    int len = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        val = arr[i];
        if (arr[i] == 0)
            val = -1;

        sum += val;
        if (umap.count(sum) == 0)
            umap[sum] = i;
        else
            len = max(len, i - umap[sum]);
    }

    return len;
}

// count of all subarray having equal zeros and ones.
int countSubarrayOfEqualZeroAndOnes(vector<int> &arr)
{
    if (arr.size() == 0)
        return 0;

    unordered_map<int, int> umap;
    umpa[0] = 1;

    int sum = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        val = arr[i];
        if (arr[i] == 0)
            val = -1;

        sum += val;
        umap[sum]++;
    }
    int ans = 0;

    for (pair<int, int> m : umpa)
        ans += (m.second * (m.second - 1)) >> 1;

    return ans;
}

vector<vector<string>> groupAnagrams(vector<string> &strs)
{
    unordered_map<string, vector<string>> map;
    for (string &str : strs)
    {
        int freq[26] = {0};
        for (char &ch : str)
            freq[ch - 'a']++;

        string RLES = "";
        for (int i = 0; i < 26; i++)
            if (freq[i])
                RLES += (char)(i + 'a') + to_string(freq[i]);

        map[RLES].push_back(str);
    }

    vector<vector<string>> ans;
    for (auto p : map)
        ans.push_back(p.second);

    return ans;
}

//Leetcode : 295 =============================================

class MedianFinder
{

    priority_queue<int> smallerPQ;                            // max PQ
    priority_queue<int, vector<int>, greater<int>> greaterPQ; // min PQ

public:
    MedianFinder()
    {
    }

    void addNum(int num)
    {
        if (smallerPQ.size() == 0 || smallerPQ.top() > num)
            smallerPQ.push(num);
        else
            greaterPQ.push(num);

        if (smallerPQ.size() > (greaterPQ.size() + 1))
        {
            greaterPQ.push(smallerPQ.top());
            smallerPQ.pop();
        }
        else if (smallerPQ.size() < greaterPQ.size())
        {
            smallerPQ.push(greaterPQ.top());
            greaterPQ.pop();
        }
    }

    double findMedian()
    {
        if (smallerPQ.size() == greaterPQ.size())
            return smallerPQ.size() == 0 ? 0 : (smallerPQ.top() + greaterPQ.top()) / 2.0;
        else
            return smallerPQ.top();
    }
};
