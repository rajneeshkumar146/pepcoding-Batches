#include <iostream>
using namespace std;

void test4()
{
    int i = 97;
    int j = (int)(&i);
    cout << *(int *)j << endl;
}

int a = 10;
void test3()
{
    static int ii = 10;
    cout << ii << " " << a << endl;
    a++;
    ii++;
}
int *test2()
{
    static int a = 10;
    cout << a << " " << &a << endl;
    return &a;
}

void checkStack(int n)
{
    if (n == 2)
        return;
    int a = 10;
    cout << &a << endl;

    checkStack(n + 1);
}

void test1()
{
    int a = 10;
    int *p = &a;
    int **q = &p;

    cout << a << " " << &a << endl;
    cout << p << " " << &p << endl;
    cout << q << " " << &q << endl;
    cout << endl;
    cout << *q << " " << p << endl;
    cout << **q << " " << a << endl;
}

int main(int args, char **argv)
{
    // test1();
    // checkStack(0);
    // cout << test2();

    // for (int i = 0; i < 10; i++)
    // {
    //     test3();
    //     a++;
    // }

    test4();
    return 0;
}