#include <iostream>
#include <vector>

using namespace std;

void input(vector<int> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        cin >> arr[i];
    }
}

void print(vector<int> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        cout << arr[i] << " ";
    }
    cout << endl;
}

// used for swapping ith and jth elements of array
void swap(vector<int> &arr, int i, int j)
{
    cout << ("Swapping " + to_string(arr[i]) + " and " + to_string(arr[j])) << endl;
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

void partition(vector<int> &arr, int pivot)
{
    int n = arr.size();
    int pt = 0, itr = 0;

    while (itr < n)
    {
        if (arr[itr] <= pivot)
            swap(arr, itr++, pt++);
        else
            itr++;
    }
}

int main()
{
    int n, m;
    cin >> n;
    vector<int> A(n, 0);
    input(A);

    int pivot;
    cin >> pivot;
    partition(A, pivot);
    print(A);
    return 0;
}