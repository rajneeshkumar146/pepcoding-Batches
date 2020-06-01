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

void printIncDec(int a, int b)
{
    if (a == b + 1)
        return;
    cout << a << " hi " << endl;
    printIncDec(a + 1, b);
    cout << a << " bye " << endl;
}

int fact(int n)
{
    // if(n<=1) return 1;
    // return fact(n-1)*n;
    return n <= 1 ? 1 : fact(n - 1) * n;
}

int power(int a, int b)
{
    if (b == 0)
        return 1;
    return power(a, b - 1) * a;
}

int powerBtr(int a, int b)
{
    if (b == 0)
        return 1;
    int ans = powerBtr(a, b / 2);
    ans *ans;
    return b % 2 != 0 ? ans * a : ans;
}

void display(vector<int> &arr, int idx)
{
    if (idx == arr.size())
        return;

    cout << arr[idx] << " ";
    display(arr, idx + 1);
}

int maximum(vector<int> &arr, int idx)
{
    if (idx == arr.size() - 1)
        return arr[idx];
    int smallMax = maximum(arr, idx + 1);
    return max(smallMax, arr[idx]); //for Java: Math.max(smallMax,arr[idx]);
}

int minimum(vector<int> &arr, int idx)
{
    if (idx == arr.size() - 1)
        return arr[idx];
    int smallMin = minimum(arr, idx + 1);
    return min(smallMin, arr[idx]); //for Java: Math.min(smallMax,arr[idx]);
}

bool find(vector<int> &arr, int idx, int data) // for Java: bool is boolean
{
    if (idx == arr.size())
        return false;
    if (arr[idx] == data)
        return true;
    return find(arr, idx + 1, data);
}

//====================================================================================

vector<string> subseq(string str)
{
    if (str.length() == 0)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }
    char ch = str[0];            // str.charAt(0);
    string nstr = str.substr(1); // str.substring(1);
    vector<string> recAns = subseq(nstr);

    vector<string> myAns;
    for (string s : recAns)
    {
        myAns.push_back(s);
        myAns.push_back(ch + s);
    }

    return myAns;
}

int subseq(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    count += subseq(str.substr(1), ans + str[0]);
    count += subseq(str.substr(1), ans);

    return count;
}

void set3()
{
    vector<string> ans; 
    // ans=subseq("abc");
    cout<<subseq("abc","")<<endl;



    for (string s : ans)
        cout << s << " ";
}

void set2()
{
    // vector<int> arr(10,0);
    vector<int> arr = {1, 45, 56, 2, 34, 23, -9, 56, 89, 898, 5};
    display(arr, 0);
    cout << endl;

    cout << maximum(arr, 0) << endl;
    cout << minimum(arr, 0) << endl;
}

void set1()
{
    // cout << "Hi coders!"<<endl;
    // int a,b;
    // cin>>a>>b;
    // printInc(a,b);
    // printIncDec(a,b);

    // int n; cin>>n;
    // cout<<fact(n)<<endl;

    int a, b;
    cin >> a >> b;
    cout << power(a, b) << endl;
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