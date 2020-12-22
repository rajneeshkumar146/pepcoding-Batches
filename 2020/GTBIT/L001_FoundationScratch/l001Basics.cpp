#include <iostream>
using namespace std;

int multiply(int a,int b){
    int c = a * b;
    return c;
}

int add(int a,int b){
    int c = a + b;
    return c;
}

int subtract(int a,int b){
    int c = b - a;
    return c;
}

int divide(int a,int b){
    int c = b / a;
    return c;
}

void printmessage(){
   cout << "see you soon!"<<endl; 
}

int main()
{
    int a, b;
    cin >> a >> b;
    cout << multiply(a,b) << endl;
    cout << multiply(a,b) << endl;
    cout << multiply(a,b) << endl;
    cout << multiply(a,b) << endl;
    cout << multiply(a,b) << endl;
    cout << add(a,b) << endl;
    printmessage();
    return 0;
}
