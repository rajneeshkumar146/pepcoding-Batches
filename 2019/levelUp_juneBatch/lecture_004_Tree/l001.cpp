#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

class Node
{
public:
    int data;
    Node *left = nullptr;
    Node *right = nullptr;
    Node(int data)
    {
        this->data = data;
    }
};

int idx = 0;
Node *constructTree(vector<int> &arr)
{

    if (idx == arr.size() || arr[idx] == -1)
    {
        idx++;
        return nullptr;
    }
    Node *node = new Node(arr[idx++]);
    node->left = constructTree(arr);
    node->right = constructTree(arr);
    return node;
}

void inOrder(Node *node)
{
    if (node == nullptr)
        return;

    inOrder(node->left);
    cout << node->data << " ";
    inOrder(node->right);
}

int size(Node *node)
{
    return node == nullptr ? 0 : size(node->left) + size(node->right) + 1;
}

int height(Node *node)
{
    return node == nullptr ? -1 : max(height(node->left), height(node->right)) + 1; // for no of edges: -1, and in terms of no of nodes return 0;
}

int maximum(Node *node)
{
    return node == nullptr ? -1e8 : max(max(maximum(node->left), maximum(node->right)), node->data);
}

int minimum(Node *node)
{
    return node == nullptr ? 1e8 : min(min(minimum(node->left), minimum(node->right)), node->data);
}

bool find(Node *node, int data)
{
    if (node == nullptr)
        return false;
    if (node->data == data)
        return true;
    return find(node->left, data) || find(node->right, data);
}

bool rootToNodePath(Node *node, int data, vector<Node *> &path)
{
    if (node == nullptr)
        return false;
    if (node->data == data)
    {
        path.push_back(node);
        return true;
    }
    bool res = rootToNodePath(node->left, data, path) || rootToNodePath(node->right, data, path);
    if (res)
        path.push_back(node);
    return res;
}

vector<Node *> rootToNodePath02(Node *node, int data)
{
    if (node == nullptr)
        return {};
    if (node->data == data)
    {
        vector<Node *> base;
        base.push_back(node);
        return base;
    }

    vector<Node *> la = rootToNodePath02(node->left, data);
    if (la.size() != 0)
    {
        la.push_back(node);
        return la;
    }
    vector<Node *> ra = rootToNodePath02(node->right, data);
    if (ra.size() != 0)
    {
        ra.push_back(node);
        return ra;
    }

    return {};
}

Node *lowestCommonAncestor(Node *root, Node *p, Node *q)
{
    vector<Node *> arr1, arr2;
    bool res = rootToNodePath(root, p->data, arr1) && rootToNodePath(root, q->data, arr2);
    if (!res)
        return nullptr;

    int c1 = arr1.size() - 1;
    int c2 = arr2.size() - 1;
    Node *LCA = nullptr;
    while (c1 >= 0 && c2 >= 0)
    {
        if (arr1[c1]->data != arr2[c2]->data)
            break;

        LCA = arr1[c1];
        c1--;
        c2--;
    }
    return LCA;
}

void kDown(Node *node, int k, Node *block)
{
    if (node == nullptr || node == block)
        return;

    if (k == 0)
    {
        cout << node->data << " ";
        return;
    }

    kDown(node->left, k - 1, block);
    kDown(node->right, k - 1, block);
}

void kAway(Node *node, int k, int data)
{
    vector<Node *> arr;
    rootToNodePath(node, data, arr);

    Node *block = nullptr;
    for (int i = 0; i < arr.size(); i++)
    {
        kDown(arr[i], k - i, block);
        block = arr[i];
    }
}

int kAway02(Node *node, int k, int data)
{
    if (node == nullptr)
        return -1;

    if (node->data == data)
    {
        kDown(node, k, nullptr);
        return 1;
    }

    int ld = kAway02(node->left, k, data);
    if (ld > 0)
    {
        kDown(node, k - ld, node->left);
        return ld + 1;
    }

    int rd = kAway02(node->right, k, data);
    if (rd > 0)
    {

        kDown(node, k - rd, node->right);
        return rd + 1;
    }

    return -1;
}

int distanceBWNodes(Node *root, int p, int q)
{

    vector<Node *> arr1, arr2;
    bool res = rootToNodePath(root, p, arr1) && rootToNodePath(root, q, arr2);
    if (!res)
        return 0;

    int c1 = arr1.size() - 1;
    int c2 = arr2.size() - 1;
    int LCA = 0;
    while (c1 >= 0 && c2 >= 0)
    {
        if (arr1[c1]->data != arr2[c2]->data)
            break;

        LCA++;
        c1--;
        c2--;
    }

    return (arr1.size() + arr2.size() - 2 * LCA);
}

