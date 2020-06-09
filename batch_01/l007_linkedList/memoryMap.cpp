#include <iostream>

using namespace std;

void set1()
{
    int a = 10;
    int *b = &a;
    int **c = &b;

    cout << a << endl;
    cout << b << " " << *b << endl;
    cout << c << " " << *c << endl;

    int *arr = new int[10];
    arr[0] = 10;
    arr[1] = 20;
    int **arr1 = new int *[10];
    arr1[0] = arr;
    cout << arr1[0][1] << endl;
}

void solve()
{
    set1();
}

int main()
{
    solve();
    return 0;
}