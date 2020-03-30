#include <iostream>
#include <vector>

using namespace std;

void printIncreasing(int a, int b)
{
    if (a == b + 1)
        return;

    cout << a << " ";
    printIncreasing(a + 1, b);
}

void printDecreasing(int a, int b)
{
    if (a == b + 1)
        return;

    printDecreasing(a + 1, b);
    cout << a << " ";
}

void printIncDec(int a, int b)
{
    if (a == b + 1)
        return;

    cout << a << "hi" << endl;
    printIncDec(a + 1, b);
    cout << a << "bye" << endl;
}

void printEvenOdd(int a, int b)
{
    if (a == b + 1)
        return;

    if (a % 2 == 0) //even
        cout << a << " ";
    printEvenOdd(a + 1, b);
    if (a % 2 != 0) //odd
        cout << a << " ";
}



void set2(){
    // vector<int> arr(n,0);
    // for(int i=0;i<n;i++){
    //     cin>>arr[i];
    // }

    vector<int> arr={1,234,34,7,2,1,23,4,5,6,3,2,5,7,32};


}

void set1()
{
    int a, b;
    cin >> a >> b;
    // printIncreasing(a, b);
    // printDecreasing(a, b);
    // printIncDec(a, b);
    printEvenOdd(a, b);
}

void solve()
{
    // set1();
    set2();
}

int main()
{
    solve();
    return 0;
}
