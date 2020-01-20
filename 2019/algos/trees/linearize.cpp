/* To linearize a given tree.
    1. to left-oriented tree in post-order
    2. to its inorder as Doubly Linked List
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

void display(Node *node)
{
    if (node == NULL)
        return;
    string str = "";

    str += node->left == NULL ? "." : to_string(node->left->data);
    str += "->" + to_string(node->data) + "<-";
    str += node->right == NULL ? "." : to_string(node->right->data);
    cout << str << endl;

    display(node->left);
    display(node->right);
}

// Given a tree, linearize it to a left-oriented tree
/* eg: 
       20
    10  30
        50  70
to
                20
            10
        50
    30
70 
 */
Node *linearize(Node *node)
{
    if (node == nullptr)
        return nullptr;
    if (node->left == nullptr && node->right == nullptr)
        return node;

    Node *left_tail = linearize(node->left);   //assume that the recursion linearized the node->left and returned left_tail.
    Node *right_tail = linearize(node->right); //same assumption as above.
    if (left_tail == NULL)                     // if only right of node exists
    {
        node->left = node->right;
        node->right = NULL;
        return right_tail;
    }
    else if (right_tail == NULL) // if only left exists.
    {
        return left_tail;
    }
    else //both left and right tail exists.
    {
        left_tail->left = node->right;
        node->right = NULL;
        return right_tail;
    }
} // code repeated in if else for easier understanding. other wise return statement can be brought out and node->right=NULL can be written outside only once for same output.

// Given a tree, convert it into a linearized inorder traversal linked it each other as doubly linked list DLL
/* eg: 
       20
    10  30
        50  70
to
    head > 10 >< 50 >< 20 >< 30 >< 70 <tail
    where > and < represent pointers to right and left respectively
*/
Node *head = NULL;
Node *previous = NULL;
void linearize_Inorder_DLL(Node *root)
{
    if (root == NULL)
        return;
    // making calls/making calculations in inorder i.e. left, current, right
    linearize_Inorder_DLL(root->left); //left call
    // calculations for current node i.e. root
    if (head == NULL)
        head = root;
    else
    {
        root->left = previous;  //for the first time, previous will be NULL, hence root->left will be NULL
        previous->right = root; // and hence, this statement won't be effective that time. All other time, there would be a head and a previous.
    }
    previous = root;
    linearize_Inorder_DLL(root->right); // right call
}

int main()
{
    vector<int> arr{20, 10, -1, -1, 30, 50, -1, -1, 70, -1, -1};
    Node *root = create(arr);
    display(root);
    // linearize(root);
    // display(root);
    linearize_Inorder_DLL(root);
    while (head != NULL)
    { //head is a global/static variable pointing to head of the linearized DLL tree
        cout << head->data << "->";
        head = head->right;
    }
    cout << endl;
    while (previous != NULL)
    { // previous is the global/static variable pointing to tail of the linearized DLL tree
        cout << previous->data << "<-";
        previous = previous->left;
    }
    return 0;
}