#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
#include <unordered_map>
using namespace std;

//Leetcode 20
bool isValid(string s)
{
    stack<int> st;
    int n = s.length();

    for (int i = 0; i < n; i++)
    {
        if (s[i] == '(' || s[i] == '[' || s[i] == '{')
            st.push(i);
        else
        {
            if (st.size() == 0)
                return false;
            else if (s[i] == ')' && s[st.top()] != '(')
                return false;
            else if (s[i] == ']' && s[st.top()] != '[')
                return false;
            else if (s[i] == '}' && s[st.top()] != '{')
                return false;
            else
                st.pop();
        }
    }

    return st.size() == 0;
}

//Leetcode 1021
string removeOuterParentheses(string S)
{
    string str = "";

    int balanceBrac = 0;
    for (int i = 0; i < S.length(); i++)
    {
        if (S[i] == '(' && balanceBrac++ > 0)
            str += S[i];
        else if (S[i] == ')' && balanceBrac-- > 1)
            str += S[i];
    }

    return str;
}

vector<int> ngor(vector<int> &arr)
{
    stack<int> st;
    st.push(-1);

    int n = arr.size();
    vector<int> ans(n, n);

    for (int i = 0; i < n; i++)
    {
        while (st.top() != -1 && arr[st.top()] < arr[i])
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
    st.push(-1);

    int n = arr.size();
    vector<int> ans(n, -1);

    for (int i = n - 1; i >= 0; i--)
    {
        while (st.top() != -1 && arr[st.top()] < arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }

    return ans;
}

vector<int> nsor(vector<int> &arr)
{
    stack<int> st;
    st.push(-1);

    int n = arr.size();
    vector<int> ans(n, n);

    for (int i = 0; i < n; i++)
    {
        while (st.top() != -1 && arr[st.top()] > arr[i])
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
    st.push(-1);

    int n = arr.size();
    vector<int> ans(n, -1);

    for (int i = n - 1; i >= 0; i--)
    {
        while (st.top() != -1 && arr[st.top()] > arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }

    return ans;
}

//Leetcode 503
vector<int> nextGreaterElements(vector<int> &arr)
{
    stack<int> st;
    st.push(-1);

    int n = arr.size();
    vector<int> ans(n, -1);

    for (int i = 0; i < 2 * n; i++)
    {
        while (st.top() != -1 && arr[st.top()] < arr[i % n])
        {
            ans[st.top()] = arr[i % n];
            st.pop();
        }
        if (i < n)
            st.push(i);
    }

    return ans;
}

//Leetcode 921
int minAddToMakeValid(string S)
{
    stack<int> st;

    int n = S.length();
    for (int i = 0; i < n; i++)
    {
        if (st.size() != 0 && S[st.top()] == '(' && S[i] == ')')
            st.pop();
        else
            st.push(i);
    }

    return st.size();
}

int minAddToMakeValid_02(string s)
{
    int openingBracketReq = 0;
    int closingBracketReq = 0;

    int n = s.length();

    for (int i = 0; i < n; i++)
    {
        if (s[i] == '(')
            closingBracketReq++;
        else if (closingBracketReq > 0)
            closingBracketReq--;
        else
            openingBracketReq++;
    }

    return openingBracketReq + closingBracketReq;
}

//Leetcode 32
int longestValidParentheses(string s)
{
    stack<int> st;
    st.push(-1);
    int n = s.length();
    int len = 0;
    for (int i = 0; i < n; i++)
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

//Leetcode 1249
string minRemoveToMakeValid(string s)
{
    stack<int> st;

    int n = s.length();
    for (int i = 0; i < n; i++)
    {
        if (s[i] == ')')
        {
            if (st.size() != 0)
                st.pop();
            else
                s[i] = '#';
        }
        else if (s[i] == '(')
            st.push(i);
    }

    while (st.size() != 0)
    {
        s[st.top()] = '#';
        st.pop();
    }

    string ans = "";
    for (int i = 0; i < n; i++)
    {
        if (s[i] != '#')
            ans += s[i];
    }

    return ans;
}

//Leetcode 84

int largestRectangleArea_01(vector<int> &heights)
{
    vector<int> sl = nsol(heights);
    vector<int> sr = nsor(heights);

    int area = 0;
    for (int i = 0; i < heights.size(); i++)
    {
        int w = sr[i] - sl[i] - 1;
        int h = heights[i];

        area = max(area, w * h);
    }

    return area;
}

int largestRectangleArea_02(vector<int> &heights)
{
    int n = heights.size();
    if (n == 0)
        return 0;

    stack<int> st;
    st.push(-1);
    int area = 0;
    for (int i = 0; i < heights.size(); i++)
    {
        while (st.top() != -1 && heights[i] <= heights[st.top()])
        {
            int h = heights[st.top()];
            st.pop();
            int w = i - st.top() - 1;

            area = max(area, h * w);
        }
        st.push(i);
    }

    while (st.top() != -1)
    {
        int h = heights[st.top()];
        st.pop();
        int w = n - st.top() - 1;

        area = max(area, h * w);
    }
    return area;
}

int maximalRectangle(vector<vector<char>> &matrix)
{
    if (matrix.size() == 0 || matrix[0].size() == 0)
        return 0;
    int n = matrix.size();
    int m = matrix[0].size();

    vector<int> heights(m, 0);
    int area = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
        }

        area = max(area, largestRectangleArea_02(heights));
    }

    return area;
}

int trap_01(vector<int> &height)
{
    int water = 0;
    int n = height.size();
    vector<int> left(n, 0);
    vector<int> right(n, 0);

    int prev = 0;
    for (int i = 0; i < n; i++)
    {
        left[i] = max(prev, height[i]);
        prev = left[i];
    }

    prev = 0;
    for (int i = n - 1; i >= 0; i--)
    {
        right[i] = max(prev, height[i]);
        prev = right[i];
    }

    for (int i = 0; i < n; i++)
    {
        water += min(left[i], right[i]) - height[i];
    }

    return water;
}

int trap_02(vector<int> &heights)
{
    int n = heights.size();
    if (n == 0)
        return 0;

    stack<int> st;
    int water = 0;
    for (int i = 0; i < heights.size(); i++)
    {
        while (st.size() != 0 && heights[st.top()] < heights[i])
        {
            int h = heights[st.top()];
            st.pop();
            if (st.size() == 0)
                break;
            int w = i - st.top() - 1;

            water += (min(heights[st.top()], heights[i]) - h) * w;
        }
        st.push(i);
    }

    return water;
}

int trap_03(vector<int> &heights)
{
    int n = heights.size();
    if (n == 0)
        return 0;
    int li = 0, ri = n - 1, lmax = 0, rmax = 0;
    int water = 0;
    while (li <= ri)
    {
        lmax = max(lmax, heights[li]);
        rmax = max(rmax, heights[ri]);

        // water += lmax <= rmax ? lmax - heights[li++] : rmax - heights[ri--];
        if (lmax <= rmax)
            water += lmax - heights[li++];
        else
            water += rmax - heights[ri--];
    }

    return water;
}

//Leetcode 735
vector<int> asteroidCollision(vector<int> &arr)
{
    stack<int> st;
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
            // do Nothing.
        }
    }

    vector<int> ans(st.size());
    for (int i = st.size() - 1; i >= 0; i--)
    {
        ans[i] = st.top();
        st.pop();
    }

    return ans;
}

//Leetcode 316, 1081
string smallestSubsequence(string s)
{
    if (s.length() <= 1)
        return s;

    vector<bool> seen(26, false);
    vector<int> freq(26, 0);

    for (char ch : s)
        freq[ch - 'a']++;

    stack<char> st;

    for (int i = 0; i < s.length(); i++)
    {
        char ch = s[i];

        freq[ch - 'a']--;
        if (seen[ch - 'a'])
            continue;

        while (st.size() != 0 && st.top() > ch && freq[st.top() - 'a'] > 0)
        {
            seen[st.top() - 'a'] = false;
            st.pop();
        }

        seen[ch - 'a'] = true;
        st.push(ch);
    }

    string ans = "";
    while (st.size() != 0)
    {
        ans += st.top();
        st.pop();
    }

    reverse(ans.begin(), ans.end());
    return ans;
}

string removeKdigits(string num, int k)
{
    stack<char> st;
    for (int i = 0; i < num.length(); i++)
    {
        char digit = num[i];

        while (st.size() != 0 && k > 0 && st.top() > digit)
        {
            st.pop();
            k--;
        }
        st.push(digit);
    }

    while (st.size() != 0 && k-- > 0)
        st.pop();

    string ans = "";
    while (st.size() != 0)
    {
        ans += st.top();
        st.pop();
    }

    while (ans.size() != 0)
    {
        if (ans.back() != '0')
            break;
        ans.pop_back();
    }

    reverse(ans.begin(), ans.end());
    return ans.length() == 0 ? "0" : ans;
}

//Leetcode 155
class MinStack
{
public:
    stack<long> st;
    long minSf = 0;

    MinStack()
    {
    }

    void push(int x)
    {
        if (st.size() == 0)
        {
            st.push(x);
            minSf = x;
            return;
        }

        if (x < minSf)
        {
            st.push((x - minSf) + x);
            minSf = x;
        }
        else
            st.push(x);
    }

    void pop()
    {
        if (st.top() < minSf)
        {
            minSf = (minSf - st.top()) + minSf;
        }

        st.pop();
    }

    int top()
    {
        if (st.top() < minSf)
            return (int)minSf;
        return (int)st.top();
    }

    int getMin()
    {
        return (int)minSf;
    }
};

class LRUCache
{

    class Node
    {
    public:
        int key = 0;
        int value = 0;

        Node *next = nullptr;
        Node *prev = nullptr;

        Node(int key, int value)
        {
            this->key = key;
            this->value = value;
        }
    };

    Node *head = nullptr;
    Node *tail = nullptr;
    int size = 0;
    int maxSize = 0;

    void addLast(Node *node)
    {
        if (this->size == 0)
        {
            this->head = node;
            this->tail = node;
        }
        else
        {
            this->tail->next = node;
            node->prev = this->tail;
            this->tail = node;
        }
        this->size++;
    }

    void removeNode(Node *node)
    {

        if (this->size == 1)
        {
            this->head =  nullptr;
            this->tail = nullptr;
        }
        else if (node->prev == nullptr)
        {
            this->head = node->next;

            this->head->prev = nullptr;
            node->next = nullptr;
        }
        else if (node->next == nullptr)
        {
            this->tail = node->prev;

            this->tail->next = nullptr;
            node->prev = nullptr;
        }
        else
        {
            Node *prev = node->prev;
            Node *next = node->next;

            prev->next = next;
            next->prev = prev;

            node->next = nullptr;
            node->prev = nullptr;
        }
        this->size--;
    }

    unordered_map<int, Node *> map; // key, node

public:
    LRUCache(int capacity)
    {
        this->maxSize = capacity;
    }

    int get(int key)
    {
        if (map.find(key) == map.end())
            return -1;

        Node *node = map[key];
        int rv = node->value;

        removeNode(node);
        addLast(node);

        return rv;
    }

    void put(int key, int value)
    {
        if (map.find(key) == map.end())
        {
            Node *node = new Node(key, value);
            map[key] = node;
            addLast(node);
            if(this->size > this->maxSize){
               node = this->head;
                
               map.erase(node->key);
               removeNode(node);  
            }
        }
        else
        {
            int val = get(key);
            if (val != value)
                map[key]->value = value;
        }
    }
};
