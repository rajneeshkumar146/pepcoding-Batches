#include <iostream>
#include <vector>
using namespace std;

int coinChangePermutation_INF(vector<int> &arr, int lidx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lidx; i < arr.size(); i++)
        if (tar - arr[lidx] >= 0)
            count += coinChangePermutation_INF(arr, 0, tar - arr[lidx], ans + to_string(arr[i]) + " ");

    return count;
}

int coinChangePermutation(vector<int> &arr, int lidx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lidx; i < arr.size(); i++)
    {
        int temp = arr[i];
        if (arr[i] > 0 && tar - arr[i] >= 0)
        {
            arr[i] = -arr[i];
            count += coinChangePermutation(arr, 0, tar - temp, ans + to_string(temp) + " ");
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

//Queen's===========================================================

int queensCombination(vector<bool> &rooms, int room, int qpsf, int tnq, string ans) // qpsf: queen place so far.
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int r = room; r < rooms.size(); r++)
        count += queensCombination(rooms, r + 1, qpsf + 1, tnq, ans + "Q" + to_string(qpsf) + "R" + to_string(r) + " ");

    return count;
}

int queensPermutation(vector<bool> &rooms, int room, int qpsf, int tnq, string ans) // qpsf: queen place so far.
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int r = room; r < rooms.size(); r++)
        if (!rooms[r])
        {
            rooms[r] = true;
            count += queensPermutation(rooms, 0, qpsf + 1, tnq, ans + "Q" + to_string(qpsf) + "R" + to_string(r) + " ");
            rooms[r] = false;
        }

    return count;
}

void queenProblem()
{
    vector<bool> rooms(16, false);
    int tnq = 4;
    cout << queensCombination(rooms, 0, 0, tnq, "") << endl;
    // cout << queensPermutation(rooms, 0, 0, tnq, "") << endl;
}

void coinChange()
{
    vector<int> arr{2, 3, 5, 7};
    // vector<int> arr{1,1,1,1,1};
    int tar = 10;
    // cout << coinChangePermutation_INF(arr,0, tar, "") << endl;
    // cout << coinChangePermutation(arr, 0, tar, "") << endl;

    // cout << coinChangeCombination_INF(arr,0, tar, "") << endl;
    cout << coinChangeCombination(arr, 0, tar, "") << endl;
}

int main()
{
    coinChange();
    // queenProblem();
    return 0;
}
