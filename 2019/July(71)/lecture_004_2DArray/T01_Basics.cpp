zd#include <iostream>
#include <vector>

using namespace std;

void twoDVector()
{
    int n = 3;
    int m = 4;
    vector<vector<int>> arr(n, vector<int>(m, 1)); // int[][] arr=new int[n][m];

    // for (int i = 0; i < n; i++)
    // {
    //     for (int j = 0; j < m; j++)
    //     {
    //         cout << arr[i][j] << " ";
    //     }
    //     cout << endl;
    // }

    for (vector<int> ar : arr)
    {
        for (int ele : ar)
        {
            cout << ele << " ";
        }
        cout << endl;
    }
}

void twoDArray()
{
    int n = 3;
    int m = 4;
    int **arr = new int *[n];
    for (int i = 0; i < n; i++)
    {
        int *ar = new int(m);
        arr[i] = ar;
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            arr[i][j] = 10;
            cout << arr[i][j] << " ";
        }
        cout << endl;
    }
}

int *oneDPointer()
{
    int n = 10;
    // int arr[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int *arr = new int(n);

    for (int i = 0; i < n; i++)
    {
        arr[i] = 0;
    }

    // for (int i = 0; i < n; i++)
    // {
    //     cout << arr[i] << " ";
    // }

    return arr;
}

void solve()
{
    // int *arr = oneDPointer();
    // for (int i = 0; i < 100; i++)
    // {
    //     cout << arr[i] << " ";
    // }

    // twoDArray();
    twoDVector();
}

int main(int args, char **argv)
{
    solve();
    return 0;
}
