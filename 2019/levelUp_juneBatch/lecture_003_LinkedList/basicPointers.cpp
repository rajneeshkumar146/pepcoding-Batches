#include <iostream>
#include <vector>
using namespace std;

void exp4()
{
    // vector<int> **arr = new vector<int> *[10];
    // for (int i = 0; i < 10; i++)
    // {
    // vector<int> *a =
    // arr[i] = new vector<int>(5, 19);
    //  }

    // for (int i = 0; i < 10; i++)
    // {
    //     for (int j = 0; j < 10; j++)
    //     {
    //         // cout<<arr[i]->at(j)<<" ";
    //     }
    //     cout << endl;
    // }

    vector<int> *arr = new vector<int>[10];
    for (int i = 0; i < 10; i++)
    {
        vector<int> a(10, 18);
        arr[i] = a;
    }

    for (int i = 0; i < 10; i++)
    {
        for (int j = 0; j < 10; j++)
        {
            // cout<<arr[i]->at(j)<<" ";
            cout << arr[i][j] << " ";
        }
        cout << endl;
    }

    // vector<int> arr(5, 8);

    // for (int i = 0; i < 5; i++)
    // {
    //     // cout<<arr[i]<<" ";
    //     cout << arr.at(i) << " ";
    // }
}

void exp3()
{
    int arr[10] = {0};
    for (int i = 0; i < 10; i++)
        arr[i] = i + 100;

    for (int i = 0; i < 10; i++)
        cout << arr[i] << " ";
}

void exp2()
{
    // int *a = new int(10);
    // cout << *a << endl;

    // int *a = new int[10];
    // for (int i = 0; i < 10; i++)
    //     a[i] = i + 100;

    // for (int i = 0; i < 10; i++)
    //     cout << *(a + i) << " ";

    int **a = new int *[10];
    for (int i = 0; i < 5; i++)
    {
        int *b = new int[6];
        a[i] = b;
    }

    for (int i = 5; i < 10; i++)
    {
        int *b = new int(6);
        a[i] = b;
    }
}

void exp1()
{
    int a = 10;
    int *b = &a; // (type)* name=&var;
    int **c = &b;
    // cout << a << endl;
    // cout << b << " " << *b << endl;
    // cout << c << " " << *c << endl;

    vector<int> arr(10, 0);
    cout << &arr << endl;
}

void solve()
{
    // exp1();
    // exp2();
    // exp3();
    exp4();
}

int main()
{
    solve();
    return 0;
}