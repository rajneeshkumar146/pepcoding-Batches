#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

vector<TreeNode *> nodeToRootPath(TreeNode *node, int data)
{
    if (node == nullptr)
        return {};

    if (node->val == data)
    {
        vector<TreeNode *> base;
        base.push_back(node);
        return base;
    }

    vector<TreeNode *> left = nodeToRootPath(node->left, data);
    if (left.size() != 0)
    {
        left.push_back(node);
        return left;
    }

    vector<TreeNode *> right = nodeToRootPath(node->right, data);
    if (right.size() != 0)
    {
        right.push_back(node);
        return right;
    }

    return {};
}

TreeNode *lowestCommonAncestor(TreeNode *node, TreeNode *p, TreeNode *q)
{
    vector<TreeNode *> list1 = nodeToRootPath(node, p->val);
    vector<TreeNode *> list2 = nodeToRootPath(node, q->val);

    int i = list1.size() - 1;
    int j = list2.size() - 1;

    TreeNode *LCA = nullptr;
    while (i >= 0 && j >= 0)
    {
        if (list1[i] == list2[j])
            LCA = list1[i];

        i--;
        j--;
    }

    return LCA;
}

vector<int> rightSideView(TreeNode* node) {
    if(node==nullptr) return {};

    queue<TreeNode*> que;
    que.push(node);

    vector<int> ans;

    while(que.size()!=0){
        int size = que.size();
        ans.push_back(que.front()->val);
        
        while(size-->0){
           TreeNode* vtx = que.front(); que.pop();

           if(vtx->right != nullptr) que.push(vtx->right); 
           if(vtx->left != nullptr) que.push(vtx->left);
        }
    }

    return ans;
}


void rightSideView_(TreeNode* node,int level,vector<int>& ans){
    if(node == nullptr) return;

    if(level == ans.size()) ans.push_back(node->val);

    rightSideView_(node->right,level+1,ans);
    rightSideView_(node->left,level+1,ans);
}

vector<int> rightSideView(TreeNode* node) {
    vector<int> ans;
    rightSideView_(node,0,ans);
    return ans;
}




