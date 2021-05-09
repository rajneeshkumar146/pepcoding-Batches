#include <iostream>
#include <vector>

using namespace std;

void reverse(vector<int> &arr, int i, int j)
{
    while (i < j)
    {
        swap(arr[i++], arr[j--]);
    }
}

void rotateByK(vector<int> &arr, int r)
{
    int n = arr.size();
    // r %= n;
    // if (r < 0)
    // r += n;

    r = (r % n + n) % n;

    reverse(arr, 0, n - 1);
    reverse(arr, n - r, n - 1);
    reverse(arr, 0, n - r - 1);
}

void segregatePositiveAndNegative(vector<int> &arr)
{
    int n = arr.size(), pt = -1, itr = 0;
    while (itr < n)
    {
        if (arr[itr] < 0)
            swap(arr[++pt], arr[itr]);
        itr++;
    }
}

void segregateZeroAndOne(vector<int> &arr)
{
    int n = arr.size(), pt = -1, itr = 0;
    while (itr < n)
    {
        if (arr[itr] == 0)
            swap(arr[++pt], arr[itr]);
        itr++;
    }
}

void segregateZeroOneAndTwo(vector<int> &arr)
{
    int n = arr.size(), pt1 = -1, pt2 = n - 1, itr = 0;
    while (itr <= pt2)
    {
        if (arr[itr] == 0)
            swap(arr[++pt1], arr[itr++]);
        else if (arr[itr] == 2)
            swap(arr[pt2--], arr[itr]);
        else
            itr++;
    }
}

// https://practice.geeksforgeeks.org/problems/max-sum-in-the-configuration/1
int max_sum(vector<int> &arr, int n)
{
    int sum = 0, sumWithIndex = 0;
    for (int i = 0; i < n; i++)
    {
        sum += arr[i];
        sumWithIndex += arr[i] * i;
    }

    int maxAns = sumWithIndex;
    for (int i = 1; i < n; i++)
    {
        sumWithIndex = sumWithIndex - sum + arr[i - 1] * n;
        maxAns = max(maxAns, sumWithIndex);
    }

    return maxAns;
}
