#include <iostream>
using namespace std;

int main()
{
    // int n,a; cin>>n>>a;
    // int count=a;
    // for(int i=1;i<=n;i++){
    //     cout<<count<<" ";
    //     count+=a;
    // }

    cout << "Enter number: ";
    int n;
    cin >> n;

    cout<<2<<" ";
    for (int num = 3; num <= n; num++)
    {
        bool flag = true;
        for (int i = 2; i*i <= num; i++)
        {
            if (num % i == 0)
            {
                flag = false;
                break;
            }
        }

        if (flag)
            cout << num << " ";
        // else
        //     cout << "Not Prime" << endl;
    }

    return 0;
}