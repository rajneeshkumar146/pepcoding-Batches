#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

void input(vector<int> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        cin >> arr[i];
    }
}

void targetSum(vector<int> &arr, int data, int si, int ei)
{
    sort(arr.begin(), arr.end());
    while (ei>=0 && si < arr.size())
    {
        int sum = arr[si] + arr[ei];
        if (sum > data)
            ei--;
        else if (sum < data)
            si++;
        else
        {
            cout << arr[si] << ", " << arr[ei] << endl;
            si++;
            ei--;
        }
    }
}

int main()
{
    int n;
    cin >> n;
    vector<int> A(n, 0);
    input(A);

    int data;
    cin >> data;
    targetSum(A, data, 0, n - 1);
    return 0;
}