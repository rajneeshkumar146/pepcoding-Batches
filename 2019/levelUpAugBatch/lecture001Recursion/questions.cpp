#include <iostream>
#include <vector>
#include <unordered_set>
#include <algorithm>

using namespace std;

//Leetcode 46
vector<vector<int>> res;
vector<int> smallAns;

void permute_(vector<int> &arr, int count, vector<bool> &vis)
{
    if (count == arr.size())
    {
        res.push_back(smallAns);
        return;
    }

    for (int i = 0; i < arr.size(); i++)
    {
        if (!vis[i])
        {
            vis[i] = true;
            smallAns.push_back(arr[i]);
            permute_(arr, count + 1, vis);
            smallAns.pop_back();
            vis[i] = false;
        }
    }
}

vector<vector<int>> permute(vector<int> &nums)
{
    vector<bool> vis(nums.size(), false);
    permute_(nums, 0, vis);
    return res;
}


//Leetcode 47
void permuteUnique(vector<int> &arr, int count, vector<bool> &vis)
{
    if (count == arr.size())
    {
        res.push_back(smallAns);
        return;
    }

    unordered_set<int> isUsed;
    for (int i = 0; i < arr.size(); i++)
    {
        if (!vis[i] && isUsed.find(arr[i])==isUsed.end())
        {

            isUsed.insert(arr[i]);
            
            vis[i] = true;
            smallAns.push_back(arr[i]);
            permuteUnique(arr, count + 1, vis);
            smallAns.pop_back();
            vis[i] = false;
        }
    }
}

void permuteUnique_02(vector<int> &arr, int count, vector<bool> &vis)
{
    if (count == arr.size())
    {
        res.push_back(smallAns);
        return;
    }

    int prev = -(int)1e8;
    for (int i = 0; i < arr.size(); i++)
    {
        if (!vis[i] && prev!=arr[i])
        {

            vis[i] = true;
            smallAns.push_back(arr[i]);
            permuteUnique_02(arr, count + 1, vis);
            smallAns.pop_back();
            vis[i] = false;

            prev = arr[i];
        }
    }
}

vector<vector<int>> permuteUnique(vector<int> &nums)
{
    sort(nums.begin(),nums.end());
    vector<bool> vis(nums.size(), false);
    // permuteUnique(nums, 0, vis);
     permuteUnique_02(nums, 0, vis);
    return res;
}
