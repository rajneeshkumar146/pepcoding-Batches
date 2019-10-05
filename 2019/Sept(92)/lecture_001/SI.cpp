#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int p = 1000;
    int r = 4;
    int t = 2;
    int si = (p * r * t) / 100;
    // cout << "SI is: " << si << endl;
    cout << "SI is: " + to_string(si) << endl;
    return 0;
}