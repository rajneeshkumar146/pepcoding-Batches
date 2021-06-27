#include <iostream>
#include <unordered_set>
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
        ans.push_back(root);
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

void burningTreeNodeWithWater(TreeNode *root, int time, TreeNode *blockNode, unordered_set<int> &waterSet,
                              vector<vector<int>> &ans)
{
    if (root == nullptr || root == blockNode || waterSet.find(root->val) != waterSet.end())
        return;
    if (time == ans.size()) // if(time == ans.size()) ans.push_back({});
        ans.push_back({});
    ans[time].push_back(root->val);

    burningTreeNodeWithWater(root->left, time + 1, blockNode, waterSet, ans);
    burningTreeNodeWithWater(root->right, time + 1, blockNode, waterSet, ans);
}

int burningTreeWithWater(TreeNode *root, int fireNode, unordered_set<int> &waterSet,
                         vector<vector<int>> ans)
{
    if (root == nullptr)
        return -1;
    if (root->val == fireNode)
    {
        if (waterSet.find(root->val) == waterSet.end())
        { // foor cpp : map.find(root->val) != map.end();
            burningTreeNodeWithWater(root, 0, nullptr, waterSet, ans);
            return 1;
        }
        return -2; // fire node is present but it have water.
    }

    int lt = burningTreeWithWater(root->left, fireNode, waterSet, ans);
    if (lt > 0)
    {
        if (waterSet.find(root->val) == waterSet.end())
        {
            burningTreeNodeWithWater(root, lt, root->left, waterSet, ans);
            return lt + 1;
        }
        return -2; // fire node is present but it have water.
    }

    if (lt == -2)
        return -2;

    int rt = burningTreeWithWater(root->right, fireNode, waterSet, ans);
    if (rt > 0)
    {
        if (waterSet.find(root->val) == waterSet.end())
        {
            burningTreeNodeWithWater(root, rt, root->right, waterSet, ans);
            return rt + 1;
        }
        return -2; // fire node is present but it have water.
    }
    if (rt == -2)
        return -2;

    return -1;
}

void burningTreeWithWater(TreeNode *root, int data)
{
    unordered_set<int> waterSet;
    vector<vector<int>> ans;

    burningTreeWithWater(root, data, waterSet, ans);
    for (vector<int> &ar : ans)
    {
        for (int ele : ar)
            cout << ele << " ";
        cout << endl;
    }
}

TreeNode *lowestCommonAncestor(TreeNode *root, TreeNode *p, TreeNode *q)
{
    vector<TreeNode *> list1, list2;
    nodeToRootPath_(root, p->val, list1);
    nodeToRootPath_(root, q->val, list2);

    int i = list1.size() - 1, j = list2.size() - 1;
    TreeNode *LCA = nullptr;

    while (i >= 0 && j >= 0)
    {
        if (list1[i] != list2[j])
            break;

        LCA = list1[i];
        i--;
        j--;
    }

    return LCA;
}

TreeNode *LCANode = nullptr;
bool LCA(TreeNode *root, TreeNode *p, TreeNode *q)
{
    if (root == nullptr)
        return false;
    bool selfPresent = (root == p || root == q);

    bool leftPresent = LCA(root->left, p, q);
    if (LCANode != nullptr)
        return true;

    bool rightPresent = LCA(root->right, p, q);
    if (LCANode != nullptr)
        return true;

    if ((leftPresent && rightPresent) || (leftPresent && selfPresent) || (rightPresent && selfPresent))
        LCANode = root;

    return leftPresent || rightPresent || selfPresent;
}

TreeNode *lowestCommonAncestor(TreeNode *root, TreeNode *p, TreeNode *q)
{
    LCA(root, p, q);
    return LCANode;
}

int main()
{

    return 0;
}