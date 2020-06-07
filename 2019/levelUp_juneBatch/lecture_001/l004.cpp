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
        count += combinationINFI_subseq(arr, idx, tar - arr[idx], ans + to_string(arr[idx]) + " ");
    count += combinationINFI_subseq(arr, idx + 1, tar, ans);
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
        count += permutationINFI_subseq(arr, 0, tar - arr[idx], ans + to_string(arr[idx]) + " ");
    count += permutationINFI_subseq(arr, idx + 1, tar, ans);
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

    if (arr[idx] >= 0 && tar - arr[idx] >= 0)
    {

        int temp = arr[idx];
        arr[idx] = -arr[idx];
        count += permutationSingleCoin_subseq(arr, 0, tar - temp, ans + to_string(temp) + " ");
        arr[idx] = -arr[idx];
    }

    count += permutationSingleCoin_subseq(arr, idx + 1, tar, ans);
    return count;
}

//========================================================================

int queenCombination_1D(vector<bool> &boxes, int tnq, int idx, int qpsf, string ans) // tnq is equal to target.
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < boxes.size(); i++)
    {
        count += queenCombination_1D(boxes, tnq, i + 1, qpsf + 1, ans + "b" + to_string(i) + "q" + to_string(qpsf) + " ");
    }
    return count;
}

int queenCombination_1D_subseq(vector<bool> &boxes, int tnq, int idx, int qpsf, string ans) // tnq is equal to target.
{
    if (qpsf == tnq || idx == boxes.size())
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    count += queenCombination_1D_subseq(boxes, tnq, idx + 1, qpsf + 1, ans + "b" + to_string(idx) + "q" + to_string(qpsf) + " ");
    count += queenCombination_1D_subseq(boxes, tnq, idx + 1, qpsf, ans);

    return count;
}

int queenPermutation_1D(vector<bool> &boxes, int tnq, int idx, int qpsf, string ans) // tnq is equal to target.
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < boxes.size(); i++)
    {
        if (!boxes[i])
        {
            boxes[i] = true;
            count += queenPermutation_1D(boxes, tnq, 0, qpsf + 1, ans + "b" + to_string(i) + "q" + to_string(qpsf) + " ");
            boxes[i] = false;
        }
    }
    return count;
}

int queenCombination_2D(vector<vector<bool>> &boxes, int tnq, int idx, int qpsf, string ans) // tnq is equal to target.
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();
        count += queenCombination_2D(boxes, tnq, i + 1, qpsf + 1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
    }
    return count;
}

int queenPermutation_2D(vector<vector<bool>> &boxes, int tnq, int idx, int qpsf, string ans) // tnq is equal to target.
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();
        if (!boxes[r][c])
        {
            boxes[r][c] = true;
            count += queenPermutation_2D(boxes, tnq, 0, qpsf + 1, ans + "b" + to_string(i) + "q" + to_string(qpsf) + " ");
            boxes[r][c] = false;
        }
    }
    return count;
}

void queenCombinationPermuation()
{
    // vector<bool> boxes(5, false);
    // int tnq = 3;
    // cout << queenCombination_1D(boxes, tnq, 0, 0, "") << endl;
    // cout << queenCombination_1D_subseq(boxes, tnq, 0, 0, "") << endl;
    // cout << queenPermutation_1D(boxes, tnq, 0, 0, "") << endl;

    vector<vector<bool>> boxes(4, vector<bool>(4, 0));
    int tnq = 4;
    // cout << queenCombination_2D(boxes, tnq, 0, 0, "") << endl;
     cout << queenPermutation_2D(boxes, tnq, 0, 0, "") << endl;
}

void coinChangeCombinationPermuation()
{
    vector<int> arr = {2, 3, 5, 7};
    int tar = 10;

    // vector<int> arr{1,1,1,1,1};
    // int tar=3;
    // sumation(nCr)==========================================================

    // cout << permutationINFI(arr, 0, tar, "") << endl;
    // cout << combinationINFI(arr, 0, tar, "") << endl;
    cout << combinationSingleCoin(arr, 0, tar, "") << endl;
    // cout << permutationSingleCoin(arr, 0, tar, "") << endl;

    //2^n========================================================================

    // cout << permutationINFI_subseq(arr, 0, tar, "") << endl;
    // cout << combinationINFI_subseq(arr, 0, tar, "") << endl;
    // cout << combinationSingleCoin_subseq(arr, 0, tar, "") << endl;
    // cout << permutationSingleCoin_subseq(arr, 0, tar, "") << endl;
}

void solve()
{
    // coinChangeCombinationPermuation();
    queenCombinationPermuation();
}

int main()
{
    solve();
    return 0;
}