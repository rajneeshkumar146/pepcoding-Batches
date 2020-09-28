#include <iostream>
#include <vector>

using namespace std;

class Node
{
public:
    int data = 0;
    Node *left = nullptr;
    Node *right = nullptr;

    int bal = 0;
    int height = 0;

    Node(int data)
    {
        this->data = data;
    }
};

void updateHeightAndBalance(Node *node) // O(1)
{
    int lh = -1;
    int rh = -1;
    if (node->left != nullptr)
        lh = node->left->height;
    if (node->right != nullptr)
        rh = node->right->height;

    node->bal = lh - rh;
    node->height = max(lh, rh) + 1;
}

Node *rightRotation(Node *A) // O(1)
{
    Node *B = A->left;
    Node *BKaRight = B->right;

    B->right = A;
    A->left = BKaRight;

    updateHeightAndBalance(A);
    updateHeightAndBalance(B);

    return B;
}

Node *leftRotation(Node *A) //O(1)
{
    Node *B = A->right;
    Node *BKaLeft = B->left;

    B->left = A;
    A->right = BKaLeft;

    updateHeightAndBalance(A);
    updateHeightAndBalance(B);

    return B;
}

Node *getRotation(Node *node) // O(1)
{
    updateHeightAndBalance(node);

    if (node->bal == 2) // ll,lr
    {
        if (node->left->bal == 1) // ll
        {
            return rightRotation(node);
        }
        else // lr
        {
            node->left = leftRotation(node->left);
            return rightRotation(node);
        }
    }
    else if (node->bal == -2) // rr,rl
    {
        if (node->right->bal == 1) // rl
        {
            node->right = rightRotation(node->right);
            return leftRotation(node);
        }
        else // rr
        {
            return leftRotation(node);
        }
    }

    return node;
}

//========================================================

void display(Node *node)
{
    if (node == nullptr)
        return;

    string str = "";
    str += (node->left != nullptr ? to_string(node->left->data) : ".");
    str += " <- " + to_string(node->data) + " -> ";
    str += (node->right != nullptr ? to_string(node->right->data) : ".");

    cout << str << endl;

    display(node->left);
    display(node->right);
}

int maximum(Node *node)
{
    Node *curr = node;
    while (curr->right != nullptr)
    {
        curr = curr->right;
    }

    return curr->data;
}

Node *insertIntoBST(Node *root, int val) //O(logn)
{
    if (root == nullptr)
        return new Node(val);

    if (val < root->data)
        root->left = insertIntoBST(root->left, val);
    else
        root->right = insertIntoBST(root->right, val);

    root = getRotation(root);
    return root;
}

Node *deleteNode(Node *root, int val) //O(logn)
{
    if (root == nullptr)
        return nullptr;

    if (val < root->data)
        root->left = deleteNode(root->left, val);
    else if (val > root->data)
        root->right = deleteNode(root->right, val);
    else
    {

        if (root->left == nullptr || root->right == nullptr)
        {
            Node *node = root->left != nullptr ? root->left : root->right;
            delete root; // not for java.
            return node;
        }

        int mVal = maximum(root->left);
        root->data = mVal;
        root->left = deleteNode(root->left, mVal);
    }

    root = getRotation(root);
    return root;
}

void solve()
{
    Node *root = nullptr;
    for (int i = 1; i <= 14; i++)
        root = insertIntoBST(root, i * 10);

    display(root);
}

int main()
{
    solve();
    return 0;
}
