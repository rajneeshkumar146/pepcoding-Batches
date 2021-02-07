#include <iostream>
#include <vector>
#include <unordered_map>
#include <queue>

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

class verticalPair
{
public:
    TreeNode *node = nullptr;
    int hl = 0; // horizontal Level

    verticalPair(TreeNode *node, int hl)
    {
        this->node = node;
        this->hl = hl;
    }
};

vector<vector<int>> verticalOrderTraversal(TreeNode *root)
{
    queue<verticalPair> que;
    que.push(verticalPair(root, 0));

    int minHL = 0, maxHL = 0;
    unordered_map<int, vector<int>> map;

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            verticalPair rp = que.front();
            que.pop();

            map[rp.hl].push_back(rp.node->val);
            minHL = min(minHL, rp.hl);
            maxHL = max(maxHL, rp.hl);

            if (rp.node->left != nullptr)
                que.push(verticalPair(rp.node->left, rp.hl - 1));
            if (rp.node->right != nullptr)
                que.push(verticalPair(rp.node->right, rp.hl + 1));
        }
    }

    vector<vector<int>> res;
    while (minHL <= maxHL)
        res.push_back(map[minHL++]);

    return res;
}

void width(TreeNode *root, int hl, vector<int> &ans)
{
    if (root == nullptr)
        return;

    ans[0] = min(hl, ans[0]);
    ans[1] = max(hl, ans[1]);

    width(root->left, hl - 1, ans);
    width(root->right, hl + 1, ans);
}

vector<vector<int>> verticalOrderTraversal_02(TreeNode *root)
{
    queue<verticalPair> que;
    vector<int> minMax(2, 0);
    width(root, 0, minMax);
    int len = minMax[1] - minMax[0] + 1;
    vector<vector<int>> res(len);

    que.push(verticalPair(root, -minMax[0]));

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            verticalPair rp = que.front();
            que.pop();

            res[rp.hl].push_back(rp.node->val);

            if (rp.node->left != nullptr)
                que.push(verticalPair(rp.node->left, rp.hl - 1));
            if (rp.node->right != nullptr)
                que.push(verticalPair(rp.node->right, rp.hl + 1));
        }
    }

    return res;
}

vector<int> verticalSum(TreeNode *root)
{
    queue<verticalPair> que;
    vector<int> minMax(2, 0);
    width(root, 0, minMax);
    int len = minMax[1] - minMax[0] + 1;
    vector<int> res(len, 0);

    que.push(verticalPair(root, -minMax[0]));

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            verticalPair rp = que.front();
            que.pop();

            res[rp.hl] += rp.node->val;

            if (rp.node->left != nullptr)
                que.push(verticalPair(rp.node->left, rp.hl - 1));
            if (rp.node->right != nullptr)
                que.push(verticalPair(rp.node->right, rp.hl + 1));
        }
    }

    return res;
}

vector<int> bottomView(TreeNode *root)
{
    queue<verticalPair> que;
    vector<int> minMax(2, 0);
    width(root, 0, minMax);
    int len = minMax[1] - minMax[0] + 1;
    vector<int> res(len, 0);

    que.push(verticalPair(root, -minMax[0]));

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            verticalPair rp = que.front();
            que.pop();

            res[rp.hl] = rp.node->val;

            if (rp.node->left != nullptr)
                que.push(verticalPair(rp.node->left, rp.hl - 1));
            if (rp.node->right != nullptr)
                que.push(verticalPair(rp.node->right, rp.hl + 1));
        }
    }

    return res;
}

vector<int> bottomView(TreeNode *root)
{
    queue<verticalPair> que;
    vector<int> minMax(2, 0);
    width(root, 0, minMax);
    int len = minMax[1] - minMax[0] + 1;

    vector<int> res(len, 0);
    vector<bool> resVis(len, false);

    que.push(verticalPair(root, -minMax[0]));

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            verticalPair rp = que.front();
            que.pop();

            if (!resVis[rp.hl])
            {
                res[rp.hl] = rp.node->val;
                resVis[rp.hl] = true;
            }

            if (rp.node->left != nullptr)
                que.push(verticalPair(rp.node->left, rp.hl - 1));
            if (rp.node->right != nullptr)
                que.push(verticalPair(rp.node->right, rp.hl + 1));
        }
    }

    return res;
}

class verticalPair2
{
public:
    TreeNode *node = nullptr;
    int x = 0; // horizontal Level
    int y = 0; // vertical Level

    verticalPair2(TreeNode *node, int x, int y)
    {
        this->node = node;
        this->x = x;
        this->y = y;
    }
};

struct comp
{
public:
    bool operator()(const verticalPair2 a, const verticalPair2 b) const
    {
        if (a.y != b.y)
            return a.y > b.y;   // this - other, '-' replace with '>';
        else
            return a.node->val > b.node->val;
    }
};

vector<vector<int>> verticalOrderTraversal_02(TreeNode *root)
{
    priority_queue<verticalPair2, vector<verticalPair2>, comp> que;
    vector<int> minMax(2, 0);
    width(root, 0, minMax);
    int len = minMax[1] - minMax[0] + 1;
    vector<vector<int>> res(len);

    que.push(verticalPair2(root, -minMax[0], 0));

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            verticalPair2 rp = que.top();
            que.pop();

            res[rp.x].push_back(rp.node->val);

            if (rp.node->left != nullptr)
                que.push(verticalPair2(rp.node->left, rp.x - 1, rp.y + 1));
            if (rp.node->right != nullptr)
                que.push(verticalPair2(rp.node->right, rp.x + 1, rp.y + 1));
        }
    }

    return res;
}
