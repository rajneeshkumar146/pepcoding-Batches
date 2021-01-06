#include <iostream>
#include <vector>

using namespace std;

bool isPalindrome(string str)
{
    int si = 0, ei = str.length() - 1;
    while (si < ei)
    {
        if (str[si++] != str[ei--])
            return false;
    }

    return true;
}

void getAllSubstrings(string str)
{
    for (int i = 0; i < str.length(); i++)
    {
        for (int len = 1; i + len <= str.length(); len++)
        {
            cout << (str.substr(i, len));
        }
    }
}

string compression1(string str)
{
    if (str.length() <= 1)
        return str;
    string ans = "";
    ans += str[0];

    for (int i = 1; i <= str.length(); i++)
    {
        int count = 1;
        while (i < str.length() && str[i - 1] == str[i])
        {
            count++;
            i++;
        }

        // if (count != 1)
        //     ans += to_string(count);
        if (i < str.length())
            ans += str[i];
    }

    return ans;
}

string compression2(string str)
{
    if (str.length() <= 1)
        return str;
    string ans = "";
    ans += str[0];

    for (int i = 1; i <= str.length(); i++)
    {
        int count = 1;
        while (i < str.length() && str[i - 1] == str[i])
        {
            count++;
            i++;
        }

        if (count != 1)
            ans += to_string(count);
        if (i < str.length())
            ans += str[i];
    }

    return ans;
}

string diffrenceOfTwoNumber(string str)
{
    if (str.length() <= 1)
        return str;
    string ans = "";
    ans += str[0];
    for (int i = 1; i < str.length(); i++)
    {
        char ch0 = str[i - 1];
        char ch1 = str[i];
        ans += to_string(ch1 - ch0);
        ans += ch1;
    }

    return ans;
}

vector<string> subseq(string str)
{
    vector<string> ans;
    ans.push_back("");

    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        int size = ans.size();
        for (int j = 0; j < size; j++)
        {
            ans.push_back(ans[j] + ch);
        }
    }

    return ans;
}

bool isPrime(int num)
{
    for (int i = 2; i * i <= num; i++)
    {
        if (num % i == 0)
            return false;
    }

    return true;
}

void deleteNonPrime(vector<int> &al)
{
    vector<int> list;
    for (int ele : al)
    {
        if (!isPrime(ele))
            list.push_back(ele);
    }

    al.clear();
    for (int ele : list)
        al.push_back(ele);
}

int main()
{
    int n;
    cin >> n;
    vector<int> arr(n, 0);
    for (int i = 0; i < n; i++)
        cin >> arr[i];
    deleteNonPrime(arr);

    string str = "[";
    int idx = 0;
    for (int ele : arr){
        str += to_string(ele);
        if(idx < arr.size() - 1);
        
        str += ", ";
        idx++;
    }
    
    cout << str + "]";

    return 0;
}
