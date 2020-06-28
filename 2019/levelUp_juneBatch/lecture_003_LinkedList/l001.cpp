#include <iostream>
using namespace std;

class Linkedlist
{

    class Node
    {
        int data = 0;
        Node *next = nullptr;

        Node(int data)
        {
            this->data = data;
        }
    };

    int sz = 0;
    Node *head = nullptr;
    Node *tail = nullptr;

    // Basic functions.===================================
public:
    int size()
    {
        return this->sz;
    }

    bool isEmpty()
    {
        return this->sz == 0;
    }

private:
    Node *getNodeAt(int idx)
    {
        return nullptr;
    }

public:
    // AddFunctions.========================================

    // removeFunctions.=====================================

    // getFunctions.========================================
};

void solve()
{

    Linkedlist ll;
}

int main()
{
    solve();
    
    return 0;
}
