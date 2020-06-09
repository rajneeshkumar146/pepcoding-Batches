#include <iostream>
using namespace std;

int **set3()
{
    int **a = new int *[10];
    for (int i = 0; i < 10; i++)
    {
        a[i] = new int[5];
        for (int j = 0; j < 5; j++)
        {
            a[i][j] = i * j + 1;
        }
    }

    return a;
}

int *set2()
{
    int *a = new int[10];
    for (int i = 0; i < 10; i++)
    {
        a[i] = 1000;
    }

    return a;
}

void set1()
{
    int a = 10;
    int *b = &a;
    int **c = &b;

    cout << a << endl;
    cout << b << " " << &a << endl;
    cout << c << " " << &b << endl;

    cout << *b << endl;
    cout << *c << endl;
    cout << **c << endl;
}
void solve()
{
    // set1();
    int *a = set2();
    // int a[4] = {1, 23, 3, 4};
    long long int b = (long long int)a;
    cout << a << " " << b << endl;
    cout << *(int *)b << endl;

    // int **a = set3();
    // for (int i = 0; i < 10; i++)
    // {
    //     for (int j = 0; j < 5; j++)
    //     {
    //         cout << a[i][j] << " ";
    //     }
    //     cout << endl;
    // }
}

int main()
{
    solve();
    return 0;
}