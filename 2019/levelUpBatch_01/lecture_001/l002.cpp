#include <iostream>
#include <vector>
using namespace std;

// public static int  coinChgPerm_01(int[] arr,int tar,String ans)
int coinChgPerm_01(vector<int> &arr, int tar, string ans)
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
            count += coinChgPerm_01(arr, tar - arr[i], ans + to_string(arr[i]) + " ");
        }
    }

    return count;
}

int coinChgPerm_02(vector<int> &arr, int tar, string ans, vector<bool> &vis)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        if (!vis[i] && tar - arr[i] >= 0)
        {
            vis[i] = true;
            count += coinChgPerm_02(arr, tar - arr[i], ans + to_string(arr[i]) + " ", vis);
            vis[i] = false;
        }
    }

    return count;
}

int coinChgPerm_03(vector<int> &arr, int idx, int tar, string ans)
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
        count += coinChgPerm_03(arr, 0, tar - arr[idx], ans + to_string(arr[idx]) + " ");
    count += coinChgPerm_03(arr, idx + 1, tar, ans);

    return count;
}


void solve()
{
    vector<int> arr = {2, 3, 5, 7};
    int tar = 10;
    vector<bool> vis(4, false);
    // cout << coinChgPerm_01(arr, tar, "") << endl;
    // cout << coinChgPerm_02(arr, tar, "", vis) << endl;
    cout << coinChgPerm_03(arr, 0, tar, "") << endl;
}

int main()
{
    solve();
    return 0;
}
