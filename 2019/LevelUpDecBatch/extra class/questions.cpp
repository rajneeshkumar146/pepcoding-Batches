#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

vector<int> twoSum(vector<int> &nums, int target)
{
    unordered_map<int, int> map;
    for (int i = 0; i < nums.size(); i++)
        map[nums[i]] = i;

    for (int i = 0; i < nums.size(); i++)
    {
        if (map.find(target - nums[i]) != map.end())
        {
            if (map[target - nums[i]] != i)
                return {map[target - nums[i]], i};
        }
    }

    return {};
}

vector<vector<int>> twoSum(vector<int> &nums, int target, int si, int ei)
{
    vector<vector<int>> res;
    while (si < ei)
    {
        int sum = nums[si] + nums[ei];
        if (sum == target)
        {
            res.push_back({nums[si++], nums[ei--]});
            while (si < ei && nums[si] == nums[si - 1])
                si++;
            while (si < ei && nums[ei] == nums[ei + 1])
                ei--;
        }
        else if (sum > target)
            ei--;
        else
            si++;
    }

    return res;
}

void makeMyAns(vector<vector<int>> &res, vector<vector<int>> &smallAns, int ele)
{
    for (vector<int> a : smallAns)
    {
        a.push_back(ele);
        res.push_back(a);
    }
}

vector<vector<int>> threeSum(vector<int> &nums, int target, int si, int ei)
{
    vector<vector<int>> res;
    for (int i = si; i < ei; i++)
    {
        while (i != si && i < ei && nums[i] == nums[i - 1])
            i++;

        vector<vector<int>> smallAns = twoSum(nums, target - nums[i], i + 1, ei);
        makeMyAns(res, smallAns, nums[i]);
    }

    return res;
}

vector<vector<int>> fourSum(vector<int> &nums, int target, int si, int ei)
{
    vector<vector<int>> res;
    for (int i = si; i < ei; i++)
    {
        while (i != si && i < ei && nums[i] == nums[i - 1])
            i++;

        vector<vector<int>> smallAns = threeSum(nums, target - nums[i], i + 1, ei);
        makeMyAns(res, smallAns, nums[i]);
    }

    return res;
}

vector<vector<int>> threeSum(vector<int> &nums)
{
    sort(nums.begin(), nums.end());
}

public
int canCompleteCircuit(int[] gas, int[] cost)
{
    int extraGas = 0;
    int sp = 0;
    int dificit = 0;

    for (int i = 0; i < gas.length; i++)
    {
        extraGas += gas[i] - cost[i];
        if (extraGas < 0)
        {
            dificit += extraGas;
            extraGas = 0;
            sp = i + 1;
        }
    }

    return (sp == gas.length || extraGas + dificit < 0) ? -1 : sp;
}

int findMin(vector<int> &nums)
{
    int si = 0, ei = nums.size() - 1;
    if (nums[si] < nums[ei])
        return nums[si];

    while (si < ei)
    {
        int mid = (si + ei) / 2;
        if (nums[mid] < nums[ei])
            ei = mid;
        else if (nums[si] <= nums[mid])
            si = mid + 1;
    }

    return nums[si];
}

int main()
{

    return 0;
}