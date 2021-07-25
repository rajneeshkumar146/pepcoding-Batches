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

void pathSum_(TreeNode *root, int targetSum, vector<int> &smallAns, vector<vector<int>> &res)
{
    if (root == NULL)
        return;
    if (root->left == NULL && root->right == NULL)
    {
        if (targetSum - root->val == 0)
        {
            smallAns.push_back(root->val);
            res.push_back(smallAns);
            smallAns.pop_back();
        }
        return;
    }

    smallAns.push_back(root->val);

    pathSum_(root->left, targetSum - root->val, smallAns, res);
    pathSum_(root->right, targetSum - root->val, smallAns, res);

    smallAns.pop_back();
}

vector<vector<int>> pathSum(TreeNode *root, int targetSum)
{
    vector<vector<int>> res;
    vector<int> smallAns;
    pathSum_(root, targetSum, smallAns, res);
    return res;
}

vector<int> maxPathSum_01(TreeNode *root)
{
    if (root == NULL)
    {
        return {-(int)1e9, -(int)1e9};
    }

    if (root->left == NULL && root->right == NULL)
    {
        return {-(int)1e9, root->val};
    }

    auto lr = maxPathSum_01(root->left);
    auto rr = maxPathSum_01(root->right);

    int ltl = max(lr[0], rr[0]);
    if (root->left != NULL && root->right != NULL)
    {
        ltl = max(ltl, lr[1] + rr[1] + root->val);
    }

    int ntl = max(lr[1], rr[1]) + root->val;
    return {ltl, ntl};
}

int dia = 0;
int diameter(TreeNode *root)
{
    if (root == NULL)
        return 0;

    int lh = diameter(root->left);
    int rh = diameter(root->right);

    dia = max(dia, lh + 2 + rh);

    return max(lh, rh) + 1;
}