#include <iostream>
#include <vector>
using namespace std;

void printIncreasing(int a, int b)
{
    if (a == b + 1)
        return;

    cout << a << " ";
    printIncreasing(a + 1, b);
}

void printDecreasing(int a, int b)
{
    if (a == b + 1)
        return;
    printDecreasing(a + 1, b);
    cout << a << " ";
}

void printIncreasingDecreasing(int a, int b)
{
    if (a == b + 1)
        return;
    cout << a << " hi" << endl;
    printIncreasingDecreasing(a + 1, b);
    cout << a << " bye" << endl;
}

void printIDoddEven(int a, int b)
{
    if (a == b + 1)
        return;
    if (a % 2 == 0)
        cout << a << " ";
    printIDoddEven(a + 1, b);
    if (a % 2 != 0)
        cout << a << " ";
}

int fibo(int n)
{
    if (n <= 1)
        return n;
    return fibo(n - 1) + fibo(n - 2);
}
//set2======================================================

int maximum(vector<int> &arr, int vidx)
{
    if (vidx == arr.size() - 1)
        return arr[vidx];
    return max(arr[vidx], maximum(arr, vidx + 1));
}

int minimum(vector<int> &arr, int vidx)
{
    if (vidx == arr.size() - 1)
        return arr[vidx];
    return min(arr[vidx], minimum(arr, vidx + 1));
}

bool find(vector<int> &arr, int vidx, int data)
{
    if (vidx == arr.size())
        return false;
    if (arr[vidx] == data)
        return true;
    return find(arr, vidx + 1, data);
}

//set3.===========================================================
vector<string> subseq(string str)
{
    if (str.length() == 0)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    char ch = str[0];
    string nstr = str.substr(1);

    vector<string> smallAns = subseq(nstr);
    vector<string> ans;

    for (string s : smallAns)
    {
        ans.push_back(s);
        ans.push_back(ch + s);
    }

    return ans;
}

vector<string> permutation(string str)
{
    if (str.length() == 0)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    char ch = str[0];
    string nstr = str.substr(1);

    vector<string> smallAns = permutation(nstr);
    vector<string> myAns;

    for (string s : smallAns)
        for (int i = 0; i <= s.size(); i++)
        {
            string cut = s.substr(0, i) + ch + s.substr(i);
            myAns.push_back(cut);
        }

    return myAns;
}

int permutation(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        string nstr = str.substr(0, i) + str.substr(i + 1);
        count += permutation(nstr, ans + ch);
    }
    return count;
}

int permutationUnique(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    vector<bool> vis(26, 0);
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        if (!vis[ch - 'a'])
        {
            vis[ch-'a']=true;
            string nstr = str.substr(0, i) + str.substr(i + 1);
            count += permutationUnique(nstr, ans + ch);
        }
    }
    return count;
}

void set3()
{
    // cout << permutation("aba", "") << endl;
    cout << permutationUnique("aba", "") << endl;
}

void set2()
{
    int n;
    cin >> n;
    vector<int> arr(n, 0);
    for (int i = 0; i < n; i++)
        cin >> arr[i];
}

void set1()
{
    int a, b;
    cin >> a >> b;
    // printIncreasing(a,b);
    // printDecreasing(a, b);
    // printIncreasingDecreasing(a,b);
    printIDoddEven(a, b);
}

void solve()
{
    // set1();
    // set2();
    set3();
}

int main()
{
    solve();
    return 0;
}