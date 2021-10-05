#include <iostream>
#include <exception>
using namespace std;
class stack
{
private:
    int *arr;
    int tos;
    int NoOfElements;
    int MaxCapacity;

public:
    stack(int size)
    {
        initialize(size);
    }

    stack()
    {
        stack(10);
    }

protected:
    void initialize(int size)
    {
        this->NoOfElements = 0;
        this->MaxCapacity = size;
        this->arr = new int[this->MaxCapacity];
        this->tos = -1;
    }

public:
    void push(int data)
    {
        if (this->NoOfElements == this->MaxCapacity)
            return;
        this->arr[++this->tos] = data;
        this->NoOfElements++;
    }

    int peek()
    {
        if (this->NoOfElements == 0)
            return -1;
        return this->arr[this->tos];
    }

    int pop()
    {
        if (this->NoOfElements == 0)
            return -1;
        int rv = this->arr[this->tos--];
        this->NoOfElements--;
        return rv;
    }
};