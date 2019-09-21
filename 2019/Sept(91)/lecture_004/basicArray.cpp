#include <iostream>
#include <vector>
using namespace std;

void display(vector<int>& arr)
{
    for (int i : arr)
    {
        cout << i << " ";
    }
    cout << endl;
}

void input(vector<int>& arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        cin >> arr[i];
    }

    display(arr);
}

int main()
{
    vector<int> arr(5, 0);
    input(arr);
    display(arr);
}
