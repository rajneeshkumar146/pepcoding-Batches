#include <iostream>
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

int size(TreeNode* root) {
        return root == nullptr ? 0 : size(root->left) + size(root->right) + 1;
    }

    int height(TreeNode* root) {
        return root == nullptr ? -1 : max(height(root->left), height(root->right)) + 1;
    }

    int maximum(TreeNode* root) {
        return root == nullptr ? -(int) 1e9 : max(root->val, max(maximum(root->left), maximum(root->right)));
    }

    int minimum(TreeNode* root) {
        return root == nullptr ? (int) 1e9 : min(root->val,min(minimum(root->left), minimum(root->right)));
    }

    bool find(TreeNode* root, int data) {
        if (root == nullptr)
            return false;

        if (root->val == data)
            return true;

        return find(root->left, data) || find(root->right, data);
    }

    vector<TreeNode*> nodeToRootPath(TreeNode root, int data) {

    }

int main()
{


    return 0;
}