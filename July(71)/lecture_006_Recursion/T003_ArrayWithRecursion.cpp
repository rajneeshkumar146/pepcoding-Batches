#include <iostream>
#include <vector>
#include <climits>
#include <algorithm>

using namespace std;

void display(vector<int> &arr, int vidx) //for Java. public static void display(int[] arr,int vidx)
{
    if (vidx == arr.size())
        return;

    cout << arr[vidx];
    display(arr, vidx + 1);
}

int maximum(vector<int> arr, int vidx)
{
    if (vidx == arr.size())
    {
        return INT_MIN; //  (int)-1e6;, (int)1e6
    }


    int faithMax = maximum(arr, vidx + 1);
    int actualMax = max(faithMax, arr[vidx]);
    return actualMax;
}


    int minimum(vector<int> arr, int vidx) {
        if (vidx == arr.length) {
            return INT_MAX; // (int)-1e6;, (int)1e6
        }

        int faithMin = minimum(arr, vidx + 1);
        int actualMin = min(faithMin, arr[vidx]);
        return actualMin;
    }

void solve()
{
    vector<int> arr = {10, 20, 30, 40, 50, 60, 700, 80, 90, 100};
}

int main(int args, char **argv)
{
    solve();
    return 0;
}