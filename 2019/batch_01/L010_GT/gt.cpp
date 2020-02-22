#include <iostream>
#include <vector>
#include <stack>
#include <queue>

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
    for (int i = 0; i < arr.size() - 1; i++)
    {
        if (arr[i] == -1)
        {
            Node *node = st.top();
            st.pop();
            st.top()->childs.push_back(node);
        }
        else
        {
            st.push(new Node(arr[i]));
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

int size(Node *root)
{
    int s = 0;

    for (Node *child : root->childs)
    {
        s += size(child);
    }
    return s + 1;
}

int height(Node *root)
{
    int h = -1;
    for (Node *child : root->childs)
    {
        h = max(h, height(child));
    }
    return h + 1;
}

bool find(Node *root, int data)
{
    if (root->data == data)
        return true;

    bool res = false;
    for (Node *child : root->childs)
    {
        res = res || find(child, data);
    }

    return res;
}

vector<Node *> nodeToRootPath(Node *node, int data)
{
    if (node->data == data)
    {
        vector<Node *> base;
        base.push_back(node);
        return base;
    }

    for (Node *child : node->childs)
    {
        vector<Node *> list = nodeToRootPath(child, data);
        if (list.size() != 0)
        {
            list.push_back(node);
            return list;
        }
    }

    vector<Node *> ans;
    return ans;
}

void LCA(Node *node, int data1, int data2)
{
    vector<Node *> list1 = nodeToRootPath(node, data1);
    vector<Node *> list2 = nodeToRootPath(node, data2);

    int i = list1.size() - 1;
    int j = list2.size() - 1;

    int lca = -1;
    while (i >= 0 && j >= 0)
    {
        if (list1[i]->data != list2[j]->data)
        {
            break;
        }

        lca = list1[i]->data;
        i--;
        j--;
    }

    cout << lca << endl;
}

void kaway(Node *node, Node *avoid, int level)
{
    if (node == avoid)
        return;

    if (level == 0)
    {
        cout << node->data << " ";
        return;
    }

    for (Node *child : node->childs)
    {
        kaway(child, avoid, level - 1);
    }
}

void kfar(Node *node, int k, int data)
{
    vector<Node *> list = nodeToRootPath(node, data);
    Node *avoid = nullptr;

    for (int i = 0; i < list.size(); i++)
    {
        kaway(list[i], avoid, k - i);
        avoid = list[i];
    }
}

void levelOrderLineWise(Node *node)
{
    queue<Node *> que;
    que.push(node);
    int level = 0;

    while (que.size() != 0)
    {
        int size = que.size();

        cout << "Level: " << level << " -> ";
        while (size-- > 0)
        {
            Node *rnode = que.front();
            que.pop();
            cout << rnode->data << ", ";
            for (Node *child : rnode->childs)
            {
                que.push(child);
            }
        }

        level++;
        cout << endl;
    }
    cout << endl;
}

bool isSymmetricTree_(Node *node1, Node *node2)
{
    if (node1->childs.size() != node2->childs.size() || node1->data != node2->data)
        return false;

    for (int i = 0, j = node1->childs.size() - 1; i <= j; i++, j--)
    {
        Node *child1 = node1->childs[i];
        Node *child2 = node2->childs[j];

        if (!isSymmetricTree_(child1, child2))
            return false;
    }

    return true;
}

void isSymmetricTree(Node *node)
{
    cout << isSymmetricTree_(node, node) << endl;
}

bool isMirror(Node *node1, Node *node2)
{

    if (node1->childs.size() != node2->childs.size() || node1->data != node2->data)
        return false;

    for (int i = 0, j = node1->childs.size() - 1; j >= 0; i++, j--)
    {
        Node *child1 = node1->childs[i];
        Node *child2 = node2->childs[j];

        if (!isMirror(child1, child2))
            return false;
    }

    return true;
}

void solve()
{
    vector<int> arr{10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 100, -1, 110, -1, -1, 90, -1, -1, 40, -1, -1};
    // vector<int> arr{10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 100, -1, 110, -1, -1, 90, -1, -1, 40,50,-1,60,-1, -1, -1};
    vector<int> arr1{10, 40, -1, 30, 90, -1, 80, 110, -1, 100, -1,-1, 70, -1, -1, 20, 60, -1, 50, -1, -1, -1};

    Node *node = createTree(arr);
    // display(node);

    // kfar(node, 2, 30);
    // LCA(node, 80, 80);
    // levelOrderLineWise(node);
    // isSymmetricTree(node);

    cout << isMirror(createTree(arr), createTree(arr1))<<endl;
}

int main()
{
    solve();
    return 0;
}
