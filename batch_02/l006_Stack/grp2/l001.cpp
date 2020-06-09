#include <iostream>
#include <stack>

using namespace std;

void nextGreatestOnRight(vector<int> &arr)
{
    stack<int> st;
    vector<int> ans(arr.size(), arr.size());
    for (int i = 0; i < arr.size(); i++)
    {
        while (st.size() != 0 && arr[st.top()] < arr[i])
        {
            int idx = st.top();
            st.pop();

            ans[idx] = i;
        }
        st.push(i);
    }
}

//1,2,3,1,3,3,3,2,2,5,5,4,5,6,2,1
int maxAreaInHistogram(vector<int> &arr)
{
    int maxArea = 0;
    stack<int> st;
    st.push(-1);

    for (int i = 0; i < arr.size(); i++)
    {
        while (st.top() != -1 && arr[st.top()] >= arr[i])
        {

            int height = arr[st.top()];
            st.pop();

            int width = i - st.top() - 1;
            maxArea = max(maxArea, height * width);
        }

        st.push(i);
    }

    while (st.top() != -1)
    {

        int height = arr[st.top()];
        st.pop();

        int width = arr.size() - st.top() - 1;
        maxArea = max(maxArea, height * width);
    }

    return maxArea;
}

int maxAreaInHistogram(string &str)
{
    int maxLeng = 0;
    stack<int> st;
    st.push(-1);

    for (int i = 0; i < str.length(); i++)
    {
        while (st.top() != -1 && str[i] == ')' && str[st.top()] == '(')
        {
            st.pop();

            int width = i - st.top();
            maxLeng = max(maxLeng, width);
        }

        st.push(i);
    }
    return maxLeng;
}

bool balanceBrackets(string &str)
{
    stack<int> st;
    for (int i = 0; i < str.length; i++)
    {
        if (str[i] == '(' || str[i] == '[' || str[i] == '{' || str[i] == '>')
            st.push(i);
        else
        {
            if (st.size() == 0)
                return false;
            else if (str[i] == ')' && str[st.top()] != '(')
                return false;
            else if (str[i] == ']' && str[st.top()] != '[')
                return false;
            else if (str[i] == '}' && str[st.top()] != '{')
                return false;
            else if (str[i] == '>' && str[st.top()] != '<')
                return false;
            else
                st.pop();
        }
    }

    return st.size() == 0;
}

vector<int> asteroidCollision(vector<int> &arr)
{
    stack<int> st;
    for (int i = 0; i < arr.size(); i++)
    {
        if (arr[i] > 0)
            st.push(arr[i]);
        else
        {
            while (st.size() != 0 && st.top() > 0 && st.top() < -arr[i])
            {
                st.pop();
            }

            if (st.size() != 0 && st.top() == -arr[i])
                st.pop();
            else if (st.size() == 0 || st.top() < 0)
            {
                st.push(arr[i]);
            }
        }
    }

    vector<int> ans(st.size(), 0);
    int k = st.size() - 1;
    while (st.size() != 0)
    {
        ans[k--] = st.top();
        st.pop();
    }
    return ans;
}

class MinStack
{
public:
    stack<int> st;
    int minVal = 0;

    MinStack()
    {
        st.clear();
        minVal = 0;
    }

    void push(int x)
    {
        if (st.size() == 0)
        {
            st.push(x);
            minVal = x;
            return;
        }

        if (x > minVal)
            st.push(x);
        else
        {
            st.push(2 * x - minVal);
            minVal = x;
        }
    }

    void pop()
    {
        if (st.top() <= minVal)
            minVal = minVal - st.top() + minVal;

        st.pop();
    }

    int top()
    {
        if (st.top() <= minVal)
            return minVal;

        return st.top();
    }

    int getMin()
    {
        return minVal;
    }
};
