#include <iostream>
#include <vector>

using namespace std;

void printInc(int a, int b)
{
    if (a == b + 1)
        return;

    cout << a << " ";
    printInc(a + 1, b);
}

void printDec(int a, int b)
{
    if (a == b + 1)
        return;

    printDec(a + 1, b);
    cout << a << " ";
}

void printInDe(int a, int b)
{
    if (a == b + 1)
        return;

    if (a % 2 != 0)
        cout << a << " ";

    printInDe(a + 1, b);

    if (a % 2 == 0)
        cout << a << " ";
}

int power(int a, int b)
{
    return b == 0 ? 1 : power(a, b - 1) * a;
}

int powerBtr(int a, int b)
{
    if (b == 0)
        return 1;

    int val = powerBtr(a, b / 2);
    val *= val;

    return ((b & 1 == 0) ? val : val * a);
}

//=====================================

void display(vector<int> &arr, int vidx)
{
    if (vidx == arr.size())
        return;

    cout << arr[vidx] << " ";
    display(arr, vidx + 1);
}

// bool find(vector<int> &arr, int vidx, int data)
// {
//     if (vidx == arr.size())
//         return false;
//     if (arr[vidx] == data)
//         return true;
//     return find(arr, vidx + 1, data);
// }

// int firstIndex(vector<int>& arr,int vidx,int data){
//     if(vidx==arr.size()) return -1;

//     if(arr[vidx]==data) return vidx;

//     return firstIndex(arr,vidx+1,data);
// }

// int lastIndex(vector<int>& arr,int vidx,int data){
//     if(vidx==arr.size()) return -1;

//      int ans=lastIndex(arr,vidx+1,data);
//      if(ans!=-1) return ans;

//     if(arr[vidx]==data) return vidx;

// }

void subseq(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << " ";
        return;
    }

    subseq(str.substr(1), ans);
    subseq(str.substr(1), ans + str[0]);
}
vector<string> words;

vector<string> keyPad(string str, int idx)
{
    if (idx == str.length())
    {
        vector<string> base;
        base.push_back("");
        // for (int i = 0; i < word.length(); i++)
        //     base.push_back(word[i] + "");
        return base;
    }

    int ch = str[idx] - '0';
    string word = words[ch];

    vector<string> recAns = keyPad(str, idx + 1);

    vector<string> myAns;
    for (int i = 0; i < word.length(); i++)
    {
        for (string s : recAns)
        {
            myAns.push_back(s + word[i]);
        }

        // printKPC(str,idx+1,ans+word[i]);
    }

    if (str.length() >= 2)
    {
        int num = ch * 10 + (str[1] - '0');
        if (num >= 10 && num <= 11)
        {
            word = words[num];
            vector<string> recAns = keyPad(str, idx + 2);

            for (int i = 0; i < word.length(); i++)
            {
                for (string s : recAns)
                {
                    myAns.push_back(s + word[i]);
                }

                // printKPC(str,idx+2,ans+word[i])
            }
        }
    }

    return myAns;
}

int printBoardPath(int a, int n, vector<int> &moves, string ans)
{
    if (a == n)
    {
        cout << ans << endl;
        return 1;
    }
    int count=0;
    for (int i = 0; i < moves.size(); i++)
    {
        if (a + moves[i] <= n)
        {
            count+=printBoardPath(a + moves[i], n,moves, ans + to_string(moves[i]));
        }
    }
    return count;
}

void solve()
{
    // printInDe(1, 10);
    vector<int> arr = {1, 2, 20, 30, -2, 32, 324, 3, 56, 6, 67};
    // display(arr, 0);

    // subseq("abc", "");
    // cout<<lastIndex(arr,0,20);
    vector<int> moves={1,2,3,4,5,6};
    cout<<printBoardPath(0,10,moves,"");
}

int main()
{
    solve();
    return 0;
}