#include <iostream>
using namespace std;

int main(int args, char **argv)
{

    int n;
    cout << "please! entre a number: ";
    cin >> n;

    int count_ = 0;
    while (n != 0)
    {
        n /= 10;
        count_++;
    }

    cout << count_ << endl;

    return 0;
}

void input(vector<int>& arr){
   
   vector<vector<int>> arr(4,vector<int>(4,0));

   swap(arr[2],arr[3]);

}