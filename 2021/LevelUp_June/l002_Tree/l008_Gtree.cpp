#include <iostream>
#include <vector>

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

bool rootToNodePath(Node* node,int data,vector<int>& ans){

}

int diameter(Node* node){

}




