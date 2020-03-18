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

    for (int i = 0; i < n; i++)  //for(int i=n-1;i>=0;i--) -> ngol
    {
        while (st.size() != 0 && arr[st.top()] < arr[i])  // for right <  , for left >
        {
            int idx = st.top();
            st.pop();
            ans[idx] = arr[i];
        }

        st.push(i);
    }

    while (st.size() != 0)
    {
        int idx = st.top();
        st.pop();
        ans[idx] = -1;
    }

    display(ans);
}

void solve()
{
}

int main()
{
    solve();
    return 0;
}