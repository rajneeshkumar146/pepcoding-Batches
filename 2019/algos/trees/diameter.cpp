using namespace std;
#include <iostream>
#include <vector>
#include <math.h>

class Node
{
public:
    int data = 0;
    Node *left = nullptr;
    Node *right = nullptr;

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
    if (arr.size() == idx || arr[idx] == -1)
    {
        idx++;
        return NULL;
    }
    Node *nnode = new Node(arr[idx], NULL, NULL);
    idx++;
    nnode->left = create(arr);
    nnode->right = create(arr);

    return nnode;
}

int height(Node *node)
{
    if (node == nullptr)
        return 0;
    return max(height(node->left), height(node->right)) + 1;
}

// diameter is the maximum path between any two leaves of the tree. It can either pass through the root or can be within a sub-tree(i.e. without the root).
int diameter(Node *node)
{
    if (node == NULL)
        return 0;
    // max of left and right diamteres will give the maximum of diameters withing both the sub-trees.
    //  max of max diameter and combined height of left and right and root, will tell if diameter is larger with root or without root.
    return max(max(diameter(node->left), diameter(node->right)), height(node->left) + height(node->right) + 1);
}

// Finding height and diameter simulataneously at each level rather than calling the height recursion at every call.
vector<int> diameter_optimized(Node *node)
{
    if (node == NULL)
        return vector<int>(2);

    vector<int> left = diameter_optimized(node->left);
    vector<int> right = diameter_optimized(node->right);

    vector<int> ans(2);                                           //{diameter, height}
    ans[1] = max(left[1], right[1]) + 1;                          // curren_height = max(prev_leftHeight, prev_rightHeight) + 1
    ans[0] = max(max(left[0], right[0]), left[1] + right[1] + 1); // current_diameter = max of diameter through root and diameter without root.

    return ans;
}

// Using above optimization but in simpler way. Uses static variable. The final answer will be stored in the global variable rather than the returned value.
int dia = 0;
int diameter_static(Node *node)
{
    if (node == NULL)
        return 0;

    int left_height = diameter_static(node->left);   // keeping faith that the recursion would return the max height of left-subtree of node.
    int right_height = diameter_static(node->right); // same faith as above for right sub-tree

    // updating global variable dia at every step
    dia = max(dia, left_height + right_height + 1);
    return max(left_height, right_height) + 1;
}

int main()
{
    vector<int> arr{10, 20, 30, -1, -1, 40, -1, -1, 50, 60, 80, -1, -1, -1, 70, 90, -1, 100, -1, -1, -1};
    Node *root = create(arr);
    // cout << diameter(root) << endl;
    // cout << diameter_optimized(root)[0] << endl;
    diameter_static(root);
    cout << dia; // fetching the result from the global variable after its function call.
}
