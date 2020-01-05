#include <iostream>
#include <vector>

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
int keyPad(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    int idx1 = ques[0] - '0';

    //  if(idx1==0){
    //      count+=keyPad(ques.substr(1),ans);
    //      return;
    //  }

    string word = words[idx1];

    for (int i = 0; i < word.length(); i++)
    {
        count += keyPad(ques.substr(1), ans + word[i]);
    }

    if (ques.length() >= 2)
    {
        int idx = idx1 * 10 + (ques[1] - '0');
        if (idx >= 10 && idx <= 11)
        {
            word = words[idx];

            for (int i = 0; i < word.length(); i++)
            {
                count += keyPad(ques.substr(2), ans + word[i]);
            }
        }
    }

    return count;
}

int permu(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < str.length(); i++)
    {
        string nstr = str.substr(0, i) + str.substr(i + 1);
        count += permu(nstr, ans + str[i]);
    }
    return count;
}

int permu2(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    vector<bool> vis(26,false);

    for (int i = 0; i < str.length(); i++)
    {
        int k=str[i] - 'a';
        if (!vis[k])
        {
            vis[k]=true;
            string nstr = str.substr(0, i) + str.substr(i + 1);
            count += permu2(nstr, ans + str[i]);
        }
    }
    return count;
}


int permu3(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    int vis=0;

    for (int i = 0; i < str.length(); i++)
    {
        int k=str[i] - 'a';
        int mask=1<<k;
        if ((vis&mask)==0)
        {
            vis|=mask;
            string nstr = str.substr(0, i) + str.substr(i + 1);
            count += permu3(nstr, ans + str[i]);
        }
    }
    return count;
}


void basicQues()
{
    // cout<<subseq("abc", "")<<endl;
    // cout << keyPad("10110", "") << endl;
    // cout << permu("aba", "") << endl;
    // cout<<permu2("aba","")<<endl;
    cout<<permu3("aba","")<<endl;
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