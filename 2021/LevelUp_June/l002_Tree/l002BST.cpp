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
    TreeNode *curr = root;
    while (curr->right != nullptr)
        curr = curr->right;

    return curr->val;
}

int minimum(TreeNode *root)
{
    TreeNode *curr = root;
    while (curr->left != nullptr)
        curr = curr->left;

    return curr->val;
}

bool findData(TreeNode *root, int data)
{
    TreeNode *curr = root;
    while (curr != nullptr)
    {
        if (curr->val == data)
            return true;
        else if (curr->val < data)
            curr = curr->right;
        else
            curr = curr->left;
    }
}

vector<TreeNode *> nodeToRootPath(TreeNode *root, int data)
{
    TreeNode *curr = root;
    vector<TreeNode *> ans;
    while (curr != nullptr)
    {
        ans.push_back(curr);
        if (curr->val == data)
            break;
        else if (curr->val < data)
            curr = curr->right;
        else
            curr = curr->left;
    }

    return ans;
}

TreeNode *lowestCommonAncestor(TreeNode *root, TreeNode *p, TreeNode *q)
{
    TreeNode *LCA = nullptr, *curr = root;
    while (curr != nullptr)
    {
        if (curr->val < p->val && curr->val < q->val)
            curr = curr->right;
        else if (curr->val > p->val && curr->val > q->val)
            curr = curr->left;
        else
        {
            LCA = curr;
            break;
        }
    }

    return (LCA != nullptr && findData(LCA, p->val) && findData(LCA, q->val)) ? LCA : nullptr;
}

//1382
class BalanceBST
{
public:
    vector<int> height;
    void updateHeight(TreeNode *root)
    {
        int lh = root->left != NULL ? height[root->left->val] : -1;
        int rh = root->right != NULL ? height[root->right->val] : -1;

        height[root->val] = max(lh, rh) + 1;
    }

    int getBal(TreeNode *root)
    {
        int lh = root->left != NULL ? height[root->left->val] : -1;
        int rh = root->right != NULL ? height[root->right->val] : -1;

        return lh - rh;
    }

    TreeNode *rightRotation(TreeNode *A)
    {
        TreeNode *B = A->left;
        TreeNode *BkaRight = B->right;

        B->right = A;
        A->left = BkaRight;

        B->right = getRotation(A);
        return getRotation(B);
    }

    //O(1)
    TreeNode *leftRotation(TreeNode *A)
    {
        TreeNode *B = A->right;
        TreeNode *BkaLeft = B->left;

        B->left = A;
        A->right = BkaLeft;

        B->left = getRotation(A);
        return getRotation(B);
    }

    TreeNode *getRotation(TreeNode *root)
    {

        updateHeight(root);
        if (getBal(root) >= 2) //ll,lr
        {
            if (getBal(root->left) >= 1) // ll
            {
                return rightRotation(root);
            }
            else // lr
            {
                root->left = leftRotation(root->left);
                return rightRotation(root);
            }
        }
        else if (getBal(root) <= -2) // rr,rl
        {

            if (getBal(root->right) <= -1) // rr
            {
                return leftRotation(root);
            }
            else // rl
            {
                root->right = rightRotation(root->right);
                return leftRotation(root);
            }
        }

        return root;
    }

    TreeNode *reconstructTree(TreeNode *root)
    {
        if (root == nullptr)
            return nullptr;

        root->left = reconstructTree(root->left);
        root->right = reconstructTree(root->right);

        return getRotation(root);
    }

    TreeNode *balanceBST(TreeNode *root)
    {
        height.resize((int)1e5 + 1, -1);
        return reconstructTree(root);
    }
};