#include <iostream>
#include <vector>
using namespace std;

void display(vector<int>& arr)
{
    // if (vidx == arr.size())
    //     return;
    for (int ele : arr)
        cout << ele << " ";
    cout << endl;

    // cout << arr[vidx] << " ";
    // display(arr, vidx + 1);
}

void task1(int arr[10],int a){
    for (int i = 0; i < 10; i++)
    {
        cin >> arr[i];
    }
}

void input(vector<int>& arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        cin >> arr[i];
    }
    display(arr);
}

void solve()
{
    // vector<int> arr(10, 0);
    // display(arr);
    // input(arr);
    // display(arr);
    int arr[10]={0};

}

int main()
{
    solve();
    return 0;
}