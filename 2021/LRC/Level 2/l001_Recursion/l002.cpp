#include <iostream>
#include <vector>
using namespace std;

int cc_infiPermutation(vector<int> &arr, int tar, string psf)
{
    if (tar == 0)
    {
        cout << psf << endl;
        return 1;
    }

    int count = 0;
    for (int ele : arr)
        if (tar - ele >= 0)
            count += cc_infiPermutation(arr, tar - ele, psf + to_string(ele));

    return count;
}

int cc_infiCombination(vector<int> &arr, int idx, int tar, string psf)
{
    if (tar == 0)
    {
        cout << psf << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
        {
            count += cc_infiCombination(arr, i, tar - arr[i], psf + to_string(arr[i]));
        }
    }

    return count;
}

int cc_singlePermutation(vector<int> &arr, int tar, string psf)
{
    if (tar == 0)
    {
        cout << psf << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        int ele = arr[i];
        if (arr[i] > 0 && tar - ele >= 0)
        {
            arr[i] = -arr[i];
            count += cc_singlePermutation(arr, tar - ele, psf + to_string(ele));
            arr[i] = -arr[i];
        }
    }
    return count;
}

int cc_singleCombination(vector<int> &arr, int idx, int tar, string psf)
{
    if (tar == 0)
    {
        cout << psf << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
        {
            count += cc_singleCombination(arr, i + 1, tar - arr[i], psf + to_string(arr[i]));
        }
    }

    return count;
}

void simpleMethod()
{
    vector<int> arr = {2, 3, 5, 7};
    int tar = 10;
    string psf = "";

    // cout << cc_infiPermutation(arr, tar, psf) << endl;
    // cout << cc_infiCombination(arr, 0, tar, psf) << endl;
    // cout << cc_singleCombination(arr, 0, tar, psf) << endl;
    cout << cc_singlePermutation(arr, tar, psf) << endl;
}

// subsequence Method.==============================================

int cc_infiPermutation_sub(vector<int> &arr, int idx, int tar, string psf)
{
    if (tar == 0 || idx == arr.size())
    {
        if (tar == 0)
        {
            cout << psf << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (tar - arr[idx] >= 0)
        count += cc_infiPermutation_sub(arr, 0, tar - arr[idx], psf + to_string(arr[idx]));
    count += cc_infiPermutation_sub(arr, idx + 1, tar, psf);

    return count;
}

int cc_infiCombination_sub(vector<int> &arr, int idx, int tar, string psf)
{

    if (tar == 0 || idx == arr.size())
    {
        if (tar == 0)
        {
            cout << psf << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (tar - arr[idx] >= 0)
        count += cc_infiCombination_sub(arr, idx, tar - arr[idx], psf + to_string(arr[idx]));
    count += cc_infiCombination_sub(arr, idx + 1, tar, psf);

    return count;
}

int cc_singlePermutation_sub(vector<int> &arr, int idx, int tar, string psf)
{

    if (tar == 0 || idx == arr.size())
    {
        if (tar == 0)
        {
            cout << psf << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;

    int ele = arr[idx];
    if (arr[idx] > 0 && tar - ele >= 0)
    {
        arr[idx] = -arr[idx];
        count += cc_singlePermutation_sub(arr, 0, tar - ele, psf + to_string(ele));
        arr[idx] = -arr[idx];
    }
    count += cc_singlePermutation_sub(arr, idx + 1, tar, psf);

    return count;
}

int cc_singleCombination_sub(vector<int> &arr, int idx, int tar, string psf)
{

    if (tar == 0 || idx == arr.size())
    {
        if (tar == 0)
        {
            cout << psf << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (tar - arr[idx] >= 0)
        count += cc_singleCombination_sub(arr, idx + 1, tar - arr[idx], psf + to_string(arr[idx]));
    count += cc_singleCombination_sub(arr, idx + 1, tar, psf);

    return count;
}

void subseqMethod()
{
    vector<int> arr = {2, 3, 5, 7};
    int tar = 10;
    string psf = "";

    // cout << cc_infiPermutation(arr, tar, psf) << endl;
    // cout << cc_infiCombination(arr, 0, tar, psf) << endl;
    cout << cc_singlePermutation_sub(arr, 0, tar, psf) << endl;
}

int main()
{
    subseqMethod();
    return 0;
}