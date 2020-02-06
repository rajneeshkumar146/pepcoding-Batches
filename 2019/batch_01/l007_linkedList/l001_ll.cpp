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
        if (size == 0)
            return -1;
        
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

private:
    class pairReve
    {
    public:
        Node *prevNode = nullptr;
    };

    void reverseDataRec_(Node *node, pairReve *prev, int level)
    {
        if (node == nullptr)
            return;

        reverseDataRec_(node->next, prev, level + 1);
        if (level > this->size / 2)
        {
            int temp = prev->prevNode->data;
            prev->prevNode->data = node->data;
            node->data = temp;

            prev->prevNode = prev->prevNode->next;
        }
    }

public:
    void reverseDataRec()
    {
        pairReve *prev = new pairReve();
        prev->prevNode = head;

        reverseDataRec_(head, prev, 0);
    }

private:
    Node *reverseList_(Node *node)
    {
        Node *prev = nullptr;
        Node *curr = node;

        while (curr != nullptr)
        {
            Node *forw = curr->next;
            curr->next = prev;
            prev = curr;
            curr = forw;
        }

        return prev;
    }

public:
    void reverseList()
    {
        Node *prev = reverseList_(head);

        tail = head;
        head = prev;
    }

    bool isPlaindrome()
    {
        Node *midNode = mid();
        Node *nhead = midNode->next;
        midNode->next = nullptr;

        Node *prev = reverseList_(nhead);

        Node *list1 = prev;
        Node *list2 = head;
        bool flag = true;
        while (list1 != nullptr && list2 != nullptr)
        {
            if (list1->data != list2->data)
                flag = false;

            list1 = list1->next;
            list2 = list2->next;
        }

        prev = reverseList_(prev);
        midNode->next = nhead;

        return flag;
    }
};

void solve()
{
    LinkedList ll;
    for (int i = 1; i <= 10; i++)
    {
        ll.addLast(i * 10);
    }

    for (int i = 10; i >= 1; i--)
    {
        ll.addLast(i * 10);
    }

    // ll.removeLast();
    ll.display();

    // ll.reverseDataRec();
    // ll.reverseList();

    cout << ll.isPlaindrome() << endl;
    // ll.display();
}

int main()
{
    solve();
    return 0;
}