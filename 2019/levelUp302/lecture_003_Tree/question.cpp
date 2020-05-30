#include <iostream>
#include <vector>

using namespace std;

struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

//Leetcode: 98 - validate-binary-search-tree================

long long prevData = -1e12;
bool isValidBST(TreeNode *root)
{
    if (root == nullptr)
        return true;

    if (!isValidBST(root->left))
        return false;

    if (prevData >= root->val)
        return false;
        
    prevData = root->val;

    if (!isValidBST(root->right))
        return false;

    return true;
}

//Leetcode 99: recover-binary-search-tree==========================

TreeNode *a = nullptr;
TreeNode *b = nullptr;
TreeNode *c = nullptr; // previous.

bool recoverTree_(TreeNode *root)
{
    if (root == nullptr)
        return false;

    if (recoverTree_(root->left))
        return true;

    if (c != nullptr && c->val > root->val)
    {
        b = root; // first time it may be wrong.
        if (a == nullptr)
            a = c; // a always br previous
        else
            return true;
    }
    
    c = root;  // set previous.

    if (recoverTree_(root->right))
        return true;

    return false;
}

void recoverTree(TreeNode *root)
{
    recoverTree_(root);
    if (a != nullptr)
    {
        int temp = a->val;
        a->val = b->val;
        b->val = temp;
    }
}