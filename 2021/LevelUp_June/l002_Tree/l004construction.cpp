#include <iostream>
#include <vector>
#include <list>

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

TreeNode *constructFromInOrder(vector<int> &inOrder, int si, int ei)
{
    if (si > ei)
        return nullptr;
    int mid = (si + ei) / 2;
    TreeNode *root = new TreeNode(inOrder[mid]);

    root->left = constructFromInOrder(inOrder, si, mid - 1);
    root->right = constructFromInOrder(inOrder, mid + 1, ei);

    return root;
}

TreeNode *constructFromInOrder(vector<int> &inOrder)
{
    return constructFromInOrder(inOrder, 0, inOrder.size() - 1);
}

TreeNode *getMidNode(TreeNode *head)
{
    if (head == nullptr || head->right == nullptr)
        return head;
    TreeNode *slow = head, *fast = head;
    while (fast->right != nullptr && fast->right->right != nullptr)
    {
        fast = fast->right->right;
        slow = slow->right;
    }

    return slow;
}

TreeNode *dllToBST(TreeNode *head)
{
    if (head == nullptr || head->right == nullptr)
        return head;

    TreeNode *midNode = getMidNode(head);
    TreeNode *prev = midNode->left, *forw = midNode->right;

    midNode->left = midNode->right = forw->left = nullptr;
    if (prev != nullptr)
        prev->right = nullptr;

    TreeNode *root = midNode, *leftHead = (prev != nullptr ? head : nullptr), *rightHead = forw;

    root->left = dllToBST(leftHead);
    root->right = dllToBST(rightHead);

    return root;
}
