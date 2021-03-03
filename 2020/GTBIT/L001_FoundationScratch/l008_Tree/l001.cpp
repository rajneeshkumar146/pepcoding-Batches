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

// Traversal Of Tree
void preOrderTraversal(Node *root, vector<int> &ans)
{
    if (root == nullptr)
        return;

    ans.push_back(root->data);
    preOrderTraversal(root->left, ans);
    preOrderTraversal(root->right, ans);
}

void inOrderTraversal(Node *root, vector<int> &ans)
{
    if (root == nullptr)
        return;

    inOrderTraversal(root->left, ans);
    ans.push_back(root->data);
    inOrderTraversal(root->right, ans);
}

void postOrderTraversal(Node *root, vector<int> &ans)
{
    if (root == nullptr)
        return;

    postOrderTraversal(root->left, ans);
    postOrderTraversal(root->right, ans);
    ans.push_back(root->data);
}

void traversal(Node *root)
{
    vector<int> ans;
    preOrderTraversal(root, ans);
    // inOrderTraversal(root, ans);
    // postOrderTraversal(root, ans);
}
