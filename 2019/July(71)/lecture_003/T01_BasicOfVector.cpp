#include <iostream>
#include <vector>

using namespace std;

void inverse(vector<int> &arr)
{
    vector<int> ans(arr.size(), 0);
    for (int i = arr.size() - 1; i >= 0; i--)
    {
        ans[arr[i]] = i;
    }

    arr = ans;
}

void reverse(vector<int> &arr)
{
    int i = 0;
    int j = arr.size() - 1;
    while (i < j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        i++;
        j--;
    }
}

int lastIndex(vector<int> &arr, int data)
{
    for (int i = arr.size() - 1; i >= 0; i--)
    {
        if (arr[i] == data)
            return i;
    }
    return -1;
}

int firstIndex(vector<int> &arr, int data)
{
    for (int i = 0; i < arr.size(); i++)
    {
        if (arr[i] == data)
            return i;
    }
    return -1;
}

int find(vector<int> &arr, int data)
{
    for (int i = 0; i < arr.size(); i++)
    {
        if (arr[i] == data)
            return i;
    }
    return -1;
}

int maximum(vector<int> &arr)
{
    int max_ = arr[0];
    for (int i = 1; i < arr.size(); i++)
    {
        if (arr[i] > max_)
            max_ = arr[i];
    }
    return max_;
}

void display(vector<int> &arr)
{
    for (int i : arr)
        cout << i << " ";

    cout << endl;
}

void inputArray(vector<int> &arr)
{
    for (int i = 0; i < arr.size(); i++)
        cin >> arr[i];
}

void solve()
{
    int n;
    cin >> n;
    vector<int> arr(n, 0);

    inputArray(arr);

    // int data;
    // cin >> data;
    // find(arr, data);
    inverse(arr);

    display(arr);
}

int main(int args, char **argv)
{
    solve();
    return 0;
}