int diameter(Node *root)
{
    if (root == nullptr)
        return 0;

    int ld = diameter(root->left);
    int rd = diameter(root->right);

    int lh = height(root->left);
    int rh = height(root->right);

    return max(max(ld, rd), lh + rh + 2);
}

pair<int, int> diameter_Btr(Node *root)
{
    if (root == nullptr)
        return {0, -1};

    pair<int, int> la = diameter_Btr(root->left);
    pair<int, int> ra = diameter_Btr(root->right);

    int dia = max(max(la.first, ra.first), la.second + ra.second + 2);
    int height = max(la.second, ra.second) + 1;
    return {dia, height};
}

int dia_ = 0;
int diameter_Btr02(Node *root)
{
    if (root == nullptr)
        return -1;

    int la = diameter_Btr02(root->left);
    int ra = diameter_Btr02(root->right);

    int dia = max(dia_, la + ra + 2);
    return max(la, ra) + 1;
}

class allSolPair
{
public:
    bool isBST = false;
    int maxEle = -1e8;
    int minEle = 1e8;

    int countOfBST = 0;

    int maxSizeOfBST = 0;
    Node *maxBSTNode = nullptr;

    bool isBal = false;
    int height = -1;
};

allSolPair allSolution(Node *node)
{
    if (node == nullptr)
    {
        allSolPair base;
        base.isBST = true;
        base.isBal = true;
        return base;
    }
    allSolPair lp = allSolution(node->left);
    allSolPair rp = allSolution(node->right);

    allSolPair mypair;
    if (lp.isBST && rp.isBST && lp.maxEle < node->data && node->data < rp.minEle)
    {
        mypair.isBST = true;
        mypair.countOfBST += 1;

        mypair.maxSizeOfBST = lp.maxSizeOfBST + rp.maxSizeOfBST + 1;
        mypair.maxBSTNode = node;
    }

    mypair.countOfBST += (lp.countOfBST + rp.countOfBST);

    mypair.maxEle = max(max(lp.maxEle, rp.maxEle), node->data);
    mypair.minEle = min(min(lp.minEle, rp.minEle), node->data);

    if (!mypair.isBST)
    {
        mypair.maxSizeOfBST = lp.maxSizeOfBST < rp.maxSizeOfBST ? rp.maxSizeOfBST : lp.maxSizeOfBST;
        mypair.maxBSTNode = lp.maxSizeOfBST < rp.maxSizeOfBST ? rp.maxBSTNode : lp.maxBSTNode;
    }

    if (lp.isBal && rp.isBal && abs(lp.height - rp.height) < 2)
        mypair.isBal = true;

    mypair.height = max(lp.height, rp.height) + 1;

    return mypair;
}

class pairSum
{
public:
    int maxSumLTL = 0; // max sum leaf to leaf.
    int nodeToLeafMaxSum = 0;
    string maxPathLTL = "";
    string maxPathNTL = ""; // max sum node to leaf.

    pairSum(int a, int b, string c, string d)
    {
        maxSumLTL = a;
        nodeToLeafMaxSum = b;
        maxPathLTL = c;
        maxPathNTL = d;
    }
};

pairSum leafToLeafPathSum02(Node *node)
{
    if (node == nullptr)
    {
        pairSum base(-1e8, -1e8, "", "");
        return base;
    }

    pairSum lp = leafToLeafPathSum02(node->left);
    pairSum rp = leafToLeafPathSum02(node->right);

    pairSum myPair(-1e8, -1e8, "", "");

    if (node->left == nullptr && node->right == nullptr)
    {
        pairSum base(-1e8, node->data, "", to_string(node->data));
        return base;
    }

    if (node->left != nullptr && node->right != nullptr)
    {
        myPair.maxSumLTL = max(max(lp.maxSumLTL, rp.maxSumLTL), lp.nodeToLeafMaxSum + rp.nodeToLeafMaxSum + node->data);
        if (myPair.maxSumLTL == lp.maxSumLTL)
            myPair.maxPathLTL = lp.maxPathLTL;
        else if (myPair.maxSumLTL == rp.maxSumLTL)
            myPair.maxPathLTL = rp.maxPathLTL;
        else
        {
            myPair.maxPathLTL = lp.maxPathNTL + " " + to_string(node->data) + " " + rp.maxPathNTL;
        }
    }
    else
    {
        if (lp.maxSumLTL > rp.maxSumLTL)
        {
            myPair.maxSumLTL = lp.maxSumLTL;
            myPair.maxPathLTL = lp.maxPathLTL;
        }
        else
        {
            myPair.maxSumLTL = rp.maxSumLTL;
            myPair.maxPathLTL = rp.maxPathLTL;
        }
    }

    if (lp.nodeToLeafMaxSum > rp.nodeToLeafMaxSum)
    {
        myPair.nodeToLeafMaxSum = lp.nodeToLeafMaxSum + node->data;
        myPair.maxPathNTL = lp.maxPathNTL + " " + to_string(node->data);
    }
    else
    {
        myPair.nodeToLeafMaxSum = rp.nodeToLeafMaxSum + node->data;
        myPair.maxPathNTL = rp.maxPathNTL + " " + to_string(node->data);
    }

    return myPair;
}

