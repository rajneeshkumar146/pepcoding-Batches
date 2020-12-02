#include <iostream>
#include <vector>

using namespace std;

void seperatePositiveNegative(vector<int> &arr)
{
    if (arr.size() == 0)
        return;

    int piviot = -1, idx = 0, n = arr.size();
    while (idx < n)
    {
        if (arr[idx] >= 0)
            swap(arr[++piviot], arr[idx]);
        idx++;
    }
}

void segregateZeroOnes(vector<int> &arr)
{
    if (arr.size() == 0)
        return;

    int piviot = -1, idx = 0, n = arr.size();
    while (idx < n)
    
    {
        if (arr[idx] == 0)
            swap(arr[++piviot], arr[idx]);
        idx++;
    }
}


//Leetcode 75
void segregateZeroOnesAndTwo(vector<int> &arr)
{
    if (arr.size() == 0)
        return;

    int p1 = -1, idx = 0, n = arr.size(), p2 = n - 1;
    while (idx <= p2)
    {
        if (arr[idx] == 0)
            swap(arr[++p1], arr[idx++]);
        else if (arr[idx] == 2)
            swap(arr[idx], arr[p2--]);
        else idx++;
    }
}

int main()
{
}