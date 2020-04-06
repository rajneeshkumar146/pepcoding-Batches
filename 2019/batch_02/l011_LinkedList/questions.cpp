#include <iostream>
using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

ListNode *middleNodeByINDEX(ListNode *head)
{ // first middle node is our mid in even size.
    ListNode *slow = head;
    ListNode *fast = head;

    while (fast != nullptr && fast->next != nullptr && fast->next->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
    }
    return slow;
}

//leetcode 876.=================================================
ListNode *middleNode(ListNode *head) // second middle node is our mid in even size.
{
    ListNode *slow = head;
    ListNode *fast = head;

    while (fast != nullptr && fast->next != nullptr && fast->next->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
    }
    return fast->next == nullptr ? slow : slow->next;
}

//leetcode 206=======================================================
ListNode* reverseList(ListNode* head)
{
    ListNode *prev = nullptr;
    ListNode *curr = head;
    ListNode *forw = nullptr;

    while (curr != nullptr)
    {
        forw = curr->next; //backup.

        curr->next = prev; // link join.

        prev = curr; // move forward.
        curr = forw;
    }

    return prev; // new head.
}

//leetcode 234.================================================
bool isPalindrome(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return true;

    ListNode *mid = middleNodeByINDEX(head);
    ListNode *nhead = mid->next;
    mid->next = nullptr;

    nhead = reverseList(nhead);

    ListNode *curr1 = head;
    ListNode *curr2 = nhead;

    while (curr1 != nullptr && curr2 != nullptr)
    {
        if (curr1->val != curr2->val)
            return false;

        curr1 = curr1->next;
        curr2 = curr2->next;
    }

    nhead = reverseList(nhead);
    mid->next=nhead;

    return true;
}

//leetcode 141.================================================


