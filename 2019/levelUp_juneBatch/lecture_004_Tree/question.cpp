#include <iostream>
#include <vector>
#include <stack>
using namespace std;

class TreeNode
{
public:
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() {}
    TreeNode(int val) { this->val = val; }
    TreeNode(int val, TreeNode *left, TreeNode *right)
    {
        this->val = val;
        this->left = left;
        this->right = right;
    }
};

int maxSum = 0;
// isBSt=1/0, maxEle,minEle,Sum
vector<int> maxSumBST_(TreeNode *root)
{
    if (root == nullptr)
    {
        return {1, (int)-1e8, (int)1e8, 0};
    }

    vector<int> la = maxSumBST_(root->left);
    vector<int> ra = maxSumBST_(root->right);

    bool isBST = la[0] == 1 && ra[0] == 1 && la[1] < root->val && root->val < ra[2];
    int sum = la[3] + ra[3] + root->val;
    if (isBST)
        maxSum = max(maxSum, sum);

    return {isBST ? 1 : 0, max(root->val, max(la[1], ra[1])), min(root->val, min(la[2], ra[2])), sum};
}

// isBSt=1/0, maxEle,minEle,Sum,maxSum
vector<int> maxSumBST_02(TreeNode *root)
{
    if (root == nullptr)
    {
        return {1, (int)-1e8, (int)1e8, 0, 0};
    }

    vector<int> la = maxSumBST_02(root->left);
    vector<int> ra = maxSumBST_02(root->right);

    bool isBST = la[0] == 1 && ra[0] == 1 && la[1] < root->val && root->val < ra[2];
    int sum = la[3] + ra[3] + root->val;

    int maxSum = 0;
    if (isBST)
        maxSum = max(sum, max(la[4], ra[4]));
    else
        maxSum = max(la[4], ra[4]);

    return {isBST ? 1 : 0, max(root->val, max(la[1], ra[1])), min(root->val, min(la[2], ra[2])), sum, maxSum};
}

int maxSumBST(TreeNode *root)
{
    // maxSumBST_(root);
    // return maxSum;

    return maxSumBST_(root)[4];
}

//Leetcode 113.============================================================

bool hasPathSum(TreeNode *root, int sum)
{
    if (root == nullptr)
        return false;
    if (root->left == nullptr && root->right == nullptr && sum - root->val == 0)
        return true;

    return hasPathSum(root->left, sum - root->val) || hasPathSum(root->right, sum - root->val);
}

//Leetcode 114.==========================================================

vector<vector<int>> ans;
vector<int> res;

void pathSum_(TreeNode *root, int sum)
{
    if (root == nullptr)
        return;
    if (root->left == nullptr && root->right == nullptr && sum - root->val == 0)
    {
        vector<int> base(res);
        base.push_back(root->val);
        ans.push_back(res);

        // ans.push_back(res);
        // ans.back().push_back(root->val);

        // res.push_back(root->val);
        // ans.push_back(res);
        // res.pop_back();

        return;
    }

    res.push_back(root->val);

    pathSum_(root->left, sum - root->val);
    pathSum_(root->right, sum - root->val);

    res.pop_back();
}

vector<vector<int>> pathSum(TreeNode *root, int sum)
{
    pathSum_(root, sum);
    return ans;
}

//Leetcode 98.==============================================================

int prevBSTVal = -1e8;
bool isValidBST(TreeNode *root)
{
    if (root == nullptr)
        return true;

    if (!isValidBST(root->left))
        return false;

    if (prevBSTVal >= root->val)
        return false;
    prevBSTVal = root->val;

    if (!isValidBST(root->right))
        return false;

    return true;
}

//Leetcode 99.================================================================

TreeNode *a = nullptr;
TreeNode *b = nullptr;
TreeNode *prevNode = nullptr;

