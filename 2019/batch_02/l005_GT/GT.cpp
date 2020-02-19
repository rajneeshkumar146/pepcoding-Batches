#include <iostream>
#include <vector>
#include <stack>

using namespace std;

class Node
{
public:
    int data = 0;
    vector<Node *> childs;
    Node()
    {
    }

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
        if (arr[i] != -1)
        {
            Node *node = new Node(arr[i]);
            st.push(node);
        }
        else
        {
            Node *node = st.top();
            st.pop();
            st.top()->childs.push_back(node);
        }
    }

    return st.top();
}

void display(Node *node)
{

    cout << node->data << " -> ";
    for (Node *child : node->childs)
    {
        cout << child->data << ", ";
    }
    cout << endl;

    for (Node *child : node->childs)
    {
        display(child);
    }
}

int size(Node *node)
{
    int sz = 0;
    for (Node *child : node->childs)
    {
        sz += size(child);
    }
    return sz + 1;
}

int height(Node *node)
{
    int h = -1;
    for (Node *child : node->childs)
    {
        h = max(h, height(child));
    }
    return h + 1;
}

int maximum(Node *node)
{
    int maxdata = node->data;
    for (Node *child : node->childs)
    {
        maxdata = max(maxdata, maximum(child));
    }
    return maxdata;
}

bool find(Node *node, int data)
{
    bool res = false;
    if (node->data == data)
        return true;

    for (Node *child : node->childs)
    {
        res = res || find(child, data);
    }
    return res;
}

Node *getTail(Node *node)
{
    if (node->childs.size() == 0)
        return node;

    return getTail(node->childs[0]);
}

void linearTree(Node *node)
{

    for (Node *child : node->childs)
    {
        linearTree(child);
    }

    for (int i = node->childs.size() - 2; i >= 0; i--)
    {
        Node *tail = getTail(node->childs[i]);
        tail->childs.push_back(node->childs[i + 1]);

        node->childs.pop_back();
    }
}

Node *linearize_02(Node *node)
{
    if (node->childs.size() == 0) //tail.
        return node;

    Node *myTail = linearize_02(node->childs.back());
    for (int i = node->childs.size() - 2; i >= 0; i--)
    {
        Node *tail = linearize_02(node->childs[i]);
        tail->childs.push_back(node->childs.back());

        node->childs.pop_back();
    }

    return myTail;
}

bool isSymmetric(Node *node1, Node *node2)
{
    if (node1->childs.size() != node2->childs.size())
        return false;

    for (int i = 0, j = node2->childs.size() - 1; i <= j; i++, j--)
    {
        Node *child1 = node1->childs[i];
        Node *child2 = node2->childs[j];

        if (!isSymmetric(child1, child2))
            return false;
    }

    return true;
}

bool isTreeSymmetric(Node *node1, Node *node2)
{
    if (node1->childs.size() != node2->childs.size() || node1->data != node2->data)
        return false;

    for (int i = 0, j = node2->childs.size() - 1; j >= 0; i++, j--)
    {
        Node *child1 = node1->childs[i];
        Node *child2 = node2->childs[j];

        if (!isTreeSymmetric(child1, child2))
            return false;
    }

    return true;
}

void removeLeafs(Node *node)
{
    vector<Node *> nchilds;
    for (Node *child : node->childs)
    {
        if (child->childs.size() != 0)
            nchilds.push_back(child);
    }

    node->childs = nchilds;

    for (Node *child : node->childs)
    {
        removeLeafs(child);
    }
}

class allSol
{
    int height = -1;
    int size = 0;
    bool find = false;
    // int diameter = 0;

    Node *pred = nullptr;
    Node *succ = nullptr;
    Node *prev = nullptr;

    int ceil = 1e8;
    int floor = -1e8;
};

void allSolution(Node *node, int data, int level, allSol &pair)
{
    if (node == nullptr)
        return;

    pair.height = max(pair.height, level);
    pair.size++;
    pair.find = pair.find || node->data == data;

    if (node->data > data)
    {
        pair.ceil = min(pair.ceil, node->data);
    }

    if (node->data < data)
    {
        pair.floor = max(pair.floor, node->data);
    }

    if (node->data == data && pair.pred == nullptr)
    {
        pair.pred = pair.prev;
    }
    else if (pair.prev != nullptr && pair.succ == nullptr && pair.prev->data == data)
    {
        pair.succ = node;
    }

    pair.prev = node;

    for (Node *child : node->childs)
    {
        allSolution(child, data, level + 1, pair);
    }
}

void solve()
{
    vector<int> arr{10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 100, -1, 110, -1, -1, 90, -1, -1, 40, -1, -1};
    Node *node = createTree(arr);
    linearTree(node);
    display(node);
}

int main()
{
    solve();
    return 0;
}
