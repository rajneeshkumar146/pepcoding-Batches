#include <iostream>
#include <vector>
using namespace std;

void display(vector<vector<int>> &arr)
{
    for (vector<int> ar : arr)
    {
        for (int ele : ar)
        {
            cout << ele << " ";
        }
        cout << endl;
    }
    cout << endl;
}

void swapfor4(vector<vector<int>> &arr, int i, int j, int k, int l)
{
    int temp = arr[i][j];
    arr[i][j] = arr[k][l];
    arr[k][l] = temp;
}

void transpose(vector<vector<int>> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        for (int j = i; j < arr[0].size(); j++)
        {
            // swapfor4(arr,i,j,j,i);
            swap(arr[i][j], arr[j][i]);
        }
    }
}

void colSwap(vector<vector<int>> &arr)
{
    int i = 0, j = arr[0].size() - 1;
    while (i < j)
    {
        for (int k = 0; k < arr.size(); k++)
        {
            // swapfor4(arr,i,j,j,i);
            swap(arr[k][i], arr[k][j]);
        }
        i++;
        j--;
    }
}

void rowSwap(vector<vector<int>> &arr)
{
    int i = 0, j = arr.size() - 1;
    while (i < j)
    {
        swap(arr[i], arr[j]);
        i++;
        j--;
    }
}

void turnBy90(vector<vector<int>> &arr, bool is90)
{
    transpose(arr);
    if (is90 == 1)
        colSwap(arr);
    else
        rowSwap(arr);

    display(arr);
}

void countOfSetBits(int num)
{
    int digit = 0;
    int count = 0;
    while (num != 0 )
    {
        if ((num & 1) != 0)
            digit++;
        count++;
        num >>= 1;
    }

    cout << digit << endl;
}

int main()
{

    // vector<vector<int>> arr{{1, 2, 3, 4},
    //                         {5, 6, 7, 8},
    //                         {9, 10, 11, 12},
    //                         {13, 14, 15, 16}};
    // display(arr);
    // turnBy90(arr, true);
    // turnBy90(arr,false);

    countOfSetBits(3);

    return 0;
}