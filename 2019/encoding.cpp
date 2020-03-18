#include<iostream>
#include<map>
using namespace std;
void encodings(string ques,string asf){
    if(ques.size()==0){
        cout<<asf<<endl;
        return;
    }
    char ch = ques[0];
    int num = ch-'0';
    string ros = ques.substr(1);
    if(num>=1 && num<=9){
        char ans = char(num+'a'-1);
        encodings(ros,asf+ans);
    }
    
    
    if(ques.size()>1){
        char ch2 = ques[1];
        int num1=ch2-'0';
        string ros1 = ques.substr(2);
        int num3 = num*10+num1;
        if(num3>=10 && num3<=26){
            char ch3 = char(num3+'a'-1);
            encodings(ros1,asf+ch3);
        }
    }
}


int main(int argc,char** argv){
    encodings("123","");

}