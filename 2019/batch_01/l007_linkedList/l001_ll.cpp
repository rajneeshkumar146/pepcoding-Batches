#include <iostream>
#include <list>
#include <unordered_map>

using namespace std;

class LRU
{
    list<int> ll;
    unordered_map<int, int> map;
    int defaultSize = 4;

    void set(int key, int val)
    {
        if (map.find(key) == map.end())
        {
            if (ll.size() == defaultSize)
            {
                int lastKey = ll.back();
                ll.pop_back();
                map.erase(lastKey);
            }

            ll.push_front(key);
            map[key] = val;
        }
        else
        {
            ll.remove(key);
            ll.push_front(key);
            map[key] = val;
        }
    }

    int get(int key)
    {
        if (map.find(key) == map.end())
            return -1;
        else
        {
            ll.remove(key);
            ll.push_front(key);
            return map[key];
        }
    }
};

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

private:
    void addFirstNode(Node *node)
    {
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

    void addLastNode(Node *data)
    {
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

    Node *removeFirstNode()
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

        size--;
        return rn;
    }

public:
    void addFirst(int data)
    {
        Node *node = new Node(data);
        addFirstNode(node);
    }

    void addLast(int data)
    {
        Node *node = new Node(data);
        addLastNode(node);
    }

    int removeFirst()
    {
        Node *rn = removeFirstNode();
        if (rn == nullptr)
            return -1;
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

    void fold()
    {
        Node *midNode = mid();
        Node *nhead = midNode->next;
        midNode->next = nullptr;

        Node *prev = reverseList_(nhead);

        Node *curr1 = head;
        Node *curr2 = prev;

        while (curr2 != nullptr)
        {
            Node *cn1 = curr1->next;
            Node *cn2 = curr2->next;

            curr1->next = curr2;
            curr2->next = cn1;

            curr1 = cn1;
            curr2 = cn2;
        }

        if (curr1 == nullptr)
            tail = nhead;
        else
            tail = curr1;
    }

private:
    Node *detectCycle_()
    {

        Node *slow = head;
        Node *fast = head;

        while (fast != nullptr && fast->next != nullptr)
        {
            slow = slow->next;
            fast = fast->next->next;

            if (slow == fast)
                return slow;
        }

        return nullptr;
    }

public:
    bool detectCycle()
    {
        return detectCycle_() != nullptr;
    }

    int intersectionPoint()
    {
        Node *node1 = detectCycle_();

        if (node1 != nullptr)
        {
            Node *node2 = head;
            while (node2 != slow)
            {
                node1 = node1->next;
                node2 = node2->next;
            }

            return slow->data;
        }
        return -1;
    }
};

void solve()
{
    LinkedList ll;
    for (int i = 1; i <= 10; i++)
    {
        ll.addLast(i * 10);
    }

    // for (int i = 10; i >= 1; i--)
    // {
    //     ll.addLast(i * 10);
    // }

    // ll.removeLast();
    ll.display();

    // ll.reverseDataRec();
    // ll.reverseList();

    // cout << ll.isPlaindrome() << endl;
    // ll.display();

    ll.fold();
    ll.addLast(100);
    ll.display();
}

int main()
{
    solve();
    return 0;
}