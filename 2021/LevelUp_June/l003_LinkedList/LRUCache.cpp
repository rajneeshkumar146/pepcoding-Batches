#include <unordered_map>

using namespace std;

class LRUCache
{

    // data Members
private:
    class ListNode
    {
    public:
        int key, value;
        ListNode *next = NULL;
        ListNode *prev = NULL;

        ListNode(int key, int value)
        {
            this->key = key;
            this->value = value;
        }
    };

    unordered_map<int, ListNode *> map;
    int capacity;
    int size;
    ListNode *head = NULL;
    ListNode *tail = NULL;

public:
    LRUCache(int capacity)
    {
        this->capacity = capacity;
        this->size = 0;
        this->map.clear();
    }

private:

    void addLast(ListNode* node){

    }

    void remove(ListNode* node){

    }

    void makeRecent(ListNode *node)
    {
        remove(node);
        addLast(node);
    }

public:
    int get(int key)
    {
        if (map.find(key) == map.end())
            return -1;

        ListNode *node = map[key];
        makeRecent(node);
        return node->value;
    }

    void put(int key, int value)
    {
        if (map.find(key) != map.end())
        {
            ListNode *node = map[key];
            node->value = value;
            makeRecent(node);
        }
        else
        {
            ListNode *node = new ListNode(key, value);
            if (this->size == this->capacity)
            {
                remove(this->head);
            }

            addLast(node);
        }
    }
};
