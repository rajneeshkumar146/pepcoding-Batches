#include <iostream>
#include <vector>

using namespace std;

class Node
{
public:
    int val = 0;
    vector<Node *> childs;

    Node(int val)
    {
        this->val = val;
    }
};

bool rootToNodePath(Node *root, int data, vector<Node *> &ans)
{
    if (root->val == data)
    {
        ans.push_back(root);
        return true;
    }

    bool res = false;
    for (Node *child : root->childs)
    {
        res = res || rootToNodePath(child, data, ans);
        // if(res) break;
    }

    if (res)
        ans.push_back(root);

    return res;
}

vector<Node *> rootToNodePath(Node *root, int data)
{
    vector<Node *> ans;
    rootToNodePath(root, data, ans);

    return ans;
}

void kDown(Node *root, Node *blockNode, int time, vector<vector<int>> &ans)
{
    if (root == blockNode)
        return;

    if (ans.size() == time)
        ans.push_back({});

    ans[time].push_back(root->val);
    for (Node *child : root->childs)
        kDown(child, blockNode, time + 1, ans);
}

// Method_01
vector<vector<int>> burningTree_01(Node *root, int target, int K)
{
    vector<Node *> list;
    rootToNodePath(root, target, list);

    vector<vector<int>> ans;
    Node *blockNode = nullptr;

    for (int i = 0; i < list.size(); i++)
    {
        kDown(list[i], blockNode, i, ans);
        blockNode = list[i];
    }

    return ans;
}
