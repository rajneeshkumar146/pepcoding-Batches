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

int permutation(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    // vector<bool> maping(26, false);
    int maping=0;

    for (int i = 0; i < ques.length(); i++)
    {
        char ch = ques[i];
        if ((maping & (1<<(ch - 'a')))== 0)
        {
            maping |=(1<<(ch - 'a'));
            string roq = ques.substr(0, i) + ques.substr(i + 1);
            count += permutation(roq, ans + ch);
        }
    }

    return count;
}

int uniqueInK(vector<int>& arr,int k){
    // vector<int> bits(32,0);
     int ans=0;

    for(int i=0;i<32;i++){   
        int count=0;

      for(int ele : arr){
            int mask=(1<<i);
            if((ele&mask)!=0){
                count++;
            }
        }

        if(count%k!=0){
            ans|=(1<<i);
        }


    }
     
    // for(int ele:bits){
    //     cout<<ele<<" ";
    // }

    // for(int i=0;i<32;i++){
    //     if(bits[i]%k != 0){
    //        ans|=(1<<i);
    //     }
    // }


    return ans;
}

void basicQues()
{
    // string str;
    // cin >> str;
    // String str=scn.nextLine();

    // subseq("abc", "");
    // removeHi("hhhhihihihihiiiiihihiihhihih","");
    // removeDupli(str, "");
    // cout << permutation("abab", "") << endl;

    vector<int> arr={2,2,3,4,3,2,3};
    cout<<uniqueInK(arr,3);
}

//======================================================

int mazePathSimple(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    if (sr + 1 <= er)
        count += mazePathSimple(sr + 1, sc, er, ec, ans + "V");
    if (sc + 1 <= ec)
        count += mazePathSimple(sr, sc + 1, er, ec, ans + "H");

    return count;
}

int mazePathMulti(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int jump = 1; jump + sr <= er; jump++)
    {
        count += mazePathMulti(sr + jump, sc, er, ec, ans + "V" + to_string(jump));
    }

    for (int jump = 1; jump + sc <= ec; jump++)
    {
        count += mazePathMulti(sr, sc + jump, er, ec, ans + "H" + to_string(jump));
    }

    for (int jump = 1; jump + sr <= er && jump + sc <= ec; jump++)
    {
        count += mazePathMulti(sr + jump, sc + jump, er, ec, ans + "D" + to_string(jump));
    }

    return count;
}

int boardPath(int s, int d, string ans)
{
    if (s == d)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int dice = 1; dice <= 6 && s + dice <= d; dice++)
    {
        count += boardPath(s + dice, d, ans + to_string(dice));
    }

    return count;
}

void pathType()
{
    // cout << mazePathSimple(0, 0, 2, 2, "") << endl;
    // cout<<mazePathMulti(0,0,3,3,"")<<endl;
    cout << boardPath(0, 10, "") << endl;
}

//======================================================

void solve()
{
    basicQues();
    // pathType();
}

int main(int args, char **argv)
{
    solve();
    return 0;
}