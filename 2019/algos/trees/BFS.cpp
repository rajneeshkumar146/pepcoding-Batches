// Level order traversal or Breadth First Search  using LinkedList
using namespace std;
#include <iostream>
#include <list>
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

// using iterator of a list to iterate the lst in a for loop
void displayList(list<Node *> &lst)
{
    list<Node *>::iterator it;
    cout << "Displaying list ->" << endl;
    for (it = lst.begin(); it != lst.end(); it++)
    {
        if (*it != NULL)
        {
            cout << (*it)->data << " ";
        }
        else
            cout << "NULL ";
    }
    cout << endl;
}

// putting null at end of each level and using it to detect continuation or end of loop.
// 10 null -> null 20 50 -> 20 50 null -> 50 null 30 40 -> null 30 40 60 70 -> 30 40 60 70 null and so on
void BFS1_usingNULL(Node *node)
{
    list<Node *> lst;
    lst.push_back(node);
    lst.push_back(NULL);
    // displayList(lst);

    int level = 0;
    cout << "Level = " << level << endl;
    while (lst.size() != 1)
    {
        Node *rnode = lst.front();
        lst.pop_front();
        cout << rnode->data << " ";

        if (rnode->left != NULL)
            lst.push_back(rnode->left);

        if (rnode->right != NULL)
            lst.push_back(rnode->right);

        // displayList(lst);

        if (lst.front() == NULL)
        {
            lst.pop_front();
            lst.push_back(NULL);
            level++;
            cout << endl
                 << "Level is: " << level << endl;
        }
    }
}

// By keeping a count of number of elements before starting the loop, we can track the elements of a particular level by running loop only till the initial size of the list.
// 10 (run 1 time)->20 50 (run 2 times) -> 30 40 60 70 (run 4 times) ....and so on
void BFS2_sizeMethod(Node *node)
{
    list<Node *> lst;
    lst.push_back(node);
    int level = 0;

    while (lst.size() > 0)
    {
        cout << "Level is: " << level << endl;
        int size = lst.size();
        while (size-- > 0)
        {
            Node *rnode = lst.front();
            lst.pop_front();
            cout << rnode->data << " ";
            if (rnode->left != NULL)
                lst.push_back(rnode->left);
            if (rnode->right != NULL)
                lst.push_back(rnode->right);
        }
        level++;
        cout << endl;
    }
}

int main()
{
    vector<int> arr{10, 20, 30, -1, -1, 40, -1, -1, 50, 60, 80, -1, -1, -1, 70, 90, -1, 100, -1, -1, -1};
    // vector<int> arr{10, -1, -1};
    Node *root = create(arr);
    // BFS1_usingNULL(root);
    BFS2_sizeMethod(root);

    return 0;
}