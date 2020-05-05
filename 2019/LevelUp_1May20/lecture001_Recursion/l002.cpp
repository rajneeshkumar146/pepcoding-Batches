#include <iostream>
#include <vector>
using namespace std;

int coinChangePermutation_INF(vector<int> &arr, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int ele : arr)
        if (tar - ele >= 0)
            count += coinChangePermutation_INF(arr, tar - ele, ans + to_string(ele) + " ");

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
    {   int temp = arr[i];
        if (arr[i] > 0 && tar - arr[i] >= 0 )
        {   arr[i] = -arr[i];
            count += coinChangePermutation(arr, tar - temp, ans + to_string(temp) + " ");
            arr[i] = -arr[i];
        }
    }
    return count;
}

int coinChangeCombination_INF(vector<int> &arr, int lidx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lidx; i < arr.size(); i++)
        if (tar - arr[i] >= 0)
            count += coinChangeCombination_INF(arr, i, tar - arr[i], ans + to_string(arr[i]) + " ");

    return count;
}

int coinChangeCombination(vector<int> &arr, int lidx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lidx; i < arr.size(); i++)
        if (tar - arr[i] >= 0)
            count += coinChangeCombination(arr, i + 1, tar - arr[i], ans + to_string(arr[i]) + " ");

    return count;
}

void coinChange()
{
    vector<int> arr{2, 3, 5, 7};
    int tar = 10;
    // cout << coinChangePermutation_INF(arr, tar, "") << endl;
    cout << coinChangePermutation(arr, tar, "") << endl;

    // cout << coinChangeCombination_INF(arr,0, tar, "") << endl;
    // cout << coinChangeCombination(arr,0, tar, "") << endl;
}

int main()
{
    coinChange();
    return 0;
}
