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