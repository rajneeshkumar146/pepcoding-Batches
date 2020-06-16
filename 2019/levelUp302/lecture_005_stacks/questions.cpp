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
