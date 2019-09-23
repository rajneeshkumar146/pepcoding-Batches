#include <iostream>
#include <vector>
#include <string>
using namespace std;

//basic.===================================

int subsequence(string str, string ans)
{
    if (str.size() == 0)
    {
        cout << ans << " ";
        return 1;
    }

    char ch = str[0];
    string roq = str.substr(1);
    int count = 0;

    count += subsequence(roq, ans + ch); //char want to contribute.
    count += subsequence(roq, ans);      //char didnt want to contribute.

    return count;
}

int permuation(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << " ";
        return 1;
    }

    int count = 0;
    int isSet=0;
    for (int i = 0; i < str.length(); i++)
    {
           char ch = str[i];
           int mask=1<<(ch-'a');
        if ((isSet&mask)==0)
        {
            string one = str.substr(0, i);
            string two = str.substr(i + 1);

            count += permuation(one + two, ans + ch);
            isSet|=mask;
        }
    }

    return count;
}

string codes[]={"+","abc","def","ghi","jkl","mno","pqrs","tuv","wx","yz","*","#"};

int keyPad(string ques,string ans){
if(ques.length()==0){
    cout<<ans<<" ";
    return 1;
}

char ch=ques[0];
string roq=ques.substr(1);
string code=codes[ch-'0'];

int count=0;
for(int i=0;i<code.length();i++){
     count+=keyPad(roq,ans+code[i]);
}

return count;
}


void basic()
{
    // << subsequence("abc", "") << endl;
    // cout << permuation("aaa", "") << endl;
    cout<<keyPad("235","");
}


//pathProblem.===========================
int functionCall=0;
string res="";

int mazePath(int sr,int sc,int er,int ec,string ans){
     functionCall++;
   if(sr==er && sc==ec){
        // cout<<ans<<" ";
        return 0;
    }

    int count=0;
    int lefts=0;
    int rights=0;
    int diagonals=0;
    if(sc+1<=ec){
     lefts=mazePath(sr,sc+1,er,ec,ans+"H");
    //  lefts+="H";
    }


    if(sr+1<=er){
     rights=mazePath(sr+1,sc,er,ec,ans+"V");
    //  rights+="V";
    }


    if(sr+1<=er&& sc+1<=ec){
     diagonals=mazePath(sr+1,sc+1,er,ec,ans+"D");
    //    diagonals+="D";  
    }

    // string ans_="";
    // if(ans_.length()<lefts.length()) ans_=lefts;
    // if(ans_.length()<rights.length()) ans_=rights;
    // if(ans_.length()<diagonals.length()) ans_=diagonals;
    
    return max(max(lefts,rights),diagonals)+1;
}


int mazePath_multi(int sr,int sc,int er,int ec,string ans){
    if(sr==er && sc==ec){
        cout<<ans<<" ";
        return 1;
    }

    int count=0;
    for(int i=1;sc+i<=ec;i++){
     count+=mazePath_multi(sr,sc+i,er,ec,ans+"H"+to_string(i));
    }


    for(int i=1;sr+i<=er;i++){
     count+=mazePath_multi(sr+i,sc,er,ec,ans+"V"+ to_string(i));
    }


    for(int i=1;sr+i<=er && sc+i<=ec;i++){
     count+=mazePath_multi(sr+i,sc+i,er,ec,ans+"D"+to_string(i));
    }
    
    return count;
}

int boardPath(int si,int ei,string ans){
    if(si==ei){
        cout<<ans<<endl;
        return 1;
    }

    int count=0;
    for(int dice=1;dice<=6 && si+dice<=ei;dice++){
        count+=boardPath(si+dice,ei,ans+to_string(dice));
    }

    return count;

}

void pathProblem(){
    cout<<mazePath(0,0,3,5,"")<<endl;
    cout<<functionCall<<endl;
    cout<<res<<endl;
    
    // cout<<mazePath_multi(0,0,2,2,"")<<endl;
    // cout<<boardPath(0,10,"")<<endl;

}

void solve()
{
    basic();
    // pathProblem();
}

int main(int args, char **argv)
{
    solve();
    return 0;
}