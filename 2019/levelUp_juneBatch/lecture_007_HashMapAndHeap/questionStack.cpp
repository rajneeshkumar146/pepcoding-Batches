#include <iostream>
#include <vector>
#include <stack>
using namespace std;

//Leetcode 20
bool isValid(string s)
{
    stack<int> st;
    int n = s.length();

    for (int i = 0; i < n; i++)
    {
        if (s[i] == '(' || s[i] == '[' || s[i] == '{')
            st.push(i);
        else
        {
            if (st.size() == 0)
                return false;
            else if (s[i] == ')' && s[st.top()] != '(')
                return false;
            else if (s[i] == ']' && s[st.top()] != '[')
                return false;
            else if (s[i] == '}' && s[st.top()] != '{')
                return false;
            else
                st.pop();
        }
    }

    return st.size() == 0;
}

//Leetcode 1021
string removeOuterParentheses(string S)
{
    string str = "";

    int balanceBrac = 0;
    for (int i = 0; i < S.length(); i++)
    {
        if (S[i] == '(' && balanceBrac++ > 0)
            str += S[i];
        else if (S[i] == ')' && balanceBrac-- > 1)
            str += S[i];
    }

    return str;
}

vector<int> ngor(vector<int> &arr)
{
    stack<int> st;
    st.push(-1);

    int n = arr.size();
    vector<int> ans(n, n);

    for (int i = 0; i < n; i++)
    {
        while (st.top() != -1 && arr[st.top()] < arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }

    return ans;
}

vector<int> ngol(vector<int> &arr)
{
    stack<int> st;
    st.push(-1);

    int n = arr.size();
    vector<int> ans(n, -1);

    for (int i = n - 1; i >= 0; i--)
    {
        while (st.top() != -1 && arr[st.top()] < arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }

    return ans;
}

vector<int> nsor(vector<int> &arr)
{
    stack<int> st;
    st.push(-1);

    int n = arr.size();
    vector<int> ans(n, n);

    for (int i = 0; i < n; i++)
    {
        while (st.top() != -1 && arr[st.top()] > arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }

    return ans;
}

vector<int> ngol(vector<int> &arr)
{
    stack<int> st;
    st.push(-1);

    int n = arr.size();
    vector<int> ans(n, -1);

    for (int i = n - 1; i >= 0; i--)
    {
        while (st.top() != -1 && arr[st.top()] > arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }

    return ans;
}

//Leetcode 503
vector<int> nextGreaterElements(vector<int> &arr)
{
    stack<int> st;
    st.push(-1);

    int n = arr.size();
    vector<int> ans(n, -1);

    for (int i = 0; i < 2 * n; i++)
    {
        while (st.top() != -1 && arr[st.top()] < arr[i % n])
        {
            ans[st.top()] = arr[i % n];
            st.pop();
        }
        if (i < n)
            st.push(i);
    }

    return ans;
}

//Leetcode 921
int minAddToMakeValid(string S)
{
    stack<int> st;

    int n = S.length();
    for (int i = 0; i < n; i++)
    {
        if (st.size() != 0 && S[st.top()] == '(' && S[i] == ')')
            st.pop();
        else
            st.push(i);
    }

    return st.size();
}

int minAddToMakeValid_02(string s)
{
    int openingBracketReq = 0;
    int closingBracketReq = 0;

    int n = s.length();

    for (int i = 0; i < n; i++)
    {
        if (s[i] == '(')
            closingBracketReq++;
        else if (closingBracketReq > 0)
            closingBracketReq--;
        else
            openingBracketReq++;
    }

    return openingBracketReq + closingBracketReq;
}

//Leetcode 32
int longestValidParentheses(string s)
{
    stack<int> st;
    st.push(-1);
    int n = s.length();
    int len = 0;
    for (int i = 0; i < n; i++)
    {
        if (st.top() != -1 && s[st.top()] == '(' && s[i] == ')')
        {
            st.pop();
            len = max(len, i - st.top());
        }
        else
            st.push(i);
    }

    return len;
}

//Leetcode 1249
string minRemoveToMakeValid(string s) {
        stack<int> st;
        
        int n = s.length();
        for(int i=0;i<n;i++){
            if(s[i]==')'){
                if(st.size()!=0) st.pop();
                else s[i] = '#';
                
            }else if(s[i]=='(') st.push(i);
        }
        
        while(st.size()!=0){
            s[st.top()]='#';
            st.pop();
        }
        
        string ans = "";
        for(int i=0;i<n;i++){
            if(s[i]!='#') ans+=s[i];
        }
        
        return ans;
        
        
    }

void solve()
{
}

int main()
{
    solve();
    return 0;
}