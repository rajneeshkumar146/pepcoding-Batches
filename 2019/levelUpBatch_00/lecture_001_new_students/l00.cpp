#include <iostream>
#include <vector>
using namespace std;

int fact(int n)
{
    // return n == 0 ? 1 : fact(n - 1) * n;
    if (n == 0)
        return 1;

    int recAns = fact(n - 1);
    return recAns * n;
}

int power(int a, int b)
{
    if (a == 0)
        return 0;
    if (b == 0)
        return 1;

    int recAns = power(a, b - 1);
    return recAns * a;
}

void printIncr(int a, int b)
{
    if (a == b + 1)
        return;

    if (a % 2 == 0)
        cout << "even: " << a << endl;

    printIncr(a + 1, b);

    if (a % 2 != 0)
        cout << "odd: " << a << endl;
}

void display(vector<int> &arr, int vidx)
{
    if (vidx == arr.size())
        return;
    cout << arr[vidx] << " ";

    display(arr, vidx + 1);
}

bool findData_01(vector<int> &arr, int vidx, int data)
{
    if (vidx == arr.size())
        return false;

    if (arr[vidx] == data)
        return true;

    return findData_01(arr, vidx + 1, data);
}

bool findData_02(vector<int> &arr, int vidx, int data)
{
    if (vidx == arr.size())
        return false;

    bool recAns = findData_02(arr, vidx + 1, data);
    if (recAns)
        return true;

    return arr[vidx] == data;
}

int firstIndex(vector<int> &arr, int vidx, int data)
{
    if (vidx == arr.size())
        return -1;

    if (arr[vidx] == data)
        return vidx;
    return firstIndex(arr, vidx + 1, data);
}

int lastIndex(vector<int> &arr, int vidx, int data)
{
    if (vidx == arr.size())
        return -1;

    int ans = lastIndex(arr, vidx + 1, data);
    if (ans != -1)
        return ans;
        
    return arr[vidx] == data ? vidx : -1;
}

void solve()
{
    // printIncr(0, 10);
    vector<int> arr{10, 20, 30, 40, 50, 60, 70, 80, 90, 100}; // int[] arr={};
    display(arr, 0);
}

int main()
{
    solve();
    return 0;
}