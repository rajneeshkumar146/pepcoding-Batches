#include <iostream>
#include <vector>
using namespace std;

void display(vector<int> &arr)
{
    for (int i : arr)
    {
        cout << i << " ";
    }
    cout << endl;
}

void input(vector<int> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        cin >> arr[i];
    }
}

int maximum(vector<int> &arr)
{
    int max_ = arr[0];
    for (int i = 1; i < arr.size(); i++)
    {
        if (arr[i] > max_)
            max_ = arr[i];
    }

    cout << "maximum: " << max_ << endl;
}

int minimum(vector<int> &arr)
{
    int min_ = arr[0];
    for (int i = 1; i < arr.size(); i++)
    {
        if (arr[i] < min_)
            min_ = arr[i];
    }

    cout << "minimum: " << min_ << endl;
}

int main()
{
    int n;
    cin >> n;
    vector<int> arr(n, 0);
    input(arr);  
    // display(arr);

    maximum(arr);
    minimum(arr);
}
