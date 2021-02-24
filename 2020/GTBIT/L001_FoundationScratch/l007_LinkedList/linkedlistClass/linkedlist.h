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
    int sizeOfLL = 0;

public:
    bool isEmpty()
    {
        return this->sizeOfLL == 0;
    }

    int size()
    {
        return this->sizeOfLL;
    }

    void display()
    {
        Node *curr = this->head;
        while (curr != nullptr)
        {
            cout << curr->data << " -> ";
            curr = curr->next;
        }
        cout << endl;
    }

    // Exceptions
private:
    void EmptyException()
    {
        if (this->sizeOfLL == 0)
        {
            cout << "LinkedList is Empty: -1" << endl;
            throw -1;
        }
    }

    void IndexOutOfBoundSizeExclusiveException(int idx)
    {
        if (idx < 0 || idx >= this->sizeOfLL)
        {
            cout << "Index Out Of Bound: -1" << endl;
            throw -1;
        }
    }

    void IndexOutOfBoundSizeInclusiveException(int idx)
    {
        if (idx < 0 || idx > this->sizeOfLL)
        {
            cout << "Index Out Of Bound: -1" << endl;
            throw -1;
        }
    }

    // Get_===========================================================================
public:
    int getFirst()
    {
        EmptyException();
        return this->head->data;
    }

    int getLast()
    {
        EmptyException();
        return this->tail->data;
    }

private:
    Node *getNodeAt(int idx)
    {
        Node *curr = this->head;
        while (idx-- > 0)
        {
            curr = curr->next;
        }
        return curr;
    }

public:
    int getAt(int idx)
    {
        IndexOutOfBoundSizeExclusiveException(idx);
        Node *node = getNodeAt(idx);
        return node->data;
    }
};