//LevelOrder/BFS.=======================================================================

void levelOrder_01(Node *node)
{
    queue<Node *> que;
    que.push(node);
    while (que.size() != 0)
    {
        Node *rvtx = que.front();
        que.pop();
        cout << rvtx->data << " ";

        if (rvtx->left != nullptr)
            que.push(rvtx->left);
        if (rvtx->right != nullptr)
            que.push(rvtx->right);
    }
}

void levelOrder_02(Node *node)
{
    queue<Node *> que;
    que.push(node);
    que.push(nullptr);
    while (que.size() != 1)
    {
        Node *rvtx = que.front();
        que.pop();
        cout << rvtx->data << " ";

        if (rvtx->left != nullptr)
            que.push(rvtx->left);
        if (rvtx->right != nullptr)
            que.push(rvtx->right);

        if (que.front() == nullptr)
        {
            cout << endl;
            que.pop();
            que.push(nullptr);
        }
    }
}

void levelOrder_03(Node *node)
{
    queue<Node *> que;
    que.push(node);
    int level = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        cout << "Level: " << level << " -> ";
        while (size-- > 0)
        {
            Node *rvtx = que.front();
            que.pop();
            cout << rvtx->data << " ";

            if (rvtx->left != nullptr)
                que.push(rvtx->left);
            if (rvtx->right != nullptr)
                que.push(rvtx->right);
        }
        level++;
        cout << endl;
    }
}

//Views.======================================================================

void leftView(Node *node)
{
    queue<Node *> que;
    que.push(node);
    while (que.size() != 0)
    {
        int size = que.size();
        cout << que.front()->data << " ";
        while (size-- > 0)
        {
            Node *rvtx = que.front();
            que.pop();

            if (rvtx->left != nullptr)
                que.push(rvtx->left);
            if (rvtx->right != nullptr)
                que.push(rvtx->right);
        }
        cout << endl;
    }
    cout << endl;
}

void rightView(Node *node)
{
    queue<Node *> que;
    que.push(node);
    while (que.size() != 0)
    {
        int size = que.size();
        Node *prev = nullptr;
        while (size-- > 0)
        {
            Node *rvtx = que.front();
            que.pop();

            if (rvtx->left != nullptr)
                que.push(rvtx->left);
            if (rvtx->right != nullptr)
                que.push(rvtx->right);

            prev = rvtx;
        }
        cout << prev->data << " ";
        cout << endl;
    }
    cout << endl;
}

void width(Node *node, int level, pair<int, int> &maxMin)
{
    if (node == nullptr)
        return;

    maxMin.first = min(maxMin.first, level);
    maxMin.second = max(maxMin.second, level);

    width(node->left, level - 1, maxMin);
    width(node->right, level + 1, maxMin);
}

void verticalOrderTraversal(Node *node)
{
    pair<int, int> maxMin = {0, 0};
    width(node, 0, maxMin);
    int w = maxMin.second - maxMin.first + 1;
    vector<vector<int>> ans(w, vector<int>());

    queue<pair<Node *, int>> que;
    que.push({node, -maxMin.first});

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            pair<Node *, int> rpair = que.front();
            que.pop();

            ans[rpair.second].push_back(rpair.first->data);

            if (rpair.first->left != nullptr)
                que.push({rpair.first->left, rpair.second - 1});

            if (rpair.first->right != nullptr)
                que.push({rpair.first->right, rpair.second + 1});
        }
    }

    for (vector<int> &ar : ans)
    {
        for (int ele : ar)
            cout << ele << " ";
        cout << endl;
    }
    cout << endl;
}

void verticalOrderSum(Node *node)
{
    pair<int, int> maxMin = {0, 0};
    width(node, 0, maxMin);
    int w = maxMin.second - maxMin.first + 1;
    vector<int> ans(w, -1);

    queue<pair<Node *, int>> que;
    que.push({node, -maxMin.first});

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            pair<Node *, int> rpair = que.front();
            que.pop();

            ans[rpair.second] += rpair.first->data;

            if (rpair.first->left != nullptr)
                que.push({rpair.first->left, rpair.second - 1});

            if (rpair.first->right != nullptr)
                que.push({rpair.first->right, rpair.second + 1});
        }
    }

    for (int ele : ans)
        cout << ele << " ";
    cout << endl;
}

