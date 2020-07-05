#include <iostream>
#include <stack>
#include <vector>
#include <queue>

using namespace std;

class Node
{
public:
    int data;
    vector<Node *> childs;

    Node(int data)
    {
        this->data = data;
    }
};

Node *createTree(vector<int> &arr)
{
    stack<Node *> st;
    for (int i = 0; i < arr.size() - 1; i++)
    {
        int ele = arr[i];
        if (ele == -1)
        {
            Node *node = st.top();
            st.pop();
            st.top()->childs.push_back(node);
        }
        else
            st.push(new Node(ele));
    }

    return st.top();
}

void preOrder(Node *node)
{
    cout << node->data << " ";
    for (Node *child : node->childs)
        preOrder(child);
}

void postOrder(Node *node)
{
    for (Node *child : node->childs)
        postOrder(child);
    cout << node->data << " ";
}

int height(Node *node)
{
    int h = -1;
    for (Node *child : node->childs)
        h = max(h, height(child));

    return h + 1;
}

int size(Node *node)
{
    int sz = 0;
    for (Node *child : node->childs)
        sz += size(child);

    return sz + 1;
}

void display(Node *node)
{
    string str = to_string(node->data) + " -> ";
    for (Node *child : node->childs)
        str += to_string(child->data) + ", ";
    cout << str << endl;

    for (Node *child : node->childs)
        display(child);
}

bool find(Node *node, int data)
{
    if (node->data == data)
        return true;

    for (Node *child : node->childs)
        if (find(child, data))
            return true;

    return false;
}

void levelOrderLlineWise(Node *node)
{
    queue<Node *> que;
    que.push(node);
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            Node *rnode = que.front();
            que.pop();
            cout << rnode->data << " ";

            for (Node *child : rnode->childs)
                que.push(child);
        }
        cout << endl;
    }
    cout << endl;
}

bool nodeToRootPath(Node *node, int data, vector<Node *> &ans)
{
    if (node->data == data)
    {
        ans.push_back(node);
        return true;
    }

    bool res = false;
    for (Node *child : node->childs)
        res = res || nodeToRootPath(child, data, ans);

    if (res)
        ans.push_back(node);

    return res;
}

void distanceBetweenTwoNodes(Node *node, int d1, int d2)
{
}

Node *getTail(Node *node)
{
    while (node->childs.size() != 0)
        node = node->childs[0];
    return node;
}

void linearize(Node *node)
{
    int n = node->childs.size();
    for (int i = n - 1; i >= 0; i--)
    {
        Node *child = node->childs[i];
        linearize(child);

        if (i < n - 1)
        {
            Node *tail = getTail(child);
            tail->childs.push_back(node->childs[i + 1]);
            node->childs.pop_back();
        }
    }
}

Node *linearize2(Node *node)
{
    if (node->childs.size() == 0)
        return node;

    int n = node->childs.size();
    Node *lastTail = linearize2(node->childs[n - 1]);

    for (int i = n - 2; i >= 0; i--)
    {
        Node *child = node->childs[i];
        Node *secondLastTail = linearize2(child);
        
        secondLastTail->childs.push_back(node->childs[i + 1]);
        node->childs.pop_back();
    }

    return lastTail;
}

void set1(Node *node)
{
    vector<Node *> ans;
    nodeToRootPath(node, 140, ans);

    linearize(node);
    display(node);
}

void solve()
{
    vector<int> arr{10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 100, -1, 110, -1, -1, 90, -1, -1, 40, 120, 140, -1, 150, -1, -1, -1, -1};
    Node *root = createTree(arr);
    display(root);

    set1(root);
}

int main()
{
    solve();
    return 0;
}
