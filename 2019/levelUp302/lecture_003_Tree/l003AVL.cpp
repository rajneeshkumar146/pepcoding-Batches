#include <iostream>
#include <vector>

using namespace std;

class Node
{
public:
    int data;
    Node *left = nullptr;  // Node* left=nullptr;
    Node *right = nullptr; // Node* right=nullptr;

    int height = 0;
    int bal = 0;

    Node(int data)
    {
        this->data = data;
        this->height = 0;
        this->bal = 0;
    }

    Node()
    {
    }
};

//AVL Util.========================================================

void UpdateHeightAndBalance(Node *node) // O(1)
{
    int lh = -1;
    int rh = -1;
    if (node->left != nullptr)
        lh = node->left->height;
    if (node->right != nullptr)
        rh = node->right->height;

    node->height = max(lh, rh) + 1;
    node->bal = lh - rh;
}

//ll rotation.
Node *ll(Node *A) // O(1)
{
    Node *B = A->left;
    Node *BkaRight = B->right;

    B->right = A;
    A->left = BkaRight;

    UpdateHeightAndBalance(A);
    UpdateHeightAndBalance(B);

    return B;
}

//rr rotation.
Node *rr(Node *A) // O(1)65
{
    Node *B = A->right;
    Node *Bkaleft = B->left;

    B->left = A;
    A->right = Bkaleft;

    UpdateHeightAndBalance(A);
    UpdateHeightAndBalance(B);

    return B;
}

//getRotation

Node *getRotation(Node *node) //O(1)
{
    UpdateHeightAndBalance(node);
    if (node->bal == 2) //ll,lr
    {
        if (node->left->bal == 1) // ll
            return ll(node);
        else //lr
        {
            node->left = rr(node->left);
            return ll(node);
        }
    }
    else if (node->bal == -2) // rr,rls
    {

        if (node->right->bal == -1) //rr
            return rr(node);
        else //rl
        {
            node->right = ll(node->right);
            return rr(node);
        }
    }
    return node;
}

//BST.===========================================================================

Node *constructBST(vector<int> &arr, int si, int ei) // O(n) if sorted array.
{
    if (si > ei)
        return nullptr;

    int mid = (si + ei) >> 1; // (si+ei)/2;
    Node *node = new Node(arr[mid]);
    node->left = constructBST(arr, si, mid - 1);
    node->right = constructBST(arr, mid + 1, ei);

    UpdateHeightAndBalance(node);
    return node;
}

void display(Node *node) // O(n)
{
    if (node == nullptr)
        return;

    string str = "";
    str += ((node->left != nullptr) ? to_string(node->left->data) + "[" + to_string(node->left->bal) + "," + to_string(node->left->height) + "]" : ".");
    str += " <- " + to_string(node->data) + "[" + to_string(node->bal) + "," + to_string(node->height) + "]" + " -> ";
    str += ((node->right != nullptr) ? to_string(node->right->data) + "[" + to_string(node->right->bal) + "," + to_string(node->right->height) + "]" : ".");
    cout << (str) << endl;

    display(node->left);
    display(node->right);
}

Node *addData(Node *root, int data) //O(logn)
{
    if (root == nullptr)
        return new Node(data);
    if (data < root->data)
        root->left = addData(root->left, data);
    else
        root->right = addData(root->right, data);

    return getRotation(root); //O(1)
}

int maximumEle(Node *node) // O(logn)
{
    Node *curr = node;
    while (curr->right != nullptr)
        curr = curr->right;

    return curr->data;
}

Node *removeData(Node *root, int data) //O(logn)
{
    if (root == nullptr)
        return nullptr; // never found data;

    if (data < root->data)
        root->left = removeData(root->left, data);
    else if (data > root->data)
        root->right = removeData(root->right, data);
    else
    { // data found.

        if (root->left == nullptr || root->right == nullptr)
            return root->left != nullptr ? root->left : root->right;

        int maxInleft = maximumEle(root->left);
        root->data = maxInleft;
        root->left = removeData(root->left, maxInleft);
    }
    return getRotation(root); //O(1)
}

void solve()
{
    vector<int> arr = {100, 40, 20, 70, 30, 80, 10, 60, 90, 50, 130, 120, 110};
    // Node *root = constructBST(arr, 0, arr.size() - 1);

    Node *root = nullptr;
    for (int i = 0; i < arr.size(); i++)
    {
        root = addData(root, arr[i]);
        display(root);
        cout << endl<< endl<< endl;
    }

    display(root);
}

int main()
{
    solve();
    return 0;
}
