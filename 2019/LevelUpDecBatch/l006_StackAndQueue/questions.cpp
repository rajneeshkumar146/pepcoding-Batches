#include <iostream>
#include <stack>
#include <vector>

using namespace std;

// N : Next, G = greater, S : Smaller, L : Left, R : Right
void NGOR(vector<int> &arr, vector<int> &ans)
{
    int n = arr.size();
    ans.resize(n, n); //Java :  Arrays.fill(ans,n);

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

void NGOL(vector<int> &arr, vector<int> &ans)
{
    int n = arr.size();
    ans.resize(n, -1); //Java :  Arrays.fill(ans,-1);

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

void NSOR(vector<int> &arr, vector<int> &ans)
{
    int n = arr.size();
    ans.resize(n, n); //Java :  Arrays.fill(ans,n);

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
}

void NSOL(vector<int> &arr, vector<int> &ans)
{
    int n = arr.size();
    ans.resize(n, -1); //Java :  Arrays.fill(ans,-1);

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
}

//503
vector<int> nextGreaterElements(vector<int> &arr)
{
    int n = arr.size();
    vector<int> ans(n, -1); //Java :  Arrays.fill(ans,n);

    stack<int> st;
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

// https://practice.geeksforgeeks.org/problems/stock-span-problem-1587115621/1
vector<int> NGOL(int arr[], int n)
{
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
    return ans;
}

vector<int> calculateSpan(int arr[], int n)
{
    vector<int> ans = NGOL(arr, n);
    for (int i = 0; i < n; i++)
        ans[i] = i - ans[i];
    return ans;
}

// Leetcode 901

// 20
bool isValid(string str)
{
    if (str.length() == 0)
        return true;
    int n = str.length();
    if (str[0] == ')' || str[0] == ']' || str[0] == '}')
        return false;
    if (str[n - 1] == '(' || str[n - 1] == '[' || str[n - 1] == '{')
        return false;

    stack<char> st;
    for (int i = 0; i < n; i++)
    {
        char ch = str[i];
        if (ch == '(' || ch == '[' || ch == '{')
            st.push(ch);
        else
        {
            if (st.size() == 0) // more no of closing brackets
                return false;
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

    return st.size() == 0; // st.size() != 0; // means No of Opening Brackets
}

// 946
bool validateStackSequences(vector<int> &pushed, vector<int> &popped)
{
    stack<int> st;
    int i = 0, n = popped.size();
    for (int ele : pushed)
    {
        st.push(ele);
        while (st.size() != 0 && st.top() == popped[i])
        {
            st.pop();
            i++;
        }
    }

    return i != n; // st.size() == 0;
}

// 1249
string minRemoveToMakeValid(string s)
{
    vector<int> st;
    int n = s.length();
    for (int i = 0; i < n; i++)
    {
        char ch = s[i];
        if (ch == '(')
            st.push_back(i);
        else if (ch == ')')
        {
            if (st.size() != 0 && s[st.back()] == '(')
                st.pop_back();
            else
                st.push_back(i);
        }
    }

    string ans = "";
    int idx = 0;
    for (int i = 0; i < n; i++)
    {
        if (idx < st.size() && st[idx] == i)
        {
            idx++;
            continue;
        }

        ans.push_back(s[i]);
    }

    return ans;
}

//735
vector<int> asteroidCollision(vector<int> &arr)
{
    stack<int> st;

    int n = arr.size();
    for (int ele : arr)
    {
        if (ele > 0)
        {
            st.push(ele);
            continue;
        }

        while (st.size() != 0 && st.top() > 0 && st.top() < -ele)
            st.pop();

        if (st.size() != 0 && st.top() == -ele)
            st.pop();
        else if (st.size() == 0 || st.top() < 0)
            st.push(ele);
        else
        {
        }
    }

    vector<int> ans(st.size(), 0);
    int idx = st.size() - 1;
    while (st.size() != 0)
    {
        ans[idx--] = st.top();
        st.pop();
    }

    return ans;
}

//84
int largestRectangleArea(vector<int> &heights)
{
    int n = heights.size();
    vector<int> nsol;
    vector<int> nsor;
    NSOL(heights, nsol);
    NSOR(heights, nsor);

    int maxArea = 0;
    for (int i = 0; i < n; i++)
    {
        int h = heights[i];
        int w = nsor[i] - nsol[i] - 1;

        maxArea = max(maxArea, h * w);
    }

    return maxArea;
}

//84
int largestRectangleArea_02(vector<int> &heights)
{
    int n = heights.size();
    stack<int> st;
    st.push(-1);
    int maxArea = 0;

    int i = 0;
    while (i < n)
    {
        while (st.top() != -1 && heights[st.top()] >= heights[i])
        {
            int idx = st.top();
            st.pop();

            int h = heights[idx];
            int w = i - st.top() - 1;
            maxArea = max(maxArea, h * w);
        }

        st.push(i++);
    }

    while (st.top() != -1)
    {
        int idx = st.top();
        st.pop();

        int h = heights[idx];
        int w = n - st.top() - 1;
        maxArea = max(maxArea, h * w);
    }

    return maxArea;
}

//316
string removeDuplicateLetters(string s)
{

    string st = "";
    vector<bool> vis(26, false);
    vector<int> freq(26, 0);
    for (char ch : s)
        freq[ch - 'a']++;

    for (char ch : s)
    {
        freq[ch - 'a']--;
        if (vis[ch - 'a'])
            continue;

        while (st.length() != 0 && st.back() > ch && freq[st.back() - 'a'] > 0)
        {
            char rch = st.back();
            st.pop_back();
            vis[rch - 'a'] = false;
        }

        vis[ch - 'a'] = true;
        st.push_back(ch);
    }

    return st;
}

int trap(vector<int> &height)
{
    int n = height.size();
    vector<int> lHeight(n, 0);
    vector<int> rHeight(n, 0);

    int prev = 0;
    for (int i = 0; i < n; i++)
    {
        lHeight[i] = max(height[i], prev);
        prev = lHeight[i];
    }

    prev = 0;
    for (int i = n - 1; i >= 0; i--)
    {
        rHeight[i] = max(height[i], prev);
        prev = rHeight[i];
    }

    int totalWater = 0;
    for (int i = 0; i < n; i++)
    {
        totalWater += min(lHeight[i], rHeight[i]) - height[i];
    }

    return totalWater;
}

int trap_01(vector<int> &height)
{
    int n = height.size();
    stack<int> st;

    int totalWater = 0;
    for (int i = 0; i < n; i++)
    {
        while (st.size() != 0 && height[st.top()] <= height[i])
        {
            int idx = st.top();
            st.pop();
            if (st.size() == 0)
                break;

            int w = i - st.top() - 1;
            int h = height[idx];

            totalWater += w * (min(height[st.top()], height[i]) - h);
        }

        st.push(i);
    }

    return totalWater;
}

int trap_03(vector<int> &height)
{
    int n = height.size();
    int lmax = 0, rmax = 0;
    int l = 0, r = n - 1, totalWater = 0;

    while (l < r)
    {
        lmax = max(lmax, height[l]);
        rmax = max(rmax, height[r]);

        totalWater += lmax < rmax ? lmax - height[l++] : rmax - height[r--];
    }

    return totalWater;
}

class MinStack
{
public:
    stack<long long> st;
    long long min = 0;

    MinStack()
    {
    }

    void push(int val)
    {
        if (st.size() == 0)
        {
            st.push(val);
            min = val;
        }
        else
        {
            if (val < min)
            {
                st.push(val + (val - min));
                min = val;
            }
            else
                st.push(val);
        }
    }

    void pop()
    {
        if (st.top() < min)
            min = min + (min - st.top());
        st.pop();
    }

    int top()
    {
        if (st.top() < min)
            return (int)min;
        return (int)st.top();
    }

    int getMin()
    {
        return (int)min;
    }
};

int main()
{
    return 0;
}
