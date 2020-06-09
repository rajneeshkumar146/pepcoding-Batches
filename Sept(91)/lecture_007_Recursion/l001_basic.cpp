#include <iostream>
using namespace std;

void printIncre(int st, int end)
{
    if (st == end + 1)
        return;

    cout << st << " ";
    printIncre(st + 1, end);
}

void printDecr(int st, int end)
{
    if (st == end + 1)
        return;

    printDecr(st + 1, end);
    cout << st << " ";
}

int printDecre_returnType(int st, int end)
{
    if (st == end)
    {
        cout << st << " ";
        return st - 1;
    }

    int head = printDecre_returnType(st + 1, end);
    cout << head << " ";
    return head - 1;
}

int fact(int n)
{
    if (n <= 1)
        return 1;

    int res = fact(n - 1);
    return res * n;
}

int main(int args, char **argv)
{
    int st, end;
    // cin >> st >> end;
    // printIncre(st, end);
    // printDecr(st, end);
    // printDecre_returnType(1, 10);
    cout << fact(7) << endl;
    return 0;
}