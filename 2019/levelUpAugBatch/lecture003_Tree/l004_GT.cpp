#include <iostream>
#include <vector>
#include <stack>

using namespace std;

class Node
{
public:
    int data = 0;
    vector<Node *> childs;

    Node(int data)
    {
        this->data = data;
    }
};

Node *constructTree(vector<int> &arr)
{
    stack<Node *> st;
    for (int i = 0; i < arr.size() - 1; i++)
    {
        int ele = arr[i];
        if (ele != -1)
            st.push(new Node(ele));
        else
        {
            Node *node = st.top();
            st.pop();
            st.top()->childs.push_back(node);
        }
    }

    return st.top();
}



void display(Node *node)
{

    string sb = "";
    sb += (node->data + " -> ");

    for (Node *child : node->childs)
        sb += (child->data + " ");

    cout << sb << endl;

    for (Node *child : node->childs)
        display(child);
}
