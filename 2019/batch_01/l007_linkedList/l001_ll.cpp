#include <iostream>
using namespace std;
class LinkedList
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

private:
    Node *head = nullptr;
    Node *tail = nullptr;
    int size = 0;

    //Basic.========================================

public:
    ~LinkedList()
    {
        Node *temp = head;
        while (head != nullptr)
        {
            head = head->next;
            delete temp;
            temp = head;
        }
    }

    void addFirst(int data)
    {
        Node *node = new Node(data);
        if (head == nullptr)
        {
            head = node;
            tail = node;
        }
        else
        {
            node->next = head;
            head = node;
        }
        size++;
    }

    void addLast(int data)
    {
        Node *node = new Node(data);
        if (tail == nullptr)
        {
            head = node;
            tail = node;
        }
        else
        {
            tail->next = node;
            tail = node;
        }
        size++;
    }

    int removefirst()
    {
        Node *rn = nullptr;
        if (head != nullptr)
        {
            if (size == 1)
            {
                tail = nullptr;
            }
            rn = head;
            head = head->next;
        }

        int data = rn != nullptr ? rn->data : -1;
        size--;
        delete rn;
        return data;
    }

    Node *getNodeAt(int idx)
    {
        if (idx == 0)
            return head;

        Node *temp = head;
        while (idx != 0)
        {
            temp = temp->next;
            idx--;
        }

        return temp;
    }

    void addAt(int idx, int data)
    {
        if (idx > size || idx < 0)
            return;
        if (idx == 0)
            addFirst(data);
        else if (idx == size)
            addLast(data);
        else
        {
            Node *prev = getNodeAt(idx - 1);
            Node *next = prev->next;

            Node *node = new Node(data);
            prev->next = node;
            node->next = next;
            size++;
        }
    }

    int removeLast()
    {
        Node *rn = nullptr;
        if (size == 1)
        {
            rn = tail;
            tail = nullptr;
            head = nullptr;
        }
        else
        {
            Node *temp = getNodeAt(size - 2);
            rn = tail;
            temp->next = nullptr;
            tail = temp;
        }

        int data = rn != nullptr ? rn->data : -1;
        delete rn;
        return data;
    }

    void removeAt(int idx)
    {
        if (idx >= size || idx < 0)
            return;
        if (idx == 0)
            removefirst();
        else if (idx == size - 1)
            removeLast();
        else
        {
            Node *prev = getNodeAt(idx - 1);
            Node *forward = prev->next;

            prev->next = forward->next;
            size--;
            delete forward;
        }
    }

    Node *mid()
    {
        Node *slow = head;
        Node *fast = head;

        while (fast != nullptr && fast->next != nullptr && fast->next->next != nullptr)
        {
            fast = fast->next->next;
            slow = slow->next;
        }

        return slow;
    }

    void display()
    {
        Node *curr = head;
        while (curr != nullptr)
        {
            cout << curr->data << " ";
            curr = curr->next;
        }
        cout << endl;
    }

    //reverse.========================================
};

void solve()
{
    LinkedList ll;
    for (int i = 1; i <= 10; i++)
    {
        ll.addFirst(i * 10);
    }

    ll.removeLast();
    ll.display();
}

int main()
{
    solve();
    return 0;
}