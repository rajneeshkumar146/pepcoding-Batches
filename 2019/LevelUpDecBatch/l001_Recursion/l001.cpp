#include <iostream>
#include <vector>
using namespace std;

void printDecreasing(int n)
{
    if (n == 0)
        return;

    cout << n << endl;
    printDecreasing(n - 1);
}

void printIncreasing(int n)
{
    if (n == 0)
        return;

    printIncreasing(n - 1);
    cout << n << endl;
}

// n = 5.
int printTreePath(int n)
{
    if (n == 1 || n == 0)
    {
        cout << "base: " << n << endl;
        return n;
    }
    int ans = 0;

    cout << "pre: " << n << endl;
    ans += printTreePath(n - 1);

    cout << "In: " << n << endl;

    ans += printTreePath(n - 2);
    cout << "post: " << n << endl;

    return ans + 3;
}

void display(vector<int> &arr, int idx)
{
    if (idx == arr.size())
        return;

    cout << arr[idx] << " ";
    display(arr, idx + 1);
}

int maximumEle(vector<int> &arr, int idx)
{
    if (idx == arr.size())
        return -(int)1e9;
    return max(arr[idx], maximumEle(arr, idx + 1));
}

int minimumEle(vector<int> &arr, int idx)
{
    if (idx == arr.size())
        return (int)1e9;
    return min(arr[idx], minimumEle(arr, idx + 1));
}

int findData(vector<int> &arr, int idx, int data)
{
    if (idx == arr.size())
        return -1;
    if (arr[idx] == data)
        return idx;
    return findData(arr, idx + 1, data);
}

vector<string> subsequence(string str, int idx)
{
    if (idx == str.length())
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    char ch = str[idx];

    vector<string> smallAns = subsequence(str, idx + 1);
    vector<string> myAns;

    for (string s : smallAns)
    {
        myAns.push_back(s);
        myAns.push_back(ch + s);
    }

    return myAns;
}

int subseq(string ques,string ans){
    if(ques.length() == 0){
        cout<< ans << endl;
        return 1;
    }

    int count = 0;
    count += subseq(ques.substr(1),ans + ques[0]);
    count += subseq(ques.substr(1),ans);

    return count;
}

int subseq(string ques,int idx,string ans){
    if(idx == ques.length()){
        cout<< ans << endl;
        return 1;
    }

    int count = 0;
    count += subseq(ques,idx + 1,ans + ques[idx]);
    count += subseq(ques, idx + 1,ans);

    return count;
}


vector<string> codes = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};

vector<string> getKPC(string str, int idx)
{
    if (idx == str.length())
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    int indexOfCode = str[idx] - '0';

    vector<string> smallAns = getKPC(str, idx + 1);
    vector<string> myAns;

    string code = codes[indexOfCode];
    for (int i = 0; i < code.length(); i++)
    {
        for (string s : smallAns)
        {
            myAns.push_back(code[i] + s);
        }
    }

    return myAns;
}

int main()
{
    return 0;
}