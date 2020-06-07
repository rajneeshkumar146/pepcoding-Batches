#include <iostream>
#include <vector>
using namespace std;

int permutationINFI(vector<int> &arr, int idx, int tar, string ans)
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
            count += permutationINFI(arr, 0, tar - arr[i], ans + to_string(arr[i]) + " ");
    }
    return count;
}

int combinationINFI(vector<int> &arr, int idx, int tar, string ans)
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
            count += combinationINFI(arr, i, tar - arr[i], ans + to_string(arr[i]) + " ");
    }
    return count;
}

int combinationSingleCoin(vector<int> &arr, int idx, int tar, string ans)
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
            count += combinationSingleCoin(arr, i + 1, tar - arr[i], ans + to_string(arr[i]) + " ");
    }
    return count;
}

int permutationSingleCoin(vector<int> &arr, int idx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < arr.size(); i++)
    {
        if (arr[i] >= 0 && tar - arr[i] >= 0)
        {
            int temp = arr[i];
            arr[i] = -arr[i];
            count += permutationSingleCoin(arr, 0, tar - temp, ans + to_string(temp) + " ");
            arr[i] = -arr[i];
        }
    }
    return count;
}

//============================================================================

int combinationSingleCoin_subseq(vector<int> &arr, int idx, int tar, string ans)
{
    if (tar == 0 || idx == arr.size())
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
        count += combinationSingleCoin_subseq(arr, idx + 1, tar - arr[idx], ans + to_string(arr[idx]) + " ");
    count += combinationSingleCoin_subseq(arr, idx + 1, tar, ans);
    return count;
}

int combinationINFI_subseq(vector<int> &arr, int idx, int tar, string ans)
{
    if (tar == 0 || idx == arr.size())
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
        count += combinationINFI_subseq(arr, ?, tar - arr[idx], ans + to_string(arr[idx]) + " ");
    count += combinationINFI_subseq(arr, ?, tar, ans);
    return count;
}

int permutationINFI_subseq(vector<int> &arr, int idx, int tar, string ans)
{
    if (tar == 0 || idx == arr.size())
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
        count += permutationINFI_subseq(arr, ?, tar - arr[idx], ans + to_string(arr[idx]) + " ");
    count += permutationINFI_subseq(arr, ?, tar, ans);
    return count;
}

int permutationSingleCoin_subseq(vector<int> &arr, int idx, int tar, string ans)
{
    if (tar == 0 || idx == arr.size())
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
        count += permutationSingleCoin_subseq(arr, ?, tar - arr[idx], ans + to_string(arr[idx]) + " ");
    count += permutationSingleCoin_subseq(arr, ?, tar, ans);
    return count;
}


void solve()
{
    vector<int> arr = {2, 3, 5, 7};
    int tar = 10;
    // cout << permutationINFI(arr, 0, tar, "") << endl;
    // cout << combinationINFI(arr, 0, tar, "") << endl;
    // cout << combinationSingleCoin(arr, 0, tar, "") << endl;
    // cout << permutationSingleCoin(arr, 0, tar, "") << endl;

    cout << combinationSingleCoin_subseq(arr, 0, tar, "") << endl;
}

int main()
{
    solve();
    return 0;
}