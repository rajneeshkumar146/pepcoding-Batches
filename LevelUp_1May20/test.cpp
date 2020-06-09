#include <iostream>
#include <vector>
using namespace std;

void input(vector<int> &arr)
{
    for (int i = 0; i < arr.size(); i++)
        cout << arr[i] << " ";
    cout << endl;

    for (int i = 0; i < arr.size(); i++)
        arr[i] = i * 10;

    for (int i = 0; i < arr.size(); i++)
        cout << arr[i] << " ";
    cout << endl;
}

int fun1(int *a)
{
    cout << &*a << endl;
}

int *fun1()
{
    int arr[10] = {0};
    int *a = arr;
    for (int i = 0; i < 10; i++)
        arr[i] = i * 10;
    return a;
}

int main()
{
    // int *a = fun1();
    // vector<int> arr(10,1);
    // input(arr);
    // for (int i = 0; i < 100; i++) cout<<arr[i]<<" ";
    // cout<<endl;

    int a = 10;
    // int *b = &a;
    // cout << b << endl;
    cout << &a << endl;
    // cout << b << endl;
    // cout << &*b << endl;
    fun1(&a);

    return 0;
}