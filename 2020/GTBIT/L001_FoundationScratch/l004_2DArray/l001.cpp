#include <iostream>
#include <vector>
using namespace std;

void input(vector<vector<int>> &arr)
{
    int n = arr.size();
    int m = arr[0].size();
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> arr[i][j];
        }
    }
}

void display1(vector<vector<int>> &arr)
{
    int n = arr.size();
    int m = arr[0].size();
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cout << arr[i][j] << " ";
        }
        cout << endl;
    }
}

void display(vector<vector<int>> &arr)
{
    for (vector<int> ar : arr)
    {
        for (int e : ar)
        {
            cout << e << " ";
        }
        cout << endl;
    }
}

int maximum(vector<vector<int>> &arr)
{
    int maxEle = -1e9;
    for (vector<int> ar : arr)
        for (int e : ar)
            maxEle = max(maxEle, e);
    
    return maxEle;
}

int main()
{
    return 0;
}
