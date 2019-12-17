#include <iostream>
#include <vector>
using namespace std;

void subseq(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << endl;
        return;
    }

    char ch = ques[0];
    subseq(ques.substr(1), ans + ch);
    subseq(ques.substr(1), ans);
}

void subseq_01(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << endl;
        return;
    }

    char ch = ques[0];
    int ch_ = ques[0];
    subseq_01(ques.substr(1), ans + ch);
    subseq_01(ques.substr(1), ans);
    subseq_01(ques.substr(1), ans + to_string(ch_));
}

void permutation(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << endl;
        return;
    }

    // int arr[26] = {0};
    int arr = 0;

    for (int i = 0; i < ques.length(); i++)
    {
        int mask = 1 << (ques[i] - 'a');
        // if (arr[ques[i] - 'a'] == 0)
        if ((arr & mask) == 0)
        {
            // arr[ques[i] - 'a'] = 1;
            arr |= mask;
            string nques = ques.substr(0, i) + ques.substr(i + 1);
            permutation(nques, ques[i] + ans);
        }
    }
}

string words[] = {".,:;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wx", "yz", "@$&", "-_%"};

int keyPad(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int idx = ques[0] - '0';
    int count = 0;
    string word = words[idx];

    for (int i = 0; i < word.length(); i++)
    {
        count += keyPad(ques.substr(1), ans + word[i]);
    }

    if (ques.length() >= 2)
    {
        idx = (ques[0] - '0') * 10 + (ques[1] - '0');
        if (idx >= 10 && idx < 12)
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

//==========================================

int targetSum(vector<int> &arr, int idx, int tar, string ans)
{
    if (idx == arr.size() || tar == 0)
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    for (int i = idx; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
        {
            count += targetSum(arr, i + 1, tar - arr[i],
                               ans + to_string(arr[i]) + " ");
        }
    }

    return count;
}

int targetSum_02(vector<int> &arr, int idx, int tar, string ans)
{
    if (idx == arr.size() || tar == 0)
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;

    if (tar - arr[idx] >= 0)
        count += targetSum_02(arr, idx + 1, tar - arr[idx], ans + to_string(arr[idx]) + " ");

    count += targetSum_02(arr, idx + 1, tar, ans);
    return count;
}

int equiSet(vector<int>& arr,int idx,int set1,int set2,string ans1,string ans2){
    if(idx==arr.size()){
        if(set1==set2){
            cout<<ans1<<" = "<<ans2<<endl;
            return 1;
        }
        return 0;
    }

    int count=0;

     count+=equiSet(arr,idx+1,set1+arr[idx],set2,ans1+to_string(arr[idx])+" ",ans2);
     count+=equiSet(arr,idx+1,set1,set2+arr[idx],ans1,ans2+to_string(arr[idx])+" ");

    return count;


}

void setType()
{
    vector<int> arr = {10, 20, 30, 40, 50, 60, 70};
    // cout<<targetSum(arr,0,100,"")<<endl;
    // cout << targetSum_02(arr, 0, 100, "") << endl;

    cout<<equiSet(arr,1,10,0,"10 ","")<<endl;
}

void basic()
{
    // subseq("abc","");
    permutation("aba", "");
}

//=====================================

int mazePath(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    if (sr + 1 <= er)
    {
        count += mazePath(sr + 1, sc, er, ec, ans + "V");
    }

    if (sc + 1 <= ec)
    {
        count += mazePath(sr, sc + 1, er, ec, ans + "H");
    }
    if (sc + 1 <= ec && sr + 1 <= er)
    {
        count += mazePath(sr + 1, sc + 1, er, ec, ans + "D");
    }

    return count;
}

int mazePath_01(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 1; sr + i <= er; i++)
    {
        count += mazePath_01(sr + i, sc, er, ec, ans + "V" + to_string(i));
    }

    for (int i = 1; sc + i <= ec; i++)
    {
        count += mazePath_01(sr, sc + i, er, ec, ans + "H" + to_string(i));
    }

    for (int i = 1; sc + i <= ec && sr + i <= er; i++)
    {
        count += mazePath_01(sr + i, sc + i, er, ec, ans + "D" + to_string(i));
    }

    return count;
}

void path()
{
    // mazePath(0,0,2,2,"");
    cout << mazePath_01(0, 0, 2, 2, "") << endl;
}

void solve()
{
    // basic();
    // path();
    setType();
}

int main()
{
    solve();
    return 0;
}