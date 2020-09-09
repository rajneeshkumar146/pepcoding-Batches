#include <iostream>
using namespace std;

class linkedlist
{
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
    int elementCount = 0;

    //basic->============================================

    int size()
    {
        return this->elementCount;
    }

    bool isEmpty()
    {
        return this->elementCount == 0;
    }

public:
    string toString()
    {
        Node *curr = this->head;
        string sb = "";
        sb += "[";

        while (curr != nullptr)
        {
            sb += to_string(curr->data);
            if (curr->next != nullptr)
                sb += ",";
            curr = curr->next;
        }

        sb += "]";
        return sb;
    }

    //add->===============================================
private:
    void addFirstNode(Node *node)
    {
        if (this->head == nullptr)
        {
            this->head = node;
            this->tail = node;
        }
        else
        {
            node->next = this->head;
            this->head = node;
        }

        this->elementCount++;
    }

public:
    void addFirst(int val)
    {
        Node *node = new Node(val);
        addFirstNode(node);
    }

public:
    void addLastNode(Node *node)
    {
        if (this->head == nullptr)
        {
            this->head = node;
            this->tail = node;
        }
        else
        {
            this->tail->next = node;
            this->tail = node;
        }

        this->elementCount++;
    }

    void addLast(int val)
    {
        Node *node = new Node(val);
        addLastNode(node);
    }

    void addNodeAt(Node *node, int idx)
    {
        if (idx == 0)
            addFirstNode(node);
        else if (idx == this->elementCount)
            addLastNode(node);
        else
        {
            Node *prev = getNodeAt(idx - 1);
            Node *curr = prev->next;

            prev->next = node;
            curr->next = node;

            this->elementCount++;
        }
    }

    void addAt(int data, int idx)
    {
        if (idx < 0 || idx > this->elementCount)
        {
            throw("invalidLocation: " + to_string(idx));
        }

        Node *node = new Node(data);
        addNodeAt(node, idx);
    }

    //remove->============================================
    Node *removeFirstNode()
    {
        Node *node = this->head;
        if (this->elementCount == 1)
        {
            this->head = nullptr;
            this->tail = nullptr;
        }
        else
        {
            this->head = this->head->next;
            node->next = nullptr;
        }

        this->elementCount--;
        return node;
    }

    int removeFirst(int val)
    {
        if (this->elementCount == 0)
        {
            throw("nullptrPointerException: -1");
        }

        Node *node = removeFirstNode();
        int rv = node->data;
        delete node;
        return rv;
    }

    Node *removeLastNode()
    {
        Node *node = this->tail;
        if (this->elementCount == 1)
        {
            this->head = nullptr;
            this->tail = nullptr;
        }
        else
        {
            Node *prev = getNodeAt(this->elementCount - 2);
            this->tail = prev;
            prev->next = nullptr;
        }

        this->elementCount--;
        return node;
    }

    int removeLast(int val)
    {
        if (this->elementCount == 0)
        {
            throw("nullptrPointerException: -1");
        }

        Node *node = new Node(val);
        int rv = node->data;
        delete node;
        return rv;
    }

    Node *removeNodeAt(int idx)
    {
        if (idx == 0)
            return removeFirstNode();
        else if (idx == this->elementCount - 1)
            return removeLastNode();
        else
        {

            Node *prev = getNodeAt(idx - 1);
            Node *curr = prev->next;

            prev->next = curr->next;
            curr->next = nullptr;

            this->elementCount--;
            return curr;
        }
    }

    int removeAt(int idx)
    {
        if (idx < 0 || idx >= this->elementCount)
        {
            throw("invalidLocation: " + idx);
        }

        Node *node = removeNodeAt(idx);
        int rv = node->data;
        delete node;
        return rv;
    }

    //get->================================================
    Node *getFirstNode()
    {
        return this->head;
    }

    int getFirst()
    {
        if (this->elementCount == 0)
        {
            throw("nullptrPointerException: -1");
        }

        Node *node = getFirstNode();
        return node->data;
    }

    Node *getLastNode()
    {
        return this->tail;
    }

    int getLast()
    {
        if (this->elementCount == 0)
        {
            throw("nullptrPointerException");
        }

        Node *node = getLastNode();
        return node->data;
    }

    Node *getNodeAt(int idx)
    {
        Node *curr = this->head;

        while (idx-- > 0)
        {
            curr = curr->next;
        }

        return curr;
    }

    int getAt(int idx)
    {
        if (idx < 0 || idx >= this->elementCount)
        {
            throw("invalidLocation: " + idx);
        }

        Node *node = getNodeAt(idx);
        return node->data;
    }
};

int main()
{
    linkedlist ll;
    for (int i = 1; i <= 10; i++)
    {
        ll.addLast(i * 10);
    }

    for (int i = 10; i >= 1; i--)
    {
        ll.addFirst(i * 10);
    }

    cout << ll.toString() << endl;

    return 0;
}