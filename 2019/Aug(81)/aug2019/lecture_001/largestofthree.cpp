#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int a, b, c;
    cout << "Entre a: " << endl;
    cin >> a;

    cout << "Entre b: " << endl;
    cin >> b;

    cout << "Entre c: " << endl;
    cin >> c;

    if(a>b && b>c){
        cout<<"largest is a:" <<a<<endl;
    }else if(b>a && b>c){
        cout<<"largest is b:" <<b<<endl;
    }else{ 
        cout<<"largest is c:" <<c<<endl;
    }
}