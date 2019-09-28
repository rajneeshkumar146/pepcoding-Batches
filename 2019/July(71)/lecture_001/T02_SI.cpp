#include<iostream>
using namespace std;


int main(int args,char** argv){
 
    int p,r,t;
    cout<<"please input value of p ,t and r"<<endl;
    cin>>p;
    cin>>t;
    cin>>r;

    int si=0;
    si=(p*t*r)/100;
    cout<<"SI is: "<<si;

    return 0;
}