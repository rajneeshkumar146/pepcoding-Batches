/* 
    PathSum problems:
1. Given a tree and a targetSum:
        1. return true/false if any path from input root node to any leaf with  sum = targetSum exists .
        2. return all paths possible for above problem into a 2D vector
        3. In s given tree, find all paths from any node to any node down its respective branch, so that sum = targetSum.

2. Find maxSum from 1 leaf to another leaf in the given tree
*/

using namespace std;
#include <iostream>
#include <vector>
#include <map>

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

void displayTree(Node *node)
{
    if (node == NULL)
        return;

    string str = "";
    str += (node->left == NULL) ? "." : to_string(node->left->data);
    str += "->" + to_string(node->data) + "<-";
    str += (node->right == NULL) ? "." : to_string(node->right->data);
    cout << str << endl;

    displayTree(node->left);
    displayTree(node->right);
}

void display2DVector(vector<vector<int>> &vec)
{
    for (vector<int> a : vec)
    {
        for (int b : a)
        {
            cout << b << " ";
        }
        cout << endl;
    }
    cout << endl;
}

// return true/false if any path from input node to any leaf with sum = targetSum exists. Also print the path
bool PathSum_root2leaf(Node *node, int target, string ans)
{
    if (node == NULL)
        return false;
    if (node->left == NULL and node->right == NULL && (target - node->data) == 0)
    {
        cout << ans << " " << node->data << endl;
        return true;
    }
    bool res = false;
    res = res || PathSum_root2leaf(node->left, target - node->data, ans + " " + to_string(node->data)); //if res becomes true at any instant, the other part of this OR expression won't be called and checked.
    res = res || PathSum_root2leaf(node->right, target - node->data, ans + " " + to_string(node->data));
    return res;
}

/* return all paths for above problem in a 2D vector
Method-1 : simple creating a 2D list at base case and adding element to the front of it
 */
vector<vector<int>> PathSum_returnPath_method1(Node *node, int target)
{
    if (node == NULL)
    {
        // cout << "returned empty vector" << endl;
        return vector<vector<int>>(); //return an empty 2D vector
    }
    if (node->left == NULL && node->right == NULL && (target - node->data) == 0) //vector with path will be returned only when targetSum is achieved
    {
        vector<vector<int>> base; // creating the 2D vector because the function return type is 2D vector
        vector<int> small;
        small.push_back(node->data);
        base.push_back(small);
        // cout << "Returning from base case with vector containint " << node->data << endl;
        return base;
    }

    vector<vector<int>> myAns;
    // cout << "Calling left of " << node->data << " target remaining is " << target - node->data << endl;
    vector<vector<int>> left = PathSum_returnPath_method1(node->left, target - node->data); //faith that this would give all paths to the left of current node
    // display2DVector(left);
    if (left.size() != 0) //left not empty
    {
        for (vector<int> small : left)
        {
            small.insert(small.begin(), node->data); //adding current node to the top of all paths returned
            cout << "Added " << node->data << endl;
            myAns.push_back(small);
        }
    }
    vector<vector<int>> right = PathSum_returnPath_method1(node->right, target - node->data);
    if (right.size() != 0) //right not empty
    {
        for (vector<int> small : right)
        {
            small.insert(small.begin(), node->data);
            // cout << "Added " << node->data << endl;
            myAns.push_back(small);
        }
    }

    return myAns;
}

// Method-2: pass a 1-D and a 2-D vector as parameters into the function
void PathSum_returnPath_method2(Node *node, int target, vector<vector<int>> &ans, vector<int> &temp)
{
    if (node == NULL)
        return;
    if (node->left == NULL && node->right == NULL && (target - node->data) == 0)
    {
        vector<int> small = temp;
        small.insert(small.end(), node->data);
        ans.push_back(small);
        return;
    }
    // We here use the technique of generating all possible paths by choosing each node once when going down a node and removing it while coming up
    temp.push_back(node->data);
    PathSum_returnPath_method2(node->left, target - node->data, ans, temp);
    PathSum_returnPath_method2(node->right, target - node->data, ans, temp);
    temp.pop_back();
}

