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

vector<int> nsor(vector<int> &arr)
{
    stack<int> st;
    vector<int> ans(arr.size(), arr.size());

    for (int i = 0; i < arr.size(); i++)
    {
        while (st.size() != 0 && arr[st.top()] > arr[i]) // for smaller replace to '<' to '>'
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
    stack<int> st;
    vector<int> ans(arr.size(), -1);

    for (int i = arr.size() - 1; i >= 0; i--)
    {
        while (st.size() != 0 && arr[st.top()] > arr[i]) // for smaller replace to '<' to '>'
        {
            ans[st.top()] = i;
            st.pop();
        }

        st.push(i);
    }

    return ans;
}

int largestRectangleArea(vector<int> &heights)
{
    vector<int> nsolA = nsol(heights);
    vector<int> nsorA = nsor(heights);

    int max_ = 0;
    for (int i = 0; i < heights.size(); i++)
    {
        int width = nsorA[i] - nsolA[i] - 1;
        int area = heights[i] * width;
        max_ = max(max_, area);
    }
    return max_;
}

vector<int> largestRectangleArea_btr(vector<int> &heights)
{
    int n = heights.size();
    stack<int> st;
    st.push(-1);
    int maxArea = 0;

    int lb = 0, rb = 0, h = 0;

    for (int i = 0; i < n; i++)
    {
        while (st.top() != -1 && heights[st.top()] >= heights[i])
        {
            int height = heights[st.top()];
            st.pop();
            int area = height * (i - st.top() - 1);
            if (area > maxArea)
            {
                maxArea = area;
                lb = st.top() + 1;
                rb = i - 1;
                h = height;
            }
        }
        st.push(i);
    }

    while (st.top() != -1)
    {
        int height = heights[st.top()];
        st.pop();
        int area = height * (n - st.top() - 1);
        if (area > maxArea)
        {
            maxArea = area;
            lb = st.top() + 1;
            rb = n - 1;
            h = height;
        }
    }

    return {maxArea, lb, rb, h};
}

int largestRectangleArea(vector<int> &heights)
{
    int n = heights.size();
    stack<int> st;
    st.push(-1);
    int maxArea = 0;

    for (int i = 0; i < n; i++)
    {
        while (st.top() != -1 && heights[st.top()] >= heights[i])
        {
            int height = heights[st.top()];
            st.pop();
            int area = height * (i - st.top() - 1);
            if (area > maxArea)
                maxArea = area;
        }
        st.push(i);
    }

    while (st.top() != -1)
    {
        int height = heights[st.top()];
        st.pop();
        int area = height * (n - st.top() - 1);
        if (area > maxArea)
            maxArea = area;
    }

    return maxArea;
}

int maximalRectangle(vector<vector<char>> &matrix)
{
    if (matrix.size() == 0 || matrix[0].size() == 0)
        return 0;

    int n = matrix.size();
    int m = matrix[0].size();
    vector<int> heights(m, 0);

    int maxArea = 0;

    for (int r = 0; r < n; r++)
    {
        for (int c = 0; c < m; c++)
        {
            int ch = matrix[r][c] - '0';
            if (ch == 1)
                heights[c] += 1;
            else
                heights[c] = 0;
        }

        int area = largestRectangleArea(heights);
        maxArea = max(maxArea, area);
    }

    return maxArea;
}

int trap(vector<int> &height)
{
    int n = height.size();
    vector<int> gol(n, 0); // greatest so far till ith index
    vector<int> gor(n, 0);

    int prev = -1;
    for (int i = 0; i < n; i++)
    {
        gol[i] = max(prev, height[i]);
        prev = gol[i];
    }

    prev = -1;
    for (int i = n - 1; i >= 0; i--)
    {
        gor[i] = max(prev, height[i]);
        prev = gor[i];
    }

    int twater = 0;
    for (int i = 0; i < n; i++)
        twater += min(gor[i], gol[i]) - height[i];

    return twater;
}

int trap02(vector<int> &height)
{
    int n = height.size();
    stack<int> st;
    int water = 0;

    for (int i = 0; i < n; i++)
    {
        while (st.size() != 0 && height[i] >= height[st.top()])
        {
            int h = height[st.top()];
            st.pop();
            if (st.size() == 0)
                break;

            int w = i - st.top() - 1;
            water += w * (min(height[i], height[st.top()]) - h);
        }
        st.push(i);
    }
    return water;
}
