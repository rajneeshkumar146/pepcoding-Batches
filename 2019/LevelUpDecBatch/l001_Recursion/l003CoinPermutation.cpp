#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int coinChangePermutationInfi(vector<int> &arr, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int ele : arr)
    {
        if (tar - ele >= 0)
            count += coinChangePermutationInfi(arr, tar - ele, ans + to_string(ele));
    }

    return count;
}

int coinChangeCombinationInfi(vector<int> &arr, int idx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
            count += coinChangeCombinationInfi(arr, i, tar - arr[i], ans + to_string(arr[i]));
    }

    return count;
}

int coinChangeCombination(vector<int> &arr, int idx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
            count += coinChangeCombination(arr, i + 1, tar - arr[i], ans + to_string(arr[i]));
    }

    return count;
}

int coinChangePermutation(vector<int> &arr, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        int ele = arr[i];
        if (arr[i] > 0 && tar - ele >= 0)
        {
            arr[i] = -arr[i];
            count += coinChangePermutation(arr, tar - ele, ans + to_string(ele));
            arr[i] = -arr[i];
        }
    }

    return count;
}

int coinChangeCombinationInfiSubSeq(vector<int> &arr, int idx, int tar, string ans)
{
    if (idx == arr.size() || tar == 0)
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (tar - arr[idx] >= 0)
        count += coinChangeCombinationInfiSubSeq(arr, idx, tar - arr[idx], ans + to_string(arr[idx]));
    count += coinChangeCombinationInfiSubSeq(arr, idx + 1, tar, ans);

    return count;
}

int coinChangeCombinationSubSeq(vector<int> &arr, int idx, int tar, string ans)
{
    if (idx == arr.size() || tar == 0)
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (tar - arr[idx] >= 0)
        count += coinChangeCombinationSubSeq(arr, idx + 1, tar - arr[idx], ans + to_string(arr[idx]));
    count += coinChangeCombinationSubSeq(arr, idx + 1, tar, ans);

    return count;
}

int coinChangePermutationInfiSubSeq(vector<int> &arr, int idx, int tar, string ans)
{

    if (idx == arr.size() || tar == 0)
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (tar - arr[idx] >= 0)
        count += coinChangePermutationInfiSubSeq(arr, 0, tar - arr[idx], ans + to_string(arr[idx]));
    count += coinChangePermutationInfiSubSeq(arr, idx + 1, tar, ans);

    return count;
}

int coinChangePermutationSubSeq(vector<int> &arr, int idx, int tar, string ans)
{

    if (idx == arr.size() || tar == 0)
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    int ele = arr[idx];
    if (tar - ele >= 0 && arr[idx] > 0)
    {
        arr[idx] = -arr[idx];
        count += coinChangePermutationSubSeq(arr, 0, tar - ele, ans + to_string(ele));
        arr[idx] = -arr[idx];
    }
    count += coinChangePermutationSubSeq(arr, idx + 1, tar, ans);

    return count;
}

//  Questions.====================================================================

vector<int> smallAns;
vector<vector<int>> res;

void combinationSum(vector<int> &arr, int idx, int target)
{
    if (idx == arr.size() || target == 0)
    {
        if (target == 0)
        {
            res.push_back(smallAns);
        }
        return;
    }

    if (target - arr[idx] >= 0)
    {
        smallAns.push_back(arr[idx]);
        combinationSum(arr, idx, target - arr[idx]);
        smallAns.pop_back();
    }
    combinationSum(arr, idx + 1, target);
}

vector<vector<int>> combinationSum(vector<int> &arr, int target)
{
    combinationSum(arr, 0, target);
    return res;
}

void combinationSum2(vector<int> &arr, int idx, int target)
{
    if (target == 0)
    {
        res.push_back(smallAns);
        return;
    }

    int prev = -1;
    for (int i = idx; i < arr.size(); i++)
    {
        if (prev != -1 && arr[prev] == arr[i])
            continue;

        if (target - arr[i] >= 0)
        {
            smallAns.push_back(arr[i]);
            combinationSum2(arr, i + 1, target - arr[i]);
            smallAns.pop_back();
        }

        prev = i;
    }
}

