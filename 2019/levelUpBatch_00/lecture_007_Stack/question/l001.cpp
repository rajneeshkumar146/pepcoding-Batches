#include <iostream>
#include <stack>
#include <vector>

using namespace std;

void display(vector<int> &arr)
{
    for (int ele : arr)
        cout << ele << " ";
    cout << endl;
}

void ngor(vector<int> &arr)
{
    int n = arr.size();
    vector<int> ans(n, -1);
    stack<int> st;

    for (int i = 0; i < n; i++) //for(int i=n-1;i>=0;i--) -> ngol
    {
        while (st.size() != 0 && arr[st.top()] < arr[i]) // for right <  , for left >
        {
            int idx = st.top();
            st.pop();
            ans[idx] = arr[i];
        }

        st.push(i);
    }

    while (st.size() != 0)
    {
        int idx = st.top();
        st.pop();
        ans[idx] = -1;
    }

    display(ans);
}

bool validBrackets_leet20(string &str)
{
    stack<int> st;
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        if (ch == '(' || ch == '[' || ch == '{')
            st.push(i);
        else
        {                       //closing bracket.
            if (st.size() == 0) //extra closing brackets.
                return false;
            else if (str[st.top()] == '(' && ch != ')') // not a perfect match pair.
                return false;
            else if (str[st.top()] == '[' && ch != ']')
                return false;
            else if (str[st.top()] == '{' && ch != '}')
                return false;
            else
                st.pop();
        }
    }
    // extra opening brackets.
    return st.size() == 0;
}

string removeOuterParentheses_leet_1021(string S)
{
    string ans = "";
    int count = 0;
    for (int i = 0; i < S.length(); i++)
    {
        char ch = S[i];
        if (ch == '(' && count++ > 0)
            ans += S[i];
        if (ch == ')' && count-- > 1)
            ans += S[i];
    }
    return ans;
}

int longestValidParentheses(string str)
{
    stack<int> st;
    st.push(-1);
    int max_ = 0;

    for (int i = 0; i < str.length(); i++)
    {
        if (st.top() != -1 && str[i] == ')' && str[st.top()] == '(')
        {
            st.pop();
            int len = i - st.top();
            if (max_ < len)
                max_ = len;
        }
        else
            st.push(i);
    }
    return max_;
}

void solve()
{
}

int main()
{
    solve();
    return 0;
}