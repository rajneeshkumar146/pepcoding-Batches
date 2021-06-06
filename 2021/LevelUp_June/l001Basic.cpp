#include <iostream>
#include <vector>
using namespace std;

void printIncreasing(int a, int b)
{
    if (a > b)
        return;
    cout << a << endl;
    printIncreasing(a + 1, b);
}

void printDecreasing(int a, int b)
{

    if (a > b)
        return;
    printDecreasing(a + 1, b);
    cout << a << endl;
}

void printIncreasingDecreasing(int a, int b)
{
    if (a > b)
        return;

    cout << a << endl;
    printIncreasingDecreasing(a + 1, b);
    cout << a << endl;
}

void printOddEven(int a, int b)
{

    if (a > b)
        return;

    if (a % 2 != 0)
        cout << a << endl;
    printOddEven(a + 1, b);
    if (a % 2 == 0)
        cout << a << endl;
}

int fact(int n)
{
    // return n == 0 ? 1 : fact(n - 1) * n;

    if (n == 0)
        return 1;

    int smallAns = fact(n - 1);
    int myAns = smallAns * n;

    return myAns;
}

int power(int a, int b)
{
    return b == 0 ? 1 : power(a, b - 1) * a;
}

int powerBtr(int a, int b)
{
    if (b == 0)
        return 1;

    int smallAns = powerBtr(a, b / 2);
    smallAns *= smallAns;

    return b % 2 != 0 ? smallAns * a : smallAns;
}

void display(vector<int> &arr, int idx)
{
    if (idx == arr.size())
        return;

    cout << (arr[idx]) << endl;
    display(arr, idx + 1);
}

void displayReverse(vector<int> &arr, int idx)
{
    if (idx == arr.size())
        return;

    displayReverse(arr, idx + 1);
    cout << arr[idx] << endl;
}

int maximum(vector<int> &arr, int idx)
{
    if (idx == arr.size())
        return -1e9;
    int maxSF = maximum(arr, idx + 1);
    return max(maxSF, arr[idx]);
}

int minimum(vector<int> &arr, int idx)
{
    if (idx == arr.size())
        return 1e9;
    int minSF = minimum(arr, idx + 1);
    return min(minSF, arr[idx]);
}

bool find(vector<int> &arr, int idx, int data)
{
    if (idx == arr.size())
        return false;
    return arr[idx] == data || find(arr, idx + 1, data);
}

int firstIndex(vector<int> &arr, int idx, int data)
{
    if (idx == arr.size())
        return -1;

    if (arr[idx] == data)
        return idx;
    return firstIndex(arr, idx + 1, data);

    // return arr[idx] == data ? idx : firstIndex(arr,idx +1 ,data);
}

int lastIndex(vector<int> &arr, int idx, int data)
{
    if (idx == arr.size())
        return -1;
    int smallRes = lastIndex(arr, idx + 1, data);
    if (smallRes != -1)
        return smallRes;

    return arr[idx] == data ? idx : -1;
}

// n = 5.
int printTreePath(int n)
{
    if (n == 1 || n == 0)
    {
        cout << "base: " << n << endl;
        return n;
    }
    int ans = 0;

    cout << "pre: " << n << endl;
    ans += printTreePath(n - 1);

    cout << "In: " << n << endl;

    ans += printTreePath(n - 2);
    cout << "post: " << n << endl;

    return ans + 3;
}

vector<int> allIndex(vector<int> &arr, int idx, int data, int count)
{
    if (idx == arr.size())
    {
        vector<int> base(count, 0);
        return base;
    }

    if (arr[idx] == data)
        count++;
    vector<int> ans = allIndex(arr, idx + 1, data, count);
    if (arr[idx] == data)
        ans[count - 1] = idx;

    return ans;
}

vector<string> subSeq(string str, int idx)
{
    if (idx == str.length())
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> recAns = subSeq(str, idx + 1);
    vector<string> myAns(recAns);
    for (string s : recAns)
        myAns.push_back(str[idx] + s);

    return myAns;
}

vector<string> getKPC(string str)
{
}

int main()
{
    return 0;
}