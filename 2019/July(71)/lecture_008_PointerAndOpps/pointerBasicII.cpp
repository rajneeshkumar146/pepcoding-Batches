#include <iostream>

using namespace std;

void caseIII()
{
    int *p = new int[1000];
    for (int i = 0; i < 1000; i++)
    {
        p[i] = 10;
    }

    // for (int i = 0; i < 1000; i++)
    // {
    //     cout << i << endl;
    // }
}

void caseII(int *arr)
{
    int size = sizeof(arr) / sizeof(arr[0]);
    cout << size << endl;
}

void caseI()
{
    // int arr[17];
    // arr[0] = 10;
    // arr[1] = 20;
    // arr[2] = 30;
    // arr[4] = 312;
    // arr[16] = 200;

    // int *p1 = arr;

    // cout << arr << " " << &arr << endl;
    // cout << arr << " " << *(arr + 1) << endl; //new thing.
    // cout << arr << " " << &arr[2] << endl;

    // p1++;

    // int arr[] = {1, 2, 3, 4, 5, 6, 7};
    // int size = sizeof(arr) / sizeof(arr[0]);
    // cout << size << endl;
    // caseII(arr);

    // for (int i = 0; i < 10000; i++)
    // {
    //     caseIII();
    // }
}

void solve()
{
    // caseI();
    // int a=10;
    // int sum=++a + a++ + ++a;
    // cout<<sum;
    for (int i = 0; i < 10000000; i++)
        caseIII(); //memory leak;
}

int main(int args, char **argv)
{
    solve();
    return 0;
}