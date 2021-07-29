#include <iostream>
#include <vector>
#include <queue>
#include <list>
using namespace std;

class Node
{
public:
    int val = 0;
    vector<Node *> children;

    Node(int val)
    {
        this->val = val;
    }
};

int height(Node *node)
{
    int maxheight = -1;
    for (Node *child : node->children)
    {
        maxheight = max(maxheight, height(child));
    }

    return maxheight + 1;
}

int size(Node *node)
{
    int s = 0;
    for (Node *child : node->children)
    {
        s += size(child);
    }

    return s + 1;
}

int maximumEle(Node *node)
{
    int maxEle = node->val;
    for (Node *child : node->children)
    {
        maxEle = max(maxEle, maximumEle(child));
    }

    return maxEle;
}

bool findData(Node *node, int data)
{
    if (node->val == data)
        return true;

    bool res = false;
    for (Node *child : node->children)
        res = res || findData(child, data);
    return res;
}

bool rootToNodePath(Node *node, int data, vector<Node *> &ans)
{
    if (node->val == data)
    {
        ans.push_back(node);
        return true;
    }

    bool res = false;
    for (Node *child : node->children)
    {
        res = res || rootToNodePath(child, data, ans);
    }

    if (res)
        ans.push_back(node);
    return res;
}

//{d,h}
vector<int> diameter_(Node *node)
{
    int h1 = -1, h2 = -1, d = 0;
    for (Node *child : node->children)
    {
        vector<int> ans = diameter_(child);
        if (ans[1] > h1)
        {
            h2 = h1;
            h1 = ans[1];
        }
        else if (ans[1] > h2)
            h2 = ans[1];

        d = max(ans[0], d);
    }

    return {max(h1 + h2 + 2, d), max(h1, h2) + 1};
}

int d = 0;
int diameter_02(Node *node)
{
    int h1 = -1, h2 = -1;
    for (Node *child : node->children)
    {
        int h = diameter_02(child);
        if (h > h1)
        {
            h2 = h1;
            h1 = h;
        }
        else if (h > h2)
            h2 = h;
    }
    d = max(h1 + h2 + 2, d);

    return max(h1, h2) + 1;
}

int diameter(Node *node)
{
    if (node == nullptr)
        return 0;
    return diameter_(node)[0];
}

// BFS
void bfs(Node *root)
{

    queue<Node *> que;
    que.push(root);

    int level = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        cout << "Level : " << level;
        while (size-- > 0)
        {
            Node *node = que.front();
            que.pop();

            cout << node->val;

            for (Node *child : node->children)
            {
                que.push(child);
            }
        }
        cout << endl;
        level++;
    }
}

vector<int> zigZag(Node *root)
{
    list<Node *> que; // push_back, pop_front
    list<Node *> st;  // push_front, pop_front

    que.push_back(root);
    vector<int> ans;

    int level = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            Node *node = que.front();
            que.pop_front();
            ans.push_back(node->val);
            if (level % 2 == 0)
            {
                for (int i = 0; i < node->children.size(); i++)
                    st.push_front(node->children[i]);
            }
            else
            {
                for (int i = node->children.size() - 1; i >= 0; i--)
                    st.push_front(node->children[i]);
            }
        }

        swap(que, st);
        level++;
    }
    return ans;
}

Node *linearize(Node *root)
{
    if (root->children.size() == 0)
        return root;

    Node *gTail = linearize(root->children.back());
    for (int i = root->children.size() - 2; i >= 0; i--)
    {
        Node *child = root->children[i];
        Node *tail = linearize(child);
        tail->children.push_back(root->children[i + 1]);
        root->children.pop_back();
    }

    return gTail;
}

bool isMirror(Node *root1, Node *root2)
{
    if (root1->children.size() != root2->children.size())
        return false;

    int size = root1->children.size();
    for (int i = 0, j = size - 1; j >= 0; i++, j--)
    {
        Node *child1 = root1->children[i];
        Node *child2 = root2->children[j];

        if (!isMirror(child1, child2))
            return false;
    }

    return true;
}

// O(KN)
int kThLargest(Node *node, int k)
{
    return 0;
}

// 114
