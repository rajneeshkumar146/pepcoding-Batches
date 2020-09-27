#include <iostream>
#include <vector>

using namespace std;

class Node
{
public:
    int data = 0;
    Node *left = nullptr;
    Node *right = nullptr;

    Node(int data)
    {
        this->data = data;
    }
};

Node *constructTree(vector<int> &arr, int si, int ei)
{
    if (si > ei)
        return nullptr;

    int mid = (si + ei) / 2;
    Node *node = new Node(arr[mid]);
    node->left = constructTree(arr, si, mid - 1);
    node->right = constructTree(arr, mid + 1, ei);

    return node;
}

int maximum(Node *node)
{
    Node *curr = node;
    while (curr->right != nullptr)
    {
        curr = curr->right;
    }

    return curr->data;
}

int minimum(Node *node)
{
    Node *curr = node;
    while (curr->left != nullptr)
    {
        curr = curr->left;
    }

    return curr->data;
}

bool find(Node *node, int data)
{
    Node *curr = node;

    while (curr != nullptr)
    {
        if (curr->data == data)
            return true;
        else if (curr->data < data)
            curr = curr->right;
        else
            curr = curr->left;
    }

    return false;
}

Node *insertIntoBST(Node *root, int val)
{
    if (root == nullptr)
        return new Node(val);

    if (val < root->data)
        root->left = insertIntoBST(root->left, val);
    else
        root->right = insertIntoBST(root->right, val);

    return root;
}

Node *deleteNode(Node *root, int val)
{
    if (root == nullptr)
        return nullptr;

    if (val < root->data)
        root->left = deleteNode(root->left, val);
    else if (val > root->data)
        root->right = deleteNode(root->right, val);
    else
    {

        if (root->left == nullptr || root->right == nullptr)
        {
            Node *node = root->left != nullptr ? root->left : root->right;
            delete root; // not for java.
            return node;
        }

        int mVal = maximum(root->left);
        root->data = mVal;
        root->left = deleteNode(root->left, mVal);
    }

    return root;
}

void allSolution(Node *node, int data)
{
    Node *curr = node;
    Node *pred = nullptr;
    Node *succ = nullptr;

    while (curr != nullptr)
    {
        if (curr->data == data)
        {

            if (curr->left != nullptr)
            {
                pred = curr->left;
                while (pred->right != nullptr)
                    pred = pred->right;
            }

            if (curr->right != nullptr)
            {

                succ = curr->right;
                while (succ->left != nullptr)
                    succ = succ->left;
            }

            break;
        }
        else if (curr->data < data)
        {
            pred = curr;
            curr = curr->right;
        }
        else
        {
            succ = curr;
            curr = curr->left;
        }
    }
}


void solve()
{
    vector<int> arr;
    for (int i = 1; i <= 14; i++)
        arr.push_back(i * 10);

    Node *node = constructTree(arr, 0, arr.size() - 1);
}

int main()
{
    solve();
    return 0;
}
