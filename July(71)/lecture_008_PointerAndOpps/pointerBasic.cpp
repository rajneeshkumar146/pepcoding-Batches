#include <iostream>

using namespace std;

void caseIV()
{
    int i = 97;
    int j = (int)(&i);

    cout << (char)i << endl;
    cout << &i << endl;
    cout << j << " " << (int *)j << endl;
}

int g_var = 0; //global
//static and global
void caseIII_()
{
    static int i = 0;
    i++;
    cout << i << " ";
}

void caseIII()
{
    for (int i = 0; i < 10; i++)
    {
        caseIII_();
    }
}

void stackIdentification(int idx)
{
    if (idx == 2)
        return;
    cout << &idx << endl;
    stackIdentification(idx + 1);
}

// int *caseII_()
// {
//     int var = 10;
//     return &var;
// }

// void caseII() //address of local varisable which is not exist.
// {
//     int *p = caseII_();
//     cout << p << *p << endl;
// }

void caseI()
{

    int a = 10;
    int *p = &a;
    int **q = &p;

    cout << a << " " << &a << endl;
    cout << p << " " << &p << endl;
    cout << q << " " << &q << endl;

    cout << *p << " " << &(*p) << endl;
    cout << *q << " " << &(*q) << endl;
    cout << **q << " " << &(**q) << endl;

    cout << *&*&*&*&*&*&*&*&*q;
}

void solve()
{
    // caseI();
    // caseII();
    // stackIdentification(0);
    // caseIII();
    caseIV();

    // char ch = 65;
    // cout << ch << endl;
    // char ch2 = -10;
    // cout << (int)ch2 << endl;
}

int main(int args, char **argv)
{
    solve();
    return 0;
}