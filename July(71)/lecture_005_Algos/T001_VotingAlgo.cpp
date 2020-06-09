#include <iostream>
using namespace std;

int potentialCandidate(vector<int> &arr)
{
    int ele = arr[0];
    int freq = 1;
    for (int i = 1; i < arr.size(); i++)
    {
        if (arr[i] == ele)
            freq++;
        else
            freq--;

        if (freq == 0)
        {
            ele = arr[i];
            freq = 1;
        }
    }

    return ele;
}

bool validation(vector<int> &arr, int ele)
{
    int freq = 0;
    for (int val : arr)
    {
        if (ele == val)
            freq++;
    }

    return (freq > arr.size() / 2) ? true : false;
}

void VotingAlgo()
{

    int candidate = potentialCandidate(arr);
    bool isValid = validation(arr, candidate);

    if (isValid)
    {
        cout << candidate << endl;
    }
    else
    {
        cout << -1 << endl;
    }
}

solve()
{
    int n;
    cin >> n;
    vector<int> arr(n, 0);
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }
}

int main(int args, char **argv)
{
    solve();
    return 0;
}