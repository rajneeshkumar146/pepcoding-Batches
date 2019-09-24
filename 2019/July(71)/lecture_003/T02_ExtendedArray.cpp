#include <iostream>
#include <vector>

using namespace std;

vector<int> allIndex(vector<int> &arr, int data)
{
    vector<int> ans;
    for (int i = 0; i < arr.size(); i++)
    {
        if (arr[i] == data)
            ans.push_back(i);
    }

    return ans;
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

    vector<int> ans = allIndex(arr, 9);
    for (int i : ans)
        cout << i << " ";

    // display(arr);
}

int main(int args, char **argv)
{
    solve();
    return 0;
}
