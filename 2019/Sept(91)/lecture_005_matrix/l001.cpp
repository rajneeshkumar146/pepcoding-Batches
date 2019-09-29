#include <iostream>
#include <vector>
using namespace std;

void input(vector<vector<int>> &arr)
{
    for (int r = 0; r < arr.size(); r++)
    {
        for (int c = 0; c < arr[0].size(); c++)
        {
            cin >> arr[r][c];
        }
    }
}

void display(vector<vector<int>> &arr)
{
    for (int r = 0; r < arr.size(); r++)
    {
        for (int c = 0; c < arr[0].size(); c++)
        {
            cout << arr[r][c] << " ";
        }

        cout << endl;
    }
}

void addMatrix(vector<vector<int>> &arr, vector<vector<int>> &arr1)
{
    for (int r = 0; r < arr.size(); r++)
    {
        for (int c = 0; c < arr[0].size(); c++)
        {
            arr[r][c] += arr1[r][c];
        }
    }
}

void waveDisplay(vector<vector<int>> &arr)
{

    for (int r = 0; r < arr.size(); r++)
    {
        if (r % 2 != 0)
        { //odd
            for (int c = arr[0].size() - 1; c >= 0; c--)
            {
                cout << arr[r][c] << " ";
            }
        }
        else
        { //even
            for (int c = 0; c < arr[0].size(); c++)
            {
                cout << arr[r][c] << " ";
            }
        }

        cout << endl;
    }
}

void waveDisplay_V(vector<vector<int>> &arr)
{

    for (int c = 0; c < arr.size(); c++)
    {
        if (c % 2 != 0)
        { //odd
            for (int r = arr.size() - 1; r >= 0; r--)
            {
                cout << arr[r][c] << " ";
            }
        }
        else
        { //even
            for (int r = 0; r < arr.size(); r++)
            {
                cout << arr[r][c] << " ";
            }
        }

        cout << endl;
    }
}

void matrixMultiplication(vector<vector<int>> &arr, vector<vector<int>> &arr1)
{
    if (arr[0].size() != arr1.size())
        return;
    vector<vector<int>> ans(arr.size(), vector<int>(arr1[0].size(), 0));

    for (int c = 0; c < ans[0].size(); c++)
    {
        for (int r = 0; r < ans.size(); r++)
        {
            for (int k = 0; k < arr[0].size(); k++)
            {
                ans[r][c] += arr[r][k] * arr1[k][c];
            }
        }
    }
}

int main(int args, char **argv)
{
    int n, m;
    cin >> n >> m;
    vector<vector<int>> arr(n, vector<int>(m, 0));
    input(arr);

    // vector<vector<int>> arr1(n, vector<int>(m, 0));
    // input(arr1);
    // addMatrix(arr, arr1);

    waveDisplay(arr);

    // display(arr);
}