#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

void targetSum(vector<int> &arr, int target)
{
    sort(arr.begin(), arr.end());

    int left = 0;
    int right = arr.size() - 1;
    bool flag = false;
    while (left < right)
    {
        int sum = arr[left] + arr[right];
        if (sum == target)
        {
            cout << "(" + to_string(left) + ", " + to_string(right) + "), " << endl;
            left++;
            right--;
            flag = true;
        }
        else if (sum < target)
        {
            left++;
        }
        else
        {
            right--;
        }
    }

    if (flag == false)
        cout << "No such pair exist: " << -1;
}

void equilIndex(vector<int> &arr)
{
    int Sum = 0;
    for (int i : arr)
    {
        Sum += i;
    }
    Sum -= arr[0];
    int leftSum = arr[0];
    for (int i = 1; i < arr.size() - 1; i++)
    {
        Sum -= arr[i];
        if (leftSum == Sum)
        {
            cout << "Equilibrium Index: " << i << endl;
        }
        leftSum += arr[i];
    }
}

void maximumWaterContainer(vector<int> &arr)
{
    int maxAns = 0;
    int left = 0;
    int right = arr.size() - 1;
    while (left < right)
    {
        int width = right - left;
        int height = arr[left];
        if (arr[left] < arr[right])
        {
            height = arr[left];
            left++;
        }
        else
        {
            height = arr[right];
            right--;
        }
        int potentialAns = height * width;

        maxAns = max(maxAns, potentialAns);
    }

    cout << maxAns << endl;
}

void solve()
{
    vector<int> arr = {-7, 5, 1, 2, -4, 3, 0, 0, 0};
    equilIndex(arr);
}

int main(int args, char **argv)
{
    solve();
    return 0;
}