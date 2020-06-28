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

ListNode *midNode(ListNode *node)
{
    if (node == nullptr || node->next == nullptr)
        return node;

    ListNode *slow = node, *fast = node;
    while (fast->next != nullptr && fast->next->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
    }
    return slow;
}

ListNode *mergeTwoLists(ListNode *l1, ListNode *l2)
{
    if (l1 == nullptr || l2 == nullptr)
        return l1 == nullptr ? l2 : l1;

    ListNode *head = new ListNode(-1); // dummy Node.
    ListNode *prev = head;

    ListNode *c1 = l1, *c2 = l2;

    while (c1 != nullptr && c2 != nullptr)
    {
        if (c1->val <= c2->val)
        {
            prev->next = c1;
            prev = c1;
            c1 = c1->next;
        }
        else
        {
            prev->next = c2;
            prev = c2;
            c2 = c2->next;
        }
    }

    if (c2 != nullptr)
        prev->next = c2;
    else
        prev->next = c1;

    return head->next;
}

//Leetcode 148.
ListNode *sortList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *mid = midNode(head);
    ListNode *nhead = mid->next;
    mid->next = nullptr;

    return mergeTwoLists(sortList(head), sortList(nhead));
}




