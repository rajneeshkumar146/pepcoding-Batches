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

//Leetcode 876
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

ListNode *middleNode2(ListNode *head)
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

//Leetcode 206
ListNode *reverseList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *prev = nullptr;
    ListNode *forw = nullptr;
    ListNode *curr = head;

    while (curr != nullptr)
    {
        forw = curr->next; // backup

        curr->next = prev; // link

        prev = curr; // move
        curr = forw;
    }

    return prev;
}

void reveseData(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return;

    ListNode *mid = middleNode2(head);
    ListNode *tHead = mid->next;
    mid->next = nullptr;

    tHead = reverseList(tHead);
    ListNode *c1 = head;
    ListNode *c2 = tHead;

    while (c1 != nullptr && c2 != nullptr)
    {
        int temp = c1->val;
        c1->val = c2->val;
        c2->val = temp;

        c1 = c1->next;
        c2 = c2->next;
    }

    tHead = reverseList(tHead);
    mid->next = tHead;
}

//Leetcode 234

bool isPalindrome(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return true;

    ListNode *mid = middleNode2(head);
    ListNode *tHead = mid->next;
    mid->next = nullptr;

    tHead = reverseList(tHead);
    ListNode *c1 = head;
    ListNode *c2 = tHead;

    bool res = true;
    while (c1 != nullptr && c2 != nullptr)
    {
        if (c1->val != c2->val)
        {
            res = false;
            break;
        }

        c1 = c1->next;
        c2 = c2->next;
    }

    tHead = reverseList(tHead);
    mid->next = tHead;

    return res;
}

//Leetcode 143
void reorderList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return;

    ListNode *mid = middleNode2(head);
    ListNode *tHead = mid->next;
    mid->next = nullptr;

    tHead = reverseList(tHead);
    ListNode *c1 = head;
    ListNode *c2 = tHead;

    ListNode *f1 = nullptr;
    ListNode *f2 = nullptr;

    while (c1 != nullptr && c2 != nullptr)
    {
        f1 = c1->next; // backup
        f2 = c2->next;

        c1->next = c2; // link
        c2->next = f1;

        c1 = f1; // move
        c2 = f2;
    }
}

ListNode *th1 = nullptr;
ListNode *tt1 = nullptr;
ListNode *th2 = nullptr;
ListNode *tt2 = nullptr;

void addFirst(ListNode *node)
{
    if (th1 == nullptr)
    {
        th1 = node;
        tt1 = node;
    }
    else
    {
        node->next = th1;
        th1 = node;
    }
}

void addLast(ListNode *node)
{
    if (th2 == nullptr)
    {
        th2 = node;
        tt2 = node;
    }
    else
    {
        tt2->next = node;
        tt2 = node;
    }
}

ListNode *orderList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return;

    int cnt = 0;
    ListNode *curr = head;
    while (curr != nullptr)
    {
        ListNode *forw = curr->next;
        curr->next = nullptr;

        if (cnt == 0)
            addLast(curr);
        else
            addFirst(curr);

        curr = forw;
        cnt = (cnt + 1) % 2;
    }

    tt2->next = th1;
    return th2;
}

//Leetcode 
ListNode *mergeTwoLists(ListNode *l1, ListNode *l2)
{
    if(l1 == nullptr || l2 == nullptr) return (l1 == nullptr? l2:l1);

    ListNode* dummy=new ListNode(-1);
    ListNode* prev = dummy;

    ListNode* c1 = l1;
    ListNode* c2 = l2;
    while(c1!=nullptr && c2!=nullptr){
        
        if(c1->val < c2->val){
            prev->next = c1;
            c1=c1->next;
        }else{
            prev->next = c2;
            c2=c2->next;
        }

        prev = prev->next;
    }

    if(c1!=nullptr) prev->next = c1;
    else prev->next = c2;

    return dummy->next;
}
