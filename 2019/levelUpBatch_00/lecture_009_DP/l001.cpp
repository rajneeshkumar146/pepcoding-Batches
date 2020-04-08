#include <iostream>
#include <vector>
using namespace std;

vector<vector<int>> multiplication(vector<vector<int>> &a, vector<vector<int>> &b)
{
    vector<vector<int>> ans(2, vector<int>(2, 0));

    int a0 = a[0][0] * b[0][0] + a[0][1] * b[1][0];
    int a1 = a[0][0] * b[0][1] + a[0][1] * b[1][1];
    int a2 = a[1][0] * b[0][0] + a[1][1] * b[1][0];
    int a3 = a[1][0] * b[0][1] + a[1][1] * b[1][1];

    ans[0][0] = a0;
    ans[0][1] = a1;
    ans[1][0] = a2;
    ans[1][1] = a3;

    return ans;
}

vector<vector<int>> fiboLogn(vector<vector<int>> &a, int n)
{
    if (n == 1)
        return a;

    vector<vector<int>> recAns = fiboLogn(a, n / 2);
    recAns = multiplication(recAns, recAns);

    return n % 2 != 0 ? multiplication(recAns, a) : recAns;
}

vector<vector<int>>* test(){
vector<vector<int>> *ans = new vector<vector<int>>(2,vector<int>(2,0));
    (*ans)[0][0]=100;

    return ans;
}

int main()
{

vector<vector<int>> ans=*test();
cout<<ans[0][0];

int** arr=new int*[10];
for(int i=0;i<10;i++){
    arr[i]=new int[10];
}

    

    // vector<vector<int>> a = {{1,1}, {1,0}};
    // vector<vector<int>> mat = fiboLogn(a, 7);
    // cout<<mat[0][1]<<endl;

    return 0;
}
