#include <iostream>
#include <vector>
#include <list>
#include <stringstream>

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

TreeNode *mergeTwoSortedDLL(TreeNode *l1, TreeNode *l2)
{
    if (l1 == nullptr || l2 == nullptr)
        return l1 != nullptr ? l1 : l2;

    TreeNode *dummy = new TreeNode(-1);
    TreeNode *prev = dummy, *c1 = l1, *c2 = l2;

    while (c1 != nullptr && c2 != nullptr)
    {
        if (c1->val <= c2->val)
        {
            prev->right = c1;
            c1->left = prev;

            c1 = c1->right;
        }
        else
        {
            prev->right = c2;
            c2->left = prev;

            c2 = c2->right;
        }

        prev = prev->right;
    }

    if (c1 != nullptr)
    {
        prev->right = c1;
        c1->left = prev;
    }
    else
    {
        prev->right = c2;
        c2->left = prev;
    }

    TreeNode *head = dummy->right;
    dummy->right = head->left = nullptr;

    return head;
}

TreeNode *mergeSort(TreeNode *head)
{
    if (head == nullptr || head->right == nullptr)
        return head;

    TreeNode *midNode = getMidNode(head);
    TreeNode *forwHead = midNode->right;
    forwHead->left = midNode->right = nullptr;

    return mergeTwoSortedDLL(mergeSort(head), mergeSort(forwHead));
}

TreeNode *getRightMostNode(TreeNode *node, TreeNode *curr)
{
    while (node->right != nullptr && node->right != curr)
    {
        node = node->right;
    }

    return node;
}

TreeNode *dll(TreeNode *root)
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

    return head;
}

void display(TreeNode *node)
{
    if (node == nullptr)
        return;

    string str = "";
    str += ((node->left != nullptr ? to_string(node->left->val) : "."));
    str += (" -> " + to_string(node->val) + " <- ");
    str += ((node->right != nullptr ? to_string(node->right->val) : "."));

    cout << str << endl;

    display(node->left);
    display(node->right);
}

TreeNode *BTToBST(TreeNode *root)
{
    if (root == nullptr)
        return nullptr;

    TreeNode *head = dll(root); // Binary Tree to Doubly Lihnked List
    head = mergeSort(head);     // doubly Linked List to Sorted Doubly Linked List
    root = dllToBST(head);

    return root;
}

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

// idx: {0 :: actual idx}
TreeNode *bstFromPreorder(vector<int> &preorder, int lr, int rr, vector<int> &idx)
{
    int i = idx[0];
    if (i >= preorder.size() || preorder[i] < lr || preorder[i] > rr)
        return nullptr;

    TreeNode *root = new TreeNode(preorder[i]);
    idx[0]++;

    root->left = bstFromPreorder(preorder, lr, root->val, idx);
    root->right = bstFromPreorder(preorder, root->val, rr, idx);

    return root;
}

TreeNode *bstFromPreorder(vector<int> &preorder)
{
    vector<int> idx(1, 0);
    return bstFromPreorder(preorder, -1e9, 1e9, idx);
}

// idx: {0 :: actual idx}
TreeNode *bstFromPostorder(vector<int> &postorder, int lr, int rr, vector<int> &idx)
{
    int i = idx[0];
    if (i <= -1 || postorder[i] < lr || postorder[i] > rr)
        return nullptr;

    TreeNode *root = new TreeNode(postorder[i]);
    idx[0]--;

    root->right = bstFromPostorder(postorder, root->val, rr, idx);
    root->left = bstFromPostorder(postorder, lr, root->val, idx);

    return root;
}

TreeNode *bstFromPreorder(vector<int> &postorder)
{
    vector<int> idx(1, postorder.size() - 1);
    return bstFromPreorder(postorder, -1e9, 1e9, idx);
}

class Codec
{
public:
    void serialize(TreeNode *root, string &sb)
    {
        if (root == nullptr)
            return;

        sb += to_string(root->val) + " ";
        serialize(root->left, sb);
        serialize(root->right, sb);
    }

    string serialize(TreeNode *root)
    {
        string sb = "";
        serialize(root, sb);
        return sb;
    }

    TreeNode *bstFromPreorder(vector<int> &preorder, int lr, int rr, vector<int> &idx)
    {
        int i = idx[0];
        if (i >= preorder.size() || preorder[i] < lr || preorder[i] > rr)
            return nullptr;

        TreeNode *root = new TreeNode(preorder[i]);
        idx[0]++;

        root->left = bstFromPreorder(preorder, lr, root->val, idx);
        root->right = bstFromPreorder(preorder, root->val, rr, idx);

        return root;
    }

    TreeNode *deserialize(string data)
    {
        if (data.length() == 0)
            return nullptr;
        stringstream ss(data);
        string word;

        vector<int> preorder;
        while (ss >> word)
        {
            int val = stoi(word);
            preorder.push_back(val);
        }

        vector<int> idx(1, 0);
        return bstFromPreorder(preorder, -(int)1e9, (int)1e9, idx);
    }
};

// for you :: https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/construct-bst-from-levelorder-traversal/ojquestion
//for you :: https://www.lintcode.com/problem/1307/

class BSTPair
{
public:
    bool isBal = true;
    int h = -1;

    bool isBST = true;
    int min = (int)1e9;
    int max = -(int)1e9;

    int size = 0;
    TreeNode *largestRoot = nullptr;
};

BSTPair isBalanced_(TreeNode *root)
{
    if (root == nullptr)
    {
        BSTPair base;
        return base;
    }

    BSTPair lp = isBalanced_(root->left);
    BSTPair rp = isBalanced_(root->right);

    BSTPair myPair;
    myPair.isBal = lp.isBal && rp.isBal;
    if (myPair.isBal && abs(lp.h - rp.h) < 2)
    {
        myPair.h = max(lp.h, rp.h) + 1;
    }
    else
    {
        myPair.isBal = false;
    }

    return myPair;
}

bool isBalanced(TreeNode *root)
{
    return isBalanced_(root).isBal;
}

BSTPair largestBST_(TreeNode *root)
{
    if (root == nullptr)
    {
        BSTPair base;
        return base;
    }

    BSTPair lp = largestBST_(root->left);
    BSTPair rp = largestBST_(root->right);

    BSTPair myPair;
    BSTPair.isBST = false;
    if (lp.isBST && rp.isBST && lp.max < root->val && root->val < rp.min)
    {
        myPair.isBST = true;
        myPair.min = min(lp.min, root->val);
        myPair.max = max(rp.max, root->val);
        myPair.size = lp.size + rp.size + 1;
        myPair.largestRoot = root;
    }
    else
    {
        if (lp.size > rp.size)
        {
            myPair.size = lp.size;
            myPair.largestRoot = lp.largestRoot;
        }
        else
        {
            myPair.size = rp.size;
            myPair.largestRoot = rp.largestRoot;
        }
    }

    return myPair;
}

TreeNode *largestBST(TreeNode *root)
{
    return largestBST_(root).largestRoot;
}
