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

void nsor(vector<int> &arr)
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
}

void nsol(vector<int> &arr)
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

int main()
{
}