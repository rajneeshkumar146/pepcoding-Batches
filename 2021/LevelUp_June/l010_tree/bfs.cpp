#include<queue>

using namespace std;

// left view of a binary tree ====================================================== 
struct TreeNode {
      int val;
      TreeNode *left;
      TreeNode *right;
      TreeNode() : val(0), left(nullptr), right(nullptr) {}
      TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
      TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
  };

  struct Node
{
    int data;
    struct Node* left;
    struct Node* right;
    
    Node(int x){
        data = x;
        left = right = nullptr;
    }
};

vector<int> leftView(TreeNode* root){
    queue<TreeNode*> q;
    vector<int> ans;

    q.push(root);

    while(q.size()){
        int size=q.size();
        bool first=true;

        while(size--){
            auto top=q.front();
            q.pop();

            if(first){
                ans.push_back(top->val);
                first=false;
            }

            if(top->left!=nullptr){
                q.push(top->left);
            }
            
            if(top->right!=nullptr){
                q.push(top->right);
            }
        }
    }

    return ans;
}

void leftViewRec(vector<int>& ans,Node* root, int level){
    if(root==nullptr) return;

    if(level==ans.size()){
        ans.push_back(root->data);
    }

    leftViewRec(ans,root->left,level+1);
    leftViewRec(ans,root->right,level+1);
}

vector<int> leftView(Node *root)
{
   vector<int> ans;

   leftViewRec(ans,root,0);
   
   return ans;
}


// right view of a tree =========================================== 

void rightViewRec(vector<int>& ans,TreeNode* root, int level){
    if(root==nullptr) return;

    if(level==ans.size()){
        ans.push_back(root->val);
    }

    rightViewRec(ans,root->right,level+1);
    rightViewRec(ans,root->left,level+1);
}

vector<int> rightSideView(TreeNode* root) {
    vector<int> ans;

   rightViewRec(ans,root,0);
   
   return ans;
}

int main(){

}