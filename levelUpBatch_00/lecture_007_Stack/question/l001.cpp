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

    // while (st.size() != 0)
    // {
    //     int idx = st.top();
    //     st.pop();
    //     ans[idx] = -1;
    // }

    // display(ans);
}

vector<int> nsor(vector<int> &arr)
{
    int n = arr.size();
    vector<int> ans(n, n);
    stack<int> st;

    for (int i = 0; i < n; i++) //for(int i=n-1;i>=0;i--) -> ngol
    {
        while (st.size() != 0 && arr[st.top()] > arr[i]) // for right <  , for left >
        {
            int idx = st.top();
            st.pop();
            ans[idx] = i;
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

    for (int i = n - 1; i >= 0; i--) //for(int i=n-1;i>=0;i--) -> ngol
    {
        while (st.size() != 0 && arr[st.top()] > arr[i]) // for right <  , for left >
        {
            int idx = st.top();
            st.pop();
            ans[idx] = i;
        }

        st.push(i);
    }
    return ans;
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

int largestRectangleArea(vector<int> &arr)
{
    if (arr.size() == 0)
        return 0;
    int n = arr.size();
    stack<int> st;
    st.push(-1);
    int maxArea = 0;

    for (int i = 0; i < n; i++)
    {
        while (st.top() != -1 && arr[st.top()] >= arr[i])
        {

            int h = arr[st.top()];
            st.pop();
            int w = i - st.top() - 1;
            int area = h * w;
            maxArea = max(maxArea, area);
        }
        st.push(i);
    }

    while (st.top() != -1)
    {

        int h = arr[st.top()];
        st.pop();
        int w = n - st.top() - 1;
        int area = h * w;
        maxArea = max(maxArea, area);
    }

    return maxArea;
}

int largestRectangleArea_02(vector<int> &arr)
{
    vector<int> left = nsol(arr);
    vector<int> right = nsor(arr);
    int n = arr.size();
    int maxArea = 0;
    for (int i = 0; i < n; i++)
    {
        int w = right[i] - left[i] - 1;
        int h = arr[i];
        int area = h * w;
        maxArea = max(maxArea, area);
    }

    return maxArea;
}

int maximalRectangle(vector<vector<char>> &arr)
{
    int n = arr.size();
    if (n == 0 || arr[0].size() == 0)
        return 0;

    int m = arr[0].size();
    int maxArea = 0;
    vector<int> height(m, 0);
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++) // O(m)
        {
            int val = arr[i][j] - '0';
            if (val == 1)
                height[i] += 1;
            else
                height[i] = 0;
        }

        int area = largestRectangleArea(height);
        maxArea = max(maxArea, area);
    }

    return maxArea;
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
            {
                st.pop();
            }

            if (st.size() != 0 && st.top() == -ele)
                st.pop();
            else if (st.size() == 0 || st.top() < 0)
                st.push(ele);
            else if (st.size() != 0 && st.top() > -ele)
            {
                // do nothing. because ele will vanished.
            }
        }
    }

    vector<int> ans(st.size(), 0);
    for (int i = st.size() - 1; i >= 0; i--)
    {
        ans[i] = st.top();
        st.pop();
    }
}

int trap01(vector<int> &arr)
{
    int n = arr.size();
    vector<int> left(n, 0);
    vector<int> right(n, 0);

    int prev = -1;
    for (int i = 0; i < n; i++)
    {
        left[i] = max(prev, arr[i]);
        prev = left[i];
    }

    prev = -1;
    for (int i = n - 1; i >= 0; i--)
    {
        right[i] = max(prev, arr[i]);
        prev = right[i];
    }

    int water = 0;
    for (int i = 0; i < n; i++)
    {
        int minH = min(left[i], right[i]);
        water += minH - arr[i];
    }
    return water;
}


int trap(vector<int> &arr)
{
    stack<int> st;
    int water=0;
    for(int i=0;i<arr.size();i++){
        while(st.size()!=0 && arr[i]>=arr[st.top()]){
            int h=arr[st.top()]; st.pop();
            if(st.size()==0) break;

            int oh=min(arr[st.top()],arr[i])-h;
            water+=oh*(i-st.top()-1);
        }
        st.push(i);
    }

    return water;
}

void solve()
{
}

int main()
{
    solve();
    return 0;
}