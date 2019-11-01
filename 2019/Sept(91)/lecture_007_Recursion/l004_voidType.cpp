#include <iostream>
#include <vector>
#include <string>
#include <climits>

using namespace std;

//======================================================
void subseq(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << " ";
        return;
    }

    char ch = ques[0];
    string roq = ques.substr(1);

    subseq(roq, ans + ch);
    subseq(roq, ans);
}

void removeHi(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans;
        return;
    }

    if (ques.length() > 1 && ques[0] == 'h' && ques[1] == 'i')
        removeHi(ques.substr(2), ans);
    else
        removeHi(ques.substr(1), ans + ques[0]);
}

void removeDupli(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans;
        return;
    }

    if (ques[0] == ans[ans.length() - 1])
        removeDupli(ques.substr(1), ans);
    else
        removeDupli(ques.substr(1), ans + ques[0]);
}

void compression(string ques, string ans, int count)
{
    if (ques.length() == 0)
    {
        cout << ans;
        return;
    }

    char ch = ques[0];
    string roq = ques.substr(1);
    if (roq.length() != 0)
    {
        if (ch == roq[0])
            compression(roq, ans, count + 1);
        else
        {
            if (count > 1)
                compression(roq, ans + ch + to_string(count), 1);
            else
                compression(roq, ans + ch, 1);
        }
    }
    else
    {
        if (count > 1)
            compression(roq, ans + ch + to_string(count), 1);
        else
            compression(roq, ans + ch, 1);
    }
}

void basicQues()
{
    string str;
    cin >> str;
    // String str=scn.nextLine();

    // subseq("abc", "");
    // removeHi("hhhhihihihihiiiiihihiihhihih","");
    removeDupli(str, "");
}

//======================================================

int mazePathSimple(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    // if(sr>er || sc>ec){
    //     cout<<"hi"<<endl;
    //     return 0;
    // }

    int count = 0;
    if (sr + 1 <= er)
        count += mazePathSimple(sr + 1, sc, er, ec, ans + "V");
    if (sc + 1 <= ec)
        count += mazePathSimple(sr, sc + 1, er, ec, ans + "H");

    return count;
}

void pathType()
{
    cout<<mazePathSimple(0, 0, 2, 2, "")<<endl;
}

//======================================================

void solve()
{
    // basicQues();
    pathType();
}

int main(int args,char** argv)
{
    // solve();
    return 0;
}