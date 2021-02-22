#include <iostream>
#include <vector>
#include <stack>
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

//968
// -1 : is camera required
//  0 : i'm covered
// 1 : i'm a camera
int cameras = 0;

int minCameraCover_(TreeNode *node)
{
    if (node == nullptr)
        return 0;

    int lans = minCameraCover_(node->left);
    int rans = minCameraCover_(node->right);

    if (lans == -1 || rans == -1)
    {
        cameras++;
        return 1;
    }

    if (lans == 1 || rans == 1)
    {
        return 0;
    }

    return -1;
}

int minCameraCover(TreeNode *root)
{
    int ans = minCameraCover_(root);
    if (ans == -1)
        return cameras + 1;
    return cameras;
}

//337
vector<int> rob_(TreeNode *root)
{
    if (root == nullptr)
    {
        return {0, 0};
    }

    vector<int> lans = rob_(root->left);
    vector<int> rans = rob_(root->right);

    vector<int> myAns{0, 0};
    myAns[0] = lans[1] + root->val + rans[1];
    myAns[1] = max(lans[0], lans[1]) + max(rans[0], rans[1]);

    return myAns;
}

int rob(TreeNode *root)
{
    if (root == nullptr)
        return 0;
    vector<int> ans = rob_(root);
    return max(ans[0], ans[1]);
}

// 230
void insertAllLeft(stack<TreeNode *> &st, TreeNode *node)
{
    while (node != nullptr)
    {
        st.push(node);
        node = node->left;
    }
}

int kthSmallest(TreeNode *root, int k)
{
    stack<TreeNode *> st;
    insertAllLeft(st, root);
    while (k-- > 1)
    {
        TreeNode *node = st.top();
        st.pop();
        insertAllLeft(st, node->right);
    }

    return st.top()->val;
}

//653
void insertAllRight(stack<TreeNode *> &st, TreeNode *node)
{
    while (node != nullptr)
    {
        st.push(node);
        node = node->right;
    }
}

bool findTarget(TreeNode *root, int k)
{
    if (root == nullptr)
        return false;

    stack<TreeNode *> lst;
    stack<TreeNode *> rst;

    insertAllLeft(lst, root);
    insertAllRight(rst, root);

    while (lst.top()->val < rst.top()->val)
    {
        int sum = lst.top()->val + rst.top()->val;
        if (sum == k)
            return true;
        else if (sum < k)
        {
            TreeNode *node = lst.top();
            lst.pop();
            insertAllLeft(lst, node->right);
        }
        else
        {
            TreeNode *node = rst.top();
            rst.pop();
            insertAllRight(rst, node->left);
        }
    }

    return false;
}
