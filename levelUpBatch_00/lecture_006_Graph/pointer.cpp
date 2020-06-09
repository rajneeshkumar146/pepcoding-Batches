#include <iostream>

using namespace std;

int *test2()
{
    // int a = 10;
    int* d = new int(1000);
    int *c = &d;
    delete d;
    // int *arr = new int[10];
    // for (int i = 0; i < 10; i++)
    // {
    //     cin >> arr[i];
    // }

    // int ar[] = {10, 20, 30, 40, 50, 60};
    // cout << sizeof(ar) / sizeof(arr[0]) << endl;
    // int *b = &ar[0];
    return c;
}

void test1()
{
    int a = 10;
    int *b = &a;
    int **c = &b;

    cout << a << " " << &a << endl;
    cout << b << " " << &b << endl;
    cout << c << " " << **c << endl;
}

int main()
{
    // test1();
    int *a = test2();
    cout << *a << endl;
    // for (int i = 0; i < 100; i++)
    // {
    //     cout << a[i] << " ";
    // }

    return 0;
}