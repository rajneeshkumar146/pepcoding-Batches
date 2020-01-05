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
    vector<bool> vis(26, false);

    for (int i = 0; i < str.length(); i++)
    {
        int k = str[i] - 'a';
        if (!vis[k])
        {
            vis[k] = true;
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
    int vis = 0;

    for (int i = 0; i < str.length(); i++)
    {
        int k = str[i] - 'a';
        int mask = 1 << k;
        if ((vis & mask) == 0)
        {
            vis |= mask;
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
    cout << permu3("aba", "") << endl;
}

//====================================

int permuInfi(vector<int> &arr, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
            count += permuInfi(arr, tar - arr[i], ans + to_string(arr[i]) + " ");
    }
    return count;
}

int permuWithoutRepe(vector<int> &arr, vector<bool> &vis, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        if (!vis[i] && tar - arr[i] >= 0)
        {
            vis[i] = true;
            count += permuWithoutRepe(arr, vis, tar - arr[i], ans + to_string(arr[i]) + " ");
            vis[i] = false;
        }
    }
    return count;
}

int combiInfi(vector<int> &arr, int idx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = idx; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
            count += combiInfi(arr, i, tar - arr[i], ans + to_string(arr[i]) + " ");
    }
    return count;
}

int combiWithoutRepe(vector<int> &arr, int idx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;

    for (int i = idx; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
            count += combiWithoutRepe(arr, i + 1, tar - arr[i], ans + to_string(arr[i]) + " ");
    }
    return count;
}

int combiInfiSubseq(vector<int> &arr, vector<bool> &vis, int idx, int tar, string ans)
{
    if (tar == 0 || idx == arr.size())
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (!vis[idx] && tar - arr[idx] >= 0)
    {
        vis[idx] = true;
        count += combiInfiSubseq(arr, vis, idx + 1, tar - arr[idx], ans + to_string(idx) + " ");
        vis[idx] = false;
    }
    count += combiInfiSubseq(arr, vis, idx + 1, tar, ans);

    return count;
}

int subset(vector<int> &arr, int idx, string ans)
{

    cout << ans << endl;
    if (idx == arr.size())
    {
        return 1;
    }
    int count = 0;

    for (int i = idx; i < arr.size(); i++)
    {
        count += subset(arr, i + 1, ans + to_string(arr[i]) + " ");
    }
    return count;
}

void combiPermu()
{
    // vector<int> arr{2, 3, 5, 7};
    vector<int> arr(5, 1);
    vector<bool> vis(arr.size(), false);
    int tar = 3;
    // cout << permuInfi(arr, tar, "") << endl;
    // cout << permuWithoutRepe(arr, vis, tar, "") << endl;

    // cout<<combiInfi(arr,0,tar,"")<<endl;
    // cout << combiWithoutRepe(arr, 0, tar, "") << endl;

    cout << combiInfiSubseq(arr, vis, 0, tar, "") << endl;

    // vector<int> arr{1, 2, 3};
    // subset(arr, 0, "");
}

//=====================================

int queenCombi(int box, int idx, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < box; i++)
    {
        count += queenCombi(box, i + 1, qpsf + 1, tnq, ans + "b" + to_string(i) + "q" + to_string(qpsf));
    }
    return count;
}

int queenPermu(vector<bool> &box, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < box.size(); i++)
    {
        if (!box[i])
        {
            box[i] = true;
            count += queenPermu(box, qpsf + 1, tnq, ans + "b" + to_string(i) + "q" + to_string(qpsf));
            box[i] = false;
        }
    }
    return count;
}

void queens()
{
     vector<bool> box(5,false);
    // cout << queenCombi(5, 0, 0, 3, "") << endl;
    cout<<queenPermu(box,0,3,"")<<endl;
}

void solve()
{
    // basicQues();
    // combiPermu();
    queens();
}

int main()
{
    solve();
    return 0;
}