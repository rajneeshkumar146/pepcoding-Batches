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

//Question.============================================================

void input(vector<vector<int>>& arr){
    for(int i=0;i < arr.size();i++){
        for(int j=0;j < arr[0].size();j++){
            cin >> arr[i][j];    
        }
    }
}

void output(vector<vector<int>>& arr){
    for(int i=0;i < arr.size();i++){
        for(int j=0;j < arr[0].size();j++){
            cout << arr[i][j] << endl;    
        }
    }
}

void waveDisplay(vector<vector<int>>& arr,int row,int col){
    for(int j = 0; j < col;j++){
        if( j % 2 == 0){
            for(int i = 0;i<row;i++) cout<<arr[i][j]<<endl;
        }else{
            for(int i = row - 1;i >= 0;i--) cout<<arr[i][j]<<endl;
        }
    }
}

void waveDisplay2(vector<vector<int>>& arr,int row,int col){
    for(int i = 0; i < row;i++){
        if( i % 2 == 0){
            for(int j = 0;j<col;j++) cout<<arr[i][j]<<endl;
        }else{
            for(int j = col - 1;j >= 0;j--) cout<<arr[i][j]<<endl;
        }
    }
}

void exitPoint(vector<vector<int>>& arr,int row,int col){
    int i = 0, j = 0, d = 0; 
    while(true){
        d = (d + arr[i][j]) % 4;
        
        if(d == 0){  // East
           j++;
           if(j == col) {
               cout << i << endl << j - 1 << endl;
               break;
           }  
        }else if(d == 1){ //South
           i++;
           if(i == row){
               cout << i - 1 << endl << j << endl;
               break;
           }             
        }else if(d == 2){  //West
           j--;
           if(j == -1) {
               cout << i << endl << j + 1 << endl; 
               break;
           } 
        }else{   // North
           i--;
           if(i == -1){
               cout << i + 1 << endl << j << endl;
               break;
           } 
        }
    }
}

void printDiangonals(vector<vector<int>>& arr){
        
        int n = arr.size();
        int m = arr[0].size();
        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; i < n && j < m;i++,j++){
                cout<<(arr[i][j])<<endl;
            }
        }
    }



int main(){
    int n, m; cin >> n >> m;
    vector<vector<int>> arr(n,vector<int>(m,0));
    input(arr);
    exitPoint(arr,n,m);
   
    return 0;
}
