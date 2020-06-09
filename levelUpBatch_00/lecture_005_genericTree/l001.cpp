#include <iostream>
#include <vector>
#include <stack>

using namespace std;

class Node
{
public:
    int data = 0;
    vector<Node *> childs;

    Node(int data)
    {
        this->data = data;
    }
};

Node *createTree(vector<int> &arr)
{
    stack<Node *> st;
    Node *root = nullptr;
    for (int i = 0; i < arr.size(); i++)
    {
        if (arr[i] != -1)
        {
            Node *node = new Node(arr[i]);
            if (i == 0)
                root = node;
            st.push(node);
        }
        else
        {
            Node *node = st.top();
            st.pop();
            st.top()->childs.push_back(node);
        }
    }
    return root;
}

void display(Node *node)
{

    cout << node->data << " -> ";
    for (Node *n : node->childs)
    {
        cout << n->data << ", ";
    }
    cout << endl;

    for (Node *n : node->childs)
    {
        display(n);
    }
}

//basic.==================================

int size(Node *root)
{
    int s = 1;
    for (Node *n : root->childs)
    {
        s += size(n);
    }
    return s;
}

int height(Node *root)
{
    int h = -1;
    for (Node *n : root->childs)
    {
        h = max(h, height(n));
    }
    return h + 1;
}

bool find(Node *root, int data)
{
    if (root->data == data)
        return true;

    bool res = false;
    for (Node *n : root->childs)
    {
        res = res || find(n, data);
    }
    return res;
}

bool rootToNodePath_01(Node *root, int data, vector<Node *> &path)
{
    if (root->data == data)
    {
        path.push_back(root);
        return true;
    }

    path.push_back(root);
    bool res = false;
    for (Node *n : root->childs)
    {
        res = res || rootToNodePath_01(n, data, path);
    }
    if (!res)
        path.pop_back();
    return res;
}

vector<Node *> rootToNodePath_02(Node *node, int data)
{
    if (node->data == data)
    {
        vector<Node *> base;
        base.push_back(node);
        return base;
    }

    vector<Node *> myAns;
    for (Node *n : node->childs)
    {
        vector<Node *> recAns = rootToNodePath_02(n, data);
        for (Node *child : recAns)
        {
            myAns.push_back(child);
        }
        if (recAns.size() != 0)
            break;
    }

    if (myAns.size() != 0)
        myAns.push_back(node);
    return myAns;
}

vector<Node *> rootToNodePath_03(Node *node, int data)
{
    if (node->data == data)
    {
        vector<Node *> base;
        base.push_back(node);
        return base;
    }

    for (Node *n : node->childs)
    {
        vector<Node *> recAns = rootToNodePath_03(n, data);
        if (recAns.size() != 0)
        {
            recAns.push_back(node);
            return recAns;
        }
    }
    vector<Node *> myAns;
    return myAns;
}

bool isMirror(Node *node1, Node *node2)
{

    if (node1->childs.size() != node2->childs.size() || node1->data != node2->data)
        return false;

    for (int i = 0, j = node1->childs.size() - 1; i <= j; i++, j--)
    {
        bool res = isMirror(node1->childs[i], node2->childs[j]);
        if (!res)
            return false;
    }
    return true;
}

void flattern(Node *node)
{
    vector<Node *> nchilds;
    for (Node *n : node->childs)
    {
        flattern(n);

        nchilds.push_back(n);
        for (Node *child : n->childs)
        {
            nchilds.push_back(child);
        }

        n->childs.clear();
    }

    node->childs.clear();
    node->childs = nchilds;
}

Node *linear(Node *node)
{
    if (node->childs.size() == 0)
        return node;

    Node *lastTail = linear(node->childs[node->childs.size() - 1]);
    for (int i = node->childs.size() - 2; i >= 0; i--)
    {
        Node *secondLastTail = linear(node->childs[i]);
        secondLastTail->childs.push_back(node->childs[i + 1]);
        node->childs.pop_back();
    }
    return lastTail;
}

void solve()
{
    vector<int> arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 100, -1, 110, -1, -1, 90, -1, -1, 40, 120, 140, -1, 150, -1, -1, -1};
    Node *node = createTree(arr);

    // vector<Node *> ans = rootToNodePath_03(node, 80);
    // for (Node *n : ans)
    // {
    //     cout << n->data << " ";
    // }

    // flattern(node);
    linear(node);
    display(node);
}

int main()
{
    solve();
    return 0;
}