// meaning of true: mujhai 2 fluctuation mil gyi hai.
// meaning of false: !(mujhai 2 fluctuation mil gyi hai).
bool recoverTree_(TreeNode *root)
{

    if (root == nullptr)
        return false;

    if (recoverTree_(root->left))
        return true;

    if (prevNode != nullptr && prevNode->val > root->val)
    {
        b = root;
        if (a == nullptr)
            a = prevNode;
        else
            return true;
    }

    prevNode = root;
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

//Geeks : https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/

int maxSumLTL = -1e8; // max sum leaf to leaf.
int leafToLeafPathSum(TreeNode *node)
{
    if (node == nullptr)
        return -1e8;

    int lPathSum = leafToLeafPathSum(node->left);
    int rPathSum = leafToLeafPathSum(node->right);

    if (node->left == nullptr && node->right == nullptr)
        return node->val;

    if (node->left != nullptr && node->right != nullptr)
        maxSumLTL = max(maxSumLTL, lPathSum + rPathSum + node->val;
    return max(lPathSum, rPathSum) + node->val;
}

//Leetcode 124.===============================================================

int maxSumNTN = -1e8; // max sum leaf to leaf.
int nodeToNodePathSum_(TreeNode *node)
{
    if (node == nullptr)
        return 0;

    int lPathSum = nodeToNodePathSum_(node->left);
    int rPathSum = nodeToNodePathSum_(node->right);

    int max_ = max(lPathSum, rPathSum);
    maxSumNTN = max(max(maxSumNTN, node->val), max(lPathSum + rPathSum + node->val, max_ + node->val));
    return max(max_ + node->val, node->val);
}

int maxPathSum(TreeNode *root)
{
    nodeToNodePathSum_(root);
    return maxSumNTN;
}

//Leetcode 236.=======================================================================

TreeNode *lca = nullptr;
bool lowestCommonAncestor_(TreeNode *root, TreeNode *p, TreeNode *q)
{
    if (root == nullptr)
        return false;

    bool selfFound = root == p || root == q;

    bool leftFound = lowestCommonAncestor_(root->left, p, q);
    if (lca != nullptr)
        return true;

    bool rightFound = lowestCommonAncestor_(root->right, p, q);
    if (lca != nullptr)
        return true;

    if ((selfFound && leftFound) || (selfFound && rightFound) || (rightFound && leftFound))
        lca = root;

    return selfFound || leftFound || rightFound;
}

TreeNode *lowestCommonAncestor(TreeNode *root, TreeNode *p, TreeNode *q)
{
    lowestCommonAncestor_(root, p, q);
    return lca;
}

//Letcode 173.
class BSTIterator
{
public:
    stack<TreeNode *> st;

    BSTIterator(TreeNode *root)
    {
        insertLeftMost(root);
    }

    void insertLeftMost(TreeNode *root)
    {
        TreeNode *curr = root;
        while (curr != nullptr)
        {
            st.push(curr);
            curr = curr->left;
        }
    }

    int next()
    {
        TreeNode *rnode = st.top();
        st.pop();
        insertLeftMost(rnode->right);

        return rnode->val;
    }

    bool hasNext()
    {
        return st.size() != 0;
    }

    // while(obj.hasNext()) cout<<obj.next()<<endl;
};

// https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1

TreeNode *prevDLL = nullptr, *head = nullptr;
void DLL(TreeNode *node)
{
    if (node == nullptr)
        return;

    DLL(node->left);
    if (head == nullptr)
        head = node;
    else
    {
        prevDLL->right = node;
        node->left = prevDLL;
    }

    prevDLL = node;
    DLL(node->right);
}

TreeNode *bToDLL(TreeNode *root)
{
    prevDLL = nullptr;
    head = nullptr;
    DLL(root);
    return head;
}

//Leetcode : 426

TreeNode *treeToDoublyList(TreeNode *root)
{
    if (root == nullptr)
        return root;

    prevDLL = nullptr;
    head = nullptr;
    DLL(root);

    prevDLL->right = head;
    head->left = prevDLL;

    return head;
}

//Leetcode : 105
TreeNode *buildTree(vector<int> &preorder, int psi, int pei, vector<int> &inorder, int isi, int iei) // si=starting index, ei = end index.
{
    if (psi > pei)
        return nullptr;

    TreeNode *node = new TreeNode(preorder[psi]);

    int idx = isi;
    while (inorder[idx] != preorder[psi])
        idx++;

    int tel = idx - isi;

    node->left = buildTree(preorder, psi + 1, psi + tel, inorder, isi, idx - 1);
    node->right = buildTree(preorder, psi + tel + 1, pei, inorder, idx + 1, iei);

    return node;
}

TreeNode *buildTree(vector<int> &preorder, vector<int> &inorder)
{
    int n = preorder.size();
    return buildTree(preorder, 0, n - 1, inorder, 0, n - 1);
}

//Leetcode : 106
TreeNode *buildTree(vector<int> &postorder, int psi, int pei, vector<int> &inorder, int isi, int iei) // si=starting index, ei = end index.
{
    if (psi > pei)
        return nullptr;

    TreeNode *node = new TreeNode(postorder[pei]);

    int idx = isi;
    while (inorder[idx] != postorder[pei])
        idx++;

    int tel = idx - isi;

    node->left = buildTree(postorder, psi, psi + tel - 1, inorder, isi, idx - 1);
    node->right = buildTree(postorder, psi + tel, pei - 1, inorder, idx + 1, iei);

    return node;
}

TreeNode *buildTree(vector<int> &inorder, vector<int> &postorder)
{
    int n = inorder.size();
    return buildTree(postorder, 0, n - 1, inorder, 0, n - 1);
}

//Leetcode 889.

TreeNode *buildTree(vector<int> &preorder, int psi, int pei, vector<int> &postorder, int ppsi, int ppei) // si=starting index, ei = end index.
{
    if (psi > pei)
        return nullptr;

    if (psi == pei)
        return new TreeNode(preorder[psi]);

    TreeNode *node = new TreeNode(preorder[psi]);
    int idx = ppsi;
    while (postorder[idx] != preorder[psi + 1])
        idx++;

    int tel = idx - ppsi + 1;

    node->left = buildTree(preorder, psi + 1, psi + tel, postorder, ppsi, idx);
    node->right = buildTree(preorder, psi + tel + 1, pei, postorder, idx + 1, ppei - 1);

    return node;
}

TreeNode *constructFromPrePost(vector<int> &pre, vector<int> &post)
{
    int n = pre.size();
    return buildTree(pre, 0, n - 1, post, 0, n - 1);
}

// -1 : need a camera.
//  0 : is a camera
//  1 : dosen't need a camera.

int camera = 0;
int minCameraCover_(TreeNode *root)
{
    if (root == nullptr)
        return 1;
    if (root->left == nullptr && root->right == nullptr)
        return -1;

    int lans = minCameraCover_(root->left);
    int rans = minCameraCover_(root->right);

    if (lans == -1 || rans == -1)
    {
        camera++;
        return 0;
    }
    else if (lans == 0 || rans == 0)
        return 1;

    return -1;
}

int minCameraCover(TreeNode *root)
{
    if (root = nullptr)
        return 0;

    if (minCameraCover_(root) == -1)
        camera++;

    return camera;
}

//Leetcode 230

void leftMost(TreeNode *node, stack<TreeNode *> &st)
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
    leftMost(root, st);

    int ans = -1;
    while (k-- > 0)
    {
        TreeNode *rnode = st.top();
        st.pop();
        leftMost(rnode->right, st);
        
        ans = rnode->val;
    }
    return ans;
}

//Leetcode 662