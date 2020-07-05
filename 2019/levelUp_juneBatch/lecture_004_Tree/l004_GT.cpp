#include <iostream>
#include <stack>
#include <vector>

using namespace std;

class Node
{
public:
    int data;
    vector<Node *> childs;

    Node(int data)
    {
        this->data = data;
    }
};

Node *createTree(vector<int> &arr)
{
    stack<Node *> st;
    for (int i = 0; i < arr.size() - 1; i++)
    {
        int ele = arr[i];
        if (ele == -1)
        {
            Node *node = st.top();
            st.pop();
            st.top()->childs.push_back(node);
        }
        else
            st.push(new Node(ele));
    }

    return st.top();
}

void preOrder(Node *node)
{
    cout << node->data << " ";
    for (Node *child : node->childs)
        preOrder(child);
}

void postOrder(Node *node)
{
    for (Node *child : node->childs)
        postOrder(child);
    cout << node->data << " ";
}

int height(Node *node)
{
    int h = -1;
    for (Node *child : node->childs)
        h = max(h, height(child));

    return h + 1;
}

int size(Node *node)
{
    int sz = 0;
    for (Node *child : node->childs)
        sz += size(child);

    return sz + 1;
}

void display(Node* node){

}

bool find(Node* node,int data){

}

void levelOrderLlineWise(Node* node){

}

void nodeToRootPath(Node* node,int data){

}

void distanceBetweenTwoNodes(Node* node,int d1,int d2){

}

void solve()
{
    vector<int> arr{10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 100, -1, 110, -1, -1, 90, -1, -1, 40, 120, 140, -1, 150, -1, -1, -1, -1};
}

int main()
{
    solve();
    return 0;
}
