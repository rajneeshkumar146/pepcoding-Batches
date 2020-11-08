#include <iostream>
#include <vector>

using namespace std;

class stack
{
private:
    vector<int> arr;

    // int *arr2;
    // int maxSize;

    int tos;          // top of stack
    int elementCount; // total elements present in stack.

    void intializeValues(int size)
    {
        // this->arr2 = new int[size];
        // this->maxSize = size;

        this->arr.resize(size, 0);
        this->tos = -1;
        this->elementCount = 0;
    }

public:
    stack()
    {
        intializeValues(10);
    }

    stack(int size)
    {
        intializeValues(size);
    }

    int size()
    {
        return this->elementCount;
    }

    bool isEmpty()
    {
        return this->elementCount == 0;
    }

private:
    int capacity()
    {
        return this->arr.size();
        // return this->maxSize
    }

public:
    void push(int data)
    {
        // if (this->size() == this->capacity())
        // {
        //     throw new Exception("StackIsFull");
        // }

        this->arr[++this->tos] = data;
        this->elementCount++;
    }

    int peek()
    {
        // if(this.isEmpty()){
        //     throw new Exception("StackIsEmpty");
        // }

        return this->arr[this->tos];
    }

    int pop()
    {
        // if(this.isEmpty()){
        //     throw new Exception("StackIsEmpty");
        // }

        int rv = this->arr[this->tos];
        this->arr[this->tos--] = 0;
        this->elementCount--;

        return rv;
    }
};

int main()
{
    stack st;
    st.push(10);
    cout << st.peek() << endl;
    return 0;
}