vector<vector<int>> combinationSum2(vector<int> &arr, int target)
{
    sort(arr.begin(), arr.end());
    combinationSum2(arr, 0, target);
    return res;
}

// leetcode 46
void permute(vector<int> &nums, int count, vector<bool> &vis)
{
    if (count == nums.size())
    {
        res.push_back(smallAns);
        return;
    }
    for (int i = 0; i < nums.size(); i++)
    {
        if (!vis[i])
        {
            vis[i] = true;
            smallAns.push_back(nums[i]);
            permute(nums, count + 1, vis);
            smallAns.pop_back();
            vis[i] = false;
        }
    }
}

// leetcode 47
void permuteUnique(vector<int> &nums, int count, vector<bool> &vis)
{
    if (count == nums.size())
    {
        res.push_back(smallAns);
        return;
    }

    vector<int> visLev(21, 0);
    for (int i = 0; i < nums.size(); i++)
    {
        if (!vis[i] && !visLev[nums[i] + 10])
        {
            vis[i] = true;
            visLev[nums[i] + 10] = true;

            smallAns.push_back(nums[i]);
            permuteUnique(nums, count + 1, vis);
            smallAns.pop_back();
            vis[i] = false;
        }
    }
}

// leetcode 47
void permuteUnique(vector<int> &nums, int count, vector<bool> &vis)
{
    if (count == nums.size())
    {
        res.push_back(smallAns);
        return;
    }

    int prev = -1;
    for (int i = 0; i < nums.size(); i++)
    {
        if (prev != -1 && nums[prev] == nums[i])
            continue;

        if (!vis[i])
        {
            vis[i] = true;

            smallAns.push_back(nums[i]);
            permuteUnique(nums, count + 1, vis);
            smallAns.pop_back();
            vis[i] = false;

            prev = i;
        }
    }
}
vector<vector<int>> permuteUnique(vector<int> &nums)
{
    sort(nums.begin(), nums.end());
    vector<bool> vis(nums.size(), false);
    permuteUnique(nums, 0, vis);
    return res;
}

int nQueen01(vector<vector<bool>> &boxes, int idx, int tnq, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    int n = boxes.size();
    int m = boxes[0].size();
    for (int i = idx; i < n * m; i++)
    {
        int r = i / m;
        int c = i % m;
        if (isSafeToPlaceQueen(boxes, r, c))
        {
            boxes[r][c] = true;
            count += nQueen01(boxes, i + 1, tnq - 1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
            boxes[r][c] = false;
        }
    }
    return count;
}

vector<bool> rowA;
vector<bool> colA;
vector<bool> diagA;
vector<bool> aDiagA;

void toggleNQueen(int r, int c, int n)
{
    rowA[r] = !rowA[r];
    colA[c] = !colA[c];
    diagA[r - c + n - 1] = !diagA[r - c + n - 1];
    aDiagA[r + c] = !aDiagA[r + c];
}

int nQueen02(int n, int idx, int tnq, string ans)
{
    if (tnq == 0)
    {
        cout << (ans) << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < n * n; i++)
    {
        int r = i / n;
        int c = i % n;
        if (!rowA[r] && !colA[c] && !diagA[r - c + n - 1] && !aDiagA[r + c])
        {
            toggleNQueen(r, c, n);
            count += nQueen02(n, i + 1, tnq - 1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
            toggleNQueen(r, c, n);
        }
    }
    return count;
}

int main()
{
    vector<int> arr{2, 3, 5, 7};
    int tar = 10;
    // cout << coinChangePermutationInfi(arr, tar, "");
    // cout << coinChangeCombinationInfi(arr, 0,tar, "");
    // cout << coinChangeCombination(arr, 0, tar, "")<<endl;
    // cout << coinChangePermutation(arr,tar, "");

    // cout << coinChangePermutationInfiSubSeq(arr, 0, tar, "") << endl;
    // cout << coinChangeCombinationInfiSubSeq(arr, 0, tar, "") << endl;
    // cout << coinChangeCombinationSubSeq(arr, 0, tar, "") << endl;
    cout << coinChangePermutationSubSeq(arr, 0, tar, "") << endl;
    rowA.resize(n,false);


    return 0;
}