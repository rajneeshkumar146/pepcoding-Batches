#include <iostream>
#include <vector>
using namespace std;

void display(vector<int> &arr, int vidx)
{
    if (vidx == arr.size())
        return;

    cout << arr[vidx] << " ";
    display(arr, vidx + 1);
}

bool find(vector<int> &arr, int vidx, int data)
{
    if (vidx == arr.size())
        return false;

    if (arr[vidx] == data)
        return true;

    return find(arr, vidx + 1, data);
}

int maximum(vector<int> &arr, int vidx)
{
    if (vidx == arr.size() - 1)
    {
        return arr[vidx];
    }

    int recAns = maximum(arr, vidx + 1);
    return max(recAns, arr[vidx]);
}

int minimum(vector<int> &arr, int vidx)
{

    if (vidx == arr.size() - 1)
    {
        return arr[vidx];
    }

    int recAns = minimum(arr, vidx + 1);
    return min(recAns, arr[vidx]);
}

int lastIndex(vector<int> &arr, int vidx, int data)
{
    // if (vidx == arr.size())
    // {
    //     return -1;
    // }

    // int recAns = lastIndex(arr, vidx + 1, data);
    // if (recAns != -1)
    //     return recAns;
    // else
    //     return (arr[vidx] == data ? vidx : -1);

    return vidx==arr.size()?-1:lastIndex(arr,vidx+1,data)?true:arr[vidx]==data?true:false;


}

int stairs(int n)
{

    if (n == 0)
    {
        return 1;
    }

    int count = 0;

    if (n - 1 >= 0)
        count += stairs(n - 1); //1 step jump.

    if (n - 2 >= 0)
        count += stairs(n - 2); //2 step jump.

    if (n - 3 >= 0)
        count += stairs(n - 3); //3 step jump.

    // for (int i = 1; i <= 3; i++)
    // {
    //     if (n - i >= 0)
    //         count += stairs(n - i);
    // }

    return count;
}

void solve()
{
    // vector<int> arr = {1, 0, 6, 8, 10, 4, 5, 5, 6, 8, -3, 2, 12, 8, 3};
    // display(arr, 0);

    cout << stairs(4) << endl;
}

int main(int args, char **argv)
{
    solve();
    return 0;
}