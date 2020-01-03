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

// lqpl = last queen place location.
// qpsf = queen placed so far.
int queenPermu(int nbox, int idx, int qpsf, int tnq, vector<bool> &vis, string ans)
{
    if (idx == nbox || qpsf == tnq)
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (!vis[idx])
    {
        vis[idx] = true;
        count += queenPermu(nbox, 0, qpsf + 1, tnq, vis, ans + "b" + to_string(idx) + "q" + to_string(qpsf));
        vis[idx] = false;
    }
    count += queenPermu(nbox, idx + 1, qpsf, tnq, vis, ans);
    return count;
}

int queenPermu2(int nbox, int qpsf, int tnq, vector<bool> &vis, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < nbox; i++)
        if (!vis[i])
        {
            vis[i] = true;
            count += queenPermu2(nbox, qpsf + 1, tnq, vis, ans + "b" + to_string(i) + "q" + to_string(qpsf));
            vis[i] = false;
        }
    return count;
}

int queenCombi(int nbox, int lqpl, int qpsf, int tnq, string ans)
{
    if (lqpl == nbox || qpsf == tnq)
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    count += queenCombi(nbox, lqpl + 1, qpsf + 1, tnq, ans + "b" + to_string(lqpl) + "q" + to_string(qpsf));
    count += queenCombi(nbox, lqpl + 1, qpsf, tnq, ans);

    return count;
}

void queen()
{
    // cout << queenCombi(5, 0, 0, 3, "") << endl;

    vector<bool> vis(5, false);
    // cout << queenPermu(5, 0, 0, 3, vis, "") << endl;
    cout << queenPermu2(5, 0, 3, vis, "") << endl;
}

int main()
{
    // coinChange();
    queen();
    return 0;
}