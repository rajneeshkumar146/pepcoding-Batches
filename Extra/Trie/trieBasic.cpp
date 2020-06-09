#include <iostream>
#include <vector>

using namespace std;

class Node
{
public:
    int WordEnd = 0;
    vector<Node *> childs;

    Node()
    {
        this->childs.assign(26, nullptr);
        this->WordEnd = 0;
    }
};

Node *root = nullptr;

void insert(string word)
{
    Node *curr = root;
    int n = word.length();
    for (int i = 0; i < n; i++)
    {
        int idx = word[i] - 'a';
        if (curr->childs[idx] == nullptr)
            curr->childs[idx] = new Node();

        curr = curr->childs[idx];
    }
    curr->WordEnd++;
}
