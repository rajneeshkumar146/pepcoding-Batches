/* Given a tree:
    1. Check if the tree is BST or not
    2. Find all BST inside the tree (Leaves are BST too)
    3. Size of largest BST
    4. Root node of largest BST
*/

using namespace std;
#include <iostream>
#include <vector>

class Node
{
public:
    int data = 0;
    Node *left = NULL;
    Node *right = NULL;

    Node(int data, Node *left, Node *right)
    {
        this->data = data;
        this->left = left;
        this->right = right;
    }
};

int idx = 0;
Node *create(vector<int> &arr)
{
    if (idx == arr.size() || arr[idx] == -1)
    {
        idx++;
        return nullptr;
    }
    Node *nnode = new Node(arr[idx], NULL, NULL);
    idx++;
    nnode->left = create(arr);
    nnode->right = create(arr);

    return nnode;
}

// class to contain all variables
class BSTpair
{
public:
    bool isBST = true; //leaves will be taken as true for isBST
    int count_all_BST = 0;
    int size_largest_BST = 0;
    int largest_root = 0;

    // Min and Max variables to store min and max values at each node
    int min = (int)1e8; //Set to +infinity so that it could be modified with traversal.
    int max = (int)-1e8;

    void display()
    {
        cout << "isBST: " << isBST << endl;
        cout << "count_all_BST: " << count_all_BST << endl;
        cout << "size_largest_BST: " << size_largest_BST << endl;
        cout << "largest_root : " << largest_root << endl;
        cout << "min:" << min << endl;
        cout << "max: " << max << endl;
        cout << "#############" << endl
             << endl;
    }
};

BSTpair BSTsol(Node *node)
{
    if (node == NULL)
    {
        // cout << "hit the null. Return fresh BSTPair..." << endl;
        BSTpair b;
        return b;
    }

    // cout << "at node: " << node->data << endl;
    BSTpair lp, rp, myPair; //left and right pair
    lp = BSTsol(node->left);
    // cout << "displaying lp: --- of " << node->data << endl;
    // lp.display();
    rp = BSTsol(node->right);
    // cout << "displaying rp: ----of " << node->data << endl;
    // rp.display();

    if (lp.isBST && rp.isBST && lp.max < node->data && rp.min >= node->data) //current node along with left and right sub-tree become a BST i.e. each sub-tree under the current node is a part of a BST.
    {
        // cout << "Inside if" << endl;
        myPair.count_all_BST = lp.count_all_BST + rp.count_all_BST + 1; //count of all BST on lp and rp and + 1 for the another BST formed from left branch, right branch and current node.
        myPair.size_largest_BST = myPair.count_all_BST;                 // since the current node system is also a BST, it would become the largest BST so far, and its size would be = count of total BST.
        myPair.largest_root = node->data;                               // root of largest BST sub-tree would be current node
    }
    else
    {
        // cout << "inside else: ----" << endl;
        myPair.isBST = false;
        myPair.count_all_BST = lp.count_all_BST + rp.count_all_BST;
        myPair.size_largest_BST = max(lp.size_largest_BST, rp.size_largest_BST);

        (lp.size_largest_BST > rp.size_largest_BST) ? (myPair.largest_root = lp.largest_root) : (myPair.largest_root = rp.largest_root); //rightPair(rp) value will be chosen if rp size >= lp size
    }

    myPair.min = min(min(lp.min, rp.min), node->data);
    myPair.max = max(max(lp.max, rp.max), node->data);

    // cout << "displaying myPair: -----of " << node->data << endl;
    // myPair.display();

    return myPair;
}

int main()
{
    vector<int> arr{10, 15, 10, 9, -1, -1, 13, -1, -1, 20, 28, -1, -1, 48, -1, -1, 16, 12, -1, -1, 18, -1, -1};
    Node *root = create(arr);
    BSTpair a;
    a.display();
    a = BSTsol(root);
    a.display();
    return 0;
}