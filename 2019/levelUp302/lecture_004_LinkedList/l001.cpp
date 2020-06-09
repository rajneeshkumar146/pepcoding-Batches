#include <iostream>

using namespace std;

class linkedlist
{
public:
    class Node
    {
    public:
        int data = 0;
        Node *next = nullptr;

        Node(int data)
        {
            this->data = data;
        }
    };

    Node *head = nullptr;
    Node *tail = nullptr;
    int size = 0;

    int size_()
    {
        return this->size;
    }

    bool isEmpty()
    {
        return this->size == 0;
    }

    void display(){
        Node* curr=this->head;
        while(curr!=nullptr){
            cout<<curr->data<<" ";
            curr=curr->next;
        }
        cout<<endl;
    }

    //Add_Function.===================================================

    void addFirstNode(Node *node)
    {
        if (this->size == 0)
        {
            this->tail = node;
            this->head = node;
        }
        else
        {
            node->next = this->head;
            this->head = node;
        }
        this->size++;
    }

    void addFirst(int data)
    {
        Node *node = new Node(data);
        addFirstNode(node);
    }
};

int main(){
    linkedlist ll;
    for(int i=1;i<10;i++){
        ll.addFirst(i*10);
    }
    ll.display();

    return 0;
}