#include <iostream>
#include <vector>
using namespace std;

//leetcode: 91.=========================================================

int numDecodings(string &s, int idx)
{
    if (idx == s.length())
    {
        return 1;
    }

    char ch = s[idx];
    int count = 0;
    if (ch != '0')
    {
        // char ch_ = (char)((ch - '1') + 'a');
        count += numDecodings(s, idx + 1);
    }

    if (idx + 1 < s.length())
    {
        int num = (ch - '0') * 10 + (s[idx + 1] - '0');
        if (num >= 10 && num <= 26)
        {
            // char ch_ = (char)('a' + num - 1);
            count += numDecodings(s, idx + 2);
        }
    }

    return count;
}

int numDecodings(string s)
{
    if (s.length() == 0)
        return 0;

    return numDecodings(s, 0);
}

//leetcode 40.==========================================
void combinationSum2_(vector<int> &arr, int idx, int target, vector<vector<int>> &res, vector<int> &ans)
{
    if (target == 0)
    {
        vector<int> base;
        for (int ele : ans)
            base.push_back(ele);
        res.push_back(base);
        return;
    }

    int prev = -1;
    for (int i = idx; i < arr.size(); i++)
    {
        if (prev == arr[i])
            continue;

        prev = arr[i];
        if (target - arr[i] >= 0)
        {
            ans.push_back(arr[i]);
            combinationSum2_(arr, i + 1, target - arr[i], res, ans);
            ans.pop_back();
        }
    }
}

vector<vector<int>> combinationSum2(vector<int> &arr, int target)
{
    sort(arr.begin(), arr.end()); // Arrays.sort(arr); <- Java
    vector<vector<int>> res;
    vector<int> ans;
    combinationSum2_(arr, 0, target, res, ans);

    return res;
}
