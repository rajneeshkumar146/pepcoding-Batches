#include <iostream>

using namespace std;

int subseq(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    count += subseq(str.substr(1), ans);
    count += subseq(str.substr(1), ans + str[0]);
    return count;
}

string words[] = {".,:;", "abc", "def", "ghi", "jkl", "mno", "pqrs",
                 "tuv", "wx", "yz", "@$&", "-_%"};
int keyPad(string ques,string ans){
    if(ques.length()==0){
        cout<<ans<<endl;
        return 1;
    }
    
    int count=0;
    int idx1=ques[0]-'0';
    string word=words[idx1];
     
     for(int i=0;i<word.length();i++){
         count+=keyPad(ques.substr(1),ans+word[i]);
     }
   
    return count;
}



void basicQues()
{
    // cout<<subseq("abc", "")<<endl;
    cout<<keyPad("234","")<<endl;
}

void solve()
{
    basicQues();
}

int main()
{
    solve();
    return 0;
}