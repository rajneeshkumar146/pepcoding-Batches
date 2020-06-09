#include <iostream>
#include <vector>

using namespace std;

void input(vector<vector<int>> &arr)
{
    for (int row = 0; row < arr.size(); row++)
    {
        for (int col = 0; col < arr[0].size(); col++)
        {
            cin >> arr[row][col];
        }
    }
}

void print(vector<vector<int>> &arr)
{
    for (vector<int> ar : arr)
    {
        for (int i : ar)
        {
            cout << i << " ";
        }
        cout << endl;
    }

    // for (vector<vector<int>> arr : arrr)
    // {
    //     for (vector<int> ar : arr)
    //     {
    //         for (int i : ar)
    //         {
    //             cout << i << " ";
    //         }
    //         cout << endl;
    //     }
    // }
}

int maxin2D(vector<vector<int>> &arr)
{

    int max_ = arr[0][0];
    for (vector<int> ar : arr)
    {
        for (int i : ar)
        {
            if (i > max_)
            {
                max_ = i;
            }
        }
    }

    return max_;
}

void exitpoint(vector<vector<int>> &mat)
{
    int r = 0;
    int c = 0;
    int dir = 0;

    cout << "Row and col: ";

    while (true)
    {
        dir = (dir + mat[r][c]) % 4;

        if (dir == 0)
        {
            c++;
            if (c == mat[0].size())
            {
                cout << r << ", " << (c - 1) << endl;
                break;
            }
        }
        else if (dir == 1)
        {
            r++;
            if (r == mat.size())
            {
                cout << (r - 1) << ", " << c << endl;
                break;
            }
        }
        else if (dir == 2)
        {
            c--;
            if (c == -1)
            {
                cout << r << ", " << (c + 1) << endl;
                break;
            }
        }
        else
        {
            r--;
            if (r == -1)
            {
                cout << (r + 1) << ", " << c << endl;
                break;
            }
        }
    }
}

void addArray(vector<int>& arr1,vector<int>& arr2){
     int size=arr1.size()>arr2.size()?arr1.size()+1:arr2.size()+1;
     int carry=0;
     vector<int> res(size,0);

     int i=arr1.size()-1;
     int j=arr2.size()-1;
     int k=res.size()-1;
     

     while(i>=0 || j>=0 || carry!=0){
         int ans=carry;
         if(i>=0) ans+=arr1[i];
         if(j>=0) ans+=arr2[j];

         int rem=ans%10;
         carry=ans/10;
         res[k]=rem;
         
         k--;
         i--;
         j--;

     }
}

void transpose(vector<vector<int>>& arr){
    for(int row=0;row<arr.size();row++){
       for(int col=row;col<arr[0].size();col++){
            swap(arr[row][col],arr[col][row]);
       }
    }
}

void swapCol(vector<vector<int>>& arr){

    int c1=0;
    int c2=arr[0].size()-1;
    while(c1<c2){
       
     for(int r=0;r<arr.size();r++){
         swap(arr[r][c1],arr[r][c2]);
     }

       c1++;
       c2--;
    }

}





int main(int args, char **argv)
{
    vector<vector<int>> mat = {{0, 0, 0, 0, 1},
                               {1, 0, 0, 1, 0},
                               {0, 0, 0, 0, 0},
                               {0, 1, 0, 1, 0},
                               {1, 0, 0, 0, 1}};

    exitpoint(mat);
}
