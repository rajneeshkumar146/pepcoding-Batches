#include <iostream>
using namespace std;

class TreeNode
{
public:
    int val = 0;
    TreeNode *left = nullptr;
    TreeNode *right = nullptr;

    int bal = 0;
    int height = 0;

    TreeNode(int val)
    {
        this->val = val;
    }
};

//O(1)
void updateBalAndHeight(TreeNode *root)
{
    int lh = root->left != NULL ? root->left->height : -1;
    int rh = root->right != NULL ? root->right->height : -1;
    int bal = lh - rh;

    root->height = max(lh, rh) + 1;
    root->bal = bal;
}
//O(1)
TreeNode *rightRotation(TreeNode *A)
{
    TreeNode *B = A->left;
    TreeNode *BkaRight = B->right;

    B->right = A;
    A->left = BkaRight;

    updateBalAndHeight(A);
    updateBalAndHeight(B);

    return B;
}

//O(1)
TreeNode *leftRotation(TreeNode *A)
{
    TreeNode *B = A->right;
    TreeNode *BkaLeft = B->left;

    B->left = A;
    A->right = BkaLeft;

    updateBalAndHeight(A);
    updateBalAndHeight(B);

    return B;
}

//O(1)
TreeNode *getRotation(TreeNode *root)
{
    updateBalAndHeight(root);
    if (root->bal == 2) //ll,lr
    {
        if (root->left->bal == 1) // ll
        {
            return rightRotation(root);
        }
        else // lr
        {
            root->left = leftRotation(root->left);
            return rightRotation(root);
        }
    }
    else if (root->bal == -2) // rr,rl
    {

        if (root->right->bal == -1) // rr
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

TreeNode *addData(TreeNode *root, int data)
{
    if (root == nullptr)
        return new TreeNode(data);

    if (root->val < data)
        root->right = addData(root->right, data);
    else
        root->left = addData(root->left, data);

    
    return getRotation(root);
    // return root;
}

int maximum(TreeNode *root)
{
    TreeNode *curr = root;
    while (curr->right != nullptr)
        curr = curr->right;

    return curr->val;
}

TreeNode *removeData(TreeNode *root, int data)
{
    if (root == nullptr)
        return nullptr;

    if (root->val < data)
        root->right = removeData(root->right, data);
    else if (root->val > data)
        root->left = removeData(root->left, data);
    else
    {
        if (root->left == NULL || root->right == NULL)
            return root->left != nullptr ? root->left : root->right;

        root->val = maximum(root->left);
        root->left = removeData(root->left, root->val);
    }

    // return getRotation(root);
    // return root;
}

void display(TreeNode *root)
{
    if (root == nullptr)
        return;

    string ans = "";
    ans += root->left != nullptr ? to_string(root->left->val) : ".";
    ans += " -> " + to_string(root->val) + " <- ";
    ans += root->right != nullptr ? to_string(root->right->val) : ".";

    cout << ans << endl;

    display(root->left);
    display(root->right);
}

int main()
{
    TreeNode *root = NULL;
    for (int i = 1; i <= 15; i++)
    {
        root = addData(root, i * 10);
    }

    display(root);

    return 0;
}