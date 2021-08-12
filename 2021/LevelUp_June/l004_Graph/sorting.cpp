#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

void display(vector<int> &arr)
{
    for (int e : arr)
        cout << e << " ";
    cout << endl;
}

void display2D(vector<vector<int>> &arr)
{
    for (vector<int> &a : arr)
        display(a);
    cout << endl;
}

void sortArray(vector<vector<int>> &arr)
{
    // {{A,B,C}}, sort on the basis of index 2
    sort(arr.begin(), arr.end(), [](vector<int> &a, vector<int> &b)
         { return a[2] < b[2]; });
    display2D(arr);
}

int main()
{
    vector<vector<int>> arr = {{1, 2, 3}, {1, 2, -20}, {4, 2, -3}, {10, 22, 23}};
    sortArray(arr);
    return 0;
}
