#include <iostream>
#include <vector>
#include <stack>

using namespace std;

class Node
{
public:
    int data;
    vector<Node*> childs;

    Node(int data)
    {
        this->data = data;
    }

    Node()
    {
    }
};

Node* createGTTree(vector<int>& arr){
    stack<Node*> st;
    for(int i=0;i<arr.size()-1;i++){

        if(arr[i]!=-1){
            Node* node=new Node(arr[i]);
            st.push(node);
        }else{
            Node* node=st.top(); st.pop();
            st.top()->childs.push_back(node);
        }
    }

    return st.top();
}





