#include <iostream>
#include <vector>
using namespace std;

class Node
{
public:
    int data = 0;
    Node *left = NULL;
    Node *right = NULL;

    Node(int data, Node *left, Node *right)
    {
        this->data = data;
        this->left = left;
        this->right = right;
    }
};

int idx = 0;
Node *create(vector<int> &arr)
{
    if (idx == arr.size() || arr[idx] == -1)
    {
        idx++;
        return NULL;
    }

    Node *nnode = new Node(arr[idx], NULL, NULL);
    idx++;
    nnode->left = create(arr);
    nnode->right = create(arr);
    return nnode;
}

void display(Node *node)
{
    if (node == nullptr)
        return;
    string str = "";

    str += node->left == nullptr ? "." : to_string(node->left->data);
    str += " -> " + to_string(node->data) + " <- ";
    str += node->right == nullptr ? "." : to_string(node->right->data);
    cout << str << endl;

    display(node->left);
    display(node->right);
}

bool rootToNodePath(Node *node, int data, vector<Node *> &path)
{
    if (node == NULL)
        return false;

    if (node->data == data)
    {
        path.push_back(node);
        return true;
    }

    bool res = false;
    res = res || rootToNodePath(node->left, data, path);
    res = res || rootToNodePath(node->right, data, path);
    if (res)
        path.push_back(node);
    return res;
}

void solve()
{
    vector<int> arr = {10, 20, 30, -1, -1, 40, -1, -1, 50, 60, 80, -1, -1, -1, 70, 90, -1, 100, -1, -1, -1};
    Node *root = create(arr);
    // display(root);

    vector<Node *> path;
    rootToNodePath(root, 100, path);
    for (Node *n : path)
    {
        cout << n->data << " ";
    }
}

int main()
{
    solve();
    return 0;
}