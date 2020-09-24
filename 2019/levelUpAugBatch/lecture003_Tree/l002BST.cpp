#include <iostream>
#include <vector>

using namespace std;

class Node
{
public:
    int data = 0;
    Node *left = nullptr;
    Node *right = nullptr;

    Node(int data)
    {
        this->data = data;
    }
};

Node *constructTree(vector<int> &arr, int si, int ei)
{
    if (si > ei)
        return nullptr;

    int mid = (si + ei) / 2;
    Node *node = new Node(arr[mid]);
    node->left = constructTree(arr, si, mid - 1);
    node->right = constructTree(arr, mid + 1, ei);

    return node;
}

int maximum(Node* node){
    Node* curr = node;
    while(curr->right != nullptr){
        curr = curr->right;
    }

    return curr->data;
}

int minimum(Node* node){
    Node* curr = node;
    while(curr->left != nullptr){
        curr = curr->left;
    }

    return curr->data;
}

bool find(Node* node,int data){
    Node* curr = node;

    while(curr!=nullptr){
        if(curr->data == data) return true;
        else if(curr->data < data) curr = curr->right;
        else curr = curr->left;
    }

    return false;
}





void solve()
{
    vector<int> arr;
    for (int i = 1; i <= 14; i++)
        arr.push_back(i * 10);

    Node *node = constructTree(arr, 0, arr.size() - 1);

}

int main()
{
    solve();
    return 0;
}
