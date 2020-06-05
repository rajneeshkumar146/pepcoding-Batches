#include <iostream>
#include <vector>

using namespace std;

void printIncr(int a,int b){
    if(a==b+1) return;    
    cout<<a<<" ";
    printIncr(a+1,b);
}

void printDecre(int a,int b){
    if(a==b+1) return;    
    printDecre(a+1,b);
    cout<<a<<" ";
}

void printIncreDecre(int a,int b){
    if(a==b+1) return;    
    cout<<a<<" ";
    printIncreDecre(a+1,b);
    cout<<a<<" ";
}


void printIncreOddDecreEven(int a,int b){
    if(a==b+1) return;    
    if(a%2!=0)cout<<a<<" ";
    printIncreOddDecreEven(a+1,b);
    if(a%2==0)cout<<a<<" ";
}

void display(vector<int>& arr,int idx){
   if(idx==arr.size()) return;
   cout<<arr[idx]<<" ";
   display(arr,idx+1);
}

vector<string> subseq(string str){
    if(str.length()==0){
        vector<string> base;
        base.push_back("");
        return base;
    }

    char ch=str[0]; //Java: str.charAt(0);
    vector<string> recAns=subseq(str.substr(1));  // Java: str.substring(1);
    
    vector<string> myAns;
    for(string s: recAns){
        myAns.push_back(s);
       myAns.push_back(ch+s);
    }

    return myAns;
}

int subseq(string str,string ans){
   if(str.length()==0){
       cout<<ans<<endl;
       return 1;
   }

    char ch = str[0];
    string nstr=str.substr(1);
    int count=0;

    count+=subseq(nstr,ans);
    count+=subseq(nstr,ans+ch);
    
    return count;
}

void set3(){
   
}

void set2(){
    vector<int> arr{1,23,45,67,3,23,6,8,43,567,2,457,584,6346,567973,523};
    display(arr,0);
}

void set1()
{
    // int a;
    // cin >> a;

    // cout << "Hello guys -> ";
    // cout << a << endl;
    printIncreOddDecreEven(1,10);
}



void solve()
{
    set1();
}

int main()
{
    solve();
    return 0;
}