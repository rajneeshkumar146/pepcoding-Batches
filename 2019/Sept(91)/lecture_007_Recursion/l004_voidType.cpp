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
    int maping = 0;

    for (int i = 0; i < ques.length(); i++)
    {
        char ch = ques[i];
        if ((maping & (1 << (ch - 'a'))) == 0)
        {
            maping |= (1 << (ch - 'a'));
            string roq = ques.substr(0, i) + ques.substr(i + 1);
            count += permutation(roq, ans + ch);
        }
    }

    return count;
}

int uniqueInK(vector<int> &arr, int k)
{
    // vector<int> bits(32,0);
    int ans = 0;

    for (int i = 0; i < 32; i++)
    {
        int count = 0;

        for (int ele : arr)
        {
            int mask = (1 << i);
            if ((ele & mask) != 0)
            {
                count++;
            }
        }

        if (count % k != 0)
        {
            ans |= (1 << i);
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

    vector<int> arr = {2, 2, 3, 4, 3, 2, 3};
    cout << uniqueInK(arr, 3);
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

//======================================================

int equiSet(vector<int> &arr, int vidx, int set1, int set2, string ans1, string ans2)
{
    if (vidx == arr.size())
    {
        if (set1 == set2)
        {
            cout << ans1 << " = " << ans2 << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    // count += equiSet(arr, vidx + 1, set1, set2, ans1, ans2);
    count += equiSet(arr, vidx + 1, set1 + arr[vidx], set2, ans1 + to_string(arr[vidx]) + " ", ans2);
    count += equiSet(arr, vidx + 1, set1, set2 + arr[vidx], ans1, ans2 + to_string(arr[vidx]) + " ");
    return count;
}

int coinChange_permu01(vector<int> &arr, int vidx, int target, string ans)
{

    if (target == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int ele : arr)
    {
        if (target - ele >= 0)
        {
            count += coinChange_permu01(arr, vidx, target - ele, ans + to_string(ele) + " ");
        }
    }
    return count;
}

int coinChange_permu02(vector<int> &arr, vector<bool> &isDone, int target, string ans)
{

    if (target == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        int ele = arr[i];
        if (target - ele >= 0 && !isDone[i])
        {
            isDone[i] = true;
            count += coinChange_permu02(arr, isDone, target - ele, ans + to_string(ele) + " ");
            isDone[i] = false;
        }
    }

    return count;
}

int coinChange_combi01(vector<int> &arr, int vidx, int target, string ans)
{

    if (target == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = vidx; i < arr.size(); i++)
    {
        if (target - arr[i] >= 0)
        {
            count += coinChange_combi01(arr, i, target - arr[i], ans + to_string(arr[i]) + " ");
        }
    }
    return count;
}

int coinChange_combi02(vector<int> &arr, int vidx, int target, string ans)
{

    if (target == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = vidx; i < arr.size(); i++)
    {
        if (target - arr[i] >= 0)
        {
            count += coinChange_combi02(arr, i + 1, target - arr[i], ans + to_string(arr[i]) + " ");
        }
    }
    return count;
}

int coinChange_combi03(vector<int> &arr, int vidx, int target, string ans)
{

    if (vidx == arr.size() || (target == 0 && ans.length() != 0))
    {
        if (target == 0 && ans.length() != 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (target - arr[vidx] >= 0)
    {
        count += coinChange_combi03(arr, vidx, target - arr[vidx], ans + to_string(arr[vidx]) + " ");
    }
    count += coinChange_combi03(arr, vidx + 1, target, ans);

    return count;
}

void combiAndPermu()
{
    // vector<int> ar = {10, 20, 30, 40, 50, 60, 70};
    // cout << equiSet(ar, 0, 0, 0, "", "");

    vector<int> arr = {2, 3, 5, 7};
    // vector<bool> isDone(arr.size(), false);
    //  cout<<coinChange_permu01(arr,0,10,"")<<endl;
    // cout << coinChange_permu02(arr, isDone, 10, "") << endl;

    //  cout<<coinChange_combi01(arr,0,10,"")<<endl;
    // cout << coinChange_combi02(arr, 0, 10, "") << endl;
    cout << coinChange_combi03(arr, 0, 10, "") << endl;
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
    // basicQues();
    // pathType();
    combiAndPermu();
}

int main(int args, char **argv)
{
    solve();
    return 0;
}