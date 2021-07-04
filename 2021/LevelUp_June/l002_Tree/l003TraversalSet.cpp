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

TreeNode *getRightMostNode(TreeNode *node, TreeNode *curr)
{
    while (node->right != nullptr && node->right != curr)
    {
        node = node->right;
    }

    return node;
}

bool isValidBST(TreeNode *root)
{
    TreeNode *curr = root;
    TreeNode *prev = nullptr;
    bool flag = true;
    while (curr != nullptr)
    {
        TreeNode *left = curr->left;
        if (left == nullptr)
        {

            if (prev != nullptr && prev->val >= curr->val)
            {
                flag = false;
            }
            prev = curr;

            // cout << curr->val << endl;

            curr = curr->right;
        }
        else
        {
            TreeNode *rightMostNode = getRightMostNode(left, curr);
            if (rightMostNode->right == nullptr)
            {                                // thread creation block
                rightMostNode->right = curr; // thread is created

                curr = curr->left;
            }
            else
            {                                   // thread destroy block
                rightMostNode->right = nullptr; // thread is cut down

                if (prev->val >= curr->val)
                {
                    flag = false;
                }
                prev = curr;

                curr = curr->right;
            }
        }
    }

    return flag;
}

void insertAllLeft(TreeNode *node, list<TreeNode *> &st)
{
    while (node != nullptr)
    {
        st.push_front(node);
        node = node->left;
    }
}

bool isValidBST(TreeNode *root)
{
    list<TreeNode *> st;
    insertAllLeft(root, st);
    long prev = -(long)1e13;
    while (st.size() != 0)
    {
        TreeNode *rnode = st.front();
        st.pop_front();

        if (prev >= rnode->val)
            return false;
        prev = rnode->val;

        insertAllLeft(rnode->right, st);
    }

    return true;
}

class BSTIterator
{
private:
    list<TreeNode *> st;

public:
    BSTIterator(TreeNode *root)
    {
        insertAllLeft(root);
    }

private:
    void insertAllLeft(TreeNode *node)
    {
        while (node != nullptr)
        {
            st.push_front(node);
            node = node->left;
        }
    }

public:
    int next()
    {
        TreeNode *rn = st.front();
        st.pop_front();
        insertAllLeft(rn->right);
        return rn->val;
    }

    bool hasNext()
    {
        return st.size() != 0;
    }
};

TreeNode *cdll(TreeNode *root)
{
    TreeNode *dummy = new TreeNode(-1);
    TreeNode *curr = root, *prev = dummy;
    while (curr != nullptr)
    {
        TreeNode *left = curr->left;
        if (left == nullptr)
        {
            prev->right = curr;
            curr->left = prev;
            prev = prev->right;

            curr = curr->right;
        }
        else
        {
            TreeNode *rightMostNode = getRightMostNode(left, curr);
            if (rightMostNode->right == nullptr)
            {
                rightMostNode->right = curr;
                curr = curr->left;
            }
            else
            {

                rightMostNode->right = nullptr;

                prev->right = curr;
                curr->left = prev;
                prev = prev->right;

                curr = curr->right;
            }
        }
    }

    TreeNode *head = dummy->right;
    dummy->right = head->left = nullptr;

    // for circular doubly linkedlist
    head->left = prev;
    prev->right = head;

    return head;
}