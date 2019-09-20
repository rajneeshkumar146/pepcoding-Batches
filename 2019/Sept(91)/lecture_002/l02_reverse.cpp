#include <iostream>
using namespace std;

int main(int args, char **argv)
{

    int n;
    cout << "please! entre a number: ";
    cin >> n;

    int res = 0;
    while (n != 0)
    {
        int rem = n % 10;
        n /= 10;
        res = res * 10 + rem;
    }
    cout<<"Reverse Number: "<<res<<endl;
}