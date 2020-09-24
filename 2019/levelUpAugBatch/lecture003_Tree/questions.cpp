#include <iostream>
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

TreeNode *a = nullptr, *b = nullptr, *prev_ = nullptr;
bool recoverTree_(TreeNode *root)
{
    if (root == nullptr)
        return false;

    if (recoverTree_(root->left))
        return true;

    if (prev_ != nullptr && prev_->val > root->val)
    {
        b = root;
        if (a == nullptr)
            a = prev_;
        else
            return true;
    }

    prev_ = root;
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
