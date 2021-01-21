#include <iostream>
using namespace std;

class linkedlist
{
private:
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
    int NumberOfNodes = 0;

public:
    int size()
    {
        return this->NumberOfNodes;
    }

    bool isEmpty()
    {
        return size() == 0;
    }

protected:
    void handelZeroSize(Node *node)
    {
        this->head = node;
        this->tail = node;
    }

    // ==================================================

    void addFirstNode(Node *node)
    {
        if (size() == 0)
            handelZeroSize(node);
        else
        {
            node->next = this->head;
            this->head = node;
        }

        this->NumberOfNodes++;
    }

public:
    void addFirst(int data)
    {
        Node *node = new Node(data);
        addFirstNode(node);
    }

    // ==================================================

protected:
    void addLastNode(Node *node)
    {
        if (size() == 0)
            handelZeroSize(node);
        else
        {
            this->tail->next = node;
            this->tail = node;
        }

        this->NumberOfNodes++;
    }

public:
    void addLast(int data)
    {
        Node *node = new Node(data);
        addLastNode(node);
    }

    // ==================================================

protected:
    Node *getNodeAt(int idx)
    {
        Node *temp = this->head;
        while (idx-- > 0)
        {
            temp = temp->next;
        }

        return temp;
    }

    void addNodeAt(Node *node, int idx)
    {
        if (idx == 0)
            addFirstNode(node);
        else if (idx == size())
            addLastNode(node);
        else
        {
            Node *nodeAt = getNodeAt(idx - 1);
            Node *forw = nodeAt->next;

            nodeAt->next = node;
            node->next = forw;
            this->NumberOfNodes++;
        }
    }

public:
    void addAt(int data, int idx)
    {
        if (idx < 0 || idx > size())
        {
            cout << "Invalid Index" << endl;
            throw -1;
        }

        Node *node = new Node(data);
        addNodeAt(node, idx);
    }

    // ==================================================

protected:
    void handelSize1()
    {
        this->head = nullptr;
        this->tail = nullptr;
    }

    
};
