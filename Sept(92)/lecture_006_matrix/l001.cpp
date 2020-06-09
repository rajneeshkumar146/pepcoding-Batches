#include <iostream>
#include <vector>

using namespace std;

void input(vector<vector<int>> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        for (int j = 0; j < arr[0].size(); j++)
        {
            cin >> arr[i][j];
        }
    }
}

void display(vector<vector<int>> &arr)
{
    // for(int i=0;i<arr.size();i++){
    //     for(int j=0;j<arr[0].size();j++){
    //         cout<<arr[i][j]<<" ";
    //     }
    //     cout<<endl;
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

void waveH(vector<vector<int>> &arr)
{

    for (int row = 0; row < arr.size(); row++)
    {
        if (row % 2 == 0)
        { //even
            for (int col = 0; col < arr[0].size(); col++)
            {
                cout << arr[row][col] << " ";
            }
        }
        else
        { //odd
            for (int col = arr[0].size() - 1; col >= 0; col--)
            {
                cout << arr[row][col] << " ";
            }
        }
        cout << endl;
    }
}

void exitPoint(vector<vector<int>> &arr)
{
    int dir = 0;
    int r = 0;
    int c = 0;
    while (true)
    {
        dir = (dir + arr[r][c]) % 4;

        if (dir == 0)
        {
            c++;
            if (c == arr[0].size())
            {
                cout << r << ", " << (c - 1) << endl;
                break;
            }
        }
        else if (dir == 1)
        {
            r++;

            if (r == arr.size())
            {
                cout << (r - 1) << ", " << c << endl;
                break;
            }
        }
        else if (dir == 2)
        {
            c--;

            if (c == -1)
            {
                cout << r << ", " << (c + 1) << endl;
                break;
            }
        }
        else
        {
            r--;

            if (r == -1)
            {
                cout << (r + 1) << ", " << c << endl;
                break;
            }
        }
    }
}

void spiralPrint(vector<vector<int>> &arr)
{
    int minr = 0, minc = 0;
    int maxr = arr.size() - 1, maxc = arr[0].size() - 1;
    int tne = arr.size() * arr[0].size();

    while (tne != 0)
    {
        for (int i = minc; i <= maxc && tne != 0; i++)
        {
            cout << arr[minr][i] << " ";
            tne--;
        }
        minr++;
        for (int i = minr; i <= maxr && tne != 0; i++)
        {
            cout << arr[i][maxc] << " ";
            tne--;
        }
        maxc--;

        for (int i = maxc; i >= minc && tne != 0; i--)
        {
            cout << arr[maxr][i] << " ";
            tne--;
        }
        maxr--;

        for (int i = maxr; i >= minr && tne != 0; i--)
        {
            cout << arr[i][minc] << " ";
            tne--;
        }
        minc++;
    }
}

void transpose(vector<vector<int>> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        for (int j = i; j < arr[0].size(); j++)
        {
            swap(arr[i][j], arr[j][i]);
        }
    }
}

void swapC(vector<vector<int>> &arr)
{
    int c1 = 0;
    int c2 = arr[0].size() - 1;
    while (c1 < c2)
    {

        for (int r = 0; r < arr.size(); r++)
        {
            swap(arr[r][c1], arr[r][c2]);
        }

        c1++;
        c2--;
    }
}

void swapR(vector<vector<int>> &arr)
{
    int r1 = 0;
    int r2 = arr.size() - 1;
    while (r1 < r2)
    {
        swap(arr[r1], arr[r2]);
        r1++;
        r2--;
    }
}

void rotate90(vector<vector<int>> &arr, bool isClockWise)
{
    transpose(arr);
    if (isClockWise)
        swapC(arr);
    else
        swapR(arr);
}

int main(int args, char **argv)
{
    cout << "please enter numbers: ";
    int n, m;
    cin >> n >> m;
    vector<vector<int>> arr(n, vector<int>(m, 10));
    // vector<vector<int>> arr;
    // for(int i=0;i<n;i++){
    //     vector<int> ar;
    //     for(int j=0;j<m;j++){
    //         ar.push_back(10);
    //     }
    //     arr.push_back(ar);
    // }

    input(arr);
    display(arr);

    cout<<endl;
    rotate90(arr,true);
    display(arr);
    
    return 0;
}
