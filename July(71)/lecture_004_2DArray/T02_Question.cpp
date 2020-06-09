#include <iostream>
#include <vector>

using namespace std;

void exitPoint(vector<vector<int>> &arr)
{
    int dir = 0, r = 0, c = 0;
    while (true)
    {
        dir = (dir + arr[r][c]) % 4;
        if (dir == 0)
        {
            c++;
            if (c == arr[0].size())
            {
                cout << r << ", " << c - 1 << endl;
                break;
            }
        }
        else if (dir == 1)
        {
            r++;

            if (r == arr.size())
            {
                cout << r - 1 << ", " << c << endl;
                break;
            }
        }
        else if (dir == 2)
        {
            c--;

            if (c == -1)
            {
                cout << r << ", " << c + 1 << endl;
                break;
            }
        }

        else
        {
            r--;

            if (r == -1)
            {
                cout << r + 1 << ", " << c << endl;
                break;
            }
        }
    }
}

void spiralPrint(vector<vector<int>> &arr)
{
    int minr = 0, minc = 0, maxr = arr.size() - 1, maxc = arr[0].size() - 1;
    int tne = arr.size() * arr[0].size();
    while (tne > 0)
    {

        for (int i = minc; i <= maxc && tne > 0; i++)
        {
            cout << arr[minr][i] << " ";
            tne--;
        }
        minr++;

        for (int i = minr; i <= maxr && tne > 0; i++)
        {
            cout << arr[i][maxc] << " ";
            tne--;
        }

        maxc--;

        for (int i = maxc; i >= minc && tne > 0; i--)
        {
            cout << arr[maxr][i] << " ";
            tne--;
        }

        maxr--;

        for (int i = maxr; i >= minr && tne > 0; i--)
        {
            cout << arr[i][minc] << " ";
            tne--;
        }

        minc++;
    }
}

void waveVecrtical(vector<vector<int>> &arr)
{
    for (int c = 0; c < arr[0].size(); c++)
    {
        if (c % 2 != 0)
        { //odd
            for (int r = 0; r < arr.size(); r++)
            {
                cout << arr[r][c] << " ";
            }
        }
        else
        {
            for (int r = arr.size() - 1; r >= 0; r--)
            {
                cout << arr[r][c] << " ";
            }
        }
    }

    cout << endl;
}

void waveHorizontal(vector<vector<int>> &arr)
{
    for (int r = 0; r < arr.size(); r++)
    {
        if (r % 2 == 0)
        { //even
            for (int c = 0; c < arr[r].size(); c++)
            {
                cout << arr[r][c] << " ";
            }
        }
        else
        {
            for (int c = arr[r].size() - 1; c >= 0; c--)
            {
                cout << arr[r][c] << " ";
            }
        }
    }

    cout << endl;
}

int minEle(vector<vector<int>> &arr)
{

    int min_ = arr[0][0];
    for (int row = 0; row < arr.size(); row++)
    {

        for (int col = 0; col < arr[row].size(); col++)
        {
            if (arr[row][col] < min_)
            {
                min_ = arr[row][col];
            }
        }
    }
    return min_;
}

int maxEle(vector<vector<int>> &arr)
{
    int max_ = arr[0][0];
    for (int row = 0; row < arr.size(); row++)
    {

        for (int col = 0; col < arr[row].size(); col++)
        {
            if (arr[row][col] > max_)
            {
                max_ = arr[row][col];
            }
        }
    }
    return max_;
}

bool find(vector<vector<int>> &arr, int data)
{

    for (int row = 0; row < arr.size(); row++)
    {
        for (int col = 0; col < arr[0].size(); col++)
        {
            if (arr[row][col] == data)
            {
                cout << row << ", " << col << " -> ";
                return true;
            }
        }
    }
    return false;
}

void solve()
{
    // int n = 3;
    // int m = 4;
    // vector<vector<int>> arr(n, vector<int>(m, 0));
    vector<vector<int>> arr = {{10, 3, 5},
                               {5, 7, 50},
                               {4, 8, 100}};

    // cout << (boolalpha) << find(arr, 7) << endl;
    // cout << (boolalpha) << find(arr, 700) << endl;
    // cout << maxEle(arr) << endl;
    // cout << minEle(arr) << endl;
    // waveHorizontal(arr);
    // waveVecrtical(arr);
    spiralPrint(arr);
}

int main(int args, char **argv)
{
    solve();
    return 0;
}