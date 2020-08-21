#include <iostream>

using namespace std;

class queue
{
    int *arr;
    int f;
    int b;
    int Elements;
    int TotalSize;

protected:
    void assignSize(int n)
    {
        this->arr = new int[n];
        this->f = 0;
        this->b = 0;
        this->Elements = 0;
        this->TotalSize = n;
    }

    int capacity()
    {
        return this->TotalSize;
    }

public:
    queue()
    {
        assignSize(5);
    }

    queue(int n)
    {
        assignSize(n);
    }

    bool isEmpty()
    {
        return this->Elements == 0;
    }

    int size()
    {
        return this->Elements;
    }

    void display()
    {
        string str = "[";
        for (int i = 0; i < this->Elements; i++)
        {
            int idx = (this->f + i) % this->TotalSize;
            str += to_string(this->arr[idx]);
            if (i != this->Elements - 1)
                str += ",";
        }
        str += "]";
        cout << str << endl;
    }

    void push(int val)
    {
        if (this->Elements == this->TotalSize)
            return;

        this->arr[this->b] = val;
        this->b = (this->b + 1) % this->TotalSize;
        this->Elements++;
    }

    int front()
    {
        if (this->Elements == 0)
            return -1;

        return this->arr[this->f];
    }

    int pop()
    {
        if (this->Elements == 0)
            return -1;
        int rv = this->arr[this->f];
        this->arr[this->f] = 0;

        this->f = (this->f + 1) % this->TotalSize;
        this->Elements--;

        return rv;
    }
};

void solve()
{
    queue que;
    que.push(10);
    que.push(20);
    que.push(30);
    que.push(40);
    que.push(50);

    que.pop();
    que.pop();
    que.push(60);
    que.push(70);

    que.display();
}

int main()
{
    solve();
    return 0;
}