#include <iostream>
#include <vector>
using namespace std;

void fun(int a, int b)
{
    if (a == 5)
    {
        b = 5;
        return;
    }

    a++;
    fun(a, b);
    cout << b << endl;
}

int main()
{
    int b = 1;
    fun(1, b);
    return 0;
}