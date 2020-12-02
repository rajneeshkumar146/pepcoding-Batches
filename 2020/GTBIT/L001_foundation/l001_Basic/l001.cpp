#include <iostream>
using namespace std;

void printHello()
{
    cout << "Hi There!" << endl;
    cout << "This is Rajneesh" << endl;
}

void workWithDataType()
{
    int a = 10;
    int b = 20;

    // cout << "Value of A: " << a << endl;
    // cout << "Value of B: " << b << endl;

    cout << "Value of A: " + to_string(a) << endl;
    cout << "Value of B: " + to_string(b) << endl;
}

void oddEven(int n)
{
    if (n % 2 == 0)
    {
        cout << ("Even");
    }
    else
    {
        cout << ("Odd");
    }
}

bool isPrime_(int n)
{
    bool prime = true;
    for (int i = 2; i * i <= n; i++)
    {
        if (n % i == 0)
        {
            prime = false;
            break;
        }
    }

    return prime;
}

void isPrime()
{
    int n;
    cin >> n;
    bool ans = isPrime_(n);

    if (ans)
        cout << "prime" << endl;
    else
        cout << "not prime" << endl;
}

void printAllPrime(int a, int b)
{
    for (int i = a; i <= b; i++)
    {
        bool ans = isPrime_(i);
        if (ans)
            cout << i << endl;
    }
}

void printFibo(int n)
{
    int a = 0, b = 1;
    for (int i = 1; i <= n; i++)
    {
        cout << a << endl;
        int sum = a + b;
        a = b;
        b = sum;
    }
}

int countDigits(int n){
    int count = 0;
    int num = n;
    while(num != 0){
        num /= 10;
        count++;
    }
    
    return count;
}

int rotateNumber(int n,int r){
    int len = countDigits(n);
    r %= len;
    if(r < 0) r = (r + len) % len;

    int mul = 1, div = 1;
    for(int i = 1; i <= len; i++){
        if(i <= r) div *= 10;
        else  mul*= 10;
    }

    int A = n % div;
    int B = n / div;

    return A * mul + B;
}


// cout<<(boolalpha)<<isPythagorean(a,b,c)<<endl;
bool isPythagorean(int a,int b,int c){
    if((a*a + b*b == c*c ) || (b*b + c*c == a*a) || (a*a + c*c == b*b)) return true;
    
    return false;
}

void benjaminBulbs(int n){
    for(int i = 1; i*i <= n ; i++){
        cout << i * i << endl;
    }  
}




int main()
{
    // printHello();
    // workWithDataType();
    // int n;
    // cin >> n;
    // oddEven(n);

    int t;
    cin >> t;
    while (t-- > 0)
    {
        isPrime();
    }

    return 0;
}