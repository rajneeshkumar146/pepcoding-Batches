#include <iostream>
#include <list>
#include <vector>

using namespace std;

class TreeNode
{
public:
    int val = 0;
    TreeNode *left = NULL;
    TreeNode *right = NULL;

    TreeNode(int val)
    {
        this->val = val;
    }
};

void levelOrderLineWise(TreeNode *root)
{
    list<TreeNode *> que;
    que.push_back(root);
    int level = 0;

    while (que.size() != 0)
    {
        int size = que.size();
        cout << "Level " << level << ": ";
        while (size-- > 0)
        {
            TreeNode *rn = que.front();
            que.pop_front();

            cout << rn->val << ", ";
            if (rn->left != NULL)
                que.push_back(rn->left);
            if (rn->right != NULL)
                que.push_back(rn->right);
        }
        level++;
    }
}

class vpair
{
public:
    TreeNode *node = NULL;
    int vl = 0;

    vpair(TreeNode *node, int vl)
    {
        this->node = node;
        this->vl = vl;
    }
};

// {min,max}
void widthOfShadow(TreeNode *root, int vl, vector<int> &minMax)
{
    if (root == NULL)
        return;

    minMax[0] = min(minMax[0], vl);
    minMax[1] = max(minMax[1], vl);

    widthOfShadow(root->left, vl - 1, minMax);
    widthOfShadow(root->right, vl + 1, minMax);
}

vector<vector<int>> verticalOrder(TreeNode *root)
{
    if (root == NULL)
        return {};

    vector<int> minMax(2, 0);
    widthOfShadow(root, 0, minMax);
    int width = minMax[1] - minMax[0] + 1;
    vector<vector<int>> ans(width);

    list<vpair> que;
    que.push_back(vpair(root, abs(minMax[0])));

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            vpair p = que.front();
            que.pop_front();
            TreeNode *node = p.node;
            int vl = p.vl;

            ans[vl].push_back(node->val);
            if (node->left != NULL)
                que.push_back(vpair(node->left, vl - 1));
            if (node->right != NULL)
                que.push_back(vpair(node->right, vl + 1));
        }
    }

    return ans;
}

vector<int> bottomOrder(TreeNode *root)
{
    if (root == NULL)
        return {};

    vector<int> minMax(2, 0);
    widthOfShadow(root, 0, minMax);
    int width = minMax[1] - minMax[0] + 1;
    vector<int> ans(width);

    list<vpair> que;
    que.push_back(vpair(root, abs(minMax[0])));

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            vpair p = que.front();
            que.pop_front();
            TreeNode *node = p.node;
            int vl = p.vl;

            ans[vl] = node->val;

            if (node->left != NULL)
                que.push_back(vpair(node->left, vl - 1));
            if (node->right != NULL)
                que.push_back(vpair(node->right, vl + 1));
        }
    }

    return ans;
}

vector<vector<int>> bottomOrder_Value(TreeNode *root)
{
    if (root == NULL)
        return {};

    vector<int> minMax(2, 0);
    widthOfShadow(root, 0, minMax);
    int width = minMax[1] - minMax[0] + 1;
    vector<vector<int>> ans(width);
    vector<int> hlevel(width, -1);

    list<vpair> que;
    que.push_back(vpair(root, abs(minMax[0])));

    int level = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            vpair p = que.front();
            que.pop_front();
            TreeNode *node = p.node;
            int vl = p.vl;

            if (level > hlevel[vl])
            {
                ans[vl].clear();
                hlevel[vl] = level;
            }
            ans[vl].push_back(node->val);

            if (node->left != NULL)
                que.push_back(vpair(node->left, vl - 1));
            if (node->right != NULL)
                que.push_back(vpair(node->right, vl + 1));
        }
        level++;
    }

    return ans;
}

vector<int> verticalSum(TreeNode *root)
{
    if (root == NULL)
        return {};

    vector<int> minMax(2, 0);
    widthOfShadow(root, 0, minMax);
    int width = minMax[1] - minMax[0] + 1;
    
    vector<int> ans(width,0);
    list<vpair> que;
    que.push_back(vpair(root, abs(minMax[0])));

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            vpair p = que.front();
            que.pop_front();
            TreeNode *node = p.node;
            int vl = p.vl;

            ans[vl] += node->val;

            if (node->left != NULL)
                que.push_back(vpair(node->left, vl - 1));
            if (node->right != NULL)
                que.push_back(vpair(node->right, vl + 1));
        }
    }

    return ans;
}