#include <iostream>
#include <vector>
using namespace std;

class TreeNode
{
public:
    int val = 0;
    TreeNode *left = nullptr;
    TreeNode *right = nullptr;

    TreeNode(int val)
    {
        this->val = val;
    }
};

int size(TreeNode *root)
{
    return root == nullptr ? 0 : size(root->left) + size(root->right) + 1;
}

int height(TreeNode *root)
{
    return root == nullptr ? -1 : max(height(root->left), height(root->right)) + 1;
}

int maximum(TreeNode *root)
{
    return root == nullptr ? -(int)1e9 : max(root->val, max(maximum(root->left), maximum(root->right)));
}

int minimum(TreeNode *root)
{
    return root == nullptr ? (int)1e9 : min(root->val, min(minimum(root->left), minimum(root->right)));
}

bool find(TreeNode *root, int data)
{
    if (root == nullptr)
        return false;

    if (root->val == data)
        return true;

    return find(root->left, data) || find(root->right, data);
}

bool nodeToRootPath_(TreeNode *root, int data, vector<TreeNode *> &ans)
{

    if (root == nullptr)
        return false;

    if (root->val == data)
    {
        ans->push_back(root);
        return true;
    }

    // if (nodeToRootPath_(root->left, data, ans))
    // {
    //     ans->push_back(root);
    //     return true;
    // }

    // if (nodeToRootPath_(root->right, data, ans))
    // {
    //     ans->push_back(root);
    //     return true;
    // }

    // return false;

    bool res = nodeToRootPath_(root->left, data, ans) || nodeToRootPath_(root->right, data, ans);

    if (res)
        ans.push_back(root);
    return res;
}

vector<TreeNode *> nodeToRootPath_(TreeNode *root, int data)
{
    if (root == nullptr)
    {
        return {};
    }

    if (root->val == data)
    {
        return {root};
    }

    vector<TreeNode *> left = nodeToRootPath_(root->left, data);
    if (left.size() != 0)
    {
        left.push_back(root);
        return left;
    }

    vector<TreeNode *> right = nodeToRootPath_(root->right, data);
    if (right.size() != 0)
    {
        right.push_back(root);
        return right;
    }

    return {};
}

vector<TreeNode *> nodeToRootPath(TreeNode *root, int data)
{
    return nodeToRootPath_(root, data);
}

void rootToAllLeafPath(TreeNode *root, vector<vector<int>> &ans, vector<int> &smallAns)
{
    if (root == nullptr)
        return;
    if (root->left == nullptr && root->right == nullptr)
    {
        smallAns.push_back(root->val);
        ans.push_back(smallAns);
        smallAns.pop_back();

        return;
    }

    smallAns.push_back(root->val);
    rootToAllLeafPath(root->left, ans, smallAns);
    rootToAllLeafPath(root->right, ans, smallAns);
    smallAns.pop_back();
}

vector<vector<int>> rootToAllLeafPath(TreeNode *root)
{
    vector<vector<int>> ans;
    vector<int> smallAns;

    rootToAllLeafPath(root, ans, smallAns);
    return ans;
}




int main()
{

    return 0;
}