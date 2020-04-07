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
ListNode *reverseList(ListNode *head)
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
    mid->next = nhead;

    return true;
}

//leetcode 141.================================================

bool hasCycle(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return false;

    ListNode *slow = head;
    ListNode *fast = head;
    while (fast != nullptr && fast->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
        if (slow == fast)
            break;
    }

    return slow == fast;
}

//leetcode 142.====================================================

ListNode *detectCycle(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return nullptr;

    ListNode *slow = head;
    ListNode *fast = head;
    while (fast != nullptr && fast->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
        if (slow == fast)
            break;
    }

    if (slow == fast)
    {
        slow = head;
        while (slow != fast)
        {
            slow = slow->next;
            fast = fast->next;
        }
        return slow;
    }

    return nullptr;
}

//leetcode 160.========================================

ListNode *getIntersectionNode(ListNode *headA, ListNode *headB)
{

    if (headA == nullptr || headB == nullptr)
        return nullptr;
    if (headA->next == nullptr && headB->next == nullptr && headA->val == headB->val)
        return headA;

    ListNode *tail = nullptr;
    ListNode *curr = headA;
    while (curr != nullptr)
    {
        tail = curr;
        curr = curr->next;
    }

    tail->next = headB; //cycle
    ListNode *ans = detectCycle(headA);
    tail->next = nullptr;

    return ans;
}

ListNode *mergeTwoLists(ListNode *A, ListNode *B)
{
    if (A == nullptr)
        return B;
    if (B == nullptr)
        return A;

    ListNode *head = new ListNode(0); // dummyNode;
    ListNode *curr = head;

    while (A != nullptr && B != nullptr)
    {
        if (A->val <= B->val)
        {
            curr->next = A;
            A = A->next;
        }
        else
        {
            curr->next = B;
            B = B->next;
        }
        curr = curr->next;
    }

    if (A != nullptr)
        curr->next = A;
    else if (B != nullptr)
        curr->next = B;

    return head->next;
}

//leetcode 148.===========================================

ListNode *sortList(ListNode *head)
{
    if(head==nullptr || head->next==nullptr) return head;

    ListNode* mid=middleNodeByINDEX(head);
    ListNode* nhead=mid->next;
    mid->next=nullptr;

    ListNode* a=sortList(head);
    ListNode* b=sortList(nhead);
    return mergeTwoLists(a,b);
}
