#include <iostream>
#include <vector>
using namespace std;

void printIncr(int a, int b)
{
    if (a == b + 1)
        return;

    cout << a << " ";
    printIncr(a + 1, b);
}

void printDec(int a, int b)
{
    if (a == b + 1)
        return;

    printDec(a + 1, b);
    cout << a << " ";
}

void printIncreDec(int a, int b)
{
    if (a == b + 1)
        return;

    if (a % 2 == 0)
        cout << a << " hi " << endl;
    printIncreDec(a + 1, b);
    if (a % 2 != 0)
        cout << a << " bye " << endl;
}

//recursionWithArray.==========================================

void recursionWithArray()
{
    // int n;
    // cin >> n;
    // vector<int> arr(n, 0);
    // for (int i = 0; i < n; i++)
    //     cin >> arr[i];

    vector<int> arr{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
}

int maximum(vector<int> &arr, int idx)
{
    if (idx == arr.size() - 1)
        return arr[idx];

    return max(arr[idx], maximum(arr, idx + 1));
}

int Minimum(vector<int> &arr, int idx)
{
    if (idx == arr.size() - 1)
        return arr[idx];

    return min(arr[idx], Minimum(arr, idx + 1));
}

bool find(vector<int> &arr, int idx, int data)
{
    if (idx == arr.size())
        return false;
    if (arr[idx] == data)
        return true;
    return find(arr, idx + 1, data);
}

void basics()
{
    int a, b;
    cin >> a >> b;

    // printIncr(a, b);
    // printDec(a, b);
    printIncreDec(a, b);
}

void solve()
{
    basics();
}

int main()
{
    solve();
    return 0;
}