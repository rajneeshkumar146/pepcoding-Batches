#include <iostream>
#include <vector>

using namespace std;

struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

vector<TreeNode *> nodeToRootPath(TreeNode *node, int data)
{
    if (node == nullptr)
        return {};

    if (node->val == data)
    {
        vector<TreeNode *> base;
        base.push_back(node);
        return base;
    }

    vector<TreeNode *> left = nodeToRootPath(node->left, data);
    if (left.size() != 0)
    {
        left.push_back(node);
        return left;
    }

    vector<TreeNode *> right = nodeToRootPath(node->right, data);
    if (right.size() != 0)
    {
        right.push_back(node);
        return right;
    }

    return {};
}

TreeNode *lowestCommonAncestor(TreeNode *node, TreeNode *p, TreeNode *q)
{
    vector<TreeNode *> list1 = nodeToRootPath(node, p->val);
    vector<TreeNode *> list2 = nodeToRootPath(node, q->val);

    int i = list1.size() - 1;
    int j = list2.size() - 1;

    TreeNode *LCA = nullptr;
    while (i >= 0 && j >= 0)
    {
        if (list1[i] == list2[j])
            LCA = list1[i];

        i--;
        j--;
    }

    return LCA;
}
