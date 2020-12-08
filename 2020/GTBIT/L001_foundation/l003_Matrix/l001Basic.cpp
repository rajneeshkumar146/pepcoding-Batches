#include<iostream>
#include<vector>

using namespace std;

void test1(){
    int n,m; cin >> n >> m;
    vector<vector<int>> arr(n,vector<int>(m,0));

    for(int i =0;i<n;i++){
        for(int j=0;j<m;j++){
            cin >> arr[i][j];
        }
    }

    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            cout<<arr[i][j]<<" ";
        }
        cout<<endl;
    }
}


int main(){
    test1();
    return 0;
}
