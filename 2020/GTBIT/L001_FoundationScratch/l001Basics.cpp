#include <iostream>
using namespace std;

int multiply(int a, int b)
{
    int c = a * b;
    return c;
}

int add(int a, int b)
{
    int c = a + b;
    return c;
}

int subtract(int a, int b)
{
    int c = b - a;
    return c;
}

int divide(int a, int b)
{
    int c = b / a;
    return c;
}

void printmessage()
{
    cout << "see you soon!" << endl;
}

void printNTime(int n)
{
    for (int i = 1; i <= n; i++)
    {
        cout << ("Hello!") << endl;
    }
}

void printTableOfN(int n)
{
    for (int i = 1; i <= 10; i++)
    {
        // cout << n << " X " << i << " = " << n * i << endl;
        cout << to_string(n) + " X " + to_string(i) + " = " + to_string(n * i) << endl;
    }
}

void printTableTillM(int m)
{
    for (int i = 1; i <= m; i++)
    {
        printTableOfN(i);
        cout << endl;
    }
}

void oddEven(int n){
    if(n % 2 == 0){
        cout <<to_string(n) + " -> even" << endl;
    }else{
        cout << to_string(n) + " -> odd" << endl;
    }
}

void checkNforOddEven(){
    int totalInputs; cin >> totalInputs;

    for(int i = 1;i <= totalInputs; i++){
        int number;
        cin >> number;

        oddEven(number);
    }
}


int countDigits(int n){
    int count = 0;
    while(n != 0){
        n /= 10;
        count++;
    }
    
    return count;
}

void reverse(int n){
    while(n != 0){
        int lastDigit = n % 10;
        n /= 10;
        
        cout << lastDigit << endl;
    }
}

int reverseNumber(int n){
    int ans = 0;
    while(n != 0){
        int lastDigit = n % 10;
        n /= 10;
        
        ans = ans * 10 + lastDigit;
    }

    return ans;
}

int main()
{
    // int a, b;
    // cin >> a >> b;
    // cout << multiply(a,b) << endl;
    // cout << multiply(a,b) << endl;
    // cout << multiply(a,b) << endl;
    // cout << multiply(a,b) << endl;
    // cout << multiply(a,b) << endl;
    // cout << add(a,b) << endl;
    // printmessage();

    // int n;
    // cin >> n;
    // printNTime(n);
    // printTableTillM(n);
    // printTableOfN(27);
    
    checkNforOddEven();

    return 0;
}
