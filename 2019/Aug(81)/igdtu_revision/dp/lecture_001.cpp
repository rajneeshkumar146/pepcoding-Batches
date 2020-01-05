#include <iostream>
#include <vector>
using namespace std;



int mazePath(int sr, int sc, int er, int ec, vector<vector<int>> &dp)
{
    // if (sr == er && sc == ec){
    //     dp[sr][sc]=1;
    //     return 1;
    // }

    // if (dp[sr][sc] != 0)
    //     return dp[sr][sc];
    // int count = 0;
    // for (int i=1;sr + i <= er;i++)
    //     count += mazePath(sr + i, sc, er, ec,dp);
    // for (int i=1;sc + i <= ec;i++)
    //     count += mazePath(sr, sc + i, er, ec,dp);
    // for (int i=1;sc + i <= ec&& sr+i<=er;i++)
    //     count += mazePath(sr+i, sc + i, er, ec,dp);
    // dp[sr][sc] = count;
    // return count;

    dp[er][ec]=1;
    for(int i=er;i>=sr;i--){
        for(int j=ec;j>=sc;j--){
            
            for(int k=1;k+i<=er;k++)
             dp[i][j]+=dp[i+k][j];

             
            for(int k=1;k+j<=ec;k++)
             dp[i][j]+=dp[i][j+k];

             
            for(int k=1;k+i<=er && k+j<=ec;k++)
             dp[i][j]+=dp[i+k][j+k];
        }
    }

    return dp[0][0];




}


int climbingJumpsFor6(int sp, int ep, vector<int> &dp)
{
    // if(sp==ep){
    //   dp[sp]=1;
    //   return 1;
    // }
    // if(dp[sp]!=-1) return dp[sp];

    // int count=0;
    // for(int dice=1;dice<=6 && sp+dice<=ep;dice++){
    //     count+=climbingJumpsFor6(sp+dice,ep,dp);
    //  }

    //  dp[sp]=count;
    //  return count;

    dp[ep] = 1;
    for (int i = ep - 1; i >= sp; i--)
    {
        for (int dice = 1; dice <= 6 && dice + i <= ep; dice++)
        {
            dp[i] += dp[dice + i];
        }
    }
    return dp[0];
}

int fib_rec(int n, vector<int> &dp)
{
    // if(n<=1){
    //     dp[n]=n;
    //     return n;
    // }
    // if(dp[n]!=-1) return dp[n];
    // cout<<"pre: "<<n<<endl;
    // int ans=fib_rec(n-1,dp);
    // cout<<"In: "<<n<<endl;
    // ans+=fib_rec(n-2,dp);
    // dp[n]=ans;
    // cout<<"post: "<<n<<endl;
    // return ans;

    dp[0] = 0;
    dp[1] = 1;
    for (int i = 2; i <= n; i++)
    {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
}

void display(vector<int> &dp)
{
    for (int i : dp)
        cout << i << " ";
}

void display2D(vector<vector<int>> &dp)
{
    for(vector<int> ar: dp)
    for (int i : ar)
        cout << i << " ";
}

void solve()
{
    // vector<int> dp(11, 0);
    // fib_rec(5,dp);
    // cout << climbingJumpsFor6(0, 10, dp) << endl;
    vector<vector<int>> dp(3,vector<int>(3,0));
    cout<<mazePath(0,0,2,2,dp)<<endl;
    display2D(dp);

}

int main()
{
    solve();
    return 0;
}