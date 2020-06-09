#include <iostream>
using namespace std;

int main()
{

    int a = 0, b = 1, sum = 0;
    cout << "Enter Number: "; //System.out.println("Enter Number: ");
    int n;
    cin >> n;  // int n =scn.nextInt();
     
     int prev=-1;
    for (int i = 1; i <= n; i++)
    {
        cout << a << " ";  //syso(a);
        sum = a + b;
        prev=a;
        a = b;
        b = sum;
    }

    cout << endl
         << prev << endl;

    return 0;
}