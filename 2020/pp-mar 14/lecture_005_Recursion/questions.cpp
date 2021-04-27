#include <iostream>
#include <vector>

using namespace std;
//46
void permute(vector<int> &nums, int count, vector<vector<int>> &res, vector<int> &ans)
{
    if (count == nums.size())
    {
        res.push_back(ans);
        return;
    }

    for (int i = 0; i < nums.size(); i++)
    {
        if (nums[i] >= -10 && nums[i] <= 10)
        {
            int val = nums[i];

            nums[i] = -11;
            ans.push_back(val);
            permute(nums, count + 1, res, ans);
            nums[i] = val;
            ans.pop_back();
        }
    }
}

vector<vector<int>> permute(vector<int> &nums)
{
    vector<vector<int>> res;
    vector<int> ans;

    permute(nums, 0, res, ans);
    return res;
}