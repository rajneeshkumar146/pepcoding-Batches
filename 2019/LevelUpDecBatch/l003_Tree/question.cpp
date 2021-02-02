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
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right){} *
};

//113
void pathSum(TreeNode *root, int tar, vector<vector<int>> &res, vector<int> &smallAns)
{
    if (root == nullptr)
        return;
    if (root->left == nullptr && root->right == nullptr)
    {
        if (tar - root->val == 0)
        {
            smallAns.push_back(root->val);
            res.push_back(smallAns);
            smallAns.pop_back();
        }

        return;
    }

    smallAns.push_back(root->val);
    pathSum(root->left, tar - root->val, res, smallAns);
    pathSum(root->right, tar - root->val, res, smallAns);
    smallAns.pop_back();
}

vector<vector<int>> pathSum(TreeNode *root, int targetSum)
{
    vector<vector<int>> res;
    vector<int> smallAns;
    pathSum(root, targetSum, res, smallAns);
    return res;
}


//https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/

// 124
