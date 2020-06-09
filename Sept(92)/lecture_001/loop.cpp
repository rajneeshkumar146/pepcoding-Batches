#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int i=1;
    int a=2;
    // for (; i <= 10;)
    while(i<=10)
    {
        cout << a << " ";

        a+=2;
        i++;
    }

    return 0;
}