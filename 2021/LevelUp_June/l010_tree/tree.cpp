#include<vector>

class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};

void rec(Node* root, vector<int>& ans){
    for(auto child:root->children){
        rec(child,ans);
    }

    ans.push_back(root->val);
}

vector<int> postorder(Node* root) {
    if(root==nullptr) return {};
        vector<int> ans;

        rec(root,ans);
        return ans;
}


// is mirror 

bool areMirror(Node* n1, Node* n2) {
        if(n1->children.size()!=n2->children.size()) return false;
        // if(n1.data!=n2.data) return false;
        
        int size=n1->children.size();
        
        for(int i=0; i<size; i++){
            if(!areMirror(n1->children[i],n2->children[size-1-i])) return false;
        }
        
        return true;
      }