void verticalView(Node *node)
{
    pair<int, int> maxMin = {0, 0};
    width(node, 0, maxMin);
    int w = maxMin.second - maxMin.first + 1;
    vector<int> ans(w, -1);

    queue<pair<Node *, int>> que;
    que.push({node, -maxMin.first});

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            pair<Node *, int> rpair = que.front();
            que.pop();

            if (ans[rpair.second] == -1)
                ans[rpair.second] = rpair.first->data;

            if (rpair.first->left != nullptr)
                que.push({rpair.first->left, rpair.second - 1});

            if (rpair.first->right != nullptr)
                que.push({rpair.first->right, rpair.second + 1});
        }
    }

    for (int ele : ans)
        cout << ele << " ";
    cout << endl;
}

void BottomView(Node *node)
{
    pair<int, int> maxMin = {0, 0};
    width(node, 0, maxMin);
    int w = maxMin.second - maxMin.first + 1;
    vector<int> ans(w, -1);

    queue<pair<Node *, int>> que;
    que.push({node, -maxMin.first});

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            pair<Node *, int> rpair = que.front();
            que.pop();

            ans[rpair.second] = rpair.first->data;

            if (rpair.first->left != nullptr)
                que.push({rpair.first->left, rpair.second - 1});

            if (rpair.first->right != nullptr)
                que.push({rpair.first->right, rpair.second + 1});
        }
    }

    for (int ele : ans)
        cout << ele << " ";
    cout << endl;
}

class allSol
{
public:
    int height = 0;
    int size = 0;
    bool find = false;

    int ceil = 1e8;
    int floor = -1e8;

    Node *pred = nullptr;
    Node *succ = nullptr;
    Node *prev = nullptr;
};

void allSolution(Node *node, int data, int level, allSol &p)
{
    if (node == nullptr)
        return;

    p.height = max(p.height, level);
    p.size++;
    p.find = p.find;

    allSolution(node->left, data, level + 1, p);
    allSolution(node->right, data, level + 1, p);
}

//Traversal. ===============================================================

Node *rightMost(Node *next, Node *curr)
{
    while (next->right != nullptr && next->right != curr)
        next = next->right;

    return next;
}

void morrisInOrderTraversal(Node *node)
{
    Node *curr = node;
    while (curr != nullptr)
    {
        Node *next = curr->left;
        if (next == nullptr)
        {
            cout << curr->data << " ";
            curr = curr->right;
        }
        else
        {
            Node *rMost = rightMost(next, curr);
            if (rMost->right == nullptr) // thread creation
            {
                rMost->right = curr;
                curr = curr->left;
            }
            else
            {
                cout << curr->data << " ";
                rMost->right = nullptr; // thread break
                curr = curr->right;
            }
        }
    }
}

void morrisPreOrderTraversal(Node *node)
{
    Node *curr = node;
    while (curr != nullptr)
    {
        Node *next = curr->left;
        if (next == nullptr)
        {
            cout << curr->data << " ";
            curr = curr->right;
        }
        else
        {
            Node *rMost = rightMost(next, curr);
            if (rMost->right == nullptr) // thread creation
            {
                cout << curr->data << " ";
                rMost->right = curr;
                curr = curr->left;
            }
            else
            {
                rMost->right = nullptr;
                curr = curr->right;
            }
        }
    }
}

void traversal(Node *root)
{
    // morrisInOrderTraversal(root);
    morrisPreOrderTraversal(root);
}

void display(Node *node)
{
    if (node == nullptr)
        return;
    string str = "";
    str += node->left != nullptr ? to_string(node->left->data) : ".";
    str += " <- " + to_string(node->data) + " -> ";
    str += node->right != nullptr ? to_string(node->right->data) : ".";
    cout << str << endl;

    display(node->left);
    display(node->right);
}

void set1(Node *root)
{
    // levelOrder_01(root);
    // levelOrder_02(root);
    // levelOrder_03(root);

    leftView(root);
    rightView(root);
    verticalOrderTraversal(root);
    verticalView(root);
    BottomView(root);

    allSol p;
    allSolution(root, 90, 0, p);

    cout << p.height << endl;
}

void solve()
{
    vector<int> arr = {10, 20, 30, 40, -1, -1, 50, -1, -1, 60, 70, -1, 80, -1, -1, -1, 90, 100, -1, 120, -1, -1, 110, 130, -1, -1, -1};
    // vector<int> arr = {-15, 5, -8, 2, -1, -1, 6, -1, -1, 1, -1, -1, 6, 3, -1, -1, 9, -1, 0, 4, -1, -1, -2, 10, -1, -1, -1};

    Node *root = constructTree(arr);
    // inOrder(root);
    display(root);

    // pairSum p = leafToLeafPathSum02(root);
    // cout << p.maxPathLTL << " @ " << p.maxSumLTL << endl;

    // set1(root);
    traversal(root);
}

int main()
{
    solve();
    return 0;
}