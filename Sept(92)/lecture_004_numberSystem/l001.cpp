#include <iostream>
#include <vector>
using namespace std;

int DtoB(int num)
{
    int res = 0;
    int pow = 1;

    while (num != 0)
    {

        int rem = num % 2;
        num /= 2;

        // res=res+pow*rem;
        res += pow * rem;
        pow *= 10;
    }

    return res;
}

int BtoD(int num)
{
    int res = 0;
    int pow = 1;

    while (num != 0)
    {
        int rem = num % 10;
        num /= 10;

        // res=res+pow*rem;
        res += pow * rem;
        pow *= 2;
    }

    return res;
}


int AnytoD(int num,int base1)
{
    int res = 0;
    int pow = 1;

    while (num != 0)
    {
        int rem = num % 10;
        num /= 10;

        // res=res+pow*rem;
        res += pow * rem;
        pow *= base1;
    }

    return res;
}

int DtoAny(int num,int base2)
{
    int res = 0;
    int pow = 1;

    while (num != 0)
    {

        int rem = num % base2;
        num /= base2;

        // res=res+pow*rem;
        res += pow * rem;
        pow *= 10;
    }

    return res;
}

int main(int args, char **argv)
{
    cout << "Entre Number: ";
    int num;
    cin >> num;
    int val = DtoB(num);
    cout << "B: " << val << " -> " << BtoD(val) << endl;
}