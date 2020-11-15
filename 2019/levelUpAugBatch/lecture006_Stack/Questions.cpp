#include <iostream>
#include <stack>
#include <vector>

using namespace std;

void ngor(vector<int> &arr)
{
    int n = arr.size();
    vector<int> ans(n, n);

    stack<int> st;
    for (int i = 0; i < n; i++)
    {
        while (st.size() != 0 && arr[st.top()] < arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }

        st.push(i);
    }
}

void ngol(vector<int> &arr)
{
    int n = arr.size();
    vector<int> ans(n, -1);

    stack<int> st;
    for (int i = n - 1; i >= 0; i--)
    {
        while (st.size() != 0 && arr[st.top()] < arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }

        st.push(i);
    }
}

vector<int> nsor(vector<int> &arr)
{
    int n = arr.size();
    vector<int> ans(n, n);

    stack<int> st;
    for (int i = 0; i < n; i++)
    {
        while (st.size() != 0 && arr[st.top()] > arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }

        st.push(i);
    }

    return ans;
}

vector<int> nsol(vector<int> &arr)
{
    int n = arr.size();
    vector<int> ans(n, -1);

    stack<int> st;
    for (int i = n - 1; i >= 0; i--)
    {
        while (st.size() != 0 && arr[st.top()] > arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }

        st.push(i);
    }

    return ans;
}

//20
bool isValid(string s)
{
    stack<char> st;
    int n = s.length();

    for (int i = 0; i < n; i++)
    {
        char ch = s[i];
        if (ch == '(' || ch == '[' || ch == '{')
            st.push(ch);
        else
        {
            if (st.size() == 0)
                return false; // more closing brackets
            else if (ch == ')' && st.top() != '(')
                return false;
            else if (ch == ']' && st.top() != '[')
                return false;
            else if (ch == '}' && st.top() != '{')
                return false;
            else
                st.pop();
        }
    }

    return st.size() == 0; // more opening brackets
}

//946
bool validateStackSequences(vector<int> &pushed, vector<int> &popped)
{
    stack<int> st;
    int i = 0;

    for (int ele : pushed)
    {
        st.push(ele);
        while (st.size() != 0 && st.top() == popped[i])
        {
            st.pop();
            i++;
        }
    }

    return st.size() == 0;
}

//503
vector<int> nextGreaterElements(vector<int> &arr)
{
    int n = arr.size();
    vector<int> ans(n, -1);

    stack<int> st;
    for (int i = 0; i < 2 * n; i++)
    {
        while (st.size() != 0 && arr[st.top()] < arr[i % n])
        {
            ans[st.top()] = arr[i % n];
            st.pop();
        }

        // if(st.size()!= 0&& st.top() == (i % n)) break;

        if (i < n)
            st.push(i);
    }

    return ans;
}

//1021
string removeOuterParentheses(string S)
{

    string ans = "";
    int count = 0;
    for (int i = 0; i < S.length(); i++)
    {
        char ch = S[i];

        if (ch == '(' && count++ > 0)
            ans += ch;
        else if (ch == ')' && count-- > 1)
            ans += ch;
    }

    return ans;
}

string minRemoveToMakeValid(string s)
{

    stack<int> st;
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s[i];
        if (ch == ')')
        {
            if (st.size() != 0)
                st.pop();
            else
                s[i] = '#';
        }
        else if (ch == '(')
        {
            st.push(i);
        }
    }

    while (st.size() != 0)
    {
        s[st.top()] = '#';
        st.pop();
    }

    string ans = "";
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s[i];
        if (ch != '#')
        {
            ans += ch;
        }
    }

    return ans;
}

//  public String minRemoveToMakeValid(String s) {
//         Stack<Integer> st = new Stack<>();
//         // StringBuilder sb = new StringBuilder(s);
//         char[] arr  = s.toCharArray();

//         int  n = s.length();
//         for(int i=0;i<n;i++){
//            char ch =  arr[i];
//            if(ch == ')'){
//                if(st.size() != 0) st.pop();
//                else arr[i] = '#';

//            }else if(ch == '('){
//                st.push(i);
//            }
//         }

//         while(st.size() != 0){
//             arr[st.pop()] = '#';
//         }

//         StringBuilder sb = new StringBuilder();
//         for(int i=0;i<n;i++){
//             if(arr[i] != '#') sb.append(arr[i]);
//         }

//         return sb.toString();
//     }

//Leetcode 32
int longestValidParentheses(string s)
{
    stack<int> st;
    st.push(-1);
    int len = 0;
    for (int i = 0; i < s.length(); i++)
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
            else
            {
                //negative asteroid will destroy
            }
        }
    }

    vector<int> ans(st.size(), 0);
    for (int i = st.size() - 1; i >= 0; i--)
        ans[i] = st.top(), st.pop();
    return ans;
}

class StockSpanner
{
public:
    stack<pair<int, int>> st;
    int idx = 0;

    StockSpanner()
    {
        st.push({-1, -1});
    }

    int next(int price)
    {
        while (st.top().first != -1 && st.top().second <= price)
            st.pop();
        int span = idx - st.top().first;
        st.push({idx++, price});
        return span;
    }
};

// class StockSpanner
// {
//     Stack<int[]> st = new Stack<>();
//     int idx = 0;
// public
//     StockSpanner()
//     {
//         st.push(new int[]{-1, -1});
//     }

// public
//     int next(int price)
//     {

//         while (st.peek()[0] != -1 && st.peek()[1] <= price)
//         {
//             st.pop();
//         }

//         int span = idx - st.peek()[0];
//         st.push(new int[]{idx++, price});
//         return span;
//     }
// }

//85
int maximalRectangle(vector<vector<char>> &matrix)
{
    if (matrix.size() == 0 || matrix[0].size() == 0)
        return 0;
    int n = matrix.size();
    int m = matrix[0].size();
    vector<int> arr(m, 0);

    int area = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            arr[j] = matrix[i][j] == '1' ? arr[j] + 1 : 0;
        }

        area = max(largestRectangleArea(arr), area);
    }

    return area;
}

int largestRectangleArea(vector<int> &heights)
{
    int n = heights.size();
    stack<int> st;
    st.push(-1);

    int area = 0;
    for (int i = 0; i < n; i++)
    {
        while (st.top() != -1 && heights[st.top()] >= heights[i])
        {
            int h = heights[st.top()];
            st.pop();

            int w = i - st.top() - 1;
            area = max(area, h * w);
        }
        st.push(i);
    }

    while (st.size() != 1)
    {
        int h = heights[st.top()];
        st.pop();

        int w = n - st.top() - 1;
        area = max(area, h * w);
    }

    return area;
}

// 84
int largestRectangleArea(vector<int> &heights)
{
    int n = heights.size();
    vector<int> left = nsol(heights);
    vector<int> right = nsor(heights);

    int area = 0;
    for (int i = 0; i < n; i++)
    {
        int h = heights[i];
        int w = right[i] - left[i] - 1;
        area = max(area, h * w);
    }

    return area;
}

vector<int> nsor(vector<int> &arr)
{
    int n = arr.size();
    vector<int> ans(n, n);

    stack<int> st;
    for (int i = 0; i < n; i++)
    {
        while (st.size() != 0 && arr[st.top()] > arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }

        st.push(i);
    }

    return ans;
}

vector<int> nsol(vector<int> &arr)
{
    int n = arr.size();
    vector<int> ans(n, -1);

    stack<int> st;
    for (int i = n - 1; i >= 0; i--)
    {
        while (st.size() != 0 && arr[st.top()] > arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }

        st.push(i);
    }

    return ans;
}