/* PathSum from any node to downward node using Hashmaps in O(n) time */
int PathSum_downwardOnly(Node *node, int target, int prefixSum, map<int, int> &mp)
{
    if (node == NULL)
        return 0;

    // cout << "At node : " << node->data << endl;
    prefixSum += node->data; //every branch will keep adding its own branch elements till leaf is encountered
    // cout << "prefixSum=" << prefixSum << endl;
    int count = 0;
    if (mp.find(prefixSum - target) != mp.end())
    {
        count = mp.at(prefixSum - target); //if prefixSum - target existed before, then its key will give total number of ways target could be made, uptil this point of time.
        //count is set to 0 by default, when prefixSum - target is not available in the hashmap
        // cout << "Found " << prefixSum - target << " in the hashmap" << endl;
    }

    mp[prefixSum]++; //if prefixsum is a key in the hashmap, then increment its value by 1; if its not available, then it will be created and the value will be set to 1.
    // cout << "Incremented value of " << prefixSum << " to " << mp.at(prefixSum) << endl;

    count += PathSum_downwardOnly(node->left, target, prefixSum, mp);
    count += PathSum_downwardOnly(node->right, target, prefixSum, mp);

    if (mp.at(prefixSum) == 1)
    {
        mp.erase(prefixSum); //backtracking by undoing the changes made by mp[prefixSum]++, by erasing
        // cout << "Removed value of " << prefixSum << endl;
    }
    else
    {
        mp[prefixSum]--; // or reducing the frequency by 1.
        // cout << "Decremented value of " << prefixSum << " to " << mp.at(prefixSum) << endl;
    }
    return count;
}

/* Function to set the static variable max_leaf2leaf as the maximum possible sum from a leaf to another leaf in the given tree. Here, the function returns maximum sum from given node to the leaf which gives the maximum sum when all elements of the path traced is added. */
int max_leaf2leaf = (int)-1e8;
int Leaf2Leaf_maxSum(Node *node)
{
    if (node == nullptr)
        return 0;

    int left_leaf2Node_sum, right_leaf2Node_sum;

    left_leaf2Node_sum = Leaf2Leaf_maxSum(node->left);   //faith that maximum node to leaf sum on node->left will be returned.
    right_leaf2Node_sum = Leaf2Leaf_maxSum(node->right); //faith that maximum node to leaf sum on node->right will be returned.

    if (node->left != NULL && node->right != NULL)
        max_leaf2leaf = max(max_leaf2leaf, left_leaf2Node_sum + right_leaf2Node_sum + node->data);
    // checking if leaf2leaf sum including present node is greater than the answer stored previously.

    max_leaf2leaf = max(max_leaf2leaf, max(left_leaf2Node_sum, right_leaf2Node_sum) + node->data);
    return max(left_leaf2Node_sum, right_leaf2Node_sum) + node->data;
}

int main()
{
    vector<int> arr{10, 15, 10, 9, -1, -1, 13, -1, -1, 20, 28, -1, -1, 48, -1, -1, 16, 12, -1, -1, 18, -1, -1};
    Node *root = create(arr);
    // displayTree(root);
    // cout << PathSum_root2leaf(root, 44, "");
    // vector<vector<int>> paths;
    // paths = PathSum_returnPath_method1(root, 44);

    // vector<vector<int>> paths;
    // vector<int> temp;
    // PathSum_returnPath_method2(root, 44, paths, temp);
    // display2DVector(paths);

    map<int, int> hshmap;
    hshmap.insert(pair<int, int>(0, 1));
    cout << PathSum_downwardOnly(root, 44, 0, hshmap);

    // // Leaf2Leaf_maxSum(root);
    // cout << max_leaf2leaf << endl;

    return 0;
}