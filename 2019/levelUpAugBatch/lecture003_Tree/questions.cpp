#include <iostream>
#include <stack>
#include <vector>
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

//510
Node *inorderSuccessor(Node *node)
{

    Node *curr = node;
    Node *succ = nullptr;
    if (node->right != nullptr)
    {
        succ = curr->right;
        while (succ->left != nullptr)
        {
            succ = succ->left;
        }

        return succ;
    }

    while (curr->parent != nullptr && curr->parent->val < node->val)
    {
        curr = curr->parent;
    }

    return curr->parent != nullptr ? curr->parent : nullptr;
}

Node *inorderSuccessor(Node *node)
{

    Node *curr = node;
    Node *succ = nullptr;
    if (node->right != nullptr)
    {
        succ = curr->right;
        while (succ->left != nullptr)
        {
            succ = succ->left;
        }

        return succ;
    }

    while (curr->parent != nullptr)
    {
        if (curr->parent->left == curr)
            return curr->parent;
        curr = curr->parent;
    }

    return nullptr;
}

//235
TreeNode *lowestCommonAncestor(TreeNode *root, TreeNode *p, TreeNode *q)
{

    TreeNode *curr = root;
    while (curr != nullptr)
    {
        if (curr->val < p->val && curr->val < q->val)
            curr = curr->right;
        else if (curr->val > p->val && curr->val > q->val)
            curr = curr->left;
        else
            break;
    }

    return curr;
}

//Leetcode 173
class BSTIterator
{
public:
    stack<TreeNode *> st;
    BSTIterator(TreeNode *root)
    {
        allLeft(root);
    }

    void allLeft(TreeNode *node)
    {
        while (node != nullptr)
        {
            st.push(node);
            node = node->left;
        }
    }

    int next()
    {
        TreeNode *node = st.top();
        st.pop();
        allLeft(node->right);

        return node->val;
    }

    bool hasNext()
    {
        return st.size() != 0;
    }
};

class Solution
{
    int getHeight(TreeNode *node)
    {
        return node == nullptr ? -1 : max(getHeight(node->left), getHeight(node->right)) + 1;
    }

    int getBalance(TreeNode *node)
    {
        int lh = getHeight(node->left);
        int rh = getHeight(node->right);

        return lh - rh;
    }

    TreeNode *rightRotation(TreeNode *A) // O(1)
    {
        TreeNode *B = A->left;
        TreeNode *BKaRight = B->right;

        B->right = A;
        A->left = BKaRight;

        B->right = getRotation(A);
        return getRotation(B);
    }

    TreeNode *leftRotation(TreeNode *A) //O(1)
    {
        TreeNode *B = A->right;
        TreeNode *BKaLeft = B->left;

        B->left = A;
        A->right = BKaLeft;

        B->left = getRotation(A);
        return getRotation(B);
    }

    TreeNode *getRotation(TreeNode *node) // O(1)
    {

        if (getBalance(node) >= 2) // ll,lr
        {
            if (getBalance(node->left) >= 1) // ll
            {
                return rightRotation(node);
            }
            else // lr
            {
                node->left = leftRotation(node->left);
                return rightRotation(node);
            }
        }
        else if (getBalance(node) <= -2) // rr,rl
        {
            if (getBalance(node->right) >= 1) // rl
            {
                node->right = rightRotation(node->right);
                return leftRotation(node);
            }
            else // rr
            {
                return leftRotation(node);
            }
        }

        return node;
    }

public:
    TreeNode *balanceBST(TreeNode *root)
    {
        if (root == nullptr)
            return nullptr;

        root->left = balanceBST(root->left);
        root->right = balanceBST(root->right);

        return getRotation(root);
    }
};

// Leetcode BSTree from pre order
int idx = 0;
TreeNode *buildTree(vector<int> &arr, int lrange, int rrange)
{
    if (idx >= arr.size() || arr[idx] < lrange || arr[idx] > rrange)
        return nullptr;

    int data = arr[idx++];
    TreeNode *node = new TreeNode(data);

    node->left = buildTree(arr, lrange, data);
    node->right = buildTree(arr, data, rrange);

    return node;
}

// BSTree from post order
// BSTree from Level order

void addAllLeft(TreeNode *node, stack<TreeNode *> &st)
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
    addAllLeft(root, st);

    while (--k > 0)
    {
        TreeNode *rn = st.top();
        st.pop();
        addAllLeft(rn->right, st);
    }
    return st.top()->val;
}
