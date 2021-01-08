#include <iostream>
using namespace std;

void printIncreasing(int a, int b)
{
    if (a == b + 1)
        return;

    a += 10;
    cout << "hi" << a << " ";
    printIncreasing(a + 1, b);
    cout << "Bye" << a << " ";
}

void pppppp(int a)
{
    return;
}

void ppppp(int a)
{
    cout << "hi" << a << endl;
    pppppp(a + 1);
    cout << "bye" << a << endl;
}

void pppp(int a)
{
    cout << "hi" << a << endl;
    ppppp(a + 1);
    cout << "bye" << a << endl;
}

void ppp(int a)
{
    cout << "hi" << a << endl;
    pppp(a + 1);
    cout << "bye" << a << endl;
}

void pp(int a)
{
    cout << "hi" << a << endl;
    ppp(a + 1);
    cout << "bye" << a << endl;
}

void p(int a)
{
    cout << "hi" << a << endl;
    pp(a + 1);
    cout << "bye" << a << endl;
}

// questions.===========================================

void printDecreasing(int n)
{
    if (n == 0)
        return;

    cout << n << endl;
    printDecreasing(n - 1);
}

void printIncreasing(int n)
{
    if (n == 0)
        return;

    printIncreasing(n - 1);
    cout << n << endl;
}

void printIncDec(int n)
{
    if (n == 0)
        return;
    cout << n << endl;
    printIncDec(n - 1);
    cout << n << endl;
}

void printIncDec(int n)
{
    if (n == 0)
        return;
    if (n % 2 != 0)
        cout << n << endl;
    printIncDec(n - 1);
    if (n % 2 == 0)
        cout << n << endl;
}

int factorial(int n)
{
    return n == 0 ? 1 : n * factorial(n - 1);
}

int factorial(int n){
    int recAns = 1;
    if(n > 0) recAns = n * factorial(n-1);
    
    return recAns ;
}

int main()
{
    p(1);
    return 0;
}