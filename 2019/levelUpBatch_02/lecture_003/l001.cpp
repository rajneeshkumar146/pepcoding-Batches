#include <iostream>
#include <vector>
using namespace std;

int coinPermInfinite(vector<int> &arr, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
        {
            count += coinPermInfinite(arr, tar - arr[i], ans + to_string(arr[i]));
        }
    }
    return count;
}

int coinPermWithoutRepe(vector<int> &arr, vector<bool> &vis, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0 && !vis[i])
        {
            vis[i] = true;
            count += coinPermWithoutRepe(arr, vis, tar - arr[i], ans + to_string(arr[i]));
            vis[i] = false;
        }
    }
    return count;
}

int coinCombiInfinite(vector<int> &arr, int idx, int tar, string ans)
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
        {
            count += coinCombiInfinite(arr, i, tar - arr[i], ans + to_string(arr[i]));
        }
    }
    return count;
}

int coinCombiWithouRepe(vector<int> &arr, int idx, int tar, string ans)
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
        {
            count += coinCombiWithouRepe(arr, i + 1, tar - arr[i], ans + to_string(arr[i]));
        }
    }
    return count;
}

void coinChange()
{
    vector<int> arr = {2, 3, 5, 7};
    vector<bool> vis(arr.size(), false);
    int tar = 10;
    // cout << coinPermInfinite(arr, tar, "") << endl;
    cout << coinPermWithoutRepe(arr, vis, tar, "") << endl;

    // cout << coinCombiInfinite(arr, 0, tar, "") << endl;
    // cout << coinCombiWithouRepe(arr, 0, tar, "") << endl;
}

int main()
{
    coinChange();
    return 0;
}