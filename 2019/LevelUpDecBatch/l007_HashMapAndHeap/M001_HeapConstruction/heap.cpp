#include <iostream>
#include <vector>

using namespace std;

class heap
{

private:
    vector<int> arr;
    bool isMaxHeap = true;

    void constructHeap()
    { // O(nLogn) -> O(n)
        for (int i = arr.size() - 1; i >= 0; i--)
        {
            downHeapify(i);
        }
    }

    void defaultValue(bool isMaxHeap)
    {
        this->isMaxHeap = isMaxHeap;
    }

    bool compareTo(int i, int j)
    {
    }

public:
    heap()
    {
        defaultValue(true);
    }

    heap(bool isMaxHeap)
    {
        defaultValue(isMaxHeap);
    }

    heap(vector<int> &arr, bool isMaxHeap)
    {
        defaultValue(isMaxHeap);

        for (int ele : arr)
            this->arr.push_back(ele);

        constructHeap();
    }

    int size()
    {
        return this->arr.size();
    }

    bool isEmpty()
    {
        return this->arr.size() == 0;
    }

    void add(int data)
    {
        this->arr.push_back(data);
        int n = this->arr.size();

        upHeapify(n - 1);
    }

    int remove()
    { // O(Logn)
        int rEle = arr[0];

        int n = this->arr.size();
        swap(this->arr[0], this->arr[n - 1]);
        this->arr.pop_back();

        downHeapify(0);
        return rEle;
    }

    int top()
    { // O(1)
        return this->arr[0];
    }

private:
    void downHeapify(int pi)
    { // O(logn)
        int maxIdx = pi;
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;

        if (lci < this->arr.size() && this->arr[lci] > this->arr[maxIdx])
            maxIdx = lci;

        if (rci < this->arr.size() && this->arr[rci] > this->arr[maxIdx])
            maxIdx = rci;

        if (maxIdx != pi)
        {
            swap(this->arr[pi], this->arr[maxIdx]);
            downHeapify(maxIdx);
        }
    }

    void upHeapify(int ci) // O(logn)
    {
        int pi = (ci - 1) / 2;

        if (pi >= 0 && this->arr[ci] > this->arr[pi])
        {
            swap(this->arr[pi], this->arr[ci]);
            upHeapify(pi);
        }
    }
};

//int[] arr2 = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };