#include <iostream>
using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

//876
ListNode *middleNode(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *slow = head;
    ListNode *fast = head;
    while (fast != nullptr && fast->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow;
}

ListNode *midNode(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *slow = head;
    ListNode *fast = head;
    while (fast->next != nullptr && fast->next->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow;
}

//206
ListNode *reverseList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *prev = nullptr;
    ListNode *curr = head;

    while (curr != nullptr)
    {
        ListNode *forw = curr->next; // backup

        curr->next = prev; // link

        prev = curr; // move
        curr = forw;
    }

    return prev;
}

//234
bool isPalindrome(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return true;

    ListNode *mid = midNode(head);
    ListNode *nhead = mid->next;
    mid->next = nullptr;

    nhead = reverseList(nhead);

    ListNode *curr1 = head;
    ListNode *curr2 = nhead;

    bool res = true;
    while (curr1 != nullptr && curr2 != nullptr)
    {
        if (curr1->val != curr2->val)
        {
            res = false;
            break;
        }

        curr1 = curr1->next;
        curr2 = curr2->next;
    }

    nhead = reverseList(nhead);
    mid->next = nhead;

    return res;
}

void dataReverse(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return;

    ListNode *mid = midNode(head);
    ListNode *nhead = mid->next;
    mid->next = nullptr;

    nhead = reverseList(nhead);

    ListNode *curr1 = head;
    ListNode *curr2 = nhead;

    while (curr1 != nullptr && curr2 != nullptr)
    {

        int temp = curr1->val;
        curr1->val = curr2->val;
        curr2->val = temp;

        curr1 = curr1->next;
        curr2 = curr2->next;
    }

    nhead = reverseList(nhead);
    mid->next = nhead;
}

//143
void reorderList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return;

    ListNode *mid = midNode(head);
    ListNode *nhead = mid->next;
    mid->next = nullptr;

    nhead = reverseList(nhead);

    ListNode *c1 = head;
    ListNode *c2 = nhead;

    ListNode *f1 = nullptr;
    ListNode *f2 = nullptr;

    while (c1 != nullptr && c2 != nullptr)
    {
        f1 = c1->next; // backup
        f2 = c2->next;

        c1->next = c2; // links
        c2->next = f1;

        c1 = f1; // move
        c2 = f2;
    }

    // cout<<"Reorder List: ";
    // printList(head);

    // cout<<"Actual List: ";
    // againReorderList(head);
    // printList(head);
}

void printList(ListNode *node)
{
    ListNode *curr = node;
    while (curr != nullptr)
    {
        cout << curr->val << " ";
        curr = curr->next;
    }
    cout << endl;
}

void againReorderList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return;

    ListNode *h1 = head;
    ListNode *h2 = head->next;

    ListNode *c1 = h1;
    ListNode *c2 = h2;

    while (c2 != nullptr && c2->next != nullptr)
    {
        ListNode *f = c2->next; // Backup

        c1->next = f; // links
        c2->next = f->next;

        c1 = c1->next;
        c2 = c2->next;
    }

    // c1->next = nullptr;
    h2 = reverseList(h2);
    c1->next = h2;
}

ListNode *mergeTwoLists(ListNode *l1, ListNode *l2)
{
    if (l1 == nullptr || l2 == nullptr)
        return l1 != nullptr ? l1 : l2;

    ListNode *dummy = new ListNode(-1);
    ListNode *prev = dummy;

    ListNode *c1 = l1;
    ListNode *c2 = l2;

    while (c1 != nullptr && c2 != nullptr)
    {
        if (c1->val <= c2->val)
        {
            prev->next = c1;
            c1 = c1->next;
        }
        else
        {
            prev->next = c2;
            c2 = c2->next;
        }

        prev = prev->next;
    }

    prev->next = c1 != nullptr ? c1 : c2;

    ListNode *h = dummy->next;
    dummy->next = nullptr;
    delete dummy;   
    return h;
}
