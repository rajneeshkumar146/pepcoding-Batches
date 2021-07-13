import java.util.ArrayList;

public class heap {
    // Data Members======================================

    private ArrayList<Integer> arr;
    private int noOfEle = 0;
    private boolean isMaxHeap = true;

    // Constructors=======================================

    private void intialize(boolean isMaxHeap) {
        this.arr = new ArrayList<>();
        this.noOfEle = 0;
        this.isMaxHeap = isMaxHeap;
    }

    public heap(boolean isMaxHeap) {
        intialize(isMaxHeap);
    }

    public heap() {
        this(true);
    }

    // O(n)
    public heap(int[] data, boolean isMaxHeap) {
        this(isMaxHeap);

        for (int ele : data)
            this.arr.add(ele);

        this.noOfEle = this.arr.size();

        for (int i = this.noOfEle - 1; i >= 0; i--) { // NLogN -> N
            downHeapify(i);
        }
    }

    // exceptions.==============================================

    private void UnderFlowPointerException() throws Exception {
        if (this.noOfEle == 0)
            throw new Exception("HeapUnderFlowException");
    }

    // Basics Functions.=========================================

    public int size() {
        return this.noOfEle;
    }

    public boolean isEmpty() {
        return this.noOfEle == 0;
    }

    // DS Functions.==============================================

    public int compareTo(int t, int o) {
        if (isMaxHeap) {
            return this.arr.get(t) - this.arr.get(o);
        } else {
            return this.arr.get(o) - this.arr.get(t);
        }
    }

    private void swap(int i, int j) {
        int e1 = this.arr.get(i);
        int e2 = this.arr.get(j);

        this.arr.set(i, e2);
        this.arr.set(j, e1);
    }

    // O(LogN)
    private void downHeapify(int pi) {
        int maxIdx = pi, lci = 2 * pi + 1, rci = 2 * pi + 2;
        if (lci < this.noOfEle && compareTo(lci, maxIdx) > 0)
            maxIdx = lci;
        if (rci < this.noOfEle && compareTo(rci, maxIdx) > 0)
            maxIdx = rci;

        if (maxIdx != pi) {
            swap(pi, maxIdx);
            downHeapify(maxIdx);
        }
    }

    // O(LogN)
    private void upheapify(int ci) {
        int pi = (ci - 1) / 2;
        if (compareTo(ci, pi) > 0) {
            swap(ci, pi);
            upheapify(pi);
        }
    }

    // O(1)
    public int peek() throws Exception {
        UnderFlowPointerException();
        return this.arr.get(0);
    }

    // O(LogN)
    public int remove() throws Exception {
        UnderFlowPointerException();

        int rEle = this.arr.get(0);
        swap(0, this.noOfEle - 1);
        this.arr.remove(this.noOfEle - 1);

        this.noOfEle--;
        downHeapify(0);

        return rEle;
    }

    // O(LogN)
    public void add(int data) {
        this.arr.add(data);
        this.noOfEle++;
        upheapify(this.noOfEle - 1);
    }

}