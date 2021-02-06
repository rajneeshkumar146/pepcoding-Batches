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
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right){}
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

// 124
class maxNodeToNodePair
{
public:
    int NTN_sum = -(int)1e9; // Node to Node sum
    int NTR_sum = 0;         // Node to root sum

    maxNodeToNodePair(int NTN_sum, int NTR_sum)
    {
        this->NTN_sum = NTN_sum;
        this->NTR_sum = NTR_sum;
    }

    maxNodeToNodePair()
    {
    }
};

int maxValue(vector<int> arr)
{
    int max_ = arr[0];
    for (int ele : arr)
    {
        max_ = max(max_, ele);
    }

    return max_;
}

// {nodeToNode, rootToNode}

maxNodeToNodePair maxPathSum2_(TreeNode *node)
{
    if (node == nullptr)
        return maxNodeToNodePair();

    maxNodeToNodePair lpair = maxPathSum2_(node->left);
    maxNodeToNodePair rpair = maxPathSum2_(node->right);

    maxNodeToNodePair myAns;

    myAns.NTN_sum = maxValue({lpair.NTN_sum, rpair.NTN_sum, node->val, lpair.NTR_sum + node->val,
                              rpair.NTR_sum + node->val, lpair.NTR_sum + node->val + rpair.NTR_sum});

    myAns.NTR_sum = maxValue({node->val, lpair.NTR_sum + node->val, rpair.NTR_sum + node->val});

    return myAns;
}

int maxPathSum(TreeNode *root)
{
    if (root == nullptr)
        return 0;
    return maxPathSum2_(root).NTN_sum;
}