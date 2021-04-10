using namespace std;

class stack
{
private:
    int *arr;
    int tos;
    int NoOfElements;
    int MaxCapacity;

protected:
    void intialize(int size)
    {
        this->arr = new int[size];
        this->tos = -1;
        this->NoOfElements = 0;
        this->MaxCapacity = size;
    }

    void StackEmptyException()
    {
        if (this->NoOfElements == 0)
            throw("StackISEmpty");
    }

    void StackOverflowException()
    {
        if (this->NoOfElements == this->MaxCapacity)
            throw("StackOverflow");
    }

    void push_(int data)
    {
        this->arr[++this->tos] = data;
        this->NoOfElements++;
    }

    int top_()
    {
        return this->arr[this->tos];
    }

    int pop_()
    {
        int rv = this->arr[this->tos];
        this->arr[this->tos] = 0;
        this->tos--;
        this->NoOfElements--;

        return rv;
    }

public:
    int size()
    {
        return this->NoOfElements;
    }

    bool isEmpty()
    {
        return this->NoOfElements == 0;
    }

    void push(int data)
    {
        StackOverflowException();
        push_(data);
    }

    int top()
    {
        StackEmptyException();
        return top_();
    }

    int pop()
    {
        StackEmptyException();
        return pop_();
    }
};

class dynamicStack : stack
{
}
