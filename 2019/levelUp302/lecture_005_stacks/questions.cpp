#include <iostream>
#include <stack>
#include <vector>

using namespace std;

//next greater on right.
vector<int> ngor(vector<int> &arr)
{
    stack<int> st;
    vector<int> ans(arr.size(), arr.size());

    for (int i = 0; i < arr.size(); i++)
    {
        while (st.size() != 0 && arr[st.top()] < arr[i]) // for smaller replace to '<' to '>'
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
    vector<int> ans(arr.size(), -1);

    for (int i = arr.size() - 1; i >= 0; i--)
    {
        while (st.size() != 0 && arr[st.top()] < arr[i]) // for smaller replace to '<' to '>'
        {
            ans[st.top()] = i;
            st.pop();
        }

        st.push(i);
    }

    return ans;
}

bool isValidExpre(string &str)
{
    stack<char> st;
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];

        if (ch == '(' || ch == '[' || ch == '{')
            st.push(ch);
        else
        {
            if (st.size() == 0)
                return false;
            else if (st.top() == '(' && ch != ')')
                return false;
            else if (st.top() == '[' && ch != ']')
                return false;
            else if (st.top() == '{' && ch != '}')
                return false;
            else
                st.pop();
        }
    }

    return st.size() == 0;
}

string removeOuterParentheses(string str)
{
    string ans = "";
    int count = 0;
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        if (ch == '(' && count++ > 0)
            ans += ch;
        if (ch == ')' && count-- > 1)
            ans += ch;
    }

    return ans;
}

vector<int> nextGreaterElements(vector<int> &arr)
{

    stack<int> st;
    int n = arr.size();
    vector<int> ans(n, -1);

    for (int i = 0; i < 2 * n; i++)
    {
        while (st.size() != 0 && arr[st.top()] < arr[i % n])
        {
            ans[st.top()] = arr[i % n];
            st.pop();
        }

        if (i < n)
            st.push(i);
    }

    return ans;
}

class StockSpanner
{
    stack<pair<int, int>> st; // idx, value
    // For Java: Stack<int[]> st=new Stack<>(); insert-> st.push(new []{10,6}); st.peek()[1]
    int i;

public:
    StockSpanner()
    {
        st.push({-1, -1});
        i = 0;
    }

    int next(int price)
    {
        int ans = 1;
        while (st.top().first != -1 && st.top().second <= price)
        {
            st.pop();
        }
        ans = i - st.top().first;
        st.push({i, price});
        i++;

        return ans;
    }
};

//Leetcode 921.==================================================================

int minAddToMakeValid(string str)
{

    stack<int> st;
    st.push(-1);

    for (int i = 0; i < str.length(); i++)
    {
        if (st.top() != -1 && str[i] == ')' && str[st.top()] == '(')
            st.pop();
        else
            st.push(i);
    }

    return st.size() - 1;
}

int minAddToMakeValid02(string str)
{
    int openingBracketReq = 0;
    int closingBracketReq = 0;

    for (int i = 0; i < str.length(); i++)
    {
        if (str[i] == '(')
            closingBracketReq++;
        else if (closingBracketReq > 0)
            closingBracketReq--;
        else
            openingBracketReq++;
    }

    return openingBracketReq + closingBracketReq;
}

//Leetcode 1249:

string minRemoveToMakeValid(string str)
{
    int n = str.length();
    vector<bool> marked(n, false);

    stack<int> st;
    st.push(-1);

    for (int i = 0; i < n; i++)
    {
        if (st.top() != -1 && str[i] == ')' && str[st.top()] == '(')
        {
            int val = st.top();
            st.pop();
            marked[i] = marked[val] = true;
        }
        else if (str[i] == '(')
            st.push(i);
        else if (str[i] != ')')
            marked[i] = true;
    }

    string ans = "";
    for (int i = 0; i < n; i++)
    {
        if (marked[i])
            ans += str[i];
    }

    return ans;
}

string minRemoveToMakeValid_02(string str)
{
    int n = str.length();

    stack<int> st;

    for (int i = 0; i < n; i++)
    {
        if (str[i] == ')')
        {
            if (st.size() != 0)
                st.pop();
            else
                str[i] = '*';
        }
        else if (str[i] == '(')
            st.push(i);
    }

    while (st.size() != 0)
    {
        str[st.top()] = '*';
        st.pop();
    }

    string ans = "";
    for (int i = 0; i < n; i++)
    {
        if (str[i] != '*')
            ans += str[i];
    }

    return ans;
}

//Leetcode : 735

vector<int> asteroidCollision(vector<int> &arr)
{
    stack<int> st;
    for (int ele : arr)
    {
        if (ele > 0)
            st.push(ele);
        else
        {
            while (st.size() != 0 && st.top() > 0 && st.top() < -ele)
                st.pop();

            if (st.size() != 0 && st.top() == -ele)
                st.pop();
            else if (st.size() == 0 || st.top() < 0)
                st.push(ele);
            else if (st.size() != 0 && st.top() > -ele)
            {
                //do nothing.
            }
        }
    }

    vector<int> ans(st.size(), 0);
    int i = st.size() - 1;
    while (st.size())
    {
        ans[i--] = st.top();
        st.pop();
    }

    return ans;
}
